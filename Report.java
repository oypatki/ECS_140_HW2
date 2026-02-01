import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
//----------DONT EDIT ABOVE THIS LINE-----------

public class Report {
    public static String generateReport() {
        Student[] students = new Student[100]; 
        int studentCount = 0;

        try {
            Scanner sc = new Scanner(new File("hw2.txt")); 
            while (sc.hasNextLine() && studentCount < 100) {
                String line = sc.nextLine();
                if (line.trim().isEmpty()) continue;
                String[] p = line.split(";"); 

                String id = p[0].trim();
                String fName = p[1].trim();
                String lName = p[2].trim();
                int age = Integer.parseInt(p[3].trim());
                int credits = Integer.parseInt(p[4].trim());
                String isDegree = p[5].trim();

                if (isDegree.equalsIgnoreCase("Y")) {
                    String major = mapMajor(p[6].trim()); 
                    String stand = mapStanding(p[7].trim());
                    String hasAid = p[8].trim();
                    if (hasAid.equalsIgnoreCase("Y")) {
                        double aid = Double.parseDouble(p[9].trim());
                        students[studentCount++] = new FinancialAidStudent(id, fName, lName, age, credits, major, stand, aid);
                    } else {
                        students[studentCount++] = new NonFinancialAidStudent(id, fName, lName, age, credits, major, stand);
                    }
                } else {
                    String type = p[6].trim(); 
                    if (type.equalsIgnoreCase("C")) {
                        students[studentCount++] = new CertificateStudent(id, fName, lName, age, credits, mapMajor(p[7].trim()));
                    } else {
                        students[studentCount++] = new SeniorCitizen(id, fName, lName, age, credits);
                    }
                }
            }
            sc.close();
        } catch (FileNotFoundException e) {
            return "Error: hw2.txt not found.";
        }

        // ----------DONT EDIT BELOW THIS LINE-----------
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(baos);
        PrintStream old = System.out;
        System.setOut(ps);
        // ----------DONT EDIT ABOVE THIS LINE-----------

        for (int i = 0; i < studentCount; i++) {
            students[i].printData(); 
            System.out.println(); 
        }

        System.out.println("Summary of each student's fees assessed: "); 
        System.out.println(); 
        
        for (int i = 0; i < studentCount; i++) {
            System.out.printf("%s %s has $%,.0f fees assessed \n", 
                students[i].firstName, students[i].lastName, students[i].computeFees());
        }
        System.out.println(); 

        double degNoAid = 0, degAid = 0, cert = 0, senior = 0;
        for (int i = 0; i < studentCount; i++) {
            double fee = students[i].computeFees();
            if (students[i] instanceof NonFinancialAidStudent) degNoAid += fee;
            else if (students[i] instanceof FinancialAidStudent) degAid += fee;
            else if (students[i] instanceof CertificateStudent) cert += fee;
            else if (students[i] instanceof SeniorCitizen) senior += fee;
        }

        System.out.println("Summary of all student fees assessed: ");
        System.out.println(); 
        
        System.out.printf("Degree-seeking students without financial assistance: $%,.0f\n", degNoAid);
        System.out.printf("Degree-seeking students with financial assistance: $%,.0f\n", degAid);
        System.out.printf("Certificate students: $%,.0f\n", cert);
        System.out.printf("Senior citizens: $%,.0f\n", senior);
        
        System.out.println(); 
        System.out.printf("Total fees assessed: $%,.0f\n", (degNoAid + degAid + cert + senior));

        // ----------DONT EDIT BELOW THIS LINE-----------
        System.out.flush();
        System.setOut(old);
        return baos.toString();
    }

    private static String mapMajor(String c) {
        switch(c.toUpperCase()) {
            case "S": return "gaming Science"; 
            case "M": return "hotel Management";
            case "A": return "lounge Arts"; 
            case "E": return "beverage Engineering";
            default: return "Unknown";
        }
    }
    private static String mapStanding(String c) {
        switch(c.toUpperCase()) {
            case "G": return "good"; 
            case "W": return "warning";
            case "P": return "probation"; 
            default: return "Unknown";
        }
    }
}