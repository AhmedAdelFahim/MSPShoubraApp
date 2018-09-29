package com.msp.mspshoubraapp.db;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "coworkingSpacePrice")
public class CoworkingSpacePriceEntity {
    @PrimaryKey(autoGenerate = true)
    private int id;

    private String coworkingSpaceId, itemName, itemPrice;

    @Ignore
    public CoworkingSpacePriceEntity(String coworkingSpaceId, String itemName, String itemPrice) {
        this.coworkingSpaceId = coworkingSpaceId;
        this.itemName = itemName;
        this.itemPrice = itemPrice;
    }

    public CoworkingSpacePriceEntity(int id, String coworkingSpaceId, String itemName, String itemPrice) {
        this.id = id;
        this.coworkingSpaceId = coworkingSpaceId;
        this.itemName = itemName;
        this.itemPrice = itemPrice;
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

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(String itemPrice) {
        this.itemPrice = itemPrice;
    }
}
