package lab1;

import java.util.Calendar;
import java.util.Date;

public class Student {
    private String firstName;
    private String lastName;
    private String email;
    private Date enrollmentDate;
    private Date dateOfBirth;
    private Date graduationDate;

    public Student(String firstName, String lastName, String email, int enrolYear, int birthYear, int birthMonth, int birthDay) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        setEnrollmentDate(enrolYear);
        setDateOfBirth(birthYear, birthMonth, birthDay);
        this.graduationDate = null;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getEnrollmentDate() {
        return enrollmentDate;
    }

    public void setEnrollmentDate(int year) {
        Date tempDate = new Date();
        tempDate.setYear(year - 1900);
        this.enrollmentDate = tempDate;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(int year, int month, int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month-1);
        calendar.set(Calendar.DAY_OF_MONTH, day);
        this.dateOfBirth = calendar.getTime();
    }

    public Date getGraduationDate() {
        return graduationDate;
    }

    public void setGraduationDate() {
        Date tempDate = new Date();
        tempDate.setYear(java.time.LocalDate.now().getYear() - 1900);
        this.graduationDate = tempDate;
    }

    @Override
    public String toString() {
        return this.lastName + " " + firstName + "\n";
    }
}
