package com.liuguangqiang.idaily.ui.viewmodel;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import android.os.Bundle;

import com.liuguangqiang.idaily.domain.RetrofitClient;
import com.liuguangqiang.idaily.domain.entity.Story;
import com.liuguangqiang.idaily.domain.service.StoryService;
import com.liuguangqiang.idaily.ui.act.StoryActivity;
import com.liuguangqiang.idaily.ui.model.StoryModel;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Eric on 15/6/23.
 */
public class StoryViewModel extends BaseObservable {

    private StoryModel storyModel;

    @Bindable
    public Story story;

    public String title = "";

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
        StoryService storyService = RetrofitClient.getInstance().create(StoryService.class);
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

}
