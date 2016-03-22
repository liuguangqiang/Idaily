package com.liuguangqiang.idaily.ui.view;


import java.util.List;

/**
 * Created by Eric on 16/3/21.
 */
public interface RequestView<T> {

    void onRequestFinished();

    void onRequestSuccess(List<T> list);

}
