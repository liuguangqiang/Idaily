package com.liuguangqiang.idaily.app;

import android.app.Application;

import com.liuguangqiang.idaily.BuildConfig;
import com.liuguangqiang.support.utils.Logs;

/**
 * Created by Eric on 15/6/29.
 */
public class DailyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Logs.setIsDebug(BuildConfig.DEBUG);
    }

}
