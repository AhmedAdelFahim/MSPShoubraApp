package com.msp.mspshoubraapp.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.msp.mspshoubraapp.R;
import com.msp.mspshoubraapp.db.RestaurantMenuEntity;

import java.util.List;

public class MenuRecyclerviewAdapter extends RecyclerView.Adapter<MenuRecyclerviewAdapter.ViewHolder> {
    private final Context currActivity;
    private final List<RestaurantMenuEntity> dataList;
    private final LayoutInflater inflater;

    public MenuRecyclerviewAdapter(Context currActivity, List<RestaurantMenuEntity> dataList) {
        this.currActivity = currActivity;
        this.dataList = dataList;
        this.inflater = LayoutInflater.from(currActivity);
    }

    @NonNull
    @Override
    public MenuRecyclerviewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        /*
         * Construct a new view by inflate it from an XML layout file to be used in
         *  onBindViewHolder method it will be re-used to display different items.
         */
        View itemView = inflater.inflate(R.layout.menu_list_item,parent,false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        /*
         * Get entities and bind them to the view holder using the entity model
         */
        final RestaurantMenuEntity currItem = dataList.get(position);
        holder.tilteTextview.setText(currItem.getName());
        holder.valueTextview.setText(currItem.getValue());
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        /**
         * ViewHolder model that is being inflated from XML file
         */
        TextView tilteTextview;
        TextView valueTextview;

        public ViewHolder(View itemView) {
            super(itemView);
            tilteTextview= itemView.findViewById(R.id.foodTitle);
            valueTextview = itemView.findViewById(R.id.price);
        }
    }
}
