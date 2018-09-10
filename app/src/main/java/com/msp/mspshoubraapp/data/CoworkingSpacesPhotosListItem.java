package com.msp.mspshoubraapp.data;

import java.util.ArrayList;

public class CoworkingSpacesPhotosListItem {

    private int img;

    private ArrayList<Integer> imageList;

    public CoworkingSpacesPhotosListItem(int img){ this.img = img; }

    public void setImg(int img) { this.img = img; }

    public int getImg() { return img; }

    public ArrayList<Integer> getImageList() { return imageList; }
}
