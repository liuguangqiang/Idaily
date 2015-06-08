package com.liuguangqiang.idaily.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.liuguangqiang.framework.utils.Logs;
import com.liuguangqiang.idaily.R;
import com.liuguangqiang.idaily.entity.News;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Eric on 15/6/6.
 */
public class NewsAdapter extends BaseRecyclerAdapter<News, NewsAdapter.NewsViewHolder> {

    public NewsAdapter(Context context, List<News> data) {
        super(context, data);
    }

    @Override
    public NewsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.item_news, parent, false);
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

        public void bindData(News news) {
            tvTitle.setText(news.getTitle());
            Picasso.with(itemView.getContext()).load(news.getThumbnail()).into(ivPic);
        }

    }

}
