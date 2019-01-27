package com.msp.mspshoubraapp.db;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

@Dao
public interface CoworkingSpaceDao {

    @Insert(onConflict = REPLACE)
    void insertCoworkingSpace(CoworkingSpaceEntity coworkingSpaceEntity);

    @Query("SELECT * FROM coworkingSpaces")
    LiveData<List<CoworkingSpaceEntity>> loadAllCoworkingSpaces();

    @Query("DELETE FROM coworkingSpaces")
    void deleteAllCoworkingSpaces();
}
