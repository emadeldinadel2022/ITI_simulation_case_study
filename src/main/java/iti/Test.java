package iti;

import iti.data_access_layer.InstructorDAO;
import iti.data_access_layer.TrackProgramDAO;
import iti.models.Instructor;
import iti.models.Role;
import iti.models.*;
import iti.models.Supervisor;
import iti.services.LoginService;

public class Test {

    public static void main(String []args){
        InstructorDAO instructorDAO = new InstructorDAO();
        instructorDAO.addQualification(3003,"12345678901234");
    }
}
