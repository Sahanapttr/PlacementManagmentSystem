
package model;

public class Student {
    private int studentId;
    private String fullName;
    private String email;
    private String phone;
    private String qualification;
    private int graduationYear;
    private String skills;

    public Student(int studentId, String fullName, String email,
                   String phone, String qualification,
                   int graduationYear, String skills) {
        this.studentId = studentId;
        this.fullName = fullName;
        this.email = email;
        this.phone = phone;
        this.qualification = qualification;
        this.graduationYear = graduationYear;
        this.skills = skills;
    }

    public int getStudentId() {
        return studentId;
    }

    public String getFullName() {
        return fullName;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getQualification() {
        return qualification;
    }

    public int getGraduationYear() {
        return graduationYear;
    }

    public String getSkills() {
        return skills;
    }

    public void displayDetails() {
        System.out.println("Student ID: " + studentId);
        System.out.println("Name: " + fullName);
        System.out.println("Email: " + email);
        System.out.println("Phone: " + phone);
        System.out.println("Qualification: " + qualification);
        System.out.println("Graduation Year: " + graduationYear);
        System.out.println("Skills: " + skills);
    }
}
