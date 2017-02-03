package com.example.yoshi.funtimes.Controller;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.Toast;

import com.example.yoshi.funtimes.Model.Backend.AsyncInsert;
import com.example.yoshi.funtimes.Model.DataSources.ContentProvide;
import com.example.yoshi.funtimes.R;

import java.util.Random;

import static com.example.yoshi.funtimes.Controller.AddActionWindow.COUNTRIES;

// This class defines the add business window in the first app which bounded to activity_add_business_window.XML

public class AddBusinessWindow extends Activity {

    int IDN;//business's serial number
    String name;
    String country;
    String city;
    String street;
    int housenum;
    String phoneNum;
    String email;
    String site;
    String user;
    Cursor myCursor;
    protected static int IDNrange=100;//a var that helps to determine business IDN when added

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_business_window);
        setTitle("Add business");

        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,android.R.layout.simple_dropdown_item_1line,COUNTRIES);
        AutoCompleteTextView textView=(AutoCompleteTextView)
                findViewById(R.id.countryEdit);
        textView.setAdapter(adapter);
    }

    public void addBusinessClick(View view) {
        /*
        Only want to take data after the button is pressed
         */
        /*
        For IDN I will choose a random number and then make sure it doesnt exist in db
         */
        do{
            Random r = new Random();
            IDN = r.nextInt(IDNrange);

            myCursor=getContentResolver().query(
                    ContentProvide.BUSINESS_URI,
                    null,
                    Integer.toString(IDN),
                    null,
                    null
            );
        }while (myCursor != null);//meaning already used
        boolean flag=true;
        //Name
        EditText Name = (EditText) findViewById(R.id.nameEdit);
        name = Name.getText().toString();
        //Country
        EditText Country = (EditText) findViewById(R.id.countryEdit);
        country = Country.getText().toString();
        //City
        EditText City = (EditText) findViewById(R.id.cityEdit);
        city = City.getText().toString();
        //Street
        EditText Street = (EditText) findViewById(R.id.streetEdit);
        street = Street.getText().toString();
        try {
            //houseNum
            EditText houseNum = (EditText) findViewById(R.id.housenumEdit);
            housenum = Integer.parseInt(houseNum.getText().toString());
        }catch(Exception e){
            Toast.makeText(getApplicationContext(),"house number field was not filled",Toast.LENGTH_LONG).show();
            flag=false;
            return;
        }
        //phone-number
        try{
        EditText phonenum = (EditText) findViewById(R.id.phonenumEdit);
        phoneNum = phonenum.getText().toString();
        }catch(Exception e){
            Toast.makeText(getApplicationContext(),"phone number field was not filled",Toast.LENGTH_LONG).show();
            flag=false;
            return;
        }
        //Email
        EditText Email = (EditText) findViewById(R.id.emailEdit);
        email = Email.getText().toString();
        //Site
        //phoneNum
        EditText Site = (EditText) findViewById(R.id.siteEdit);
        site = Site.getText().toString();

        user = getIntent().getStringExtra("user");

        //country check
        for (String s:
                COUNTRIES) {
            if(!(country.equals(s))) {
                flag=false;
            }
            else{
                flag=true;
                break;
            }
        }
        if(!flag){
            Toast.makeText(getApplicationContext(),"There is no such Country ",Toast.LENGTH_LONG).show();
            flag=false;
            return;
        }
        //business' name check
        if (name.equals("")){
            Toast.makeText(getApplicationContext(),"business' name field was not filled",Toast.LENGTH_LONG).show();
            flag=false;
            return;
        }
        if (name.length()>20){
            Toast.makeText(getApplicationContext(),"business' name field must be smaller then 20 characters ",Toast.LENGTH_LONG).show();
            flag=false;
            return;
        }
        //city check
        if (city.equals("")) {
            Toast.makeText(getApplicationContext(), "cities field was not filled", Toast.LENGTH_LONG).show();
            flag = false;
            return;
        }

        if (city.length()>20){
            Toast.makeText(getApplicationContext(),"city field must be smaller then 20 characters ",Toast.LENGTH_LONG).show();
            flag=false;
            return;
        }

        //Street check
        if (street.equals("")){
            Toast.makeText(getApplicationContext(),"street field was not filled",Toast.LENGTH_LONG).show();
            flag=false;
            return;
        }

        if (street.length()>20){
            Toast.makeText(getApplicationContext(),"street field must be smaller then 20 characters ",Toast.LENGTH_LONG).show();
            flag=false;
            return;
        }
        //houseNum check
        if (Integer.toString(housenum).equals("")){
            Toast.makeText(getApplicationContext(),"house number field was not filled",Toast.LENGTH_LONG).show();
            flag=false;
            return;
        }

        if (housenum>10000){
            Toast.makeText(getApplicationContext(),"house number field can't be this long",Toast.LENGTH_LONG).show();
            flag=false;
            return;
        }
        //Email Check

        if (!(email.contains("@"))){
            Toast.makeText(getApplicationContext(),"You entered invalid syntax of Email",Toast.LENGTH_LONG).show();
            flag=false;
            return;
        }
        //phone check
        if (phoneNum.equals("")){
            Toast.makeText(getApplicationContext(),"phone number field was not filled",Toast.LENGTH_LONG).show();
            flag=false;
            return;
        }

        if ((phoneNum.length()>14)){
            Toast.makeText(getApplicationContext(),"the entered phone number is too long",Toast.LENGTH_LONG).show();
            flag=false;
            return;
        }



        //////

        ContentValues business = new ContentValues();
        business.put("IDN", IDN);
        business.put("name", name);
        business.put("country", country);
        business.put("city", city);
        business.put("street", street);
        business.put("housenum", housenum);
        business.put("phonenum", phoneNum);
        business.put("email", email);
        business.put("site", site);
        business.put("user", user);

        AsyncInsert dbInsert = new AsyncInsert();
        dbInsert.execute( ContentProvide.BUSINESS_URI, business, this);

        Intent myIntent = new Intent(this, AddActionWindow.class);//get as moved automatically to AddAction process
        myIntent.putExtra("user", user);
        startActivity(myIntent);
    }
}
