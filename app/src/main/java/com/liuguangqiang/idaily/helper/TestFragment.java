package com.liuguangqiang.idaily.helper;

import android.os.Bundle;
import android.support.annotation.CheckResult;
import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;

/**
 * Created by Eric on 15/7/16.
 */
public class TestFragment extends Fragment {

    private static final String TAG = TestFragment.class.getSimpleName();

    public static TestFragment attach(Fragment target) {
        return attach(target.getChildFragmentManager());
    }

    public static TestFragment attach(FragmentActivity target) {
        return attach(target.getSupportFragmentManager());
    }

    private static TestFragment attach(FragmentManager fragmentManager) {
        TestFragment fragment = (TestFragment) fragmentManager.findFragmentByTag(TAG);

        if (fragment == null) {
            fragment = new TestFragment();
            fragmentManager.beginTransaction().add(fragment, TAG).commit();
        }

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        test(null);
//        getStr(123);
    }

    @CheckResult
    private String test(@NonNull String test) {
        return "";
    }

    private void getStr(@StringRes @DrawableRes @ColorRes int resId) {

    }

}

