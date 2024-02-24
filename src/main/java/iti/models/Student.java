package iti.models;

public class Student extends User{
    private int programID;
    private String intakeID;
    private String totalGrade;

    public int getProgramID() {
        return programID;
    }

    public String getIntakeID() {
        return intakeID;
    }

    public String getTotalGrade() {
        return totalGrade;
    }
    public Student(String userID,String userName, String userEmail, String userPhoneNumber, String userAddress, int trackID, String intake_id, int program_id) {
        super(userID, userName, userEmail, userPhoneNumber,  userAddress, trackID);
        this.intakeID = intake_id;
        this.programID = program_id;
    }


    public Student(String userID, String userName) {
        super(userID, userName);
    }
}
