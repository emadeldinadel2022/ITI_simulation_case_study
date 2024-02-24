package iti.iti_simulation.login_controller;


import iti.iti_simulation.ControllerHelper;
import iti.iti_simulation.DataHolder;
import iti.models.User;
import iti.models.Role;
import iti.services.LoginService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import java.util.Arrays;



public class LoginController {
    @FXML
    private Button exitButton;
    @FXML
    private TextField txtFieldUserEmail;
    @FXML
    private PasswordField txtFieldUserPassword;
    @FXML
    private ChoiceBox<Role> roleChoiceBox;
    @FXML
    private Button loginButton;
    @FXML
    private Label loginAlert;
    private Role selectedRole;
    private LoginService loginService;
    private ControllerHelper controllerHelper;

    @FXML
    public void initialize() {
        Role[] roles = Arrays.copyOf(Role.values(), Role.values().length - 1);
        roleChoiceBox.getItems().setAll(roles);
    }

    @FXML
    void userLogin(ActionEvent event) {
        if (isEmpty()) {
            loginAlert.setText("Complete your Information, fields are empty");
            return;
        }

        selectedRole = roleChoiceBox.getValue();
        if (selectedRole == null) {
            loginAlert.setText("There is no role selected, please select a role");
            return;
        }

        String userEmail = txtFieldUserEmail.getText();
        String userPassword = txtFieldUserPassword.getText();

        if (userEmail.isEmpty()) {
            loginAlert.setText("Email field is empty, please enter your email");
            return;
        }

        if (userPassword.isEmpty()) {
            loginAlert.setText("Password field is empty, please enter your password");
            return;
        }

        if (!LoginService.isValidEmail(userEmail)) {
            loginAlert.setText("Invalid email pattern, please enter valid email");
            return;
        }

        if (!LoginService.isValidPassword(userPassword)) {
            loginAlert.setText("Very small password, please enter valid password");
            return;
        }

        loginService = new LoginService(selectedRole);
        if (!loginService.login(userEmail, userPassword)) {
            loginAlert.setText("Login failed. Invalid username or password.");
            return;
        }

        User currentUser = loginService.getUserModel(userEmail, loginService.getUserDAO());
        currentUser.setRole(selectedRole);
        DataHolder.setCurrentUser(currentUser);

        controllerHelper = new ControllerHelper(selectedRole);
        controllerHelper.setShowAboutView(true);
        controllerHelper.loadMainScreen(currentUser, event);
    }

    private boolean isEmpty(){
        return txtFieldUserEmail.getLength() <= 0
                && txtFieldUserPassword.getLength() <= 0
                && selectedRole == null;
    }

}
