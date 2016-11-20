package com.yzs.yzslibrarydemo.activity;

import android.os.Bundle;
import android.util.Log;

import com.yzs.yzslibrary.base.YzsBaseActivity;
import com.yzs.yzslibrary.entity.EventCenter;
import com.yzs.yzslibrarydemo.R;
import com.yzs.yzslibrarydemo.fragment.MyDemoFragment;

public class MainActivity extends YzsBaseActivity {

    MyDemoFragment fragment;


    @Override
    protected void initContentView(Bundle bundle) {
        setContentView(R.layout.main);
        fragment = new MyDemoFragment();
        Log.e("MainActivity", "initContentView");
//        getFragmentManager().beginTransaction().replace(R.id.fl, fragment)
//                .commit();
        getSupportFragmentManager().beginTransaction().replace(R.id.fl, fragment).commit();
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

    @Override
    protected void onResume() {
        super.onResume();
        Log.e("MainActivity", "onResume");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e("MainActivity", "onDestroy");
    }


}
