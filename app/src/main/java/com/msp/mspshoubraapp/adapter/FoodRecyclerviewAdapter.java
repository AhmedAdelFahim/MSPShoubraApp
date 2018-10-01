package com.msp.mspshoubraapp.adapter;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;


import com.msp.mspshoubraapp.R;
import com.msp.mspshoubraapp.db.RestaurantEntity;
import com.msp.mspshoubraapp.ui.HomeActivity;
import com.msp.mspshoubraapp.ui.MenuActivity;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.List;

public class FoodRecyclerviewAdapter extends RecyclerView.Adapter<FoodRecyclerviewAdapter.ViewHolder> {
    private Activity currActivity;
    private List<RestaurantEntity> dataList;
    private LayoutInflater inflater;

    public FoodRecyclerviewAdapter(Activity currActivity, List<RestaurantEntity> dataList) {
        this.currActivity = currActivity;
        this.dataList = dataList;
        this.inflater = LayoutInflater.from(currActivity);
    }

    @NonNull
    @Override
    public FoodRecyclerviewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = inflater.inflate(R.layout.food_list_item,parent,false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final RestaurantEntity currItem = dataList.get(position);
        holder.tilteTextview.setText(currItem.getName());
        String phones = currItem.getPhone1();
        if (currItem.getPhone2().trim().length() != 0) {
            phones += "\n" + currItem.getPhone2();
        }
        holder.telephoneTextview.setText(phones);
        holder.addressTextview.setText(currItem.getAddress());
        Picasso.get().load(new File(currItem.getImgLogo() + "/" + currItem.getName())).into(holder.imageView);
        holder.menuBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent menuIntent = new Intent(currActivity, MenuActivity.class);
                menuIntent.putExtra("restaurantId", currItem.getId());
                menuIntent.putExtra("restaurantName", currItem.getName());
                currActivity.startActivity(menuIntent);

            }
        });
        holder.mapBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currActivity.finish();
                Intent homeIntent = new Intent(currActivity, HomeActivity.class);
                homeIntent.putExtra("nextFregment", 1);
                homeIntent.putExtra("previousActivity", 3);
                homeIntent.putExtra("lat", currItem.getLat());
                homeIntent.putExtra("lng", currItem.getLng());
                currActivity.startActivity(homeIntent);

            }
        });
    }

    @Override
    public int getItemCount() {
        if (dataList == null) {
            return 0;
        }
        return dataList.size();
    }

    public void setRestaurant(List<RestaurantEntity> restaurantEntities) {
        if (restaurantEntities == null) {
            return;
        }
        this.dataList = restaurantEntities;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView tilteTextview;
        TextView addressTextview;
        TextView telephoneTextview;
        ImageView imageView;
        ImageButton mapBtn;
        ImageButton menuBtn;
        public ViewHolder(View itemView) {
            super(itemView);
            tilteTextview = itemView.findViewById(R.id.title_textview);
            addressTextview = itemView.findViewById(R.id.address_textview);
            telephoneTextview = itemView.findViewById(R.id.telephone_textView);
            imageView = itemView.findViewById(R.id.restaurant_logo);
            mapBtn = itemView.findViewById(R.id.map_btn);
            menuBtn = itemView.findViewById(R.id.menu_btn);
        }
    }

}
