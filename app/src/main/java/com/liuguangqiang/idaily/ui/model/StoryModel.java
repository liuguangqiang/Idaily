package com.liuguangqiang.idaily.ui.model;

import com.liuguangqiang.asyncokhttp.AsyncOkHttp;
import com.liuguangqiang.asyncokhttp.BaseResponseHandler;
import com.liuguangqiang.idaily.entity.Story;
import com.liuguangqiang.idaily.utils.ApiUtils;

/**
 * Created by Eric on 15/6/9.
 */
public class StoryModel {

    public void getStory(int id, BaseResponseHandler responseHandler) {
        String url = ApiUtils.getStory(id);
        AsyncOkHttp.getInstance().get(url, responseHandler);
    }

    public String getBody(Story story) {
        if (story == null) return "";
        return loadDataWithCSS(story.getBody(), story.getCss().get(0));
    }

    private String loadDataWithCSS(String loadData, String cssPath) {
        String header = "<html><head><link href=\"%s\" type=\"text/css\" rel=\"stylesheet\"/></head><body>";
        String footer = "</body></html>";
        StringBuilder sb = new StringBuilder();
        sb.append(String.format(header, cssPath));
        sb.append(loadData);
        sb.append(footer);
        return sb.toString();
    }

}
