package iti.data_access_layer;

import iti.models.*;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;


public class IntakeClassDAO {
    private DAO daoInstance;

    public IntakeClassDAO(){
        daoInstance = DAO.initiaDAO();
    }


    public ArrayList<IntakeClass> getStudentClasses(String studentSSNInput){
        ArrayList<IntakeClass> intakeClasses = new ArrayList<>();
        String query = "SELECT *" +
                "FROM class_student_view " +
                "WHERE STUDENT_SSN = ?";

        try (PreparedStatement statement = daoInstance.getConnection().prepareStatement(query)) {
            statement.setString(1, studentSSNInput);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    String studentSSN = resultSet.getString("STUDENT_SSN");
                    String studentName = resultSet.getString("STUDENT_NAME");
                    int classID = resultSet.getInt("CLASS_ID");
                    String classType = resultSet.getString("CLASS_TYPE");
                    String startDate = dateToString(resultSet.getDate("START_DATE"));
                    String endDate = dateToString(resultSet.getDate("END_DATE"));
                    double projectPercentage = resultSet.getDouble("PROJECT_PERCENTAGE");
                    double testPercentage = resultSet.getDouble("TEST_PERCENTAGE");
                    double assignmentPercentage = resultSet.getDouble("ASSIGNMENT_PERCENTAGE");
                    String instructorSSN = resultSet.getString("INSTRUCTOR_SSN");
                    int courseCode = resultSet.getInt("COURSE_CODE");
                    String intakeID = resultSet.getString("INTAKE_ID");
                    String instructorName = resultSet.getString("INSTRUCTOR_NAME");
                    String courseName = resultSet.getString("COURSE_NAME");

                    IntakeClass intakeClass = new IntakeClass(studentSSN, studentName, classID, classType,
                            startDate, endDate, projectPercentage,
                            testPercentage, assignmentPercentage,
                            instructorSSN, courseCode, intakeID,
                            instructorName, courseName);
                    intakeClasses.add(intakeClass);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return intakeClasses;
    }

    public ArrayList<IntakeClass> getInstructorClasses(String instructorSSN){
        ArrayList<IntakeClass> instructorClasses = new ArrayList<>();
        String query = "SELECT CLASS_ID, CLASS_TYPE, START_DATE, END_DATE, " +
                "PROJECT_PERCENTAGE, TEST_PERCENTAGE, ASSIGNMENT_PERCENTAGE, " +
                "INSTRUCTOR_SSN, COURSE_CODE, INTAKE_ID, INSTRUCTOR_NAME, COURSE_NAME " +
                "FROM class_view " +
                "WHERE INSTRUCTOR_SSN = ?";

        try (PreparedStatement statement = daoInstance.getConnection().prepareStatement(query)) {
            statement.setString(1, instructorSSN);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    int classID = resultSet.getInt("CLASS_ID");
                    String classType = resultSet.getString("CLASS_TYPE");
                    String startDate = dateToString(resultSet.getDate("START_DATE"));
                    String endDate = dateToString(resultSet.getDate("END_DATE"));
                    double projectPercentage = resultSet.getDouble("PROJECT_PERCENTAGE");
                    double testPercentage = resultSet.getDouble("TEST_PERCENTAGE");
                    double assignmentPercentage = resultSet.getDouble("ASSIGNMENT_PERCENTAGE");
                    String instructorSSNResult = resultSet.getString("INSTRUCTOR_SSN");
                    int courseCode = resultSet.getInt("COURSE_CODE");
                    String intakeID = resultSet.getString("INTAKE_ID");
                    String instructorName = resultSet.getString("INSTRUCTOR_NAME");
                    String courseName = resultSet.getString("COURSE_NAME");

                    IntakeClass intakeClass = new IntakeClass(classID, classType, startDate, endDate,
                            projectPercentage, testPercentage,
                            assignmentPercentage, instructorSSNResult,
                            courseCode, intakeID, instructorName,
                            courseName);
                    instructorClasses.add(intakeClass);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return instructorClasses;
    }


    public ArrayList<Student> getClassStudents(int classID) {
        ArrayList<Student> classStudents = new ArrayList<>();
        String query = "SELECT STUDENT_SSN, STUDENT_NAME FROM class_student_view WHERE CLASS_ID = ?";

        try (PreparedStatement statement = daoInstance.getConnection().prepareStatement(query)) {
            statement.setInt(1, classID);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    String studentSSN = resultSet.getString("STUDENT_SSN");
                    String studentName = resultSet.getString("STUDENT_NAME");

                    Student student = new Student(studentSSN, studentName);
                    classStudents.add(student);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return classStudents;
    }
    public ArrayList<Schedule> getClassTimeTable(int classID){
        ArrayList<Schedule> classTimeTable = new ArrayList<>();
        String query = "SELECT COURSE_NAME, SCHEDULE_ID, DAY_OF_WEEK, START_TIME, END_TIME FROM schedule_view WHERE CLASS_ID = ?";

        try (PreparedStatement statement = daoInstance.getConnection() .prepareStatement(query)) {
            statement.setInt(1, classID);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    String courseName = resultSet.getString("COURSE_NAME");
                    int scheduleID = resultSet.getInt("SCHEDULE_ID");
                    String dayOfWeek = resultSet.getString("DAY_OF_WEEK");
                    String startTime = timestampToString(resultSet.getTimestamp("START_TIME"));
                    String endTime = timestampToString(resultSet.getTimestamp("END_TIME"));

                    Schedule schedule = new Schedule(scheduleID, dayOfWeek, startTime, endTime, classID, courseName);
                    classTimeTable.add(schedule);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return classTimeTable;
    }

    public ArrayList<Assignment> getClassAssignments(int classID){
        ArrayList<Assignment> classAssignments = new ArrayList<>();
        String query = "SELECT ASSIGNMENT_ID, ASSIGNMENT_DESCRIPTION, ASSIGNMENT_TYPE, DEADLINE FROM assignment WHERE CLASS_ID = ?";

        try (PreparedStatement statement = daoInstance.getConnection().prepareStatement(query)) {
            statement.setInt(1, classID);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    int assignmentID = resultSet.getInt("ASSIGNMENT_ID");
                    String assignmentDescription = resultSet.getString("ASSIGNMENT_DESCRIPTION");
                    String assignmentType = resultSet.getString("ASSIGNMENT_TYPE");
                    String deadline = timestampToString(resultSet.getTimestamp("DEADLINE"));

                    Assignment assignment = new Assignment(assignmentID, assignmentDescription, assignmentType, deadline, classID);
                    classAssignments.add(assignment);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return classAssignments;
    }

    private String timestampToString(Timestamp timestamp) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(timestamp);
    }

    private String dateToString(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(date);
    }
}
