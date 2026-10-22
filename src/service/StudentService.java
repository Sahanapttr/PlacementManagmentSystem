
package service;

import dao.StudentDAO;
import model.Student;

import java.sql.SQLException;
import java.time.Year;
import java.util.List;

public class StudentService {

    private final StudentDAO studentDAO = new StudentDAO();

    // Register a student after validating details
    public void registerStudent(String name, String email,
            String phone, String qualification,
            int graduationYear, String skills) throws SQLException {

        validateStudent(name, email, qualification, graduationYear, skills);

        Student student = new Student(
                0,
                name.trim(),
                email.trim(),
                phone == null ? "" : phone.trim(),
                qualification.trim(),
                graduationYear,
                skills.trim()
        );

            try {
                if (!studentDAO.addStudent(student)) {
                    throw new SQLException("Student registration failed.");
                }
            } catch (SQLException e) {
                    if ("23000".equals(e.getSQLState())
                            && e.getErrorCode() == 1062) {
                        throw new IllegalArgumentException(
                                "This email may already be registered. "
                                + "Please check the email and try again.");
                    }

                    throw e;
            }
    }

    // Retrieve all students
    public List<Student> getAllStudents() throws SQLException {
        return studentDAO.getAllStudents();
    }

    // Search for a student by ID
    public Student searchStudent(int studentId) throws SQLException {
        if (studentId <= 0) {
            throw new IllegalArgumentException(
                    "Student ID must be positive.");
        }

        return studentDAO.getStudentById(studentId);
    }

    // Update an existing student
    public boolean updateStudent(int studentId, String name,
            String email, String phone, String qualification,
            int graduationYear, String skills) throws SQLException {

        if (studentId <= 0) {
            throw new IllegalArgumentException(
                    "Student ID must be positive.");
        }

        if (searchStudent(studentId) == null) {
            return false;
        }

        validateStudent(name, email, qualification,
                graduationYear, skills);

        Student student = new Student(
                studentId,
                name.trim(),
                email.trim(),
                phone == null ? "" : phone.trim(),
                qualification.trim(),
                graduationYear,
                skills.trim()
        );

        return studentDAO.updateStudent(student);
    }
        // Delete a student by ID
    public boolean deleteStudent(int studentId) throws SQLException {
        if (studentId <= 0) {
            throw new IllegalArgumentException(
                    "Student ID must be positive.");
        }

        if (searchStudent(studentId) == null) {
            return false;
        }

        return studentDAO.deleteStudent(studentId);
    }

    // Common validation for registration and updates
    private void validateStudent(String name, String email,
            String qualification, int graduationYear,
            String skills) {

        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException(
                    "Name cannot be empty.");
        }

        if (email == null ||
                !email.trim().matches(
                        "^[\\w.+-]+@[\\w.-]+\\.[A-Za-z]{2,}$")) {
            throw new IllegalArgumentException(
                    "Enter a valid email address.");
        }

        if (qualification == null ||
                qualification.trim().isEmpty()) {
            throw new IllegalArgumentException(
                    "Qualification is required.");
        }

        if (graduationYear < 1950 ||
                graduationYear > Year.now().getValue() + 6) {
            throw new IllegalArgumentException(
                    "Enter a valid graduation year.");
        }

        if (skills == null || skills.trim().isEmpty()) {
            throw new IllegalArgumentException(
                    "Enter at least one skill.");
        }
    }
}
