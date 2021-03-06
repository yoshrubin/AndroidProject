package com.example.userside.Backend.DB;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;

import com.example.userside.Backend.Entitites.Action;
import com.example.userside.Backend.Entitites.Business;
import com.example.userside.Backend.Factory.Backend;
import com.example.userside.Model.AgenciesFragment;
import com.example.userside.Model.TripsFragment;
import com.example.userside.Model.secondAppActivity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;

//this is a DB for our second app and it acts as a local DB.

public class listDB implements Backend{
    private Cursor busMatrix;
    private Cursor actMatrix;
    public static ArrayList<Business> businessList = new ArrayList<>();
    public static ArrayList<Action> actionList = new ArrayList<>();
    public ArrayList<Business> tempBusinessList = new ArrayList<>();
    public ArrayList<Action> tempActionList = new ArrayList<>();
    private final ArrayList<Action> actionBusinessList = new ArrayList<>();
    private final ArrayList<Business> businessCountryList = new ArrayList<>();

    private static final Uri actUri = Uri.parse("content://com.example.yoshi.funtimes.Model.DataSources.ContentProvide/actions");
    private static final Uri busUri = Uri.parse("content://com.example.yoshi.funtimes.Model.DataSources.ContentProvide/business");

    //ContentProviderClient actResolver = secondAppActivity.context.getContentResolver().acquireContentProviderClient(actUri);
    //ContentProviderClient busResolver = secondAppActivity.context.getContentResolver().acquireContentProviderClient(busUri);

    private final ContentResolver actResolver = secondAppActivity.context.getContentResolver();
    private final ContentResolver busResolver = actResolver;
    public listDB(){

    }

    @Override
    public ArrayList<Business> getBusinessList() {
        busMatrix = busResolver.query(busUri,null,null,null,null,null);
        busMatrix.moveToFirst();
        do {
            /*
                get in value then load to list from cursor
                 */
            int IDN = busMatrix.getColumnIndex("IDN");
            int name = busMatrix.getColumnIndex("name");
            int country = busMatrix.getColumnIndex("country");
            int city = busMatrix.getColumnIndex("city");
            int street = busMatrix.getColumnIndex("street");
            int housenum = busMatrix.getColumnIndex("housenum");
            int phoneNum = busMatrix.getColumnIndex("phoneNum");
            int email = busMatrix.getColumnIndex("email");
            int site = busMatrix.getColumnIndex("site");
            int user = busMatrix.getColumnIndex("user");

            tempBusinessList.add(new Business(busMatrix.getInt(IDN), busMatrix.getString(name), busMatrix.getString(country),
                    busMatrix.getString(city), busMatrix.getString(street), busMatrix.getInt(housenum),
                    busMatrix.getString(phoneNum),busMatrix.getString(email), busMatrix.getString(site), busMatrix.getString(user)));
        } while (busMatrix.moveToNext());
        return tempBusinessList;
    }

    @Override
    public ArrayList<Action> getAttractionList() throws ParseException {
        actMatrix = actResolver.query(actUri,null,null,null,null,null);
        actMatrix.moveToFirst();
        do {
                /*
                get in value then load to list from cursor
                 */
                String myFormat = "dd/MM/yy"; //In which you need put here
                SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
                int attraction = actMatrix.getColumnIndex("attraction");
                int country = actMatrix.getColumnIndex("country");
                int startdate = actMatrix.getColumnIndex("startdate");
                int enddate = actMatrix.getColumnIndex("enddate");
                int price = actMatrix.getColumnIndex("price");
                int description = actMatrix.getColumnIndex("description");
                int IDN = actMatrix.getColumnIndex("IDN");
                int user = actMatrix.getColumnIndex("user");
                // String type, String country_Name, String start_Date, String end_Date, String cost, String description, String business_ID
                tempActionList.add(new Action(Action.Attraction.valueOf(actMatrix.getString(attraction)), actMatrix.getString(country),
                        sdf.parse(actMatrix.getString(startdate)), sdf.parse(actMatrix.getString(enddate)), actMatrix.getDouble(price),
                        actMatrix.getString(description), actMatrix.getInt(IDN), actMatrix.getString(user)));
            } while (actMatrix.moveToNext());
            return tempActionList;
    }

    @Override
    public ArrayList<Business> getBusinessList(String Country) {//we never ended up using these lists
        busMatrix = busResolver.query(busUri,null,null,null,null,null);
        busMatrix.moveToFirst();
        while(!busMatrix.isAfterLast()){
            /*
                get in value then load to list from cursor
                 */
            if(busMatrix.getString(2) == Country){
                //adding all my businesses to the selector
                int IDN = busMatrix.getColumnIndex("IDN");
                int name = busMatrix.getColumnIndex("name");
                int country = busMatrix.getColumnIndex("country");
                int city = busMatrix.getColumnIndex("city");
                int street = busMatrix.getColumnIndex("street");
                int housenum = busMatrix.getColumnIndex("housenum");
                int phoneNum = busMatrix.getColumnIndex("phoneNum");
                int email = busMatrix.getColumnIndex("email");
                int site = busMatrix.getColumnIndex("site");
                int user = busMatrix.getColumnIndex("user");

                businessCountryList.add(new Business(busMatrix.getInt(IDN), busMatrix.getString(name), busMatrix.getString(country),
                        busMatrix.getString(city), busMatrix.getString(street), busMatrix.getInt(housenum),
                        busMatrix.getString(phoneNum),busMatrix.getString(email), busMatrix.getString(site), busMatrix.getString(user)));
            }
            busMatrix.moveToNext();
        }
        return businessCountryList;
    }

    @Override
    public ArrayList<Action> getAttractionList(Business business) throws ParseException {
        actMatrix = actResolver.query(actUri,null,null,null,null);
        actMatrix.moveToFirst();
        while(!actMatrix.isAfterLast()){
            if(business.getUser() == actMatrix.getString(7)) {
                /*
                get in value then load to list from cursor
                 */
                String myFormat = "dd/MM/yy"; //In which you need put here
                SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
                int attraction = actMatrix.getColumnIndex("attraction");
                int country = actMatrix.getColumnIndex("country");
                int startdate = actMatrix.getColumnIndex("startdate");
                int enddate = actMatrix.getColumnIndex("enddate");
                int price = actMatrix.getColumnIndex("price");
                int description = actMatrix.getColumnIndex("description");
                int IDN = actMatrix.getColumnIndex("IDN");
                int user = actMatrix.getColumnIndex("user");

                actionBusinessList.add(new Action(Action.Attraction.valueOf(actMatrix.getString(attraction)), actMatrix.getString(country),
                        sdf.parse(actMatrix.getString(startdate)), sdf.parse(actMatrix.getString(enddate)), actMatrix.getDouble(price),
                        actMatrix.getString(description), actMatrix.getInt(IDN), actMatrix.getString(user)));
            }
            actMatrix.moveToNext();
        }
        return actionBusinessList;
    }

    @Override
    public void setUpDatabase() throws ParseException {
        businessList.clear();
        actionList.clear();
        businessList = getBusinessList();
        try {
            actionList = getAttractionList();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        //trip.refreshAdapter(trip.listAdapter,trip.tripGroupList,actionList);
        //agency.refreshAdapter(agency.listAdapter,agency.agencyGroupList,businessList);
    }
}