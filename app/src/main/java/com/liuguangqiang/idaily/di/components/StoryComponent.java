package com.liuguangqiang.idaily.di.components;

import com.liuguangqiang.idaily.di.modules.StoryModule;
import com.liuguangqiang.idaily.ui.act.StoryActivity;

import dagger.Component;

/**
 * Created by Eric on 16/3/22.
 */
@Component(modules = StoryModule.class)
public interface StoryComponent {

    void inject(StoryActivity activity);

}
