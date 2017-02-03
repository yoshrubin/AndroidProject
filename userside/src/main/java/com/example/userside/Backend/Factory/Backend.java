package com.example.userside.Backend.Factory;

import com.example.userside.Backend.Entitites.Action;
import com.example.userside.Backend.Entitites.Business;

import java.text.ParseException;
import java.util.ArrayList;

//our local lists which used in the second app
public interface Backend {
    ArrayList<Business> getBusinessList();
    ArrayList<Action> getAttractionList() throws ParseException;
    ArrayList<Business> getBusinessList(String Country);
    ArrayList<Action> getAttractionList(Business business) throws ParseException;
    void setUpDatabase();
}
