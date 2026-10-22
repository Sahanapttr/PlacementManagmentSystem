package dao;

import model.Job;
import util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JobDAO {

    // Add a new job vacancy
    public boolean addJob(Job job) throws SQLException {
        String sql = "INSERT INTO jobs "
                + "(company_id, job_title, required_qualification, "
                + "required_skills, min_salary, max_salary, is_active) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(sql)) {

            statement.setInt(1, job.getCompanyId());
            statement.setString(2, job.getJobTitle());
            statement.setString(3, job.getRequiredQualification());
            statement.setString(4, job.getRequiredSkills());
            statement.setDouble(5, job.getMinSalary());
            statement.setDouble(6, job.getMaxSalary());
            statement.setBoolean(7, job.isActive());

            return statement.executeUpdate() > 0;
        }
    }

    // Retrieve jobs along with their company names
    public List<Job> getAllJobs() throws SQLException {
        List<Job> jobs = new ArrayList<>();

        String sql = "SELECT j.job_id, j.company_id, c.company_name, "
                + "j.job_title, j.required_qualification, "
                + "j.required_skills, j.min_salary, j.max_salary, "
                + "j.is_active "
                + "FROM jobs j "
                + "JOIN companies c ON j.company_id = c.company_id "
                + "ORDER BY j.job_id";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Job job = new Job(
                        resultSet.getInt("job_id"),
                        resultSet.getInt("company_id"),
                        resultSet.getString("company_name"),
                        resultSet.getString("job_title"),
                        resultSet.getString("required_qualification"),
                        resultSet.getString("required_skills"),
                        resultSet.getDouble("min_salary"),
                        resultSet.getDouble("max_salary"),
                        resultSet.getBoolean("is_active")
                );

                jobs.add(job);
            }
        }

        return jobs;
    }
    
    // Search active jobs by required skill
    public List<Job> searchJobsBySkill(String skill)
            throws SQLException {

        List<Job> jobs = new ArrayList<>();

        String sql = "SELECT j.job_id, j.company_id, c.company_name, "
                + "j.job_title, j.required_qualification, "
                + "j.required_skills, j.min_salary, j.max_salary, "
                + "j.is_active "
                + "FROM jobs j "
                + "JOIN companies c ON j.company_id = c.company_id "
                + "WHERE j.is_active = TRUE "
                + "AND j.required_skills LIKE ? "
                + "ORDER BY j.job_id";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(sql)) {

            statement.setString(1, "%" + skill + "%");

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Job job = new Job(
                            resultSet.getInt("job_id"),
                            resultSet.getInt("company_id"),
                            resultSet.getString("company_name"),
                            resultSet.getString("job_title"),
                            resultSet.getString("required_qualification"),
                            resultSet.getString("required_skills"),
                            resultSet.getDouble("min_salary"),
                            resultSet.getDouble("max_salary"),
                            resultSet.getBoolean("is_active")
                    );

                    jobs.add(job);
                }
            }
        }

        return jobs;
    }

}