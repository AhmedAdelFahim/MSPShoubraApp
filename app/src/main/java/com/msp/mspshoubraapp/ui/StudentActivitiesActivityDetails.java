package com.msp.mspshoubraapp.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.msp.mspshoubraapp.AppExecutors;
import com.msp.mspshoubraapp.R;
import com.msp.mspshoubraapp.adapter.CommitteesExpandableRecyclerviewAdapter;
import com.msp.mspshoubraapp.data.ExpandableRecyclerViewItem;
import com.msp.mspshoubraapp.db.AppDatabase;
import com.msp.mspshoubraapp.db.CommitteeEntity;
import com.msp.mspshoubraapp.db.StudentActivityEntity;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;
import com.thoughtbot.expandablerecyclerview.ExpandableRecyclerViewAdapter;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class StudentActivitiesActivityDetails extends AppCompatActivity {

    ImageView logoImageView;
    TextView titleTextView;
    TextView description;
    RecyclerView committees;
    ExpandableRecyclerViewAdapter adapter;
    List<ExpandableRecyclerViewItem> items;
    List<CommitteeEntity> committeesListItems;
    StudentActivityEntity studentActivityEntity;
    private AppDatabase appDatabase;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_studentactivity_details);

        Intent intent = getIntent();
        getSupportActionBar().setTitle("Student Activities");
        studentActivityEntity = intent.getExtras().getParcelable("studentActivity");
        getSupportActionBar().setTitle(studentActivityEntity.getName());

        ActionBar actionBar = getSupportActionBar();

        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        logoImageView = findViewById(R.id.studentactivity_internal_logo);
        titleTextView = findViewById(R.id.studentactivity_internal_title);
        description = findViewById(R.id.studentactivity_description);
        progressBar = findViewById(R.id.sad_progressBar);

        titleTextView.setText(studentActivityEntity.getName());
        description.setText(studentActivityEntity.getDescription());
        Picasso
                .get()
                .load(new File(studentActivityEntity.getImgLogo() + "/" + studentActivityEntity.getName()))
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
        committeesListItems = new ArrayList<>();
        committees = findViewById(R.id.committeesRV);
        committees.setLayoutManager(new LinearLayoutManager(this));
        items = new ArrayList<>();
        appDatabase = AppDatabase.getInstance(this);
        AppExecutors.getInstance().diskIO().execute(new Runnable() {
            @Override
            public void run() {

                committeesListItems = appDatabase.committeesDao().loadAllCommittees(studentActivityEntity.getId());
                StudentActivitiesActivityDetails.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (committeesListItems != null && !committeesListItems.isEmpty()) {
                            items.add(new ExpandableRecyclerViewItem("Committees", committeesListItems));
                            adapter = new CommitteesExpandableRecyclerviewAdapter(items);
                            committees.setAdapter(adapter);
                        }
                    }
                });
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
