package com.yzs.yzslibrarydemo.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yzs.yzsbaseactivitylib.entity.EventCenter;
import com.yzs.yzsbaseactivitylib.fragment.YzsBaseFragment;
import com.yzs.yzslibrary.view.StateButton;
import com.yzs.yzslibrarydemo.R;
import com.yzs.yzslibrarydemo.activity.DemoActivity;

/**
 * Author: 姚智胜
 * Version: V1.0版本
 * Description:
 * Date: 2016/12/15.
 */
public class HomeFragment extends YzsBaseFragment {
    private static final String TAG = "HomeFragment";


    public static HomeFragment newInstance(Bundle bundle) {
        HomeFragment fragment = new HomeFragment();
        if (null != bundle) {
            fragment.setArguments(bundle);
        }
        return fragment;
    }

    @Override
    protected View initContentView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fg_home, container, false);
        return view;
    }

    @Override
    protected void initView(View view) {
        StateButton button = (StateButton) view.findViewById(R.id.btn_fg_home);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                readyGo(DemoActivity.class);
            }
        });
    }

    @Override
    protected void initLogic() {

    }


    @Override
    protected void getBundleExtras(Bundle bundle) {

    }

    @Override
    protected void onEventComing(EventCenter center) {

    }

}
