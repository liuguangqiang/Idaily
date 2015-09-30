package com.liuguangqiang.idaily.app;

import android.app.Application;

import com.liuguangqiang.framework.utils.Logs;
import com.liuguangqiang.idaily.BuildConfig;

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
