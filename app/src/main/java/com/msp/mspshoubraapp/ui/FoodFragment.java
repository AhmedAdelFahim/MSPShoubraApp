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

import com.msp.mspshoubraapp.data.FoodListItem;
import com.msp.mspshoubraapp.adapter.FoodRecyclerviewAdapter;
import com.msp.mspshoubraapp.R;

import java.util.ArrayList;

public class FoodFragment extends Fragment {

    private RecyclerView recyclerView;
    private FoodRecyclerviewAdapter adapter;
    private ArrayList<FoodListItem> itemList = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_food, container, false);
        itemList.add(new FoodListItem("Maxicno", "0101234567", "Haret Mongi, Al Hanafi, El-Sayeda Zainab", "https://scontent-cai1-1.xx.fbcdn.net/v/t1.0-9/31265219_1311493952328670_8886136977208901632_n.jpg", 30.0589, 31.2215, "max"));
        recyclerView = view.findViewById(R.id.foodCustomRecycleview);
        adapter = new FoodRecyclerviewAdapter(getActivity(), itemList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);
        return view;
    }
}
