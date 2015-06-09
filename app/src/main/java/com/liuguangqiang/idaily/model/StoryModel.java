package com.liuguangqiang.idaily.model;

import android.content.Context;

import com.google.gson.Gson;
import com.liuguangqiang.idaily.entity.Story;
import com.liuguangqiang.idaily.uitls.ApiUtils;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.TextHttpResponseHandler;

import org.apache.http.Header;

/**
 * Created by Eric on 15/6/9.
 */
public class StoryModel {

    public void getStory(Context context, int id, final OnRequestListener listener) {
        String url = ApiUtils.getStory(id);
        AsyncHttpClient httpClient = new AsyncHttpClient();
        httpClient.get(context, url, new TextHttpResponseHandler() {
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {
                Story story = new Gson().fromJson(responseString, Story.class);
                if (story != null) {
                    listener.onSuccess(story);
                }
            }
        });
    }

    public String getContent(Story story) {
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

    public interface OnRequestListener {

        void onSuccess(Story story);

    }

}
