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
import com.msp.mspshoubraapp.adapter.StudentActivityRecyclerviewAdapter;
import com.msp.mspshoubraapp.data.StudentActivityListItem;

import java.util.ArrayList;

public class StudentActivitiesActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,
        StudentActivityRecyclerviewAdapter.listItemClickListener {

    private RecyclerView recyclerView;
    private StudentActivityRecyclerviewAdapter adapter;
    private ArrayList<StudentActivityListItem> itemList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_activities);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        itemList.add(new StudentActivityListItem("IEEE Cairo University Student Branch", "https://scontent-cai1-1.xx.fbcdn.net/v/t1.0-9/12348129_935001243231666_5803219402411515065_n.jpg?_nc_cat=0&oh=5a6e134bfe4cd24596afad0e657fe7ab&oe=5C30762C"));
        recyclerView = findViewById(R.id.studentactivitiesCustomRecycleview);
        adapter = new StudentActivityRecyclerviewAdapter(this, itemList, this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);
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
            case R.id.nav_tools:
                intent = new Intent(this, GalleryActivity.class);
                intent.putExtra("title", "Tools");
                //startActivity(intent);
                //currFragment=4;
                break;
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

            /*case R.id.nav_studentactivities:
                intent = new Intent(this, StudentActivitiesActivity.class);
                //intent.putExtra("title", "Tools");
                //startActivity(intent);
                break;*/
            case R.id.nav_subjects:
                intent = new Intent(this, SubjectsActivity.class);
                //intent.putExtra("title", "Tools");
                //startActivity(intent);
                break;
        }

        if (intent != null) {
            startActivity(intent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onListItemClick(int clickedItemIndex) {
        Intent intent = new Intent(this, StudentActivitiesActivityDetails.class);

        startActivity(intent);
    }
}