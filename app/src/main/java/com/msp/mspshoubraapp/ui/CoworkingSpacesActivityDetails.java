package com.msp.mspshoubraapp.ui;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.msp.mspshoubraapp.AppExecutors;
import com.msp.mspshoubraapp.R;
import com.msp.mspshoubraapp.adapter.CoworkingSpacesPhotosRecyclerviewAdapter;
import com.msp.mspshoubraapp.adapter.PricesExpandableRecyclerviewAdapter;
import com.msp.mspshoubraapp.data.ExpandableRecyclerViewItem;
import com.msp.mspshoubraapp.db.AppDatabase;
import com.msp.mspshoubraapp.db.CoworkingSpaceEntity;
import com.msp.mspshoubraapp.db.CoworkingSpaceImageEntity;
import com.msp.mspshoubraapp.db.CoworkingSpacePriceEntity;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;
import com.thoughtbot.expandablerecyclerview.ExpandableRecyclerViewAdapter;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class CoworkingSpacesActivityDetails extends AppCompatActivity {

    ImageView logoImageView;
    TextView titleTextView, address, phone;
    RecyclerView photosRecyclerView;
    RecyclerView pricesRecyclerView;
    ExpandableRecyclerViewAdapter pricesAdapter;
    List<ExpandableRecyclerViewItem> items;
    List<CoworkingSpacePriceEntity> pricesList;
    CoworkingSpacesPhotosRecyclerviewAdapter photosAdapter;
    private ArrayList<CoworkingSpaceImageEntity> imageList;
    ImageButton mapBtn;
    ProgressBar progressBar;

    private CoworkingSpaceEntity coworkingSpaceEntity;
    private AppDatabase appDatabase;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coworkingspace_details);

        ActionBar actionBar = getSupportActionBar();

        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }


        appDatabase = AppDatabase.getInstance(this);
        coworkingSpaceEntity = getIntent().getExtras().getParcelable("coworkingSpaces");

        logoImageView = findViewById(R.id.coworkingspace_internal_logo);
        titleTextView = findViewById(R.id.coworkingspace_internal_title);
        photosRecyclerView = findViewById(R.id.recyclerview_coworkingspace_photos);
        pricesRecyclerView = findViewById(R.id.pricesRV);
        mapBtn = findViewById(R.id.map_btn);
        address = findViewById(R.id.coworking_address_textview);
        phone = findViewById(R.id.coworking_telephone_textView);
        progressBar = findViewById(R.id.csd_progressBar);

        getSupportActionBar().setTitle(coworkingSpaceEntity.getName());

        titleTextView.setText(coworkingSpaceEntity.getName());
        address.setText(coworkingSpaceEntity.getAddress());
        String phone2 = coworkingSpaceEntity.getPhone2();
        if (phone2.trim().length() == 0) {
            phone.setText(coworkingSpaceEntity.getPhone1());
        } else {
            phone.setText(coworkingSpaceEntity.getPhone1() + "\n" + phone2);
        }
        Picasso
                .get()
                .load(new File(coworkingSpaceEntity.getImgLogo()))
                .error(R.drawable.ic_error_black_24dp)
                .into(logoImageView, new Callback() {
                    @Override
                    public void onSuccess() {
                        progressBar.setVisibility(View.GONE);
                    }

                    @Override
                    public void onError(Exception e) {

                    }
                });

        imageList = new ArrayList<>();
        photosRecyclerView.setHasFixedSize(true);
        photosRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        photosAdapter = new CoworkingSpacesPhotosRecyclerviewAdapter(imageList, this);
        photosRecyclerView.setAdapter(photosAdapter);

        items = new ArrayList<>();
        pricesRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        pricesList = new ArrayList<>();

        AppExecutors.getInstance().diskIO().execute(new Runnable() {
            @Override
            public void run() {
                imageList = (ArrayList<CoworkingSpaceImageEntity>) appDatabase.coworkingSpaceImageDao().loadAllImages(coworkingSpaceEntity.getId());
                if (imageList.isEmpty()) {
                    photosRecyclerView.setVisibility(View.GONE);
                }
                photosAdapter.setImages(imageList);
                pricesList = appDatabase.coworkingSpacePriceDao().loadAllPrices(coworkingSpaceEntity.getId());
                items.add(new ExpandableRecyclerViewItem("Prices", pricesList));
                pricesAdapter = new PricesExpandableRecyclerviewAdapter(items);
                pricesRecyclerView.setAdapter(pricesAdapter);
            }
        });

        mapBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                finish();
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("geo:0,0?q=" +
                        coworkingSpaceEntity.getLat() +
                        "," +
                        coworkingSpaceEntity.getLng() +
                        "(" + coworkingSpaceEntity.getName() + ")"));
                mapIntent.setPackage("com.google.android.apps.maps");
                startActivity(mapIntent);
            }
        });
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}