package com.liuguangqiang.idaily.ui.widget;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

/**
 * Created by Eric on 15/6/5.
 */
public class LinearRecyclerView extends RecyclerView {

    private boolean isBottom;
    private boolean isTop;

    private int firstVisibleItem;
    private int visibleItemCount;
    private int totalItemCount;

    private LinearLayoutManager layoutManager;

    private OnScrollPositionListener onScrollPositionListener;

    public void setOnScrollPositionListener(OnScrollPositionListener listener) {
        onScrollPositionListener = listener;
    }

    public LinearRecyclerView(Context context) {
        super(context);
        init();
    }

    public LinearRecyclerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public LinearRecyclerView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    private void init() {
        layoutManager = new LinearLayoutManager(getContext());
        setLayoutManager(layoutManager);
        addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                if (newState == RecyclerView.SCROLL_STATE_IDLE && onScrollPositionListener != null) {
                    if (isBottom) onScrollPositionListener.onScrollToBottom();
                    if (isTop) onScrollPositionListener.onScrollToTop();
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                if (onScrollPositionListener != null) {
                    visibleItemCount = recyclerView.getChildCount();
                    totalItemCount = layoutManager.getItemCount();
                    firstVisibleItem = layoutManager.findFirstVisibleItemPosition();

                    isBottom = (firstVisibleItem + visibleItemCount) >= (totalItemCount - 1);
                    isTop = firstVisibleItem == 0;
                }
            }
        });
    }

    public interface OnScrollPositionListener {

        void onScrollToTop();

        void onScrollToBottom();

    }

}
