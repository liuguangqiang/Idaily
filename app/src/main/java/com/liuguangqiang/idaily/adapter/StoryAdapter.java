package com.liuguangqiang.idaily.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.liuguangqiang.idaily.R;
import com.liuguangqiang.idaily.entity.Story;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Eric on 15/6/6.
 */
public class StoryAdapter extends BaseRecyclerAdapter<Story, StoryAdapter.NewsViewHolder> {

    public StoryAdapter(Context context, List<Story> data) {
        super(context, data);
    }

    @Override
    public NewsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.item_story, parent, false);
        return new NewsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(NewsViewHolder holder, int position) {
        super.onBindViewHolder(holder, position);
        holder.bindData(data.get(position));
    }

    public static class NewsViewHolder extends RecyclerView.ViewHolder {

        private TextView tvTitle;
        private ImageView ivPic;

        public NewsViewHolder(View view) {
            super(view);
            tvTitle = (TextView) view.findViewById(R.id.item_tv_title);
            ivPic = (ImageView) view.findViewById(R.id.item_iv_pic);
        }

        public void bindData(Story news) {
            tvTitle.setText(news.getTitle());
            Picasso.with(itemView.getContext()).load(news.getImages().get(0)).into(ivPic);
        }

    }

}
