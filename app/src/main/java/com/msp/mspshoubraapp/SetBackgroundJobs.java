package com.msp.mspshoubraapp;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

import static android.app.PendingIntent.FLAG_UPDATE_CURRENT;
import static android.content.Context.ALARM_SERVICE;

public class SetBackgroundJobs {

    public static void updateNewsFeed(Context context) {
        Intent intent = new Intent(context, BackgroundJobs.class);
        intent.putExtra("id", Constants.NEWSFEED_BACKGROUND_ID);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, Constants.NEWSFEED_BACKGROUND_ID, intent, FLAG_UPDATE_CURRENT);
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(ALARM_SERVICE);
        alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP, Time.currentTime() * 1000 + Constants.TWO_HOURS_IN_MILLISECONDS, Constants.SIX_HOURS_IN_MILLISECONDS, pendingIntent);

    }

    public static void updateLectureTable(Context context) {
        Intent intent = new Intent(context, BackgroundJobs.class);
        intent.putExtra("id", Constants.LECTURES_TABLE_BACKGROUND_ID);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, Constants.LECTURES_TABLE_BACKGROUND_ID, intent, FLAG_UPDATE_CURRENT);
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(ALARM_SERVICE);
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, Time.currentTime() * 1000 + Constants.TWO_HOURS_IN_MILLISECONDS, Constants.ONE_WEEK_IN_MILLISECONDS, pendingIntent);
    }

    public static void updateRestaurants(Context context) {
        Intent intent = new Intent(context, BackgroundJobs.class);
        intent.putExtra("id", Constants.RESTAURANT_BACKGROUND_ID);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, Constants.RESTAURANT_BACKGROUND_ID, intent, FLAG_UPDATE_CURRENT);
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(ALARM_SERVICE);
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, Time.currentTime() * 1000 + Constants.TWO_HOURS_IN_MILLISECONDS, Constants.ONE_WEEK_IN_MILLISECONDS, pendingIntent);
    }

    public static void updateCoworkingSpaces(Context context) {
        Intent intent = new Intent(context, BackgroundJobs.class);
        intent.putExtra("id", Constants.CO_WORKING_SPACE_BACKGROUND_ID);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, Constants.CO_WORKING_SPACE_BACKGROUND_ID, intent, FLAG_UPDATE_CURRENT);
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(ALARM_SERVICE);
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, Constants.TWO_HOURS_IN_MILLISECONDS, Constants.ONE_WEEK_IN_MILLISECONDS, pendingIntent);
    }

    public static void updateStudentActivity(Context context) {
        Intent intent = new Intent(context, BackgroundJobs.class);
        intent.putExtra("id", Constants.STUDENT_ACTIVITY_BACKGROUND_ID);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, Constants.STUDENT_ACTIVITY_BACKGROUND_ID, intent, FLAG_UPDATE_CURRENT);
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(ALARM_SERVICE);
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, Time.currentTime() * 1000 + Constants.TWO_HOURS_IN_MILLISECONDS, Constants.ONE_WEEK_IN_MILLISECONDS, pendingIntent);
    }

}
