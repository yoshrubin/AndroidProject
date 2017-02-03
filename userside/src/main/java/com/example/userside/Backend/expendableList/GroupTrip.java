package com.example.userside.Backend.expendableList;

import java.util.ArrayList;

//this is a class that defines what will be contained in the Group of the Expendable list of trips


public class GroupTrip {
    private String countryName;
    private ArrayList<ChildTrip> tripDetails;

    public String getcountryName() {
        return this.countryName;
    }

    public void setCountryName(String cName){
        this.countryName=cName;
    }
    public ArrayList<ChildTrip> gettripDetails() {
        return tripDetails;
    }

    public void settripDetails(ArrayList<ChildTrip> tDetails){
        this.tripDetails=tDetails;
    }
}
