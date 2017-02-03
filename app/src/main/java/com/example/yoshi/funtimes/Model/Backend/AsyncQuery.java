package com.example.yoshi.funtimes.Model.Backend;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;


public class AsyncQuery extends AsyncTask<Object,Void,Void> {

    private final String TAG = "DBQuery";
    private Uri uri;
    private String string;
    private Context context;
    public Cursor mCursor;
    public boolean flag = true;
    public boolean ifError = false;


    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
        if(ifError)
            Toast.makeText(context, "Error Querying " + string, Toast.LENGTH_SHORT).show();

    }

    @Override
    protected Void doInBackground(Object... params) {
        uri = (Uri) params[0];
        string = (String) params[1];
        context = (Context) params[2];
        try {
            mCursor = context.getContentResolver().query(uri,null,string,null,null,null);
        } catch (Exception ex) {
           // {forbidden} Toast.makeText(context, string + "Already Exists In DB", Toast.LENGTH_SHORT).show();
            ifError = true;
        }
        publishProgress();
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        Toast.makeText(context, "Value Queried! ", Toast.LENGTH_SHORT).show();
        Log.i(TAG, uri.getPath() + " , Queried! ");
    }
}