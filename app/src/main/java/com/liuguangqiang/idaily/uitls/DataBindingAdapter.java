package com.liuguangqiang.idaily.uitls;

import android.databinding.BindingAdapter;
import android.text.TextUtils;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.liuguangqiang.idaily.adapter.BaseRecyclerAdapter;
import com.liuguangqiang.idaily.entity.BaseEntity;
import com.liuguangqiang.idaily.widget.PageableRecyclerView;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Custom binding.
 * <p/>
 * Created by Eric on 15/6/23.
 */
public class DataBindingAdapter {

    @BindingAdapter({"bind:imageUrl"})
    public static void loadImage(ImageView iv, String imageUrl) {
        if (!TextUtils.isEmpty(imageUrl))
            Picasso.with(iv.getContext()).load(imageUrl).into(iv);
    }

    @BindingAdapter({"bind:body"})
    public static void loadBody(WebView webView, String body) {
        if (!TextUtils.isEmpty(body))
            webView.loadData(body, "text/html; charset=UTF-8", null);
    }

    @BindingAdapter({"bind:datetime"})
    public static void loadDatetime(TextView textView, int datetime) {
        textView.setText(DailyUtils.getDisplayDate(textView.getContext(), datetime));
    }

    //**************************** RecyclerView ****************************

    @BindingAdapter({"bind:adapter"})
    public static void bindAdapter(PageableRecyclerView recyclerView, BaseRecyclerAdapter adapter) {
        adapter.setRecylerView(recyclerView);
        recyclerView.setAdapter(adapter);
    }

    @BindingAdapter({"bind:data"})
    public static void bindData(PageableRecyclerView recyclerView, List<BaseEntity> data) {
        recyclerView.notifyDataSetChanged();
        recyclerView.onPageFinished();
    }

}
