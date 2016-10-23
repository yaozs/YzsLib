package com.yzs.yzslibrarydemo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.yzs.yzslibrary.view.SwitcherView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Author: 姚智胜
 * Version: V1.0版本
 * Description:
 * Date: 2016/10/21
 */
public class MyDemoFragment extends Fragment {
    private static final String TAG = "MyDemoFragment";
    private SwitcherView switcherView;
    private View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (view == null) {
            view = inflater.inflate(R.layout.activity_main, container, false);
            init();
        }
        return view;
    }


    private void init() {
        switcherView = (SwitcherView) view.findViewById(R.id.switcherView);
        final ArrayList<String> strs = new ArrayList<>();
        strs.add("哎呦，不错哦");
        strs.add("你知道我是谁吗你知道我是谁吗你知道我是谁吗");
        strs.add("哈哈哈");
        strs.add("1111111111111");
        switcherView.setResource(strs);

        switcherView.setOnItemClick(new SwitcherView.OnItemClick() {
            @Override
            public void Click(int index) {
                Toast.makeText(getActivity(), strs.get(index),
                        Toast.LENGTH_SHORT).show();
            }
        });
        aaa();
        switcherView.start(2000);
        if (Patterns.WEB_URL.matcher("booooo000aidu.com").matches()) {
            Toast.makeText(getActivity(), "符合标准",
                    Toast.LENGTH_SHORT).show();
            Log.e("符合标准","符合标准");
            //符合标准
        } else{
            Toast.makeText(getActivity(), "不符合标准",
                    Toast.LENGTH_SHORT).show();
            Log.e("不符合标准","不符合标准");
            //不符合标准
        }
//        try {
//            WebAddress webAddress = new WebAddress("wwwwwwwww");
//        } catch (WebAddress.ParseException ex) {
//          Toast.makeText(getActivity(), (CharSequence) ex,Toast.LENGTH_SHORT).show();
//        }
    }

    public void aaa() {
        URL url;
        try {
            url = new URL("http://www.baidu.com");
            InputStream in = url.openStream();
            System.out.println("连接可用");
        } catch (Exception e1) {
            System.out.println("连接打不开!");
            url = null;
        }
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

    private static URL url;
    private static HttpURLConnection con;
    private static int state = -1;

    /**
     * 功能：检测当前URL是否可连接或是否有效,
     * 描述：最多连接网络 5 次, 如果 5 次都不成功，视为该地址不可用
     *
     * @param urlStr 指定URL网络地址
     * @return URL
     */
    public synchronized URL isConnect(String urlStr) {
        int counts = 0;
        if (urlStr == null || urlStr.length() <= 0) {
            return null;
        }
        while (counts < 5) {
            try {
                url = new URL(urlStr);
                con = (HttpURLConnection) url.openConnection();
                state = con.getResponseCode();
                System.out.println(counts + "= " + state);
                if (state == 200) {
                    System.out.println("URL可用！");
                }
                break;
            } catch (Exception ex) {
                counts++;
                System.out.println("URL不可用，连接第 " + counts + " 次");
                urlStr = null;
                continue;
            }
        }

        return url;
    }


    public static boolean checkURL(String url) {
        boolean value = false;
        try {
            HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();
            int code = conn.getResponseCode();
            System.out.println(">>>>>>>>>>>>>>>> " + code + " <<<<<<<<<<<<<<<<<<");
            if (code != 200) {
                value = false;
            } else {
                value = true;
            }
        } catch (MalformedURLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return value;
    }

}
