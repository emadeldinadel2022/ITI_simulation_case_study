package iti.iti_simulation.student_controller;

import iti.iti_simulation.DataHolder;
import iti.models.Student;
import iti.models.Track;
import iti.models.Program;
import iti.services.TrackService;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import java.text.SimpleDateFormat;


import java.sql.Date;

public class ProgramController {

    @FXML
    private Label trackName;

    @FXML
    private Label businessField;

    @FXML
    private Label trackDate;

    @FXML
    private Label supervisorName;

    @FXML
    private TextArea trackDescription;

    @FXML
    private Label programName;

    @FXML
    private Label programCategory;

    @FXML
    private Label programDate;

    @FXML
    private TextArea programDescription;

    private TrackService trackService;

    @FXML
    public void initialize() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        trackService = new TrackService();
        Student student = (Student) DataHolder.getCurrentUser();

        Track studentTrack = trackService.getStudentTrackView(student.getTrackID(), student.getProgramID());

        trackName.setText(studentTrack.getName());
        businessField.setText(studentTrack.getBusinessField());
        supervisorName.setText(studentTrack.getSupervisorName());
        trackDate.setText(dateFormat.format(studentTrack.getEstablismentDate()));
        trackDescription.setText(studentTrack.getDescription());

        Program studentProgram = studentTrack.getTrackPrograms().get(0);

        programName.setText(studentProgram.getName());
        programCategory.setText(studentProgram.getProgramCategory());
        programDescription.setText(studentProgram.getDescription());
        programDate.setText(dateFormat.format(studentProgram.getEstablismentDate()));
    }

}
