package com.example.yoshi.funtimes.Model.Backend;

import android.content.ContentValues;
import android.database.Cursor;

import java.text.ParseException;

/*
 * We Use the IdataSource to get/store data into our DS regardless of which it is
 */

public interface IDataSource {
     Cursor getActionsDS() throws Exception;
     Cursor getBusinessDS() throws Exception;
     Cursor getUsersDS() throws Exception;

     //the string s is actually a key in the entity which is:
     Cursor getBusinessByString(String s) throws Exception;//the key here is IDN
     Cursor getActionByString(String s) throws Exception;//the key here is IDN
     Cursor getUserByString(String s) throws Exception;//the key here is password. // TODO: maybe we should make the username as a KEY

     void addUser(ContentValues user);
     void addBusiness(ContentValues business);
     void addAction(ContentValues action) throws ParseException;

    boolean newBusiness();
    boolean newAction();
}
