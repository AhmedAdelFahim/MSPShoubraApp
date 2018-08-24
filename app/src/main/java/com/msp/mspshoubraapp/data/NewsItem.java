package com.msp.mspshoubraapp.data;

/**
 * Created by Laila Al Ashkar on 8/14/2018.
 */

public class NewsItem {
    private String newsTitle;
    private int newsImg;
    private boolean isShrink = true;

    public NewsItem(String newsTitle, int newsImg) {
        this.newsTitle = newsTitle;
        this.newsImg = newsImg;
    }

    public String getNewsTitle() {
        return newsTitle;
    }

    public int getNewsImg() {
        return newsImg;
    }

    public boolean isShrink() {
        return isShrink;
    }

    public void setShrink(boolean shrink) {
        isShrink = shrink;
    }
}
