package iti.models;

import java.sql.Date;

public abstract class BaseEntity {
    private int id;
    private String name;
    private String description;
    private Date establismentDate;

    public BaseEntity(int id) {
        this.id = id;
    }

    public BaseEntity(int id, String name, String description, Date establismentDate) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.establismentDate = establismentDate;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Date getEstablismentDate() {
        return establismentDate;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setEstablismentDate(Date establismentDate) {
        this.establismentDate = establismentDate;
    }
}
