package com.liuguangqiang.idaily.utils.events;

import com.liuguangqiang.idaily.domain.entity.Story;

import java.util.List;

/**
 * Created by Eric on 16/3/21.
 */
public class TopStoriesEvent {

    public List<Story> stories;

    public TopStoriesEvent(List<Story> stories) {
        this.stories = stories;
    }

}
