package com.msp.mspshoubraapp.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.msp.mspshoubraapp.R;
import com.msp.mspshoubraapp.db.CommitteeEntity;
import com.thoughtbot.expandablerecyclerview.ExpandableRecyclerViewAdapter;
import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;
import com.thoughtbot.expandablerecyclerview.viewholders.ChildViewHolder;
import com.thoughtbot.expandablerecyclerview.viewholders.GroupViewHolder;

import java.util.ArrayList;
import java.util.List;

public class CommitteesExpandableRecyclerviewAdapter extends
        ExpandableRecyclerViewAdapter<CommitteesExpandableRecyclerviewAdapter.ParentViewHolder,
                CommitteesExpandableRecyclerviewAdapter.DescriptionViewHolder> {

    public CommitteesExpandableRecyclerviewAdapter(List<? extends ExpandableGroup> groups) {
        super(groups);
    }


    @Override
    public ParentViewHolder onCreateGroupViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.expandable_recyclerview_item, parent, false);

        return new ParentViewHolder(view);
    }

    @Override
    public DescriptionViewHolder onCreateChildViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.committees_list_item, parent, false);

        return new DescriptionViewHolder(view);
    }

    @Override
    public void onBindChildViewHolder(DescriptionViewHolder holder, int flatPosition, ExpandableGroup group, int childIndex) {
        CommitteeEntity committeesListItem = (CommitteeEntity) group.getItems().get(childIndex);

        holder.setTitle(committeesListItem.getCommitteeName());
        holder.setDescription(committeesListItem.getCommitteeDescription());
    }

    @Override
    public void onBindGroupViewHolder(ParentViewHolder holder, int flatPosition, ExpandableGroup group) {
        holder.setViewHolderTitle(group.getTitle());
    }

    /*public void setCommittees(ArrayList<CommitteeEntity> committeeEntities)
    {
        if (committeeEntities == null) {
            return;
        }
        this.dataList = studentActivityEntities;
        notifyDataSetChanged();
    }*/
    public class ParentViewHolder extends GroupViewHolder {

        TextView viewHolderTitle;

        public ParentViewHolder(View itemView) {
            super(itemView);
            viewHolderTitle = itemView.findViewById(R.id.ERV_title);
        }

        public void setViewHolderTitle(String viewHolderTitle) {
            this.viewHolderTitle.setText(viewHolderTitle);
        }
    }


    public class DescriptionViewHolder extends ChildViewHolder {

        private TextView title;
        private TextView description;

        public DescriptionViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.committee_name);
            description = itemView.findViewById(R.id.committee_description_textview);
        }

        public void setTitle(String title) {
            this.title.setText(title);
        }

        public void setDescription(String description) {
            this.description.setText(description);
        }
    }
}
