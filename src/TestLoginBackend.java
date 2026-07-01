import backend.model.User;
import backend.service.UserService;

public class TestLoginBackend {

    public static void main(String[] args) {
        UserService userService = new UserService();

        System.out.println("Testing login backend...");

        boolean registered = userService.register(
                "1234567890",
                "1234",
                "testuser@example.com",
                "Test User"
        );

        System.out.println("Registered: " + registered);

        User user = userService.login(
                "testuser@example.com",
                "1234"
        );

        if (user != null) {
            System.out.println("Login successful.");
            System.out.println("User ID: " + user.getUserId());
            System.out.println("Name: " + user.getName());
            System.out.println("Email: " + user.getEmail());
            System.out.println("Phone: " + user.getPhoneNumber());
        } else {
            System.out.println("Login failed.");
        }
    }
}
