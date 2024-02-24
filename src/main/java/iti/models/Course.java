package iti.models;

public class Course {
    private int courseCode;
    private String courseName;
    private int courseCreditHours;
    private String courseDescription;
    private String courseSyllabus;

    public Course(int courseCode, String courseName, int courseCreditHours, String courseDescription, String courseSyllabus) {
        this.courseCode = courseCode;
        this.courseName = courseName;
        this.courseCreditHours = courseCreditHours;
        this.courseDescription = courseDescription;
        this.courseSyllabus = courseSyllabus;
    }

    public Course(int courseCode, String courseName) {
        this.courseCode = courseCode;
        this.courseName = courseName;
    }

    public int getCourseCode() {
        return courseCode;
    }

    public String getCourseName() {
        return courseName;
    }

    public int getCourseCreditHours() {
        return courseCreditHours;
    }

    public String getCourseDescription() {
        return courseDescription;
    }

    public String getCourseSyllabus() {
        return courseSyllabus;
    }
}
