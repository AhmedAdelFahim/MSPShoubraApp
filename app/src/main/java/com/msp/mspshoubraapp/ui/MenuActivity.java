package com.msp.mspshoubraapp.ui;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;

import com.msp.mspshoubraapp.R;
import com.msp.mspshoubraapp.adapter.MenuRecyclerviewAdapter;
import com.msp.mspshoubraapp.data.MenuListItem;

import java.util.ArrayList;

public class MenuActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private MenuRecyclerviewAdapter adapter;
    private ArrayList<MenuListItem> itemList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        ActionBar actionBar = getSupportActionBar();

        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }


        itemList.add(new MenuListItem("Btates", "8"));
        itemList.add(new MenuListItem("kreb", "21"));
        recyclerView = findViewById(R.id.menuCustomRecycleview);
        adapter = new MenuRecyclerviewAdapter(this, itemList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);
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
