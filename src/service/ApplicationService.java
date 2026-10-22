package service;

import dao.ApplicationDAO;
import model.Application;

import java.sql.SQLException;
import java.util.List;
import java.util.Set;

public class ApplicationService {

    private final ApplicationDAO applicationDAO = new ApplicationDAO();

    private static final Set<String> VALID_STATUSES = Set.of(
            "APPLIED",
            "SHORTLISTED",
            "INTERVIEW",
            "SELECTED",
            "REJECTED"
    );

    // Apply a student to a job
   public void applyForJob(int studentId, int jobId)
        throws SQLException {

    if (studentId <= 0) {
        throw new IllegalArgumentException(
                "Student ID must be positive.");
    }

    if (jobId <= 0) {
        throw new IllegalArgumentException(
                "Job ID must be positive.");
    }

    try {
        if (!applicationDAO.addApplication(studentId, jobId)) {
            throw new SQLException("Job application failed.");
        }
    } catch (SQLException e) {
        if ("23000".equals(e.getSQLState())) {
            throw new IllegalArgumentException(
                    "Cannot submit application. Please verify that "
                    + "the student ID and job ID exist, and that "
                    + "the student has not already applied for this job.");
        }

        throw e;
    }
}

    // View all applications
    public List<Application> getAllApplications()
            throws SQLException {
        return applicationDAO.getAllApplications();
    }

    // Update application status
    public boolean updateApplicationStatus(int applicationId,
                                           String status)
            throws SQLException {

        if (applicationId <= 0) {
            throw new IllegalArgumentException(
                    "Application ID must be positive.");
        }

        if (status == null ||
                !VALID_STATUSES.contains(status.trim().toUpperCase())) {
            throw new IllegalArgumentException(
                    "Invalid status. Use APPLIED, SHORTLISTED, "
                    + "INTERVIEW, SELECTED, or REJECTED.");
        }

        return applicationDAO.updateApplicationStatus(
                applicationId, status.trim().toUpperCase());
    }
}