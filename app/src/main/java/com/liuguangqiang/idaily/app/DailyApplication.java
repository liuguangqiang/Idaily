package com.liuguangqiang.idaily.app;

import android.app.Application;
import android.content.Context;

import com.liuguangqiang.idaily.BuildConfig;
import com.liuguangqiang.idaily.di.components.AppComponent;
import com.liuguangqiang.idaily.di.components.DaggerAppComponent;
import com.liuguangqiang.idaily.di.modules.AppModule;
import com.liuguangqiang.support.utils.Logs;

/**
 * Created by Eric on 15/6/29.
 */
public class DailyApplication extends Application {

    private AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        Logs.setIsDebug(BuildConfig.DEBUG);

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
