package com.example.userside.Backend.DB;

import com.example.userside.Backend.expendableList.GroupTrip;

import java.util.ArrayList;

//this is an implementation of filter for trips so it will be searchable


public class TripGroupFilter extends ListFilter<GroupTrip>{

    /**
     * Constructor
     *
     * @param input the query input
     * @param data  the data to filter
     */
    public TripGroupFilter(String input, ArrayList<GroupTrip> data) {
        super(input, data);
    }

    @Override
    protected ArrayList<GroupTrip> SumFilter(String i, ArrayList<GroupTrip> otherRaw) throws Exception {
        ArrayList<GroupTrip> sum = new ArrayList<>();
        //clone list
        ArrayList<GroupTrip> toRunOn = new ArrayList<>();
        toRunOn.addAll(otherRaw);
        //clone
        sum.addAll(FilterAttributes(i, toRunOn));
        //sum.addAll(TryParseRange(i,toRunOn));
        return sum;
    }

    @Override
    protected ArrayList<GroupTrip> FilterAttributes(String i, ArrayList<GroupTrip> toRunOn) throws Exception {
        ArrayList<GroupTrip> toReturn = new ArrayList<>();
        for (GroupTrip item : toRunOn)
                if (item.getcountryName().toLowerCase().contains(i.toLowerCase()))
                    toReturn.add(item);
        return toReturn;
    }
}
