package com.liuguangqiang.idaily.base;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Created by Eric on 15/6/9.
 */
public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public <T extends View> T findById(int resId) {
        return (T) findViewById(resId);
    }

}
