package com.msp.mspshoubraapp.adapter;

import android.content.Context;
import android.support.annotation.NonNull;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.msp.mspshoubraapp.R;

import com.msp.mspshoubraapp.db.CoworkingSpaceEntity;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.List;

public class CoworkingSpacesRecyclerviewAdapter extends RecyclerView.Adapter<CoworkingSpacesRecyclerviewAdapter.ViewHolder> {

    private final Context currActivity;
    private List<CoworkingSpaceEntity> dataList;
    private final LayoutInflater inflater;
    final private listItemClickListener itemClickListener;

    public CoworkingSpacesRecyclerviewAdapter
            (Context currActivity, List<CoworkingSpaceEntity> dataList,
             listItemClickListener itemClickListener) {
        this.currActivity = currActivity;
        this.dataList = dataList;
        this.inflater = LayoutInflater.from(currActivity);
        this.itemClickListener = itemClickListener;
    }

    @NonNull
    @Override
    public CoworkingSpacesRecyclerviewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        /*
         * Construct a new view by inflate it from an XML layout file to be used in
         *  onBindViewHolder method it will be re-used to display different items.
         */
        View itemView = inflater.inflate(R.layout.coworkingspaces_list_item, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final CoworkingSpacesRecyclerviewAdapter.ViewHolder holder, int position) {
        /*
         * Get entities and bind them to the view holder using the entity model
         *  using Picasso to load an image through the internet
         */
        final CoworkingSpaceEntity currItem = dataList.get(position);

        holder.titleTextView.setText(currItem.getName());

        Picasso
                .get()
                .load(new File(currItem.getImgLogo()))
                .error(R.drawable.ic_error_black_24dp)
                .into(holder.imageView, new Callback() {
                    @Override
                    public void onSuccess() {
                        holder.progressBar.setVisibility(View.GONE);
                    }

                    @Override
                    public void onError(Exception e) {

                    }
                });
    }

    @Override
    public int getItemCount() {
        //return number of current item.
        if (dataList == null) {
            return 0;
        }
        return dataList.size();
    }

    public void setCoworkingSpaces(List<CoworkingSpaceEntity> coworkingSpaceEntities) {
        //if getting successes set entities and notify.
        if (coworkingSpaceEntities == null) {
            return;
        }
        this.dataList = coworkingSpaceEntities;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        /**
         * ViewHolder model that is being inflated from XML file
         */
        TextView titleTextView;

        ImageView imageView;
        ProgressBar progressBar;

        ViewHolder(View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.coworking_title_textview);
            imageView = itemView.findViewById(R.id.coworking_logo);
            progressBar = itemView.findViewById(R.id.cs_progressBar);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int clickedPosition = getAdapterPosition();
            itemClickListener.onListItemClick(clickedPosition);
        }
    }

    public interface listItemClickListener {
        /**
         * interface for item action event.
         */

        void onListItemClick(int clickedItemIndex);
    }
}
