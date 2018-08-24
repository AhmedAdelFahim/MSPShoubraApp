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

import com.msp.mspshoubraapp.adapter.NewsFeedRecyclerViewAdapter;
import com.msp.mspshoubraapp.data.NewsItem;
import com.msp.mspshoubraapp.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Laila Al Ashkar on 8/14/2018.
 */

public class NewsFragment extends Fragment {

    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;

    List<NewsItem> newsItems;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_news, container, false);

        recyclerView = view.findViewById(R.id.news_recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        newsItems = new ArrayList<>();

        for (int i = 0; i <= 5; i++) {
            NewsItem newsItem = new NewsItem("السلام عليكم\nازيكم \nتيست تيست تيست", R.drawable.doc);
            newsItems.add(newsItem);
        }
        adapter = new NewsFeedRecyclerViewAdapter(newsItems, getActivity());

        recyclerView.setAdapter(adapter);

        return view;
    }
}
