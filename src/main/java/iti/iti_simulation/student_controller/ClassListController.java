package iti.iti_simulation.student_controller;

import iti.iti_simulation.DataHolder;
import iti.iti_simulation.TableViewHelper;
import iti.iti_simulation.TableViewHelperAdapter;
import iti.models.*;
import iti.services.ClassService;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import java.text.SimpleDateFormat;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;

public class ClassListController {

    @FXML
    private TableView<IntakeClass> tableViewClasses;

    @FXML
    private TableColumn<IntakeClass, String> colCourseName;

    @FXML
    private TableColumn<IntakeClass, String> colInstructorName;

    @FXML
    private TableColumn<IntakeClass, String> colCourseStartDate;

    @FXML
    private TableColumn<IntakeClass, String> colCourseEndDate;

    @FXML
    private TableColumn<IntakeClass, Double> colTestPercent;

    @FXML
    private TableColumn<IntakeClass, Double> colAssignmentPercent;

    @FXML
    private TableColumn<IntakeClass, Double> colProjectPercent;

    @FXML
    private TableView<Schedule> tableViewSchedule;
    @FXML
    private TableColumn<Schedule, String> colCourseNameSchedule;

    @FXML
    private TableColumn<Schedule, String> colCourseDay;

    @FXML
    private TableColumn<Schedule, String> colCourseStartTime;

    @FXML
    private TableColumn<Schedule, String> colCourseEndTime;

    private ClassService classService;

    private TableViewHelper<IntakeClass> classTableViewHelper;
    private TableViewHelper<Schedule> scheduleTableViewHelper;

    ObservableList<IntakeClass> classesObservableList = FXCollections.observableArrayList();
    ObservableList<Schedule> timeTableObservableList = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        classTableViewHelper = new TableViewHelperAdapter<>(tableViewClasses, classesObservableList);
        scheduleTableViewHelper = new TableViewHelperAdapter<>(tableViewSchedule, timeTableObservableList);

        Student student = (Student) DataHolder.getCurrentUser();
        classService = new ClassService();

        ArrayList<IntakeClass> studentClasses = classService.fetchStudentClasses(student.getUserID());
        classTableViewHelper.fillRecords(studentClasses);

        colCourseName.setCellValueFactory(cellData -> {
            return new SimpleStringProperty(cellData.getValue().getCourseName());
        });

        colInstructorName.setCellValueFactory(cellData -> {
            return new SimpleStringProperty(cellData.getValue().getInstructorName());
        });

        colCourseStartDate.setCellValueFactory(cellData -> {
            return new SimpleStringProperty(cellData.getValue().getStartDate());
        });

        colCourseEndDate.setCellValueFactory(cellData -> {
            return new SimpleStringProperty(cellData.getValue().getEndDate());
        });

        colAssignmentPercent.setCellValueFactory(cellData -> {
            return new SimpleDoubleProperty(cellData.getValue().getAssignmentPercentage()).asObject();
        });

        colProjectPercent.setCellValueFactory(cellData -> {
            return new SimpleDoubleProperty(cellData.getValue().getProjectPercentage()).asObject();
        });


        colTestPercent.setCellValueFactory(cellData -> {
            return new SimpleDoubleProperty(cellData.getValue().getTestPercentage()).asObject();
        });

        tableViewClasses.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                ArrayList<Schedule> classTimeTable = classService.fetchClassTimeTable(newSelection.getClassID());
                scheduleTableViewHelper.fillRecords(classTimeTable);

                colCourseNameSchedule.setCellValueFactory(cellData -> {
                    return new SimpleStringProperty(cellData.getValue().getCourseName());
                });

                colCourseStartTime.setCellValueFactory(cellData -> {
                    return new SimpleStringProperty(cellData.getValue().getStartTime());
                });

                colCourseEndTime.setCellValueFactory(cellData -> {
                    return new SimpleStringProperty(cellData.getValue().getEndTime());
                });

                colCourseDay.setCellValueFactory(cellData -> {
                    return new SimpleStringProperty(cellData.getValue().getDayOfWeek());
                });

            }
        });
    }


}
