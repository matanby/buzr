package com.example.iraltman.buzr;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.DrawFilter;
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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if(findViewById(R.id.container) != null){
            DealsFragment masterFragment = new DealsFragment();

            getSupportFragmentManager().beginTransaction().add(R.id.container, masterFragment).commit();
        }

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setItemIconTintList(null);
        navigationView.setNavigationItemSelectedListener(this);

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

        menu.getItem(0).setIcon(R.drawable.food_pink);


        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
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

        } else if (id == R.id.fashion) {
            CategoryDealsFragment fashionFragment = CategoryDealsFragment.newInstance(2);

            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

            transaction.replace(R.id.container, fashionFragment);
            transaction.addToBackStack(null);
            transaction.commit();


        } else if (id == R.id.shoes) {
            CategoryDealsFragment shoesFragment = CategoryDealsFragment.newInstance(3);

            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

            transaction.replace(R.id.container, shoesFragment);
            transaction.addToBackStack(null);
            transaction.commit();

        } else if (id == R.id.accessories) {
            CategoryDealsFragment accessoriesFragment = CategoryDealsFragment.newInstance(4);

            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

            transaction.replace(R.id.container, accessoriesFragment);
            transaction.addToBackStack(null);
            transaction.commit();

        } else if (id == R.id.home_furnishings) {
            CategoryDealsFragment home_furnishingsFragment = CategoryDealsFragment.newInstance(5);

            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

            transaction.replace(R.id.container, home_furnishingsFragment);
            transaction.addToBackStack(null);
            transaction.commit();

        } else if (id == R.id.beauty_salons_health) {
            CategoryDealsFragment beauty_salons_healthFragment = CategoryDealsFragment.newInstance(6);

            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

            transaction.replace(R.id.container, beauty_salons_healthFragment);
            transaction.addToBackStack(null);
            transaction.commit();

        } else if (id == R.id.electronics) {
            CategoryDealsFragment electronicsFragment = CategoryDealsFragment.newInstance(7);

            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

            transaction.replace(R.id.container, electronicsFragment);
            transaction.addToBackStack(null);
            transaction.commit();

        }  else if (id == R.id.food_and_drinks) {
            CategoryDealsFragment food_and_drinksFragment = CategoryDealsFragment.newInstance(8);

            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

            transaction.replace(R.id.container, food_and_drinksFragment);
            transaction.addToBackStack(null);
            transaction.commit();

        } else if (id == R.id.all_stores) {
            DealsFragment all_storesFragment = DealsFragment.newInstance();

            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

            transaction.replace(R.id.container, all_storesFragment);
            transaction.addToBackStack(null);
            transaction.commit();

        } else if (id == R.id.map) {
            StoresFragment mapFragment = new StoresFragment();
            mapFragment.setArguments(20);

            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

            transaction.replace(R.id.container, mapFragment);
            transaction.addToBackStack(null);
            transaction.commit();

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
}
