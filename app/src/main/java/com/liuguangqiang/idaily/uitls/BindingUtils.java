package com.liuguangqiang.idaily.uitls;

import android.databinding.BindingAdapter;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

/**
 * Created by Eric on 15/6/9.
 */
public class BindingUtils {

    @BindingAdapter({"bind:imageUrl"})
    public static void loadImage(ImageView iv, String url) {
        Picasso.with(iv.getContext()).load(url).into(iv);
    }

}
