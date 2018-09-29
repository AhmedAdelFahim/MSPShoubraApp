package com.msp.mspshoubraapp.db;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "sections")
public class SectionsEntity {
    @PrimaryKey(autoGenerate = true)
    private long id;

    private int lectureNum, secNum;
    private long dayId;
    private String subjectName, place, instructor;

    public SectionsEntity(long id, int lectureNum, long dayId, int secNum, String subjectName, String place, String instructor) {
        this.id = id;
        this.lectureNum = lectureNum;
        this.dayId = dayId;
        this.secNum = secNum;
        this.subjectName = subjectName;
        this.place = place;
        this.instructor = instructor;
    }


    @Ignore
    public SectionsEntity(int lectureNum, long dayId, int secNum, String subjectName, String place, String instructor) {
        this.lectureNum = lectureNum;
        this.dayId = dayId;
        this.secNum = secNum;
        this.subjectName = subjectName;
        this.place = place;
        this.instructor = instructor;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getLectureNum() {
        return lectureNum;
    }

    public void setLectureNum(int lectureNum) {
        this.lectureNum = lectureNum;
    }

    public long getDayId() {
        return dayId;
    }

    public void setDayId(long dayId) {
        this.dayId = dayId;
    }

    public int getSecNum() {
        return secNum;
    }

    public void setSecNum(int secNum) {
        this.secNum = secNum;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getInstructor() {
        return instructor;
    }

    public void setInstructor(String instructor) {
        this.instructor = instructor;
    }
}
