package com.liuguangqiang.idaily.widget;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;

import com.liuguangqiang.idaily.R;
import com.liuguangqiang.idaily.adapter.Bookends;

/**
 * Created by Eric on 15/6/5.
 */
public class PageableRecyclerView extends LinearRecyclerView {

    private boolean isLoading = false;

    public void onPageStart() {
        isLoading = true;
        showLoadingFooter();
    }

    public void onPageFinished() {
        isLoading = false;
        hideLoadingFooter();
    }

    private Bookends bookends;

    private OnPageListener listener;

    public void setOnPageListener(OnPageListener pageListener) {
        this.listener = pageListener;
    }

    public PageableRecyclerView(Context context) {
        super(context);
        init();
    }

    public PageableRecyclerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public PageableRecyclerView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    @Override
    public void setAdapter(Adapter adapter) {
        bookends = new Bookends(adapter);
        addFooter();
        super.setAdapter(bookends);
    }

    public void notifyDataSetChanged() {
        bookends.notifyDataSetChanged();
    }

    private void init() {
        setOnScrollPositionListener(new OnScrollPositionListener() {
            @Override
            public void onScrollToTop() {
            }

            @Override
            public void onScrollToBottom() {
                if (listener != null && !isLoading) {
                    onPageStart();
                    listener.onPage();
                }
            }
        });
    }

    private void addFooter() {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.layout_loading_footer, null);
        bookends.addFooter(view);
    }

    public void hideLoadingFooter() {
        bookends.setFooterVisibility(false);
    }

    public void showLoadingFooter() {
        bookends.setFooterVisibility(true);
    }

    public interface OnPageListener {
        void onPage();
    }

}
