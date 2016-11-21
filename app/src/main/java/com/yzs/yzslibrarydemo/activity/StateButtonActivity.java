package com.yzs.yzslibrarydemo.activity;

import android.os.Bundle;
import android.view.View;

import com.yzs.yzslibrary.base.YzsBaseActivity;
import com.yzs.yzslibrary.entity.EventCenter;
import com.yzs.yzslibrary.view.StateButton;
import com.yzs.yzslibrarydemo.R;
import com.yzs.yzslibrarydemo.entity.EventBusCode;

import org.greenrobot.eventbus.EventBus;

/**
 * Author: 姚智胜
 * Version: V1.0版本
 * Description: 不用写selector的button
 * Date: 2016/11/21
 */
public class StateButtonActivity extends YzsBaseActivity {
    private static final String TAG = "StateButtonActivity";
    private StateButton text;

    private StateButton background;

    private StateButton radius;

    private StateButton stroke;

    private StateButton dash;

    @Override
    protected void initContentView(Bundle bundle) {
        setContentView(R.layout.ac_statebutton);
    }

    @Override
    protected void initView() {
        setTitle("");
        //设置不同状态下文字变色
        text = (StateButton) findViewById(R.id.text_test);

        //最常用的设置不同背景
        background = (StateButton) findViewById(R.id.background_test);

        //设置四个角不同的圆角
        radius = (StateButton) findViewById(R.id.different_radius_test);
        radius.setRadius(new float[]{0, 0, 20, 20, 40, 40, 60, 60});

        //设置不同状态下边框颜色，宽度
        stroke = (StateButton) findViewById(R.id.stroke_test);

        //设置间断
        dash = (StateButton) findViewById(R.id.dash_test);

    }


    @Override
    protected void initLogic() {
        setTitle(title);
        text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EventCenter center = new EventCenter(EventBusCode.EVENT_BUS_DEMO);
                EventBus.getDefault().post(center);
                text.setEnabled(false);

            }
        });

        background.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                background.setEnabled(false);
            }
        });

        stroke.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stroke.setEnabled(false);
            }
        });

        dash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dash.setEnabled(false);
            }
        });
    }

    private String title;

    @Override
    protected void getBundleExtras(Bundle extras) {
        title = extras.getString("title");
    }

    @Override
    protected void onEventComing(EventCenter center) {

    }
}
