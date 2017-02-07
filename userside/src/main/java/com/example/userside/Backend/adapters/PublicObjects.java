package com.example.userside.Backend.adapters;

import com.example.userside.Model.AgenciesFragment;
import com.example.userside.Model.TripsFragment;
import com.example.userside.Model.secondAppActivity;


public class PublicObjects {

    public static secondAppActivity start = null;
    public static TripsFragment AttFrag = null;
    public static AgenciesFragment BussFrag = null;
    public static android.app.Fragment currentFrag = null;

    public static TripsFragment getAttractionFragment(){
        if(AttFrag == null)
            AttFrag = new TripsFragment();
        return AttFrag;
    }
    public static AgenciesFragment getBusinessFragment(){
        if(BussFrag == null)
            BussFrag = new AgenciesFragment();
        return BussFrag;
    }
    public static android.app.Fragment getCurrentFrag(){
        return currentFrag;
    }
}
