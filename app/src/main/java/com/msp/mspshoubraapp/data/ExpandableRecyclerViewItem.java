package com.msp.mspshoubraapp.data;

import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;

import java.util.List;

public class ExpandableRecyclerViewItem extends ExpandableGroup {

    private String title;

    public ExpandableRecyclerViewItem(String title, List items){
        super(title, items);
        this.title = title;


    }
}
