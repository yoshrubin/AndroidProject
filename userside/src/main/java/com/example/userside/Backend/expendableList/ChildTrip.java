package com.example.userside.Backend.expendableList;

import java.util.Date;

//this is a class that defines what will be contained in the child of the Expendable list of trips

public class ChildTrip {
    private String startDate;
    private String endDate;
    private String agency;
    private float price;


    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getAgency() {
        return agency;
    }

    public void setAgency(String agency) {
        this.agency = agency;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
}
