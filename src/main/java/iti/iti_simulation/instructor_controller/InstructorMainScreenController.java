package iti.iti_simulation.instructor_controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.IOException;

public class InstructorMainScreenController {

    @FXML
    private AnchorPane centerAnchorPane;

    @FXML
    private HBox hboxInstructorData;

    @FXML
    private AnchorPane instructorDataAnchorPane;

    @FXML
    private Label instructorNameLabel;

    @FXML
    private Label instructorEmail;

    @FXML
    private Label addressLabel;

    @FXML
    private Label phoneNumberLabel;

    @FXML
    private Button changePasswordButton;

    @FXML
    private Button aboutButton;

    @FXML
    private AnchorPane viewAnchorPane;

    @FXML
    private Button programButton;

    @FXML
    private Button classesListButton;

    @FXML
    private Button classLogisticsButton;

    @FXML
    private Button reportButton;


    @FXML
    private Button logOutButton;
    private Stage stage;
    private Scene scene;
    private Parent root;

    public void setInstructorName(String instructorName) {
        this.instructorNameLabel.setText(instructorName);
    }

    public void setInstructorEmail(String instructorEmail) {
        this.instructorEmail.setText(instructorEmail);
    }

    public void setAddress(String address) {
        this.addressLabel.setText(address);
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumberLabel.setText(phoneNumber);
    }

    public void setViewAnchorPane(AnchorPane viewAnchorPane) {
        this.viewAnchorPane.getChildren().setAll(viewAnchorPane);
    }

    private void loadFXMLView(String fxmlPath) {
        try {
            AnchorPane newView = FXMLLoader.load(getClass().getResource(fxmlPath));
            viewAnchorPane.getChildren().setAll(newView);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void changePassword(ActionEvent event) throws IOException {
        loadFXMLView("/iti/iti_simulation/change_password.fxml");
    }

    @FXML
    void logOut(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/iti/iti_simulation/login_view/login.fxml"));
        root = loader.load();
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    void showAbout(ActionEvent event) throws IOException {
        loadFXMLView("/iti/iti_simulation/about.fxml");
    }

    @FXML
    void showClassLogistics(ActionEvent event) throws IOException {
        loadFXMLView("/iti/iti_simulation/instructor_view/class_logistics.fxml");

    }

    @FXML
    void showClasses(ActionEvent event) throws IOException {
        loadFXMLView("/iti/iti_simulation/instructor_view/classes_list.fxml");

    }

    @FXML
    void showCourses(ActionEvent event) throws IOException {
        loadFXMLView("/iti/iti_simulation/instructor_view/course_list.fxml");
    }

    @FXML
    void showQualifications(ActionEvent event) throws IOException {
        loadFXMLView("/iti/iti_simulation/instructor_view/qualifications.fxml");

    }

    @FXML
    void showReport(ActionEvent event) throws IOException {
        loadFXMLView("/iti/iti_simulation/instructor_view/report.fxml");
    }

}
