package com.msp.mspshoubraapp.ui;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.msp.mspshoubraapp.R;
import com.msp.mspshoubraapp.adapter.LectureRecyclerViewAdapter;
import com.msp.mspshoubraapp.data.IntervalData;
import com.msp.mspshoubraapp.data.LectureData;

import java.util.ArrayList;

@SuppressLint("ValidFragment")
public class DayFragment extends Fragment {

    private String dayName;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_day, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.recyclerview_day);
        ArrayList<IntervalData> intervalData = new ArrayList<>();
        intervalData.add(new IntervalData("1", "Math", "C4", "Abdullah Zahr Ahmed"));
        intervalData.add(new IntervalData("2", "Math", "C4", "Abdullah Zahr Ahmed"));
        intervalData.add(new IntervalData("3", "Math", "C4", "Abdullah Zahr Ahmed"));
        intervalData.add(new IntervalData("4", "Math", "C4", "Abdullah Zahr Ahmed"));
        intervalData.add(new IntervalData("5", "Math", "C4", "Abdullah Zahr Ahmed"));

        ArrayList<LectureData> lectureData = new ArrayList<>();
        lectureData.add(new LectureData("9:00", "10:30", intervalData));
        lectureData.add(new LectureData("10:30", "12:00", intervalData));
        lectureData.add(new LectureData("1:00", "3:30", intervalData));
        lectureData.add(new LectureData("3:30", "5:30", intervalData));

        recyclerView.setAdapter(new LectureRecyclerViewAdapter(lectureData, getActivity()));
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        return view;
    }
}
