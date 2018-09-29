package com.msp.mspshoubraapp.db;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

@Entity(tableName = "coworkingSpaces")
public class CoworkingSpaceEntity implements Parcelable {
    @PrimaryKey
    @NonNull
    private String id;

    private String name, address, imgLogo, phone1, phone2;

    private double lat, lng;

    public CoworkingSpaceEntity(@NonNull String id, String name, String address, String imgLogo, String phone1, String phone2, double lat, double lng) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.imgLogo = imgLogo;
        this.phone1 = phone1;
        this.phone2 = phone2;
        this.lat = lat;
        this.lng = lng;
    }

    @Ignore
    protected CoworkingSpaceEntity(Parcel in) {
        id = in.readString();
        name = in.readString();
        address = in.readString();
        imgLogo = in.readString();
        phone1 = in.readString();
        phone2 = in.readString();
        lat = in.readDouble();
        lng = in.readDouble();
    }

    @Ignore
    public static final Creator<CoworkingSpaceEntity> CREATOR = new Creator<CoworkingSpaceEntity>() {
        @Override
        public CoworkingSpaceEntity createFromParcel(Parcel in) {
            return new CoworkingSpaceEntity(in);
        }

        @Override
        public CoworkingSpaceEntity[] newArray(int size) {
            return new CoworkingSpaceEntity[size];
        }
    };

    @NonNull
    public String getId() {
        return id;
    }

    public void setId(@NonNull String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getImgLogo() {
        return imgLogo;
    }

    public void setImgLogo(String imgLogo) {
        this.imgLogo = imgLogo;
    }

    public String getPhone1() {
        return phone1;
    }

    public void setPhone1(String phone1) {
        this.phone1 = phone1;
    }

    public String getPhone2() {
        return phone2;
    }

    public void setPhone2(String phone2) {
        this.phone2 = phone2;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    @Ignore
    @Override
    public int describeContents() {
        return 0;
    }

    @Ignore
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(name);
        dest.writeString(address);
        dest.writeString(imgLogo);
        dest.writeString(phone1);
        dest.writeString(phone2);
        dest.writeDouble(lat);
        dest.writeDouble(lng);
    }
}
