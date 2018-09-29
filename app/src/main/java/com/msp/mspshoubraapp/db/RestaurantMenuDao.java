package com.msp.mspshoubraapp.db;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

@Dao
public interface RestaurantMenuDao {

    @Insert
    long insertItem(RestaurantMenuEntity restaurantMenuEntity);

    @Query("SELECT * FROM restaurantMenu WHERE RestaurantId = :id")
    List<RestaurantMenuEntity> loadAllItems(String id);

    @Query("DELETE FROM restaurantMenu")
    void deleteAllItems();
}
