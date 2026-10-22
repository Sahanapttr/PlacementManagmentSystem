package service;

import dao.JobDAO;
import model.Job;

import java.sql.SQLException;
import java.util.List;

public class JobService {

    private final JobDAO jobDAO = new JobDAO();

    // Register a new job vacancy
    public void registerJob(int companyId, String jobTitle,
                            String qualification, String skills,
                            double minSalary, double maxSalary)
            throws SQLException {

        if (companyId <= 0) {
            throw new IllegalArgumentException(
                    "Company ID must be positive.");
        }

        if (jobTitle == null || jobTitle.trim().isEmpty()) {
            throw new IllegalArgumentException(
                    "Job title cannot be empty.");
        }

        if (qualification == null || qualification.trim().isEmpty()) {
            throw new IllegalArgumentException(
                    "Required qualification cannot be empty.");
        }

        if (skills == null || skills.trim().isEmpty()) {
            throw new IllegalArgumentException(
                    "Required skills cannot be empty.");
        }

        if (!Double.isFinite(minSalary) || !Double.isFinite(maxSalary)
                || minSalary < 0 || maxSalary < minSalary) {
            throw new IllegalArgumentException(
                    "Enter valid salary amounts. Maximum salary "
                    + "must be at least the minimum salary.");
        }

        Job job = new Job(
                0,
                companyId,
                "",
                jobTitle.trim(),
                qualification.trim(),
                skills.trim(),
                minSalary,
                maxSalary,
                true
        );

        if (!jobDAO.addJob(job)) {
            throw new SQLException("Job registration failed.");
        }
    }

    // Retrieve all jobs
    public List<Job> getAllJobs() throws SQLException {
            return jobDAO.getAllJobs();
        }
        // Search jobs by required skill
    public List<Job> searchJobsBySkill(String skill) throws SQLException {
        if (skill == null || skill.trim().isEmpty()) {
            throw new IllegalArgumentException(
                    "Please enter a skill to search.");
        }

        return jobDAO.searchJobsBySkill(skill.trim());
    }
}