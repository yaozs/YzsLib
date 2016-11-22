package com.yzs.yzslibrary.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

/**
 * author 姚智胜
 * version V1.0
 * Description: 在ScrollView中的ListView
 * date 2016/11/21 23:38
 */
public class BMListViewForScrollView extends ListView {

    public BMListViewForScrollView(Context context) {
        super(context);
    }

    public BMListViewForScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public BMListViewForScrollView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
                MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }
}
