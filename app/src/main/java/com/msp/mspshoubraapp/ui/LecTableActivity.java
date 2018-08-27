package com.msp.mspshoubraapp.ui;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
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

import com.msp.mspshoubraapp.R;
import com.msp.mspshoubraapp.adapter.FragmentAdapter;

import java.util.ArrayList;

public class LecTableActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private FragmentAdapter fragmentAdapter;
    private ViewPager mViewPager;
    private TabLayout tabLayout;
    private Toolbar toolbar;
    private DrawerLayout drawer;
    NavigationView navigationView;
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

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final AlertDialog.Builder alertDialog = new AlertDialog.Builder(LecTableActivity.this);
                View alertDialogView = getLayoutInflater().inflate(R.layout.change_group_dialog, null);

                Spinner spinnerGroup = alertDialogView.findViewById(R.id.spinner_group);
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

                    }
                });

                alertDialog.setView(alertDialogView);
                alertDialog.create().show();
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

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
/*

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.lec_table, menu);
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
*/

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        Intent intent;
        switch (item.getItemId()) {
            case R.id.nav_food:
                intent = new Intent(this, HomeActivity.class);
                intent.putExtra("nextFregment", 3);
                startActivity(intent);
//                fragment = new FoodFragment();
//                //addToStack = true;
//                getSupportActionBar().setTitle("Food");
//                Log.d("QWERTYUI", getSupportFragmentManager().getBackStackEntryCount() + "");
                break;
            case R.id.nav_tools:
                intent = new Intent(this, GalleryActivity.class);
                intent.putExtra("title", "Tools");
                startActivity(intent);
                break;
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
