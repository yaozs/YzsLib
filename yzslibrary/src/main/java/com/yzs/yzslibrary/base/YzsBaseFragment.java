package com.yzs.yzslibrary.base;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yzs.yzslibrary.entity.EventCenter;
import com.yzs.yzslibrary.util.LoadingDialog;

import org.greenrobot.eventbus.Subscribe;

import me.yokeyword.fragmentation.SupportFragment;

/**
 * Author: 姚智胜
 * Version: V1.0版本
 * Description:
 * Date: 2016/10/24
 */
public abstract class YzsBaseFragment extends SupportFragment {
    private static final String TAG = "YzsBaseFragment";
    protected android.view.View rootView;

    public YzsBaseFragment() { /* compiled code */ }

    @Override
    @Nullable
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        if (null!=getArguments()){
            getBundleExtras(getArguments());
        }
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


    /**
     * 获取bundle信息
     *
     * @param bundle
     */
    protected abstract void getBundleExtras(Bundle bundle);

    /**
     * EventBus接收消息
     *
     * @param center 消息接收
     */
    @Subscribe
    public void onEventMainThread(EventCenter center) {

        if (null != center) {
            onEventComing(center);
        }

    }

    /**
     * EventBus接收消息
     *
     * @param center 获取事件总线信息
     */
    protected abstract void onEventComing(EventCenter center);

    /**
     * 显示加载动画
     */
    protected void showLoadingDialog() {
        LoadingDialog.showLoadingDialog(getActivity());
    }


    /**
     * 显示加载动画 自定义加载文字
     *
     * @param str
     */
    protected void showLoadingDialog(String str) {
        LoadingDialog.showLoadingDialog(getActivity(), str);
    }

    /**
     * 取消加载动画
     */
    protected void cancelLoadingDialog() {
        LoadingDialog.cancelLoadingDialog();
    }

    /**
     * startActivity
     *
     * @param clazz 目标Activity
     */
    protected void readyGo(Class<?> clazz) {
        Intent intent = new Intent(getActivity(), clazz);
        startActivity(intent);
    }

    /**
     * startActivity with bundle
     *
     * @param clazz  目标Activity
     * @param bundle 数据
     */
    protected void readyGo(Class<?> clazz, Bundle bundle) {
        Intent intent = new Intent(getActivity(), clazz);
        if (null != bundle) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
    }

    /**
     * @param clazz 目标Activity
     */
    protected void readyGoThenKill(Class<?> clazz) {
        readyGoThenKill(clazz, null);
    }

    /**
     * @param clazz  目标Activity
     * @param bundle 数据
     */
    protected void readyGoThenKill(Class<?> clazz, Bundle bundle) {
        readyGo(clazz, bundle);
        getActivity().finish();
    }

    /**
     * startActivityForResult
     *
     * @param clazz       目标Activity
     * @param requestCode 发送判断值
     */
    protected void readyGoForResult(Class<?> clazz, int requestCode) {
        Intent intent = new Intent(getActivity(), clazz);
        startActivityForResult(intent, requestCode);
    }

    /**
     * startActivityForResult with bundle
     *
     * @param clazz       目标Activity
     * @param requestCode 发送判断值
     * @param bundle      数据
     */
    protected void readyGoForResult(Class<?> clazz, int requestCode, Bundle bundle) {
        Intent intent = new Intent(getActivity(), clazz);
        if (null != bundle) {
            intent.putExtras(bundle);
        }
        startActivityForResult(intent, requestCode);
    }


}
