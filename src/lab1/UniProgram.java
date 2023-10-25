package lab1;

import lab1.entity.Faculty;
import lab1.entity.Student;
import lab1.enums.StudyField;
import lab1.loggers.CustomLogger;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class UniProgram {
    private List<Faculty> faculties;
    private String filePath;

    private CustomLogger logger;

    public UniProgram(String fileName) {
        this.filePath = "src" + File.separator + "lab1" + File.separator + "files" + File.separator+ "faculties.txt";
        File file = new File(filePath);
        if (file.exists() && file.length() == 0)
            faculties = new ArrayList<>();
        else {
            loadDataFromFile();
            if(faculties == null)
                faculties = new ArrayList<>();
        }
        logger = new CustomLogger("log.txt");
    }

    public List<Faculty> getFaculties() {
        return faculties;
    }

    public void saveDataToFile() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath))) {
            oos.writeObject(faculties);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadDataFromFile() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath))) {
            faculties = (List<Faculty>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
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


    public void createFaculty(String name, String abbreviation, StudyField studyField) {
        for(Faculty facultyTemp: faculties){
            if(facultyTemp.getName().equalsIgnoreCase(name)){
                logger.log("Faculty with name " + name + " was already created");
                System.out.println("Faculty with name " +  name + " was already created");
                return;
            }
        }
        Faculty faculty = new Faculty(name, abbreviation, new ArrayList<>(), studyField);
        faculties.add(faculty);
        logger.log(faculty.getName() + " was created");
    }

    public void assignStudentToFaculty(String facultyAbbreviation, Student student) {
        for (Faculty faculty : faculties) {
            if (faculty.getAbbreviation().equalsIgnoreCase(facultyAbbreviation)) {
                if (!faculty.addStudent(student)){
                    System.out.println("Student with email " + student.getEmail() + " already exists");
                    logger.log("Student with email " + student.getEmail() + " already exists");
                    return;
                }
                System.out.print("Student added to the faculty successfully");
                logger.log(student.getLastName() + " " + student.getFirstName() + " was added to the faculty with abbreviation " + facultyAbbreviation);
                return;
            }
        }
        System.out.println("Faculty not found.");
        logger.log("Faculty with abbreviation " + facultyAbbreviation + " wasn't found");
    }

    public void graduateStudent(String facultyAbbreviation, String email) {
        for (Faculty faculty : faculties) {
            if (faculty.getAbbreviation().equalsIgnoreCase(facultyAbbreviation)) {
                faculty.graduateStudent(email, logger);
                return;
            }
        }
        System.out.println("Faculty not found.");
        logger.log("Faculty with abbreviation " + facultyAbbreviation + " wasn't found");
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

    public void batchStudents(){
        String tempFilePath = "src" + File.separator + "lab1" + File.separator + "files" + File.separator + "batch_students.txt";
        try (BufferedReader br = new BufferedReader(new FileReader(tempFilePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] fields = line.split(",");

                if (fields.length == 6) {
                    String firstName = fields[0].trim();
                    String lastName = fields[1].trim();
                    String email = fields[2].trim();
                    int enrollmentDate = Integer.parseInt(fields[3].trim());
                    String[] dateParts = fields[4].trim().split("\\.");
                    String faculty = fields[5].trim();

                    Student student = new Student(firstName, lastName, email, enrollmentDate, Integer.parseInt(dateParts[2]), Integer.parseInt(dateParts[1]), Integer.parseInt(dateParts[0]));
                    assignStudentToFaculty(faculty, student);
                } else {
                    System.err.println("Invalid data in the line: " + line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void batchGraduates(){
        String tempFilePath = "src" + File.separator + "lab1" + File.separator + "files" + File.separator + "batch_graduates.txt";
        try (BufferedReader br = new BufferedReader(new FileReader(tempFilePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] fields = line.split(",");

                if (fields.length == 2) {
                    String email = fields[0].trim();
                    String faculty = fields[1].trim();
                    graduateStudent(faculty, email);
                } else {
                    System.err.println("Invalid data in the line: " + line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
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
