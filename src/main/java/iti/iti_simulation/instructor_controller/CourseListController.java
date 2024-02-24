package iti.iti_simulation.instructor_controller;

import iti.iti_simulation.DataHolder;
import iti.iti_simulation.TableViewHelper;
import iti.iti_simulation.TableViewHelperAdapter;
import iti.models.Course;
import iti.models.Instructor;
import iti.models.InstructorCourseExperience;
import iti.services.CourseService;
import iti.services.InstructorService;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.util.ArrayList;

public class CourseListController{

    @FXML
    private TableView<InstructorCourseExperience> tableViewMyExpCourses;

    @FXML
    private TableColumn<InstructorCourseExperience, Integer> colMyCourseID;

    @FXML
    private TableColumn<InstructorCourseExperience, String> colMyCourseName;

    @FXML
    private TableColumn<InstructorCourseExperience, Integer> colMyExpYears;

    @FXML
    private TableView<Course> tableViewSysCourses;

    @FXML
    private TableColumn<Course, Integer> colCourseID;

    @FXML
    private TableColumn<Course, String> colCourseName;

    @FXML
    private Button bttnAddCourseExp;

    private CourseService courseService;

    private InstructorService instructorService;

    private TableViewHelper<Course> systemCoursesTableViewHelper;
    private TableViewHelper<InstructorCourseExperience> instructorCoursesTableViewHelper;

    ObservableList<InstructorCourseExperience> instructorCoursesObservableList = FXCollections.observableArrayList();
    ObservableList<Course> systemCoursesObservableList = FXCollections.observableArrayList();

    public void initialize(){
        systemCoursesTableViewHelper = new TableViewHelperAdapter<>(tableViewSysCourses, systemCoursesObservableList);
        instructorCoursesTableViewHelper = new TableViewHelperAdapter<>(tableViewMyExpCourses, instructorCoursesObservableList);

        Instructor instructor = (Instructor) DataHolder.getCurrentUser();
        courseService = new CourseService();
        instructorService = new InstructorService();

        ArrayList<InstructorCourseExperience> instructorCourses = (ArrayList<InstructorCourseExperience>)
                courseService.getInstructorExperiencedCourses(instructor.getUserID());
        ArrayList<Course> systemCourses = (ArrayList<Course>) courseService.getSystemCourse(instructor.getUserID());
        instructor.setExperiencedCourses(instructorCourses);

        systemCoursesTableViewHelper.fillRecords(systemCourses);
        instructorCoursesTableViewHelper.fillRecords(instructorCourses);

        colCourseID.setCellValueFactory(cellData -> {
            return new SimpleIntegerProperty(cellData.getValue().getCourseCode()).asObject();
        });

        colCourseName.setCellValueFactory(cellData -> {
            return new SimpleStringProperty(cellData.getValue().getCourseName());
        });

        colMyCourseID.setCellValueFactory(cellData -> {
            return new SimpleIntegerProperty(cellData.getValue().getCourseCode()).asObject();
        });

        colMyCourseName.setCellValueFactory(cellData -> {
            return new SimpleStringProperty(cellData.getValue().getCourseName());
        });

        colMyExpYears.setCellValueFactory(cellData -> {
            return new SimpleIntegerProperty(cellData.getValue().getYearsOfExperience()).asObject();
        });
    }

    @FXML
    void addCourseExperience(ActionEvent event) {
        Course selectedCourse = tableViewSysCourses.getSelectionModel().getSelectedItem();

        if (selectedCourse == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Please select a course from system courses table");
            alert.showAndWait();
        } else {
            TextInputDialog dialog = new TextInputDialog();
            dialog.setTitle("Years of Experience");
            dialog.setHeaderText("Enter years of experience:");
            dialog.setContentText("Years:");

            Stage stage = (Stage) dialog.getDialogPane().getScene().getWindow();
            stage.setAlwaysOnTop(true);
            dialog.showAndWait().ifPresent(result -> {
                try {
                    int yearsOfExperience = Integer.parseInt(result);

                    tableViewSysCourses.getItems().remove(selectedCourse);
                    InstructorCourseExperience newInstructorCourseExp =
                            new InstructorCourseExperience(selectedCourse.getCourseCode(), selectedCourse.getCourseName(), yearsOfExperience);
                    if(i)
                    tableViewMyExpCourses.getItems().add(newInstructorCourseExp);


                } catch (NumberFormatException e) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText(null);
                    alert.setContentText("Invalid input. Please enter a valid number.");
                    alert.showAndWait();
                }
            });
        }
    }



}
