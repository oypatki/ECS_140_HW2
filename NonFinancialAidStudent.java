public class NonFinancialAidStudent extends DegreeSeekingStudent {

    public NonFinancialAidStudent(String id, String firstName, String lastName, int age, 
                                  int credits, String major, String standing) {
        super(id, firstName, lastName, age, credits, major, standing);
    }

    @Override
    public double computeFees() {
        int billableCredits = Math.min(this.credits, 12);
        return 150 + (billableCredits * 275);
    }

    @Override
    public void printData() {
        System.out.println("ID number: " + id);
        System.out.println("Name:\n" + firstName + " " + lastName);
        System.out.println("Age:\n" + age);
        System.out.printf("%s is a degree-seeking student enrolled in %d credits\n", firstName, credits);
        System.out.printf("%s's major is %s\n", firstName, major);
        System.out.printf("%s's academic standing is %s\n", firstName, academicStanding);
    }
}