package backend.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import backend.db.DBConnection;
import backend.model.User;

public class UserDAO {

    public User login(String email, String password) {
        String sql = "SELECT * FROM Users WHERE UserEmail = ? AND UserPassword = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, email);
            stmt.setString(2, password);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new User(
                        rs.getInt("UserID"),
                        rs.getString("UserPhoneNum"),
                        rs.getString("UserPassword"),
                        rs.getString("UserEmail"),
                        rs.getString("UserName")
                );
            }

        } catch (SQLException e) {
            System.out.println("Login database error.");
            e.printStackTrace();
        }

        return null;
    }

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
            System.out.println("Register database error.");
            e.printStackTrace();
            return false;
        }
    }

    public boolean emailExists(String email) {
        String sql = "SELECT UserID FROM Users WHERE UserEmail = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, email);

            ResultSet rs = stmt.executeQuery();

            return rs.next();

        } catch (SQLException e) {
            System.out.println("Email check database error.");
            e.printStackTrace();
            return false;
        }
    }
}