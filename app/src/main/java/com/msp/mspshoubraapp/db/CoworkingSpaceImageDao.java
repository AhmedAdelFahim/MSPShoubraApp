package com.msp.mspshoubraapp.db;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface CoworkingSpaceImageDao {

    @Insert
    long insertImage(CoworkingSpaceImageEntity coworkingSpaceImageEntity);

    @Query("SELECT * FROM coworkingSpaceImages WHERE coworkingSpaceId = :id")
    List<CoworkingSpaceImageEntity> loadAllImages(String id);

    @Query("DELETE FROM coworkingSpaceImages")
    void deleteAllImages();


    @Query("SELECT * FROM coworkingSpaceImages WHERE image = :img")
    List<CoworkingSpaceImageEntity> findImage(String img);

}
