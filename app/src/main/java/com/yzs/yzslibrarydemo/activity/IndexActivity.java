package com.yzs.yzslibrarydemo.activity;

import android.os.Bundle;

import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.orhanobut.logger.Logger;
import com.yzs.yzslibrary.base.activity.YzsBaseActivity;
import com.yzs.yzslibrary.base.fragment.YzsBaseFragment;
import com.yzs.yzslibrary.entity.EventCenter;
import com.yzs.yzslibrary.util.loading.LoadingDialog;
import com.yzs.yzslibrarydemo.R;
import com.yzs.yzslibrarydemo.entity.TabEntity;
import com.yzs.yzslibrarydemo.fragment.HomeFragment;
import com.yzs.yzslibrarydemo.fragment.MoreFragment;
import com.yzs.yzslibrarydemo.fragment.MsgFragment;
import com.yzs.yzslibrarydemo.fragment.PersonFragment;

import java.util.ArrayList;

/**
 * Author: 姚智胜
 * Version: V1.0版本
 * Description:  首页
 * Date: 2016/12/15.
 */
public class IndexActivity extends YzsBaseActivity {
    private static final String TAG = "IndexActivity";

    public static final int FIRST = 0;
    public static final int SECOND = 1;
    public static final int THIRD = 2;
    public static final int FOURTH = 3;
    /**
     * 导航栏
     */
    private CommonTabLayout mTabLayout;

//    private ViewPager mViewPager;

    private ArrayList<YzsBaseFragment> mFragmentList = new ArrayList<>();

    private String[] mTitles = {"首页", "消息", "联系人", "更多"};

    private int[] mIconUnselectIds = {
            R.drawable.tab_home_unselect, R.drawable.tab_speech_unselect,
            R.drawable.tab_contact_unselect, R.drawable.tab_more_unselect};
    private int[] mIconSelectIds = {
            R.drawable.tab_home_select, R.drawable.tab_speech_select,
            R.drawable.tab_contact_select, R.drawable.tab_more_select};

    private ArrayList<CustomTabEntity> mTabEntities = new ArrayList<>();

    @Override
    protected void initContentView(Bundle bundle) {
        setContentView(R.layout.ac_index);

        if (bundle == null) {
            //添加4个主页

            mFragmentList.add(new HomeFragment());
            mFragmentList.add(new MsgFragment());
            mFragmentList.add(new PersonFragment());
            mFragmentList.add(new MoreFragment());

            loadMultipleRootFragment(R.id.fl_ac_index, FIRST,
                    mFragmentList.get(FIRST),
                    mFragmentList.get(SECOND),
                    mFragmentList.get(THIRD),
                    mFragmentList.get(FOURTH));
        } else {
            // 这里库已经做了Fragment恢复,所有不需要额外的处理了, 不会出现重叠问题

            // 这里我们需要拿到mFragments的引用,也可以通过getSupportFragmentManager.getFragments()自行进行判断查找(效率更高些),用下面的方法查找更方便些
            mFragmentList.set(FIRST, findFragment(HomeFragment.class));
            mFragmentList.set(SECOND, findFragment(MsgFragment.class));
            mFragmentList.set(THIRD, findFragment(PersonFragment.class));
            mFragmentList.set(FOURTH, findFragment(MoreFragment.class));

        }
    }

    @Override
    protected void initView() {
        mTabLayout = (CommonTabLayout) findViewById(R.id.tl_ac_index);


        for (int i = 0; i < mTitles.length; i++) {
            mTabEntities.add(new TabEntity(mTitles[i], mIconSelectIds[i], mIconUnselectIds[i]));
        }

        mTabLayout.setTabData(mTabEntities);
        tl_init();
    }

    @Override
    protected void initLogic() {
        showLoadingDialog(LoadingDialog.YZS_LOADING,R.mipmap.icon);
//        showLoadingDialog();

    }

    @Override
    protected void getBundleExtras(Bundle extras) {

    }

    @Override
    protected void onEventComing(EventCenter center) {

    }


    private void tl_init() {
        mTabLayout.setTabData(mTabEntities);
        mTabLayout.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                int toDoHidden = -1;
                for (int i = 0; i < mFragmentList.size(); i++) {
                    if (!mFragmentList.get(i).isHidden()) {
                        toDoHidden = i;
                        Logger.e("查找显示中的fragment-------" + toDoHidden);
                    }
                }
                Logger.e("选中的fragment-------" + position);
                Logger.e("确定显示中的fragment-------" + toDoHidden);

                showHideFragment(mFragmentList.get(position), mFragmentList.get(toDoHidden));
            }

            @Override
            public void onTabReselect(int position) {
                if (position == 0) {

                    //新消息显示
//                    mTabLayout.showMsg(0, mRandom.nextInt(100) + 1);
                }
            }
        });

    }

}
