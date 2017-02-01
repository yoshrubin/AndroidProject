package com.example.userside.Backend.expendableList;

import java.util.ArrayList;

/**
 * Created by Aviv on 01/02/2017.
 */

public class GroupAgency {
    private String agencyName;
    private ArrayList<ChildAgency> agencyDetails;

    public String getAgencyName() {
        return this.agencyName;
    }

    public void setAgencyNameName(String AName){
        this.agencyName=AName;
    }

    public ArrayList<ChildAgency> getAgencyDetails(){
        return agencyDetails;
    }

    public void setAgencyDetails(ArrayList<ChildAgency> ADetails){
        this.agencyDetails=ADetails;
    }

    /*public ArrayList<ChildTrip> gettripDetails() {
        return tripDetails;
    }

    public void settripDetails(ArrayList<ChildTrip> tDetails){
        this.tripDetails=tDetails;
    }
    */
}
