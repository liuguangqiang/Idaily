package com.liuguangqiang.idaily.ui.adapter;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.liuguangqiang.idaily.R;
import com.liuguangqiang.idaily.databinding.ItemStoryBinding;
import com.liuguangqiang.idaily.databinding.ItemStoryHeaderBinding;
import com.liuguangqiang.idaily.domain.entity.BaseEntity;
import com.liuguangqiang.idaily.domain.entity.Story;
import com.liuguangqiang.idaily.domain.entity.StorySection;

import java.util.List;

/**
 * Created by Eric at 2021/7/31
 */
public class StoriesAdapter extends BaseMultiItemQuickAdapter<BaseEntity, BaseViewHolder> {

    public static final int ITEM_STORY = 0;

    public static final int ITEM_SECTION = 1;

    public StoriesAdapter(List<BaseEntity> data) {
        super(data);
        addItemType(ITEM_STORY, R.layout.item_story);
        addItemType(ITEM_SECTION, R.layout.item_story_header);
    }

    @Override
    protected void convert(BaseViewHolder helper, BaseEntity baseEntity) {
        switch (helper.getItemViewType()) {
            case ITEM_STORY:
                ItemStoryBinding binding = (ItemStoryBinding) helper.itemView.getTag();
                binding.setStory((Story) baseEntity);
                binding.executePendingBindings();
                break;
            case ITEM_SECTION:
                ItemStoryHeaderBinding sectionBinding = (ItemStoryHeaderBinding) helper.itemView.getTag();
                sectionBinding.setSection((StorySection) baseEntity);
                sectionBinding.executePendingBindings();
                break;
            default:
                break;
        }
    }
}
