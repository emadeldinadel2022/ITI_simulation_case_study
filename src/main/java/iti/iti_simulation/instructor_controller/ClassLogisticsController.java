package iti.iti_simulation.instructor_controller;

import iti.iti_simulation.ControllerHelper;
import iti.iti_simulation.DataHolder;
import iti.iti_simulation.TableViewHelper;
import iti.iti_simulation.TableViewHelperAdapter;
import iti.models.*;
import iti.services.ClassService;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class ClassLogisticsController {

    @FXML
    private TableView<IntakeClass> tableViewClassList;

    @FXML
    private TableColumn<IntakeClass, Integer> colClassID;

    @FXML
    private TableColumn<IntakeClass, String> colCourseName;

    @FXML
    private TableView<Student> tableViewStudentList;

    @FXML
    private TableColumn<Student, String> colStudentID;

    @FXML
    private TableColumn<Student, String> colStudentName;

    @FXML
    private Button bttnTestGrade;

    @FXML
    private Button bttnAttend;

    @FXML
    private Button bttnAddQul2;

    @FXML
    private Button bttnAssignment;

    @FXML
    private Label tableOneLabel;

    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    private TextField txtFieldGrade;

    private ControllerHelper controllerHelper;

    @FXML
    private TableView<Schedule> tableViewSchedule;

    @FXML
    private TableColumn<Schedule, String> colCSchCourseName;

    @FXML
    private TableColumn<Schedule, String> colCourseDay;

    @FXML
    private TableColumn<Schedule, String> colStartTime;

    @FXML
    private TableColumn<Schedule, String> colEndTime;

    private TableViewHelper<IntakeClass> classTableViewHelper;
    private TableViewHelper<Schedule> scheduleTableViewHelper;

    private TableViewHelper<Student> studentTableViewHelper;
    private ClassService classService;

    ObservableList<IntakeClass> classesObservableList = FXCollections.observableArrayList();
    ObservableList<Schedule> timeTableObservableList = FXCollections.observableArrayList();

    ObservableList<Student> studentClassObservableList = FXCollections.observableArrayList();

    @FXML
    public void initialize(){
        studentTableViewHelper = new TableViewHelperAdapter<>(tableViewStudentList, studentClassObservableList);
        classTableViewHelper = new TableViewHelperAdapter<>(tableViewClassList, classesObservableList);
        scheduleTableViewHelper = new TableViewHelperAdapter<>(tableViewSchedule, timeTableObservableList);
        controllerHelper = new ControllerHelper(Role.INSTRUCTOR);

        Instructor student = (Instructor) DataHolder.getCurrentUser();
        classService = new ClassService();

        ArrayList<IntakeClass> studentClasses = classService.fetchInstructorClasses(student.getUserID());
        classTableViewHelper.fillRecords(studentClasses);

        colClassID.setCellValueFactory(cellData -> {
            return new SimpleIntegerProperty(cellData.getValue().getClassID()).asObject();
        });

        colCourseName.setCellValueFactory(cellData -> {
            return new SimpleStringProperty(cellData.getValue().getCourseName());
        });

        tableViewClassList.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                ArrayList<Schedule> classTimeTable = classService.fetchClassTimeTable(newSelection.getClassID());
                scheduleTableViewHelper.fillRecords(classTimeTable);

                colCSchCourseName.setCellValueFactory(cellData -> {
                    return new SimpleStringProperty(cellData.getValue().getCourseName());
                });

                colStartTime.setCellValueFactory(cellData -> {
                    return new SimpleStringProperty(cellData.getValue().getStartTime());
                });

                colEndTime.setCellValueFactory(cellData -> {
                    return new SimpleStringProperty(cellData.getValue().getEndTime());
                });

                colCourseDay.setCellValueFactory(cellData -> {
                    return new SimpleStringProperty(cellData.getValue().getDayOfWeek());
                });

                ArrayList<Student> studentsList = classService.fetchStudentClass(newSelection.getClassID());
                studentTableViewHelper.fillRecords(studentsList);

                colStudentID.setCellValueFactory(cellData -> {
                    return new SimpleStringProperty(cellData.getValue().getUserID());
                });

                colStudentName.setCellValueFactory(cellData -> {
                    return new SimpleStringProperty(cellData.getValue().getUserName());
                });
            }
        });
    }
    @FXML
    void absentStudent(ActionEvent event) {

    }

    @FXML
    void assignTestGrade(ActionEvent event) {

    }

    @FXML
    void attendStudent(ActionEvent event) {

    }

    @FXML
    void showAssignments(ActionEvent event) throws IOException {

        Instructor instructor = (Instructor) DataHolder.getCurrentUser();
        IntakeClass selectedClass = tableViewClassList.getSelectionModel().getSelectedItem();
        if (selectedClass == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("No Class Selected");
            alert.setContentText("Please select a class from the class list table to view assignments.");
            alert.showAndWait();
            return;
        }


        try {
            controllerHelper.loadMainScreen(instructor, event);
            InstructorMainScreenController mainScreenController = controllerHelper.getMainScreenLoader().getController();

            String fxmlPath = "/iti/iti_simulation/instructor_view/assignments_view.fxml";

            FXMLLoader assignmentLoader = new FXMLLoader(getClass().getResource(fxmlPath));
            AnchorPane assignemntAnchorPane = assignmentLoader.load();
            AssignmentsController assignmentsController = assignmentLoader.getController();

            assignmentsController.updateScene(selectedClass.getClassID());
            mainScreenController.setViewAnchorPane(assignemntAnchorPane);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
