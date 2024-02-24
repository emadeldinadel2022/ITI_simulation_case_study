package iti.data_access_layer;

import iti.models.Program;
import iti.models.Track;
import java.sql.PreparedStatement;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TrackProgramDAO {
    private DAO daoInstance;

    public TrackProgramDAO(){
        daoInstance = DAO.initiaDAO();
    }

    public Track getTrackProgram(int trackId, int programId) {
        Track result = null;
        String query = "SELECT * FROM track_program_view WHERE track_id = ? AND program_id = ?";
        try (PreparedStatement statement = daoInstance.getConnection().prepareStatement(query)) {
            statement.setInt(1, trackId);
            statement.setInt(2, programId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                result = new Track(resultSet.getInt("track_id"), resultSet.getString("track_name")
                        , resultSet.getString("track_description"), resultSet.getDate("establishment_date")
                        , resultSet.getString("business_field"), resultSet.getString("instructor_name"));

                Program program = new Program(resultSet.getInt("program_id"), resultSet.getString("program_name")
                        , resultSet.getString("program_Description"), resultSet.getDate("program_establishment_date")
                        , resultSet.getInt("track_id"), resultSet.getString("program_category"));

                result.getTrackPrograms().add(program);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

}
