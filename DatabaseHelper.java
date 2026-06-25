package vu.firstbankuganda;

import java.sql.*;

public class DatabaseHelper {

    // Path to your MS Access database file
    private static final String DB_URL = 
        "jdbc:ucanaccess://C:/Users/ADMIN/Documents/FirstBankUganda.accdb";

    // Saves a new account record to the database
    public static void saveAccount(String accountNumber, String firstName,
            String lastName, String nin, String email, String phone,
            String dob, String accountType, String branch, double deposit) {
        try {
            Connection conn = DriverManager.getConnection(DB_URL);
            String sql = "INSERT INTO Accounts (AccountNumber, FirstName, " +
                    "LastName, NationalID, Email, Phone, DateOfBirth, " +
                    "AccountType, Branch, OpeningDeposit) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, accountNumber);
            ps.setString(2, firstName);
            ps.setString(3, lastName);
            ps.setString(4, nin);
            ps.setString(5, email);
            ps.setString(6, phone);
            ps.setString(7, dob);
            ps.setString(8, accountType);
            ps.setString(9, branch);
            ps.setDouble(10, deposit);
            ps.executeUpdate();
            conn.close();
        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
        }
    }
}
