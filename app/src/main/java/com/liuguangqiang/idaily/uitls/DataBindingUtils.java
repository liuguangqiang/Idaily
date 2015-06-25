package com.liuguangqiang.idaily.uitls;

import android.databinding.BindingAdapter;
import android.text.TextUtils;
import android.webkit.WebView;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

/**
 * Created by Eric on 15/6/23.
 */
public class DataBindingUtils {

    @BindingAdapter({"bind:imageUrl"})
    public static void loadImage(ImageView iv, String imageUrl) {
        if (!TextUtils.isEmpty(imageUrl))
            Picasso.with(iv.getContext()).load(imageUrl).into(iv);
    }

    @BindingAdapter({"bind:body"})
    public static void loadBody(WebView webView, String body) {
        if (!TextUtils.isEmpty(body)) {
            webView.loadData(body, "text/html; charset=UTF-8", null);
        }
    }


}
