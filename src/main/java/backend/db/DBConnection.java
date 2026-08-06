package backend.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Class Name: DBConnection
 * Date: July 4, 2026
 * Programmer: Carla Garcia
 *
 * Description:
 * This class provides a method to establish a connection to the MySQL database.
 * It encapsulates the database connection details and handles the connection process.
 *
 * Important Functions:
 * The getConnection() method establishes and returns a connection to the MySQL database.
 *
 * Important Data Structures:
 * The class uses constants to store the database URL, username, and password.
 *
 * Algorithm:
 * The getConnection() method loads the MySQL JDBC driver, establishes a connection using the provided URL, username, and password,
 * and returns the Connection object. If the driver is not found or a connection error occurs, it throws an SQLException.
 *
 * @author Carla Garcia
 * @version 1.0
 */

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