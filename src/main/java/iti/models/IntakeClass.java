package iti.models;

import java.sql.Date;

public class IntakeClass {
    private String studentSSN;
    private String studentName;
    private  int classID;
    private String classType;
    private String startDate;
    private String endDate;
    private double projectPercentage;
    private double testPercentage;
    private  double assignmentPercentage;
    private String instructorSSN;
    private int courseCode;
    private String intakeID;
    private String instructorName;

    private String courseName;

    public IntakeClass(String studentSSN, String studentName, int classID, String classType,
                       String startDate, String endDate, double projectPercentage, double testPercentage,
                            double assignmentPercentage, String instructorSSN, int courseCode,
                            String intakeID, String instructorName, String courseName) {
        this.studentSSN = studentSSN;
        this.studentName = studentName;
        this.classID = classID;
        this.classType = classType;
        this.startDate = startDate;
        this.endDate = endDate;
        this.projectPercentage = projectPercentage;
        this.testPercentage = testPercentage;
        this.assignmentPercentage = assignmentPercentage;
        this.instructorSSN = instructorSSN;
        this.courseCode = courseCode;
        this.intakeID = intakeID;
        this.instructorName = instructorName;
        this.courseName = courseName;
    }

    public IntakeClass(int classID, String classType, String startDate, String endDate, double projectPercentage, double testPercentage, double assignmentPercentage, String instructorSSN, int courseCode, String intakeID, String instructorName, String courseName) {
        this.classID = classID;
        this.classType = classType;
        this.startDate = startDate;
        this.endDate = endDate;
        this.projectPercentage = projectPercentage;
        this.testPercentage = testPercentage;
        this.assignmentPercentage = assignmentPercentage;
        this.instructorSSN = instructorSSN;
        this.courseCode = courseCode;
        this.intakeID = intakeID;
        this.instructorName = instructorName;
        this.courseName = courseName;
    }

    public String getStudentSSN() {
        return studentSSN;
    }

    public String getStudentName() {
        return studentName;
    }

    public int getClassID() {
        return classID;
    }

    public String getClassType() {
        return classType;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public double getProjectPercentage() {
        return projectPercentage;
    }

    public double getTestPercentage() {
        return testPercentage;
    }

    public double getAssignmentPercentage() {
        return assignmentPercentage;
    }

    public String getInstructorSSN() {
        return instructorSSN;
    }

    public int getCourseCode() {
        return courseCode;
    }

    public String getIntakeID() {
        return intakeID;
    }

    public String getCourseName() {
        return courseName;
    }

    public String getInstructorName() {
        return instructorName;
    }
}
