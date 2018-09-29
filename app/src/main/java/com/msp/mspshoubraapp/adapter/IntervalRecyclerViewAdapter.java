package com.msp.mspshoubraapp.adapter;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.msp.mspshoubraapp.R;
import com.msp.mspshoubraapp.db.SectionsEntity;

import java.util.ArrayList;
import java.util.List;

public class IntervalRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<SectionsEntity> intervalDataList;
    private Activity activity;

    public IntervalRecyclerViewAdapter(List<SectionsEntity> intervalDataList, Activity activity) {
        this.intervalDataList = intervalDataList;
        this.activity = activity;
    }

    @Override
    public int getItemViewType(int position) {
        return intervalDataList.get(position).getSecNum();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_recyclerview_interval, parent, false);
        if (viewType == 0) {
            return new LectureHolder(view);
        } else {
            return new SectionHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        SectionsEntity intervalData = intervalDataList.get(position);

        int periodType = intervalData.getSecNum();

        if (holder.getItemViewType() == 0) {
            LectureHolder lectureHolder = (LectureHolder) holder;
            //holder.sectionNum.setText(Integer.toString(periodType));
            lectureHolder.subjectName.setText(intervalData.getSubjectName());
            lectureHolder.instructor.setText(intervalData.getInstructor());
            lectureHolder.place.setText(intervalData.getPlace());
            lectureHolder.periodType.setText("Lecture");

        } else {
            SectionHolder sectionHolder = (SectionHolder) holder;
            sectionHolder.sectionNum.setText(Integer.toString(periodType));
            sectionHolder.subjectName.setText(intervalData.getSubjectName());
            sectionHolder.instructor.setText(intervalData.getInstructor());
            sectionHolder.place.setText(intervalData.getPlace());
            sectionHolder.periodType.setText("Section");
        }

    }

    @Override
    public int getItemCount() {
        if (intervalDataList == null) {
            return 0;
        }
        return intervalDataList.size();
    }

    public void setSections(ArrayList<SectionsEntity> sectionsEntities) {
        if (sectionsEntities == null) {
            return;
        }
        this.intervalDataList = sectionsEntities;
        notifyDataSetChanged();
    }

    public class SectionHolder extends RecyclerView.ViewHolder {
        private TextView sectionNum, subjectName, instructor, place, periodType;


        public SectionHolder(View itemView) {
            super(itemView);

            sectionNum = itemView.findViewById(R.id.sec_num);
            subjectName = itemView.findViewById(R.id.subject_name);
            instructor = itemView.findViewById(R.id.instructor);
            place = itemView.findViewById(R.id.place);
            periodType = itemView.findViewById(R.id.period_type);

        }
    }

    public class LectureHolder extends RecyclerView.ViewHolder {
        private TextView sectionNum, subjectName, instructor, place, periodType;


        public LectureHolder(View itemView) {
            super(itemView);

            sectionNum = itemView.findViewById(R.id.sec_num);
            subjectName = itemView.findViewById(R.id.subject_name);
            instructor = itemView.findViewById(R.id.instructor);
            place = itemView.findViewById(R.id.place);
            periodType = itemView.findViewById(R.id.period_type);

            sectionNum.setVisibility(View.GONE);

        }
    }
}
