public class CertificateStudent extends NonDegreeSeekingStudent {
    private String certificateType; 

    public CertificateStudent(String id, String firstName, String lastName, int age, 
                              int credits, String certificateType) {
        super(id, firstName, lastName, age, credits);
        this.certificateType = certificateType;
    }

    @Override
    public double computeFees() {
        return 700 + (300 * this.credits);
    }

    @Override
    public void printData() {
        System.out.println("ID number: " + id);
        System.out.println("Name:\n" + firstName + " " + lastName);
        System.out.println("Age:\n" + age);
        System.out.printf("%s is a certificate-seeking student enrolled in %d credits\n", firstName, credits);
        System.out.printf("%s is pursuing a certificate in %s\n", firstName, certificateType);
    }
}