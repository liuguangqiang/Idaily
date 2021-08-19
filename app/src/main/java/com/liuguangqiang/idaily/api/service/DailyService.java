package com.liuguangqiang.idaily.api.service;

import com.liuguangqiang.idaily.entity.Daily;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Eric on 16/3/21.
 */
public interface DailyService {

    @GET("news/latest")
    Observable<Daily> getLatest();

    @GET("stories/before/{datetime}?client=0")
    Observable<Daily> getBefore(@Path("datetime") int datetime);

}
