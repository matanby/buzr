package com.example.iraltman.buzr;

import android.app.ActionBar;
import android.app.ActivityManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.graphics.DrawFilter;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private int activeId;

    private BroadcastReceiver locationReceiver;
    public String locationId = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        Bundle b = getIntent().getExtras();
        if(b != null && b.getString("locationId") != null){
            String locationId = b.getString("locationId");
            Log.i("Whos dat?!?!", locationId);
        }


        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#60C7F4")));

        if(findViewById(R.id.container) != null){
            DealsFragment masterFragment = new DealsFragment();

            getSupportFragmentManager().beginTransaction().add(R.id.container, masterFragment).commit();
            activeId = R.id.nav_hot_deals;
        }

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setItemIconTintList(null);
        navigationView.setNavigationItemSelectedListener(this);

        registerBroadcastReceivers();
        if (! isMyServiceRunning(WifiScanService.class)){
            Intent intent = new Intent(this, WifiScanService.class);
            startService(intent);
        }
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
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);

//        menu.getItem(0).setIcon(R.drawable.food_pink);


        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_shop) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_hot_deals) {
            DealsFragment masterFragment = new DealsFragment();

            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

            transaction.replace(R.id.container, masterFragment);
            transaction.addToBackStack(null);
            transaction.commit();
            activeId = R.id.nav_hot_deals;

        } else if (id == R.id.fashion) {
            CategoryDealsFragment fashionFragment = CategoryDealsFragment.newInstance(2, R.string.fashion);

            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

            transaction.replace(R.id.container, fashionFragment);
            transaction.addToBackStack(null);
            transaction.commit();
            activeId = R.id.fashion;


        } else if (id == R.id.shoes) {
            CategoryDealsFragment shoesFragment = CategoryDealsFragment.newInstance(3, R.string.shoes);

            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

            transaction.replace(R.id.container, shoesFragment);
            transaction.addToBackStack(null);
            transaction.commit();
            activeId = R.id.shoes;

        } else if (id == R.id.accessories) {
            CategoryDealsFragment accessoriesFragment = CategoryDealsFragment.newInstance(4, R.string.accessories);

            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

            transaction.replace(R.id.container, accessoriesFragment);
            transaction.addToBackStack(null);
            transaction.commit();
            activeId = R.id.accessories;

        } else if (id == R.id.home_furnishings) {
            CategoryDealsFragment home_furnishingsFragment = CategoryDealsFragment.newInstance(5, R.string.home_furnishings);

            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

            transaction.replace(R.id.container, home_furnishingsFragment);
            transaction.addToBackStack(null);
            transaction.commit();
            activeId = R.id.home_furnishings;

        } else if (id == R.id.beauty_salons_health) {
            CategoryDealsFragment beauty_salons_healthFragment = CategoryDealsFragment.newInstance(6, R.string.beauty_salons_and_health);

            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

            transaction.replace(R.id.container, beauty_salons_healthFragment);
            transaction.addToBackStack(null);
            transaction.commit();
            activeId = R.id.beauty_salons_health;

        } else if (id == R.id.electronics) {
            CategoryDealsFragment electronicsFragment = CategoryDealsFragment.newInstance(7, R.string.electronics);

            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

            transaction.replace(R.id.container, electronicsFragment);
            transaction.addToBackStack(null);
            transaction.commit();
            activeId = R.id.electronics;

        }  else if (id == R.id.food_and_drinks) {
            CategoryDealsFragment food_and_drinksFragment = CategoryDealsFragment.newInstance(8, R.string.food_and_drinks);

            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

            transaction.replace(R.id.container, food_and_drinksFragment);
            transaction.addToBackStack(null);
            transaction.commit();
            activeId = R.id.food_and_drinks;

        } else if (id == R.id.all_stores) {
            CategoryDealsFragment all_storesFragment = CategoryDealsFragment.newInstance(1, R.string.all_stores);

            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

            transaction.replace(R.id.container, all_storesFragment);
            transaction.addToBackStack(null);
            transaction.commit();
            activeId = R.id.all_stores;

        } else if (id == R.id.map) {
            StoresFragment mapFragment = new StoresFragment();
            mapFragment.setArguments(20);

            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

            transaction.replace(R.id.container, mapFragment);
            transaction.addToBackStack(null);
            transaction.commit();
            activeId = R.id.map;

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private boolean isMyServiceRunning(Class<?> serviceClass) {
        ActivityManager manager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
            if (serviceClass.getName().equals(service.service.getClassName())) {
                return true;
            }
        }
        return false;
    }

    private void registerBroadcastReceivers() {
        final MainActivity parentActivity = this;
        this.locationReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                Log.i("MainActivity", "Setting locationId: " + parentActivity.locationId);
                parentActivity.locationId = intent.getStringExtra("location_id");
                if (parentActivity.activeId == R.id.nav_hot_deals) {
                    parentActivity.refreshHotDeals();
                }
            }
        };
        IntentFilter locationFilter = new IntentFilter("LocationId");
        registerReceiver(this.locationReceiver, locationFilter);
    }

    private void refreshHotDeals() {
        DealsFragment masterFragment = new DealsFragment();

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        transaction.replace(R.id.container, masterFragment);
        transaction.addToBackStack(null);
        transaction.commit();
        activeId = R.id.nav_hot_deals;
    }
}
