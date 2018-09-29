package com.msp.mspshoubraapp.db;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

@Entity(tableName = "studentActivities")
public class StudentActivityEntity implements Parcelable {

    private String imgLogo, name, description;

    @PrimaryKey
    @NonNull
    private String id;

    public StudentActivityEntity(String id, String imgLogo, String name, String description) {
        this.imgLogo = imgLogo;
        this.name = name;
        this.description = description;
        this.id = id;
    }


    @Ignore
    protected StudentActivityEntity(Parcel in) {
        imgLogo = in.readString();
        name = in.readString();
        description = in.readString();
        id = in.readString();
    }

    @Ignore
    public static final Creator<StudentActivityEntity> CREATOR = new Creator<StudentActivityEntity>() {
        @Override
        public StudentActivityEntity createFromParcel(Parcel in) {
            return new StudentActivityEntity(in);
        }

        @Override
        public StudentActivityEntity[] newArray(int size) {
            return new StudentActivityEntity[size];
        }
    };

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImgLogo() {
        return imgLogo;
    }

    public void setImgLogo(String imgLogo) {
        this.imgLogo = imgLogo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Ignore
    @Override
    public int describeContents() {
        return 0;
    }

    @Ignore
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(imgLogo);
        dest.writeString(name);
        dest.writeString(description);
        dest.writeString(id);
    }
}
