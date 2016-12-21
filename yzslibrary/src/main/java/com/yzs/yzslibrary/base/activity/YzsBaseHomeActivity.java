package com.yzs.yzslibrary.base.activity;

import android.support.v4.view.ViewPager;

import com.flyco.tablayout.CommonTabLayout;
import com.yzs.yzslibrary.base.fragment.YzsBaseFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: 姚智胜
 * Version: V1.0版本
 * Description: 首页的baseActivity 提供下方导航条用户只需放入图标与fragment
 * Date: 2016/12/15
 */
public abstract class YzsBaseHomeActivity extends YzsBaseActivity {
    private static final String TAG = "YzsBaseHomeActivity";
    /**
     * title文字部分
     */
    protected String[] mTitles;
    /**
     * 未选中图标数组
     */
    protected int[] mIconUnSelectIds;
    /**
     * 选中图标数组
     */
    protected int[] mIconSelectIds;
    /**
     * 导航条
     */
    protected CommonTabLayout tabLayout;
    /**
     * fragment集合
     */
    protected List<YzsBaseFragment> mFragments = new ArrayList<>();

    protected ViewPager mViewPager;

    @Override
    protected void initView() {
        initTab();
    }

    protected abstract void initTab();


}
