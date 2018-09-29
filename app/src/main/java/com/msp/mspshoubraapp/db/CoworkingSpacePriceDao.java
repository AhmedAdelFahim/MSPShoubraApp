package com.msp.mspshoubraapp.db;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface CoworkingSpacePriceDao {

    @Insert
    long insertCoworkingSpacePrice(CoworkingSpacePriceEntity coworkingSpacePriceEntity);

    @Query("SELECT * FROM coworkingSpacePrice WHERE coworkingSpaceId = :id")
    List<CoworkingSpacePriceEntity> loadAllPrices(String id);

    @Query("DELETE FROM coworkingSpacePrice")
    void deleteAllPrices();
}
