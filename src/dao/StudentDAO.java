
package dao;

import model.Student;
import util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentDAO {

    // Register a new student
    public boolean addStudent(Student student) throws SQLException {
        String sql = "INSERT INTO students "
                + "(full_name, email, phone, qualification, "
                + "graduation_year, skills) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, student.getFullName());
            ps.setString(2, student.getEmail());
            ps.setString(3, student.getPhone());
            ps.setString(4, student.getQualification());
            ps.setInt(5, student.getGraduationYear());
            ps.setString(6, student.getSkills());

            return ps.executeUpdate() > 0;
        }
    }

    // Retrieve all students
    public List<Student> getAllStudents() throws SQLException {
        List<Student> students = new ArrayList<>();
        String sql = "SELECT * FROM students ORDER BY student_id";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Student student = new Student(
                        rs.getInt("student_id"),
                        rs.getString("full_name"),
                        rs.getString("email"),
                        rs.getString("phone"),
                        rs.getString("qualification"),
                        rs.getInt("graduation_year"),
                        rs.getString("skills")
                );

                students.add(student);
            }
        }

        return students;
    }

    // Search for a student by ID
    public Student getStudentById(int studentId) throws SQLException {
        String sql = "SELECT * FROM students WHERE student_id = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, studentId);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Student(
                            rs.getInt("student_id"),
                            rs.getString("full_name"),
                            rs.getString("email"),
                            rs.getString("phone"),
                            rs.getString("qualification"),
                            rs.getInt("graduation_year"),
                            rs.getString("skills")
                    );
                }
            }
        }

        return null;
    }

    // Update an existing student's details
    public boolean updateStudent(Student student) throws SQLException {
        String sql = "UPDATE students SET full_name = ?, email = ?, "
                + "phone = ?, qualification = ?, graduation_year = ?, "
                + "skills = ? WHERE student_id = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, student.getFullName());
            ps.setString(2, student.getEmail());
            ps.setString(3, student.getPhone());
            ps.setString(4, student.getQualification());
            ps.setInt(5, student.getGraduationYear());
            ps.setString(6, student.getSkills());
            ps.setInt(7, student.getStudentId());

            return ps.executeUpdate() > 0;
        }
    }
    public boolean deleteStudent(int studentId) throws SQLException {
    String sql = "DELETE FROM students WHERE student_id = ?";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, studentId);

            return statement.executeUpdate() > 0;
        }
    }
}
