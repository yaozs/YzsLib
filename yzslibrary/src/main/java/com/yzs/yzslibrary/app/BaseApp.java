package com.yzs.yzslibrary.app;

import android.app.Application;
import android.content.Context;
import android.content.res.Resources;


/**
 * Author: 姚智胜
 * Version: V1.0版本
 * Description:
 * Date: 2016/10/24
 */
public abstract class BaseApp extends Application {

    protected static Context applicationContext;
    protected static BaseApp instance;


    @Override
    public void onCreate() {
        super.onCreate();
        applicationContext = getApplicationContext();
        instance = this;
        onInitCreate();
    }

    public static Context getAppContext() {
        return applicationContext;
    }

    public static BaseApp getInstance() {
        return instance;
    }

    protected abstract void onInitCreate();
}
