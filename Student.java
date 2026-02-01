public abstract class Student {
    protected String id; 
    protected String firstName;
    protected String lastName;
    protected int age;
    protected int credits;

    public Student(String id, String firstName, String lastName, int age, int credits) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.credits = credits;
    }

    public abstract void printData();
    public abstract double computeFees();
}