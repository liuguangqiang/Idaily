package com.liuguangqiang.idaily.ui.viewmodel;

import android.databinding.BaseObservable;
import android.view.View;

import com.liuguangqiang.asyncokhttp.JsonResponseHandler;
import com.liuguangqiang.framework.utils.IntentUtils;
import com.liuguangqiang.framework.utils.ToastUtils;
import com.liuguangqiang.idaily.entity.Story;
import com.liuguangqiang.idaily.ui.model.StoryModel;

/**
 * Created by Eric on 15/6/23.
 */
public class StoryViewModel extends BaseObservable {

    private StoryModel mStoryModel;

    private Story story;

    public StoryViewModel() {
        this.mStoryModel = new StoryModel();
    }

    public String getTitle() {
        if (story == null) return "";

        return story.getTitle();
    }

    public String getImage() {
        if (story == null) return "";

        return story.getImage();
    }

    public String getBody() {
        return mStoryModel.getBody(story);
    }

    public void setStory(Story story) {
        this.story = story;
        notifyChange();
    }

    public View.OnClickListener getFavClickListener() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtils.show(v.getContext(), "OnClick Fav");
            }
        };
    }

    public View.OnClickListener getShareClickListener() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IntentUtils.openShare(v.getContext(), story.getTitle() + story.share_url, "");
            }
        };
    }

    public void getStory(int id) {
        mStoryModel.getStory(id, new JsonResponseHandler<Story>(Story.class) {

            @Override
            public void onSuccess(Story result) {
                if (result != null) {
                    setStory(result);
                }
            }
        });
    }

}
