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

import java.util.List;

public class CommitteesExpandableRecyclerviewAdapter extends
        ExpandableRecyclerViewAdapter<CommitteesExpandableRecyclerviewAdapter.ParentViewHolder,
                CommitteesExpandableRecyclerviewAdapter.DescriptionViewHolder> {

    public CommitteesExpandableRecyclerviewAdapter(List<? extends ExpandableGroup> groups) {
        super(groups);
    }


    @Override
    public ParentViewHolder onCreateGroupViewHolder(ViewGroup parent, int viewType) {
        /*
         * Construct a new view by inflate it from an XML layout file to be used in
         *  onBindViewHolder method it will be re-used to display different items.
         *      for outer list
         */
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.expandable_recyclerview_item, parent, false);

        return new ParentViewHolder(view);
    }

    @Override
    public DescriptionViewHolder onCreateChildViewHolder(ViewGroup parent, int viewType) {
        /*
         * Construct a new view by inflate it from an XML layout file to be used in
         *  onBindViewHolder method it will be re-used to display different items.
         *      for inner list
         */
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.committees_list_item, parent, false);

        return new DescriptionViewHolder(view);
    }

    @Override
    public void onBindChildViewHolder(DescriptionViewHolder holder, int flatPosition, ExpandableGroup group, int childIndex) {
        /*
         * Get entities and bind them to the view holder using the entity model
         *  for inner recycler view.
         */
        CommitteeEntity committeesListItem = (CommitteeEntity) group.getItems().get(childIndex);

        holder.setTitle(committeesListItem.getCommitteeName());
        holder.setDescription(committeesListItem.getCommitteeDescription());
    }

    @Override
    public void onBindGroupViewHolder(ParentViewHolder holder, int flatPosition, ExpandableGroup group) {
        /*
         * Get entities and bind them to the view holder using the entity model
         *  for outer recycler view.
         */
        holder.setViewHolderTitle(group.getTitle());
    }


    class ParentViewHolder extends GroupViewHolder {
        /**
         * ViewHolder model that is being inflated from XML file
         *  for outer list
         */

        TextView viewHolderTitle;

        ParentViewHolder(View itemView) {
            super(itemView);
            viewHolderTitle = itemView.findViewById(R.id.ERV_title);
        }

        void setViewHolderTitle(String viewHolderTitle) {
            this.viewHolderTitle.setText(viewHolderTitle);
        }
    }


    public class DescriptionViewHolder extends ChildViewHolder {
        /**
         * ViewHolder model that is being inflated from XML file
         *  for inner list
         */
        private TextView title;
        private TextView description;

        DescriptionViewHolder(View itemView) {
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
