package com.example.yoshi.funtimes.Model.DataSources;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.CancellationSignal;
import android.support.annotation.Nullable;

import com.example.yoshi.funtimes.Model.Backend.IDataSource;
import com.example.yoshi.funtimes.Model.Backend.ManagerFactory;

import java.text.ParseException;
import java.util.Objects;

/**
 * Created by yoshi on 11/25/16.
 */

public class ContentProvide extends ContentProvider {
    private static final UriMatcher sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
    private static IDataSource manager = ManagerFactory.getDB();//lll

    //sets the uris
    static {
        sUriMatcher.addURI("com.example.yoshi.funtimes", "business", 1);
        sUriMatcher.addURI("com.example.yoshi.funtimes", "actions", 2);
        sUriMatcher.addURI("com.example.yoshi.funtimes", "users", 3);
    }

    /*
    * Easy reference for the Uri to be used in MActivity for insert function
     */
    static final String PROVIDE_NAME = "content://com.example.yoshi.funtimes.Model.DataSources.ContentProvide";
    static final String URL_BUSINESS = PROVIDE_NAME + "/business";
    public static final Uri BUSINESS_URI = Uri.parse(URL_BUSINESS);
    static final String URL_USER = PROVIDE_NAME + "/users";
    public static final Uri USER_URI = Uri.parse(URL_USER);
    static final String URL_ACTION = PROVIDE_NAME + "/actions";
    public static final Uri ACTION_URI = Uri.parse(URL_ACTION);

    @Override
    public boolean onCreate() {
        return true;
    }

    /*
    public Cursor query(Uri uri, String[] strings, String s, String[] strings1, String s1) {//TODO implement the selection (with argument s)

         ///### The uri.getPath() function returns the path with the preceding "/", if you want to get rid of it you can simply
         ///use the substring function  as shown below.


        String table = uri.getPath().substring(1);//TODO why there is substring(1)
        if (table.equalsIgnoreCase("business")&& s==null) {
            try {
                return manager.getBusinessDS();
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }

        if (table.equalsIgnoreCase("business")) {
            try {
                return manager.getBusinessByString(s);
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }

        if (table.equalsIgnoreCase("users")&& s==null) {
            try {
                return manager.getUsersDS();
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }
        if (table.equalsIgnoreCase("users")) {
            try {
                return manager.getUserByString(s);
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }
        else if (table.equalsIgnoreCase("actions") && s==null) {
            try {
                return manager.getActionsDS();
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }

        else if (table.equalsIgnoreCase("actions")) {
            try {
                return manager.getActionByString(s);
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }
        return null;
    }
*/
        /*
    The function returns a cursor containing a table of agencies or trips according to the given uri
    */

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] strings, String s, String[] strings1, String s1) {
        String table = uri.getPath().substring(1);
        final String id = s;
        if (table.equalsIgnoreCase("users") && !Objects.equals(s, null))//s is the id
            try {
                AsyncTask<Void, Void, Cursor> asyncTask = new AsyncTask<Void, Void, Cursor>() {
                    @Override
                    protected Cursor doInBackground(Void... params) {
                        try {
                            return manager.getUserByString(id);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        return null;
                    }
                };
                asyncTask.execute();
                return asyncTask.get();

            } catch (Exception e) {

                e.printStackTrace();

                return null;
            }

        if (table.equalsIgnoreCase("users"))//s is the id
            try {
                AsyncTask<Void, Void, Cursor> asyncTask = new AsyncTask<Void, Void, Cursor>() {
                    @Override
                    protected Cursor doInBackground(Void... params) {
                        try {
                            return manager.getUsersDS();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        return null;
                    }
                };
                asyncTask.execute();
                return asyncTask.get();

            } catch (Exception e) {

                e.printStackTrace();

                return null;
            }

        if (table.equalsIgnoreCase("business") && !Objects.equals(s, null)) {

            try {
                AsyncTask<Void, Void, Cursor> asyncTask = new AsyncTask<Void, Void, Cursor>() {
                    @Override
                    protected Cursor doInBackground(Void... params) {
                        try {
                            return manager.getBusinessByString(id);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        return null;
                    }
                };
                asyncTask.execute();
                return asyncTask.get();

            } catch (Exception e) {

                e.printStackTrace();

                return null;
            }
        }

        if (table.equalsIgnoreCase("business")) {

            try {
                AsyncTask<Void, Void, Cursor> asyncTask = new AsyncTask<Void, Void, Cursor>() {
                    @Override
                    protected Cursor doInBackground(Void... params) {
                        try {
                            return manager.getBusinessDS();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        return null;
                    }
                };
                asyncTask.execute();
                return asyncTask.get();

            } catch (Exception e) {

                e.printStackTrace();

                return null;
            }
        }

        if (table.equalsIgnoreCase("actions") && !Objects.equals(s, null)) {

            try {
                AsyncTask<Void, Void, Cursor> asyncTask = new AsyncTask<Void, Void, Cursor>() {
                    @Override
                    protected Cursor doInBackground(Void... params) {
                        try {
                            return manager.getActionByString(id);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        return null;
                    }
                };
                asyncTask.execute();
                return asyncTask.get();

            } catch (Exception e) {

                e.printStackTrace();

                return null;
            }
    }

    if(table.equalsIgnoreCase("actions"))

    {

        try {
            AsyncTask<Void, Void, Cursor> asyncTask = new AsyncTask<Void, Void, Cursor>() {
                @Override
                protected Cursor doInBackground(Void... params) {
                    try {
                        return manager.getActionsDS();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    return null;
                }
            };
            asyncTask.execute();
            return asyncTask.get();

        } catch (Exception e) {

            e.printStackTrace();

            return null;
        }
    }
    return null;
}


    @Nullable
    @Override
    public String getType(Uri uri) {
        return null;
    }

    @Nullable
    @Override
    /*
    * here is where we will be sending the info to
    */
    public Uri insert(Uri uri, ContentValues obj) {
        String table = uri.getPath().substring(1);
        if (table.equalsIgnoreCase("actions")) {
            try {
                manager.addAction(obj);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            return null;
        }
        if (table.equalsIgnoreCase("business")) {
            manager.addBusiness(obj);
            return null;
        }
        if (table.equalsIgnoreCase("users")) {
            manager.addUser(obj);
            return null;
        }
        throw new IllegalArgumentException("This Content Provider supports only trips insertion");
        }
    @Override
    public int delete(Uri uri, String s, String[] strings) {
        return 0;
    }

    @Override
    public int update(Uri uri, ContentValues contentValues, String s, String[] strings) {
        return 0;
    }
}