package com.yzs.yzslibrarydemo;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

public class MainActivity extends FragmentActivity {

    MyDemoFragment fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        fragment = new MyDemoFragment();

//        getFragmentManager().beginTransaction().replace(R.id.fl, fragment)
//                .commit();
        getSupportFragmentManager().beginTransaction().replace(R.id.fl, fragment).commit();
    }


}
