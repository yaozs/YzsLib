package com.yzs.yzslibrary.base.activity;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.yzs.yzslibrary.R;
import com.yzs.yzslibrary.entity.EventCenter;
import com.yzs.yzslibrary.util.LoadingDialog;
import com.yzs.yzslibrary.util.SystemBarTintManager;
import com.yzs.yzslibrary.util.ToastUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import me.yokeyword.fragmentation.SupportActivity;


/**
 * Author: 姚智胜
 * Version: V1.0版本
 * Description:
 * Date: 2016/10/24
 */
public abstract class YzsBaseActivity extends SupportActivity {
    private static final String TAG = "YzsBaseActivity";

    public YzsBaseActivity() { /* compiled code */ }

    /**
     * RecyclerView空界面默认布局
     */
    protected View emptyView;

    public boolean useTitle = true;

    public Toolbar mToolbar;
    public TextView title;
    public ImageView back;
    public TextView tv_menu;
    public ImageView iv_menu;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            setTranslucentStatus(true);
            SystemBarTintManager tintManager = new SystemBarTintManager(this);
            tintManager.setStatusBarTintEnabled(true);
            tintManager.setStatusBarTintResource(R.color.colorPrimaryDark);//通知栏所需颜色
        }


        Bundle extras = getIntent().getExtras();
        if (null != extras) {
            getBundleExtras(extras);
        }
        EventBus.getDefault().register(this);
        initContentView(savedInstanceState);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        if (null != mToolbar) {
            setSupportActionBar(mToolbar);
            getSupportActionBar().setDisplayShowTitleEnabled(false);
            initTitle();
        }


        initView();
        initLogic();

    }

    protected abstract void initContentView(android.os.Bundle bundle);

    protected abstract void initView();

    protected abstract void initLogic();


    /**
     * Bundle  传递数据
     *
     * @param extras
     */
    protected abstract void getBundleExtras(Bundle extras);

    protected void initTitle() {
        title = (TextView) findViewById(R.id.toolbar_title);
        back = (ImageView) findViewById(R.id.toolbar_back);
        iv_menu = (ImageView) findViewById(R.id.toolbar_iv_menu);
        tv_menu = (TextView) findViewById(R.id.toolbar_tv_menu);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    public void setTitle(String string) {
        title.setText(string);
    }

    public void setTitle(int id) {
        title.setText(id);
    }


    @TargetApi(19)
    private void setTranslucentStatus(boolean on) {
        Window win = getWindow();
        WindowManager.LayoutParams winParams = win.getAttributes();
        final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
        if (on) {
            winParams.flags |= bits;
        } else {
            winParams.flags &= ~bits;
        }
        win.setAttributes(winParams);
    }

    protected void chooseUseTitle(boolean choose) {
        useTitle = choose;
    }

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

    protected void setEmptyView(RecyclerView recyclerView) {
        emptyView = getLayoutInflater().inflate(R.layout.empty_view, (ViewGroup) recyclerView.getParent(), false);
//        mQuickAdapter.setEmptyView(emptyView);
    }

    protected void changedView(BaseQuickAdapter adapter, View view) {
        if (null != adapter) {
            adapter.setEmptyView(view);
            adapter.notifyItemChanged(0);
        }
    }

    /**
     * 显示加载动画
     */
    protected void showLoadingDialog() {
        LoadingDialog.showLoadingDialog(this);
    }

    /**
     * 显示加载动画 自定义加载文字
     *
     * @param str
     */
    protected void showLoadingDialog(String str) {
        LoadingDialog.showLoadingDialog(this, str);
    }

    /**
     * 取消加载动画
     */
    protected void cancelLoadingDialog() {
        LoadingDialog.cancelLoadingDialog();
    }

    //Toast显示
    protected void showShortToast(String string) {
        ToastUtils.showShortToast(this, string);
    }

    protected void showShortToast(int stringId) {
        ToastUtils.showShortToast(this, stringId);
    }

    protected void showLongToast(String string) {
        ToastUtils.showShortToast(this, string);
    }

    protected void showLongToast(int stringId) {
        ToastUtils.showShortToast(this, stringId);
    }


    @Override
    protected void onDestroy() {
        emptyView = null;
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }


    /**
     * 界面跳转
     *
     * @param clazz 目标Activity
     */
    protected void readyGo(Class<?> clazz) {
        readyGo(clazz, null);
    }

    /**
     * 跳转界面，  传参
     *
     * @param clazz  目标Activity
     * @param bundle 数据
     */
    protected void readyGo(Class<?> clazz, Bundle bundle) {
        Intent intent = new Intent(this, clazz);
        if (null != bundle)
            intent.putExtras(bundle);
        startActivity(intent);
    }

    /**
     * 跳转界面并关闭当前界面
     *
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
        finish();
    }

    /**
     * startActivityForResult
     *
     * @param clazz       目标Activity
     * @param requestCode 发送判断值
     */
    protected void readyGoForResult(Class<?> clazz, int requestCode) {
        Intent intent = new Intent(this, clazz);
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
        Intent intent = new Intent(this, clazz);
        if (null != bundle) {
            intent.putExtras(bundle);
        }
        startActivityForResult(intent, requestCode);
    }
}
