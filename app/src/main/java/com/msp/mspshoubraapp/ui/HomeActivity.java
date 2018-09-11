package com.msp.mspshoubraapp.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;

import com.msp.mspshoubraapp.R;
import com.msp.mspshoubraapp.TablesFragment;

public class HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private int currFragment=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        //Fresco.initialize(this);
        Toolbar toolbar = findViewById(R.id.toolbar);
        //toolbar.setTitleTextAppearance(this,R.id.tvTitle);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        currFragment=getIntent().getIntExtra("nextFregment",0);

        if (currFragment == 0) {
            loadFragment(new NewsFragment());
        } else if (currFragment == 3) {
            loadFragment(new FoodFragment());
        } else if (currFragment == 4) {
            loadFragment(new CoworkingSpacesFragment());
        } else if (currFragment == 5) {
            loadFragment(new StudentActivityFragment());
        } else if(currFragment == 6)
            loadFragment(new SubjectsFragment());
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
                            Log.d("QWERTYUI", getSupportFragmentManager().getBackStackEntryCount() + "");
                            currFragment=0;
                        }
                        break;

                    case R.id.navigation_map:
                        if(currFragment!=1) {
                            fragment = new MapFragment();
                            getSupportActionBar().setTitle("Map");
                            Log.d("QWERTYUI", getSupportFragmentManager().getBackStackEntryCount() + "");
                            currFragment=1;
                        }
                        break;

                    case R.id.navigation_tables:
                        if(currFragment!=2) {
                            fragment = new TablesFragment();
                            getSupportActionBar().setTitle("Table");
                            Log.d("QWERTYUI", getSupportFragmentManager().getBackStackEntryCount() + "");
                            currFragment=2;
                        }
                        break;


                }

                FragmentManager fragmentManager = getSupportFragmentManager();
                if (fragmentManager.getBackStackEntryCount() >= 1) {
                    fragmentManager.popBackStack("foodFragment", FragmentManager.POP_BACK_STACK_INCLUSIVE);
                    fragmentManager.popBackStack("menuFragment", FragmentManager.POP_BACK_STACK_INCLUSIVE);
                }
                return loadFragment(fragment);
            }
        });
    }

    @Override
    public void onBackPressed() {
        FragmentManager fragments = getSupportFragmentManager();
        //Fragment homeFrag = fragments.findFragmentByTag("food");

        if (fragments.getBackStackEntryCount() > 1) {
            // We have fragments on the backstack that are poppable
            fragments.popBackStackImmediate();
        }/* else if (homeFrag == null || !homeFrag.isVisible()) {
            // We aren't showing the home screen, so that is the next stop on the back journey
            nav.setCurrentItem(0);
        } else {
            // We are already showing the home screen, so the next stop is out of the app.
            supportFinishAfterTransition();
        }*/
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
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
        Intent intent;
        switch (item.getItemId()) {
            case R.id.nav_food:
                if(currFragment!=3) {
                    fragment = new FoodFragment();
                    //addToStack = true;
                    getSupportActionBar().setTitle("Food");
                    Log.d("QWERTYUI", getSupportFragmentManager().getBackStackEntryCount() + "");
                    currFragment=3;
                }
                break;
            case R.id.nav_tools:
                intent = new Intent(this, GalleryActivity.class);
                intent.putExtra("title", "Tools");
                startActivity(intent);
                //currFragment=4;
                break;
            case R.id.nav_lec_table:
                intent = new Intent(this, LecTableActivity.class);
                intent.putExtra("title", "Lecture Table");
                startActivity(intent);
                //currFragment=4;
                break;

            case R.id.nav_coworkingspaces:
                if(currFragment!=4) {
                    fragment = new CoworkingSpacesFragment();
                    //addToStack = true;
                    getSupportActionBar().setTitle("Co-Working Spaces");
                    //Log.d("QWERTYUI", getSupportFragmentManager().getBackStackEntryCount() + "");
                    currFragment=4;
                }
                break;

            case R.id.nav_studentactivities:
                if(currFragment!=5) {
                    fragment = new StudentActivityFragment();
                    //addToStack = true;
                    getSupportActionBar().setTitle("Student Activities");
                    //Log.d("QWERTYUI", getSupportFragmentManager().getBackStackEntryCount() + "");
                    currFragment=5;
                }
                break;
            case R.id.nav_subjects:
                if(currFragment!=6) {
                    fragment = new SubjectsFragment();
                    //addToStack = true;
                    getSupportActionBar().setTitle("Subjects");
                    //Log.d("QWERTYUI", getSupportFragmentManager().getBackStackEntryCount() + "");
                    currFragment=6;
                }
                break;



        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return loadFragment(fragment);
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
