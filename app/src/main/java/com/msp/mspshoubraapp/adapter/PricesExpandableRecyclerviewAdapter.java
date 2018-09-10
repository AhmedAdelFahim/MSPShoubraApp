package com.msp.mspshoubraapp.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.msp.mspshoubraapp.R;
import com.msp.mspshoubraapp.data.MenuListItem;
import com.thoughtbot.expandablerecyclerview.ExpandableRecyclerViewAdapter;
import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;
import com.thoughtbot.expandablerecyclerview.viewholders.ChildViewHolder;
import com.thoughtbot.expandablerecyclerview.viewholders.GroupViewHolder;

import java.util.List;

public class PricesExpandableRecyclerviewAdapter extends
        ExpandableRecyclerViewAdapter<PricesExpandableRecyclerviewAdapter.ParentViewHolder,
                PricesExpandableRecyclerviewAdapter.PricesViewHolder> {


    public PricesExpandableRecyclerviewAdapter(List<?extends ExpandableGroup> groups){
        super(groups);
    }

    @Override
    public ParentViewHolder onCreateGroupViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.expandable_recyclerview_item,parent,false);

        return new ParentViewHolder(view);
    }

    @Override
    public PricesViewHolder onCreateChildViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.menu_list_item,parent,false);

        return new PricesViewHolder(view);
    }

    @Override
    public void onBindChildViewHolder(PricesViewHolder holder, int flatPosition, ExpandableGroup group, int childIndex) {
        MenuListItem menuListItem = (MenuListItem) group.getItems().get(childIndex);
        holder.setPrice(menuListItem.getValue());
        holder.setDuration(menuListItem.getTitle());
    }

    @Override
    public void onBindGroupViewHolder(ParentViewHolder holder, int flatPosition, ExpandableGroup group) {
        holder.setViewHolderTitle(group.getTitle());
    }


    public class ParentViewHolder extends GroupViewHolder{

        TextView viewHolderTitle;
        public ParentViewHolder(View itemView){
            super(itemView);
            viewHolderTitle = itemView.findViewById(R.id.ERV_title);
        }

        public void setViewHolderTitle(String viewHolderTitle) { this.viewHolderTitle.setText(viewHolderTitle); }
    }


    public class PricesViewHolder extends ChildViewHolder{

        private TextView duration;
        private TextView price;
        public PricesViewHolder(View itemView){
            super(itemView);
            duration = itemView.findViewById(R.id.foodTitle);
            price = itemView.findViewById(R.id.price);
        }

        public void setDuration(String duration) { this.duration.setText(duration); }

        public void setPrice(String price) { this.price.setText(price); }
    }
}
