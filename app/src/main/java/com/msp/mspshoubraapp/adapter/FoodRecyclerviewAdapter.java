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
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;


import com.msp.mspshoubraapp.R;
import com.msp.mspshoubraapp.data.FoodListItem;
import com.msp.mspshoubraapp.ui.MapFragment;
import com.msp.mspshoubraapp.ui.MenuFragment;
import com.squareup.picasso.Picasso;

import java.util.List;

public class FoodRecyclerviewAdapter extends RecyclerView.Adapter<FoodRecyclerviewAdapter.ViewHolder> {
    private final Context currActivity;
    private final List<FoodListItem> dataList;
    private final LayoutInflater inflater;

    public FoodRecyclerviewAdapter(Context currActivity, List<FoodListItem> dataList) {
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
        final FoodListItem currItem = dataList.get(position);
        holder.tilteTextview.setText(currItem.getTitle());
        holder.telephoneTextview.setText(currItem.getTelephone());
        holder.addressTextview.setText(currItem.getAddress());
        Picasso.with(currActivity).load(currItem.getImgURL()).into(holder.imageView);
        holder.menuBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*Intent menuIntent = new Intent(currActivity,);
                menuIntent.putExtra("menu",currItem.getMenu());
                currActivity.startActivity(menuIntent);*/
                loadFragment(new MenuFragment(), "menu");


            }
        });
        holder.mapBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*Intent mapIntent = new Intent(currActivity,MapsActivity.class);
                mapIntent.putExtra("distLat",currItem.getLat());
                mapIntent.putExtra("distLng",currItem.getLng());
                currActivity.startActivity(mapIntent);*/
                loadFragment(new MapFragment(), "map");
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
            tilteTextview = itemView.findViewById(R.id.title_textview);
            addressTextview = itemView.findViewById(R.id.address_textview);
            telephoneTextview = itemView.findViewById(R.id.telephone_textView);
            imageView = itemView.findViewById(R.id.restaurant_logo);
            mapBtn = itemView.findViewById(R.id.map_btn);
            menuBtn = itemView.findViewById(R.id.menu_btn);
        }
    }


    private boolean loadFragment(Fragment fragment, String addToStack) {
        if (fragment != null) {
            FragmentManager fragmentManager = ((FragmentActivity) currActivity).getSupportFragmentManager();
            if (addToStack.equals("")) {
                fragmentManager.beginTransaction()
                        .replace(R.id.fragment_container, fragment)
                        .commit();
                return true;
            } else {
                //fragmentManager.popBackStack("foodFragment", FragmentManager.POP_BACK_STACK_INCLUSIVE);
                // Add the new tab fragment
                fragmentManager.beginTransaction()
                        .replace(R.id.fragment_container, fragment, addToStack)
                        .addToBackStack(addToStack + "Fragment")
                        .commit();
            }
        }
        return false;
    }

}
