package com.example.yoshi.funtimes.Model.DataSources;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.MatrixCursor;

import com.example.yoshi.funtimes.Model.Backend.IDataSource;
import com.example.yoshi.funtimes.Model.Backend.ManagerFactory;
import com.example.yoshi.funtimes.Model.Entitites.Action;
import com.example.yoshi.funtimes.Model.Entitites.Business;
import com.example.yoshi.funtimes.Model.Entitites.User;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Objects;


/*
* this class implements the IDataSource interface we created.
* this class act as implantation of local DB of lists.
* */
public class ListDataSource implements IDataSource {

    private static ArrayList<Action> actionList = new ArrayList<>();
    private static ArrayList<Business> businessList = new ArrayList<>();
    private static ArrayList<User> userList = new ArrayList<>();

    /*
    * We will be using these when we implement the query function in ContentProvide
     */

    @Override
    public Cursor getActionsDS() {
        Action action;
        MatrixCursor actionCursor = new MatrixCursor(new String[] {"attraction","country","startdate",
                "enddate","price","description","IDN", "user"});
        for (int i = 0; i < actionList.size(); i++){
            action = actionList.get(i);
            actionCursor.addRow(new Object[]{ action.getAttraction(), action.getCountry(), action.getStartDate(),
            action.getEndDate(), action.getPrice(), action.getDescription(), action.getIDN(), action.getUser() });
        }
        return actionCursor;
    }

    @Override
    public Cursor getBusinessDS() {
        Business business;
        MatrixCursor businessCursor = new MatrixCursor(new String[] {"IDN","country","city",
                "street","housenum","phonenum","email","site", "user"});
        for (int i = 0; i < businessList.size(); i++){
            business = businessList.get(i);
            businessCursor.addRow(new Object[]{ business.getIDN(), business.getCountry(), business.getCity(),
                    business.getStreet(), business.getHousenum(), business.getPhoneNum(),
                    business.getEmail(), business.getSite(), business.getUser() });
        }
        return businessCursor;
    }

    @Override
    public Cursor getUsersDS() {
        User user;
        MatrixCursor userCursor = new MatrixCursor(new String[] {"name","password"});
        for (int i = 0; i < userList.size(); i++){
            user = userList.get(i);
            userCursor.addRow(new Object[]{ user.getUser(), user.getPassword() });
        }
        return userCursor;
    }


    @Override
    public Cursor getActionByString(String s) throws Exception {
        Action action;
        MatrixCursor actionCursor = new MatrixCursor(new String[] {"attraction","country","startdate",
                "enddate","price","description","IDN", "user"});
        for (int i = 0; i < actionList.size(); i++){
            action = actionList.get(i);
            if (Objects.equals(String.valueOf(action.getIDN()), s)){
                actionCursor.addRow(new Object[]{ action.getAttraction(), action.getCountry(), action.getStartDate(),
                        action.getEndDate(), action.getPrice(), action.getDescription(), action.getIDN(), action.getUser() });
                return actionCursor;
            }
        }
        return null;
    }

    @Override
    public Cursor getBusinessByString(String s) {//the key is IDN
        MatrixCursor businessCursor=new MatrixCursor(new String[]{"IDN","name","country","city","street","housenum","phoneNum","email","site","user"});
        //the order here is by deceleration order in business, im not sure if that is the needed order
        boolean flag = false;
        for(Business business: businessList){
            if((String.valueOf(business.getIDN()) == s) || (business.getUser() == s)){
                businessCursor.addRow(new Object[]{
                        business.getIDN(),
                        business.getName(),
                        business.getCountry(),
                        business.getCity(),
                        business.getStreet(),
                        business.getHousenum(),
                        business.getPhoneNum(),
                        business.getEmail(),
                        business.getSite(),
                        business.getUser()
                });
                flag=true;
            }
        }
        if(flag){
            return businessCursor;
        }
        else{
            return null;
        }
    }

    @Override
    public Cursor getUserByString(String s) {//the key is username
        for (User user: userList){
            if(s.equals(user.getUser())){
                MatrixCursor userCursor =new MatrixCursor(new String[]{"user","password"});
                userCursor.addRow(new Object[]{user.getUser(),user.getPassword()});
                return userCursor;
            }
        }
        return null;
    }




    /*
    * The add function are used to implement the insert function in the ContentProvide class
    * here we translate a ContentValue into a ArrayList (which is needed for this DS)
     */

    @Override
    public void addUser(ContentValues user) {
        userList.add(new User(
                user.getAsString("name"),
                user.getAsString("password")
        ));
        ManagerFactory.log +="New User "+user.toString()+"/n";
    }

    @Override
    public void addBusiness(ContentValues business) {
        businessList.add(new Business(
                business.getAsInteger("IDN"),
                business.getAsString("name"),
                business.getAsString("country"),
                business.getAsString("city"),
                business.getAsString("street"),
                business.getAsInteger("housenum"),
                business.getAsString("phonenum"),
                business.getAsString("email"),
                business.getAsString("site"),
                business.getAsString("user")
        ));
        ManagerFactory.businessCount += 1;
        ManagerFactory.log +="New Business "+business.toString()+"/n";
    }

    @Override
    public void addAction(ContentValues action) throws ParseException {
        String myFormat = "dd/MM/yy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        actionList.add(new Action(
                Action.Attraction.valueOf(action.getAsString("attraction")),
                action.getAsString("country"),
                sdf.parse(action.getAsString("startdate")),
                sdf.parse(action.getAsString("enddate")),
                action.getAsDouble("price"),
                action.getAsString("description"),
                action.getAsInteger("IDN"),
                action.getAsString("user")
        ));
        ManagerFactory.actionCount += 1;
        ManagerFactory.log +="New Action "+action.toString()+"/n";
    }

    @Override
    public boolean newBusiness() {
        if (ManagerFactory.businessCount == 0)
            return false;
        else{
            ManagerFactory.businessCount = 0;
            return true;
        }
    }

    @Override
    public boolean newAction() {
        if (ManagerFactory.actionCount == 0)
            return false;
        else{
            ManagerFactory.actionCount = 0;
            return true;
        }
    }
}

