package com.msp.mspshoubraapp.data;

public class SubjectsDetailsListItem {

    private String section;
    private String marks;

    public SubjectsDetailsListItem(String section, String marks) {
        this.section = section;
        this.marks = marks;
    }

    public String getSection() { return section; }

    public String getMarks() { return marks; }

    public void setSection(String section) { this.section = section; }

    public void setMarks(String marks) {
        this.marks = marks;
    }
}
