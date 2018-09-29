package com.msp.mspshoubraapp.ui;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;

import com.msp.mspshoubraapp.AppExecutors;
import com.msp.mspshoubraapp.R;
import com.msp.mspshoubraapp.adapter.MenuRecyclerviewAdapter;

import com.msp.mspshoubraapp.db.AppDatabase;
import com.msp.mspshoubraapp.db.RestaurantMenuEntity;

import java.util.ArrayList;

public class MenuActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private MenuRecyclerviewAdapter adapter;
    private ArrayList<RestaurantMenuEntity> itemList = new ArrayList<>();

    private AppDatabase appDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        ActionBar actionBar = getSupportActionBar();

        final String restaurantId = getIntent().getStringExtra("restaurantId");
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }


        /*itemList.add(new MenuListItem("Btates", "8"));
        itemList.add(new MenuListItem("kreb", "21"));*/
        recyclerView = findViewById(R.id.menuCustomRecycleview);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        appDatabase = AppDatabase.getInstance(this);
        AppExecutors.getInstance().diskIO().execute(new Runnable() {
            @Override
            public void run() {
                itemList = (ArrayList<RestaurantMenuEntity>) appDatabase.restaurantMenuDao().loadAllItems(restaurantId);
                adapter = new MenuRecyclerviewAdapter(MenuActivity.this, itemList);
                recyclerView.setAdapter(adapter);
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
