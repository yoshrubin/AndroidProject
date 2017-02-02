package com.example.userside.Model;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.userside.Backend.DB.BusinessFilter;
import com.example.userside.Backend.DB.listDB;
import com.example.userside.Backend.Entitites.Business;
import com.example.userside.Backend.adapters.agencyExpandListAdapter;
import com.example.userside.Backend.expendableList.ChildAgency;
import com.example.userside.Backend.expendableList.GroupAgency;
import com.example.userside.R;

import java.util.ArrayList;
import java.util.LinkedHashMap;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link AgenciesFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link AgenciesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AgenciesFragment extends android.app.Fragment {

    private LinkedHashMap<String, GroupAgency> subjects = new LinkedHashMap<String, GroupAgency>();
    private ArrayList<GroupAgency> agencyGroupList = new ArrayList<GroupAgency>();

    private agencyExpandListAdapter listAdapter;
    private ExpandableListView exp_agencies;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public listDB dbList = new listDB();
    public ArrayList<Business> businessList = new ArrayList<>();
    private ArrayList<Business> beforeFilterList = new ArrayList<>();

    private OnFragmentInteractionListener mListener;

    public AgenciesFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AgenciesFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AgenciesFragment newInstance(String param1, String param2) {
        AgenciesFragment fragment = new AgenciesFragment();
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
        businessList.addAll(dbList.getBusinessList());
    }
    private void loadData(){
        Business business;

        for (int i = 0; i < businessList.size(); i++) {
            business = businessList.get(i);
            addAgency(business.getName(), business.getCountry(), business.getSite(), business.getEmail());
        }
    }
    private int addAgency(String agencyName, String location, String website, String email){

        int groupPosition=0;
        //check the hash map if the group already exists
        GroupAgency headerInfo = subjects.get(agencyName);
        //add the group if doesn't exists
        if (headerInfo == null) {
            headerInfo = new GroupAgency();
            headerInfo.setAgencyDetails (new ArrayList<ChildAgency>());
            headerInfo.setAgencyName(agencyName);
            subjects.put(agencyName, headerInfo);
            agencyGroupList.add(headerInfo);
        }
        //get the children for the group
        ArrayList<ChildAgency> agencyChildList = headerInfo.getAgencyDetails();
        //size of the children list
        int listSize = agencyChildList.size();
        //add to the counter
        listSize++;
        //create a new child and add that to the group
        ChildAgency detailInfo = new ChildAgency();

        detailInfo.setAgencyLocation(location);
        detailInfo.setAgencyWebsite(website);
        detailInfo.setAgencyMail(email);

        agencyChildList.add(detailInfo);
        headerInfo.setAgencyDetails(agencyChildList);
        //find the group position inside the list
        groupPosition = agencyChildList.indexOf(headerInfo);
        return groupPosition;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.fragment_agencies, container, false);
        // add data for displaying in expandable list view
        loadData();
        //get reference of the ExpandableListView
        exp_agencies = (ExpandableListView) view.findViewById(R.id.exp_agencies);

        // create the adapter by passing your ArrayList data
        listAdapter = new agencyExpandListAdapter(secondAppActivity.context,agencyGroupList);

        // attach the adapter to the expandable list view
        exp_agencies.setAdapter(listAdapter);

        // setOnChildClickListener listener for child row click
        exp_agencies.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                //get the group header
                GroupAgency headerInfo = agencyGroupList.get(groupPosition);
                //get the child info
                ChildAgency detailInfo =  headerInfo.getAgencyDetails().get(childPosition);
                //display it or do something with it
                /*Toast.makeText(secondAppActivity.context.getApplicationContext()  , " Clicked on :: " + headerInfo.getcountryName()
                        + "/" +detailInfo.getStartDate()+"/"+ detailInfo.getAgency()+"/"+  +detailInfo.getPrice(), Toast.LENGTH_LONG).show();
               */
                final TextView site = (TextView) exp_agencies.findViewById(R.id.entred_site_agency_exp);
                final TextView location = (TextView) exp_agencies.findViewById(R.id.entred_location_agency_exp);
                final TextView email = (TextView) exp_agencies.findViewById(R.id.entred_mail_agency_exp);
                site.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        WebsiteIntet(getActivity(), site.getText().toString());
                    }
                });
                location.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        MapsIntent(getActivity(), location.getText().toString());
                    }
                });
                email.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        EmailIntent(getActivity(), email.getText().toString());
                    }
                });
                return false;
            }

        });
        // setOnGroupClickListener listener for group heading click
        exp_agencies.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                //get the group header
                GroupAgency headerInfo = agencyGroupList.get(groupPosition);
                //display it or do something with it
                /*
                Toast.makeText(secondAppActivity.context.getApplicationContext(), " Header is :: " + headerInfo.getcountryName(),
                        Toast.LENGTH_LONG).show();
                */
                return false;
            }
        });

        return view;
    }

    private void expandAll() {
        int count = listAdapter.getGroupCount();
        for (int i = 0; i < count; i++){
            exp_agencies.expandGroup(i);
        }
    }
    private void collapseAll() {
        int count = listAdapter.getGroupCount();
        for (int i = 0; i < count; i++){
            exp_agencies.collapseGroup(i);
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
       /* if (context instanceof OnFragmentInteractionListener) {
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

    @RequiresApi(api = Build.VERSION_CODES.M)
    public void Filter(String s) {
        ArrayList list = new ArrayList();
        //saving current list
        beforeFilterList.clear();
        beforeFilterList.addAll(businessList);

        list.addAll(businessList);
        BusinessFilter filter = new BusinessFilter(s, list);
        ArrayList<Business> newList;
        try {
            newList = filter.Filter();
            businessList.addAll(newList);
            agencyGroupList.clear();
            subjects.clear();
            setNewItems(agencyGroupList, subjects);
        } catch (Exception e) {
            Toast.makeText(getContext(), "Error Parsing Query", Toast.LENGTH_SHORT);
        }
    }

    public void clearFilter() {
        if (beforeFilterList.size() == 0)
            if (businessList.size() != 0)
                return;
        businessList.addAll(beforeFilterList);
        agencyGroupList.clear();
        subjects.clear();
        setNewItems(agencyGroupList,subjects);
    }

    public static void refreshAdapter(BaseExpandableListAdapter ad, ArrayList originList, ArrayList newList) {
        originList.clear();
        originList.addAll(newList);
        ad.notifyDataSetChanged();
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

    public void setNewItems(ArrayList<GroupAgency> agencyGroupList,LinkedHashMap<String, GroupAgency> subjects) {
        this.agencyGroupList = agencyGroupList;
        this.subjects = subjects;
        listAdapter.notifyDataSetChanged();
    }

    /**
     * all the intents when clicked on certain data
     * @param current
     * @param website
     */
    public static void WebsiteIntet(Activity current, String website){
        current.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://" +website)));
    }
    /**
     * all the intents when clicked on certain data
     */
    public static void MapsIntent(Activity curr,String city){
        Intent Chooser;
        String url = "http://maps.google.com/maps?daddr="+city;
        Intent iintent = new Intent(android.content.Intent.ACTION_VIEW,  Uri.parse(url));
        Chooser = Intent.createChooser(iintent,"Launch Maps");
        curr.startActivity(Chooser);
    }
    /**
     * all the intents when clicked on certain data
     */
    public static void EmailIntent(Activity curr,String email){
        if(!email.matches(".+@.+[.]com"))
            if(!email.contains("@"))
                email += "@gmail.com";
            else
                email += "gmail.com";
        Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                "mailto", email, null));
        curr.startActivity(Intent.createChooser(emailIntent, "Send email..."));
    }
}