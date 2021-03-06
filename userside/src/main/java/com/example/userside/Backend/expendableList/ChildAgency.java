package com.example.userside.Backend.expendableList;

//this is a class that defines what will be contained in the child of the Expendable list of agencies
public class ChildAgency {
    private String agencyName;
    private String agencyLocation;
    private String agencyWebsite;
    private String agencyMail;
    private String agencyPhone;

    public String getAgencyPhone() {
        return agencyPhone;
    }

    public void setAgencyPhone(String agencyPhone) {
        this.agencyPhone = agencyPhone;
    }

    public String getAgencyName() {
        return agencyName;
    }

    public void setAgencyName(String agencyName) {
        this.agencyName = agencyName;
    }

    public String getAgencyLocation() {
        return agencyLocation;
    }

    public void setAgencyLocation(String agencyLocation) {
        this.agencyLocation = agencyLocation;
    }

    public String getAgencyWebsite() {
        return agencyWebsite;
    }

    public void setAgencyWebsite(String agencyWebsite) {
        this.agencyWebsite = agencyWebsite;
    }

    public String getAgencyMail() {
        return agencyMail;
    }

    public void setAgencyMail(String agencyMail) {
        this.agencyMail = agencyMail;
    }
}
