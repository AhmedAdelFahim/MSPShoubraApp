package com.msp.mspshoubraapp.ui;

import android.annotation.SuppressLint;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.msp.mspshoubraapp.AppExecutors;
import com.msp.mspshoubraapp.R;
import com.msp.mspshoubraapp.adapter.LectureRecyclerViewAdapter;
import com.msp.mspshoubraapp.data.IntervalData;
import com.msp.mspshoubraapp.data.LectureData;
import com.msp.mspshoubraapp.db.AppDatabase;
import com.msp.mspshoubraapp.db.DayLecturesEntity;
import com.msp.mspshoubraapp.viewmodel.DayFragmentViewModel;
import com.msp.mspshoubraapp.viewmodel.DayFragmentViewModelFactory;

import java.util.ArrayList;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;

@SuppressLint("ValidFragment")
public class DayFragment extends Fragment {

    private String dayName, groupNum;
    private DayFragmentViewModel viewModel;
    private DayFragmentViewModelFactory factory;
    private AppDatabase appDatabase;
    private ArrayList<DayLecturesEntity> lecturesEntities;
    private LectureRecyclerViewAdapter adapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_day, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.recyclerview_day);
        dayName = getArguments().getString("day");
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences(getResources().getString(R.string.msp_preferences), MODE_PRIVATE);
        groupNum = sharedPreferences.getString(getResources().getString(R.string.group_num), "");
        appDatabase = AppDatabase.getInstance(getActivity());
        factory = new DayFragmentViewModelFactory(appDatabase, dayName, groupNum);
        viewModel = ViewModelProviders.of(this, factory).get(DayFragmentViewModel.class);

        adapter = new LectureRecyclerViewAdapter(lecturesEntities, getActivity());
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        setupViewModel();
        return view;
    }

    private void setupViewModel() {

        viewModel.getLecturesList().observe(this, new Observer<List<DayLecturesEntity>>() {
            @Override
            public void onChanged(@Nullable List<DayLecturesEntity> dayLecturesEntities) {
                adapter.setLectures(dayLecturesEntities);
            }
        });


    }
}
