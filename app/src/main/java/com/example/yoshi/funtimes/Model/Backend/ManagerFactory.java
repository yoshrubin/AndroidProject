package com.example.yoshi.funtimes.Model.Backend;

import com.example.yoshi.funtimes.Model.DataSources.ListDataSource;
import com.example.yoshi.funtimes.Model.DataSources.RemoteDataSource;

/**
 * Created by yoshi on 11/25/16.
 */

public class ManagerFactory {//factory Method need to be here.
    //             IDataSource
    private static IDataSource instance = null;
    public static IDataSource getDB() {
        return getDataSource();
    }

    private static IDataSource getDataSource(){
        //singleton
        if(instance == null)
            instance = new RemoteDataSource();
        return instance;
    }

    /*
    * variables I will be using in functions that I want Static log is the log of occuring events the counts are fro checking when new is added
    */
    public static String log;
    public static int businessCount;
    public static int actionCount;
}

