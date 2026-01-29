public class SeniorCitizen extends NonDegreeSeekingStudent {

    public SeniorCitizen(String id, String firstName, String lastName, int age, int credits) {
        super(id, firstName, lastName, age, credits);
    }

    @Override
    public double computeFees() {
        double total = 100;
        if (this.credits > 6) {
            total += (this.credits - 6) * 50;
        }
        return total;
    }

    @Override
    public void printData() {
        System.out.println("ID number: " + id);
        System.out.println("Name:\n" + firstName + " " + lastName);
        System.out.println("Age:\n" + age);
        System.out.printf("%s is a senior citizen student enrolled in %d credits\n", firstName, credits);
    }
}