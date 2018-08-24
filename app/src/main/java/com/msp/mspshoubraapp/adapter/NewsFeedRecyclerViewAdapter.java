package com.msp.mspshoubraapp.adapter;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


import com.msp.mspshoubraapp.R;
import com.msp.mspshoubraapp.data.NewsItem;

import java.util.List;

import nb.scode.lib.ExpandableTextView;


/**
 * Created by Laila Al Ashkar on 8/14/2018.
 */

public class NewsFeedRecyclerViewAdapter extends RecyclerView.Adapter<NewsFeedRecyclerViewAdapter.Holder> {

    private List<NewsItem> newsItemList;
    private Activity activity;

    public NewsFeedRecyclerViewAdapter(List<NewsItem> newsItemList, Activity activity) {
        this.newsItemList = newsItemList;
        this.activity = activity;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_recyclerview_news, parent, false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, final int position) {
        final NewsItem newsItem = newsItemList.get(position);

        holder.news_title.setOnStateChangeListener(
                new ExpandableTextView.OnStateChangeListener() {
                    @Override
                    public void onStateChange(boolean isShrink) {

                        newsItem.setShrink(isShrink);
                        newsItemList.set(position, newsItem);
                    }
                });
        holder.news_title.setText(newsItem.getNewsTitle());
        holder.news_title.resetState(newsItemList.get(position).isShrink());
        holder.news_img.setImageResource(newsItem.getNewsImg());
        holder.news_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(activity, android.R.style.Theme_Black_NoTitleBar_Fullscreen);
                LayoutInflater inflater = activity.getLayoutInflater();
                View dialogView = inflater.inflate(R.layout.image_full_screen_dialog, null);
                builder.setView(dialogView);

                ImageView imageView = dialogView.findViewById(R.id.image_full_screen);
                imageView.setImageResource(newsItem.getNewsImg());

                builder.create().show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return newsItemList.size();
    }


    public class Holder extends RecyclerView.ViewHolder {

        public ImageView news_img;
        public ExpandableTextView news_title;
        public ImageView up, down;

        public Holder(View itemView) {
            super(itemView);

            news_img = itemView.findViewById(R.id.recyclerview_item_img);
            news_title = itemView.findViewById(R.id.recyclerview_item_txt);
            up = itemView.findViewById(R.id.up);
            down = itemView.findViewById(R.id.down);
        }
    }
}
