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

import com.msp.mspshoubraapp.db.CoworkingSpaceEntity;
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
        View itemView = inflater.inflate(R.layout.coworkingspaces_list_item,parent,false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CoworkingSpacesRecyclerviewAdapter.ViewHolder holder, int position) {
        final CoworkingSpaceEntity currItem = dataList.get(position);

        holder.titleTextView.setText(currItem.getName());
        //holder.addressTextView.setText(currItem.getAddress());
        //holder.telephoneTextView.setText(currItem.getTelephone());
        Picasso.get().load(new File(currItem.getImgLogo())).into(holder.imageView);
        //holder.imageView.setImageResource(R.drawable.majal_logo);
    }

    @Override
    public int getItemCount() {
        if (dataList == null) {
            return 0;
        }
        return dataList.size();
    }

    public void setCoworkingSpaces(List<CoworkingSpaceEntity> coworkingSpaceEntities) {
        if (coworkingSpaceEntities == null) {
            return;
        }
        this.dataList = coworkingSpaceEntities;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView titleTextView;
        TextView addressTextView;
        TextView telephoneTextView;
        ImageView imageView;
        public ViewHolder(View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.coworking_title_textview);
            //addressTextView = itemView.findViewById(R.id.coworking_address_textview);
            //telephoneTextView = itemView.findViewById(R.id.coworking_telephone_textView);
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
