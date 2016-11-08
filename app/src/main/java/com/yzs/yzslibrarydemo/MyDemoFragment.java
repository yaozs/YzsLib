package com.yzs.yzslibrarydemo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Toast;

import com.yzs.yzslibrary.base.YzsBaseFragment;
import com.yzs.yzslibrary.view.AdaptiveWebView;
import com.yzs.yzslibrary.view.NoticeView;
import com.yzs.yzslibrary.view.nicespinner.NiceSpinner;
import com.yzs.yzslibrary.view.togglebutton.ToggleButton;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;


/**
 * Author: 姚智胜
 * Version: V1.0版本
 * Description:
 * Date: 2016/10/21
 */
public class MyDemoFragment extends YzsBaseFragment {
    private static final String TAG = "MyDemoFragment";
    private NoticeView switcherView;
    private View view;

    private ToggleButton toggleButton;
    private NiceSpinner niceSpinner;
    private AdaptiveWebView webView;

    @Override
    protected View initContentView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (view == null) {
            view = inflater.inflate(R.layout.activity_main, container, false);

        }
        return view;
    }

    @Override
    protected void initView() {
        switcherView = (NoticeView) view.findViewById(R.id.switcherView);
        toggleButton = (ToggleButton) view.findViewById(R.id.toggleButton);
        niceSpinner = (NiceSpinner) view.findViewById(R.id.nice_spinner);
        webView = (AdaptiveWebView) view.findViewById(R.id.webview);
        webView.loadUrl("https://www.baidu.com/s?wd=androidstudio%E6%80%8E%E6%A0%B7%E5%8D%B8%E8%BD%BD%E6%8F%92%E4%BB%B6&rsv_spt=1&rsv_iqid=0xa707c9730002e3f9&issp=1&f=8&rsv_bp=1&rsv_idx=2&ie=utf-8&rqlang=cn&tn=baiduhome_pg&rsv_enter=1&rsv_t=664dTHWSNZIDf4YT0cKcd%2FIjC4lf0IhA9shx8MkFGzzXdukyFMa0zO2Hs8fd7wa98WGv&oq=eventbus%20%26lt%3B.0&inputT=10070&rsv_pq=fe27f6f500032081&rsv_sug3=12&rsv_sug1=6&rsv_sug7=100&rsv_sug2=0&rsv_sug4=10070");
    }

    @Override
    protected void initLogic() {
        final ArrayList<String> strs = new ArrayList<>();
        strs.add("哎呦，不错哦");
        strs.add("你知道我是谁吗你知道我是谁吗你知道我是谁吗");
        strs.add("哈哈哈");
        strs.add("1111111111111");
        switcherView.setResource(strs);

        switcherView.setOnItemClick(new NoticeView.OnItemClick() {
            @Override
            public void Click(int index) {
                Toast.makeText(getActivity(), strs.get(index),
                        Toast.LENGTH_SHORT).show();
            }
        });

        switcherView.start(2000);

        toggleButton = (ToggleButton) view.findViewById(R.id.toggleButton);
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
                    Toast.makeText(getActivity(), "on", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getActivity(), "off", Toast.LENGTH_SHORT).show();
                }
            }
        });


        final List<String> dataset = new LinkedList<>(Arrays.asList("最新发布", "最多点赞"));

        niceSpinner.attachDataSource(dataset);
        niceSpinner.addOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(getActivity(), dataset.get(i), Toast.LENGTH_SHORT).show();
            }
        });
    }


    @Override
    public void onResume() {
        switcherView.onResume();
        super.onResume();
    }

    @Override
    public void onStop() {
        switcherView.onStop();
        super.onStop();

    }

    @Override
    public void onDestroy() {
        switcherView.onDestroy();
        super.onDestroy();
    }

}
