package com.msp.mspshoubraapp.db;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface CommitteesDao {

    @Insert
    long insertCommittee(CommitteeEntity committeeEntity);

    @Query("SELECT * FROM committees WHERE studentActivityId = :id")
    List<CommitteeEntity> loadAllCommittees(String id);

    @Query("DELETE FROM committees")
    void deleteAllCommittees();

}
