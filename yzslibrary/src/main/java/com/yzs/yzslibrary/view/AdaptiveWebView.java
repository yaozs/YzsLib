package com.yzs.yzslibrary.view;


import android.content.Context;
import android.graphics.Bitmap;
import android.net.http.SslError;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.webkit.JavascriptInterface;
import android.webkit.JsResult;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * author 姚智胜
 * version V1.0
 * Description: 解决加载Https以及根据网页动态设置WebView高度等难题
 * date 2016/5/12 13:50
 */
public class AdaptiveWebView extends WebView {

    private OnWebViewListener onWebViewListener;

    public interface OnWebViewListener {
        public void WebViewOnProgressChanged(WebView view, int newProgress);
    }

    public void setOnWebViewListener(OnWebViewListener onWebViewListener) {
        this.onWebViewListener = onWebViewListener;
    }

    public AdaptiveWebView(Context context) {
        super(context);
        init();
    }

    public AdaptiveWebView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public AdaptiveWebView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }


    private void init() {
        WebSettings setting = this.getSettings();
        setting.setJavaScriptEnabled(true);
        setting.setJavaScriptCanOpenWindowsAutomatically(true);//允许js弹出窗口
        setting.setBuiltInZoomControls(true); // 原网页基础上缩放
        setting.setUseWideViewPort(true);
        setting.setSupportZoom(true);//支持缩放
        setting.setLoadWithOverviewMode(true);
        setting.setDomStorageEnabled(true);
        setting.setBlockNetworkImage(false);
        setting.setBlockNetworkLoads(false);
        setting.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);//支持内容重新布局
        this.setVerticalScrollBarEnabled(false);
        this.setHorizontalScrollBarEnabled(false);// 水平不显示
        this.setVerticalScrollBarEnabled(false); // 垂直不显示
        setPageCacheCapacity(setting);
        setWebViewClient(this);
        setWebChromeClient(new WebChromeClient() {
            @Override
            public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
                return super.onJsAlert(view, url, message, result);
            }
        });

        addJavascriptInterface(this, "Resize");
    }

    private void setWebViewClient(WebView webView) {
        webView.setWebViewClient(new WebViewClient() {

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
            }

            @Override
            public boolean shouldOverrideKeyEvent(WebView view, KeyEvent event) {
                return super.shouldOverrideKeyEvent(view, event);
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                computeHeight();
                return true;
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                computeHeight();
//                String text = "我是来自Android";
//                view.loadUrl("javascript:carDetail('" + text + "');");
            }

            @Override
            public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
                handler.proceed();  // 接受所有网站的证书
            }


        });
        webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
                if (null != onWebViewListener) {
                    onWebViewListener.WebViewOnProgressChanged(view, newProgress);
                }
            }

            @Override
            public void onReceivedTitle(WebView view, String title) {
                super.onReceivedTitle(view, title);
            }


        });
    }

    private Handler handler = new Handler();
    private Runnable runnable;

    private void computeHeight() {
        loadUrl("javascript:Resize.fetchHeight(document.body.getBoundingClientRect().height)");
    }

    @JavascriptInterface
    public void fetchHeight(final float height) {
        final int newHeight = (int) (height * getResources().getDisplayMetrics().density);
        runnable = new Runnable() {
            @Override
            public void run() {
                if (getLayoutParams() instanceof LinearLayout.LayoutParams) {
                    LinearLayout.LayoutParams linearParams = (LinearLayout.LayoutParams) getLayoutParams();
                    linearParams.height = newHeight;
                    setLayoutParams(linearParams);
                }
            }
        };
        if (null != handler) {
            handler.postDelayed(runnable, 50);
        }

    }

    /**
     * add by zhsf @2015-11-10 当进行goBack的时候 使用前一个页面的缓存 避免每次都重新载入
     *
     * @param webSettings webView的settings
     */
    protected void setPageCacheCapacity(WebSettings webSettings) {
        try {
            Class<?> c = Class.forName("android.webkit.WebSettingsClassic");
            if (null != c) {
                Method tt = c.getMethod("setPageCacheCapacity", new Class[]{int.class});
                if (null != tt) {
                    tt.invoke(webSettings, 5);
                }
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

//    WebView加载Https网页的核心代码是：
//    @Override
//    public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
//        handler.proceed();  // 接受所有网站的证书
//    }
//
//    WebView随着网页的高度改变而改变的核心代码是：
//
//    private Handler handler = new Handler();
//    private Runnable runnable ;
//
//    private void computeHeight(){
//        loadUrl("javascript:Resize.fetchHeight(document.body.getBoundingClientRect().height)");
//    }
//
//    @JavascriptInterface
//    public void fetchHeight(final float height) {
//        final int newHeight = (int)  (height * getResources().getDisplayMetrics().density);
//        runnable = new Runnable() {
//            @Override
//            public void run() {
//                if(getLayoutParams() instanceof LinearLayout.LayoutParams){
//                    LinearLayout.LayoutParams linearParams = (LinearLayout.LayoutParams) getLayoutParams();
//                    linearParams.height = newHeight;
//                    setLayoutParams(linearParams);
//                }
//            }
//        };
//        if(null != handler){
//            handler.postDelayed(runnable, 50);
//        }
//
//    }


}
