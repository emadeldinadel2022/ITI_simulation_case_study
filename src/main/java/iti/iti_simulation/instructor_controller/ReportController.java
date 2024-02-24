package iti.iti_simulation.instructor_controller;

import iti.iti_simulation.DataHolder;
import iti.iti_simulation.TableViewHelper;
import iti.iti_simulation.TableViewHelperAdapter;
import iti.models.*;
import iti.services.ReportService;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.util.ArrayList;

public class ReportController {

    @FXML
    private TableView<InstructorCourseReport> tableViewCoursesreport;

    @FXML
    private TableColumn<InstructorCourseReport, String> coProgramName;

    @FXML
    private TableColumn<InstructorCourseReport, String> colCourseName;

    @FXML
    private TableColumn<InstructorCourseReport, Double> colAvgGrades;

    @FXML
    private TableView<FinalStudentReport> tableViewStudentReport;

    @FXML
    private TableColumn<FinalStudentReport, String> colStudentProgName;

    @FXML
    private TableColumn<FinalStudentReport, String> colStudentCourseName;

    @FXML
    private TableColumn<FinalStudentReport, String> colStudnetName;

    @FXML
    private TableColumn<FinalStudentReport, Double> colStudentGrade;
    private TableViewHelper<InstructorCourseReport> courseReportTableViewHelper;
    private TableViewHelper<FinalStudentReport> studentReportTableViewHelper;

    private ReportService reportService;

    ObservableList<FinalStudentReport> studentReportObservableList = FXCollections.observableArrayList();
    ObservableList<InstructorCourseReport> courseReportCoursesObservableList = FXCollections.observableArrayList();


    @FXML
    public void initialize() {
        courseReportTableViewHelper = new TableViewHelperAdapter<>(tableViewCoursesreport, courseReportCoursesObservableList);
        studentReportTableViewHelper = new TableViewHelperAdapter<>(tableViewStudentReport, studentReportObservableList);

        Instructor instructor = (Instructor) DataHolder.getCurrentUser();
        reportService = new ReportService();

        ArrayList<InstructorCourseReport> courseAvgGrades = reportService.fetchInstructorCourseAvgGradesReport(instructor.getUserID());
        ArrayList<FinalStudentReport> studentReports = reportService.fetchInstructorStudentReport(instructor.getUserID());

        courseReportTableViewHelper.fillRecords(courseAvgGrades);
        studentReportTableViewHelper.fillRecords(studentReports);

        coProgramName.setCellValueFactory(cellData -> {
            return new SimpleStringProperty(cellData.getValue().getProgramName());
        });

        colCourseName.setCellValueFactory(cellData -> {
            return new SimpleStringProperty(cellData.getValue().getCourseName());
        });

        colAvgGrades.setCellValueFactory(cellData -> {
            return new SimpleDoubleProperty(cellData.getValue().getAvgCourseGrade()).asObject();
        });

        colStudentProgName.setCellValueFactory(cellData -> {
            return new SimpleStringProperty(cellData.getValue().getProgramName());
        });

        colStudentCourseName.setCellValueFactory(cellData -> {
            return new SimpleStringProperty(cellData.getValue().getCourseName());
        });

        colStudnetName.setCellValueFactory(cellData -> {
            return new SimpleStringProperty(cellData.getValue().getStudentName());
        });

        colStudentGrade.setCellValueFactory(cellData -> {
            return new SimpleDoubleProperty(cellData.getValue().getFinalClassGrade()).asObject();
        });

    }
}
