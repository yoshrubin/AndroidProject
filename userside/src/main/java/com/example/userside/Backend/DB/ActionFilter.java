package com.example.userside.Backend.DB;

import com.example.userside.Backend.Entitites.Action;

import java.util.ArrayList;

/**
 * Created by yoshi on 2/1/17.
 */

public class ActionFilter extends ListFilter<Action>{

    /**
     * Constructor
     *
     * @param input the query input
     * @param data  the data to filter
     */
    public ActionFilter(String input, ArrayList<Action> data) {
        super(input, data);
    }

    @Override
    protected ArrayList<Action> SumFilter(String i, ArrayList<Action> otherRaw) throws Exception {
        ArrayList<Action> sum = new ArrayList<>();
        //clone list
        ArrayList<Action> toRunOn = new ArrayList<>();
        toRunOn.addAll(otherRaw);
        //clone
        sum.addAll(FilterAttributes(i, toRunOn));
        //sum.addAll(TryParseRange(i,toRunOn));
        return sum;
    }

    @Override
    protected ArrayList<Action> FilterAttributes(String i, ArrayList<Action> toRunOn) throws Exception {
        ArrayList<Action> toReturn = new ArrayList<>();
        String[] cols = Action.getColumns();
        for (Action item : toRunOn)
            for (String val : cols)
                if (item.getValue(val).toLowerCase().contains(i.toLowerCase()))
                    toReturn.add(item);
        return toReturn;
    }
}
