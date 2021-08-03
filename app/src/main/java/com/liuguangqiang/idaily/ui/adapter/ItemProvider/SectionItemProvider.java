package com.liuguangqiang.idaily.ui.adapter.ItemProvider;

import androidx.databinding.DataBindingUtil;

import com.chad.library.adapter.base.provider.BaseItemProvider;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.liuguangqiang.idaily.R;
import com.liuguangqiang.idaily.databinding.ItemStoryBinding;
import com.liuguangqiang.idaily.databinding.ItemStoryHeaderBinding;
import com.liuguangqiang.idaily.domain.entity.Story;
import com.liuguangqiang.idaily.domain.entity.StorySection;
import com.liuguangqiang.idaily.ui.adapter.StoriesAdapter;

import org.jetbrains.annotations.NotNull;

/**
 * Created by Eric at 2021/8/2
 */
public class SectionItemProvider<T> extends BaseItemProvider<StorySection> {

    @Override
    public int getItemViewType() {
        return StoriesAdapter.ITEM_SECTION;
    }

    @Override
    public int getLayoutId() {
        return R.layout.item_story_header;
    }

    @Override
    public void onViewHolderCreated(BaseViewHolder viewHolder, int viewType) {
        DataBindingUtil.bind(viewHolder.itemView);
    }

    @Override
    public void convert(@NotNull BaseViewHolder helper, @NotNull StorySection data) {
        ItemStoryHeaderBinding binding =DataBindingUtil.getBinding(helper.itemView);
        if (binding != null) {
            binding.setSection(data);
            binding.executePendingBindings();
        }
    }

}