package iti.iti_simulation.instructor_controller;

import iti.iti_simulation.DataHolder;
import iti.iti_simulation.TableViewHelper;
import iti.iti_simulation.TableViewHelperAdapter;
import iti.models.Instructor;
import iti.models.IntakeClass;
import iti.models.Schedule;
import iti.services.ClassService;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.util.ArrayList;

public class ClassListController {

    @FXML
    private TableView<IntakeClass> tableViewClasses;

    @FXML
    private TableColumn<IntakeClass, String> colCourseName;

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
    private TableColumn<Schedule, String> colCSchCourseName;

    @FXML
    private TableColumn<Schedule, String> colCourseDay;

    @FXML
    private TableColumn<Schedule, String> colStartTime;

    @FXML
    private TableColumn<Schedule, String> colEndTime;

    private ClassService classService;

    private TableViewHelper<IntakeClass> classTableViewHelper;
    private TableViewHelper<Schedule> scheduleTableViewHelper;

    ObservableList<IntakeClass> classesObservableList = FXCollections.observableArrayList();
    ObservableList<Schedule> timeTableObservableList = FXCollections.observableArrayList();

    @FXML
    public void initialize(){
        classTableViewHelper = new TableViewHelperAdapter<>(tableViewClasses, classesObservableList);
        scheduleTableViewHelper = new TableViewHelperAdapter<>(tableViewSchedule, timeTableObservableList);

        Instructor student = (Instructor) DataHolder.getCurrentUser();
        classService = new ClassService();

        ArrayList<IntakeClass> studentClasses = classService.fetchInstructorClasses(student.getUserID());
        classTableViewHelper.fillRecords(studentClasses);

        colCourseName.setCellValueFactory(cellData -> {
            return new SimpleStringProperty(cellData.getValue().getCourseName());
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

            }
        });

    }

}
