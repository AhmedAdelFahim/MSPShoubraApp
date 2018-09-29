package com.msp.mspshoubraapp.db;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

@Dao
public interface RestaurantDao {

    @Insert(onConflict = REPLACE)
    long insertRestaurant(RestaurantEntity restaurantEntity);

    @Query("SELECT * FROM restaurant")
    LiveData<List<RestaurantEntity>> loadAllRestaurants();

    @Query("DELETE FROM restaurant")
    void deleteAllRestaurants();
}
