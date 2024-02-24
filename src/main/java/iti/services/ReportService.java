package iti.services;

import iti.data_access_layer.ReportDAO;
import iti.models.FinalStudentReport;
import iti.models.InstructorCourseReport;

import java.util.ArrayList;

public class ReportService {
    private ReportDAO reportDAO;

    public ReportService(){
        reportDAO = new ReportDAO();
    }

    public ArrayList<FinalStudentReport> fetchStudentReport(String studentSSN){
        return reportDAO.getStudentReport(studentSSN);
    }

    public ArrayList<FinalStudentReport> fetchInstructorStudentReport(String instructorSSN){
        return reportDAO.getInstructorStudentsReport(instructorSSN);
    }

    public ArrayList<InstructorCourseReport> fetchInstructorCourseAvgGradesReport(String instructorSSN){
        return reportDAO.getInstructorCourseAvgGradesReport(instructorSSN);
    }
}
