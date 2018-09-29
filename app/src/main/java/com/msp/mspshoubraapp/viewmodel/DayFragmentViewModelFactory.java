package com.msp.mspshoubraapp.viewmodel;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;

import com.msp.mspshoubraapp.db.AppDatabase;

public class DayFragmentViewModelFactory extends ViewModelProvider.NewInstanceFactory {
    private AppDatabase appDatabase;
    private String day, groupNum;

    public DayFragmentViewModelFactory(AppDatabase appDatabase, String day, String groupNum) {
        this.appDatabase = appDatabase;
        this.day = day;
        this.groupNum = groupNum;
    }

    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        //noinspection unchecked
        return (T) new DayFragmentViewModel(appDatabase, day, groupNum);
    }
}
