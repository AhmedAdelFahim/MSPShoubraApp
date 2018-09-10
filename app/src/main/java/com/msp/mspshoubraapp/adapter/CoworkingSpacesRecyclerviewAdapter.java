package com.msp.mspshoubraapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.msp.mspshoubraapp.R;
import com.msp.mspshoubraapp.data.CoworkingSpacesListItem;
import com.msp.mspshoubraapp.ui.CoworkingSpacesActivity;
import com.msp.mspshoubraapp.ui.MenuFragment;
import com.squareup.picasso.Picasso;

import java.util.List;

public class CoworkingSpacesRecyclerviewAdapter extends RecyclerView.Adapter<CoworkingSpacesRecyclerviewAdapter.ViewHolder> {

    private final Context currActivity;
    private final List<CoworkingSpacesListItem> dataList;
    private final LayoutInflater inflater;
    final private listItemClickListener itemClickListener;

    public CoworkingSpacesRecyclerviewAdapter
            (Context currActivity, List<CoworkingSpacesListItem> dataList,
             listItemClickListener itemClickListener) {
        this.currActivity = currActivity;
        this.dataList = dataList;
        this.inflater = LayoutInflater.from(currActivity);
        this.itemClickListener = itemClickListener;
    }

    @NonNull
    @Override
    public CoworkingSpacesRecyclerviewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = inflater.inflate(R.layout.coworkingspaces_list_item,parent,false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CoworkingSpacesRecyclerviewAdapter.ViewHolder holder, int position) {
        final CoworkingSpacesListItem currItem = dataList.get(position);

        holder.titleTextView.setText(currItem.getTitle());
        holder.addressTextView.setText(currItem.getAddress());
        holder.telephoneTextView.setText(currItem.getTelephone());
        Picasso.with(currActivity).load(currItem.getImg()).into(holder.imageView);
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //open CoworkingSpacesActivity.class
            }
        });

    }

    @Override
    public int getItemCount() { return dataList.size(); }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView titleTextView;
        TextView addressTextView;
        TextView telephoneTextView;
        ImageView imageView;
        public ViewHolder(View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.coworking_title_textview);
            addressTextView = itemView.findViewById(R.id.coworking_address_textview);
            telephoneTextView = itemView.findViewById(R.id.coworking_telephone_textView);
            imageView = itemView.findViewById(R.id.coworking_logo);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int clickedPosition = getAdapterPosition();
            itemClickListener.onListItemClick(clickedPosition);
        }
    }

    public interface listItemClickListener{
        void onListItemClick(int clickedItemIndex);
    }
}
