package com.yzs.wanandroidapp.ui.fragment.home;

import android.os.Bundle;
import android.view.View;

import com.chad.library.adapter.base.BaseViewHolder;
import com.yzs.wanandroidapp.R;
import com.yzs.yzsbaseactivitylib.yzsbase.YzsBaseFragment;
import com.yzs.yzsbaseactivitylib.yzsbase.YzsBaseListFragment;

/**
 * Author: 姚智胜
 * Version: V1.0版本
 * Description: 知识体系fragment
 * Date: 2018/4/20
 */
public class KnowledgeFragment extends YzsBaseListFragment{

    public static KnowledgeFragment newInstance() {

        Bundle args = new Bundle();
        KnowledgeFragment fragment = new KnowledgeFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int initItemLayout() {
        return R.layout.item_home_layout;
    }

    @Override
    protected void initSetting() {

    }

    @Override
    protected void MyHolder(BaseViewHolder baseViewHolder, Object o) {

    }

    @Override
    protected void initLogic() {

    }
}
