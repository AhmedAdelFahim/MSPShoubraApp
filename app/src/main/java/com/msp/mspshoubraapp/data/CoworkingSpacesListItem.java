package com.msp.mspshoubraapp.data;

public class CoworkingSpacesListItem {

    private String title;
    private String address;
    private String telephone;
    private String img;

    public CoworkingSpacesListItem(String title, String address, String telephone, String img){
        this.title = title;
        this.address = address;
        this.telephone = telephone;
        this.img = img;
    }

    public String getImg() { return img; }

    public String getTitle() { return title; }

    public String getAddress() { return address; }

    public String getTelephone() { return telephone; }

    public void setAddress(String address) { this.address = address; }

    public void setTelephone(String telephone) { this.telephone = telephone; }

    public void setImg(String img) { this.img = img; }

    public void setTitle(String title) { this.title = title; }
}
