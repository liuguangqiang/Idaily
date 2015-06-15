package com.liuguangqiang.idaily.model;

import com.liuguangqiang.asyncokhttp.AsyncOkHttp;
import com.liuguangqiang.asyncokhttp.JsonResponseHandler;
import com.liuguangqiang.idaily.entity.Story;
import com.liuguangqiang.idaily.uitls.ApiUtils;

/**
 * Created by Eric on 15/6/9.
 */
public class StoryModel {

    public void getStory(int id, final OnRequestListener listener) {
        String url = ApiUtils.getStory(id);
        AsyncOkHttp.getInstance().get(url, new JsonResponseHandler<Story>(Story.class) {
            @Override
            public void onSuccess(Story result) {
                if (result != null) {
                    listener.onSuccess(result);
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
