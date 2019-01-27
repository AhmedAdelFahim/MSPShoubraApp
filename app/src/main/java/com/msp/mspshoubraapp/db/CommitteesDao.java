package com.msp.mspshoubraapp.db;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface CommitteesDao {

    @Query("DELETE FROM committees")
    void deleteAllCommittees();

    @Insert
    void insertCommittee(CommitteeEntity paramCommitteeEntity);

    @Query("SELECT * FROM committees WHERE studentActivityId = :id")
    List<CommitteeEntity> loadAllCommittees(String id);

}
