package com.example.userside.Backend.Factory;

import com.example.userside.Backend.Entitites.Action;
import com.example.userside.Backend.Entitites.Business;

import java.text.ParseException;
import java.util.ArrayList;

/**
 * Created by yoshi on 1/26/17.
 */

public interface Backend {
    ArrayList<Business> getBusinessList();
    ArrayList<Action> getAttractionList() throws ParseException;
    ArrayList<Business> getBusinessList(String Country);
    ArrayList<Action> getAttractionList(Business business) throws ParseException;
    void setUpDatabase();
}
