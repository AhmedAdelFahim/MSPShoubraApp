package com.msp.mspshoubraapp.db;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "groupDays")
public class GroupDaysEntity {

    @PrimaryKey(autoGenerate = true)
    private long id;

    private String groupNum;

    private String day;

    @Ignore
    public GroupDaysEntity(String groupNum, String day) {
        this.groupNum = groupNum;
        this.day = day;
    }

    public GroupDaysEntity(long id, String groupNum, String day) {
        this.id = id;
        this.groupNum = groupNum;
        this.day = day;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getGroupNum() {
        return groupNum;
    }

    public void setGroupNum(String groupNum) {
        this.groupNum = groupNum;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }
}
