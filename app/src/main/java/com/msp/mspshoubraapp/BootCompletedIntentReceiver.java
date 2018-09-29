package com.msp.mspshoubraapp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class BootCompletedIntentReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(final Context context, Intent intent) {

        if ("android.intent.action.BOOT_COMPLETED".equals(intent.getAction())) {
            ////// reset your alrarms here
            SetBackgroundJobs.updateNewsFeed(context);
            SetBackgroundJobs.updateCoworkingSpaces(context);
            SetBackgroundJobs.updateLectureTable(context);
            SetBackgroundJobs.updateRestaurants(context);
            SetBackgroundJobs.updateStudentActivity(context);
        }

    }

}