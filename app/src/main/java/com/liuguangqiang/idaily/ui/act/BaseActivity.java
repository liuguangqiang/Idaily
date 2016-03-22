package com.liuguangqiang.idaily.ui.act;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Created by Eric on 15/6/9.
 */
public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        onCreateBinding();
    }

    public abstract void onCreateBinding();

    public <T extends View> T findById(int resId) {
        return (T) findViewById(resId);
    }

}
