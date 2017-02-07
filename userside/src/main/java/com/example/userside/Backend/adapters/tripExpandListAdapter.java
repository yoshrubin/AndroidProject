package com.example.userside.Backend.adapters;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.example.userside.Backend.expendableList.ChildTrip;
import com.example.userside.Backend.expendableList.GroupTrip;
import com.example.userside.R;

import java.util.ArrayList;


//this is our Expendable list adapter of trips

public class tripExpandListAdapter extends BaseExpandableListAdapter {

    private final Context context;
    private ArrayList<GroupTrip> groupTrips;
    public tripExpandListAdapter(Context context,ArrayList<GroupTrip> groupTrips){
        this.context=context;
        this.groupTrips=groupTrips;
    }

    /**
     * Gets the number of groups.
     *
     * @return the number of groups
     */
    @Override
    public int getGroupCount() {
        return groupTrips.size();
    }

    /**
     * Gets the number of children in a specified group.
     *
     * @param groupPosition the position of the group for which the children
     *                      count should be returned
     * @return the children count in the specified group
     */
    @Override
    public int getChildrenCount(int groupPosition) {
        ArrayList<ChildTrip> childTrips = groupTrips.get(groupPosition).gettripDetails();
        return  childTrips.size();
    }

    /**
     * Gets the data associated with the given group.
     *
     * @param groupPosition the position of the group
     * @return the data child for the specified group
     */
    @Override
    public Object getGroup(int groupPosition) {
        return groupTrips.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition){
        ArrayList<ChildTrip> childTrips=groupTrips.get(groupPosition).gettripDetails();
        return childTrips.get(childPosition);
    }

    /**
     * Gets the ID for the group at the given position. This group ID must be
     * unique across groups. The combined ID (see
     * {@link #getCombinedGroupId(long)}) must be unique across ALL items
     * (groups and all children).
     *
     * @param groupPosition the position of the group for which the ID is wanted
     * @return the ID associated with the group
     */
    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    /**
     * Gets the ID for the given child within the given group. This ID must be
     * unique across all children within the group. The combined ID (see
     * {@link #getCombinedChildId(long, long)}) must be unique across ALL items
     * (groups and all children).
     *
     * @param groupPosition the position of the group that contains the child
     * @param childPosition the position of the child within the group for which
     *                      the ID is wanted
     * @return the ID associated with the child
     */
    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    /**
     * Indicates whether the child and group IDs are stable across changes to the
     * underlying data.
     *
     * @return whether or not the same ID always refers to the same object
     * @see Adapter#hasStableIds()
     */
    @Override
    public boolean hasStableIds() {
        return true;
    }

    /**
     * Gets a View that displays the given group. This View is only for the
     * group--the Views for the group's children will be fetched using
     * {@link #getChildView(int, int, boolean, View, ViewGroup)}.
     *
     * @param groupPosition the position of the group for which the View is
     *                      returned
     * @param isExpanded    whether the group is expanded or collapsed
     * @param convertView   the old view to reuse, if possible. You should check
     *                      that this view is non-null and of an appropriate type before
     *                      using. If it is not possible to convert this view to display
     *                      the correct data, this method can create a new view. It is not
     *                      guaranteed that the convertView will have been previously
     *                      created by
     *                      {@link #getGroupView(int, boolean, View, ViewGroup)}.
     * @param parent        the parent that this view will eventually be attached to
     * @return the View corresponding to the group at the specified position
     */
    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
       GroupTrip groupTrip=(GroupTrip) getGroup(groupPosition);
        if(convertView==null){
            LayoutInflater inf =(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView=inf.inflate(R.layout.group_trip_item,null);
        }
        TextView country=(TextView)convertView.findViewById(R.id.trip_dest);
        country.setText(groupTrip.getcountryName());
        return convertView;
    }

    /**
     * Gets a View that displays the data for the given child within the given
     * group.
     *
     * @param groupPosition the position of the group that contains the child
     * @param childPosition the position of the child (for which the View is
     *                      returned) within the group
     * @param isLastChild   Whether the child is the last child within the group
     * @param convertView   the old view to reuse, if possible. You should check
     *                      that this view is non-null and of an appropriate type before
     *                      using. If it is not possible to convert this view to display
     *                      the correct data, this method can create a new view. It is not
     *                      guaranteed that the convertView will have been previously
     *                      created by
     *                      {@link #getChildView(int, int, boolean, View, ViewGroup)}.
     * @param parent        the parent that this view will eventually be attached to
     * @return the View corresponding to the child at the specified position
     */
    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {

        ChildTrip childTrip=(ChildTrip)getChild(groupPosition,childPosition);

        if(convertView==null){
            LayoutInflater inflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView=inflater.inflate(R.layout.child_trip_item,null);
        }
        TextView startDate =(TextView)convertView.findViewById(R.id.entered_startdate_trip_exp);
        startDate.setText(childTrip.getStartDate());

        TextView endDate =(TextView)convertView.findViewById(R.id.entred_enddate_trip_exp);
        endDate.setText(childTrip.getEndDate().toString());

        TextView agency =(TextView)convertView.findViewById(R.id.entred_agency_trip_exp);
        agency.setText(childTrip.getAgency().toString());

        TextView price =(TextView)convertView.findViewById(R.id.entred_price_trip_exp);
        price.setText(String.valueOf(childTrip.getPrice()));

        return convertView;
    }

    /**
     * Whether the child at the specified position is selectable.
     *
     * @param groupPosition the position of the group that contains the child
     * @param childPosition the position of the child within the group
     * @return whether the child is selectable.
     */
    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    public void setNewItems(ArrayList<GroupTrip> groupTrips) {
        this.groupTrips = groupTrips;
        notifyDataSetChanged();
    }
}
