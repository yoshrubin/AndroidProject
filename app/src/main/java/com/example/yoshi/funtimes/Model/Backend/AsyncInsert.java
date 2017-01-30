package com.example.yoshi.funtimes.Model.Backend;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by yoshi on 12/14/16.
 */

public class AsyncInsert extends AsyncTask<Object,Void,Void>{

    private final String TAG = "DBInsert";
    private Uri uri;
    private ContentValues value;
    private Context context;
    boolean ifError=false;

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
        if(ifError)
            Toast.makeText(context,"cannot insert value to the database!", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected Void doInBackground(Object... params) {
        uri = (Uri) params[0];
        value = (ContentValues) params[1];
        context = (Context) params[2];
        try {
            ContentResolver mCursor = context.getContentResolver();
            mCursor.insert(uri, value);
        }catch (Exception ex)
        {
            ifError=true;
        }
        publishProgress();
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        if(!ifError) {
            Toast.makeText(context, "Value Inserted! ", Toast.LENGTH_SHORT).show();
            Log.i(TAG, uri.getPath() + " , Inserted! ");
        }
        else{
            Toast.makeText(context, "Failed to Insert", Toast.LENGTH_SHORT).show();
            Log.i(TAG, uri.getPath() + " , Not Inserted! ");
        }
    }
}
