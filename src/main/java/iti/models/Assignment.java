package iti.models;

import java.sql.Timestamp;

public class Assignment {
    private int assignmentID;
    private String assignmentDescription;
    private String assignmentType;
    private String deadline;
    private int classID;

    public Assignment(int assignmentID, String assignmentDescription, String assignmentType, String deadline, int classID) {
        this.assignmentID = assignmentID;
        this.assignmentDescription = assignmentDescription;
        this.assignmentType = assignmentType;
        this.deadline = deadline;
        this.classID = classID;
    }

    // Getters and setters
    public int getAssignmentID() {
        return assignmentID;
    }

    public String getAssignmentDescription() {
        return assignmentDescription;
    }

    public String getAssignmentType() {
        return assignmentType;
    }


    public String getDeadline() {
        return deadline;
    }

    public int getClassID() {
        return classID;
    }


}
