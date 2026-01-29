import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class StudentReportManager {
    public static void main(String[] args) {
        Student[] students = new Student[100];
        int studentCount = 0;

        try {
            File file = new File("hw2.txt");
            Scanner scanner = new Scanner(file);

            while (scanner.hasNextLine() && studentCount < 100) {
                String line = scanner.nextLine();
                if (line.trim().isEmpty()) continue;

                String[] parts = line.split(";");
                
                String id = parts[0].trim();
                String firstName = parts[1].trim();
                String lastName = parts[2].trim();
                int age = Integer.parseInt(parts[3].trim());
                int credits = Integer.parseInt(parts[4].trim());
                String degreeSeekingCode = parts[5].trim();

                if (degreeSeekingCode.equalsIgnoreCase("Y")) {
                    String major = mapMajor(parts[6].trim());
                    String standing = mapStanding(parts[7].trim());
                    String aidCode = parts[8].trim();

                    if (aidCode.equalsIgnoreCase("Y")) {
                        double aidAmount = Double.parseDouble(parts[9].trim());
                        students[studentCount] = new FinancialAidStudent(id, firstName, lastName, age, credits, major, standing, aidAmount);
                    } else {
                        students[studentCount] = new NonFinancialAidStudent(id, firstName, lastName, age, credits, major, standing);
                    }
                } else {
                    String typeCode = parts[6].trim();
                    if (typeCode.equalsIgnoreCase("C")) {
                        String certType = mapMajor(parts[7].trim());
                        students[studentCount] = new CertificateStudent(id, firstName, lastName, age, credits, certType);
                    } else if (typeCode.equalsIgnoreCase("S")) {
                        students[studentCount] = new SeniorCitizen(id, firstName, lastName, age, credits);
                    }
                }
                studentCount++;
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("Error: hw2.txt not found.");
            return;
        }

        generateReports(students, studentCount);
    }

    private static void generateReports(Student[] students, int count) {
        double degNoAidTotal = 0;
        double degAidTotal = 0;
        double certTotal = 0;
        double seniorTotal = 0;

        System.out.println("--- Report 1: Student Fee Assessment ---");
        for (int i = 0; i < count; i++) {
            double fees = students[i].computeFees();
            System.out.printf("%s %s: $%,.2f\n", students[i].firstName, students[i].lastName, fees);

            if (students[i] instanceof NonFinancialAidStudent) degNoAidTotal += fees;
            else if (students[i] instanceof FinancialAidStudent) degAidTotal += fees;
            else if (students[i] instanceof CertificateStudent) certTotal += fees;
            else if (students[i] instanceof SeniorCitizen) seniorTotal += fees;
        }

        System.out.println("\nSummary of student fees assessed:");
        System.out.printf("Degree-seeking students without financial assistance: $%,.0f\n", degNoAidTotal);
        System.out.printf("Degree-seeking students with financial assistance: $%,.0f\n", degAidTotal);
        System.out.printf("Certificate students: $%,.0f\n", certTotal);
        System.out.printf("Senior citizens: $%,.0f\n", seniorTotal);
        System.out.printf("Total fees assessed: $%,.0f\n", (degNoAidTotal + degAidTotal + certTotal + seniorTotal));
    }

    private static String mapMajor(String code) {
        switch (code.toUpperCase()) {
            case "S": return "gaming science";
            case "M": return "hotel management";
            case "A": return "lounge arts";
            case "E": return "beverage engineering";
            default: return "unknown";
        }
    }

    private static String mapStanding(String code) {
        switch (code.toUpperCase()) {
            case "G": return "good";
            case "W": return "warning";
            case "P": return "probation";
            default: return "unknown";
        }
    }
}