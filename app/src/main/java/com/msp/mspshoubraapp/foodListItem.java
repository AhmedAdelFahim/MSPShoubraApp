package com.msp.mspshoubraapp;

public class foodListItem {
    private String title;
    private String telephone;
    private String address;
    private String imgURL;

    public foodListItem(String title, String telephone, String address, String imgURL) {
        this.title = title;
        this.telephone = telephone;
        this.address = address;
        this.imgURL = imgURL;
    }

    public void setTitle(String title) {

        this.title = title;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setImgURL(String imgURL) {
        this.imgURL = imgURL;
    }

    public String getTitle() {

        return title;
    }

    public String getTelephone() {
        return telephone;
    }

    public String getAddress() {
        return address;
    }

    public String getImgURL() {
        return imgURL;
    }
}
