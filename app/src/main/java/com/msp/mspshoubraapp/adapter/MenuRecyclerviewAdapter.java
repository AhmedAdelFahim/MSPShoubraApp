package com.msp.mspshoubraapp.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.msp.mspshoubraapp.data.MenuListItem;
import com.msp.mspshoubraapp.R;

import java.util.List;

public class MenuRecyclerviewAdapter extends RecyclerView.Adapter<MenuRecyclerviewAdapter.ViewHolder> {
    private final Context currActivity;
    private final List<MenuListItem> dataList;
    private final LayoutInflater inflater;

    public MenuRecyclerviewAdapter(Context currActivity, List<MenuListItem> dataList) {
        this.currActivity = currActivity;
        this.dataList = dataList;
        this.inflater = LayoutInflater.from(currActivity);
    }

    @NonNull
    @Override
    public MenuRecyclerviewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = inflater.inflate(R.layout.menu_list_item,parent,false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final MenuListItem currItem = dataList.get(position);
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
            valueTextview = itemView.findViewById(R.id.price);
        }
    }
}
