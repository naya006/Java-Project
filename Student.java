import java.util.ArrayList;

public class Student {

    //variables
    private String firstName;
    private String lastName;
    private String studyProgramme;
    private int numberOfModules;

    private ArrayList<Module> modules;

    //contractors


    public Student() {
        modules = new ArrayList<Module>();

    }

    public Student(String firstName, String lastName, String studyProgramme, int numberOfModules) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.studyProgramme = studyProgramme;
        this.numberOfModules = numberOfModules;
    }

    //methods
    public String getFirstName() { return firstName; }

    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() { return lastName; }

    public void setLastName(String lastName) { this.lastName = lastName; }

    public String getStudyProgramme() { return studyProgramme; }

    public void setStudyProgramme(String studyProgramme) { this.studyProgramme = studyProgramme; }

    public int getNumberOfModules() { return numberOfModules; }

    public void setNumberOfModules(int numberOfModules) { this.numberOfModules = numberOfModules; }

    //addModule
    public void addModule(String code, String name, Double grade) {
        Module m = new Module(code, name, grade);
        modules.add(m);
    }

    //updateGrade
    public void updateGrade(String moduleCode, double newGrade) {
        for(Module m1 : modules) {
            if (moduleCode.equals(m1.getCode())){
                m1.setGrade(newGrade);
                m1.getName();
            }

        }
    }

    //toString
    public String toString() {
        String mod = "";
        for (Module module : modules) {
            module.toString();
            mod += module.toString();
        }

        return "\nStudent Name: " + firstName +
                " " + lastName +
                "\nCourse: " + studyProgramme +
                mod;
    }


}
