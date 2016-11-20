package com.yzs.yzslibrarydemo.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yzs.yzslibrarydemo.R;
import com.yzs.yzslibrarydemo.entity.DemoBean;

import java.util.List;

/**
 * Author: 姚智胜
 * Version: V1.0版本
 * Description:
 * Date: 2016/11/20
 */
public class DemoAdapter extends BaseQuickAdapter<DemoBean, BaseViewHolder> {
    private static final String TAG = "DemoAdapter";

    public DemoAdapter(int layoutResId, List<DemoBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, DemoBean demoBean) {
        baseViewHolder.setText(R.id.tv_item_demo, demoBean.getName());
    }
}
