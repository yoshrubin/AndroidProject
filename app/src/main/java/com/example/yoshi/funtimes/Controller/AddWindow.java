package com.example.yoshi.funtimes.Controller;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.yoshi.funtimes.Model.DataSources.ContentProvide;
import com.example.yoshi.funtimes.R;
//this class is responsible for the first and main activity(window) got opened right after regular log in.
//bounded with add_window.XML
public class AddWindow extends Activity {

    private static String user;
    private static Cursor myCursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_window);
        setTitle("Add a new...");

        /*
    Here I wanna make sure the user has a business before he can create a new activity.
     */
        user = getIntent().getStringExtra("user");
        Button activity = (Button)findViewById(R.id.addActivityButton);
        myCursor = getContentResolver().query(
                ContentProvide.BUSINESS_URI,
                null,
                user,
                null,
                null
        );
        if (myCursor != null){
            activity.setVisibility(View.VISIBLE);
        }
    }



    public void addBusinessClick(View view) {//first button
        //TODO startActivity(new Intent(AddWindow.this,MActivity.class));
        Intent myIntent = new Intent(AddWindow.this, AddBusinessWindow.class);
        myIntent.putExtra("user",user);
        startActivity(myIntent);
    }

    public void addActivityClick(View view) {//second button
        Intent myIntent = new Intent(AddWindow.this, AddActionWindow.class);
        myIntent.putExtra("user",user);
        startActivity(myIntent);
    }
}
