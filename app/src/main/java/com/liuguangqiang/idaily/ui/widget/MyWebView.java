package com.liuguangqiang.idaily.ui.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.webkit.WebView;

import com.liuguangqiang.support.utils.Logs;

/**
 * Created by Eric on 15/10/26.
 */
public class MyWebView extends WebView {

    public MyWebView(Context context) {
        this(context, null);
    }

    public MyWebView(Context context, AttributeSet attrs) {
        super(context, attrs, 0);
    }

    public MyWebView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        Logs.i("t:" + t);
    }

}
