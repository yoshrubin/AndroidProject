package com.example.yoshi.funtimes.Controller;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.yoshi.funtimes.Model.Backend.AsyncInsert;
import com.example.yoshi.funtimes.Model.Backend.AsyncQuery;
import com.example.yoshi.funtimes.Model.DataSources.ContentProvide;
import com.example.yoshi.funtimes.R;

import static com.example.yoshi.funtimes.Controller.LoginActivity.MY_PREFS_NAME;

public class Register extends Activity {

    Cursor myCursor = null;
    boolean flag=true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        setTitle("Register");
    }


    public void doneRegisterClicked(View view) {


        EditText username =(EditText)findViewById(R.id.editText8);
        EditText password =(EditText)findViewById(R.id.editText9);
        EditText confirmPassword =(EditText)findViewById(R.id.editText10);
        String usernameTest=username.getText().toString();
        String passwordTest=password.getText().toString();
        String confirmPasswordTest=confirmPassword.getText().toString();

        if(usernameTest.equals("")){
            Toast.makeText(getApplicationContext(),"username field is empty",Toast.LENGTH_LONG).show();
            flag=false;
            return;
        }
        if(usernameTest.length()>20){
            Toast.makeText(getApplicationContext(),"username must be shorter than 20 characters",Toast.LENGTH_LONG).show();
            flag=false;
            return;
        }

        if(usernameTest.contains(" ")){
            Toast.makeText(getApplicationContext(),"username must not contain space char",Toast.LENGTH_LONG).show();
            flag=false;
            return;
        }


        if(passwordTest.equals("")){
            Toast.makeText(getApplicationContext(),"password field is empty",Toast.LENGTH_LONG).show();
            flag=false;
            return;
        }
        if((passwordTest.length()>12) || (passwordTest.length()<6)){
            Toast.makeText(getApplicationContext(),"password must contain 6-12 characters",Toast.LENGTH_LONG).show();
            flag=false;
            return;
        }
        if(flag){
           /*AsyncQuery dbQuery = new AsyncQuery();
           dbQuery.execute(ContentProvide.USER_URI,username.getText().toString(),this);
           myCursor = dbQuery.mCursor;*/
            myCursor=getContentResolver().query(
                    ContentProvide.USER_URI,
                    null,
                    username.getText().toString(),
                    null,
                    null
            );
        }
        if(!confirmPasswordTest.equals(passwordTest)){
            Toast.makeText(getApplicationContext(),"The password is not equal to the confirmation",Toast.LENGTH_LONG).show();
            return;
        }
        final ContentValues values=new ContentValues();
        values.put("name",usernameTest);
        values.put("password",passwordTest);

        if (myCursor == null){
            AsyncInsert dbInsert = new AsyncInsert();
            dbInsert.execute( ContentProvide.USER_URI, values, this);

            SharedPreferences.Editor editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
            editor.putString("usernameKey",username.getText().toString());
            editor.putString("passwordKey",password.getText().toString());
            editor.commit();

            Intent myIntent = new Intent(Register.this, AddWindow.class);
            /*
            *Still not sure if I wanna send  the username
             */

            myIntent.putExtra("user",usernameTest);
            //myIntent.putExtra("Password",pwd);
            startActivity(myIntent);
        }
        else if(myCursor != null){
            Toast.makeText(getApplicationContext(),"User already exists",Toast.LENGTH_LONG).show();
            return;
        }
    }
}
