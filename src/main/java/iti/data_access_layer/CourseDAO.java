package iti.data_access_layer;

import iti.models.Course;
import iti.models.InstructorCourseExperience;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CourseDAO {
    private DAO daoInstance;

    public CourseDAO(){
        daoInstance = DAO.initiaDAO();
    }

    public List<Course> getCoursesByProgramId(int programId) {
        List<Course> courses = new ArrayList<>();
        String query = "SELECT * FROM course_program_view WHERE program_id = ?";

        try (PreparedStatement statement = daoInstance.getConnection().prepareStatement(query)) {
            statement.setInt(1, programId);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Course course = new Course(resultSet.getInt("course_code"), resultSet.getString("course_name")
                                , resultSet.getInt("credit_hours"), resultSet.getString("course_description")
                                , resultSet.getString("course_syllabus"));
                courses.add(course);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return courses;
    }

    public List<InstructorCourseExperience> getInstructorExperiencedCourses(String instructorSSN){
        List<InstructorCourseExperience> courses = new ArrayList<>();
        String query = "SELECT  *" +
                        "FROM course_instructor_view " +
                        "WHERE instructor_ssn = ?";

        try (PreparedStatement statement = daoInstance.getConnection().prepareStatement(query)) {
            statement.setString(1, instructorSSN);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                InstructorCourseExperience experience = new InstructorCourseExperience(resultSet.getString("instructor_ssn"), resultSet.getInt("course_code")
                                            , resultSet.getString("course_name"), resultSet.getInt("years_of_experience"));
                courses.add(experience);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
      return courses;
    }

    public List<Course> getSystemCourses(String instructorSSN){
        List<Course> courses = new ArrayList<>();
        String query = "SELECT course_code, course_name " +
                "FROM course " +
                "WHERE course_code NOT IN (" +
                "SELECT course_code " +
                "FROM course_instructor_view " +
                "WHERE instructor_ssn = ?)";

        try (PreparedStatement statement = daoInstance.getConnection().prepareStatement(query)) {
            statement.setString(1, instructorSSN);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Course course = new Course(resultSet.getInt("course_code")
                        , resultSet.getString("course_name"));
                courses.add(course);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return courses;
    }
}
