package iti.models;

import java.sql.Timestamp;

public class Schedule {
    private int scheduleID;
    private String dayOfWeek;
    private String startTime;
    private String endTime;
    private int classID;
    private String courseName;

    public Schedule(int scheduleID, String dayOfWeek, String startTime, String endTime, int classID, String courseName) {
        this.scheduleID = scheduleID;
        this.dayOfWeek = dayOfWeek;
        this.startTime = startTime;
        this.endTime = endTime;
        this.classID = classID;
        this.courseName = courseName;
    }

    // Getters and setters
    public int getScheduleID() {
        return scheduleID;
    }


    public String getDayOfWeek() {
        return dayOfWeek;
    }


    public String getStartTime() {
        return startTime;
    }


    public String getEndTime() {
        return endTime;
    }


    public String getCourseName() {
        return courseName;
    }

    public int getClassID() {
        return classID;
    }


}
