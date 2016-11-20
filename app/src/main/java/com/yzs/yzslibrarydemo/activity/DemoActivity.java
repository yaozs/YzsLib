package com.yzs.yzslibrarydemo.activity;

import android.os.Bundle;

import com.yzs.yzslibrary.base.YzsBaseActivity;
import com.yzs.yzslibrary.entity.EventCenter;
import com.yzs.yzslibrarydemo.R;

/**
 * Author: 姚智胜
 * Version: V1.0版本
 * Description:
 * Date: 2016/11/18
 */
public class DemoActivity extends YzsBaseActivity {

    private static final String TAG = "DemoActivity";

    @Override
    protected void initContentView(Bundle bundle) {
        setContentView(R.layout.ac_demo);
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initLogic() {

    }

    @Override
    protected void getBundleExtras(Bundle extras) {

    }

    @Override
    protected void onEventComing(EventCenter center) {

    }
}
