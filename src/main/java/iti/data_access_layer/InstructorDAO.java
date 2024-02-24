package iti.data_access_layer;

import iti.models.Instructor;
import iti.models.User;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class InstructorDAO implements UserDAO{

    private DAO daoInstance;

    public DAO getDAOInstance() {
        return daoInstance;
    }

    public InstructorDAO(){
        daoInstance = DAO.initiaDAO();
    }

    @Override
    public boolean checkLogin(String userEmail, String userPassword) {
        String query = "SELECT COUNT(*) " +
                        "FROM instructor " +
                        "WHERE instructor_email = ? and instructor_password = ?";
        try (PreparedStatement statement = daoInstance.getConnection().prepareStatement(query)) {
            statement.setString(1, userEmail);
            statement.setString(2, userPassword);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                int count = resultSet.getInt(1);
                return count > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public User findByUserEmail(String userEmail) {
        String query = "SELECT instructor_ssn, instructor_name, instructor_email, phone, " +
                "address, works_for " +
                "FROM instructor_view " +
                "WHERE instructor_email = ?";
        try (PreparedStatement statement = daoInstance.getConnection().prepareStatement(query)) {
            statement.setString(1, userEmail);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                String instructorID = resultSet.getString("instructor_ssn");
                String instructorName = resultSet.getString("instructor_name");
                String instructorEmail = resultSet.getString("instructor_email");
                String phoneNumber = resultSet.getString("phone");
                String address = resultSet.getString("address");
                int works_for = resultSet.getInt("works_for");

                return new Instructor(instructorID, instructorName, instructorEmail, phoneNumber, address, works_for);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean addCourseExp( int courseCode, String instructorSSN, int yearsOfExp){
        try (CallableStatement cstmt = daoInstance.getConnection().prepareCall("{? = call instructor_management_pkg.check_exp_course(?, ?, ?)}")) {
            cstmt.registerOutParameter(1, java.sql.Types.INTEGER);
            cstmt.setInt(2, courseCode);
            cstmt.setString(3, instructorSSN);
            cstmt.registerOutParameter(4, java.sql.Types.INTEGER);
            cstmt.executeUpdate();
            if(cstmt.getInt(1) == 0){
                insertInstructorCourseExp(courseCode, instructorSSN, yearsOfExp);
                return true;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    private void insertInstructorCourseExp(int courseCode, String instructorSSN, int yearOfExp){
        try (CallableStatement cstmt = daoInstance.getConnection().prepareCall("{call instructor_management_pkg.add_exp_course(?, ?, ?)}")) {
            cstmt.setInt(1, courseCode);
            cstmt.setString(2, instructorSSN);
            cstmt.setInt(3, yearOfExp);
            cstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean addQualification( int qualificationID, String instructorSSN){
        try (CallableStatement cstmt = daoInstance.getConnection().prepareCall("{? = call instructor_management_pkg.check_certification(?, ?, ?)}")) {
            cstmt.registerOutParameter(1, java.sql.Types.INTEGER);
            cstmt.setInt(2, qualificationID);
            cstmt.setString(3, instructorSSN);
            cstmt.registerOutParameter(4, java.sql.Types.INTEGER);
            cstmt.executeUpdate();
            if(cstmt.getInt(1) == 0){
                insertInstructorQualification(qualificationID, instructorSSN);
                return true;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    private void insertInstructorQualification(int qualificationID, String instructorSSN){
        try (CallableStatement cstmt = daoInstance.getConnection().prepareCall("{call instructor_management_pkg.add_qualification(?, ?)}")) {
            cstmt.setInt(1, qualificationID);
            cstmt.setString(2, instructorSSN);
            cstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


}
