package com.example.userside.Backend.Factory;

import com.example.userside.Backend.DB.listDB;

/**
 * Created by yoshi on 1/26/17.
 */

public class BackendFactory {
        static Backend instance = null;
        public static Backend getFactoryDatabase(){
            if(instance == null)
                instance = new listDB();
            return instance;
        }
}
