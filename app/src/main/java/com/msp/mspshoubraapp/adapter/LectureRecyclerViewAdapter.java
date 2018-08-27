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

import com.msp.mspshoubraapp.R;
import com.msp.mspshoubraapp.data.LectureData;
import com.msp.mspshoubraapp.data.NewsItem;

import java.util.ArrayList;
import java.util.List;


public class LectureRecyclerViewAdapter extends RecyclerView.Adapter<LectureRecyclerViewAdapter.Holder> {


    List<LectureData> lectureData;

    private Activity activity;

    public LectureRecyclerViewAdapter(List<LectureData> lectureData, Activity activity) {
        this.lectureData = lectureData;
        this.activity = activity;
    }

    @NonNull
    @Override
    public LectureRecyclerViewAdapter.Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_recyclerview_day, parent, false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LectureRecyclerViewAdapter.Holder holder, final int position) {
        LectureData lectureDataItem = lectureData.get(position);
        holder.startTime.setText(lectureDataItem.getStartTime());
        holder.endTime.setText(lectureDataItem.getEndTime());

        IntervalRecyclerViewAdapter intervalRecyclerViewAdapter = new IntervalRecyclerViewAdapter(lectureDataItem.getIntervalData(), activity);
        holder.recyclerView.setAdapter(intervalRecyclerViewAdapter);
        holder.recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false);
        holder.recyclerView.setLayoutManager(layoutManager);
    }

    @Override
    public int getItemCount() {
        return lectureData.size();
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
