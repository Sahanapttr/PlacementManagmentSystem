package dao;

import model.Application;
import util.DBConnection;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
//import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ApplicationDAO {

    // Apply a student to a job
    public boolean addApplication(int studentId, int jobId)
            throws SQLException {

        String sql = "INSERT INTO applications "
                + "(student_id, job_id, application_date, status) "
                + "VALUES (?, ?, ?, 'APPLIED')";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(sql)) {

            statement.setInt(1, studentId);
            statement.setInt(2, jobId);
            statement.setDate(3, Date.valueOf(LocalDate.now()));

            return statement.executeUpdate() > 0;
        }
    }

    // Retrieve all applications with student, job, and company details
    public List<Application> getAllApplications() throws SQLException {
        List<Application> applications = new ArrayList<>();

        String sql = "SELECT a.application_id, a.student_id, "
                + "s.full_name, a.job_id, j.job_title, "
                + "c.company_name, a.application_date, a.status "
                + "FROM applications a "
                + "JOIN students s ON a.student_id = s.student_id "
                + "JOIN jobs j ON a.job_id = j.job_id "
                + "JOIN companies c ON j.company_id = c.company_id "
                + "ORDER BY a.application_id";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Application application = new Application(
                        resultSet.getInt("application_id"),
                        resultSet.getInt("student_id"),
                        resultSet.getString("full_name"),
                        resultSet.getInt("job_id"),
                        resultSet.getString("job_title"),
                        resultSet.getString("company_name"),
                        resultSet.getDate("application_date").toLocalDate(),
                        resultSet.getString("status")
                );

                applications.add(application);
            }
        }

        return applications;
    }

    // Update the status of an application
    public boolean updateApplicationStatus(int applicationId,
                                           String status)
            throws SQLException {

        String sql = "UPDATE applications SET status = ? "
                + "WHERE application_id = ?";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(sql)) {

            statement.setString(1, status);
            statement.setInt(2, applicationId);

            return statement.executeUpdate() > 0;
        }
    }
}