package com.msp.mspshoubraapp.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.msp.mspshoubraapp.db.AppDatabase;
import com.msp.mspshoubraapp.db.CoworkingSpaceEntity;
import com.msp.mspshoubraapp.db.StudentActivityEntity;

import java.util.List;

public class CoworkingSpaceViewModel extends AndroidViewModel {
    private AppDatabase appDatabase;
    private LiveData<List<CoworkingSpaceEntity>> coworkingSpaces;

    public CoworkingSpaceViewModel(@NonNull Application application) {
        super(application);
        appDatabase = AppDatabase.getInstance(this.getApplication());
        coworkingSpaces = appDatabase.coworkingSpaceDao().loadAllCoworkingSpaces();
    }

    public LiveData<List<CoworkingSpaceEntity>> getCoworkingSpaces() {
        return coworkingSpaces;
    }
}
