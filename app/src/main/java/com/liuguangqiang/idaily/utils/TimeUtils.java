package com.liuguangqiang.idaily.utils;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeUtils {

    /**
     * Return timestamp in milliseconds
     */
    public static long getTimestamp() {
        Timestamp ts = new Timestamp(System.currentTimeMillis());
        return ts.getTime();
    }

    /**
     * Return a timestamp in seconds.
     *
     * @return
     */
    public static long getTimestampSeconds() {
        String timestamp = String.valueOf(getTimestamp());
        return Long.parseLong(timestamp.substring(0, 10));
    }

    /**
     * Convert a timestampe to a date string.
     *
     * @param timestamp in milliseconds
     */
    public static String timeStamp2Date(long timestamp) {
        return timeStamp2Date(timestamp, "yyyy-MM-dd HH:mm:ss", false);
    }

    /**
     * Convert a timestampe to a date string.
     *
     * @param timestamp in seconds
     */
    public static String timeStamp2DateInSeconds(long timestamp) {
        return timeStamp2Date(timestamp, "yyyy-MM-dd HH:mm:ss", true);
    }

    public static String timeStamp2Date(long timestamp, String formats, boolean inSecond) {
        long ts = inSecond ? timestamp * 1000 : timestamp;
        String date = new SimpleDateFormat(formats).format(new Date(ts));
        return date;
    }

    /**
     * Return a datetime string by a format.
     *
     * @param format such as:yyyyMMdd, yyyy-MM-dd
     * @return
     */
    public static String getDateTimeByFormat(String format) {
        return convertByFormatter(new Date(), format);
    }


    /**
     * @param date
     * @param format
     * @return
     */
    public static String convertByFormatter(final Date date, final String format) {
        SimpleDateFormat formatter = new SimpleDateFormat(format);
        return formatter.format(date);
    }
}
