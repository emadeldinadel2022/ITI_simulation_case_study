package iti.data_access_layer;

import iti.models.Instructor;
import iti.models.User;
import iti.models.Supervisor;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SupervisorDAO extends InstructorDAO{
    @Override
    public User findByUserEmail(String userEmail) {
        String query = "SELECT instructor_ssn, instructor_name, instructor_email, phone, " +
                "address, works_for, track_supervisor " +
                "FROM instructor_view " +
                "WHERE instructor_email = ?" +
                "AND track_supervisor IS NOT NULL";
        try (PreparedStatement statement = getDAOInstance().getConnection().prepareStatement(query)) {
            statement.setString(1, userEmail);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                String instructorID = resultSet.getString("instructor_ssn");
                String instructorName = resultSet.getString("instructor_name");
                String instructorEmail = resultSet.getString("instructor_email");
                String phoneNumber = resultSet.getString("phone");
                String address = resultSet.getString("address");
                int works_for = resultSet.getInt("works_for");
                int supervisor_of = resultSet.getInt("track_supervisor");

                return new Supervisor(instructorID, instructorName, instructorEmail, phoneNumber, address, works_for, supervisor_of);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
