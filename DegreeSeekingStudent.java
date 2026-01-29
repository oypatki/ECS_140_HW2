public abstract class DegreeSeekingStudent extends Student {
    protected String major; 
    protected String academicStanding; 

    public DegreeSeekingStudent(String id, String firstName, String lastName, int age, 
                                int credits, String major, String academicStanding) {
        super(id, firstName, lastName, age, credits);
        this.major = major;
        this.academicStanding = academicStanding;
    }

    @Override
    public abstract double computeFees();
}