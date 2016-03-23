package com.liuguangqiang.idaily.ui.viewmodel;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.liuguangqiang.idaily.BR;
import com.liuguangqiang.idaily.utils.databinding.DBRecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * A abstract ViewModel for RecylerView
 * <p/>
 * Created by Eric on 16/3/21.
 */
public abstract class AbsRecyclerViewModel<T> extends BaseObservable {

    @Bindable
    public List<T> data = new ArrayList<>();

    @Bindable
    public int footerStatus = DBRecyclerView.SHOW_FOOTER;

    @Bindable
    public boolean loading = false;

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    public int getFooterStatus() {
        return footerStatus;
    }

    public void setFooterStatus(int footerStatus) {
        this.footerStatus = footerStatus;
    }

    public void setLoading(boolean loading) {
        this.loading = loading;
    }

    public boolean isLoading() {
        return loading;
    }

    public void requestData() {
        loading = true;
        notifyPropertyChanged(BR.loading);
    }

    public void onRequestFinished() {
        loading = false;
        notifyPropertyChanged(BR.loading);
    }

    public void onRequestSuccess(List<T> list) {
        if (list.size() < 20) {
            footerStatus = DBRecyclerView.HIDE_FOOTER;
        } else {
            footerStatus = DBRecyclerView.SHOW_FOOTER;
        }

        data.addAll(list);
        notifyPropertyChanged(BR.data);
    }

}
