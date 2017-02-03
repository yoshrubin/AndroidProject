package com.example.yoshi.funtimes.Model.Backend;

import com.example.yoshi.funtimes.Model.DataSources.ListDataSource;
import com.example.yoshi.funtimes.Model.DataSources.RemoteDataSource;



//an important Singleton that determine what kind of DB we CHOOSE local list or remote DB
public class ManagerFactory {
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
    * variables I will be using in functions that I want Static log is the log of occurring events the counts are fro checking when new is added
    */
    public static String log;
    public static int businessCount;
    public static int actionCount;
}

