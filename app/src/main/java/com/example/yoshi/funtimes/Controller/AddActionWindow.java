package com.example.yoshi.funtimes.Controller;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.yoshi.funtimes.Model.Backend.AsyncInsert;
import com.example.yoshi.funtimes.Model.DataSources.ContentProvide;
import com.example.yoshi.funtimes.Model.Entitites.Action;
import com.example.yoshi.funtimes.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Random;

import static com.example.yoshi.funtimes.Controller.AddBusinessWindow.IDNrange;

/*
* This class defines the add action window in the first app which bounded to add_action_window.XML
* */
public class AddActionWindow extends Activity {

    Spinner spinnerAction;
    Action.Attraction attraction;
    Spinner spinnerBusiness;

    String country;
    Date startDate;
    Date endDate;
    double price;
    String description;
    int IDN;

    Calendar myCalendar = Calendar.getInstance();//[its a var that needed for our date picker]

    EditText startEdit, endEdit;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private int TextIDClicked;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        String user = getIntent().getStringExtra("user");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_action_window);
        setTitle("Add activity");
        startEdit=(EditText)findViewById(R.id.startEdit);
        endEdit=(EditText)findViewById(R.id.endEdit);


        datePickerPopup();
        //region Attraction Spinner Create
        spinnerAction = (Spinner) findViewById(R.id.chooseSpinner);
        List<String> list = new ArrayList<>();
        list.add("HOTEL");
        list.add("TRAVEL_AGENCY");
        list.add("ENTERTAINMENT");
        list.add("FLIGHTS");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<>
                (this, android.R.layout.simple_spinner_item, list);

        dataAdapter.setDropDownViewResource
                (android.R.layout.simple_spinner_dropdown_item);

        spinnerAction.setAdapter(dataAdapter);

        Cursor myCursor = getContentResolver().query(
                ContentProvide.BUSINESS_URI,
                null,
                user,
                null,
                null
        );
        spinnerBusiness = (Spinner) findViewById(R.id.spinnerBusiness);
        List<String> businessList = new ArrayList<>();
        myCursor.moveToFirst();
        while(!myCursor.isAfterLast()){
            businessList.add(myCursor.getString(1));
            myCursor.moveToNext();
        }
        ArrayAdapter<String> newDataAdapter = new ArrayAdapter<>
                (this, android.R.layout.simple_spinner_item, businessList);
        newDataAdapter.setDropDownViewResource
                (android.R.layout.simple_spinner_dropdown_item);

        spinnerBusiness.setAdapter(newDataAdapter);

        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,android.R.layout.simple_dropdown_item_1line,COUNTRIES);

        AutoCompleteTextView textView=(AutoCompleteTextView)
                findViewById(R.id.countryEdit);
        textView.setAdapter(adapter);
        //endregion
    }



    //region Countries list which going to be chosen from auto-complete text
    public static final String[] COUNTRIES = new String[]{

            "Afghanistan",
            "Albania",
            "Algeria",
            "American Samoa",
            "Andorra",
            "Angola",
            "Anguilla",
            "Antigua and Barbuda",
            "Argentina",
            "Armenia",
            "Aruba",
            "Australia",
            "Austria",
            "Azerbaijan",
            "Bahamas",
            "Bahrain",
            "Bangladesh",
            "Barbados",
            "Belarus",
            "Belgium",
            "Belize",
            "Benin",
            "Bermuda",
            "Bhutan",
            "Bolivia",
            "Bonaire",
            "Bosnia-Herzegovina",
            "Botswana",
            "Bouvet Island",
            "Brazil",
            "Brunei",
            "Bulgaria",
            "Burkina Faso",
            "Burundi",
            "Cambodia",
            "Cameroon",
            "Canada",
            "Cape Verde",
            "Cayman Islands",
            "Central African Republic",
            "Chad",
            "Chile",
            "China",
            "Christmas Island",
            "Cocos (Keeling) Islands",
            "Colombia",
            "Comoros",
            "Congo, Democratic Republic of the (Zaire)",
            "Congo, Republic of",
            "Cook Islands",
            "Costa Rica",
            "Croatia",
            "Cuba",
            "Curacao",
            "Cyprus",
            "Czech Republic",
            "Denmark",
            "Djibouti",
            "Dominica",
            "Dominican Republic",
            "Ecuador",
            "Egypt",
            "El Salvador",
            "Equatorial Guinea",
            "Eritrea",
            "Estonia",
            "Ethiopia",
            "Falkland Islands",
            "Faroe Islands",
            "Fiji",
            "Finland",
            "France",
            "French Guiana",
            "Gabon",
            "Gambia",
            "Georgia",
            "Germany",
            "Ghana",
            "Gibraltar",
            "Greece",
            "Greenland",
            "Grenada",
            "Guadeloupe (French)",
            "Guam (USA)",
            "Guatemala",
            "Guinea",
            "Guinea Bissau",
            "Guyana",
            "Haiti",
            "Holy See",
            "Honduras",
            "Hong Kong",
            "Hungary",
            "Iceland",
            "India",
            "Indonesia",
            "Iran",
            "Iraq",
            "Ireland",
            "Israel",
            "Italy",
            "Ivory Coast (Cote D`Ivoire)",
            "Jamaica",
            "Japan",
            "Jordan",
            "Kazakhstan",
            "Kenya",
            "Kiribati",
            "Kosovo",
            "Kuwait",
            "Kyrgyzstan",
            "Laos",
            "Latvia",
            "Lebanon",
            "Lesotho",
            "Liberia",
            "Libya",
            "Liechtenstein",
            "Lithuania",
            "Luxembourg",
            "Macau",
            "Macedonia",
            "Madagascar",
            "Malawi",
            "Malaysia",
            "Maldives",
            "Mali",
            "Malta",
            "Marshall Islands",
            "Martinique (French)",
            "Mauritania",
            "Mauritius",
            "Mayotte",
            "Mexico",
            "Micronesia",
            "Moldova",
            "Monaco",
            "Mongolia",
            "Montenegro",
            "Montserrat",
            "Morocco",
            "Mozambique",
            "Myanmar",
            "Namibia",
            "Nauru",
            "Nepal",
            "Netherlands",
            "Netherlands Antilles",
            "New Caledonia (French)",
            "New Zealand",
            "Nicaragua",
            "Niger",
            "Nigeria",
            "Niue",
            "Norfolk Island",
            "North Korea",
            "Northern Mariana Islands",
            "Norway",
            "Oman",
            "Pakistan",
            "Palau",
            "Panama",
            "Papua New Guinea",
            "Paraguay",
            "Peru",
            "Philippines",
            "Pitcairn Island",
            "Poland",
            "Polynesia (French)",
            "Portugal",
            "Puerto Rico",
            "Qatar",
            "Reunion",
            "Romania",
            "Russia",
            "Rwanda",
            "Saint Helena",
            "Saint Kitts and Nevis",
            "Saint Lucia",
            "Saint Pierre and Miquelon",
            "Saint Vincent and Grenadines",
            "Samoa",
            "San Marino",
            "Sao Tome and Principe",
            "Saudi Arabia",
            "Senegal",
            "Serbia",
            "Seychelles",
            "Sierra Leone",
            "Singapore",
            "Sint Maarten",
            "Slovakia",
            "Slovenia",
            "Solomon Islands",
            "Somalia",
            "South Africa",
            "South Georgia and South Sandwich Islands",
            "South Korea",
            "South Sudan",
            "Spain",
            "Sri Lanka",
            "Sudan",
            "Suriname",
            "Svalbard and Jan Mayen Islands",
            "Swaziland",
            "Sweden",
            "Switzerland",
            "Syria",
            "Taiwan",
            "Tajikistan",
            "Tanzania",
            "Thailand",
            "Timor-Leste (East Timor)",
            "Togo",
            "Tokelau",
            "Tonga",
            "Trinidad and Tobago",
            "Tunisia",
            "Turkey",
            "Turkmenistan",
            "Turks and Caicos Islands",
            "Tuvalu",
            "Uganda",
            "Ukraine",
            "United Arab Emirates",
            "United Kingdom",
            "United States",
            "Uruguay",
            "Uzbekistan",
            "Vanuatu",
            "Venezuela",
            "Vietnam",
            "Virgin Islands",
            "Wallis and Futuna Islands",
            "Yemen",
            "Zambia",
            "Zimbabwe"};
    //endregion


    //those are functions that needed to be here so the datePicker will work
    private void datePickerPopup() {
        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel(TextIDClicked);
            }

        };

        startEdit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new DatePickerDialog(AddActionWindow.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
                TextIDClicked = startEdit.getId();
            }
        });
        endEdit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new DatePickerDialog(AddActionWindow.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
                TextIDClicked = endEdit.getId();
            }
        });
    }

    private void updateLabel(int TVID){
        String format = "dd/MM/yyyy";
        SimpleDateFormat sdf = new SimpleDateFormat(format,Locale.US);

        if(TVID == startEdit.getId())
            startEdit.setText(sdf.format(myCalendar.getTime()));
        else
            endEdit.setText(sdf.format(myCalendar.getTime()));
    }

    //this is a function that bounds with the XML
    public void addActionClick(View view) throws Exception {

        String myFormat = "dd/MM/yyyy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        //Attraction
        Spinner Attraction = (Spinner) findViewById(R.id.chooseSpinner);
        //Country
        EditText Country = (EditText) findViewById(R.id.countryEdit);
        //StartDate
        EditText StartDate = (EditText) findViewById(R.id.startEdit);
        //EndDate
        EditText EndDate = (EditText) findViewById(R.id.endEdit);
        //Price
        EditText Price = (EditText) findViewById(R.id.priceEdit);
        //Description
        EditText Description = (EditText) findViewById(R.id.descriptEdit);
        //business
        Spinner Business = (Spinner) findViewById(R.id.spinnerBusiness);
        if (preCheck(Country, StartDate, EndDate, Price)) return;
        boolean flag;


        attraction = Action.Attraction.valueOf(Attraction.getSelectedItem().toString());

        country = Country.getText().toString();

        startDate = sdf.parse(StartDate.getText().toString());

        endDate = sdf.parse(EndDate.getText().toString());

        price = Double.parseDouble(Price.getText().toString());

        description = Description.getText().toString();

        //post conditions

        if(startDate.getTime()> endDate.getTime()){
            Toast.makeText(getApplicationContext(),"start date can not be after end date",Toast.LENGTH_LONG).show();
            flag=false;
            return;
        }
        if((price>10000000)||(price<0)){
            Toast.makeText(getApplicationContext(),"the price of the activity must be positive and smaller than 10 million ",Toast.LENGTH_LONG).show();
            flag=false;
            return;
        }

        /*
        For IDN I will get same IDN as business it was created for
         */

        Cursor myCursor;
        myCursor = getContentResolver().query(
                ContentProvide.BUSINESS_URI,
                null,
                Business.getSelectedItem().toString(),
                null,
                null);

        myCursor.moveToFirst();

        IDN = myCursor.getInt(0);
        String user = getIntent().getStringExtra("user");

        ContentValues action = new ContentValues();
        action.put("attraction", String.valueOf(attraction));
        action.put("country", country);
        action.put("startdate", sdf.format(startDate));
        action.put("enddate", sdf.format(endDate));
        action.put("price", price);
        action.put("description", description);
        action.put("IDN", IDN);
        action.put("user", user);

        AsyncInsert dbInsert = new AsyncInsert();
        dbInsert.execute( ContentProvide.ACTION_URI, action, this);

        Intent myIntent = new Intent(this, AddWindow.class);
        myIntent.putExtra("user",user);

        startActivity(myIntent);
    }

    private boolean preCheck(EditText country, EditText startDate, EditText endDate, EditText price) {
        //check
        boolean flag=true;

        //country check
        for (String s:
                COUNTRIES) {
            if(!(country.getText().toString().equals(s))) {
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
            return true;
        }
        //Date check
        if(startDate.getText().toString().equals("")){
            Toast.makeText(getApplicationContext(),"start date was not filled",Toast.LENGTH_LONG).show();
            flag=false;
            return true;
        }

        if(endDate.getText().toString().equals("")){
            Toast.makeText(getApplicationContext(),"end date was not filled",Toast.LENGTH_LONG).show();
            flag=false;
            return true;
        }

        //Price check
        if(price.getText().toString().equals("")){
            Toast.makeText(getApplicationContext(),"price was not filled",Toast.LENGTH_LONG).show();
            flag=false;
            return true;
        }
        return false;
    }
}

