package com.msp.mspshoubraapp.adapter;

import android.app.Activity;
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
import com.msp.mspshoubraapp.db.StudentActivityEntity;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.List;

public class StudentActivityRecyclerviewAdapter extends RecyclerView.Adapter<StudentActivityRecyclerviewAdapter.ViewHolder> {

    private final Activity currActivity;
    private List<StudentActivityEntity> dataList;
    private final LayoutInflater inflater;
    final private listItemClickListener itemClickListener;

    public StudentActivityRecyclerviewAdapter(Activity currActivity,
                                              List<StudentActivityEntity> dataList,
                                              listItemClickListener itemClickListener) {
        this.currActivity = currActivity;
        this.dataList = dataList;
        this.inflater = LayoutInflater.from(currActivity);
        this.itemClickListener = itemClickListener;
    }

    @NonNull
    @Override
    public StudentActivityRecyclerviewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = inflater.inflate(R.layout.studentactivities_list_item, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final StudentActivityRecyclerviewAdapter.ViewHolder holder, int position) {
        final StudentActivityEntity currItem = dataList.get(position);

        holder.titleTextView.setText(currItem.getName());

        Picasso
                .get()
                .load(new File(currItem.getImgLogo() + "/" + currItem.getName()))
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
        if (dataList == null) {
            return 0;
        }
        return dataList.size();
    }

    public void setStudentActivity(List<StudentActivityEntity> studentActivityEntities) {
        if (studentActivityEntities == null) {
            return;
        }
        this.dataList = studentActivityEntities;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView titleTextView;
        ImageView imageView;
        ProgressBar progressBar;

        public ViewHolder(View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.studentactivity_title_textview);
            imageView = itemView.findViewById(R.id.studentactivity_logo);
            progressBar = itemView.findViewById(R.id.sa_progressBar);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int clickedPosition = getAdapterPosition();
            itemClickListener.onListItemClick(clickedPosition);
        }
    }

    public interface listItemClickListener {
        void onListItemClick(int clickedItemIndex);
    }


}

