package com.liuguangqiang.idaily.domain.service;

import com.liuguangqiang.idaily.domain.entity.Story;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by Eric on 16/3/21.
 */
public interface StoryService {

    @GET("story/{story_id}")
    Observable<Story> getStory(@Path("story_id") int storyId);

}
