package com.liuguangqiang.idaily.utils.navigator;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

/**
 * Created by Eric on 15/10/11.
 */
public class BaseNavigator {

    public void start(Context context, Class<?> cls) {
        Intent intent = new Intent(context, cls);
        start(context, intent);
    }

    public void start(Context context, Class<?> cls, Bundle bundle) {
        Intent intent = new Intent(context, cls);
        intent.putExtras(bundle);
        start(context, intent);
    }

    public void start(Context context, Intent intent) {
        if (!(context instanceof Activity)) {
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        }
        context.startActivity(intent);
    }

}
