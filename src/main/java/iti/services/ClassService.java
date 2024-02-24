package iti.services;

import iti.data_access_layer.IntakeClassDAO;
import iti.models.Assignment;
import iti.models.IntakeClass;
import iti.models.Schedule;
import iti.models.Student;

import java.util.ArrayList;

public class ClassService {

    private IntakeClassDAO intakeClassDAO;

    public ClassService(){
        intakeClassDAO = new IntakeClassDAO();
    }

    public ArrayList<IntakeClass> fetchStudentClasses(String studentSSN){
        return intakeClassDAO.getStudentClasses(studentSSN);
    }

    public ArrayList<IntakeClass> fetchInstructorClasses(String instructorSSN){
        return intakeClassDAO.getInstructorClasses(instructorSSN);
    }

    public ArrayList<Student> fetchStudentClass(int classID){
        return intakeClassDAO.getClassStudents(classID);
    }
    public ArrayList<Schedule> fetchClassTimeTable(int classID){
        return intakeClassDAO.getClassTimeTable(classID);
    }

    public ArrayList<Assignment> fetchClassAssignments(int classID){
        return  intakeClassDAO.getClassAssignments(classID);
    }
}
