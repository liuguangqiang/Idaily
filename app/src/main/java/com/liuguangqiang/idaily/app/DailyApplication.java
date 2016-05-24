package com.liuguangqiang.idaily.app;

import android.app.Application;
import android.content.Context;

import com.liuguangqiang.idaily.BuildConfig;
import com.liuguangqiang.idaily.di.components.AppComponent;
import com.liuguangqiang.idaily.di.components.DaggerAppComponent;
import com.liuguangqiang.idaily.di.modules.AppModule;
import com.liuguangqiang.support.utils.Logs;

import hugo.weaving.DebugLog;
import timber.log.Timber;


/**
 * Created by Eric on 15/6/29.
 */
public class DailyApplication extends Application {

    private AppComponent appComponent;

    @DebugLog
    @Override
    public void onCreate() {
        super.onCreate();
        Logs.setIsDebug(BuildConfig.DEBUG);

        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
            Timber.tag("daily");
        }

        appComponent = DaggerAppComponent.builder().appModule(new AppModule()).build();
        appComponent.inject(this);
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }

    public static DailyApplication from(Context context) {
        return (DailyApplication) context.getApplicationContext();
    }

}
