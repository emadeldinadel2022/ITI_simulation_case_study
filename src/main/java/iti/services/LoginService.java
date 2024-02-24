package iti.services;

import iti.data_access_layer.*;
import iti.models.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LoginService {
    private UserDAO userDAO;

    public LoginService(Role role){
        getUserDAO(role);
    }
    public UserDAO getUserDAO() {
        return userDAO;
    }

    private void getUserDAO(Role role) {
        switch (role) {
            case STUDENT:
                this.userDAO = new StudentDAO();
                break;
            case INSTRUCTOR:
                this.userDAO = new InstructorDAO();
                break;
            case SUPERVISOR:
                this.userDAO = new SupervisorDAO();
                break;
            default:
                throw new IllegalArgumentException("Invalid role");
        }
    }

    public <T extends User> T getUserModel(String userEmail, UserDAO userDAO) {
        return (T) userDAO.findByUserEmail(userEmail);
    }


    public boolean login(String userEmail, String password) {
        boolean loginStatus = this.userDAO.checkLogin(userEmail, password);
        if (loginStatus) {
            System.out.println("Login successful");
            return true;
        } else {
            System.out.println("Login failed. Invalid username or password.");
            return false;
        }
    }

    public static boolean isValidEmail(String userEmail) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";

        Pattern pattern = Pattern.compile(emailRegex);

        Matcher matcher = pattern.matcher(userEmail);

        return matcher.matches();
    }

    public static boolean isValidPassword(String password){
        return password.length() >= 5;
    }

}
