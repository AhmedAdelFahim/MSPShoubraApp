package com.msp.mspshoubraapp.data;

import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;

import java.util.List;

public class SubjectsListItem extends ExpandableGroup {

    private String subjectName;

    public SubjectsListItem(String subjectName, List items){
        super(subjectName, items);
        this.subjectName = subjectName;
    }

    public String getSubjectName() { return subjectName; }

    public void setSubjectName(String subjectName) { this.subjectName = subjectName; }
}
