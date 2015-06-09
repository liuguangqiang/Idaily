package com.liuguangqiang.idaily.presenter;


import android.content.Context;

import com.liuguangqiang.android.mvp.Presenter;
import com.liuguangqiang.idaily.entity.Story;
import com.liuguangqiang.idaily.model.StoryModel;
import com.liuguangqiang.idaily.view.StoryView;
import com.liuguangqiang.idaily.view.callback.StoryViewCallback;

/**
 * Created by Eric on 15/6/9.
 */
public class StoryPresenter extends Presenter<StoryView, StoryViewCallback> {

    private StoryModel mStoryModel;
    private Context mContext;

    public StoryPresenter(Context context, StoryView storyView) {
        super(storyView);
        mContext = context;
        mStoryModel = new StoryModel();
    }

    @Override
    protected void populateUi(StoryView view) {

    }

    @Override
    protected StoryViewCallback createUiCallback(final StoryView view) {
        return new StoryViewCallback() {

            @Override
            public void getStory(int id) {
                mStoryModel.getStory(mContext, id, new StoryModel.OnRequestListener() {
                    @Override
                    public void onSuccess(Story story) {
                        view.bindStory(story);
                        view.bindContent(mStoryModel.getContent(story));
                    }
                });
            }

        };
    }

}
