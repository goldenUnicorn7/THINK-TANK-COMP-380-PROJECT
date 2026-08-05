package backend.model;

/**
 * Class Name: User
 * Date: June 28, 2026
 * Programmer: Snigdha Bolisetty
 *
 * Description:
 * Represents a registered user of the car rental desktop application.
 * The class stores the user's identifier, phone number, password,
 * email address, and name.
 *
 * Important Functions:
 * The constructors create User objects with or without an existing user ID.
 * Getter and setter methods provide access to and modification of the user's
 * account information.
 *
 * Important Data Structures:
 * This class uses primitive integer values and String objects to store user
 * information. No complex data structure is required because this model class
 * mainly transfers user data between the database, service layer, and frontend.
 *
 * Algorithm:
 * No complex algorithm is used. The class functions as a data container for
 * user registration, login, and account-related operations.
 *
 * @author Snigdha Bolisetty
 * @version 1.0
 */

/**
 * Represents a registered user of the car rental desktop application.
 * The class stores the user's identifier, phone number, password,
 * email address, and name.
 */
public class User {

    /** The unique identifier for the user. */
    private int userID;

    /** The phone number associated with the user's account. */
    private String userPhoneNumber;

    /** The password for the user's account. */
    private String userPassword;

    /** The email address associated with the user's account. */
    private String userEmail;

    /** The name of the user. */
    private String userName;

    /** Creates an empty User object. */
    public User() {
    }

    /** 
     * Creates a User object with the specified attributes, including user ID.
     *
     * @param userID The unique identifier for the user
     * @param userPhoneNumber The user's phone number
     * @param userPassword The user's password
     * @param email The user's email address
     * @param name The user's name
     */
    public User(int userID, String userPhoneNumber, String userPassword, String email, String name) {
        this.userID = userID;
        this.userPhoneNumber = userPhoneNumber;
        this.userPassword = userPassword;
        this.userEmail = email;
        this.userName = name;
    }

    /**
     * Creates a User object with the specified attributes, excluding user ID.
     *
     * @param userPhoneNumber The user's phone number
     * @param userPassword The user's password
     * @param email The user's email address
     * @param name The user's name
     */
    public User(String userPhoneNumber, String userPassword, String email, String name) {
        this.userPhoneNumber = userPhoneNumber;
        this.userPassword = userPassword;
        this.userEmail = email;
        this.userName = name;
    }

    /**
     * Returns the unique identifier for the user.
     *
     * @return The user ID
     */
    public int getUserId() {
        return userID;
    }

    /**
     * Returns the phone number associated with the user's account.
     *
     * @return The user's phone number
     */
    public String getPhoneNumber() {
        return userPhoneNumber;
    }

    /**
     * Returns the password for the user's account.
     *
     * @return The user's password
     */
    public String getPassword() {
        return userPassword;
    }

    /**
     * Returns the email address associated with the user's account.
     *
     * @return The user's email address
     */
    public String getEmail() {
        return userEmail;
    }

    /**
     * Returns the name of the user.
     *
     * @return The user's name
     */
    public String getName() {
        return userName;
    }

    /**
     * Sets the unique identifier for the user.
     *
     * @param userID The user ID to set
     */
    public void setUserID(int userID) {
        this.userID = userID;
    }

    /**
     * Sets the phone number associated with the user's account.
     *
     * @param userPhoneNumber The user's phone number to set
     */
    public void setUserPhoneNumber(String userPhoneNumber) {
        this.userPhoneNumber = userPhoneNumber;
    }

    /**
     * Sets the password for the user's account.
     *
     * @param userPassword The user's password to set
     */
    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    /**
     * Sets the email address associated with the user's account.
     *
     * @param userEmail The user's email address to set
     */
    public void setEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    /**
     * Sets the name of the user.
     *
     * @param userName The user's name to set
     */
    public void setName(String userName) {
        this.userName = userName;
    }

}
