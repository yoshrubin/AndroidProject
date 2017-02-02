package com.example.userside.Backend.Entitites;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by yoshi on 11/20/16.
 */

public class Action {
    public enum Attraction {HOTEL,TRAVEL_AGENCY,ENTERTAINMENT,FLIGHTS}

    String myFormat = "dd/MM/yy"; //In which you need put here
    SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

    private Attraction attraction;
    private String country;
    private Date startDate;
    private Date endDate;
    private double price;
    private String description;
    private int IDN;
    private String user;

    public Action(Attraction attraction, String country, Date startDate, Date endDate, double price, String description, int IDN, String user) {
        this.attraction = attraction;
        this.country = country;
        this.startDate = startDate;
        this.endDate = endDate;
        this.price = price;
        this.description = description;
        this.IDN = IDN;
        this.user = user;

    }

    public static String[] getColumns(){
        return new String[]{"attraction", "country", "startDate", "endDate", "price", "description", "IDN", "user"};
    }

    public Attraction getAttraction() {
        return attraction;
    }

    public void setAttraction(Attraction attraction) {
        this.attraction = attraction;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Date  getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String activity) {
        this.description = activity;
    }

    public int getIDN() {
        return IDN;
    }

    public void setIDN(int IDN) {
        this.IDN = IDN;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getValue(String Col) throws Exception {
        switch (Col){
            case "attraction":
                return getAttraction().toString();
            case "country":
                return getCountry();
            case "startDate":
                return sdf.format(getStartDate());
            case "endDate":
                return sdf.format(getEndDate());
            case "price":
                return Double.toString(getPrice());
            case "description":
                return getDescription();
            case "IDN":
                return Integer.toString(getIDN());
            case "user":
                return getUser();
            default:
                throw new Exception("Column doesn't Exist");
        }
    }

    @Override
    public String toString() {
        return "Action{" +
                "attraction=" + attraction +
                ", country='" + country + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", price=" + price +
                ", description='" + description + '\'' +
                ", IDN=" + IDN +
                ", user=" + user +
                '}';
    }
}
