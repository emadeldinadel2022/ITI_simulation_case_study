package iti.models;

import java.util.ArrayList;
import java.util.List;

public class Instructor extends User {
    private List<Qualification> instructorQualifications;
    private List<InstructorCourseExperience> experiencedCourses;

    public Instructor(String userID, String userName, String userEmail, String userPhoneNumber, String userAddress, int trackID) {
        super(userID, userName, userEmail, userPhoneNumber, userAddress, trackID);
        this.instructorQualifications = new ArrayList<>();
        this.experiencedCourses = new ArrayList<>();
    }

    public List<InstructorCourseExperience> getExperiencedCourses() {
        return experiencedCourses;
    }

    public void setExperiencedCourses(List<InstructorCourseExperience> experiencedCourses) {
        this.experiencedCourses = experiencedCourses;
    }

    public List<Qualification> getInstructorQualifications() {
        return instructorQualifications;
    }

    public void setInstructorQualifications(List<Qualification> instructorQualifications) {
        this.instructorQualifications = instructorQualifications;
    }
}
