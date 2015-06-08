package com.liuguangqiang.idaily.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.liuguangqiang.idaily.R;
import com.liuguangqiang.idaily.entity.News;

import java.util.List;


/**
 * Created by Eric on 15/6/6.
 */
public class SampleAdapter extends BaseRecyclerAdapter<News, SampleAdapter.SampleViewHolder> {

    public SampleAdapter(Context context, List<News> list) {
        super(context, list);
    }

    @Override
    public SampleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.item_sample, parent, false);
        return new SampleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SampleViewHolder holder, int position) {
        super.onBindViewHolder(holder, position);
        holder.bindData(data.get(position));
    }

    public static class SampleViewHolder extends RecyclerView.ViewHolder {

        private TextView tvTitle;

        public SampleViewHolder(View view) {
            super(view);
            tvTitle = (TextView) view.findViewById(R.id.item_tv_title);
        }

        public void bindData(News sample) {
        }

    }

}
