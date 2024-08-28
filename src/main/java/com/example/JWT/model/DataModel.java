package com.example.JWT.model;

import jakarta.persistence.Id;

public class DataModel {

    @Id
    private int dataID;
    private String information;

    public int getDataID() {
        return dataID;
    }

    public void setDataID(int dataID) {
        this.dataID = dataID;
    }

    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
    }
}
