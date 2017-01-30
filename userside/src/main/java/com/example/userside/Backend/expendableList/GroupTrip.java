package com.example.userside.Backend.expendableList;

import java.util.ArrayList;

/**
 * Created by Aviv on 25/01/2017.
 */

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
