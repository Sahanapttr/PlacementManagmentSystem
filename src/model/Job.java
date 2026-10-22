package model;

public class Job {

    private int jobId;
    private int companyId;
    private String companyName;
    private String jobTitle;
    private String requiredQualification;
    private String requiredSkills;
    private double minSalary;
    private double maxSalary;
    private boolean active;

    public Job(int jobId, int companyId, String companyName,
               String jobTitle, String requiredQualification,
               String requiredSkills, double minSalary,
               double maxSalary, boolean active) {
        this.jobId = jobId;
        this.companyId = companyId;
        this.companyName = companyName;
        this.jobTitle = jobTitle;
        this.requiredQualification = requiredQualification;
        this.requiredSkills = requiredSkills;
        this.minSalary = minSalary;
        this.maxSalary = maxSalary;
        this.active = active;
    }

    public int getJobId() {
        return jobId;
    }

    public int getCompanyId() {
        return companyId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public String getRequiredQualification() {
        return requiredQualification;
    }

    public String getRequiredSkills() {
        return requiredSkills;
    }

    public double getMinSalary() {
        return minSalary;
    }

    public double getMaxSalary() {
        return maxSalary;
    }

    public boolean isActive() {
        return active;
    }

    public void displayDetails() {
        System.out.println("Job ID: " + jobId);
        System.out.println("Company: " + companyName);
        System.out.println("Job Title: " + jobTitle);
        System.out.println("Required Qualification: "
                + requiredQualification);
        System.out.println("Required Skills: " + requiredSkills);
        System.out.printf("Salary Range: INR %,.2f - INR %,.2f%n",
                 minSalary, maxSalary);
        System.out.println("Status: " + (active ? "Active" : "Inactive"));
    }
}