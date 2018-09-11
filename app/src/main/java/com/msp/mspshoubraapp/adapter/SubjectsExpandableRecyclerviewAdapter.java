package com.msp.mspshoubraapp.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.msp.mspshoubraapp.R;
import com.msp.mspshoubraapp.data.SubjectsDetailsListItem;
import com.thoughtbot.expandablerecyclerview.ExpandableRecyclerViewAdapter;
import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;
import com.thoughtbot.expandablerecyclerview.viewholders.ChildViewHolder;
import com.thoughtbot.expandablerecyclerview.viewholders.GroupViewHolder;

import java.util.List;

public class SubjectsExpandableRecyclerviewAdapter extends
        ExpandableRecyclerViewAdapter<SubjectsExpandableRecyclerviewAdapter.ParentViewHolder,
                SubjectsExpandableRecyclerviewAdapter.SubjectViewHolder> {


    public SubjectsExpandableRecyclerviewAdapter(List<?extends ExpandableGroup> groups){
        super(groups);
    }

    @Override
    public ParentViewHolder onCreateGroupViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.subjects_list_item,parent,false);

        return new ParentViewHolder(view);
    }

    @Override
    public SubjectViewHolder onCreateChildViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.menu_list_item,parent,false);

        return new SubjectViewHolder(view);
    }

    @Override
    public void onBindChildViewHolder(SubjectViewHolder holder, int flatPosition, ExpandableGroup group, int childIndex) {
        SubjectsDetailsListItem subjectsDetailsListItem =
                (SubjectsDetailsListItem) group.getItems().get(childIndex);

        holder.setSectionName(subjectsDetailsListItem.getSection());
        holder.setMarks(subjectsDetailsListItem.getMarks());
    }

    @Override
    public void onBindGroupViewHolder(ParentViewHolder holder, int flatPosition, ExpandableGroup group) {
        holder.setSubjectName(group.getTitle());
    }


    public class ParentViewHolder extends GroupViewHolder {

        TextView subjectName;
        public ParentViewHolder(View itemView){
            super(itemView);
            subjectName = itemView.findViewById(R.id.subject_name);
        }

        public void setSubjectName(String subjectName) { this.subjectName.setText(subjectName); }
    }


    public class SubjectViewHolder extends ChildViewHolder {

        private TextView sectionName;
        private TextView marks;
        public SubjectViewHolder(View itemView){
            super(itemView);
            sectionName = itemView.findViewById(R.id.foodTitle);
            marks = itemView.findViewById(R.id.price);
        }

        public void setSectionName(String sectionName) { this.sectionName.setText(sectionName); }

        public void setMarks(String marks) { this.marks.setText(marks); }
    }
}
