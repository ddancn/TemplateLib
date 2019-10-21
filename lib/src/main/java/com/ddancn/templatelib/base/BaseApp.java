package com.ddancn.templatelib.base;

import android.app.Application;
import android.content.Context;

/**
 * @author ddan.zhuang
 * @date 2019/9/20
 */
public class BaseApp extends Application {

    private static Context context;

    @Override
    public void onCreate(){
        super.onCreate();
        context = getApplicationContext();
    }

    public static Context getContext(){
        return context;
    }
}
