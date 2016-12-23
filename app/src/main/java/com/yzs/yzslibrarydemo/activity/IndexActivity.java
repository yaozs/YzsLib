package com.yzs.yzslibrarydemo.activity;

import android.os.Bundle;
import android.os.Handler;

import com.yzs.yzslibrary.base.activity.YzsBaseHomeActivity;
import com.yzs.yzslibrary.base.fragment.YzsBaseFragment;
import com.yzs.yzslibrary.entity.EventCenter;
import com.yzs.yzslibrary.util.loading.LoadingDialog;
import com.yzs.yzslibrarydemo.R;
import com.yzs.yzslibrarydemo.fragment.HomeFragment;
import com.yzs.yzslibrarydemo.fragment.MoreFragment;
import com.yzs.yzslibrarydemo.fragment.MsgFragment;
import com.yzs.yzslibrarydemo.fragment.PersonFragment;

import static android.nfc.tech.MifareUltralight.PAGE_SIZE;

/**
 * Author: 姚智胜
 * Version: V1.0版本
 * Description:  首页
 * Date: 2016/12/15.
 */
public class IndexActivity extends YzsBaseHomeActivity {
    private static final String TAG = "IndexActivity";

    private String[] mTitles = {"首页", "消息", "联系人", "更多"};

    private int[] mIconUnselectIds = {
            R.drawable.tab_home_unselect, R.drawable.tab_speech_unselect,
            R.drawable.tab_contact_unselect, R.drawable.tab_more_unselect};
    private int[] mIconSelectIds = {
            R.drawable.tab_home_select, R.drawable.tab_speech_select,
            R.drawable.tab_contact_select, R.drawable.tab_more_select};

    @Override
    protected void initContentView(Bundle bundle) {
        setContentView(R.layout.ac_index);
    }

    @Override
    protected void initTab() {
        setmFragments(new YzsBaseFragment[]{new HomeFragment(), new MsgFragment(), new PersonFragment(), new MoreFragment()});
        setmIconSelectIds(mIconSelectIds);
        setmIconUnSelectIds(mIconUnselectIds);
    }


    @Override
    protected void initLogic() {
        //一句话调用loading
        showLoadingDialog(LoadingDialog.YZS_LOADING, R.mipmap.icon);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //取消loading
                cancelLoadingDialog();
            }
        }, 3000);

    }

    @Override
    protected void getBundleExtras(Bundle extras) {

    }

    @Override
    protected void onEventComing(EventCenter center) {

    }


}
