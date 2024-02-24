package iti.iti_simulation.instructor_controller;

import iti.iti_simulation.TableViewHelper;
import iti.iti_simulation.TableViewHelperAdapter;
import iti.models.Assignment;
import iti.models.Student;
import iti.services.ClassService;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import java.util.ArrayList;

public class AssignmentsController {

    @FXML
    private TableView<Assignment> tableViewMyQual;

    @FXML
    private TableColumn<Assignment, Integer> colAssignmentID;

    @FXML
    private TableColumn<Assignment, String> colAssignmentDescription;

    @FXML
    private TableColumn<Assignment, String> colDeadLine;

    @FXML
    private TableColumn<Assignment, String> colType;

    @FXML
    private TableView<Student> tableViewStudentList;

    @FXML
    private TableColumn<Student, String> colStudentID;

    @FXML
    private TableColumn<Student, String> colStudentName;

    @FXML
    private Button bttnAssignGrade;

    @FXML
    private TextField txtFieldGrade;

    private int classID;

    private TableViewHelper<Student> studentTableViewHelper;
    private TableViewHelper<Assignment> assignmentTableViewHelper;

    ObservableList<Assignment> assignmentsTableObservableList = FXCollections.observableArrayList();

    ObservableList<Student> studentsTableObservableList = FXCollections.observableArrayList();
    private ClassService classService;


    public void updateScene(int classID) {
        this.classID = classID;
        reflectOnScene();
    }

    private void reflectOnScene(){
        System.out.println("update: "+classID);
        classService = new ClassService();

        studentTableViewHelper = new TableViewHelperAdapter<>(tableViewStudentList, studentsTableObservableList);
        assignmentTableViewHelper = new TableViewHelperAdapter<>(tableViewMyQual, assignmentsTableObservableList);

        ArrayList<Assignment> classAssignments = classService.fetchClassAssignments(classID);
        assignmentTableViewHelper.fillRecords(classAssignments);

        colAssignmentID.setCellValueFactory(cellData -> {
            return new SimpleIntegerProperty(cellData.getValue().getAssignmentID()).asObject();
        });

        colAssignmentDescription.setCellValueFactory(cellData -> {
            return new SimpleStringProperty(cellData.getValue().getAssignmentDescription());
        });

        colDeadLine.setCellValueFactory(cellData -> {
            return new SimpleStringProperty(cellData.getValue().getDeadline());
        });

        colType.setCellValueFactory(cellData -> {
            return new SimpleStringProperty(cellData.getValue().getAssignmentType());
        });

        ArrayList<Student> studentsClass = classService.fetchStudentClass(classID);
        studentTableViewHelper.fillRecords(studentsClass);

        colStudentID.setCellValueFactory(cellData -> {
            return new SimpleStringProperty(cellData.getValue().getUserID());
        });

        colStudentName.setCellValueFactory(cellData -> {
            return new SimpleStringProperty(cellData.getValue().getUserName());
        });


    }
    public int getClassID() {
        return classID;
    }

    @FXML
    void assignGrade(ActionEvent event) {

    }

}
