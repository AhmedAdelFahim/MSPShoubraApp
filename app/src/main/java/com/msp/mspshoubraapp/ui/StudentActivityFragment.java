package com.msp.mspshoubraapp.ui;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.msp.mspshoubraapp.R;
import com.msp.mspshoubraapp.adapter.StudentActivityRecyclerviewAdapter;
import com.msp.mspshoubraapp.data.StudentActivityListItem;

import java.util.ArrayList;

public class StudentActivityFragment extends Fragment {
    private RecyclerView recyclerView;
    private StudentActivityRecyclerviewAdapter adapter;
    private ArrayList<StudentActivityListItem> itemList = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_studentactivities, container, false);
        itemList.add(new StudentActivityListItem("MSP Tech Club Shoubra Engineering","https://scontent-cai1-1.xx.fbcdn.net/v/t1.0-9/12348129_935001243231666_5803219402411515065_n.jpg?_nc_cat=0&oh=5a6e134bfe4cd24596afad0e657fe7ab&oe=5C30762C"));
        recyclerView = view.findViewById(R.id.studentactivitiesCustomRecycleview);
        adapter = new StudentActivityRecyclerviewAdapter(getActivity(), itemList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);
        return view;
    }
}
