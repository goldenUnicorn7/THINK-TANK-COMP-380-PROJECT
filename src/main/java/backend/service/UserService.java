package backend.service;

import backend.dao.UserDAO;
import backend.model.User;

/**
 * Class Name: UserService
 * Date: July 5, 2026
 * Programmer: Snigdha Bolisetty
 *
 * Description:
 * Provides business logic for user authentication and registration in the
 * car rental desktop application. This service validates user input before
 * sending login and registration requests to the UserDAO class.
 *
 * Important Functions:
 * login() validates the user's email and password before checking the database.
 * register() validates all registration fields, checks whether the email
 * already exists, creates a User object, and stores it through the DAO layer.
 *
 * Important Data Structures:
 * The User model class is used to store account information. No complex data
 * structures are required because this service mainly validates input and
 * coordinates operations between the frontend and database layers.
 *
 * Algorithm:
 * Each method checks that required values are not null or blank. Registration
 * also checks for an existing email before creating and saving a new user.
 *
 * @author Snigdha Bolisetty
 * @version 1.0
 */

public class UserService {

    /** The UserDAO instance used for database access. */
    private final UserDAO userDAO;

    /**
     * Constructs a UserService instance and initializes the UserDAO.
     */
    public UserService() {
        this.userDAO = new UserDAO();
    }

    /**
     * Authenticates a user by validating their email and password.
     * Returns the User object if authentication is successful, or null if
     * validation fails or the credentials are incorrect.
     *
     * @param email    The user's email address
     * @param password The user's password
     * @return The authenticated User object, or null if authentication fails
     */
    public User login(String email, String password) {
        if (email == null || email.trim().isEmpty()) {
            System.out.println("Email is required.");
            return null;
        }

        if (password == null || password.trim().isEmpty()) {
            System.out.println("Password is required.");
            return null;
        }

        return userDAO.login(email, password);
    }

    /**
     * Registers a new user by validating their phone number, password, email,
     * and name. Returns true if registration is successful, or false if
     * validation fails or the email already exists.
     *
     * @param phoneNumber The user's phone number
     * @param password    The user's password
     * @param email       The user's email address
     * @param name        The user's name
     * @return true if registration is successful, false otherwise
     */
    public boolean register(String phoneNumber, String password, String email, String name) {
        if (phoneNumber == null || phoneNumber.trim().isEmpty()) {
            System.out.println("Phone number is required.");
            return false;
        }

        if (password == null || password.trim().isEmpty()) {
            System.out.println("Password is required.");
            return false;
        }

        if (email == null || email.trim().isEmpty()) {
            System.out.println("Email is required.");
            return false;
        }

        if (name == null || name.trim().isEmpty()) {
            System.out.println("Name is required.");
            return false;
        }

        if (userDAO.emailExists(email)) {
            System.out.println("Email already exists.");
            return false;
        }

        User user = new User(phoneNumber, password, email, name);
        return userDAO.register(user);
    }
}
