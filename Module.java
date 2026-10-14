public class Module {
    //variables
    private String code;
    private String name;
    private double grade;

    //constructor
    public Module() {

    }

    public Module(String code, String name, double grade) {
        this.code = code;
        this.name = name;
        this.grade = grade;
    }

    //methods
    public String getCode() { return code; }

    public void setCode(String code) { this.code = code; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public double getGrade() { return grade; }

    public void setGrade(double grade) { this.grade = grade; }

    public String toString() {
        return "\nModule Details - " +
                "Code: " + code +
                " Name: " + name +
                " grade: " + grade ;
    }

}
