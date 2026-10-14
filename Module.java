public class Module {
    //variables
    private String code;
    private String name;
    private int credits;
    private Double grade;   // null means no grade has been set yet

    //constructor
    public Module() {

    }

    public Module(String code, String name, int credits, Double grade) {
        this.code = code;
        this.name = name;
        setCredits(credits);
        setGrade(grade);
    }

    //methods
    public String getCode() { return code; }

    public void setCode(String code) { this.code = code; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public int getCredits() { return credits; }

    // Credits must be a whole number from 1 to 120 (UK modules are commonly 15, 30 or 60)
    public void setCredits(int credits) {
        if (credits < 1 || credits > 120) {
            throw new IllegalArgumentException("Credits must be between 1 and 120.");
        }
        this.credits = credits;
    }

    public Double getGrade() { return grade; }

    // Accepts null (no grade yet) or a value from 0 to 100
    public void setGrade(Double grade) {
        if (grade != null && (grade < 0 || grade > 100)) {
            throw new IllegalArgumentException("Grade must be between 0 and 100.");
        }
        this.grade = grade;
    }

    public boolean hasGrade() { return grade != null; }

    // Returns the grade to one decimal place, or N/A if there is no grade yet
    public String getGradeText() {
        if (grade == null) {
            return "N/A";
        }
        return String.format("%.1f", grade);
    }

    public String toString() {
        return "Code: " + code +
                " Name: " + name +
                " Credits: " + credits +
                " Grade: " + getGradeText();
    }

}