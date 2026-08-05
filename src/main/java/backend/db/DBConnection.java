package backend.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Utility class for managing database connections. This class provides a method to establish a connection to the MySQL database.
 */
public class DBConnection {

    private static final String URL =
            "jdbc:mysql://localhost:3306/new_database";

    private static final String USER = "root";
    private static final String PASSWORD = "1234";

    /**
     * Establishes a connection to the MySQL database.
     * @return A Connection object representing the database connection.
     * @throws SQLException If a database access error occurs or the JDBC driver is not found.
     */
    public static Connection getConnection() throws SQLException {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("MySQL JDBC Driver not found.");
            e.printStackTrace();
            throw new SQLException("MySQL JDBC Driver not found.", e);
        }

        Connection connection =
                DriverManager.getConnection(URL, USER, PASSWORD);

        System.out.println("Connected to MySQL successfully.");
        System.out.println("Connected database: " + connection.getCatalog());

        return connection;
    }
}