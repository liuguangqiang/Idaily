package com.liuguangqiang.idaily.view;

import com.liuguangqiang.android.mvp.BaseUi;
import com.liuguangqiang.idaily.entity.Story;
import com.liuguangqiang.idaily.view.callback.StoryViewCallback;

/**
 * Created by Eric on 15/6/9.
 */
public interface StoryView extends BaseUi<StoryViewCallback> {

    void bindStory(Story story);

    void bindContent(String content);

}
