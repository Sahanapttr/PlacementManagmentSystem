package model;

import java.time.LocalDate;

public class Application {

    private int applicationId;
    private int studentId;
    private String studentName;
    private int jobId;
    private String jobTitle;
    private String companyName;
    private LocalDate applicationDate;
    private String status;

    public Application(int applicationId, int studentId,
                       String studentName, int jobId,
                       String jobTitle, String companyName,
                       LocalDate applicationDate, String status) {
        this.applicationId = applicationId;
        this.studentId = studentId;
        this.studentName = studentName;
        this.jobId = jobId;
        this.jobTitle = jobTitle;
        this.companyName = companyName;
        this.applicationDate = applicationDate;
        this.status = status;
    }

    public int getApplicationId() {
        return applicationId;
    }

    public int getStudentId() {
        return studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public int getJobId() {
        return jobId;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public String getCompanyName() {
        return companyName;
    }

    public LocalDate getApplicationDate() {
        return applicationDate;
    }

    public String getStatus() {
        return status;
    }

    public void displayDetails() {
        System.out.println("Application ID: " + applicationId);
        System.out.println("Student: " + studentName
                + " (ID: " + studentId + ")");
        System.out.println("Job: " + jobTitle
                + " (ID: " + jobId + ")");
        System.out.println("Company: " + companyName);
        System.out.println("Application Date: " + applicationDate);
        System.out.println("Status: " + status);
    }
}