package com.msp.mspshoubraapp.db;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface PostsIdDao {

    @Query("SELECT * FROM postsId")
    List<PostsIdEntity> loadAllIds();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insertPostId(PostsIdEntity id);

    @Query("DELETE FROM postsId")
    void deleteAllIds();

    @Query("SELECT * FROM postsId WHERE postId = :postId")
    List<PostsIdEntity> findPost(String postId);

}
