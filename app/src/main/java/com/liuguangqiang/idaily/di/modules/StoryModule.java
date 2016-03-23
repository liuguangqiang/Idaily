package com.liuguangqiang.idaily.di.modules;

import com.liuguangqiang.idaily.ui.model.StoryModel;
import com.liuguangqiang.idaily.ui.viewmodel.StoryViewModel;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Eric on 16/3/22.
 */
@Module
public class StoryModule {

    @Provides
    StoryViewModel provideViewModel() {
        return new StoryViewModel(new StoryModel());
    }

}
