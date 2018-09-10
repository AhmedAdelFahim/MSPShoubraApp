package com.msp.mspshoubraapp.ui;

import android.content.Intent;
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
import com.msp.mspshoubraapp.adapter.CoworkingSpacesRecyclerviewAdapter;
import com.msp.mspshoubraapp.data.CoworkingSpacesListItem;

import java.util.ArrayList;
import java.util.List;

public class CoworkingSpacesFragment extends Fragment implements
CoworkingSpacesRecyclerviewAdapter.listItemClickListener{
    private RecyclerView recyclerView;
    private CoworkingSpacesRecyclerviewAdapter adapter;
    private List<CoworkingSpacesListItem> itemList = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_coworkingspaces, container, false);
        itemList.add(new CoworkingSpacesListItem("Kitchen MakerSpace","58 Rood El Farag Street, Cairo, Egypt" ,"0111234567" ,"https://scontent-cai1-1.xx.fbcdn.net/v/t1.0-9/19990107_1984411215172755_227107490940945033_n.png?_nc_cat=0&oh=551ff776ecb9a5d8244c8a6b439f0f6e&oe=5BF1F3CC"));
        recyclerView = view.findViewById(R.id.coworkingSpacesCustomRecycleview);
        adapter = new CoworkingSpacesRecyclerviewAdapter(getActivity(), itemList,this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);

        return view;
    }

    @Override
    public void onListItemClick(int clickedItemIndex) {
        Intent intent = new Intent(getActivity(), CoworkingSpacesActivity.class);

        startActivity(intent);
    }
}
