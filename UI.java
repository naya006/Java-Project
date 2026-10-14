import java.util.Scanner;

public class UI {

    public static void main(String[] args) {


        Student student;
        student = new Student();
        Scanner io = new Scanner(System.in);

        System.out.println("What is your first name?");
        String firstName = io.nextLine();
        student.setFirstName(firstName);

        System.out.println("What is your last name?");
        String lastName = io.nextLine();
        student.setLastName(lastName);

        System.out.println("What course are you studying?");
        String StudyProgramme = io.nextLine();
        student.setStudyProgramme(StudyProgramme);

        System.out.println("How many modules are you studying?");
        int numberOfModules = io.nextInt();
        student.setNumberOfModules(numberOfModules);


        System.out.println("\nAdding Module details.  ");


        for (int i = 1; i <= numberOfModules; i++) {
            System.out.println("For Module (" + i + ") of " + numberOfModules);
            io.nextLine();
            System.out.println("Enter module code: ");
            String code = io.nextLine();

            System.out.println("Enter module name: ");
            String name = io.nextLine();

            System.out.println("Enter module grade (or 0 if no grade has been set): ");
            double grade = io.nextDouble();

            student.addModule(code, name, grade);
        }

        io.nextLine();
        System.out.println("Do you need to update a grade? (yes/no): ");
        String input;
        input = io.nextLine();
        if (input.equals("yes")){
            System.out.println("\nEnter module code to update grade: ");
            String moduleCode = io.nextLine();
            System.out.println("Enter new grade: ");
            double newGrade = io.nextDouble();
            student.updateGrade(moduleCode, newGrade);
            System.out.print(student);
        }
        else if (!input.equals("no")){
            System.out.print(student);
        }

    }

}

