package com.msp.mspshoubraapp.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.msp.mspshoubraapp.R;
import com.msp.mspshoubraapp.data.StudentActivityListItem;
import com.squareup.picasso.Picasso;

import java.util.List;

public class StudentActivityRecyclerviewAdapter extends RecyclerView.Adapter<StudentActivityRecyclerviewAdapter.ViewHolder> {

    private final Context currActivity;
    private final List<StudentActivityListItem> dataList;
    private final LayoutInflater inflater;
    final private listItemClickListener itemClickListener;

    public StudentActivityRecyclerviewAdapter(Context currActivity,
                                              List<StudentActivityListItem> dataList,
                                              listItemClickListener itemClickListener) {
        this.currActivity = currActivity;
        this.dataList = dataList;
        this.inflater = LayoutInflater.from(currActivity);
        this.itemClickListener = itemClickListener;
    }

    @NonNull
    @Override
    public StudentActivityRecyclerviewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = inflater.inflate(R.layout.studentactivities_list_item,parent,false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull StudentActivityRecyclerviewAdapter.ViewHolder holder, int position) {
        final StudentActivityListItem currItem = dataList.get(position);

        holder.titleTextView.setText(currItem.getTitle());
        Picasso.with(currActivity).load(currItem.getImg()).into(holder.imageView);
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //open StudentActivitiesActivity.class


            }
        });

    }

    @Override
    public int getItemCount() { return dataList.size(); }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView titleTextView;
        ImageView imageView;
        public ViewHolder(View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.studentactivity_title_textview);
            imageView = itemView.findViewById(R.id.studentactivity_logo);
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

    /*private boolean loadFragment(Fragment fragment, String addToStack) {
        if (fragment != null) {
            FragmentManager fragmentManager = ((FragmentActivity) currActivity).getSupportFragmentManager();
            if (addToStack.equals("")) {
                fragmentManager.beginTransaction()
                        .replace(R.id.fragment_container, fragment)
                        .commit();
                return true;
            } else {
                // Add the new tab fragment
                fragmentManager.beginTransaction()
                        .replace(R.id.fragment_container, fragment, addToStack)
                        .addToBackStack(addToStack + "Fragment")
                        .commit();
            }
        }
        return false;
    }*/
}

