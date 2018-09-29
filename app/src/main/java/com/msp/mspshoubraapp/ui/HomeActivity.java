package com.msp.mspshoubraapp.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.msp.mspshoubraapp.R;
import com.msp.mspshoubraapp.SetBackgroundJobs;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private int currFragment = 0, previousActivity = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        SetBackgroundJobs.updateNewsFeed(this);
        SetBackgroundJobs.updateCoworkingSpaces(this);
        SetBackgroundJobs.updateLectureTable(this);
        SetBackgroundJobs.updateRestaurants(this);
        SetBackgroundJobs.updateStudentActivity(this);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        currFragment = getIntent().getIntExtra("nextFregment", 0);
        if (getIntent().hasExtra("previousActivity")) {
            previousActivity = getIntent().getIntExtra("previousActivity", 0);
        }

        if (currFragment == 0) {
            loadFragment(new NewsFragment());
        } else if (currFragment == 1) {
            loadFragment(new MapFragment());
        }

        BottomNavigationView navigation = findViewById(R.id.bottom_nav);
        navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment = null;

                switch (item.getItemId()) {
                    case R.id.navigation_news:
                        if(currFragment!=0){
                            fragment = new NewsFragment();
                            getSupportActionBar().setTitle("News Feed");
                            currFragment=0;
                        }
                        break;

                    case R.id.navigation_map:
                        if(currFragment!=1) {
                            fragment = new MapFragment();
                            getSupportActionBar().setTitle("Map");
                            currFragment=1;
                        }
                        break;

                    case R.id.navigation_tables:
                        if(currFragment!=2) {
                            Calendar calendar = Calendar.getInstance();
                            Date date = calendar.getTime();
                            Bundle bundle = new Bundle();
                            bundle.putString("day", new SimpleDateFormat("EE", Locale.ENGLISH).format(date.getTime()));
                            fragment = new TablesFragment();
                            getSupportActionBar().setTitle("Today Table");
                            fragment.setArguments(bundle);
                            currFragment=2;
                        }
                        break;


                }
                return loadFragment(fragment);
            }
        });
    }

    @Override
    public void onBackPressed() {

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
            if (previousActivity == 3) {
                previousActivity = 0;
                finish();
                startActivity(new Intent(this, FoodActivity.class));
            } else if (previousActivity == 4) {
                previousActivity = 0;
                finish();
                //startActivity(new Intent(this,CoworkingSpacesActivity.class));
            }
        }
    }

   /* @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
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
    }*/

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        Fragment fragment = null;
        // boolean addToStack = false;
        int id = item.getItemId();
        Intent intent = null;
        switch (item.getItemId()) {
            case R.id.nav_food:

                intent = new Intent(this, FoodActivity.class);
                //intent.putExtra("title", "Tools");
                //startActivity(intent);
                break;
            /*case R.id.nav_tools:
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

        if (previousActivity == 3) {
            //previousActivity = 0;
            finish();
            startActivity(intent);
        } else if (previousActivity == 0) {
            startActivity(intent);
        } else if (previousActivity == 4) {
            finish();
            //startActivity(new Intent(this,CoworkingSpacesActivity.class));
        }
        previousActivity = 0;

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    private boolean loadFragment(Fragment fragment) {
        if (fragment != null) {

            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, fragment)
                    .commit();
            return true;


        }
        return false;
    }
}
