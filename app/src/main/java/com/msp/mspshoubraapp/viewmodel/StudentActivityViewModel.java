package com.msp.mspshoubraapp.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.support.annotation.NonNull;
import android.util.Log;

import com.msp.mspshoubraapp.db.AppDatabase;
import com.msp.mspshoubraapp.db.StudentActivityEntity;

import java.util.List;

public class StudentActivityViewModel extends AndroidViewModel {

    private AppDatabase appDatabase;
    private LiveData<List<StudentActivityEntity>> studentActivities;

    public StudentActivityViewModel(@NonNull Application application) {
        super(application);
        appDatabase = AppDatabase.getInstance(this.getApplication());
        studentActivities = appDatabase.studentActivitiesDao().loadAllStudentActivities();
    }

    public LiveData<List<StudentActivityEntity>> getAllStudentActivities() {
        return studentActivities;
    }
}
