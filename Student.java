import java.util.ArrayList;

public class Student {

    //variables
    private String firstName;
    private String lastName;
    private String studyProgramme;

    private ArrayList<Module> modules;

    //constructors
    public Student() {
        modules = new ArrayList<Module>();
    }

    public Student(String firstName, String lastName, String studyProgramme) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.studyProgramme = studyProgramme;
        this.modules = new ArrayList<Module>();
    }

    //methods
    public String getFirstName() { return firstName; }

    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() { return lastName; }

    public void setLastName(String lastName) { this.lastName = lastName; }

    public String getStudyProgramme() { return studyProgramme; }

    public void setStudyProgramme(String studyProgramme) { this.studyProgramme = studyProgramme; }

    //getModuleCount - always matches the real number of modules in the list
    public int getModuleCount() { return modules.size(); }

    //findModule - returns the module with this code (ignoring upper/lower case), or null if not found
    public Module findModule(String code) {
        if (code == null) {
            return null;
        }
        String wanted = code.trim();
        for (Module m : modules) {
            if (m.getCode() != null && m.getCode().equalsIgnoreCase(wanted)) {
                return m;
            }
        }
        return null;
    }

    //addModule - grade can be null if no grade has been set yet
    //returns false (and adds nothing) if a module with that code already exists
    public boolean addModule(String code, String name, int credits, Double grade) {
        if (findModule(code) != null) {
            return false;
        }
        modules.add(new Module(code.trim(), name.trim(), credits, grade));
        return true;
    }

    //removeModule - returns false if the module code does not exist
    public boolean removeModule(String code) {
        Module m = findModule(code);
        if (m == null) {
            return false;
        }
        modules.remove(m);
        return true;
    }

    //updateGrade - pass null to clear the grade (shown as N/A)
    //returns false if the module code does not exist
    public boolean updateGrade(String moduleCode, Double newGrade) {
        Module m = findModule(moduleCode);
        if (m == null) {
            return false;
        }
        m.setGrade(newGrade);
        return true;
    }

    //changeCode - returns false if the old code does not exist,
    //or if the new code is already used by a different module
    public boolean changeCode(String oldCode, String newCode) {
        Module m = findModule(oldCode);
        if (m == null) {
            return false;
        }
        Module other = findModule(newCode);
        if (other != null && other != m) {
            return false;
        }
        m.setCode(newCode.trim());
        return true;
    }

    //getModuleCodes - e.g. "COMP1001, COMP1002"
    public String getModuleCodes() {
        ArrayList<String> codes = new ArrayList<String>();
        for (Module m : modules) {
            codes.add(m.getCode());
        }
        return String.join(", ", codes);
    }

    //getWeightedAverage - average of graded modules, weighted by credits.
    //returns null if no module has a grade yet
    public Double getWeightedAverage() {
        double total = 0;
        int credits = 0;
        for (Module m : modules) {
            if (m.hasGrade()) {
                total += m.getGrade() * m.getCredits();
                credits += m.getCredits();
            }
        }
        if (credits == 0) {
            return null;
        }
        return total / credits;
    }

    //getClassification - UK degree classification boundaries (70 / 60 / 50 / 40).
    //Check your own university's rules, as boundaries and rounding can differ.
    public String getClassification() {
        Double average = getWeightedAverage();
        if (average == null) {
            return "N/A";
        }
        if (average >= 70) {
            return "First Class";
        }
        if (average >= 60) {
            return "Upper Second (2:1)";
        }
        if (average >= 50) {
            return "Lower Second (2:2)";
        }
        if (average >= 40) {
            return "Third Class";
        }
        return "Fail";
    }

    //getModuleSummary - table of all modules and grades (N/A where no grade yet)
    public String getModuleSummary() {
        if (modules.isEmpty()) {
            return "No modules added.";
        }

        String line = new String(new char[58]).replace('\0', '-');
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%-12s %-30s %-8s %s%n", "Code", "Module Name", "Credits", "Grade"));
        sb.append(line).append("\n");

        int totalCredits = 0;
        int gradedCredits = 0;
        for (Module m : modules) {
            sb.append(String.format("%-12s %-30s %-8s %s%n",
                    m.getCode(), m.getName(), String.valueOf(m.getCredits()), m.getGradeText()));
            totalCredits += m.getCredits();
            if (m.hasGrade()) {
                gradedCredits += m.getCredits();
            }
        }

        sb.append(line).append("\n");
        sb.append("Credits: ").append(totalCredits).append(" total, ")
                .append(gradedCredits).append(" graded\n");

        Double average = getWeightedAverage();
        if (average == null) {
            sb.append("Weighted average (graded modules only): N/A\n");
        } else {
            sb.append(String.format("Weighted average (graded modules only): %.1f%n", average));
        }
        sb.append("Classification: ").append(getClassification());
        return sb.toString();
    }

    //toString
    public String toString() {
        return "\nStudent Name: " + firstName + " " + lastName +
                "\nCourse: " + studyProgramme +
                "\n\n" + getModuleSummary();
    }

}