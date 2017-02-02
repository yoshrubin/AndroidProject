package com.example.userside.Backend.DB;

import com.example.userside.Backend.Entitites.Action;
import com.example.userside.Backend.expendableList.GroupTrip;

import java.security.acl.Group;
import java.util.ArrayList;

/**
 * Created by yoshi on 2/1/17.
 */

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
