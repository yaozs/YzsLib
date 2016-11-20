package com.yzs.yzslibrary.util;

import android.content.Context;

import com.yzs.yzslibrary.view.dialog.ProgressDialog;


/**
 * author 姚智胜
 * version V1.0
 * Description: 加载数据时显示的对话框
 * date 2016/11/17 21:33
 */
public class LoadingDialog {
    private static ProgressDialog progressDialog;

    /**
     * 载入loading
     *
     * @param context
     */
    public static void showLoadingDialog(Context context) {
        if (null == context) {
            return;
        }

        if (progressDialog == null) {
            progressDialog = new ProgressDialog(context, "请稍候");
        }
        progressDialog.setCancelable(false);
        try {
            progressDialog.show();
        } catch (Exception e) {

        }
    }

    /**
     * 载入loading 自定义string
     *
     * @param context
     * @param str
     */
    public static void showLoadingDialog(Context context, String str) {
        if (null == context) {
            return;
        }

        if (progressDialog == null && str != null) {
            progressDialog = new ProgressDialog(context, str);
        } else {
            progressDialog = new ProgressDialog(context, "请稍候");
        }
        progressDialog.setCancelable(false);
        try {
            progressDialog.show();
        } catch (Exception e) {

        }
    }

    /**
     * 取消loading
     */
    public static void cancelLoadingDialog() {
        if (progressDialog != null && progressDialog.isShowing()) {
            try {
                progressDialog.dismiss();
            } catch (Exception e) {

            }
        }
        progressDialog = null;
    }


}
