package com.msp.mspshoubraapp.adapter;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.msp.mspshoubraapp.R;
import com.msp.mspshoubraapp.data.IntervalData;

import java.util.List;

public class IntervalRecyclerViewAdapter extends RecyclerView.Adapter<IntervalRecyclerViewAdapter.Holder> {

    private List<IntervalData> intervalDataList;
    private Activity activity;

    public IntervalRecyclerViewAdapter(List<IntervalData> intervalDataList, Activity activity) {
        this.intervalDataList = intervalDataList;
        this.activity = activity;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_recyclerview_interval, parent, false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        IntervalData intervalData = intervalDataList.get(position);

        holder.sectionNum.setText(intervalData.getSectionNum());
        holder.subjectName.setText(intervalData.getSubjectName());
        holder.instructor.setText(intervalData.getInstructor());

        holder.place.setText(intervalData.getPlace());
    }

    @Override
    public int getItemCount() {
        return intervalDataList.size();
    }

    public class Holder extends RecyclerView.ViewHolder {
        private TextView sectionNum, subjectName, instructor, place;


        public Holder(View itemView) {
            super(itemView);

            sectionNum = itemView.findViewById(R.id.sec_num);
            subjectName = itemView.findViewById(R.id.subject_name);
            instructor = itemView.findViewById(R.id.instructor);
            place = itemView.findViewById(R.id.place);

        }
    }
}
