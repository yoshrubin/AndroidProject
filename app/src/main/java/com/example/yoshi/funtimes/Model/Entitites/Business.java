package com.example.yoshi.funtimes.Model.Entitites;

//this class defines Business Entity

public class Business {
    private int IDN;
    private String name;
    private String country;
    private String city;
    private String street;
    private int housenum;
    private String phoneNum;
    private String email;
    private String site;
    private String user;

    public Business(int IDN, String name, String country, String city, String street, int housenum, String phoneNum, String email, String site, String user) {
        this.IDN = IDN;
        this.name = name;
        this.country = country;
        this.city = city;
        this.street = street;
        this.housenum = housenum;
        this.phoneNum = phoneNum;
        this.email = email;
        this.site = site;
        this.user = user;
    }

    public int getIDN() {
        return IDN;
    }

    public void setIDN(int IDN) {
        this.IDN = IDN;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public int getHousenum() {
        return housenum;
    }

    public void setHousenum(int housenum) {
        this.housenum = housenum;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Business{" +
                "IDN=" + IDN +
                ", name='" + name + '\'' +
                ", country='" + country + '\'' +
                ", city='" + city + '\'' +
                ", street='" + street + '\'' +
                ", housenum=" + housenum +
                ", phoneNum=" + phoneNum +
                ", email='" + email + '\'' +
                ", site='" + site + '\'' +
                ", user='" + user + '\'' +
                '}';
    }
}

