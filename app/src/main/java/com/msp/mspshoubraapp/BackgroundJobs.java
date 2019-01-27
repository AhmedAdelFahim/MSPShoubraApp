package com.msp.mspshoubraapp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.msp.mspshoubraapp.networking.FetchDataFromApi;

public class BackgroundJobs extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        int id = intent.getIntExtra("id", -1);

        if (id == Constants.NEWSFEED_BACKGROUND_ID) {
            FetchDataFromApi.loadPosts(context, true);
        } else if (id == Constants.LECTURES_TABLE_BACKGROUND_ID) {
            FetchDataFromApi.loadLecturesTable(context, true);
        } else if (id == Constants.RESTAURANT_BACKGROUND_ID) {
            FetchDataFromApi.loadRestaurants(context, true);
        } else if (id == Constants.CO_WORKING_SPACE_BACKGROUND_ID) {
            FetchDataFromApi.loadcoworkingSpaces(context, true);
        } else if (id == Constants.STUDENT_ACTIVITY_BACKGROUND_ID) {
            FetchDataFromApi.loadStudentActivities(context, true);
        }
    }
}
