package iti.models;

public class InstructorCourseReport extends Report {
    private double avgCourseGrade;

    public InstructorCourseReport(int classID, String courseName, String instructorSSN, String instructorName, String programName, double avgCourseGrade) {
        super(classID, courseName, instructorSSN, instructorName, programName);
        this.avgCourseGrade = avgCourseGrade;
    }

    public double getAvgCourseGrade() {
        return avgCourseGrade;
    }
}
