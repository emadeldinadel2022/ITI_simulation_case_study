package iti.models;

public class FinalStudentReport extends StudentReport{
    private double finalClassGrade;
    private double testClassGarde;
    private double projectClassGrade;
    private double assignmentsClassScore;
    private double attendancePercentage;

    public FinalStudentReport(int classID, String courseName
            , String instructorName,  String studentSSN
            , double finalClassGrade, double testClassGarde, double projectClassGrade
            , double assignmentsClassScore, double attendancePercentage) {
        super(classID, courseName, instructorName, studentSSN);
        this.finalClassGrade = finalClassGrade;
        this.testClassGarde = testClassGarde;
        this.projectClassGrade = projectClassGrade;
        this.assignmentsClassScore = assignmentsClassScore;
        this.attendancePercentage = attendancePercentage;
    }

    public FinalStudentReport(int classID, String courseName, String instructorName, String instructorSSN,String programName, String studentSSN, String studentName, double finalClassGrade) {
        super(classID, courseName, instructorSSN,instructorName, programName, studentSSN, studentName);
        this.finalClassGrade = finalClassGrade;
    }

    public double getFinalClassGrade() {
        return finalClassGrade;
    }

    public double getTestClassGarde() {
        return testClassGarde;
    }

    public double getProjectClassGrade() {
        return projectClassGrade;
    }

    public double getAssignmentsClassScore() {
        return assignmentsClassScore;
    }

    public double getAttendancePercentage() {
        return attendancePercentage;
    }


}
