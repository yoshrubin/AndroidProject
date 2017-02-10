package com.example.userside.Model;

import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.userside.Backend.DB.TimeUpdate;
import com.example.userside.Backend.Factory.Backend;
import com.example.userside.Backend.Factory.BackendFactory;
import com.example.userside.Backend.adapters.PublicObjects;
import com.example.userside.R;

import java.text.ParseException;


public class secondAppActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    public static Context context;
    private SearchView searchView;
    private Backend db;
    private final TimeUpdate update = new TimeUpdate();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        PublicObjects.start = this;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_app);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        searchView = (SearchView) findViewById(R.id.searchView);
        setSupportActionBar(toolbar);
        context = getApplicationContext();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public boolean onQueryTextSubmit(String query) {
                if (PublicObjects.BussFrag != null) {
                    android.app.Fragment current = PublicObjects.currentFrag;
                    //found it business
                    if (current.getId() == PublicObjects.BussFrag.getId()) {
                        //resetting the list
                        //PublicObjects.BussFrag.updateView();
                        PublicObjects.BussFrag.clearFilter();
                        PublicObjects.BussFrag.Filter(query.toString());
                        return true;
                    }
                }
                if (PublicObjects.AttFrag != null) {
                    android.app.Fragment current = PublicObjects.currentFrag;
                    if (current.getId() == PublicObjects.AttFrag.getId()) {
                        //resetting the list
                        //PublicObjects.AttFrag.updateView();
                        PublicObjects.AttFrag.clearFilter();
                        PublicObjects.AttFrag.Filter(query.toString());
                        return true;
                    }
                }
                Snackbar.make(searchView, "Please Select a category from the Notification Drawer", Snackbar.LENGTH_LONG);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (newText.isEmpty()) {
                    if (PublicObjects.currentFrag.equals(PublicObjects.BussFrag) && PublicObjects.BussFrag != null)
                        PublicObjects.BussFrag.clearFilter();
                    if (PublicObjects.currentFrag.equals(PublicObjects.AttFrag) && PublicObjects.AttFrag != null)
                        PublicObjects.AttFrag.clearFilter();
                }
                return true;
            }
        });

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        context = this;
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        db = BackendFactory.getFactoryDatabase();
        try {
            db.setUpDatabase();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        update.execute();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.second_app, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        /*
        if (id == R.id.action_settings) {
            return true;
        }
        */

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();


        if (id == R.id.nav_agencies) {

            setTitle("All the agencies");
            android.app.Fragment f4 = PublicObjects.getBusinessFragment();
            FragmentTransaction ft = getFragmentManager().beginTransaction();
            ft.replace(R.id.content_second_app, f4); // f2_container is your FrameLayout container
            //Toast.makeText(getApplicationContext(), "I'M the agencies fragment", Toast.LENGTH_LONG).show();
            ft.commit();
            PublicObjects.currentFrag =  f4;




        } else if (id == R.id.nav_trips) {
            android.app.Fragment f5 = PublicObjects.getAttractionFragment();
            FragmentTransaction ft = getFragmentManager().beginTransaction();
            setTitle("All the trips");
            ft.replace(R.id.content_second_app, f5); // f2_container is your FrameLayout container
            //Toast.makeText(getApplicationContext(), "I'M the Trip fragment", Toast.LENGTH_LONG).show();
            ft.commit();
            PublicObjects.currentFrag =  f5;

        } else if (id == R.id.nav_exit) {

            Toast.makeText(getApplicationContext(), "see you later", Toast.LENGTH_LONG).show();
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            this.finish();
            //this.onDestroy();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;


    }

}
