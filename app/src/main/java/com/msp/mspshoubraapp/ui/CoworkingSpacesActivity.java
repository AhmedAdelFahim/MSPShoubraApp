package com.msp.mspshoubraapp.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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

public class CoworkingSpacesActivity extends AppCompatActivity {

    ImageView logoImageView;
    TextView titleTextView;
    RecyclerView photosRecyclerView;
    RecyclerView pricesRecyclerView;
    ExpandableRecyclerViewAdapter pricesAdapter;
    List<ExpandableRecyclerViewItem> items;
    List<MenuListItem> menuListItems;
    RecyclerView.Adapter photosAdapter;
    ArrayList<CoworkingSpacesPhotosListItem> imageList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coworkingspace);

        logoImageView = findViewById(R.id.coworkingspace_internal_logo);
        //logoImageView.setImageResource(R.drawable.ic_work_team);

        titleTextView = findViewById(R.id.coworkingspace_internal_title);
        //titleTextView.setText("Et3lem w 3lem Academy");

        Intent intent = getIntent();
        getSupportActionBar().setTitle("Co-Working Spaces");

        photosRecyclerView = findViewById(R.id.recyclerview_coworkingspace_photos);
        photosRecyclerView.setHasFixedSize(true);
        photosRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,false));

        imageList = new ArrayList<>();

        imageList.add(new CoworkingSpacesPhotosListItem(R.drawable.anonymous_half));
        imageList.add(new CoworkingSpacesPhotosListItem(R.drawable.doc));
        imageList.add(new CoworkingSpacesPhotosListItem(R.drawable.red));
        imageList.add(new CoworkingSpacesPhotosListItem(R.drawable.anonymous_mask));
        imageList.add(new CoworkingSpacesPhotosListItem(R.drawable.article));
        imageList.add(new CoworkingSpacesPhotosListItem(R.drawable.ieee_cusb));

        photosAdapter = new CoworkingSpacesPhotosRecyclerviewAdapter(this, imageList,this );
        photosRecyclerView.setAdapter(photosAdapter);


        pricesRecyclerView = findViewById(R.id.pricesRV);
        pricesRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        items = new ArrayList<>();
        menuListItems = new ArrayList<>();
        menuListItems.add(new MenuListItem("One hour","5"));
        menuListItems.add(new MenuListItem("All the day","15"));
        menuListItems.add(new MenuListItem("Room for teams per hour","30"));
        menuListItems.add(new MenuListItem("Projector","20"));

        items.add(new ExpandableRecyclerViewItem("Prices", menuListItems));

        pricesAdapter = new PricesExpandableRecyclerviewAdapter(items);
        pricesRecyclerView.setAdapter(pricesAdapter);
    }
}
