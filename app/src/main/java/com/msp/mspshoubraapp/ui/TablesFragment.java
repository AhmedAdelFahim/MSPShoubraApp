package com.msp.mspshoubraapp.ui;

import android.app.AlertDialog;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;

import com.msp.mspshoubraapp.R;
import com.msp.mspshoubraapp.adapter.LectureRecyclerViewAdapter;
import com.msp.mspshoubraapp.db.AppDatabase;
import com.msp.mspshoubraapp.db.DayLecturesEntity;
import com.msp.mspshoubraapp.viewmodel.DayFragmentViewModel;
import com.msp.mspshoubraapp.viewmodel.DayFragmentViewModelFactory;

import java.util.ArrayList;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by Laila Al Ashkar on 8/14/2018.
 */

public class TablesFragment extends Fragment {

    private String dayName, groupNum;
    private DayFragmentViewModel viewModel;
    private DayFragmentViewModelFactory factory;
    private AppDatabase appDatabase;
    private ArrayList<DayLecturesEntity> lecturesEntities;
    private LectureRecyclerViewAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_day, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.recyclerview_day);
        dayName = getArguments().getString("day");
        dayName = "Sun";
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences(getResources().getString(R.string.msp_preferences), MODE_PRIVATE);
        groupNum = sharedPreferences.getString(getResources().getString(R.string.group_num), "");
        if (groupNum.equals("")) {
            //FetchDataFromApi.loadLecturesTable(this,false);
            chooseGroupDialog();
        } else {
            setupViewModel();
        }
        appDatabase = AppDatabase.getInstance(getActivity());
        factory = new DayFragmentViewModelFactory(appDatabase, dayName, groupNum);
        viewModel = ViewModelProviders.of(this, factory).get(DayFragmentViewModel.class);

        adapter = new LectureRecyclerViewAdapter(lecturesEntities, getActivity());
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);

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

    private void chooseGroupDialog() {
        final AlertDialog.Builder alertDialog = new AlertDialog.Builder(getActivity());
        View alertDialogView = getLayoutInflater().inflate(R.layout.change_group_dialog, null);
        alertDialog.setView(alertDialogView);
        final AlertDialog alert = alertDialog.create();
        final Spinner spinnerGroup = alertDialogView.findViewById(R.id.spinner_group);
        Button ok = alertDialogView.findViewById(R.id.change_group_btn);
        ArrayList<Integer> groups = new ArrayList<>();
        for (int i = 1; i <= 6; i++) {
            groups.add(i);
        }
        ArrayAdapter<Integer> groupAdapter = new ArrayAdapter<Integer>(getActivity(), android.R.layout.simple_spinner_item, groups);
        groupAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerGroup.setAdapter(groupAdapter);

        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                groupNum = "Group" + spinnerGroup.getSelectedItem();
                SharedPreferences sharedPreferences = getActivity().getSharedPreferences(getResources().getString(R.string.msp_preferences), MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString(getResources().getString(R.string.group_num), groupNum);
                editor.apply();
                setupViewModel();
                alert.dismiss();

            }
        });

        alert.show();
    }
}
