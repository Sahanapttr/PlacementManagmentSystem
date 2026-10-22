
package dao;

import util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AnalyticsDAO {

    public int getTotalStudents() throws SQLException {
        return getCount("SELECT COUNT(*) FROM students");
    }

    public int getTotalCompanies() throws SQLException {
        return getCount("SELECT COUNT(*) FROM companies");
    }

    public int getActiveJobs() throws SQLException {
        return getCount(
                "SELECT COUNT(*) FROM jobs WHERE is_active = TRUE");
    }

    public int getTotalApplications() throws SQLException {
        return getCount("SELECT COUNT(*) FROM applications");
    }

    public int getSelectedApplications() throws SQLException {
        return getCount(
                "SELECT COUNT(*) FROM applications WHERE status = 'SELECTED'");
    }

    private int getCount(String sql) throws SQLException {
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            if (resultSet.next()) {
                return resultSet.getInt(1);
            }

            return 0;
        }
    }
}
