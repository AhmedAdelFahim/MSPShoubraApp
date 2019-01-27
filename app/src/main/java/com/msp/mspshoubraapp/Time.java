package com.msp.mspshoubraapp;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by ahmed on 30/08/17.
 */

public class Time {

    public static String convertUnixToString(long unixtime) {

        Date date = new Date(unixtime * 1000L);
        SimpleDateFormat formatter = new SimpleDateFormat("EEE, dd MMM yyyy 'at' HH:mm", Locale.ENGLISH);
        return formatter.format(date);
    }

    public static long currentTime() {
        return System.currentTimeMillis() / 1000L;
    }


}
