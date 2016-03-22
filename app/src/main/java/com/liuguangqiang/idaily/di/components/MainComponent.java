package com.liuguangqiang.idaily.di.components;

import com.liuguangqiang.idaily.di.modules.MainModule;
import com.liuguangqiang.idaily.ui.act.MainActivity;


import dagger.Component;

/**
 * Created by Eric on 16/3/22.
 */
@Component(modules = MainModule.class)
public interface MainComponent {

    void inject(MainActivity activity);

}
