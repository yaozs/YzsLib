package com.yzs.yzslibrarydemo.app;

import com.squareup.leakcanary.LeakCanary;
import com.yzs.yzslibrary.app.BaseApp;
import com.yzs.yzslibrary.util.ToastUtils;

/**
 * Author: 姚智胜
 * Version: V1.0版本
 * Description:
 * Date: 2016/11/20
 */
public class YzsApp extends BaseApp {
    private static final String TAG = "YzsApp";

    @Override
    protected void onInitCreate() {
        //初始化Toast
        ToastUtils.init(true);
        if (LeakCanary.isInAnalyzerProcess(this)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return;
        }
        LeakCanary.install(this);
        // Normal app init code...
    }
}
