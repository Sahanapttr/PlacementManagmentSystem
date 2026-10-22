package model;

public class Company {

    private int companyId;
    private String companyName;
    private String industry;
    private String location;
    private String contactEmail;

    public Company(int companyId, String companyName,
                   String industry, String location,
                   String contactEmail) {
        this.companyId = companyId;
        this.companyName = companyName;
        this.industry = industry;
        this.location = location;
        this.contactEmail = contactEmail;
    }

    public int getCompanyId() {
        return companyId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public String getIndustry() {
        return industry;
    }

    public String getLocation() {
        return location;
    }

    public String getContactEmail() {
        return contactEmail;
    }

    public void displayDetails() {
        System.out.println("Company ID: " + companyId);
        System.out.println("Company Name: " + companyName);
        System.out.println("Industry: " + industry);
        System.out.println("Location: " + location);
        System.out.println("Contact Email: " + contactEmail);
    }
}