package iti.models;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class Track extends BaseEntity{
    private String businessField;
    private String supervisorName;
    private List<Program> trackPrograms;


    public Track(int id, String name, String description, Date establismentDate, String businessField, String supervisorName) {
        super(id, name, description, establismentDate);
        this.businessField = businessField;
        this.supervisorName = supervisorName;
        this.trackPrograms = new ArrayList<>();
    }

    public List<Program> getTrackPrograms() {
        return trackPrograms;
    }

    public void setTrackPrograms(List<Program> trackPrograms) {
        this.trackPrograms = trackPrograms;
    }

    public String getBusinessField() {
        return businessField;
    }

    public String getSupervisorName() {
        return supervisorName;
    }


}
