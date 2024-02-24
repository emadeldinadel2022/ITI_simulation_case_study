package iti.iti_simulation.student_controller;

import iti.iti_simulation.DataHolder;
import iti.iti_simulation.TableViewHelper;
import iti.models.Course;
import iti.models.Student;
import iti.services.CourseService;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.collections.FXCollections;



import java.util.ArrayList;

public class CourseListController implements TableViewHelper {

    @FXML
    private TableView<Course> courseListTableView;

    @FXML
    private TableColumn<Course, Integer> colCourseCode;

    @FXML
    private TableColumn<Course, String> colCourseName;

    @FXML
    private TableColumn<Course, Integer> colCourseCreditHours;

    @FXML
    private TextArea txtAreaCourseDescription;

    @FXML
    private TextArea txtAreaCourseSyllabus;

    private CourseService courseService;

    ObservableList<Course> coursesObservableList = FXCollections.observableArrayList();

    @FXML
    public void initialize(){

        Student student = (Student) DataHolder.getCurrentUser();
        courseService = new CourseService();
        ArrayList<Course> coursesList = (ArrayList<Course>) courseService.getStudentCourseListView(student.getProgramID());
        fillRecords(coursesList);

        courseListTableView.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                txtAreaCourseDescription.setText(newSelection.getCourseDescription());
                txtAreaCourseSyllabus.setText(newSelection.getCourseSyllabus());

            }
        });
    }

    @Override
    public void fillRecords(ArrayList arrayListRecords) {
        coursesObservableList.setAll(arrayListRecords);
        courseListTableView.setItems(coursesObservableList);

        colCourseCode.setCellValueFactory(cellData -> {
            return new SimpleIntegerProperty(cellData.getValue().getCourseCode()).asObject();
        });

        colCourseName.setCellValueFactory(cellData -> {
            return new SimpleStringProperty(cellData.getValue().getCourseName());
        });

        colCourseCreditHours.setCellValueFactory(cellData -> {
            return new SimpleIntegerProperty(cellData.getValue().getCourseCreditHours()).asObject();
        });
    }


}
