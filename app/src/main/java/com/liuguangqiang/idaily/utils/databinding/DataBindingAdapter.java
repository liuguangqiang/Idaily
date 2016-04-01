package com.liuguangqiang.idaily.utils.databinding;

import android.databinding.BindingAdapter;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.liuguangqiang.idaily.ui.widget.MyWebView;
import com.liuguangqiang.idaily.utils.DailyUtils;

/**
 * Custom binding.
 * <p>
 * Created by Eric on 15/6/23.
 */
public class DataBindingAdapter {

    @BindingAdapter({"imageUrl"})
    public static void loadImage(ImageView iv, String imageUrl) {
        if (!TextUtils.isEmpty(imageUrl))
            Glide.with(iv.getContext()).load(imageUrl).into(iv);
    }

    @BindingAdapter({"body"})
    public static void loadBody(MyWebView webView, String body) {
        if (!TextUtils.isEmpty(body))
            webView.loadData(body, "text/html; charset=UTF-8", null);
    }

    @BindingAdapter({"datetime"})
    public static void loadDatetime(TextView textView, int datetime) {
        textView.setText(DailyUtils.getDisplayDate(textView.getContext(), datetime));
    }

}
