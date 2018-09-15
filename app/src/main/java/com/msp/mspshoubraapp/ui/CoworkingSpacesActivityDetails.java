package com.msp.mspshoubraapp.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.msp.mspshoubraapp.R;
import com.msp.mspshoubraapp.adapter.CoworkingSpacesPhotosRecyclerviewAdapter;
import com.msp.mspshoubraapp.adapter.PricesExpandableRecyclerviewAdapter;
import com.msp.mspshoubraapp.data.CoworkingSpacesPhotosListItem;
import com.msp.mspshoubraapp.data.ExpandableRecyclerViewItem;
import com.msp.mspshoubraapp.data.MenuListItem;
import com.thoughtbot.expandablerecyclerview.ExpandableRecyclerViewAdapter;

import java.util.ArrayList;
import java.util.List;

public class CoworkingSpacesActivityDetails extends AppCompatActivity {

    ImageView logoImageView;
    TextView titleTextView;
    RecyclerView photosRecyclerView;
    RecyclerView pricesRecyclerView;
    ExpandableRecyclerViewAdapter pricesAdapter;
    List<ExpandableRecyclerViewItem> items;
    List<MenuListItem> menuListItems;
    RecyclerView.Adapter photosAdapter;
    ArrayList<CoworkingSpacesPhotosListItem> imageList;
    ImageButton mapBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coworkingspace_details);

        ActionBar actionBar = getSupportActionBar();

        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        logoImageView = findViewById(R.id.coworkingspace_internal_logo);
        //logoImageView.setImageResource(R.drawable.ic_work_team);

        titleTextView = findViewById(R.id.coworkingspace_internal_title);
        //titleTextView.setText("Et3lem w 3lem Academy");

        Intent intent = getIntent();
        getSupportActionBar().setTitle("Co-Working Spaces");

        photosRecyclerView = findViewById(R.id.recyclerview_coworkingspace_photos);
        photosRecyclerView.setHasFixedSize(true);
        photosRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        imageList = new ArrayList<>();

        imageList.add(new CoworkingSpacesPhotosListItem(R.drawable.t1));
        imageList.add(new CoworkingSpacesPhotosListItem(R.drawable.t2));
        imageList.add(new CoworkingSpacesPhotosListItem(R.drawable.t3));
        imageList.add(new CoworkingSpacesPhotosListItem(R.drawable.t4));
        imageList.add(new CoworkingSpacesPhotosListItem(R.drawable.t5));
        imageList.add(new CoworkingSpacesPhotosListItem(R.drawable.t6));
        imageList.add(new CoworkingSpacesPhotosListItem(R.drawable.t7));

        photosAdapter = new CoworkingSpacesPhotosRecyclerviewAdapter(this, imageList, this);
        photosRecyclerView.setAdapter(photosAdapter);


        pricesRecyclerView = findViewById(R.id.pricesRV);
        pricesRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        items = new ArrayList<>();
        menuListItems = new ArrayList<>();
        menuListItems.add(new MenuListItem("One hour", "5"));
        menuListItems.add(new MenuListItem("All the day", "15"));
        menuListItems.add(new MenuListItem("Room for teams per hour", "30"));
        menuListItems.add(new MenuListItem("Projector", "20"));

        items.add(new ExpandableRecyclerViewItem("Prices", menuListItems));

        pricesAdapter = new PricesExpandableRecyclerviewAdapter(items);
        pricesRecyclerView.setAdapter(pricesAdapter);

        mapBtn = findViewById(R.id.map_btn);

        mapBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                finish();
                Intent homeIntent = new Intent(CoworkingSpacesActivityDetails.this, HomeActivity.class);
                homeIntent.putExtra("nextFregment", 1);
                homeIntent.putExtra("previousActivity", 4);
                startActivity(homeIntent);
            }
        });
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            //NavUtils.navigateUpFromSameTask(this);
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
