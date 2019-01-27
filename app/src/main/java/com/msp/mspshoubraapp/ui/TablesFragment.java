package com.msp.mspshoubraapp.ui;

import android.app.AlertDialog;

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
import android.widget.TextView;
import android.widget.Toast;

import com.msp.mspshoubraapp.AppExecutors;
import com.msp.mspshoubraapp.R;
import com.msp.mspshoubraapp.adapter.LectureRecyclerViewAdapter;
import com.msp.mspshoubraapp.db.AppDatabase;
import com.msp.mspshoubraapp.db.DayLecturesEntity;
import com.msp.mspshoubraapp.networking.ConnectivityStatus;
import com.msp.mspshoubraapp.networking.FetchDataFromApi;
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
    private AppDatabase appDatabase;
    private ArrayList<DayLecturesEntity> lecturesEntities;
    private LectureRecyclerViewAdapter adapter;
    private RecyclerView recyclerView;

    @Nullable
    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_tables, container, false);
        recyclerView = view.findViewById(R.id.recyclerview_day);
        ImageView emoji = view.findViewById(R.id.sleep_emoji);
        TextView noLecText = view.findViewById(R.id.no_lecture);
        dayName = getArguments().getString("day");
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences(getResources().getString(R.string.msp_preferences), MODE_PRIVATE);
        appDatabase = AppDatabase.getInstance(getActivity());
        if (dayName != null) {
            if (dayName.equals("Fri") || dayName.equals("Sat")) {
                recyclerView.setVisibility(View.GONE);
                emoji.setVisibility(View.VISIBLE);
                noLecText.setVisibility(View.VISIBLE);
            } else {
                if (sharedPreferences.getBoolean(getResources().getString(R.string.lecturesTable), true)) {
                    if (ConnectivityStatus.isConnected(getActivity())) {
                        FetchDataFromApi.loadLecturesTable(getActivity(), false);
                    } else {
                        Toast.makeText(getActivity(), "No Internet Connection", Toast.LENGTH_SHORT).show();
                        return view;
                    }
                }
                recyclerView.setHasFixedSize(true);
                LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
                recyclerView.setLayoutManager(layoutManager);

                groupNum = sharedPreferences.getString(getResources().getString(R.string.group_num), "");
                if (groupNum.equals("")) {
                    chooseGroupDialog();
                } else {
                    loadTodayTable(recyclerView);
                }


            }
        }

        return view;
    }

    private void loadTodayTable(final RecyclerView recyclerView) {
        AppExecutors.getInstance().diskIO().execute(new Runnable() {
            @Override
            public void run() {
                lecturesEntities = (ArrayList<DayLecturesEntity>) appDatabase.lecturesTableDao().loadAllLecturesList(groupNum, dayName);
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        adapter = new LectureRecyclerViewAdapter(lecturesEntities, getActivity(), groupNum, dayName);
                        recyclerView.setAdapter(adapter);
                    }
                });
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
                loadTodayTable(recyclerView);
                alert.dismiss();

            }
        });

        alert.show();
    }
}
