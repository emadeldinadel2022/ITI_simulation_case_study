package iti.models;

import java.util.ArrayList;

//                int supervisor_of = resultSet.getInt("track_supervisor");
public class Supervisor extends Instructor{
    private int supervisor_of;

    public int getSupervisor_of() {
        return supervisor_of;
    }

    public Supervisor(String userID, String userName, String userEmail, String userPhoneNumber, String userAddress, int trackID, int supervisor_of) {
        super(userID, userName, userEmail, userPhoneNumber, userAddress, trackID);
        this.supervisor_of = supervisor_of;
    }
}
