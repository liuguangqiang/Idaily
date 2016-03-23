package com.liuguangqiang.idaily.utils.navigator;

import android.content.Context;
import android.os.Bundle;

import com.liuguangqiang.idaily.domain.entity.Story;
import com.liuguangqiang.idaily.ui.act.StoryActivity;

/**
 * Created by Eric on 15/10/11.
 */
public class Navigator extends BaseNavigator {

    private static Navigator instance = new Navigator();

    private Navigator() {
    }

    public static Navigator getInstance() {
        return instance;
    }

    public void openStory(Context context, Story story) {
        Bundle bundle = new Bundle();
        bundle.putParcelable(StoryActivity.ARG_STORY, story);
        start(context, StoryActivity.class, bundle);
    }

}
