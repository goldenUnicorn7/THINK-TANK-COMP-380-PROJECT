package backend;

import backend.model.User;

/**
 * Class Name: session
 * Date: July 6, 2026
 * Programmer: Snigdha Bolisetty
 *
 * Description:
 * This class manages the current user session in the car rental desktop application.
 * It provides methods to set, get, and clear the current logged-in user.
 *
 * Important Functions:
 * setCurrentUser(User user) sets the current user for the session.
 * getCurrentUser() retrieves the current logged-in user.
 * clear() clears the current user from the session.
 *
 * Important Data Structures:
 * The class uses a static User object to store the current user's information.
 *
 * Algorithm:
 * The class maintains a single static reference to the current user, allowing
 * easy access throughout the application. It provides methods to manage this
 * reference safely.
 *
 * @author Snigdha Bolisetty
 * @version 1.0
 */

public class session {

    /** The static User object representing the current logged-in user. */
    private static User currentUser;

    /** 
     * Sets the current user for the session.
     *
     * @param user The User object representing the logged-in user
     */
    public static void setCurrentUser(User user) {
        currentUser = user;
    }

    /** 
     * Retrieves the current logged-in user.
     *
     * @return The User object representing the current user, or null if no user is logged in
     */
    public static User getCurrentUser() {
        return currentUser;
    }

    /** 
     * Clears the current user from the session, effectively logging out the user.
     */
    public static void clear() {
        currentUser = null;
    }
}
