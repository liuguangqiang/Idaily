package com.liuguangqiang.idaily.ui.model;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.liuguangqiang.idaily.domain.RetrofitClient;
import com.liuguangqiang.idaily.domain.entity.BaseEntity;
import com.liuguangqiang.idaily.domain.entity.Daily;
import com.liuguangqiang.idaily.domain.entity.Story;
import com.liuguangqiang.idaily.domain.entity.StorySection;
import com.liuguangqiang.idaily.domain.service.DailyService;
import com.liuguangqiang.idaily.ui.view.MainView;
import com.liuguangqiang.idaily.ui.view.RequestView;

import java.util.ArrayList;
import java.util.List;


import hugo.weaving.DebugLog;
import io.reactivex.Observable;
import io.reactivex.Observer;
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

    private MutableLiveData<List<BaseEntity>> liveData = new MutableLiveData<>();
    private MutableLiveData<List<Story>> topLiveData = new MutableLiveData<>();

    private DailyService dailyService;

    public MainModel() {
        dailyService = RetrofitClient.getInstance().create(DailyService.class);
        liveData.setValue(data);
    }

    public MutableLiveData<List<BaseEntity>> getLiveData() {
        return liveData;
    }

    public MutableLiveData<List<Story>> getTopLiveData() {
        return topLiveData;
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
//                                view.bindTopStories(daily.getTop_stories());
                                topLiveData.postValue(daily.getTop_stories());
                            } else {
                                section = new StorySection(daily.getDate());
                                data.add(section);
                            }

                            Timber.d("getDaily size:" + daily.getStories().size());
                            data.addAll(daily.getStories());
                            liveData.postValue(data);
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
