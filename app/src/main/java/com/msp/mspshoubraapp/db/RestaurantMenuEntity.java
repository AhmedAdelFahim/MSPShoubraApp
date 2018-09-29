package com.msp.mspshoubraapp.db;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "restaurantMenu")
public class RestaurantMenuEntity {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String restaurantId;

    private String name;

    private String value;


    @Ignore
    public RestaurantMenuEntity(String restaurantId, String name, String value) {
        this.restaurantId = restaurantId;
        this.name = name;
        this.value = value;
    }

    public RestaurantMenuEntity(int id, String restaurantId, String name, String value) {
        this.id = id;
        this.restaurantId = restaurantId;
        this.name = name;
        this.value = value;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(String restaurantId) {
        restaurantId = restaurantId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
