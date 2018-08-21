package com.msp.mspshoubraapp;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class foodRecyclerviewAdapter extends RecyclerView.Adapter<foodRecyclerviewAdapter.ViewHolder> {
    private final Context currActivity;
    private final List<foodListItem> dataList;
    private final LayoutInflater inflater;

    public foodRecyclerviewAdapter(Context currActivity, List<foodListItem> dataList) {
        this.currActivity = currActivity;
        this.dataList = dataList;
        this.inflater = LayoutInflater.from(currActivity);
    }

    @NonNull
    @Override
    public foodRecyclerviewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = inflater.inflate(R.layout.food_list_item,parent,false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final foodListItem currItem=dataList.get(position);
        holder.tilteTextview.setText(currItem.getTitle());
        holder.telephoneTextview.setText(currItem.getTelephone());
        holder.addressTextview.setText(currItem.getAddress());
        Picasso.with(currActivity).load(currItem.getImgURL()).into(holder.imageView);
        holder.menuBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //handle after creating menu activity.
            }
        });
        holder.mapBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mapIntent = new Intent(currActivity,MapsActivity.class);
                mapIntent.putExtra("distLat",currItem.getLat());
                mapIntent.putExtra("distLng",currItem.getLng());
                currActivity.startActivity(mapIntent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataList.size();
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
            tilteTextview= itemView.findViewById(R.id.titleTextview);
            addressTextview= itemView.findViewById(R.id.addressTextview);
            telephoneTextview= itemView.findViewById(R.id.telephonetextView);
            imageView= itemView.findViewById(R.id.imageView);
            mapBtn= itemView.findViewById(R.id.mapBtn);
            menuBtn= itemView.findViewById(R.id.menuBtn);
        }
    }
}
