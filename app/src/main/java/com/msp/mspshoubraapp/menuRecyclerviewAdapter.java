package com.msp.mspshoubraapp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.List;

public class menuRecyclerviewAdapter extends RecyclerView.Adapter<menuRecyclerviewAdapter.ViewHolder> {
    private final Context currActivity;
    private final List<menuListItem> dataList;
    private final LayoutInflater inflater;

    public menuRecyclerviewAdapter(Context currActivity, List<menuListItem> dataList) {
        this.currActivity = currActivity;
        this.dataList = dataList;
        this.inflater = LayoutInflater.from(currActivity);
    }

    @NonNull
    @Override
    public menuRecyclerviewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = inflater.inflate(R.layout.menu_list_item,parent,false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final menuListItem currItem=dataList.get(position);
        holder.tilteTextview.setText(currItem.getTitle());
        holder.valueTextview.setText(currItem.getValue());
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView tilteTextview;
        TextView valueTextview;
        ImageButton menuBtn;
        public ViewHolder(View itemView) {
            super(itemView);
            tilteTextview= itemView.findViewById(R.id.foodTitle);
            valueTextview= itemView.findViewById(R.id.foodValue);
        }
    }
}
