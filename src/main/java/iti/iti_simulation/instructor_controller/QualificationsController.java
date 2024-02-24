package iti.iti_simulation.instructor_controller;

import iti.data_access_layer.InstructorDAO;
import iti.iti_simulation.DataHolder;
import iti.iti_simulation.TableViewHelper;
import iti.models.Course;
import iti.models.Instructor;
import iti.models.InstructorCourseExperience;
import iti.models.Qualification;
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

public class QualificationsController implements TableViewHelper {

    @FXML
    private TableView<Qualification> tableViewMyQual;

    @FXML
    private TableColumn<Qualification, Integer> colQualID;

    @FXML
    private TableColumn<Qualification, String> colQualName;

    @FXML
    private TableView<Qualification> tableViewSysQual;

    @FXML
    private TableColumn<Qualification, Integer> colSysQualID;

    @FXML
    private TableColumn<Qualification, String> colSysQualName;

    @FXML
    private Button bttnAddQul;
    private InstructorService instructorService;

    ObservableList<Qualification> instructorQualificationsObservableList = FXCollections.observableArrayList();
    ObservableList<Qualification> systemQualificationsObservableList = FXCollections.observableArrayList();
    Instructor instructor;


    @FXML
    public void initialize(){
        instructor = (Instructor) DataHolder.getCurrentUser();
        instructorService = new InstructorService();

        ArrayList<Qualification> instructorCertifications = (ArrayList<Qualification>) instructorService.getInstructorCertifications(instructor.getUserID());
        ArrayList<Qualification> systemCertifications = (ArrayList<Qualification>) instructorService.getSystemCertifications(instructor.getUserID());
        instructor.setInstructorQualifications(instructorCertifications);

        fillRecords((ArrayList) instructor.getInstructorQualifications());
        fillSecondTable(systemCertifications);

    }

    @FXML
    void addQualification(ActionEvent event) {
        Qualification selectedQualification = tableViewSysQual.getSelectionModel().getSelectedItem();

        if (selectedQualification == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Please select a certificate from system qualification table");
            alert.showAndWait();
        } else {
            tableViewSysQual.getItems().remove(selectedQualification);
            Qualification certification = new Qualification(selectedQualification.getQualificationID(), selectedQualification.getQualificationName());
            if (instructorService.addInstructorQualification(selectedQualification.getQualificationID(), instructor.getUserID())) {
                tableViewMyQual.getItems().add(selectedQualification);
            }else{
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("Failed to add certification, please try again later");
                alert.showAndWait();
            }

        }

    }

    @Override
    public void fillRecords(ArrayList arrayListRecords) {
        instructorQualificationsObservableList.setAll(arrayListRecords);
        tableViewMyQual.setItems(instructorQualificationsObservableList);

        colQualID.setCellValueFactory(cellData -> {
            return new SimpleIntegerProperty(cellData.getValue().getQualificationID()).asObject();
        });

        colQualName.setCellValueFactory(cellData -> {
            return new SimpleStringProperty(cellData.getValue().getQualificationName());
        });
    }

    public void fillSecondTable(ArrayList arrayListRecords){
        systemQualificationsObservableList.setAll(arrayListRecords);
        tableViewSysQual.setItems(systemQualificationsObservableList);

        colSysQualID.setCellValueFactory(cellData -> {
            return new SimpleIntegerProperty(cellData.getValue().getQualificationID()).asObject();
        });

        colSysQualName.setCellValueFactory(cellData -> {
            return new SimpleStringProperty(cellData.getValue().getQualificationName());
        });
    }
}
