package com.msp.mspshoubraapp.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.msp.mspshoubraapp.R;
import com.msp.mspshoubraapp.adapter.SubjectsExpandableRecyclerviewAdapter;
import com.msp.mspshoubraapp.data.SubjectsDetailsListItem;
import com.msp.mspshoubraapp.data.SubjectsListItem;
import com.thoughtbot.expandablerecyclerview.ExpandableRecyclerViewAdapter;

import java.util.ArrayList;
import java.util.List;

public class SubjectsActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    RecyclerView subjectsRV;
    ExpandableRecyclerViewAdapter adapter;
    List<SubjectsListItem> subjectTitle;
    List<SubjectsDetailsListItem> subjectsDetailsListItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subjects);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        subjectsRV = findViewById(R.id.subjectsRV);
        subjectsRV.setLayoutManager(new LinearLayoutManager(this));

        subjectTitle = new ArrayList<>();
        subjectsDetailsListItems = new ArrayList<>();

        subjectTitle.clear();
        for (int i = 0; i < 13; i++) {
            subjectsDetailsListItems.clear();
            subjectsDetailsListItems.add(new SubjectsDetailsListItem("Midterm", "30"));
            subjectsDetailsListItems.add(new SubjectsDetailsListItem("Oral", "10"));
            subjectsDetailsListItems.add(new SubjectsDetailsListItem("Project", "20"));
            subjectsDetailsListItems.add(new SubjectsDetailsListItem("Final", "90"));
            subjectsDetailsListItems.add(new SubjectsDetailsListItem("Total", "150"));

            subjectTitle.add(new SubjectsListItem("Maths", subjectsDetailsListItems));
        }

        adapter = new SubjectsExpandableRecyclerviewAdapter(subjectTitle);
        subjectsRV.setAdapter(adapter);

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
        Intent intent = null;
        switch (item.getItemId()) {
            case R.id.nav_food:

                intent = new Intent(this, FoodActivity.class);
                //intent.putExtra("title", "Tools");
                //startActivity(intent);
                break;
           /* case R.id.nav_tools:
                intent = new Intent(this, GalleryActivity.class);
                intent.putExtra("title", "Tools");
                //startActivity(intent);
                //currFragment=4;
                break;*/
            case R.id.nav_lec_table:
                intent = new Intent(this, LecTableActivity.class);
                intent.putExtra("title", "Lecture Table");
                //startActivity(intent);
                //currFragment=4;
                break;

            case R.id.nav_coworkingspaces:
                intent = new Intent(this, CoworkingSpacesActivity.class);
                //intent.putExtra("title", "Tools");
                //startActivity(intent);

                break;

            case R.id.nav_studentactivities:
                intent = new Intent(this, StudentActivitiesActivity.class);
                //intent.putExtra("title", "Tools");
                //startActivity(intent);
                break;
            /*case R.id.nav_subjects:
                intent = new Intent(this, SubjectsActivity.class);
                //intent.putExtra("title", "Tools");
                //startActivity(intent);
                break;*/
        }

        if (intent != null) {
            startActivity(intent);
        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
