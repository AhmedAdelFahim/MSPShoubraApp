package com.msp.mspshoubraapp.ui;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Toast;

import com.msp.mspshoubraapp.Constants;
import com.msp.mspshoubraapp.R;
import com.msp.mspshoubraapp.adapter.CoworkingSpacesRecyclerviewAdapter;
import com.msp.mspshoubraapp.db.CoworkingSpaceEntity;
import com.msp.mspshoubraapp.networking.ConnectivityStatus;
import com.msp.mspshoubraapp.networking.FetchDataFromApi;
import com.msp.mspshoubraapp.viewmodel.CoworkingSpaceViewModel;

import java.util.ArrayList;
import java.util.List;

public class CoworkingSpacesActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, CoworkingSpacesRecyclerviewAdapter.listItemClickListener {

    private RecyclerView recyclerView;
    private CoworkingSpacesRecyclerviewAdapter adapter;
    private List<CoworkingSpaceEntity> itemList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coworking_spaces);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        SharedPreferences sharedPreferences = getSharedPreferences(getResources().getString(R.string.msp_preferences), MODE_PRIVATE);
        if (sharedPreferences.getBoolean(getResources().getString(R.string.coworkingSpace), true)) {
            if (ConnectivityStatus.isConnected(this)) {
                FetchDataFromApi.loadcoworkingSpaces(this, false);
            } else {
                Toast.makeText(this, "No Internet Connection", Toast.LENGTH_SHORT).show();
            }

        }

        recyclerView = findViewById(R.id.coworkingSpacesCustomRecycleview);
        adapter = new CoworkingSpacesRecyclerviewAdapter(this, itemList, this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);
        setupViewModel();
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
                finish();
                break;
            case R.id.nav_lec_table:
                intent = new Intent(this, LecTableActivity.class);
                intent.putExtra("title", "Lecture Table");
                finish();
                break;
            case R.id.nav_studentactivities:
                intent = new Intent(this, StudentActivitiesActivity.class);
                finish();
                break;
            case R.id.nav_materials:
                intent = new Intent(this, MaterialsActivity.class);
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
            case R.id.nav_about_dev:
                intent = new Intent(this, AboutDevelopersActivity.class);
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

    @Override
    public void onListItemClick(int clickedItemIndex) {
        Intent intent = new Intent(this, CoworkingSpacesActivityDetails.class);
        intent.putExtra("coworkingSpaces", itemList.get(clickedItemIndex));
        startActivity(intent);
    }

    private void setupViewModel() {
        CoworkingSpaceViewModel viewModel = ViewModelProviders.of(this).get(CoworkingSpaceViewModel.class);
        viewModel.getCoworkingSpaces().observe(this, new Observer<List<CoworkingSpaceEntity>>() {
            @Override
            public void onChanged(@Nullable List<CoworkingSpaceEntity> coworkingSpaceEntities) {
                itemList = coworkingSpaceEntities;
                adapter.setCoworkingSpaces(coworkingSpaceEntities);
            }
        });
    }

}
