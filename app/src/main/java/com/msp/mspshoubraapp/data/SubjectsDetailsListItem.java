package com.msp.mspshoubraapp.data;

public class SubjectsDetailsListItem {

    private String section;
    private int marks;

    public SubjectsDetailsListItem(String section, int marks) {
        this.section = section;
        this.marks = marks;
    }

    public String getSection() { return section; }

    public int getMarks() { return marks; }

    public void setSection(String section) { this.section = section; }

    public void setMarks(int marks) {
        this.marks = marks;
    }
}
