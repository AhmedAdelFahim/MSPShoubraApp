package com.msp.mspshoubraapp;

public class menuListItem {
    private String title;
    private String value;

    public menuListItem(String title, String value) {
        this.title = title;
        this.value = value;
    }

    public String getTitle() {

        return title;
    }

    public String getValue() {
        return value;
    }

    public void setTitle(String title) {

        this.title = title;
    }

    public void setValue(String value) {
        this.value = value+" LE";
    }
}
