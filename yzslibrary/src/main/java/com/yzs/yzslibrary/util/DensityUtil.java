package com.yzs.yzslibrary.util;

import android.content.Context;

/**
 * 创建人：姚智胜
 * 创建时间： 2016/11/7.
 * 简介：
 */

public class DensityUtil {
    public DensityUtil() {
    }

    public static int dip2px(Context context, float dpValue) {
        float scale = context.getResources().getDisplayMetrics().density;
        return (int)(dpValue * scale + 0.5F);
    }

    public static int px2dip(Context context, float pxValue) {
        float scale = context.getResources().getDisplayMetrics().density;
        return (int)(pxValue / scale + 0.5F);
    }
}
