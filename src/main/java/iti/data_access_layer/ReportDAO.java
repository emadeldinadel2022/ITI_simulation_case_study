package iti.data_access_layer;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import iti.models.FinalStudentReport;
import iti.models.InstructorCourseReport;

public class ReportDAO {

    private DAO daoInstance;

    public ReportDAO() {
        daoInstance = DAO.initiaDAO();
    }

    public ArrayList<FinalStudentReport> getStudentReport(String studentSSN) {
        ArrayList<FinalStudentReport> reports = new ArrayList<>();
        String query = "SELECT * FROM student_report WHERE student_ssn = ?";

        try {
            PreparedStatement statement = daoInstance.getConnection().prepareStatement(query);
            statement.setString(1, studentSSN);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int classID = resultSet.getInt("class_id");
                String courseName = resultSet.getString("course_name");
                String instructorName = resultSet.getString("instructor_name");
                double finalClassGrade = resultSet.getDouble("final_grade");
                double testClassGrade = resultSet.getDouble("test_grade");
                double projectClassGrade = resultSet.getDouble("project_grade");
                double assignmentsClassScore = resultSet.getDouble("assignment_grade");
                double attendancePercentage = resultSet.getDouble("attendance_percentage");

                FinalStudentReport report = new FinalStudentReport(classID, courseName, instructorName
                        , studentSSN, finalClassGrade, testClassGrade
                        , projectClassGrade, assignmentsClassScore, attendancePercentage);
                reports.add(report);
            }

            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return reports;
    }

    public ArrayList<FinalStudentReport> getInstructorStudentsReport(String instructorSSNInput) {
        ArrayList<FinalStudentReport> reports = new ArrayList<>();
        String query = "SELECT class_id, course_name, instructor_ssn, instructor_name" +
                        ", program_name, student_ssn, student_name, final_grade " +
                        "FROM student_instructor_fg_report WHERE instructor_ssn = ?";
        try {
            PreparedStatement statement = daoInstance.getConnection().prepareStatement(query);
            statement.setString(1, instructorSSNInput);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int classID = resultSet.getInt("class_id");
                String courseName = resultSet.getString("course_name");
                String instructorSSN = resultSet.getString("instructor_ssn");
                String instructorName = resultSet.getString("instructor_name");
                String programName = resultSet.getString("program_name");
                String studentSSN = resultSet.getString("student_ssn");
                String studentName = resultSet.getString("student_name");
                double finalGrade = resultSet.getDouble("final_grade");

                FinalStudentReport report = new FinalStudentReport(classID, courseName, instructorSSN, instructorName, programName, studentSSN, studentName, finalGrade);
                reports.add(report);
            }

            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return reports;
    }

    public ArrayList<InstructorCourseReport> getInstructorCourseAvgGradesReport(String instructorSSNInput) {
        ArrayList<InstructorCourseReport> reports = new ArrayList<>();
        String query = "SELECT class_id, course_name, instructor_ssn, instructor_name, program_name, avg_grades FROM avg_grades_per_instructor WHERE instructor_ssn = ?";

        try {
            PreparedStatement statement = daoInstance.getConnection().prepareStatement(query);
            statement.setString(1, instructorSSNInput);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int classID = resultSet.getInt("class_id");
                String courseName = resultSet.getString("course_name");
                String instructorSSN = resultSet.getString("instructor_ssn");
                String instructorName = resultSet.getString("instructor_name");
                String programName = resultSet.getString("program_name");
                double avgCourseGrade = resultSet.getDouble("avg_grades");

                InstructorCourseReport report = new InstructorCourseReport(classID, courseName, instructorSSN, instructorName, programName, avgCourseGrade);
                reports.add(report);
            }

            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return reports;
    }

}
