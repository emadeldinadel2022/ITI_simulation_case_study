package iti.models;

import java.sql.Date;

public class Program extends BaseEntity{
    private int trackID;
    private String programCategory;

    public Program(int id, String name, String description, Date establismentDate, int trackID, String programCategory) {
        super(id, name, description, establismentDate);
        this.programCategory = programCategory;
        this.trackID = trackID;
    }

    public int getTrackID() {
        return trackID;
    }

    public String getProgramCategory() {
        return programCategory;
    }


}
