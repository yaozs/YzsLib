package com.yzs.yzslibrary.base.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.widget.FrameLayout;

import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.orhanobut.logger.Logger;
import com.yzs.yzslibrary.R;
import com.yzs.yzslibrary.base.fragment.YzsBaseFragment;
import com.yzs.yzslibrary.entity.TabEntity;

import java.util.ArrayList;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.L;

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
    private String[] mTitles;
    /**
     * 未选中图标数组
     */
    private int[] mIconUnSelectIds;
    /**
     * 选中图标数组
     */
    private int[] mIconSelectIds;
    /**
     * fragment集合
     */
    protected YzsBaseFragment[] mFragments;
    /**
     * 导航条
     */
    protected CommonTabLayout mTabLayout;
    /**
     * 图标信息对象
     */
    private ArrayList<CustomTabEntity> mTabEntities = new ArrayList<>();

    protected ViewPager mViewPager;

    protected FrameLayout mFrameLayout;

    private Bundle bundle;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setBundle(savedInstanceState);
    }


    @Override
    protected void initView() {
        mTabLayout = (CommonTabLayout) findViewById(R.id.base_tabLayout);
        mViewPager = (ViewPager) findViewById(R.id.base_tabLayout_viewPager);
        mFrameLayout = (FrameLayout) findViewById(R.id.base_tabLayout_frameLayout);
        initTab();

        if (null == mFragments || mFragments.length == 0) {
            throw new RuntimeException("mFragments is null!");
        }
//        initFragments();
        initTabEntities();
        if (null == mTabLayout) {
            throw new RuntimeException("CommonTabLayout is null!");
        }

        if (null == mTitles || mTitles.length == 0) {
            mTabLayout.setTextsize(0);
        }

        if (null != mViewPager) {
            Logger.e("Choose_ViewPager");
            initViewpagerAdapter();
        } else {
            initFragments();
            Logger.e("Choose_frameLayout");
        }
        setTabSelect();
    }

    private void initTabEntities() {
        if (null == mFragments || mFragments.length == 0 || mFragments.length != mIconSelectIds.length ||
                mFragments.length != mIconUnSelectIds.length) {
            throw new RuntimeException("mFragments is null!or Fragments and the number of ICONS do not meet");
        }
        for (int i = 0; i < mFragments.length; i++) {
            mTabEntities.add(new TabEntity(mTitles == null ? "" : mTitles[i], mIconSelectIds[i], mIconUnSelectIds[i]));
        }
        mTabLayout.setTabData(mTabEntities);
    }

    /**
     * 初始化Fragments
     */
    private void initFragments() {
        //加载mFragments
        if (getBundle() == null) {
            //加载mFragments
            loadMultipleRootFragment(R.id.base_tabLayout_frameLayout, 1, mFragments);
        } else {
            // 这里库已经做了Fragment恢复,所有不需要额外的处理了, 不会出现重叠问题
            for (int i = 0; i < mFragments.length; i++) {
                Logger.e("initFragments" + i);
                mFragments[i] = findFragment(mFragments[i].getClass());
            }
        }
    }

    /**
     * 初始化viewpager的adapter
     */
    private void initViewpagerAdapter() {
        mViewPager.setAdapter(new MyPagerAdapter(getSupportFragmentManager()));
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                mTabLayout.setCurrentTab(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    /**
     * 为mTabLayout
     */
    private void setTabSelect() {
        Logger.e("setTabSelect");
        mTabLayout.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                if (null != mViewPager) {
                    mViewPager.setCurrentItem(position);
                } else {
                    int toDoHidden = -1;
                    for (int i = 0; i < mFragments.length; i++) {
                        if (!mFragments[i].isHidden()) {
                            toDoHidden = i;
                            Logger.e("查找显示中的fragment-------" + toDoHidden);
                        }
                    }
                    Logger.e("选中的fragment-------" + position);
                    Logger.e("确定显示中的fragment-------" + toDoHidden);

                    showHideFragment(mFragments[position], mFragments[toDoHidden]);
                }
            }

            @Override
            public void onTabReselect(int position) {
                if (position == 0) {
                    Logger.e("再次选中项" + position);
                }
            }
        });
    }

    /**
     * 设置TabLayout属性，所有关于TabLayout属性在这里设置
     */
    protected abstract void initTab();

    /**
     * 获取Fragment数组
     *
     * @return mFragments
     */
    public YzsBaseFragment[] getmFragments() {
        return mFragments;
    }

    /**
     * 放入Fragment数组（必须继承YzsBaseFragment）
     *
     * @param mFragments
     */
    public void setmFragments(YzsBaseFragment[] mFragments) {
        this.mFragments = mFragments;
    }

    /**
     * 获取选中图标数组
     *
     * @return mIconSelectIds
     */
    public int[] getmIconSelectIds() {
        return mIconSelectIds;
    }

    /**
     * 放入选中图标数组
     *
     * @param mIconSelectIds
     */
    public void setmIconSelectIds(int[] mIconSelectIds) {
        this.mIconSelectIds = mIconSelectIds;
    }

    /**
     * 获取未选中图标数组
     *
     * @return mIconUnSelectIds
     */
    public int[] getmIconUnSelectIds() {
        return mIconUnSelectIds;
    }

    /**
     * 放入未选中图标数组
     *
     * @param mIconUnSelectIds
     */
    public void setmIconUnSelectIds(int[] mIconUnSelectIds) {
        this.mIconUnSelectIds = mIconUnSelectIds;
    }

    /**
     * 获取mTitles数组
     *
     * @return mTitles
     */
    public String[] getmTitles() {
        return mTitles;
    }

    /**
     * 放入mTitles数组
     *
     * @param mTitles
     */
    public void setmTitles(String[] mTitles) {
        this.mTitles = mTitles;
    }

    public Bundle getBundle() {
        return bundle;
    }

    public void setBundle(Bundle bundle) {
        this.bundle = bundle;
    }

    private class MyPagerAdapter extends FragmentPagerAdapter {
        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public int getCount() {
            return mFragments.length;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mTitles == null ? "" : mTitles[position];
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments[position];
        }
    }

}
