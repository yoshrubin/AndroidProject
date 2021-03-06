 package com.example.userside.Backend.DB;

 import com.example.userside.Backend.expendableList.GroupAgency;

 import java.util.ArrayList;
         
        //this is an implementation of filter for businesses so it will be searchable
         
         public class BusinessFilter extends ListFilter<GroupAgency>{
     
                 /**
           * Constructor
           *
           * @param input the query input
           * @param data  the data to filter
           */
                 public BusinessFilter(String input, ArrayList<GroupAgency> data) {
                 super(input, data);
             }
     
                 @Override
         protected ArrayList<GroupAgency> SumFilter(String i, ArrayList<GroupAgency> otherRaw) throws Exception {
                 ArrayList<GroupAgency> sum = new ArrayList<>();
                 //clone list
                         ArrayList<GroupAgency> toRunOn = new ArrayList<>();
                 toRunOn.addAll(otherRaw);
                 //clone
                         sum.addAll(FilterAttributes(i, toRunOn));
                 //sum.addAll(TryParseRange(i,toRunOn));
                         return sum;
             }
     
                 @Override
         protected ArrayList<GroupAgency> FilterAttributes(String i, ArrayList<GroupAgency> toRunOn) throws Exception {
                 ArrayList<GroupAgency> toReturn = new ArrayList<>();
                 for (GroupAgency item : toRunOn)
                             if (item.getAgencyName().toLowerCase().contains(i.toLowerCase()))
                                 toReturn.add(item);
                 return toReturn;
             }
     }