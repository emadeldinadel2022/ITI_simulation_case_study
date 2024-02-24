package iti.models;

public class InstructorCourseExperience {
    private String instructorSSN;
    private int courseCode;
    private String courseName;

    private int yearsOfExperience;

    public InstructorCourseExperience(String instructorSSN, int courseCode, String courseName, int yearsOfExperience) {
        this.instructorSSN = instructorSSN;
        this.courseCode = courseCode;
        this.courseName = courseName;
        this.yearsOfExperience = yearsOfExperience;
    }

    public InstructorCourseExperience(int courseCode, String courseName, int yearsOfExperience) {
        this.courseCode = courseCode;
        this.courseName = courseName;
        this.yearsOfExperience = yearsOfExperience;
    }

    public String getInstructorSSN() {
        return instructorSSN;
    }

    public int getCourseCode() {
        return courseCode;
    }

    public String getCourseName() {
        return courseName;
    }

    public int getYearsOfExperience() {
        return yearsOfExperience;
    }

    public void setYearsOfExperience(int yearsOfExperience) {
        this.yearsOfExperience = yearsOfExperience;
    }
}
