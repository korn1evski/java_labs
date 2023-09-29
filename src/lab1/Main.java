package lab1;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        UniProgram uniProgram = new UniProgram();
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
            System.out.println("10. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            String firstNameTemp;
            String lastNameTemp;
            String emailTemp;
            int enrolYearTemp;
            String[] splitBirthTemp;
            String facultyAbbreviation;
            String facultyNameTemp;
            String facultyAbbreviationTemp;
            String studyFieldStrTemp;

            switch (choice) {
                case 1:
                    System.out.print("Enter student first name: ");
                    firstNameTemp = scanner.nextLine();
                    System.out.print("Enter student last name: ");
                    lastNameTemp = scanner.nextLine();
                    System.out.print("Enter student email: ");
                    emailTemp = scanner.nextLine();
                    System.out.print("Enter student year of enrollment: ");
                    enrolYearTemp = scanner.nextInt();
                    scanner.nextLine();
                    System.out.println("Enter birthday in example format (2.2.2022): ");
                    splitBirthTemp = scanner.nextLine().split("\\.");


                    // Create a Student object
                    Student student = new Student(firstNameTemp, lastNameTemp, emailTemp, enrolYearTemp, Integer.parseInt(splitBirthTemp[2]), Integer.parseInt(splitBirthTemp[1]), Integer.parseInt(splitBirthTemp[0])); // Replace null with actual dates

                    // Prompt for faculty abbreviation
                    System.out.print("Enter faculty abbreviation: ");
                    facultyAbbreviation = scanner.nextLine();


                    for (Faculty faculty : uniProgram.getFaculties()) {
                        if (faculty.getAbbreviation().equalsIgnoreCase(facultyAbbreviation)) {
                            faculty.addStudent(student);
                            System.out.print("Student added to the faculty successfully");
                            break;
                        }
                    }
                    break;

                case 2:
                    System.out.println("Enter faculty abbreviation: ");
                    facultyAbbreviation = scanner.nextLine();
                    System.out.print("Enter student's email in order to graduate him: ");
                    emailTemp = scanner.nextLine();
                    outerLoop:
                    for (Faculty faculty: uniProgram.getFaculties()){
                        if(faculty.getAbbreviation().equalsIgnoreCase(facultyAbbreviation)){
                            for(Student studentTemp : faculty.getStudents())
                                if(studentTemp.getEmail().equalsIgnoreCase(emailTemp)) {
                                    studentTemp.setGraduationDate();
                                    System.out.println("Student graduated successfully");
                                    break outerLoop;
                                }
                        }
                    }
                    break;

                case 3:
                    System.out.print("Enter faculty abbreviation: ");
                    facultyAbbreviation = scanner.nextLine();
                    uniProgram.displayCurrentEnrolledStudents(facultyAbbreviation);
                    break;

                case 4:
                    System.out.print("Enter faculty abbreviation: ");
                    facultyAbbreviation = scanner.nextLine();
                    uniProgram.displayGraduates(facultyAbbreviation);
                    break;

                case 5:
                    System.out.print("Enter faculty abbreviation: ");
                    facultyAbbreviation = scanner.nextLine();
                    System.out.println("Enter student's email: ");
                    emailTemp = scanner.nextLine();
                    for(Faculty faculty: uniProgram.getFaculties()){
                        if(faculty.getAbbreviation().equalsIgnoreCase(facultyAbbreviation)) {
                            faculty.containsStudent(emailTemp);
                            break;
                        }
                    }
                    break;

                case 6:
                    System.out.print("Enter faculty name: ");
                    facultyNameTemp = scanner.nextLine();
                    System.out.print("Enter faculty abbreviation: ");
                    facultyAbbreviationTemp = scanner.nextLine();
                    System.out.print("Enter study field (e.g., SOFTWARE_ENGINEERING): ");
                    studyFieldStrTemp = scanner.nextLine();
                    StudyField studyFieldTemp = StudyField.valueOf(studyFieldStrTemp);

                    uniProgram.createFaculty(facultyNameTemp, facultyAbbreviationTemp, studyFieldTemp);
                    System.out.println("Faculty created.");
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
                    System.out.println("Exiting program.");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }

    }
}
