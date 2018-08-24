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

import com.msp.mspshoubraapp.data.MenuListItem;
import com.msp.mspshoubraapp.adapter.MenuRecyclerviewAdapter;
import com.msp.mspshoubraapp.R;

import java.util.ArrayList;

public class MenuFragment extends Fragment {

    private RecyclerView recyclerView;
    private MenuRecyclerviewAdapter adapter;
    private ArrayList<MenuListItem> itemList = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_menu, container, false);
        itemList.add(new MenuListItem("Btates", "8"));
        itemList.add(new MenuListItem("kreb", "21"));
        recyclerView = view.findViewById(R.id.menuCustomRecycleview);
        adapter = new MenuRecyclerviewAdapter(getActivity(), itemList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);
        return view;
    }
}
