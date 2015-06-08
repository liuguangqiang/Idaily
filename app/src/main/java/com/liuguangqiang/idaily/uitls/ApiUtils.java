package com.liuguangqiang.idaily.uitls;

/**
 * Created by Eric on 15/6/6.
 */
public class ApiUtils {

    public static String getLatest() {
        return "http://news.at.zhihu.com/api/4/news/latest";
    }

    public static String getStory(int id) {
        return String.format("http://news-at.zhihu.com/api/4/story/%d", id);
    }

    public static String getStoryExtra(int id) {
        return String.format("http://news-at.zhihu.com/api/4/story-extra/%d", id);
    }

}
