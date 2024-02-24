package iti.services;

import iti.data_access_layer.TrackProgramDAO;
import iti.models.Track;

public class TrackService {
    private TrackProgramDAO trackProgramDAO;

    public TrackService(){
        this.trackProgramDAO = new TrackProgramDAO();
    }

    public Track getStudentTrackView(int trackID, int programID){
        return trackProgramDAO.getTrackProgram(trackID, programID);
    }
}
