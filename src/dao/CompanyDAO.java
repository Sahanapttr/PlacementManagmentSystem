package dao;

import model.Company;
import util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CompanyDAO {

    // Add a new company to the database
    public boolean addCompany(Company company) throws SQLException {
        String sql = "INSERT INTO companies "
                + "(company_name, industry, location, contact_email) "
                + "VALUES (?, ?, ?, ?)";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(sql)) {

            statement.setString(1, company.getCompanyName());
            statement.setString(2, company.getIndustry());
            statement.setString(3, company.getLocation());
            statement.setString(4, company.getContactEmail());

            return statement.executeUpdate() > 0;
        }
    }

    // Retrieve all companies from the database
    public List<Company> getAllCompanies() throws SQLException {
        List<Company> companies = new ArrayList<>();

        String sql = "SELECT company_id, company_name, industry, "
                + "location, contact_email FROM companies "
                + "ORDER BY company_id";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Company company = new Company(
                        resultSet.getInt("company_id"),
                        resultSet.getString("company_name"),
                        resultSet.getString("industry"),
                        resultSet.getString("location"),
                        resultSet.getString("contact_email")
                );

                companies.add(company);
            }
        }

        return companies;
    }
}