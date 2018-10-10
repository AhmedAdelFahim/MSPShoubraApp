package com.msp.mspshoubraapp.ui;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.msp.mspshoubraapp.R;
import com.msp.mspshoubraapp.adapter.FragmentAdapter;
import com.msp.mspshoubraapp.networking.ConnectivityStatus;
import com.msp.mspshoubraapp.networking.FetchDataFromApi;

import java.util.ArrayList;

public class LecTableActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private FragmentAdapter fragmentAdapter;
    private ViewPager mViewPager;
    private TabLayout tabLayout;
    private Toolbar toolbar;
    private DrawerLayout drawer;
    private NavigationView navigationView;
    private String groupNum = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_lec_table);

        toolbar = findViewById(R.id.toolbar);
        mViewPager = findViewById(R.id.container);
        drawer = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        tabLayout = findViewById(R.id.tabs);
        drawer = findViewById(R.id.drawer_layout);

        //String title = getIntent().getStringExtra("title");
        //getSupportActionBar().setTitle(title);
        setSupportActionBar(toolbar);
        SharedPreferences sharedPreferences = getSharedPreferences(getResources().getString(R.string.msp_preferences), MODE_PRIVATE);
        if (sharedPreferences.getBoolean(getResources().getString(R.string.lecturesTable), true)) {
            if (ConnectivityStatus.isConnected(this)) {
                FetchDataFromApi.loadLecturesTable(this, false);
            } else {
                Toast.makeText(this, "No Internet Connection", Toast.LENGTH_SHORT).show();
            }

        }


        //sharedPreferences = getSharedPreferences(getResources().getString(R.string.msp_preferences), MODE_PRIVATE);
        groupNum = sharedPreferences.getString(getResources().getString(R.string.group_num), "");
        if (groupNum.equals("")) {
            //FetchDataFromApi.loadLecturesTable(this,false);
            chooseGroupDialog();
        } else {
            getSupportActionBar().setTitle("Lecture Table " + groupNum);
        }
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chooseGroupDialog();
            }
        });

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        fragmentAdapter = new FragmentAdapter(getSupportFragmentManager()/*, this*/);


        // Set up the ViewPager with the sections adapter.
        mViewPager.setAdapter(fragmentAdapter);


        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mViewPager));
    }

    private void chooseGroupDialog() {
        final AlertDialog.Builder alertDialog = new AlertDialog.Builder(LecTableActivity.this);
        View alertDialogView = getLayoutInflater().inflate(R.layout.change_group_dialog, null);
        alertDialog.setView(alertDialogView);
        final AlertDialog alert = alertDialog.create();
        final Spinner spinnerGroup = alertDialogView.findViewById(R.id.spinner_group);
        Button ok = alertDialogView.findViewById(R.id.change_group_btn);
        ArrayList<Integer> groups = new ArrayList<>();
        for (int i = 1; i <= 6; i++) {
            groups.add(i);
        }
        ArrayAdapter<Integer> groupAdapter = new ArrayAdapter<Integer>(LecTableActivity.this, android.R.layout.simple_spinner_item, groups);
        groupAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerGroup.setAdapter(groupAdapter);

        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                groupNum = "Group" + spinnerGroup.getSelectedItem();
                SharedPreferences sharedPreferences = getSharedPreferences(getResources().getString(R.string.msp_preferences), MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString(getResources().getString(R.string.group_num), groupNum);
                editor.apply();
                getSupportActionBar().setTitle("Lecture Table " + groupNum);
                alert.dismiss();
                Intent intent = getIntent();
                finish();
                startActivity(intent);

            }
        });

        alert.show();
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

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        Intent intent;
        switch (item.getItemId()) {
            case R.id.nav_food:
                intent = new Intent(this, FoodActivity.class);
                intent.putExtra("nextFregment", 3);
                finish();
                startActivity(intent);

                break;
            case R.id.nav_coworkingspaces:
                intent = new Intent(this, CoworkingSpacesActivity.class);
                intent.putExtra("nextFregment", 4);
                finish();
                startActivity(intent);
                break;
            case R.id.nav_studentactivities:
                intent = new Intent(this, StudentActivitiesActivity.class);
                intent.putExtra("nextFregment", 5);
                finish();
                startActivity(intent);
                break;
            case R.id.nav_materials:
                intent = new Intent(this, MaterialsActivity.class);
                finish();
                startActivity(intent);

                break;
            case R.id.nav_subjects:
                intent = new Intent(this, SubjectsActivity.class);
                finish();
                startActivity(intent);
                break;
           /* case R.id.nav_tools:
                intent = new Intent(this, GalleryActivity.class);
                intent.putExtra("title", "Tools");
                startActivity(intent);
                break;*/
            case R.id.nav_lec_table:
                /*intent = new Intent(this, LecTableActivity.class);
                intent.putExtra("title", "Lecture");
                startActivity(intent);*/
                break;
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


}
