package com.liuguangqiang.idaily.utils.databinding;

import android.databinding.BindingAdapter;

import com.liuguangqiang.idaily.R;
import com.liuguangqiang.idaily.domain.entity.BaseEntity;
import com.liuguangqiang.support.widgets.recyclerview.SuperRecyclerView;
import com.liuguangqiang.support.widgets.recyclerview.adapter.AbsRVAdapter;

import java.util.List;

/**
 * Custom binding for RecyclerView.
 * <p>
 * Created by Eric on 15/6/23.
 */
public class DBRecyclerView {

    public static int SHOW_FOOTER = 0;

    public static int HIDE_FOOTER = 1;

    @BindingAdapter({"adapter"})
    public static void bindAdapter(SuperRecyclerView recyclerView, AbsRVAdapter adapter) {
        recyclerView.setAdapter(adapter);
        recyclerView.setPageFooter(R.layout.layout_loading_footer);
    }

    @BindingAdapter({"data"})
    public static void bindData(SuperRecyclerView recyclerView, List<BaseEntity> data) {
        recyclerView.notifyDataSetChanged();
        recyclerView.setIsLoading(false);
    }

    @BindingAdapter({"isLoading"})
    public static void isLoading(SuperRecyclerView recyclerView, boolean isLoading) {
        recyclerView.setIsLoading(isLoading);
    }

    @BindingAdapter({"footerStatus"})
    public static void footerStatus(SuperRecyclerView recyclerView, int footerStatus) {
        if (footerStatus == SHOW_FOOTER) {
            recyclerView.setPageEnable(true);
            recyclerView.showLoadingFooter();
        } else {
            recyclerView.setPageEnable(false);
            recyclerView.removePageFooter();
        }
    }

}
