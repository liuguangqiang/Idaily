package com.liuguangqiang.idaily.ui.viewmodel;

import android.databinding.BaseObservable;
import android.view.View;

import com.liuguangqiang.idaily.domain.entity.Story;
import com.liuguangqiang.idaily.utils.navigator.Navigator;

/**
 * Created by Eric on 15/10/11.
 */
public class TopStoryViewModel extends BaseObservable {

    private Story story;

    public void setStory(Story s) {
        this.story = s;
    }

    public View.OnClickListener getPicClickListener() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigator.getInstance().openStory(v.getContext(), story);
            }
        };
    }

    public String getImage() {
        if (story == null) return "";

        return story.getImage();
    }

}
