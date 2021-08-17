package com.liuguangqiang.idaily.api.entity;

import com.liuguangqiang.idaily.adapter.StoriesAdapter;

/**
 * Created by Eric on 15/6/9.
 */
public class StorySection extends BaseEntity {

    public int datetime;

    public StorySection(int datetime) {
        this.datetime = datetime;
    }

    @Override
    public int getItemType() {
        return StoriesAdapter.ITEM_SECTION;
    }
}
