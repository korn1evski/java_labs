package lab1;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class UniProgram {
    private List<Faculty> faculties;

    public UniProgram(List<Faculty> faculties) {
        this.faculties = faculties;
    }

    public List<Faculty> getFaculties() {
        return faculties;
    }

    public void displayFaculties(){
        for(Faculty faculty : faculties)
            System.out.print(faculty.getName() + " ");
        System.out.println();
    }

    public void facultiesBelongingToField(String field){
        boolean facultiesFound = false;
        for(Faculty faculty : faculties){
            if(faculty.getStudyField().name().equalsIgnoreCase(field)) {
                facultiesFound = true;
                System.out.print(faculty.getName() + " ");
            }
        }
        if(!facultiesFound)
            System.out.println("No faculties were found with field " + field);
        else
            System.out.println();
    }

    public void setFaculties(List<Faculty> faculties) {
        this.faculties = faculties;
    }

    public UniProgram(){
        this(new ArrayList<Faculty>());
    }

    public void createFaculty(String name, String abbreviation, StudyField studyField) {
        Faculty faculty = new Faculty(name, abbreviation, new ArrayList<>(), studyField);
        faculties.add(faculty);
    }

    public void assignStudentToFaculty(String facultyAbbreviation, Student student) {
        for (Faculty faculty : faculties) {
            if (faculty.getAbbreviation().equalsIgnoreCase(facultyAbbreviation)) {
                faculty.addStudent(student);
                return;
            }
        }
        System.out.println("Faculty not found.");
    }

    public void graduateStudent(String facultyAbbreviation, String email) {
        for (Faculty faculty : faculties) {
            if (faculty.getAbbreviation().equalsIgnoreCase(facultyAbbreviation)) {
                faculty.graduateStudent(email);
            }
        }
        System.out.println("Faculty not found.");
    }

    public void displayCurrentEnrolledStudents(String facultyAbbreviation) {
        for (Faculty faculty : faculties) {
            if (faculty.getAbbreviation().equalsIgnoreCase(facultyAbbreviation)) {
                faculty.displayEnrolledStudents();
                return;
            }
        }
        System.out.println("Faculty not found.");
    }

    public void displayGraduates(String facultyAbbreviation) {
        for (Faculty faculty : faculties) {
            if (faculty.getAbbreviation().equalsIgnoreCase(facultyAbbreviation)) {
                faculty.displayGraduates();
                return;
            }
        }
        System.out.println("Faculty not found.");
    }

    public boolean doesStudentBelongToFaculty(String facultyAbbreviation, String email) {
        for (Faculty faculty : faculties) {
            if (faculty.getAbbreviation().equalsIgnoreCase(facultyAbbreviation)) {
                return faculty.containsStudent(email);
            }
        }
        return false;
    }

    public void findFacultyByStudent(String email){
        for( Faculty faculty: this.faculties){
            for(Student student: faculty.getStudents())
                if(student.getEmail().equalsIgnoreCase(email)) {
                    System.out.println("Student with email " + email + " is in faculty with name " + faculty.getName());
                    return;
                }
        }
        System.out.println("No faculty was found for student with email " + email);
    }


}
