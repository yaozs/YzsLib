package com.yzs.yzslibrarydemo;

import android.os.Bundle;
import android.util.Log;

import com.yzs.yzslibrary.base.YzsBaseFragmentActivity;

public class MainActivity extends YzsBaseFragmentActivity {

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
    protected void onResume() {
        super.onResume();
        Log.e("MainActivity","onResume");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e("MainActivity","onDestroy");
    }


}
