package com.liuguangqiang.idaily.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.liuguangqiang.idaily.R;
import com.liuguangqiang.idaily.entity.BaseEntity;
import com.liuguangqiang.idaily.entity.Story;
import com.liuguangqiang.idaily.entity.StorySection;
import com.liuguangqiang.idaily.uitls.DailyUtils;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Eric on 15/6/6.
 */
public class StoryAdapter extends BaseRecyclerAdapter<BaseEntity, StoryAdapter.NewsViewHolder> {

    private static final int ITEM_STORY = 0;

    private static final int ITEM_SECTION = 1;

    public StoryAdapter(Context context, List<BaseEntity> data) {
        super(context, data);
    }

    @Override
    public NewsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.item_story, parent, false);
        View itemSection = layoutInflater.inflate(R.layout.item_story_header, parent, false);
        if (viewType == ITEM_STORY) {
            return new NewsViewHolder(view);
        } else {
            return new NewsViewHolder(itemSection, true);
        }
    }

    @Override
    public void onBindViewHolder(NewsViewHolder holder, int position) {
        super.onBindViewHolder(holder, position);

        BaseEntity entity = data.get(position);
        if (getItemViewType(position) == ITEM_STORY) {
            holder.bindData((Story) entity);
        } else {
            holder.bindSection((StorySection) entity);
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (data.get(position) instanceof StorySection)
            return ITEM_SECTION;

        return ITEM_STORY;
    }

    public static class NewsViewHolder extends RecyclerView.ViewHolder {

        private TextView tvTitle;
        private ImageView ivPic;

        private TextView tvDatetime;

        public NewsViewHolder(View view) {
            super(view);
            tvTitle = (TextView) view.findViewById(R.id.item_tv_title);
            ivPic = (ImageView) view.findViewById(R.id.item_iv_pic);
        }

        public NewsViewHolder(View view, boolean section) {
            super(view);
            tvDatetime = (TextView) view.findViewById(R.id.tv_datetime);
        }

        public void bindData(Story story) {
            tvTitle.setText(story.getTitle());
            if (!story.getImages().isEmpty())
                Picasso.with(itemView.getContext()).load(story.getImages().get(0)).into(ivPic);
        }

        public void bindSection(StorySection section) {
            tvDatetime.setText(DailyUtils.getDiplayDate(itemView.getContext(), section.datetime));
        }

    }

}
