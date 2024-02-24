package iti.services;

import iti.data_access_layer.InstructorDAO;
import iti.data_access_layer.QualificationDAO;
import iti.models.Qualification;

import java.util.List;

public class InstructorService {
    private QualificationDAO qualificationDAO;
    private InstructorDAO instructorDAO;

    public InstructorService(){
        this.qualificationDAO = new QualificationDAO();
        this.instructorDAO = new InstructorDAO();
    }

    public List<Qualification> getInstructorCertifications(String instructorSSN){
        return qualificationDAO.getQualificationsByInstructorSSN(instructorSSN);
    }

    public List<Qualification> getSystemCertifications(String instructorSSN){
        return qualificationDAO.getAllQualificationsNotAssociatedWithInstructor(instructorSSN);
    }

    public boolean addInstructorExpCourse(int courseCode, String instructorSSN, int yearsOfExp){
        return instructorDAO.addCourseExp(courseCode, instructorSSN, yearsOfExp);
    }

    public boolean addInstructorQualification(int qualificationID, String instructorSSN){
        return instructorDAO.addQualification(qualificationID,instructorSSN);
    }
}
