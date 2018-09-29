package com.msp.mspshoubraapp.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.msp.mspshoubraapp.db.AppDatabase;
import com.msp.mspshoubraapp.db.RestaurantEntity;
import com.msp.mspshoubraapp.db.StudentActivityEntity;

import java.util.List;

public class RestaurantViewModel extends AndroidViewModel {
    private AppDatabase appDatabase;
    private LiveData<List<RestaurantEntity>> restaurants;

    public RestaurantViewModel(@NonNull Application application) {
        super(application);
        appDatabase = AppDatabase.getInstance(this.getApplication());
        restaurants = appDatabase.restaurantDao().loadAllRestaurants();
    }

    public LiveData<List<RestaurantEntity>> getAllRestaurants() {
        return restaurants;
    }
}
