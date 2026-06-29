package backend.model;

public class User {
    private int userID;
    private String userPhoneNumber;
    private String userPassword;
    private String userEmail;
    private String userName;

    public User() {}

    public User(int userID, String userPhoneNumber, String userPassword, String email, String name){
        this.userID = userID;
        this.userPhoneNumber = userPhoneNumber;
        this.userPassword = userPassword;
        this.userEmail = email;
        this.userName = name;
    }
    public User(String userPhoneNumber, String userPassword, String email, String name){
        this.userPhoneNumber = userPhoneNumber;
        this.userPassword = userPassword;
        this.userEmail = email;
        this.userName = name;
    }
    public int getUserId() {
        return userID;
    }
    public String getPhoneNumber() {
        return userPhoneNumber;
    }
    public String getPassword() {
        return userPassword;
    }
    public String getEmail() {
        return userEmail;
    }
    public String getName() {
        return userName;
    }
    public void setUserID(int userID) {
        this.userID = userID;
    }
    public void setUserPhoneNumber(String userPhoneNumber) {
        this.userPhoneNumber = userPhoneNumber;
    }
    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }
    public void setEmail(String userEmail) {
        this.userEmail = userEmail;
    }
    public void setName(String userName) {
        this.userName = userName;
    }

}
