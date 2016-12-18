package com.yzs.yzslibrary.view.dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.yzs.yzslibrary.R;
import com.yzs.yzslibrary.anim.Rotate3d;

/**
 * Author: 姚智胜
 * Version: V1.0版本
 * Description:
 * Date: 2016/12/18
 */
public class YzsLoadingDialog extends Dialog {
    private static final String TAG = "YzsLoadingDialog";
    private TextView tvMessage;
    private ImageView yzsLoading;
    private Context context;
    /**
     * 下方显示message
     */
    private String message;

    private Drawable drawable;

    public YzsLoadingDialog(Context context) {
        super(context, R.style.YzsLoadingDialog);
        this.context = context;
    }

    public YzsLoadingDialog(Context context, String message) {
        super(context, R.style.YzsLoadingDialog);
        this.context = context;
        this.message = message;
    }

    public YzsLoadingDialog(Context context, int theme) {
        super(context, theme);
        this.context = context;
    }

    public YzsLoadingDialog(Context context, Drawable drawable) {
        super(context);
        this.drawable = drawable;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.res_yzs_loading_dialog);
        this.setCancelable(true);
        this.setCanceledOnTouchOutside(false);
        yzsLoading = (ImageView) findViewById(R.id.iv_res_yzs_loading_dialog);
        tvMessage = (TextView) findViewById(R.id.tv_res_yzs_loading_dialog_message);
        if (message != null) {
            tvMessage.setText(message);
        }
        if (null == drawable) {
            yzsLoading.setImageDrawable(context.getResources().getDrawable(R.mipmap.icon));
        } else {
            yzsLoading.setImageDrawable(drawable);
        }
        Rotate3d rotate3d = new Rotate3d();
        rotate3d.setDuration(2000);
        rotate3d.setRepeatCount(Integer.MAX_VALUE);
        rotate3d.setRepeatMode(2);
        yzsLoading.startAnimation(rotate3d);
    }

    public void show(String message) {
        this.message = message;
        if (tvMessage != null && message != null) {
            tvMessage.setText(message);
        }
        super.show();
    }

    public void show(int msgResId) {
        show(getContext().getString(msgResId));
    }


    public YzsLoadingDialog setYzsMessage(String message) {
        this.message = message;
        if (tvMessage != null && message != null) {
            tvMessage.setText(message);
        }
        return this;
    }

    public YzsLoadingDialog setYzsMessage(int resId) {
        this.message = getContext().getString(resId);
        if (tvMessage != null && message != null) {
            tvMessage.setText(message);
        }
        return this;
    }

    @Override
    public void hide() {
        if (null != yzsLoading) {
            yzsLoading.clearAnimation();
        }
        super.hide();
    }

    @Override
    public void dismiss() {
        if (null != yzsLoading) {
            yzsLoading.clearAnimation();
        }
        super.dismiss();
    }
}
