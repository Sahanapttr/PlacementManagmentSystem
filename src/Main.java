
import model.Student;
import service.StudentService;
import model.Company;
import service.CompanyService;
import model.Job;
import service.JobService;
import model.Application;
import service.ApplicationService;
import dao.AnalyticsDAO;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static final Scanner scanner = new Scanner(System.in);
    private static final StudentService studentService =
            new StudentService();
    private static final CompanyService companyService =
        new CompanyService();
    private static final JobService jobService =
        new JobService();
    private static final ApplicationService applicationService =
        new ApplicationService();
    private static final AnalyticsDAO analyticsDAO =
        new AnalyticsDAO();
    public static void main(String[] args) {
        boolean running = true;

        while (running) {
            System.out.println("\n=================================");
            System.out.println(" PLACEMENT MANAGEMENT SYSTEM");
            System.out.println("=================================");
            System.out.println("1. Register Student");
            System.out.println("2. View All Students");
            System.out.println("3. Search Student by ID");
            System.out.println("4. Update Student Details");
            System.out.println("5. Delete Student");
            System.out.println("6. Register Company");
            System.out.println("7. View All Companies");
            System.out.println("8. Register Job");
            System.out.println("9. View All Jobs");
            System.out.println("10. Apply for a Job");
            System.out.println("11. View All Applications");
            System.out.println("12. Update Application Status");
            System.out.println("13. Placement Analytics & Reports");
            System.out.println("14. Search Jobs by Skill");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");

            try {
                int choice = readInt();

                switch (choice) {
                    case 1:
                        registerStudent();
                        break;
                    case 2:
                        viewAllStudents();
                        break;
                    case 3:
                        searchStudent();
                        break;
                    case 4:
                        updateStudent();
                        break;
                    case 5:
                        deleteStudent();
                        break;
                    case 6:
                        registerCompany();
                        break;
                    case 7:
                        viewAllCompanies();
                        break;
                    case 8:
                        registerJob();
                        break;
                    case 9:
                        viewAllJobs();
                        break;
                    case 10:
                        applyForJob();
                        break;
                    case 11:
                        viewAllApplications();
                        break;
                    case 12:
                        updateApplicationStatus();
                        break;
                    case 13:
                        viewPlacementAnalytics();
                        break;
                    case 14:
                        searchJobsBySkill();
                        break;
                    case 0:
                        running = false;
                        System.out.println(
                                "Thank you for using the system!");
                        break;
                    default:
                        System.out.println(
                                "Invalid choice. Try again.");
                }
            } catch (IllegalArgumentException e) {
                System.out.println("Input error: " + e.getMessage());
            } catch (SQLException e) {
                System.out.println(
                        "Database operation failed: " + e.getMessage());
            }
        }

        scanner.close();
    }

    private static void registerStudent() throws SQLException {
        System.out.println("\n--- Student Registration ---");

        System.out.print("Full name: ");
        String name = scanner.nextLine();

        System.out.print("Email: ");
        String email = scanner.nextLine();

        System.out.print("Phone (optional): ");
        String phone = scanner.nextLine();

        System.out.print("Qualification: ");
        String qualification = scanner.nextLine();

        System.out.print("Graduation year: ");
        int year = readInt();

        System.out.print("Skills (comma-separated): ");
        String skills = scanner.nextLine();

        studentService.registerStudent(
                name, email, phone, qualification, year, skills);

        System.out.println("Student registered successfully!");
    }

    private static void viewAllStudents() throws SQLException {
        List<Student> students = studentService.getAllStudents();

        if (students.isEmpty()) {
            System.out.println("No students registered yet.");
            return;
        }

        System.out.println("\n--- Registered Students ---");

        for (Student student : students) {
            System.out.println("---------------------------");
            student.displayDetails();
        }
    }

    private static void searchStudent() throws SQLException {
        System.out.print("Enter student ID: ");
        int id = readInt();

        Student student = studentService.searchStudent(id);

        if (student == null) {
            System.out.println("No student found with ID " + id);
        } else {
            student.displayDetails();
        }
    }

    private static void updateStudent() throws SQLException {
        System.out.print("Enter student ID to update: ");
        int id = readInt();

        Student existing = studentService.searchStudent(id);

        if (existing == null) {
            System.out.println("No student found with ID " + id);
            return;
        }

        System.out.println("Current student details:");
        existing.displayDetails();

        System.out.println("\nEnter the new details:");

        System.out.print("Full name: ");
        String name = scanner.nextLine();

        System.out.print("Email: ");
        String email = scanner.nextLine();

        System.out.print("Phone (optional): ");
        String phone = scanner.nextLine();

        System.out.print("Qualification: ");
        String qualification = scanner.nextLine();

        System.out.print("Graduation year: ");
        int year = readInt();

        System.out.print("Skills (comma-separated): ");
        String skills = scanner.nextLine();

        boolean updated = studentService.updateStudent(
                id, name, email, phone,
                qualification, year, skills);

        if (updated) {
            System.out.println("Student updated successfully!");
        } else {
            System.out.println("Student could not be updated.");
        }
    }

    private static int readInt() {
        String input = scanner.nextLine().trim();

        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(
                    "Please enter a whole number.");
        }
    }

    private static void deleteStudent() throws SQLException {
        System.out.print("Enter student ID to delete: ");
        int id = readInt();

        Student student = studentService.searchStudent(id);

        if (student == null) {
            System.out.println("No student found with ID " + id);
            return;
        }

        System.out.println("Student details:");
        student.displayDetails();

        System.out.print("Are you sure you want to delete this student? (yes/no): ");
        String confirmation = scanner.nextLine().trim();

        if (!confirmation.equalsIgnoreCase("yes")) {
            System.out.println("Deletion cancelled.");
            return;
        }

        boolean deleted = studentService.deleteStudent(id);

        if (deleted) {
            System.out.println("Student deleted successfully!");
        } else {
            System.out.println("Student could not be deleted.");
        }
    }

   private static void registerCompany() throws SQLException {
        System.out.println("\n--- Company Registration ---");

        System.out.print("Company name: ");
        String name = scanner.nextLine();

        System.out.print("Industry: ");
        String industry = scanner.nextLine();

        System.out.print("Location: ");
        String location = scanner.nextLine();

        System.out.print("Contact email (optional): ");
        String email = scanner.nextLine();

        companyService.registerCompany(
                name, industry, location, email);

        System.out.println("Company registered successfully!");
    }

private static void viewAllCompanies() throws SQLException {
        List<Company> companies = companyService.getAllCompanies();

        if (companies.isEmpty()) {
            System.out.println("No companies registered yet.");
            return;
        }

        System.out.println("\n--- Registered Companies ---");

        for (Company company : companies) {
            System.out.println("---------------------------");
            company.displayDetails();
        }
    }
    // Register a new job vacancy
private static void registerJob() throws SQLException {
        System.out.println("\n--- Job Registration ---");

        System.out.print("Enter company ID: ");
        int companyId = readInt();

        System.out.print("Job title: ");
        String title = scanner.nextLine();

        System.out.print("Required qualification: ");
        String qualification = scanner.nextLine();

        System.out.print("Required skills (comma-separated): ");
        String skills = scanner.nextLine();

        System.out.print("Minimum salary (annual, in INR): ");
        double minSalary = readDouble();

        System.out.print("Maximum salary (annual, in INR): ");
        double maxSalary = readDouble();

        jobService.registerJob(
                companyId, title, qualification,
                skills, minSalary, maxSalary);

        System.out.println("Job registered successfully!");
    }

// View all job vacancies
private static void viewAllJobs() throws SQLException {
        List<Job> jobs = jobService.getAllJobs();

        if (jobs.isEmpty()) {
            System.out.println("No jobs registered yet.");
            return;
        }

        System.out.println("\n--- Registered Jobs ---");

        for (Job job : jobs) {
            System.out.println("---------------------------");
            job.displayDetails();
        }
    }

// Read a decimal number from the console
private static double readDouble() {
        String input = scanner.nextLine().trim();

        try {
            return Double.parseDouble(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(
                    "Please enter a valid number, such as 300000.");
        }
    }
 // Apply a student to a job
    private static void applyForJob() throws SQLException {
    System.out.println("\n--- Apply for a Job ---");

    System.out.print("Enter student ID: ");
    int studentId = readInt();

    System.out.print("Enter job ID: ");
    int jobId = readInt();

    try {
        applicationService.applyForJob(studentId, jobId);
        System.out.println(
                "Job application submitted successfully!");

    } catch (IllegalArgumentException e) {
        System.out.println("Application error: " + e.getMessage());
    }
}

    // View all job applications
    private static void viewAllApplications() throws SQLException {
        List<Application> applications =
                applicationService.getAllApplications();

        if (applications.isEmpty()) {
            System.out.println("No applications found.");
            return;
        }

        System.out.println("\n--- All Job Applications ---");

        for (Application application : applications) {
            System.out.println("---------------------------");
            application.displayDetails();
        }
    }

    // Update an application's status
    private static void updateApplicationStatus() throws SQLException {
        System.out.println("\n--- Update Application Status ---");

        System.out.print("Enter application ID: ");
        int applicationId = readInt();

        System.out.println("Available statuses:");
        System.out.println("1. APPLIED");
        System.out.println("2. SHORTLISTED");
        System.out.println("3. INTERVIEW");
        System.out.println("4. SELECTED");
        System.out.println("5. REJECTED");

        System.out.print("Choose the new status (1-5): ");
        int choice = readInt();

        String status;

        switch (choice) {
            case 1:
                status = "APPLIED";
                break;
            case 2:
                status = "SHORTLISTED";
                break;
            case 3:
                status = "INTERVIEW";
                break;
            case 4:
                status = "SELECTED";
                break;
            case 5:
                status = "REJECTED";
                break;
            default:
                System.out.println("Invalid status choice.");
                return;
        }

        boolean updated = applicationService.updateApplicationStatus(
                applicationId, status);

        if (updated) {
            System.out.println("Application status updated successfully!");
        } else {
            System.out.println(
                    "No application found with ID " + applicationId);
        }
    }
    private static void viewPlacementAnalytics() throws SQLException {
        System.out.println("\n======================================");
        System.out.println("       PLACEMENT ANALYTICS");
        System.out.println("======================================");

        int totalStudents = analyticsDAO.getTotalStudents();
        int totalCompanies = analyticsDAO.getTotalCompanies();
        int activeJobs = analyticsDAO.getActiveJobs();
        int totalApplications = analyticsDAO.getTotalApplications();
        int selectedApplications =
                analyticsDAO.getSelectedApplications();

        System.out.println("Total Students          : " + totalStudents);
        System.out.println("Total Companies         : " + totalCompanies);
        System.out.println("Active Job Openings     : " + activeJobs);
        System.out.println("Total Applications      : " + totalApplications);
        System.out.println("Selected Applications   : " + selectedApplications);

        System.out.println("======================================");
    }
    
    private static void searchJobsBySkill() throws SQLException {
        System.out.println("\n--- Search Jobs by Skill ---");

        System.out.print("Enter a skill (e.g., Java, Python, SQL): ");
        String skill = scanner.nextLine().trim();

        List<Job> jobs = jobService.searchJobsBySkill(skill);

        if (jobs.isEmpty()) {
            System.out.println(
                    "No active jobs found matching: " + skill);
            return;
        }

        System.out.println("\n--- Matching Job Openings ---");

        for (Job job : jobs) {
            System.out.println("---------------------------");
            job.displayDetails();
        }
    }

}

