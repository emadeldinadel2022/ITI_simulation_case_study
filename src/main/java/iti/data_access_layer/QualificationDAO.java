package iti.data_access_layer;

import iti.models.Qualification;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;

public class QualificationDAO {

    private DAO daoInstance;

    public QualificationDAO(){
        daoInstance = DAO.initiaDAO();
    }

    public List<Qualification> getQualificationsByInstructorSSN(String instructorSSN) {
        List<Qualification> qualifications = new ArrayList<>();
        String query = "SELECT qualification_id, qualification_name " +
                "FROM instructor_qualification_view " +
                "WHERE instructor_ssn = ?";
        try (PreparedStatement statement = daoInstance.getConnection().prepareStatement(query)) {
            statement.setString(1, instructorSSN);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Qualification qualification = new Qualification(resultSet.getInt("qualification_id")
                        , resultSet.getString("qualification_name"));
                qualifications.add(qualification);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return qualifications;
    }

    public List<Qualification> getAllQualificationsNotAssociatedWithInstructor(String instructorSSN) {
        List<Qualification> qualifications = new ArrayList<>();
        String query = "SELECT DISTINCT qualification_id, qualification_name FROM instructor_qualification_view \n" +
                "WHERE qualification_id not in(\n" +
                "        SELECT qualification_id FROM instructor_qualification_view \n" +
                "        WHERE instructor_ssn = ?\n" +
                ")";
        try (PreparedStatement statement = daoInstance.getConnection().prepareStatement(query)) {
            statement.setString(1, instructorSSN);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Qualification qualification = new Qualification(resultSet.getInt("qualification_id")
                        , resultSet.getString("qualification_name"));
                qualifications.add(qualification);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return qualifications;
    }


}
