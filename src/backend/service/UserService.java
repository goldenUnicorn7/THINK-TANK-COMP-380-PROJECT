package backend.service;

import backend.dao.UserDAO;
import backend.model.User;

public class UserService {

    private final UserDAO userDAO;

    public UserService() {
        this.userDAO = new UserDAO();
    }

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
