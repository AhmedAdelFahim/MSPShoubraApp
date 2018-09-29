package com.msp.mspshoubraapp.db;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "dayLectures")
public class DayLecturesEntity {

    @PrimaryKey(autoGenerate = true)
    private long id;

    private long groupId;
    private int lectureNum;

    private String startTime, endTime;


    @Ignore
    public DayLecturesEntity(long groupId, int lectureNum, String startTime, String endTime) {
        this.groupId = groupId;
        this.lectureNum = lectureNum;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public DayLecturesEntity(long id, long groupId, int lectureNum, String startTime, String endTime) {
        this.id = id;
        this.groupId = groupId;
        this.lectureNum = lectureNum;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getGroupId() {
        return groupId;
    }

    public void setGroupId(long groupId) {
        this.groupId = groupId;
    }

    public int getLectureNum() {
        return lectureNum;
    }

    public void setLectureNum(int lectureNum) {
        this.lectureNum = lectureNum;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }
}
