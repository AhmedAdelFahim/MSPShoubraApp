package com.msp.mspshoubraapp.db;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "postsId")
public class PostsIdEntity {

    /*@PrimaryKey(autoGenerate = true)
    private int id;*/


    @PrimaryKey
    @NonNull
    private String postId;

    public PostsIdEntity(String postId) {
        this.postId = postId;
    }

   /* public PostsIdEntity(int id, String postId) {
        this.id = id;
        this.postId = postId;
    }*/

   /* public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }*/

    public String getPostId() {
        return postId;
    }

    public void setPostId(String postId) {
        this.postId = postId;
    }
}
