package com.liuguangqiang.idaily.adapter.ItemProvider;

import androidx.databinding.DataBindingUtil;

import com.chad.library.adapter.base.provider.BaseItemProvider;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.liuguangqiang.idaily.R;
import com.liuguangqiang.idaily.databinding.ItemStoryBinding;
import com.liuguangqiang.idaily.entity.Story;
import com.liuguangqiang.idaily.adapter.StoriesAdapter;

import org.jetbrains.annotations.NotNull;

import timber.log.Timber;

/**
 * Created by Eric at 2021/8/2
 */
public class StoryItemProvider<T> extends BaseItemProvider<Story> {

    @Override
    public int getItemViewType() {
        return StoriesAdapter.ITEM_STORY;
    }

    @Override
    public int getLayoutId() {
        return R.layout.item_story;
    }

    @Override
    public void onViewHolderCreated(BaseViewHolder viewHolder, int viewType) {
        DataBindingUtil.bind(viewHolder.itemView);
    }

    @Override
    public void convert(@NotNull BaseViewHolder helper, @NotNull Story data) {
        Timber.d("StoryItemProvider convert");
        ItemStoryBinding binding = DataBindingUtil.getBinding(helper.itemView);
        if (binding != null) {
            Timber.d("StoryItemProvider binding");
            binding.setStory(data);
            binding.executePendingBindings();
        }
    }

}