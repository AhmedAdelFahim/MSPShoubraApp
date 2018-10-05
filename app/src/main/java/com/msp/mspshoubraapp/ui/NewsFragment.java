package com.msp.mspshoubraapp.ui;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.msp.mspshoubraapp.Constants;
import com.msp.mspshoubraapp.adapter.NewsFeedRecyclerViewAdapter;
import com.msp.mspshoubraapp.R;
import com.msp.mspshoubraapp.data.PostData;
import com.msp.mspshoubraapp.networking.BuildData;
import com.msp.mspshoubraapp.networking.ConnectivityStatus;
import com.msp.mspshoubraapp.networking.VolleySingleton;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Laila Al Ashkar on 8/14/2018.
 */

public class NewsFragment extends Fragment {

    RecyclerView recyclerView;
    NewsFeedRecyclerViewAdapter adapter;

    List<PostData> newsItems;
    private ProgressDialog progressDialog;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_news, container, false);

        recyclerView = view.findViewById(R.id.news_recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        newsItems = new ArrayList<>();
        adapter = new NewsFeedRecyclerViewAdapter(newsItems, getActivity());
        recyclerView.setAdapter(adapter);
        if (ConnectivityStatus.isConnected(getActivity())) {
            getPosts();
        } else {
            Toast.makeText(getActivity(), "No Internet Connection", Toast.LENGTH_SHORT).show();
        }
        //getPosts();
        return view;
    }


    private void getPosts() {
        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage(getActivity().getResources().getString(R.string.wait));
        progressDialog.show();
        JsonObjectRequest postsJsonObject = new JsonObjectRequest(Request.Method.GET, Constants.URL_NEWSFEED, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                adapter.setNewsFeed(BuildData.extractNewsFeedJson(response, getActivity(), false));
                progressDialog.dismiss();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.dismiss();
                //Toast.makeText(context, error.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

        VolleySingleton.getInstance(getActivity()).addToRequestQueue(postsJsonObject);
    }
}
