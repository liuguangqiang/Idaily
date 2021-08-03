package com.liuguangqiang.idaily.ui.adapter;

import com.chad.library.adapter.base.BaseProviderMultiAdapter;
import com.chad.library.adapter.base.module.LoadMoreModule;
import com.liuguangqiang.idaily.domain.entity.BaseEntity;
import com.liuguangqiang.idaily.domain.entity.StorySection;
import com.liuguangqiang.idaily.ui.adapter.ItemProvider.SectionItemProvider;
import com.liuguangqiang.idaily.ui.adapter.ItemProvider.StoryItemProvider;

import org.jetbrains.annotations.Nullable;

import java.util.List;

import timber.log.Timber;

/**
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
