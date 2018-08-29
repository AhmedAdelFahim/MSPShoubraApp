package com.msp.mspshoubraapp.data;

import java.util.ArrayList;

/**
 * Created by Laila Al Ashkar on 8/14/2018.
 */

public class NewsItem {
    private String newsTitle;
    private int newsImg;
    private ArrayList<Integer> imageList;
    private boolean isExpanded = false;



    public NewsItem(String newsTitle, ArrayList<Integer> imageList) {
        this.newsTitle = newsTitle;
        this.imageList = imageList;
    }

    public String getNewsTitle() {
        return newsTitle;
    }

    public int getNewsImg() {
        return newsImg;
    }

    public ArrayList<Integer> getImageList() {
        return imageList;
    }

    public boolean isExpanded() {
        return isExpanded;
    }

    public void setExpanded(boolean expand) {
        isExpanded = expand;
    }
}
