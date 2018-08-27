package com.msp.mspshoubraapp.data;

public class IntervalData {
    private String subjectName, place, instructor, sectionNum;

    public IntervalData(String sectionNum, String subjectName, String place, String instructor) {
        this.sectionNum = sectionNum;
        this.subjectName = subjectName;
        this.place = place;
        this.instructor = instructor;
    }

    public String getSectionNum() {
        return sectionNum;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public String getPlace() {
        return place;
    }

    public String getInstructor() {
        return instructor;
    }
}
