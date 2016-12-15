package com.yzs.yzslibrary.base.activity;

import android.support.v7.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yzs.yzslibrary.R;

import java.util.List;

/**
 * Author: 姚智胜
 * Version: V1.0版本
 * Description:  普通list的baseActivity
 * Date: 2016/12/15
 */
public abstract class YzsBaseListActivity<T> extends YzsBaseActivity {
    private static final String TAG = "YzsBaseListActivity";
    /**
     * 普通list布局
     */
    protected static final int LINEAR_LAYOUT_MANAGER = 0;
    /**
     * grid布局
     */
    protected static final int GRID_LAYOUT_MANAGER = 1;
    /**
     * 瀑布流布局
     */
    protected static final int STAGGERED_GRID_LAYOUT_MANAGER = 2;

    protected RecyclerView mRecyclerView;
    /**
     * 默认为0单行布局
     */
    protected int mListType = 0;

    @Override
    protected void initView() {
        mRecyclerView = (RecyclerView) findViewById(R.id.base_list);

    }

    /**
     * @param listType 选择布局种类
     */
    protected void chooseListType(int listType) {
        switch (listType) {
            case LINEAR_LAYOUT_MANAGER:

                break;
            case GRID_LAYOUT_MANAGER:

                break;
            case STAGGERED_GRID_LAYOUT_MANAGER:

                break;
            default:

                break;
        }
    }

    /**
     * adapter内的处理
     *
     * @param baseViewHolder BaseViewHolder
     * @param t              泛型T
     */
    protected abstract void MyHolder(BaseViewHolder baseViewHolder, T t);

    public class YzsListAdapter extends BaseQuickAdapter<T, BaseViewHolder> {

        public YzsListAdapter(int layoutResId, List<T> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder baseViewHolder, T t) {
            MyHolder(baseViewHolder, t);
        }
    }

}
