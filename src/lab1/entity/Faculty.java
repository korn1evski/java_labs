package lab1.entity;

import lab1.enums.StudyField;
import lab1.loggers.CustomLogger;

import java.io.Serializable;
import java.util.List;

public class Faculty implements Serializable {
    private String name;
    private String abbreviation;
    private List<Student> students;
    private StudyField studyField;

    public Faculty(String name, String abbreviation, List<Student> students, StudyField studyField) {
        this.name = name;
        this.abbreviation = abbreviation;
        this.students = students;
        this.studyField = studyField;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    public void setAbbreviation(String abbreviation) {
        this.abbreviation = abbreviation;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public StudyField getStudyField() {
        return studyField;
    }

    public void setStudyField(StudyField studyField) {
        this.studyField = studyField;
    }

    public boolean addStudent(Student student) {
        for(Student studentTemp : students)
            if(student.getEmail().equalsIgnoreCase(studentTemp.getEmail()))
                return false;

        students.add(student);
        return true;
    }

    public void removeStudent(Student student) {
        students.remove(student);
    }

    public void graduateStudent(String email, CustomLogger logger) {
        for (Student student : students) {
            if (student.getEmail().equalsIgnoreCase(email)) {
               student.setGraduationDate();
               logger.log(student.getLastName() + " " + student.getFirstName() + " is not graduate of " + name);
               return;
            }
        }
        System.out.println("Student not found in this faculty.");
        logger.log( "Student with email " + email + " wasn't found in faculty " + name);
    }

    public boolean containsStudent(String email) {
        for (Student student : students) {
            if (student.getEmail().equalsIgnoreCase(email)) {
                System.out.println(this.name + " contains student " + student.toString());
                return true;
            }
        }
        System.out.println(this.name + " doesn't contain student with email " + email);
        return false;
    }

    public void displayEnrolledStudents() {
        boolean hasEnrolled = false;
        for(Student student : students){
            if(student.getGraduationDate() == null) {
                System.out.print(student.toString());
                hasEnrolled = true;
            }
        }
        if(!hasEnrolled)
            System.out.println("There are no enrolled students in faculty " + this.name);
    }

    public void displayGraduates(){
        boolean hasGraduates = false;
        for (Student student : students){
            if(student.getGraduationDate() != null) {
                System.out.println(student.toString());
                hasGraduates = true;
            }
        }
        if(!hasGraduates)
            System.out.println("There are no graduates in faculty " + this.name);
    }


}
