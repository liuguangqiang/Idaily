package com.liuguangqiang.idaily.ui.model;

import com.liuguangqiang.idaily.domain.RetrofitClient;
import com.liuguangqiang.idaily.domain.entity.Story;
import com.liuguangqiang.idaily.domain.service.StoryService;


import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


/**
 * Created by Eric on 15/6/9.
 */
public class StoryModel {

    private StoryService storyService;

     public StoryModel() {
        storyService = RetrofitClient.getInstance().create(StoryService.class);
    }

//    public Subscription getStory(int id, Observer<Story> observer) {
//        return storyService.getStory(id).subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(observer);
//    }

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
