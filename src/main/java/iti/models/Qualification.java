package iti.models;

public class Qualification {
    private int qualificationID;
    private String qualificationName;

    public Qualification(int qualificationID, String qualificationName) {
        this.qualificationID = qualificationID;
        this.qualificationName = qualificationName;
    }

    public int getQualificationID() {
        return qualificationID;
    }

    public String getQualificationName() {
        return qualificationName;
    }
}
