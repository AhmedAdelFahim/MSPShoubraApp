package com.msp.mspshoubraapp.db;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

@Dao
public interface StudentActivitiesDao {

    @Insert(onConflict = REPLACE)
    long insertStudentActivity(StudentActivityEntity studentActivityEntity);

    @Query("SELECT * FROM studentActivities")
    LiveData<List<StudentActivityEntity>> loadAllStudentActivities();

    @Query("DELETE FROM studentActivities")
    void deleteAllStudentActivities();

}
