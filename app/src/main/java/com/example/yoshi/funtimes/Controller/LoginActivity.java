package com.example.yoshi.funtimes.Controller;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.yoshi.funtimes.Model.DataSources.ContentProvide;
import com.example.yoshi.funtimes.Model.Service.service;
import com.example.yoshi.funtimes.R;

import static com.example.yoshi.funtimes.Model.DataSources.ContentProvide.ACTION_URI;


/**
 * A login screen that offers login via user/password.
 * AND the first window/activity got opened.
 * bounded with login_window.XML
 */
public class LoginActivity extends Activity {

    public static final String MY_PREFS_NAME="myPrefsFile";
    private EditText usernameText = null;
    private EditText passwordText=null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);/**/
        setContentView(R.layout.login_window);
        setTitle("Login");

        Cursor test = getContentResolver().query(ACTION_URI,null,null,null,null,null);
        Cursor mytest = test;

        /*
        Creating Shared Preference
         */
        SharedPreferences sharedPreferences= getSharedPreferences(MY_PREFS_NAME,MODE_PRIVATE);
        String username=sharedPreferences.getString("usernameKey",null);
        String password=sharedPreferences.getString("passwordKey",null);
        usernameText=(EditText) findViewById(R.id.userEdit);
        usernameText.setText(username);
        passwordText=(EditText) findViewById(R.id.passEdit);
        passwordText.setText(password);
        /*
        service to check if db is updated and refreshes local content
         */
        startService(new Intent(getBaseContext(),service.class));
    }
    private void loginCheck(Cursor mCursor){
        String tempPassword="";

        if (mCursor == null) {
            Toast.makeText(getApplicationContext(), "Username does not exist, feel free to register!\n", Toast.LENGTH_LONG).show();
            return;
        }

        if (mCursor.moveToFirst()) {
            do {
                int currentColumnIndex = mCursor.getColumnIndex("password");
                tempPassword = mCursor.getString(currentColumnIndex);

            } while (mCursor.moveToNext());
        }
        if (!tempPassword.equals(passwordText.getText().toString())) {
            Toast.makeText(getApplicationContext(), "Username or Password is incorrect", Toast.LENGTH_LONG).show();
            return;
        }

        Intent myIntent = new Intent(LoginActivity.this, AddWindow.class);
        /*
        * Not sure if I want to send the username to the next window
         */
        myIntent.putExtra("user",usernameText.getText().toString());
        //myIntent.putExtra("password",passwordText.getText().toString());

        startActivity(myIntent);
    }

    public void loginClicked(View view) {
        SharedPreferences.Editor editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
        editor.putString("usernameKey",usernameText.getText().toString());
        editor.putString("passwordKey",passwordText.getText().toString());
        editor.apply();
        Cursor mCursor;

        //local string that store the password temporary



        mCursor=getContentResolver().query(
                ContentProvide.USER_URI,
                null,
                usernameText.getText().toString(),//usernameText.getText().toString(),
                null,
                null
        );

        loginCheck(mCursor);//TODO I only encapsulated the check to a method FIRST we need to ask how and where we need to use this method

    }

    public void registerClicked(View view) {//Register button
        Intent myIntent = new Intent(this, Register.class);
        startActivity(myIntent);
    }
}

