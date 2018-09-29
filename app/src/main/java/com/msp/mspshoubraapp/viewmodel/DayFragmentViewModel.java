package com.msp.mspshoubraapp.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.msp.mspshoubraapp.db.AppDatabase;
import com.msp.mspshoubraapp.db.DayLecturesEntity;

import java.util.List;

public class DayFragmentViewModel extends ViewModel {

    private AppDatabase appDatabase;
    private String day, groupNum;

    private LiveData<List<DayLecturesEntity>> lecturesList;

    public DayFragmentViewModel(AppDatabase appDatabase, String day, String groupNum) {
        this.appDatabase = appDatabase;
        this.day = day;
        this.groupNum = groupNum;
        lecturesList = appDatabase.lecturesTableDao().loadAllLectures(groupNum, day);
    }

    public LiveData<List<DayLecturesEntity>> getLecturesList() {
        return lecturesList;
    }
}
