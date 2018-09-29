package com.msp.mspshoubraapp.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

@Database(entities = {StudentActivityEntity.class,
        CommitteeEntity.class,
        RestaurantEntity.class,
        RestaurantMenuEntity.class,
        PostsIdEntity.class,
        CoworkingSpaceEntity.class,
        CoworkingSpacePriceEntity.class,
        CoworkingSpaceImageEntity.class,
        SectionsEntity.class, DayLecturesEntity.class,
        GroupDaysEntity.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    private static final Object LOCK = new Object();
    private static final String DATABASE_NAME = "msp_app_db";
    private static AppDatabase sInstance;

    public static AppDatabase getInstance(Context context) {
        if (sInstance == null) {
            synchronized (LOCK) {
                //Log.d("Test", "Creating new database instance");
                sInstance = Room.databaseBuilder(context.getApplicationContext(),
                        AppDatabase.class, AppDatabase.DATABASE_NAME)
                        .build();
            }
        }
        //Log.d("Test", "Getting the database instance");
        return sInstance;
    }

    public abstract StudentActivitiesDao studentActivitiesDao();

    public abstract CommitteesDao committeesDao();

    public abstract RestaurantDao restaurantDao();

    public abstract RestaurantMenuDao restaurantMenuDao();

    public abstract PostsIdDao postsIdDao();

    public abstract CoworkingSpaceDao coworkingSpaceDao();

    public abstract CoworkingSpacePriceDao coworkingSpacePriceDao();

    public abstract CoworkingSpaceImageDao coworkingSpaceImageDao();

    public abstract LecturesTableDao lecturesTableDao();
}
