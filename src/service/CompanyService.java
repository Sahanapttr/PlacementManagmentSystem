package service;

import dao.CompanyDAO;
import model.Company;

import java.sql.SQLException;
import java.util.List;

public class CompanyService {

    private final CompanyDAO companyDAO = new CompanyDAO();

    // Register a company after validating its details
    public void registerCompany(String companyName,
                               String industry,
                               String location,
                               String contactEmail) throws SQLException {

        if (companyName == null || companyName.trim().isEmpty()) {
            throw new IllegalArgumentException(
                    "Company name cannot be empty.");
        }

        if (industry == null || industry.trim().isEmpty()) {
            throw new IllegalArgumentException(
                    "Industry cannot be empty.");
        }

        if (location == null || location.trim().isEmpty()) {
            throw new IllegalArgumentException(
                    "Location cannot be empty.");
        }

        if (contactEmail != null && !contactEmail.trim().isEmpty()
                && !contactEmail.trim().matches(
                        "^[\\w.+-]+@[\\w.-]+\\.[A-Za-z]{2,}$")) {
            throw new IllegalArgumentException(
                    "Enter a valid contact email address.");
        }

        Company company = new Company(
                0,
                companyName.trim(),
                industry.trim(),
                location.trim(),
                contactEmail == null ? "" : contactEmail.trim()
        );

        if (!companyDAO.addCompany(company)) {
            throw new SQLException("Company registration failed.");
        }
    }

    // Retrieve all registered companies
    public List<Company> getAllCompanies() throws SQLException {
        return companyDAO.getAllCompanies();
    }
}