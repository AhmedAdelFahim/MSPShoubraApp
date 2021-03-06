package com.msp.mspshoubraapp.adapter;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.msp.mspshoubraapp.AppExecutors;
import com.msp.mspshoubraapp.R;
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
    private String groupNum, dayName;

    public LectureRecyclerViewAdapter(List<DayLecturesEntity> lectureData, Activity activity, String groupNum, String dayName) {
        this.lectureData = lectureData;
        this.activity = activity;
        sectionsEntities = new ArrayList<>();
        appDatabase = AppDatabase.getInstance(activity);
        this.groupNum = groupNum;
        this.dayName = dayName;
    }

    @NonNull
    @Override
    public LectureRecyclerViewAdapter.Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        /*
         * Construct a new view by inflate it from an XML layout file to be used in
         *  onBindViewHolder method it will be re-used to display different items.
         */
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_recyclerview_day, parent, false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final LectureRecyclerViewAdapter.Holder holder, final int position) {
        /*
         * Get entities and bind them to the view holder using the entity model
         */
        final DayLecturesEntity lectureDataItem = lectureData.get(position);
        holder.startTime.setText(lectureDataItem.getStartTime());
        holder.endTime.setText(lectureDataItem.getEndTime());
        LinearLayoutManager layoutManager = new LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false);
        holder.recyclerView.setHasFixedSize(true);
        holder.recyclerView.setLayoutManager(layoutManager);
        ArrayList<SectionsEntity> sectionsEntities = new ArrayList<>();
        final IntervalRecyclerViewAdapter intervalRecyclerViewAdapter = new IntervalRecyclerViewAdapter(sectionsEntities, activity);
        holder.recyclerView.setAdapter(intervalRecyclerViewAdapter);

        AppExecutors.getInstance().diskIO().execute(new Runnable() {
            @Override
            public void run() {
                final ArrayList<SectionsEntity> sectionsEntities = (ArrayList<SectionsEntity>) appDatabase.lecturesTableDao().loadAllSections(lectureDataItem.getLectureNum(), appDatabase.lecturesTableDao().getGroupId(groupNum, dayName));
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
        /**
         * ViewHolder model that is being inflated from XML file
         */
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
