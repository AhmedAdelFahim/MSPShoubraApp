package com.msp.mspshoubraapp.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.jsibbold.zoomage.ZoomageView;
import com.msp.mspshoubraapp.R;
import com.msp.mspshoubraapp.db.CoworkingSpaceImageEntity;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class CoworkingSpacesPhotosRecyclerviewAdapter
        extends RecyclerView.Adapter<CoworkingSpacesPhotosRecyclerviewAdapter.ViewHolder> {
    private List<CoworkingSpaceImageEntity> photosList;
    private final LayoutInflater inflater;
    private Activity activity;

    public CoworkingSpacesPhotosRecyclerviewAdapter(List<CoworkingSpaceImageEntity> photosList,
                                                    Activity activity) {
        this.photosList = photosList;
        this.activity = activity;
        this.inflater = LayoutInflater.from(activity);
    }

    @NonNull
    @Override
    public CoworkingSpacesPhotosRecyclerviewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = inflater.inflate(R.layout.item_recyclerview_coworkingspace_photos, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CoworkingSpacesPhotosRecyclerviewAdapter.ViewHolder holder, final int position) {
        final CoworkingSpaceImageEntity currItem = photosList.get(position);

        Picasso.get().load(new File(currItem.getImage())).into(holder.imageView);
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //enterFullScreen(position, photosList);
            }
        });

    }


    @Override
    public int getItemCount() {
        return photosList.size();
    }

    public void setImages(ArrayList<CoworkingSpaceImageEntity> imageList) {
        if (imageList == null) {
            return;
        }
        this.photosList = imageList;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;

        public ViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.recyclerview_item_coworkingspace_photo);
        }
    }


}
