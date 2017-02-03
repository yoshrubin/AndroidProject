package com.example.userside.Backend.expendableList;

import java.util.ArrayList;

//this is a class that defines what will be contained in the Group of the Expendable list of agencies


public class GroupAgency {
    private String agencyName;
    private ArrayList<ChildAgency> agencyDetails;

    public String getAgencyName() {
        return this.agencyName;
    }

    public void setAgencyName(String AName){
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
