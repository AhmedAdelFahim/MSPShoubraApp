package com.msp.mspshoubraapp.ui;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.msp.mspshoubraapp.Constants;
import com.msp.mspshoubraapp.R;

public class AboutDevelopersActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    ImageView adelLI, adelGH, lailaLI, lailaGH, massoudLI, massoudGH;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_developers);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        adelLI = findViewById(R.id.AdelLI);
        adelGH = findViewById(R.id.AdelGH);
        lailaLI = findViewById(R.id.LailaLI);
        lailaGH = findViewById(R.id.LailaGH);
        massoudLI = findViewById(R.id.MassoudLI);
        massoudGH = findViewById(R.id.MassoudGH);


        adelLI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(Constants.AHMED_LINKEDIN));
                startActivity(i);
            }
        });

        adelGH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(Constants.AHMED_GIT));
                startActivity(i);
            }
        });

        lailaLI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(Constants.LAILA_LINKEDIN));
                startActivity(i);
            }
        });

        lailaGH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(Constants.LAILA_GIT));
                startActivity(i);
            }
        });

        massoudLI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(Constants.MOHAMMAD_LINKEDIN));
                startActivity(i);
            }
        });

        massoudGH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(Constants.MOHAMMAD_GIT));
                startActivity(i);
            }
        });

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
        Intent intent = null;
        switch (item.getItemId()) {
            case R.id.nav_food:

                intent = new Intent(this, FoodActivity.class);
                finish();
                break;

            case R.id.nav_lec_table:
                intent = new Intent(this, LecTableActivity.class);
                intent.putExtra("title", "Lecture Table");
                finish();
                break;

            case R.id.nav_coworkingspaces:
                intent = new Intent(this, CoworkingSpacesActivity.class);
                finish();
                break;

            case R.id.nav_studentactivities:
                intent = new Intent(this, StudentActivitiesActivity.class);
                finish();
                break;

            case R.id.nav_subjects:
                intent = new Intent(this, SubjectsActivity.class);
                finish();
                break;

            case R.id.nav_privacypolicy:
                intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(Constants.URL_PRIVACYPOLICY));
                break;
            case R.id.nav_materials:
                intent = new Intent(this, MaterialsActivity.class);
                finish();
                break;

        }

        if (intent != null) {
            startActivity(intent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
