package com.msp.mspshoubraapp.data;

import java.util.ArrayList;

public class PostData {

    private String body, id;
    private int timePosted;
    private ArrayList<String> images;
    private boolean isExpanded = false;

    public PostData(String id, String body, int timePosted, ArrayList<String> images) {
        this.body = body;
        this.timePosted = timePosted;
        this.images = images;
        this.id = id;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public int getTimePosted() {
        return timePosted;
    }

    public void setTimePosted(int timePosted) {
        this.timePosted = timePosted;
    }

    public ArrayList<String> getImages() {
        return images;
    }

    public void setImages(ArrayList<String> images) {
        this.images = images;
    }

    public boolean isExpanded() {
        return isExpanded;
    }

    public void setExpanded(boolean expanded) {
        isExpanded = expanded;
    }
}
