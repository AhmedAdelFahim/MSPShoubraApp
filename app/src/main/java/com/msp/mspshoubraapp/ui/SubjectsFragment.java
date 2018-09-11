package com.msp.mspshoubraapp.ui;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.msp.mspshoubraapp.R;
import com.msp.mspshoubraapp.adapter.SubjectsExpandableRecyclerviewAdapter;
import com.msp.mspshoubraapp.data.SubjectsDetailsListItem;
import com.msp.mspshoubraapp.data.SubjectsListItem;
import com.thoughtbot.expandablerecyclerview.ExpandableRecyclerViewAdapter;

import java.util.ArrayList;
import java.util.List;

public class SubjectsFragment extends android.support.v4.app.Fragment {

    RecyclerView subjectsRV;
    ExpandableRecyclerViewAdapter adapter;
    List<SubjectsListItem> subjectTitle;
    List<SubjectsDetailsListItem> subjectsDetailsListItems;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_subjects, container, false);
        subjectsRV = view.findViewById(R.id.subjectsRV);
        subjectsRV.setLayoutManager(new LinearLayoutManager(getActivity()));

        subjectTitle = new ArrayList<>();
        subjectsDetailsListItems = new ArrayList<>();

        subjectTitle.clear();
        for(int i=0; i<13; i++) {
            subjectsDetailsListItems.clear();
            subjectsDetailsListItems.add(new SubjectsDetailsListItem("Midterm", "30"));
            subjectsDetailsListItems.add(new SubjectsDetailsListItem("Oral", "10"));
            subjectsDetailsListItems.add(new SubjectsDetailsListItem("Project", "20"));
            subjectsDetailsListItems.add(new SubjectsDetailsListItem("Final", "90"));
            subjectsDetailsListItems.add(new SubjectsDetailsListItem("Total", "150"));

            subjectTitle.add(new SubjectsListItem("Maths",subjectsDetailsListItems));
        }

        adapter = new SubjectsExpandableRecyclerviewAdapter(subjectTitle);
        subjectsRV.setAdapter(adapter);

        return view;
    }
}
