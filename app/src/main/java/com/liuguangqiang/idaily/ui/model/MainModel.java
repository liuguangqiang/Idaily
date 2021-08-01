package com.liuguangqiang.idaily.ui.model;

import com.liuguangqiang.idaily.domain.RetrofitClient;
import com.liuguangqiang.idaily.domain.entity.BaseEntity;
import com.liuguangqiang.idaily.domain.entity.Daily;
import com.liuguangqiang.idaily.domain.entity.StorySection;
import com.liuguangqiang.idaily.domain.service.DailyService;
import com.liuguangqiang.idaily.ui.view.MainView;
import com.liuguangqiang.idaily.ui.view.RequestView;

import java.util.ArrayList;
import java.util.List;


import hugo.weaving.DebugLog;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;

/**
 * Created by Eric on 15/6/25.
 */
public class MainModel {

    private int lastDatetime = 0;
    private List<BaseEntity> data = new ArrayList<>();

    private DailyService dailyService;

    public MainModel() {
        dailyService = RetrofitClient.getInstance().create(DailyService.class);
    }

    private MainView view;
    private RequestView requestView;

    public void setView(MainView view, RequestView<BaseEntity> requestView) {
        this.view = view;
        this.requestView = requestView;
    }

    public void getDaily() {
        getDaily(lastDatetime);
    }

    private StorySection section;

    @DebugLog
    public void getDaily(final int datetime) {
        Timber.d("getDaily %d ", datetime);
        Observable<Daily> observable = datetime > 0 ? dailyService.getBefore(datetime) : dailyService.getLatest();
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Daily>() {
                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {
                        requestView.onRequestFinished();
                    }

                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Daily daily) {
                        if (daily != null) {
                            lastDatetime = daily.getDate();
                            data.clear();

                            if (datetime == 0) {
                                view.bindTopStories(daily.getTop_stories());
                            } else {
                                section = new StorySection(daily.getDate());
                                data.add(section);
                            }

                            data.addAll(daily.getStories());
                            requestView.onRequestSuccess(data);
                        }
                    }
                });


//        Observable<Daily> observabl1e = dailyService.getLatest();
//        observable.subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Observer<Daily>() {
//                    @Override
//                    public void onCompleted() {
//                        requestView.onRequestFinished();
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//
//                    }
//
//                    @Override
//                    public void onNext(Daily daily) {
//                    }
//                });
    }

}
