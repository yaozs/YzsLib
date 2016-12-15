package com.yzs.yzslibrarydemo.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yzs.yzslibrary.base.fragment.YzsBaseFragment;
import com.yzs.yzslibrary.entity.EventCenter;
import com.yzs.yzslibrarydemo.R;

/**
 * Author: 姚智胜
 * Version: V1.0版本
 * Description:
 * Date: 2016/12/15.
 */
public class PersonFragment extends YzsBaseFragment {
    private static final String TAG = "PersonFragment";


    @Override
    protected View initContentView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fg_person, container, false);
        return view;
    }

    @Override
    protected void initView(View view) {

    }

    @Override
    protected void initLogic(View view) {

    }

    @Override
    protected void getBundleExtras(Bundle bundle) {

    }

    @Override
    protected void onEventComing(EventCenter center) {

    }
}
