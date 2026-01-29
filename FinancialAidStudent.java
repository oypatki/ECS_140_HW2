public class FinancialAidStudent extends DegreeSeekingStudent {
    private double aidAmount;

    public FinancialAidStudent(String id, String firstName, String lastName, int age, 
                               int credits, String major, String standing, double aidAmount) {
        super(id, firstName, lastName, age, credits, major, standing);
        this.aidAmount = aidAmount;
    }

    @Override
    public double computeFees() {
        int billableCredits = Math.min(this.credits, 12);
        double baseFees = 150 + (billableCredits * 275);
        
        double finalTotal = baseFees - aidAmount;
        return Math.max(0, finalTotal);
    }

    @Override
    public void printData() {
        System.out.println("ID number: " + id);
        System.out.println("Name:\n" + firstName + " " + lastName); 
        System.out.println("Age:\n" + age);
        System.out.printf("%s is a degree-seeking student enrolled in %d credits\n", firstName, credits);
        System.out.printf("%s receives $%.2f in financial assistance per term\n", firstName, aidAmount);
        System.out.printf("%s's major is %s\n", firstName, major);
        System.out.printf("%s's academic standing is %s\n", firstName, academicStanding);
    }
}