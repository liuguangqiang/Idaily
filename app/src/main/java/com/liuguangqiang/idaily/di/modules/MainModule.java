package com.liuguangqiang.idaily.di.modules;

import android.content.Context;

import com.liuguangqiang.idaily.ui.model.MainModel;
import com.liuguangqiang.idaily.ui.viewmodel.MainViewModel;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Eric on 16/3/22.
 */
@Module
public class MainModule {

    private Context context;

    public MainModule(Context context) {
        this.context = context;
    }

    @Provides
    MainViewModel provideViewModel(Context context) {
        return new MainViewModel(context, new MainModel());
    }

    @Provides
    Context provideContext() {
        return context;
    }

}
