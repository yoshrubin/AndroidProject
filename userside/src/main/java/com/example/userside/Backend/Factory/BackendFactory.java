package com.example.userside.Backend.Factory;

import com.example.userside.Backend.DB.listDB;


//a Singleton that determines what DB will be used in the second app

public class BackendFactory {
        static Backend instance = null;
        public static Backend getFactoryDatabase(){
            if(instance == null)
                instance = new listDB();
            return instance;
        }
}
