package com.yzs.yzslibrarydemo.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;

import com.yzs.yzslibrary.base.YzsBaseActivity;
import com.yzs.yzslibrary.entity.EventCenter;
import com.yzs.yzslibrary.view.NoticeView;
import com.yzs.yzslibrary.view.nicespinner.NiceSpinner;
import com.yzs.yzslibrary.view.togglebutton.ToggleButton;
import com.yzs.yzslibrarydemo.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class FirstViewActivity extends YzsBaseActivity {

    private NoticeView noticeView;//广告条控件
    private ToggleButton toggleButton;//仿苹果滑动控件
    private NiceSpinner niceSpinner;//spinner替代控件

    @Override
    protected void initContentView(Bundle bundle) {
        setContentView(R.layout.ac_first_view);
    }


    @Override
    protected void initView() {
        noticeView = (NoticeView) findViewById(R.id.switcherView);
        toggleButton = (ToggleButton) findViewById(R.id.toggleButton);
        niceSpinner = (NiceSpinner) findViewById(R.id.nice_spinner);
    }

    @Override
    protected void initLogic() {
        setTitle(title);
        showLoadingDialog();
        final ArrayList<String> strs = new ArrayList<>();
        strs.add("哎呦，不错哦");
        strs.add("你知道我是谁吗你知道我是谁吗你知道我是谁吗");
        strs.add("哈哈哈");
        strs.add("1111111111111");
        noticeView.setResource(strs);

        noticeView.setOnItemClick(new NoticeView.OnItemClick() {
            @Override
            public void Click(int index) {
                showShortToast(strs.get(index));
            }
        });

        noticeView.start(2000);

//        //切换开关
//        toggleBtn.toggle();
//        //切换无动画
//        toggleBtn.toggle(false);
//        //开关切换事件
//        toggleBtn.setOnToggleChanged(new OnToggleChanged(){
//            @Override
//            public void onToggle(boolean on) {
//            }
//        });
//
//        toggleBtn.setToggleOn();
//        toggleBtn.setToggleOff();
//        //无动画切换
//        toggleBtn.setToggleOn(false);
//        toggleBtn.setToggleOff(false);
//
//        //禁用动画
//        toggleBtn.setAnimate(false);
        toggleButton.setOnToggleChanged(new ToggleButton.OnToggleChanged() {
            @Override
            public void onToggle(boolean on) {
                if (on) {
                    showShortToast("on");
                } else {
                    showShortToast("off");
                }
            }
        });


        final List<String> dataset = new LinkedList<>(Arrays.asList("最新发布", "最多点赞",
                "最新发布1最新发布1最新发布1", "最多点赞1","最新发布222", "点赞"));

        niceSpinner.attachDataSource(dataset);
        niceSpinner.addOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                showShortToast(dataset.get(i));
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

    @Override
    protected void onResume() {
        noticeView.onResume();
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        noticeView.onDestroy();
        super.onDestroy();
    }


}
