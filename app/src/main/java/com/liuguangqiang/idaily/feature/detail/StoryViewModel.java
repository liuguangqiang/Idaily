package com.liuguangqiang.idaily.feature.detail;


import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import android.os.Bundle;

import com.liuguangqiang.idaily.api.ServiceFactory;
import com.liuguangqiang.idaily.entity.Story;
import com.liuguangqiang.idaily.api.service.StoryService;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Eric on 15/6/23.
 */
public class StoryViewModel extends ViewModel {

    public Story story;

    public String title = "";

    public MutableLiveData<Story> storyLiveData = new MutableLiveData<>();

    public void pushArguments(Bundle bundle) {
        Story story = bundle.getParcelable(StoryActivity.ARG_STORY);
        if (story != null) {
            setTitle(story.getTitle());
            getStory(story.id);
        }
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        if (story == null) return "";

        return story.getImage();
    }

    public String getBody() {
        return getBody(storyLiveData.getValue());
    }

    public void setStory(Story story) {
        storyLiveData.postValue(story);
    }

    public Story getStory() {
        return story;
    }

    public void getStory(int id) {
        StoryService storyService = ServiceFactory.getInstance().create(StoryService.class);
        storyService.getStory(id).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<Story>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Story story) {
                setStory(story);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
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
