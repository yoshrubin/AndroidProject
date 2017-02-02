package com.example.userside.Backend.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.example.userside.Backend.expendableList.ChildAgency;
import com.example.userside.Backend.expendableList.GroupAgency;
import com.example.userside.R;

import java.util.ArrayList;

/**
 * Created by Aviv on 01/02/2017.
 */

public class agencyExpandListAdapter extends BaseExpandableListAdapter {


    private Context context;
    private ArrayList<GroupAgency> groupAgencies;
    public agencyExpandListAdapter(Context context,ArrayList<GroupAgency> groupAgencies){
        this.context=context;
        this.groupAgencies=groupAgencies;
    }


    /**
     * Gets the number of groups.
     *
     * @return the number of groups
     */

    @Override
    public int getGroupCount() {
        return groupAgencies.size();
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
        ArrayList<ChildAgency> childAgencies = groupAgencies.get(groupPosition).getAgencyDetails();
        return childAgencies.size();
    }

    /**
     * Gets the data associated with the given group.
     *
     * @param groupPosition the position of the group
     * @return the data child for the specified group
     */
    @Override
    public Object getGroup(int groupPosition) {
        return groupAgencies.get(groupPosition);
    }

    /**
     * Gets the data associated with the given child within the given group.
     *
     * @param groupPosition the position of the group that the child resides in
     * @param childPosition the position of the child with respect to other
     *                      children in the group
     * @return the data of the child
     */

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        ArrayList<ChildAgency> childAgencies=groupAgencies.get(groupPosition).getAgencyDetails();
        return childAgencies.get(childPosition);
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
        GroupAgency groupAgency=(GroupAgency)getGroup(groupPosition);
        if(convertView==null){
            LayoutInflater inf =(LayoutInflater)context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            convertView=inf.inflate(R.layout.group_agency_item,null);
        }
        TextView agencyName=(TextView)convertView.findViewById(R.id.agency_name);
        agencyName.setText(groupAgency.getAgencyName());
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
    /*ChildTrip childTrip=(ChildTrip)getChild(groupPosition,childPosition);

        if(convertView==null){
            LayoutInflater inflater=(LayoutInflater)context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
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

        return convertView;*/
    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        ChildAgency childAgency=(ChildAgency)getChild(groupPosition,childPosition);
        if(convertView==null){
            LayoutInflater inflater=(LayoutInflater)context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            convertView=inflater.inflate(R.layout.child_ageny_item,null);
        }
        TextView location =(TextView)convertView.findViewById(R.id.entred_location_agency_exp);
        location.setText(childAgency.getAgencyLocation());
        TextView email =(TextView)convertView.findViewById(R.id.entred_mail_agency_exp);
        email.setText(childAgency.getAgencyMail());
        TextView website =(TextView)convertView.findViewById(R.id.entred_site_agency_exp);
        website.setText(childAgency.getAgencyWebsite());
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
}
