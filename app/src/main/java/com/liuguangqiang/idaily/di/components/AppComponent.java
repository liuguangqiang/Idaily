package com.liuguangqiang.idaily.di.components;

import com.liuguangqiang.idaily.app.DailyApplication;
import com.liuguangqiang.idaily.di.modules.AppModule;
import com.liuguangqiang.idaily.di.modules.MainModule;
import com.liuguangqiang.idaily.ui.act.MainActivity;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Eric on 16/3/22.
 */
@Component(modules = AppModule.class)
public interface AppComponent {

    DailyApplication inject(DailyApplication application);

}
