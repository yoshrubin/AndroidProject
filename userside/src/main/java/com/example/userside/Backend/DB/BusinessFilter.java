 package com.example.userside.Backend.DB;

         import com.example.userside.Backend.Entitites.Business;
         import com.example.userside.Backend.expendableList.GroupAgency;
         
         import java.security.acl.Group;
         import java.util.ArrayList;
         
         /**
   * Created by yoshi on 2/1/17.
   */
         
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