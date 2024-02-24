package iti.services;

import iti.data_access_layer.CourseDAO;
import iti.models.Course;
import iti.models.InstructorCourseExperience;

import java.util.List;

public class CourseService {
    private CourseDAO courseDAO;

    public CourseService(){
        this.courseDAO = new CourseDAO();
    }

    public List<Course> getStudentCourseListView(int programID){
        return courseDAO.getCoursesByProgramId(programID);
    }

    public List<InstructorCourseExperience> getInstructorExperiencedCourses(String instructorSSN){
        return courseDAO.getInstructorExperiencedCourses(instructorSSN);
    }

    public List<Course> getSystemCourse(String instructorSSN){
        return courseDAO.getSystemCourses(instructorSSN);
    }
}
