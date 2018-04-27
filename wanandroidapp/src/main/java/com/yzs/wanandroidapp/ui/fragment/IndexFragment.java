package com.yzs.wanandroidapp.ui.fragment;

import android.os.Bundle;

import com.yzs.wanandroidapp.R;
import com.yzs.wanandroidapp.ui.fragment.home.HomeFragment;
import com.yzs.wanandroidapp.ui.fragment.home.KnowledgeFragment;
import com.yzs.wanandroidapp.ui.fragment.home.NaviFragment;
import com.yzs.wanandroidapp.ui.fragment.home.ProjectFragment;
import com.yzs.yzsbaseactivitylib.yzsbase.YzsBaseFragment;
import com.yzs.yzsbaseactivitylib.yzsbase.YzsBaseHomeFragment;

/**
 * Author: 姚智胜
 * Version: V1.0版本
 * Description:
 * Date: 2018/04/20
 */

public class IndexFragment extends YzsBaseHomeFragment {

    private String[] mTitles = {"首页", "知识体系", "导航", "项目"};

    private int[] mIconUnselectIds = {
            R.mipmap.icon_home_pager_not_selected, R.mipmap.icon_knowledge_hierarchy_not_selected,
            R.mipmap.icon_navigation_not_selected, R.mipmap.icon_project_not_selected};
    private int[] mIconSelectIds = {
            R.mipmap.icon_home_pager_selected, R.mipmap.icon_knowledge_hierarchy_selected,
            R.mipmap.icon_navigation_selected, R.mipmap.icon_project_selected};

    public static IndexFragment newInstance() {
        Bundle args = new Bundle();
        IndexFragment fragment = new IndexFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void onTabSelect(int i) {

    }

    @Override
    protected void onTabReselect(int i) {

    }

    @Override
    protected void initTab() {
        setmFragments(new YzsBaseFragment[]{HomeFragment.newInstance(),
                KnowledgeFragment.newInstance(),
                NaviFragment.newInstance(),
                ProjectFragment.newInstance()});

        //如果不想显示图标，最简便的方法就是直接不调用这两个方法
        setmIconSelectIds(mIconSelectIds);
         setmIconUnSelectIds(mIconUnselectIds);

        setmTitles(mTitles);
        setInitChooseTab(0);

    }

    @Override
    protected void initLogic() {

    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fg_index;
    }

    @Override
    public boolean showToolBar() {
        return false;
    }
}
