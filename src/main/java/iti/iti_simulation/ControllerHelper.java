package iti.iti_simulation;

import iti.iti_simulation.instructor_controller.InstructorMainScreenController;
import iti.iti_simulation.student_controller.MainScreenController;
import iti.models.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class ControllerHelper {

    private FXMLLoader mainScreenLoader;

    private static final FXMLLoader ABOUTVIEW =
            new FXMLLoader(ControllerHelper.class.getResource("/iti/iti_simulation/about.fxml"));

    private boolean showAboutView;

    public boolean isShowAboutView() {
        return showAboutView;
    }

    public void setShowAboutView(boolean showAboutView) {
        this.showAboutView = showAboutView;
    }



    public FXMLLoader getMainScreenLoader() throws IOException {
        return mainScreenLoader;
    }

    public ControllerHelper(Role role){
        this.mainScreenLoader = new FXMLLoader(getClass().getResource(getFXMLPath(role)));
    }

    public void loadMainScreen(User user, ActionEvent event) {
        try {
            FXMLLoader loader = getMainScreenLoader();
            Parent root = loader.load();
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

            if (user instanceof Student) {
                Student student = (Student) DataHolder.getCurrentUser();
                MainScreenController studentMainController = loader.getController();
                studentMainController.setStudentEmail(student.getUserEmail());
                studentMainController.setStudentName(student.getUserName());
                studentMainController.setPhoneNumber(student.getUserPhoneNumber());
                studentMainController.setAddress(student.getUserAddress());
                if(showAboutView){
                    AnchorPane aboutAnchorPane = ABOUTVIEW.load();
                    studentMainController.setViewAnchorPane(aboutAnchorPane);
                }
            } else if (user instanceof Supervisor) {
                Supervisor supervisor = (Supervisor) DataHolder.getCurrentUser();
                System.out.println("supervisor of:"+supervisor.getSupervisor_of());

            } else if (user instanceof Instructor) {
                Instructor instructor = (Instructor) DataHolder.getCurrentUser();
                InstructorMainScreenController instructorMainScreenController = loader.getController();
                instructorMainScreenController.setInstructorEmail(instructor.getUserEmail());
                instructorMainScreenController.setInstructorName(instructor.getUserName());
                instructorMainScreenController.setAddress(instructor.getUserAddress());
                instructorMainScreenController.setPhoneNumber(instructor.getUserPhoneNumber());
                if(showAboutView){
                    AnchorPane aboutAnchorPane = ABOUTVIEW.load();
                    instructorMainScreenController.setViewAnchorPane(aboutAnchorPane);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Error loading main screen", e);
        }
    }

    private String getFXMLPath(Role role) {
        switch (role) {
            case STUDENT:
                return "/iti/iti_simulation/student_view/student_main_screen.fxml";
            case INSTRUCTOR:
                return "/iti/iti_simulation/instructor_view/instructor_main_screen.fxml";
            case SUPERVISOR:
                return "/iti/iti_simulation/supervisor_view/supervisor_main_screen.fxml";
            default:
                throw new IllegalArgumentException("Unknown role: " + role.name());
        }
    }

    public <T> T getScreenController(){
        return (T) mainScreenLoader.getController();
    }
}
