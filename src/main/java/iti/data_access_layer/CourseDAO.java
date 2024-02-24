package iti.data_access_layer;

import iti.models.Course;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProgramCourseDAO implements CourseDAO{
    private DAO daoInstance;

    public  ProgramCourseDAO(){
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
}
