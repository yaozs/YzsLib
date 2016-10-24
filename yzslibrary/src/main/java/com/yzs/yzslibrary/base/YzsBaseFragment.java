package com.yzs.yzslibrary.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Author: 姚智胜
 * Version: V1.0版本
 * Description:
 * Date: 2016/10/24
 */
public abstract class YzsBaseFragment extends Fragment {
    private static final String TAG = "YzsBaseFragment";
    protected android.view.View rootView;

    public YzsBaseFragment() { /* compiled code */ }

    @Override
    @Nullable
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = initContentView(inflater, container, savedInstanceState);
        initView();
        initLogic();
        return view;
    }

    protected View createFragmentView(LayoutInflater inflater, @Nullable ViewGroup container, int resId) {
        View view = null;
        if (null != inflater) {
            view = inflater.inflate(resId, container, false);
        }

        // TODO
        if (null == view) {
            Log.e(TAG, "fragment view is not created！");
        }

        return view;
    }

    // 初始化UI setContentView
    protected abstract View initContentView(LayoutInflater inflater, @Nullable ViewGroup container,
                                            @Nullable Bundle savedInstanceState);
    // 初始化控件
    protected abstract void initView();
    // 逻辑处理
    protected abstract void initLogic();
}
