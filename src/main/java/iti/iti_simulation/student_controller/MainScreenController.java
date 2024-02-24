package iti.iti_simulation.student_controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MainScreenController {

    @FXML
    private AnchorPane centerAnchorPane;

    @FXML
    private HBox hboxStudentData;

    @FXML
    private AnchorPane studentDataAnchorPane;

    @FXML
    private Label studentNameLabel;

    @FXML
    private Label studentEmail;

    @FXML
    private Label addressLabel;

    @FXML
    private Label phoneNumberLabel;

    @FXML
    private Label totalGradeLabel;

    @FXML
    private Button changePasswordButton;

    @FXML
    private AnchorPane viewAnchorPane;

    @FXML
    private Button aboutButton;

    @FXML
    private Button programButton;

    @FXML
    private Button coursesListButton;

    @FXML
    private Button classesListButton;

    @FXML
    private Button reportButton;

    @FXML
    private Button logOutButton;

    private Stage stage;
    private Scene scene;
    private Parent root;


    public void setStudentName(String studentName) {
        this.studentNameLabel.setText(studentName);
    }

    public void setStudentEmail(String studentEmail) {
        this.studentEmail.setText(studentEmail);
    }

    public void setAddress(String address) {
        this.addressLabel.setText(address);
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumberLabel.setText(phoneNumber);
    }

    public void setTotalGrade(String totalGradeLabel) {
        this.totalGradeLabel.setText(totalGradeLabel);
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
    void LogOut(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/iti/iti_simulation/login_view/login.fxml"));
        root = loader.load();
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void changePassword(ActionEvent event) throws IOException {
        loadFXMLView("/iti/iti_simulation/change_password.fxml");
    }

    @FXML
    void getAbout(ActionEvent event) throws IOException {
        loadFXMLView("/iti/iti_simulation/about.fxml");
    }

    @FXML
    void getClassList(ActionEvent event) throws IOException {
        loadFXMLView("/iti/iti_simulation/student_view/class_list.fxml");
    }

    @FXML
    void getCourseList(ActionEvent event) throws IOException {
        loadFXMLView("/iti/iti_simulation/student_view/course_list.fxml");
    }

    @FXML
    void getProgramDetails(ActionEvent event) throws IOException {
        loadFXMLView("/iti/iti_simulation/student_view/program.fxml");
    }

    @FXML
    void showReport(ActionEvent event) throws IOException {
        loadFXMLView("/iti/iti_simulation/student_view/report.fxml");
    }

}
