package com.liuguangqiang.idaily.adapter;

import com.chad.library.adapter.base.BaseProviderMultiAdapter;
import com.chad.library.adapter.base.module.LoadMoreModule;
import com.liuguangqiang.idaily.api.entity.BaseEntity;
import com.liuguangqiang.idaily.api.entity.StorySection;
import com.liuguangqiang.idaily.adapter.ItemProvider.SectionItemProvider;
import com.liuguangqiang.idaily.adapter.ItemProvider.StoryItemProvider;

import org.jetbrains.annotations.Nullable;

import java.util.List;

/**
 * 首页列表adapter
 *
 * Created by Eric at 2021/7/31
 */
public class StoriesAdapter extends BaseProviderMultiAdapter<BaseEntity> implements LoadMoreModule {

    public static final int ITEM_STORY = 0;

    public static final int ITEM_SECTION = 1;

    public StoriesAdapter(@Nullable List<BaseEntity> data) {
        super(data);
        addItemProvider(new StoryItemProvider());
        addItemProvider(new SectionItemProvider());
    }

    @Override
    protected int getItemType(List<? extends BaseEntity> list, int i) {
        if (getItem(i) instanceof StorySection) {
            return ITEM_SECTION;
        }
        return ITEM_STORY;
    }
}
