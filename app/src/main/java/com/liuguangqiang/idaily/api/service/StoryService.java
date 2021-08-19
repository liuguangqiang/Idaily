package com.liuguangqiang.idaily.api.service;

import com.liuguangqiang.idaily.entity.Story;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Eric on 16/3/21.
 */
public interface StoryService {

    @GET("story/{story_id}")
    Observable<Story> getStory(@Path("story_id") int storyId);

}
