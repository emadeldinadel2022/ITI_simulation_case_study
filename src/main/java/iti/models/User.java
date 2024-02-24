package iti.models;

public abstract class User {
    private String userID;
    private String userName;
    private String userEmail;
    private String userPassword;
    private String userAddress;
    private String userPhoneNumber;
    private Role role;
    private int trackID;


    public User(String userID, String userName, String userEmail, String userPhoneNumber, String userAddress, int trackID) {
        this.userID = userID;
        this.userName = userName;
        this.userEmail = userEmail;
        this.userAddress = userAddress;
        this.userPhoneNumber = userPhoneNumber;
        this.trackID = trackID;
    }

    public User(String userEmail, String userPassword, Role role) {
        this.userEmail = userEmail;
        this.userPassword = userPassword;
        this.role = role;
    }

    public User(String userID, String userName) {
        this.userID = userID;
        this.userName = userName;
    }

    public String getUserID() {
        return userID;
    }

    public String getUserName() {
        return userName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public String getUserAddress() {
        return userAddress;
    }

    public String getUserPhoneNumber() {
        return userPhoneNumber;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public int getTrackID() {
        return trackID;
    }

}
