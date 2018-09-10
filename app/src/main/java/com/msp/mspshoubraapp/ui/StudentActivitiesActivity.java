package com.msp.mspshoubraapp.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;

import com.msp.mspshoubraapp.R;
import com.msp.mspshoubraapp.adapter.CommitteesExpandableRecyclerviewAdapter;
import com.msp.mspshoubraapp.data.CommitteesListItem;
import com.msp.mspshoubraapp.data.ExpandableRecyclerViewItem;
import com.thoughtbot.expandablerecyclerview.ExpandableRecyclerViewAdapter;

import java.util.ArrayList;
import java.util.List;

import javax.sql.CommonDataSource;

public class StudentActivitiesActivity extends AppCompatActivity {

    ImageView logoImageView;
    TextView titleTextView;
    TextView description;
    RecyclerView committees;
    ExpandableRecyclerViewAdapter adapter;
    List<ExpandableRecyclerViewItem> items;
    List<CommitteesListItem> committeesListItems;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_studentactivity);

        logoImageView = findViewById(R.id.studentactivity_internal_logo);
        titleTextView = findViewById(R.id.studentactivity_internal_title);
        description = findViewById(R.id.committee_description_textview);

        Intent intent = getIntent();
        getSupportActionBar().setTitle("Student Activities");

        committees = findViewById(R.id.committeesRV);
        committees.setLayoutManager(new LinearLayoutManager(this));

        items = new ArrayList<>();
        committeesListItems = new ArrayList<>();

        committeesListItems.add(new CommitteesListItem("Web Committee","BLA BLA BLA BLA BLA BLA BLA BLA BLA BLA BLA BLA BLA BLA BLA BLA BLA BLA BLA BLA BLA BLA BLA BLA BLA BLA BLA BLA BLA BLA"));
        committeesListItems.add(new CommitteesListItem("Media Committee","BLA BLA BLA BLA BLA BLA BLA BLA BLA BLA BLA BLA BLA BLA BLA BLA BLA BLA BLA BLA BLA BLA BLA BLA BLA BLA BLA BLA BLA BLA"));
        committeesListItems.add(new CommitteesListItem("HR Committee","BLA BLA BLA BLA BLA BLA BLA BLA BLA BLA BLA BLA BLA BLA BLA BLA BLA BLA BLA BLA BLA BLA BLA BLA BLA BLA BLA BLA BLA BLA"));
        committeesListItems.add(new CommitteesListItem("PR Committee","BLA BLA BLA BLA BLA BLA BLA BLA BLA BLA BLA BLA BLA BLA BLA BLA BLA BLA BLA BLA BLA BLA BLA BLA BLA BLA BLA BLA BLA BLA"));
        committeesListItems.add(new CommitteesListItem("Microsoft Committee","BLA BLA BLA BLA BLA BLA BLA BLA BLA BLA BLA BLA BLA BLA BLA BLA BLA BLA BLA BLA BLA BLA BLA BLA BLA BLA BLA BLA BLA BLA"));

        items.add(new ExpandableRecyclerViewItem("Committees",committeesListItems));

        adapter = new CommitteesExpandableRecyclerviewAdapter(items);
        committees.setAdapter(adapter);

    }
}
