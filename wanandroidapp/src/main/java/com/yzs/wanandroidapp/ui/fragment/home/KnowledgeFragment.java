package com.yzs.wanandroidapp.ui.fragment.home;

import android.os.Bundle;
import android.view.View;

import com.chad.library.adapter.base.BaseViewHolder;
import com.yzs.wanandroidapp.R;
import com.yzs.wanandroidapp.bean.HomeKnowledgeBean;
import com.yzs.yzsbaseactivitylib.entity.BaseListType;
import com.yzs.yzsbaseactivitylib.yzsbase.YzsBaseFragment;
import com.yzs.yzsbaseactivitylib.yzsbase.YzsBaseListFragment;

/**
 * Author: 姚智胜
 * Version: V1.0版本
 * Description: 知识体系fragment
 * Date: 2018/4/20
 */
public class KnowledgeFragment extends YzsBaseListFragment<HomeKnowledgeBean.DataBean> {

    public static KnowledgeFragment newInstance() {

        Bundle args = new Bundle();
        KnowledgeFragment fragment = new KnowledgeFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int initItemLayout() {
        return R.layout.item_layout_knowledge;
    }

    @Override
    protected void initSetting() {
        isOpenLoad(false, false);
        setListType(BaseListType.LINEAR_LAYOUT_MANAGER, true);//设置展示方式
    }

    @Override
    protected void MyHolder(BaseViewHolder baseViewHolder, HomeKnowledgeBean.DataBean bean) {
        baseViewHolder.setText(R.id.tv_knowledge_title, bean.getName());

    }


    @Override
    protected void initLogic() {

    }
}
