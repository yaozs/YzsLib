package com.yzs.yzslibrarydemo.activity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.yzs.yzsbaseactivitylib.activity.YzsBaseActivity;
import com.yzs.yzsbaseactivitylib.entity.EventCenter;
import com.yzs.yzslibrary.view.AdaptiveWebView;
import com.yzs.yzslibrarydemo.R;

/**
 * Author: 姚智胜
 * Version: V1.0版本
 * Description: 自适应webview  Demo
 * Date: 2016/11/20
 */
public class AdaptiveWebViewActivity extends YzsBaseActivity {
    private static final String TAG = "AdaptiveWebViewActivity";

    private AdaptiveWebView webView;

    @Override
    protected void initContentView(Bundle bundle) {
        setContentView(R.layout.ac_adaptivewebview);
    }

    @Override
    protected void initView() {
        webView = (AdaptiveWebView) findViewById(R.id.adaptiveWebView);
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                showLoadingDialog();
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                cancelLoadingDialog();
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                //去WebView打开
                view.loadUrl(url);
                return true;
            }

        });
        webView.loadUrl("http://m.toutiao.com/group/6348269082899497218/?iid=6241145735&app=news_article&wxshare_count=1&tt_from=weixin&utm_source=weixin&utm_medium=toutiao_android&utm_campaign=client_share");

    }

    @Override
    protected void initLogic() {
        setTitle(title);
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
