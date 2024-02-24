package iti.models;

public abstract class StudentReport extends Report{
    private String studentSSN;
    private String studentName;

    public StudentReport(int classID, String courseName, String instructorSSN, String instructorName, String programName, String studentSSN, String studentName) {
        super(classID, courseName, instructorSSN, instructorName, programName);
        this.studentSSN = studentSSN;
        this.studentName = studentName;
    }

    public StudentReport(int classID, String courseName, String instructorName, String studentSSN, String studentName) {
        super(classID, courseName, instructorName);
        this.studentSSN = studentSSN;
        this.studentName = studentName;
    }

    public StudentReport(int classID, String courseName, String instructorName, String studentSSN) {
        super(classID, courseName, instructorName);
        this.studentSSN = studentSSN;
    }


    public String getStudentSSN() {
        return studentSSN;
    }

    public String getStudentName() {
        return studentName;
    }
}
