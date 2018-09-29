package com.msp.mspshoubraapp.adapter;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.msp.mspshoubraapp.AppExecutors;
import com.msp.mspshoubraapp.R;
import com.msp.mspshoubraapp.data.LectureData;
import com.msp.mspshoubraapp.data.NewsItem;
import com.msp.mspshoubraapp.db.AppDatabase;
import com.msp.mspshoubraapp.db.DayLecturesEntity;
import com.msp.mspshoubraapp.db.SectionsEntity;

import java.util.ArrayList;
import java.util.List;


public class LectureRecyclerViewAdapter extends RecyclerView.Adapter<LectureRecyclerViewAdapter.Holder> {


    private List<DayLecturesEntity> lectureData;
    private List<SectionsEntity> sectionsEntities;
    private Activity activity;
    private AppDatabase appDatabase;

    public LectureRecyclerViewAdapter(List<DayLecturesEntity> lectureData, Activity activity) {
        this.lectureData = lectureData;
        this.activity = activity;
        sectionsEntities = new ArrayList<>();
        appDatabase = AppDatabase.getInstance(activity);
    }

    @NonNull
    @Override
    public LectureRecyclerViewAdapter.Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_recyclerview_day, parent, false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final LectureRecyclerViewAdapter.Holder holder, final int position) {
        final DayLecturesEntity lectureDataItem = lectureData.get(position);
        holder.startTime.setText(lectureDataItem.getStartTime());
        holder.endTime.setText(lectureDataItem.getEndTime());
        LinearLayoutManager layoutManager = new LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false);
        holder.recyclerView.setHasFixedSize(true);
        holder.recyclerView.setLayoutManager(layoutManager);
        /*IntervalRecyclerViewAdapter intervalRecyclerViewAdapter = new IntervalRecyclerViewAdapter(sectionsEntities, activity);
        holder.recyclerView.setAdapter(intervalRecyclerViewAdapter);*/
        ArrayList<SectionsEntity> sectionsEntities = new ArrayList<>();
        final IntervalRecyclerViewAdapter intervalRecyclerViewAdapter = new IntervalRecyclerViewAdapter(sectionsEntities, activity);
        holder.recyclerView.setAdapter(intervalRecyclerViewAdapter);

        AppExecutors.getInstance().diskIO().execute(new Runnable() {
            @Override
            public void run() {
                final ArrayList<SectionsEntity> sectionsEntities = (ArrayList<SectionsEntity>) appDatabase.lecturesTableDao().loadAllSections(lectureDataItem.getLectureNum());
                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        intervalRecyclerViewAdapter.setSections(sectionsEntities);

                    }
                });
            }
        });

    }

    @Override
    public int getItemCount() {
        if (lectureData == null)
            return 0;
        return lectureData.size();
    }

    public void setLectures(List<DayLecturesEntity> dayLecturesEntities) {

        if (dayLecturesEntities == null) {
            return;
        }
        this.lectureData = dayLecturesEntities;
        notifyDataSetChanged();
    }


    public class Holder extends RecyclerView.ViewHolder {
        private TextView startTime, endTime;
        private RecyclerView recyclerView;

        public Holder(View itemView) {
            super(itemView);

            startTime = itemView.findViewById(R.id.start_time);
            endTime = itemView.findViewById(R.id.end_time);
            recyclerView = itemView.findViewById(R.id.recyclerView_interval);

        }
    }
}
