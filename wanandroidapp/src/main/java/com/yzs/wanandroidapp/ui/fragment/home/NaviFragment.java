package com.yzs.wanandroidapp.ui.fragment.home;

import android.os.Bundle;
import android.view.View;

import com.yzs.wanandroidapp.R;
import com.yzs.yzsbaseactivitylib.yzsbase.YzsBaseActivity;
import com.yzs.yzsbaseactivitylib.yzsbase.YzsBaseFragment;

/**
 * Author: 姚智胜
 * Version: V1.0版本
 * Description: 导航fragment
 * Date: 2018/4/20
 */
public class NaviFragment extends YzsBaseFragment{

    public static NaviFragment newInstance() {

        Bundle args = new Bundle();

        NaviFragment fragment = new NaviFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    protected void initLogic() {

    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fg_home_navi;
    }

    @Override
    protected void initView(View view) {

    }
}
