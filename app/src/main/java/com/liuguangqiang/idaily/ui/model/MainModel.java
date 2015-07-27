package com.liuguangqiang.idaily.ui.model;

import com.liuguangqiang.asyncokhttp.AsyncOkHttp;
import com.liuguangqiang.asyncokhttp.JsonResponseHandler;
import com.liuguangqiang.idaily.entity.BaseEntity;
import com.liuguangqiang.idaily.entity.Daily;
import com.liuguangqiang.idaily.entity.StorySection;
import com.liuguangqiang.idaily.listener.RequestCallback;
import com.liuguangqiang.idaily.utils.ApiUtils;
import com.liuguangqiang.idaily.ui.viewmodel.MainViewModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Eric on 15/6/25.
 */
public class MainModel {

    private int lastDatetime = 0;

    private List<BaseEntity> data = new ArrayList<>();

    private RequestCallback<List<BaseEntity>> callback;

    private MainViewModel.OnDisplayTopStoryListener onDisplayTopStoryListener;

    public MainModel(RequestCallback requestCallback, MainViewModel.OnDisplayTopStoryListener onDisplayTopStoryListener) {
        this.callback = requestCallback;
        this.onDisplayTopStoryListener = onDisplayTopStoryListener;
    }

    public void getDaily() {
        getDaily(lastDatetime);
    }

    public void getDaily(final int datetime) {
        String url = datetime > 0 ? ApiUtils.getNewsBefore(datetime) : ApiUtils.getLatest();
        AsyncOkHttp.getInstance().get(url, new JsonResponseHandler<Daily>(Daily.class) {
            @Override
            public void onSuccess(Daily daily) {
                if (daily != null) {
                    if (datetime == 0) {
                        onDisplayTopStoryListener.onDisplayTopStories(daily.getTop_stories());
                    }

                    StorySection section = new StorySection(daily.getDate());
                    lastDatetime = daily.getDate();

                    data.clear();
                    data.add(section);
                    data.addAll(daily.getStories());

                    callback.requestSuccess(data);
                }
            }

            @Override
            public void onFinish() {
                callback.requestFinished();
            }
        });
    }

}
