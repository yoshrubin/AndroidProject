package com.example.userside.Backend.DB;

import java.util.ArrayList;
import com.example.userside.Backend.Entitites.Business;

/**
 * Created by yoshi on 2/1/17.
 */

public class BusinessFilter extends ListFilter<Business> {

    public BusinessFilter(String input, ArrayList<Business> data) {
        super(input, data);
    }

    @Override
    protected ArrayList<Business> SumFilter(String i, ArrayList<Business> otherRaw) throws Exception {
        ArrayList<Business> sum = new ArrayList<>();
        //clone list
        ArrayList<Business> toRunOn = new ArrayList<>();
        toRunOn.addAll(otherRaw);
        //clone
        sum.addAll(FilterAttributes(i, toRunOn));
        //sum.addAll(TryParseRange(i,toRunOn));
        return sum;
    }

    @Override
    protected ArrayList<Business> FilterAttributes(String i, ArrayList<Business> toRunOn) throws Exception {
        ArrayList<Business> toReturn = new ArrayList<>();
        String[] cols = Business.getColumns();
        for (Business item : toRunOn)
            for (String val : cols)
                if (item.getValue(val).toLowerCase().contains(i.toLowerCase()))
                    toReturn.add(item);
        return toReturn;
    }
}