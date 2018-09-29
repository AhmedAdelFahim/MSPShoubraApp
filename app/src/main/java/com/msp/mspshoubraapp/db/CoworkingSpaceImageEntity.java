package com.msp.mspshoubraapp.db;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "coworkingSpaceImages")
public class CoworkingSpaceImageEntity {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String coworkingSpaceId, image;

    @Ignore
    public CoworkingSpaceImageEntity(String coworkingSpaceId, String image) {
        this.coworkingSpaceId = coworkingSpaceId;
        this.image = image;
    }

    public CoworkingSpaceImageEntity(int id, String coworkingSpaceId, String image) {
        this.id = id;
        this.coworkingSpaceId = coworkingSpaceId;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCoworkingSpaceId() {
        return coworkingSpaceId;
    }

    public void setCoworkingSpaceId(String coworkingSpaceId) {
        this.coworkingSpaceId = coworkingSpaceId;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
