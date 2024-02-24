package iti.data_access_layer;
import iti.models.Student;
import iti.models.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StudentDAO implements UserDAO{
    private DAO daoInstance;

    public StudentDAO(){
        daoInstance = DAO.initiaDAO();
    }

    @Override
    public boolean checkLogin(String userEmail, String userPassword) {
        String query = "SELECT COUNT(*) " +
                        "FROM student " +
                        "WHERE student_email = ? and student_password = ?";
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
        String query = "SELECT student_ssn, student_name, student_email, phone_number, " +
                        "address, intake_id, program_id, track_id " +
                        "FROM student_view " +
                        "WHERE student_email = ?";
        try (PreparedStatement statement = daoInstance.getConnection().prepareStatement(query)) {
            statement.setString(1, userEmail);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                String studentID = resultSet.getString("student_ssn");
                String studentName = resultSet.getString("student_name");
                String studentEmail = resultSet.getString("student_email");
                String phoneNumber = resultSet.getString("phone_number");
                String address = resultSet.getString("address");
                String intakeId = resultSet.getString("intake_id");
                int programId = resultSet.getInt("program_id");
                int trackId = resultSet.getInt("track_id");

                return new Student(studentID, studentName, studentEmail, phoneNumber, address, trackId, intakeId, programId);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}
