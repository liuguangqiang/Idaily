package com.liuguangqiang.idaily.base;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.liuguangqiang.android.mvp.Presenter;

/**
 * Created by Eric on 15/6/9.
 */
public class BaseActivity extends AppCompatActivity {

    private Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = createPresenter();
    }

    public Presenter createPresenter() {
        return null;
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (presenter != null && !presenter.isAttachedUi()) {
            presenter.attach();
            onAttachedUi();
        }
    }

    protected void onAttachedUi() {
    }

    public <T extends View> T findById(int resId) {
        return (T) findViewById(resId);
    }

}
