package com.example.userside.Model;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.Toast;

import com.example.userside.Backend.DB.listDB;
import com.example.userside.Backend.Entitites.Action;
import com.example.userside.Backend.adapters.tripExpandListAdapter;
import com.example.userside.Backend.expendableList.ChildTrip;
import com.example.userside.Backend.expendableList.GroupTrip;
import com.example.userside.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Locale;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link TripsFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link TripsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TripsFragment extends android.app.Fragment {

    public listDB dbList = new listDB();
    public ArrayList<Action> actionList = new ArrayList<>();

    private LinkedHashMap<String, GroupTrip> subjects = new LinkedHashMap<String, GroupTrip>();
    private ArrayList<GroupTrip> tripGroupList = new ArrayList<GroupTrip>();

    private tripExpandListAdapter listAdapter;
    private ExpandableListView exp_trips;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public TripsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment TripsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static TripsFragment newInstance(String param1, String param2) {
        TripsFragment fragment = new TripsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);

        }
        try {
            actionList = dbList.getAttractionList();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    private void loadData() {
        String myFormat = "dd/MM/yy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        Action action;
        String startDate, endDate;

        for (int i = 0; i < actionList.size(); i++){
            action = actionList.get(i);
            startDate = sdf.format(action.getStartDate());
            endDate = sdf.format(action.getEndDate());
            addTrip(action.getCountry(),startDate,endDate,action.getUser(),(float)action.getPrice());
        }
    }

    //private int addTrip(String country, Date startD, Date endD, String agency, float price) {
    private int addTrip(String country, String startD, String endD, String agency, float price) {

        int groupPosition = 0;

        //check the hash map if the group already exists
        GroupTrip headerInfo = subjects.get(country);
        //add the group if doesn't exists
        if (headerInfo == null) {
            headerInfo = new GroupTrip();
            headerInfo.settripDetails(new ArrayList<ChildTrip>());
            headerInfo.setCountryName(country);
            subjects.put(country, headerInfo);
            tripGroupList.add(headerInfo);
        }
        //get the children for the group
        ArrayList<ChildTrip> tripChildList = headerInfo.gettripDetails();
        //size of the children list
        int listSize = tripChildList.size();
        //add to the counter
        listSize++;

        //create a new child and add that to the group
        ChildTrip detailInfo = new ChildTrip();


        detailInfo.setStartDate(startD);
        detailInfo.setEndDate(endD);
        detailInfo.setAgency(agency);
        detailInfo.setPrice(price);

        tripChildList.add(detailInfo);
        headerInfo.settripDetails(tripChildList);
        //find the group position inside the list
        groupPosition = tripGroupList.indexOf(headerInfo);
        return groupPosition;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_trips,container, false);
        // Inflate the layout for this fragment

        // add data for displaying in expandable list view
        loadData();

        //get reference of the ExpandableListView
        exp_trips = (ExpandableListView) view.findViewById(R.id.exp_trips);

        // create the adapter by passing your ArrayList data
        listAdapter = new tripExpandListAdapter(secondAppActivity.context,tripGroupList );

        // attach the adapter to the expandable list view
        exp_trips.setAdapter(listAdapter);

        //expand all the Groups
        //expandAll();

        // setOnChildClickListener listener for child row click
        exp_trips.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                //get the group header
                GroupTrip headerInfo = tripGroupList.get(groupPosition);
                //get the child info
                ChildTrip detailInfo =  headerInfo.gettripDetails().get(childPosition);
                //display it or do something with it
                Toast.makeText(secondAppActivity.context.getApplicationContext()  , " Clicked on :: " + headerInfo.getcountryName()
                        + "/" +detailInfo.getStartDate()+"/"+ detailInfo.getAgency()+"/"+  +detailInfo.getPrice(), Toast.LENGTH_LONG).show();
                return false;
            }
        });

        // setOnGroupClickListener listener for group heading click
        exp_trips.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                //get the group header
                GroupTrip headerInfo = tripGroupList.get(groupPosition);
                //display it or do something with it
                Toast.makeText(secondAppActivity.context.getApplicationContext(), " Header is :: " + headerInfo.getcountryName(),
                        Toast.LENGTH_LONG).show();

                return false;
            }
        });

        return view;
    }

    private void expandAll() {
        int count = listAdapter.getGroupCount();
        for (int i = 0; i < count; i++){
            exp_trips.expandGroup(i);
        }
    }

    //method to collapse all groups
    private void collapseAll() {
        int count = listAdapter.getGroupCount();
        for (int i = 0; i < count; i++){
            exp_trips.collapseGroup(i);
        }
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
/*        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }*/
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);


    }
}
