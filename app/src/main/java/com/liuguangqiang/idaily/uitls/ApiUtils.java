package com.liuguangqiang.idaily.uitls;

/**
 * Created by Eric on 15/6/6.
 */
public class ApiUtils {

    private static final String HOST_NAME = "http://news-at.zhihu.com/api/4";

    private static final String FORMAT = HOST_NAME + "/%s";

    public static String getLatest() {
        return String.format(FORMAT, "news/latest");
    }

    public static String getNewsBefore(int datetime) {
        String action = String.format("stories/before/%d?client=0", datetime);
        return String.format(FORMAT, action);
    }

    public static String getStory(int id) {
        String action = String.format("story/%d", id);
        return String.format(FORMAT, action);
    }

}
