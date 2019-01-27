package com.msp.mspshoubraapp.db;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
@Entity(tableName = "committees")
public class CommitteeEntity {


    @PrimaryKey(autoGenerate = true)
    private int id;

    private String studentActivityId;
    private String committeeName, committeeDescription;


    @Ignore
    public CommitteeEntity(String studentActivityId, String committeeName, String committeeDescription) {
        this.studentActivityId = studentActivityId;
        this.committeeName = committeeName;
        this.committeeDescription = committeeDescription;
    }

    public CommitteeEntity(int id, String studentActivityId, String committeeName, String committeeDescription) {
        this.id = id;
        this.studentActivityId = studentActivityId;
        this.committeeName = committeeName;
        this.committeeDescription = committeeDescription;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStudentActivityId() {
        return studentActivityId;
    }

    public void setStudentActivityId(String studentActivityId) {
        this.studentActivityId = studentActivityId;
    }

    public String getCommitteeName() {
        return committeeName;
    }

    public void setCommitteeName(String committeeName) {
        this.committeeName = committeeName;
    }

    public String getCommitteeDescription() {
        return committeeDescription;
    }

    public void setCommitteeDescription(String committeeDescription) {
        this.committeeDescription = committeeDescription;
    }
}