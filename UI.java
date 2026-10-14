import java.util.Scanner;

public class UI {

    private static final Scanner io = new Scanner(System.in);

    public static void main(String[] args) {

        Student student = new Student();

        student.setFirstName(readText("What is your first name?"));
        student.setLastName(readText("What is your last name?"));
        student.setStudyProgramme(readText("What course are you studying?"));

        boolean running = true;
        while (running) {
            printMenu();
            int choice = readInt("Choose an option (1-6):", 1, 6);

            switch (choice) {
                case 1:
                    addModule(student);
                    break;
                case 2:
                    updateGrade(student);
                    break;
                case 3:
                    editModule(student);
                    break;
                case 4:
                    removeModule(student);
                    break;
                case 5:
                    System.out.println(student);
                    break;
                case 6:
                    running = false;
                    break;
            }
        }

        System.out.println("\nFinal summary:");
        System.out.println(student);
        System.out.println("\nGoodbye!");
    }

    // ---------- menu actions ----------

    private static void printMenu() {
        System.out.println("\n===== Student Module Tracker =====");
        System.out.println("1. Add a module");
        System.out.println("2. Update a grade");
        System.out.println("3. Edit a module's code, name or credits");
        System.out.println("4. Remove a module");
        System.out.println("5. View modules and grades");
        System.out.println("6. Quit");
    }

    private static void addModule(Student student) {
        String code = readText("Enter module code: ");
        if (student.findModule(code) != null) {
            System.out.println("A module with the code " + code + " already exists. Nothing was added.");
            return;
        }

        String name = readText("Enter module name: ");
        int credits = readInt("Enter module credits (1-120, e.g. 15, 30 or 60): ", 1, 120);
        Double grade = readOptionalGrade("Enter module grade (0-100), or press Enter if no grade has been set: ");

        student.addModule(code, name, credits, grade);
        System.out.println("Module added.");
    }

    private static void updateGrade(Student student) {
        Module module = chooseModule(student, "Enter module code to update grade: ");
        if (module == null) {
            return;
        }

        Double newGrade = readOptionalGrade("Enter new grade (0-100), or press Enter to clear the grade: ");
        student.updateGrade(module.getCode(), newGrade);
        System.out.println("Grade updated.");
    }

    private static void editModule(Student student) {
        Module module = chooseModule(student, "Enter the code of the module to edit: ");
        if (module == null) {
            return;
        }

        System.out.println("What would you like to change?");
        System.out.println("1. Module code");
        System.out.println("2. Module name");
        System.out.println("3. Module credits");
        System.out.println("4. Cancel");
        int choice = readInt("Choose an option (1-4):", 1, 4);

        switch (choice) {
            case 1: {
                String newCode = readText("Enter the new module code: ");
                if (student.changeCode(module.getCode(), newCode)) {
                    System.out.println("Module code changed.");
                } else {
                    System.out.println("Another module already uses the code " + newCode + ". Nothing was changed.");
                }
                break;
            }
            case 2: {
                String newName = readText("Enter the new module name: ");
                module.setName(newName);
                System.out.println("Module name changed.");
                break;
            }
            case 3: {
                int newCredits = readInt("Enter the new number of credits (1-120): ", 1, 120);
                module.setCredits(newCredits);
                System.out.println("Module credits changed.");
                break;
            }
            default:
                System.out.println("Nothing was changed.");
        }
    }

    private static void removeModule(Student student) {
        Module module = chooseModule(student, "Enter the code of the module to remove: ");
        if (module == null) {
            return;
        }

        if (readYesNo("Remove " + module.getCode() + " - " + module.getName() + "? (yes/no): ")) {
            student.removeModule(module.getCode());
            System.out.println("Module removed.");
        } else {
            System.out.println("Nothing was removed.");
        }
    }

    // Asks for a module code and returns that module, or null (with a message) if there isn't one
    private static Module chooseModule(Student student, String prompt) {
        if (student.getModuleCount() == 0) {
            System.out.println("No modules have been added yet.");
            return null;
        }

        String code = readText(prompt);
        Module module = student.findModule(code);
        if (module == null) {
            System.out.println("No module found with the code \"" + code + "\".");
            System.out.println("Available module codes: " + student.getModuleCodes());
        }
        return module;
    }

    // ---------- input helpers ----------

    // Keeps asking until the user types something that isn't blank
    private static String readText(String prompt) {
        while (true) {
            System.out.println(prompt);
            String line = io.nextLine().trim();
            if (!line.isEmpty()) {
                return line;
            }
            System.out.println("Input cannot be empty. Please try again.");
        }
    }

    // Keeps asking until the user types a whole number between min and max
    private static int readInt(String prompt, int min, int max) {
        while (true) {
            System.out.println(prompt);
            String line = io.nextLine().trim();
            try {
                int value = Integer.parseInt(line);
                if (value >= min && value <= max) {
                    return value;
                }
                System.out.println("Please enter a whole number between " + min + " and " + max + ".");
            } catch (NumberFormatException e) {
                System.out.println("That is not a valid whole number. Please try again.");
            }
        }
    }

    // A grade from 0 to 100, or null if the user just presses Enter (no grade)
    private static Double readOptionalGrade(String prompt) {
        while (true) {
            System.out.println(prompt);
            String line = io.nextLine().trim();
            if (line.isEmpty()) {
                return null;
            }
            try {
                double grade = Double.parseDouble(line);
                if (grade >= 0 && grade <= 100) {
                    return grade;
                }
                System.out.println("Grade must be between 0 and 100.");
            } catch (NumberFormatException e) {
                System.out.println("That is not a valid number. Please try again.");
            }
        }
    }

    // Accepts yes / y / no / n in any case
    private static boolean readYesNo(String prompt) {
        while (true) {
            System.out.println(prompt);
            String line = io.nextLine().trim().toLowerCase();
            if (line.equals("yes") || line.equals("y")) {
                return true;
            }
            if (line.equals("no") || line.equals("n")) {
                return false;
            }
            System.out.println("Please type yes or no.");
        }
    }

}