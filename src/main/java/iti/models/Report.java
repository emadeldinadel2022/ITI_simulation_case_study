package iti.models;

public abstract class Report {
    private int classID;
    private String courseName;
    private String instructorSSN;
    private String instructorName;
    private String programName;

    public Report(int classID, String courseName, String instructorSSN, String instructorName, String programName) {
        this.classID = classID;
        this.courseName = courseName;
        this.instructorSSN = instructorSSN;
        this.instructorName = instructorName;
        this.programName = programName;
    }

    public Report(int classID, String courseName, String instructorSSN, String instructorName) {
        this.classID = classID;
        this.courseName = courseName;
        this.instructorSSN = instructorSSN;
        this.instructorName = instructorName;
    }

    public Report(int classID, String courseName, String instructorName) {
        this.classID = classID;
        this.courseName = courseName;
        this.instructorName = instructorName;
    }

    public int getClassID() {
        return classID;
    }

    public String getCourseName() {
        return courseName;
    }

    public String getInstructorSSN() {
        return instructorSSN;
    }

    public String getInstructorName() {
        return instructorName;
    }

    public String getProgramName() {
        return programName;
    }
}
