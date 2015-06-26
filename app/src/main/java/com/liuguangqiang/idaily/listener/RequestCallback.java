package com.liuguangqiang.idaily.listener;

/**
 * Created by Eric on 15/6/26.
 */
public interface RequestCallback<T> {

    void requestSuccess(T t);

    void requestFinished();

}
