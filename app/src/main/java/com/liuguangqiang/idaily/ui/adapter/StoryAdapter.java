package com.liuguangqiang.idaily.ui.adapter;

import android.content.Context;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.liuguangqiang.idaily.databinding.ItemStoryBinding;
import com.liuguangqiang.idaily.databinding.ItemStoryHeaderBinding;
import com.liuguangqiang.idaily.domain.entity.BaseEntity;
import com.liuguangqiang.idaily.domain.entity.Story;
import com.liuguangqiang.idaily.domain.entity.StorySection;
import com.liuguangqiang.support.widgets.recyclerview.adapter.AbsRVAdapter;

import java.util.List;

/**
 * Created by Eric on 15/6/6.
 */
public class StoryAdapter extends AbsRVAdapter<BaseEntity, StoryAdapter.StoryViewHolder> {

    private static final int ITEM_STORY = 0;

    private static final int ITEM_SECTION = 1;

    public StoryAdapter(Context context, List<BaseEntity> data) {
        super(context, data);
    }

    @Override
    public StoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == ITEM_STORY)
            return StoryViewHolder.createViewHolder(ItemStoryBinding.inflate(layoutInflater));
        else
            return StoryViewHolder.createViewHolder(ItemStoryHeaderBinding.inflate(layoutInflater));
    }

    @Override
    public void onBindViewHolder(StoryViewHolder holder, int position) {
        super.onBindViewHolder(holder, position);
        BaseEntity entity = data.get(position);
        if (getItemViewType(position) == ITEM_STORY)
            holder.bindData((Story) entity);
        else
            holder.bindSection((StorySection) entity);
    }

    @Override
    public int getItemViewType(int position) {
        if (data.get(position) instanceof StorySection)
            return ITEM_SECTION;

        return ITEM_STORY;
    }

    public static class StoryViewHolder extends RecyclerView.ViewHolder {

        public static StoryViewHolder createViewHolder(ViewDataBinding binding) {
            return new StoryViewHolder(binding.getRoot(), binding);
        }

        public StoryViewHolder(View view, ViewDataBinding binding) {
            super(view);
            itemView.setTag(binding);
        }

        public void bindData(Story story) {
            ItemStoryBinding binding = (ItemStoryBinding) itemView.getTag();
            binding.setStory(story);
            binding.executePendingBindings();
        }

        public void bindSection(StorySection section) {
            ItemStoryHeaderBinding binding = (ItemStoryHeaderBinding) itemView.getTag();
            binding.setSection(section);
            binding.executePendingBindings();
        }
    }
}
