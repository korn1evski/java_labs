package lab1;

import lab1.entity.Student;
import lab1.enums.StudyField;
import lab1.helpers.UserInputHelper;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        UniProgram uniProgram = new UniProgram("faculties.txt");
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            uniProgram.saveDataToFile();
        }));
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nUniversity Program Menu:");
            System.out.println("------------Faculty Operations------------");
            System.out.println("1. Create and assign a student to a faculty");
            System.out.println("2. Graduate a student from a faculty");
            System.out.println("3. Display current enrolled students (Graduates would be ignored)");
            System.out.println("4. Display graduates (Currently enrolled students would be ignored)");
            System.out.println("5. Tell or not if a student belongs to this faculty");
            System.out.println("------------General operations------------");
            System.out.println("6. Create a new faculty");
            System.out.println("7. Search what faculty a student belongs to by a unique identifier (for example by email or a unique ID).");
            System.out.println("8. Display University faculties");
            System.out.println("9. Display all faculties belonging to a field. (Ex. FOOD_TECHNOLOGY)");
            System.out.println("10. Batch students");
            System.out.println("11. Batch graduates");
            System.out.println("12. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            String firstNameTemp;
            String lastNameTemp;
            String emailTemp;
            int enrolYearTemp;
            String[] splitBirthTemp;
            String facultyNameTemp;
            String facultyAbbreviationTemp;
            String studyFieldStrTemp;
            StudyField studyFieldTemp;
            Student studentTemp;

            switch (choice) {
                case 1:
                    System.out.print("Enter student first name: ");
                    firstNameTemp = scanner.nextLine();
                    System.out.print("Enter student last name: ");
                    lastNameTemp = scanner.nextLine();
                    emailTemp = UserInputHelper.promptForValidEmail("Enter student email: ", scanner);
                    enrolYearTemp = UserInputHelper.promptForValidInteger("Enter student year of enrollment: ", scanner);
                    splitBirthTemp = UserInputHelper.promptForBirthday("Enter birthday in example format (2.2.2002): ", scanner);

                    studentTemp = new Student(firstNameTemp, lastNameTemp, emailTemp, enrolYearTemp, Integer.parseInt(splitBirthTemp[2]), Integer.parseInt(splitBirthTemp[1]), Integer.parseInt(splitBirthTemp[0]));

                    System.out.print("Enter faculty abbreviation: ");
                    facultyAbbreviationTemp = scanner.nextLine();
                    uniProgram.assignStudentToFaculty(facultyAbbreviationTemp, studentTemp);
                    break;

                case 2:
                    System.out.println("Enter faculty abbreviation: ");
                    facultyAbbreviationTemp = scanner.nextLine();
                    System.out.print("Enter student's email in order to graduate him: ");
                    emailTemp = scanner.nextLine();
                    uniProgram.graduateStudent(facultyAbbreviationTemp, emailTemp);
                    break;

                case 3:
                    System.out.print("Enter faculty abbreviation: ");
                    facultyAbbreviationTemp = scanner.nextLine();
                    uniProgram.displayCurrentEnrolledStudents(facultyAbbreviationTemp);
                    break;

                case 4:
                    System.out.print("Enter faculty abbreviation: ");
                    facultyAbbreviationTemp = scanner.nextLine();
                    uniProgram.displayGraduates(facultyAbbreviationTemp);
                    break;

                case 5:
                    System.out.print("Enter faculty abbreviation: ");
                    facultyAbbreviationTemp = scanner.nextLine();
                    System.out.println("Enter student's email: ");
                    emailTemp = scanner.nextLine();
                    uniProgram.doesStudentBelongToFaculty(facultyAbbreviationTemp, emailTemp);
                    break;

                case 6:
                    System.out.print("Enter faculty name: ");
                    facultyNameTemp = scanner.nextLine();
                    System.out.print("Enter faculty abbreviation: ");
                    facultyAbbreviationTemp = scanner.nextLine();
                    studyFieldTemp = UserInputHelper.promptForStudyField("Enter study field (e.g., SOFTWARE_ENGINEERING): ", scanner);

                    uniProgram.createFaculty(facultyNameTemp, facultyAbbreviationTemp, studyFieldTemp);
                    break;

                case 7:
                    System.out.println("Enter student's email: ");
                    emailTemp = scanner.nextLine();
                    uniProgram.findFacultyByStudent(emailTemp);
                    break;

                case 8:
                    uniProgram.displayFaculties();
                    break;

                case 9:
                    System.out.println("Enter field name: ");
                    studyFieldStrTemp = scanner.nextLine();
                    uniProgram.facultiesBelongingToField(studyFieldStrTemp);
                    break;
                case 10:
                    uniProgram.batchStudents();
                    break;
                case 11:
                    uniProgram.batchGraduates();
                    break;
                case 12:
                    System.out.println("Exiting program.");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }

    }
}
