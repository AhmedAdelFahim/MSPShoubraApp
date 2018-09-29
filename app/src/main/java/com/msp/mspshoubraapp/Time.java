package com.msp.mspshoubraapp;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by ahmed on 30/08/17.
 */

public class Time {
    /*public static String convertUnixToString(long unixtime) {

        Date date = new Date(unixtime * 1000L);
        SimpleDateFormat formatter = new SimpleDateFormat("EEE, dd MMM yyyy 'at' HH:mm z", Locale.ENGLISH);
        return formatter.format(date);
    }


    public static String convertUnixToStringHackerrankCalendar(long unixtime) {

        Date date = new Date(unixtime * 1000L);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH);
        return formatter.format(date);
    }
*/

    public static String convertUnixToString(long unixtime) {

        Date date = new Date(unixtime * 1000L);
        SimpleDateFormat formatter = new SimpleDateFormat("EEE, dd MMM yyyy 'at' HH:mm", Locale.ENGLISH);
        return formatter.format(date);
    }

    public static long currentTime() {
        return System.currentTimeMillis() / 1000L;
    }

    /*public static long convertStringToUnix(String str_date) {

        DateFormat formatter;
        formatter = new SimpleDateFormat("dd MMM yyyy HH:mm:ss", Locale.ENGLISH);
        Date date = null;
        try {
            date = formatter.parse(str_date);
        } catch (ParseException e) {
            //Log.d("EEE",e.getMessage());
            e.printStackTrace();
        }
        return date.getTime();
    }

    public static String codechefTime(String timeIST) {
        return convertUnixToString((convertStringToUnix(timeIST) / 1000L) - 12600L);
    }


    public static long convertStringToUnixGMT(String str_date, String pattern) {
        //str_date = "2018-05-13 03:30:00";
        //TimeZone timeZone = TimeZone.getDefault();
        //timeZone.setRawOffset(7200000);
        //Log.d("TIMETEST",str_date);
        TimeZone defaultTimeZone = TimeZone.getDefault();
        //Log.d("TIMETEST","dTZ = "+TimeZone.getDefault().getRawOffset());
        long defaultTimeZoneRawOffset = defaultTimeZone.getRawOffset();
        TimeZone.setDefault(TimeZone.getTimeZone("GMT"));
        //Log.d("TIMETEST","dTZ = "+TimeZone.getDefault().getRawOffset());
        DateFormat formatter;
        formatter = new SimpleDateFormat(pattern, Locale.ENGLISH);
        Date date = null;
        try {
            date = formatter.parse(str_date);
        } catch (ParseException e) {
            //Log.d("EEE",e.getMessage());
            e.printStackTrace();
        }

        long unixTime = date.getTime();
        //Log.d("TIMETEST","unixTime = "+unixTime);
        TimeZone.setDefault(defaultTimeZone);
        //Log.d("TIMETEST","dTZ = "+convertUnixToString((unixTime)/1000));
        return ((unixTime) / 1000L);
        //Log.d("TIMETEST",""+date.getTime());
        //return date.getTime();
    }

*/
}
