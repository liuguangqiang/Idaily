package com.liuguangqiang.idaily.app;

import android.os.Bundle;
import android.view.View;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

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
