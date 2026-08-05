package backend.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import backend.db.DBConnection;
import backend.model.User;

/**
 * Class Name: UserDAO
 * Date: July 4, 2026
 * Programmer: Snigdha Bolisetty
 *
 * Description:
 * Provides database access operations for user accounts in the car rental
 * desktop application. This class handles user login, registration, and
 * checking whether an email address already exists.
 *
 * Important Functions:
 * login() verifies a user's credentials, register() inserts a new user into
 * the database, and emailExists() checks whether an email is already registered.
 *
 * Important Data Structures:
 * The User model class stores account information retrieved from or sent to
 * the database. PreparedStatement is used to safely execute parameterized
 * SQL queries.
 *
 * Algorithm:
 * Each method opens a database connection, prepares an SQL statement, assigns
 * the required parameters, executes the query or update, and returns the
 * resulting User object or success value.
 *
 * @author Snigdha Bolisetty
 * @version 1.0
 */

public class UserDAO {

    /**
     * Authenticates a user by checking their email and password against the
     * database. Returns a User object if successful, or null if authentication fails.
     *
     * @param email    The user's email address
     * @param password The user's password
     * @return The authenticated User object, or null if authentication fails
     */
    public User login(String email, String password) {
        String sql = "SELECT * FROM Users WHERE UserEmail = ? AND UserPassword = ?";

        System.out.println("Trying login with email: [" + email + "]");
        System.out.println("Trying login with password: [" + password + "]");

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, email.trim());
            stmt.setString(2, password.trim());

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                System.out.println("Login user found in database.");

                return new User(
                        rs.getInt("UserID"),
                        rs.getString("UserPhoneNum"),
                        rs.getString("UserPassword"),
                        rs.getString("UserEmail"),
                        rs.getString("UserName")
                );
            } else {
                System.out.println("No matching user found.");
            }

        } catch (SQLException e) {
            System.out.println("Login database error: " + e.getMessage());
            e.printStackTrace();
        }

        return null;
    }

    /**
     * Registers a new user by inserting their information into the database.
     * Returns true if registration is successful, or false if an error occurs.
     *
     * @param user The User object containing the new user's information
     * @return true if registration is successful, false otherwise
     */
    public boolean register(User user) {
        String sql = "INSERT INTO Users (UserPhoneNum, UserPassword, UserEmail, UserName) VALUES (?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, user.getPhoneNumber());
            stmt.setString(2, user.getPassword());
            stmt.setString(3, user.getEmail());
            stmt.setString(4, user.getName());

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Register database error: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Checks whether a given email address already exists in the database.
     * Returns true if the email exists, or false if it does not.
     *
     * @param email The email address to check
     * @return true if the email exists, false otherwise
     */
    public boolean emailExists(String email) {
        String sql = "SELECT UserID FROM Users WHERE UserEmail = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, email.trim());

            ResultSet rs = stmt.executeQuery();

            return rs.next();

        } catch (SQLException e) {
            System.out.println("Email check database error: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
}