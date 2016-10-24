package com.yzs.yzslibrary.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;

/**
 * Author: 姚智胜
 * Version: V1.0版本
 * Description:
 * Date: 2016/10/24
 */
public abstract class YzsBaseFragmentActivity extends FragmentActivity {
    private static final String TAG = "YzsBaseFragmentActivity";

    public YzsBaseFragmentActivity() { /* compiled code */ }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initContentView(savedInstanceState);
        initView();
        initLogic();
    }

    protected abstract void initContentView(android.os.Bundle bundle);

    protected abstract void initView();

    protected abstract void initLogic();

}
