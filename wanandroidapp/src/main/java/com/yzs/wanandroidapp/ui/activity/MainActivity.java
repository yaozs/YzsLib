package com.yzs.wanandroidapp.ui.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.yzs.wanandroidapp.R;
import com.yzs.wanandroidapp.ui.fragment.IndexFragment;
import com.yzs.yzsbaseactivitylib.yzsbase.YzsBaseSupportFragmentActivity;

import me.yokeyword.fragmentation.SupportFragment;

/**
 * Author: 姚智胜
 * Version: V1.0版本
 * Description:
 * Date: 2018/04/20
 */

public class MainActivity extends YzsBaseSupportFragmentActivity {

    @Override
    public void initView() {

    }

    @Override
    public SupportFragment setFragment() {
        return IndexFragment.newInstance();
    }
}
