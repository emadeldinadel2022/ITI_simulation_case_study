package iti.iti_simulation.student_controller;

import iti.iti_simulation.DataHolder;
import iti.iti_simulation.TableViewHelper;
import iti.models.Course;
import iti.models.FinalStudentReport;
import iti.models.Student;
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

public class ReportController implements TableViewHelper {

    @FXML
    private TableView<FinalStudentReport> tableViewStudentReport;

    @FXML
    private TableColumn<FinalStudentReport, String> colCourseName;

    @FXML
    private TableColumn<FinalStudentReport, String> colInstructorName;

    @FXML
    private TableColumn<FinalStudentReport, Double> colTestGrade;

    @FXML
    private TableColumn<FinalStudentReport, Double> colProjectGrade;

    @FXML
    private TableColumn<FinalStudentReport, Double> colAssignmentGrade;

    @FXML
    private TableColumn<FinalStudentReport, Double> colAttendencePercent;

    @FXML
    private TableColumn<FinalStudentReport, Double> colFinalGrade;
    ObservableList<FinalStudentReport> reprotObservableList = FXCollections.observableArrayList();


    private ReportService reportService;
    @FXML
    public void initialize(){
        Student student = (Student) DataHolder.getCurrentUser();
        reportService = new ReportService();
        ArrayList<FinalStudentReport> studentReport = reportService.fetchStudentReport(student.getUserID());
        fillRecords(studentReport);

    }

    @Override
    public void fillRecords(ArrayList arrayListRecords) {
        reprotObservableList.setAll(arrayListRecords);
        tableViewStudentReport.setItems(reprotObservableList);

        colCourseName.setCellValueFactory(cellData -> {
            return new SimpleStringProperty(cellData.getValue().getCourseName());
        });

        colInstructorName.setCellValueFactory(cellData -> {
            return new SimpleStringProperty(cellData.getValue().getInstructorName());
        });

        colFinalGrade.setCellValueFactory(cellData -> {
            return new SimpleDoubleProperty(cellData.getValue().getFinalClassGrade()).asObject();
        });

        colTestGrade.setCellValueFactory(cellData -> {
            return new SimpleDoubleProperty(cellData.getValue().getTestClassGarde()).asObject();
        });

        colProjectGrade.setCellValueFactory(cellData -> {
            return new SimpleDoubleProperty(cellData.getValue().getProjectClassGrade()).asObject();
        });

        colAssignmentGrade.setCellValueFactory(cellData -> {
            return new SimpleDoubleProperty(cellData.getValue().getAssignmentsClassScore()).asObject();
        });

        colAttendencePercent.setCellValueFactory(cellData -> {
            return new SimpleDoubleProperty(cellData.getValue().getAttendancePercentage()).asObject();
        });




    }
}
