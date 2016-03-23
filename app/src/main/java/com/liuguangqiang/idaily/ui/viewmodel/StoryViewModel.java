package com.liuguangqiang.idaily.ui.viewmodel;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.os.Bundle;

import com.liuguangqiang.idaily.domain.entity.Story;
import com.liuguangqiang.idaily.ui.act.StoryActivity;
import com.liuguangqiang.idaily.ui.model.StoryModel;

import javax.inject.Inject;

import rx.Observer;

/**
 * Created by Eric on 15/6/23.
 */
public class StoryViewModel extends BaseObservable {

    private StoryModel storyModel;

    @Bindable
    public Story story;

    public String title = "";

    @Inject
    public StoryViewModel(StoryModel storyMode) {
        this.storyModel = storyMode;
    }

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
        return storyModel.getBody(story);
    }

    public void setStory(Story story) {
        this.story = story;
        this.title = story.getTitle();
        notifyChange();
    }

    public Story getStory() {
        return story;
    }

    public void getStory(int id) {
        storyModel.getStory(id, new Observer<Story>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Story story) {
                setStory(story);
            }
        });
    }

}
