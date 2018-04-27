package com.yzs.wanandroidapp.app;

import com.squareup.leakcanary.LeakCanary;
import com.yzs.yzslibrary.BuildConfig;
import com.yzs.yzslibrary.app.BaseApp;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Author: 姚智胜
 * Version: V1.0版本
 * Description:
 * Date: 2018/04/20
 */

public class App extends BaseApp {

    @Override
    protected void onInitCreate() {
        if (LeakCanary.isInAnalyzerProcess(this)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return;
        }
        LeakCanary.install(this);
        initHttp();
    }

    private void initHttp() {
//        OkHttpClient.Builder builder = new OkHttpClient().newBuilder();
////        if (BuildConfig.DEBUG) {
////            HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
////            httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
////            builder.addInterceptor(httpLoggingInterceptor);
//////            builder.addNetworkInterceptor(new StethoInterceptor());
////            BuildConfig.STETHO.addNetworkInterceptor(builder);
////        }
//        OkHttpClient client = builder.build();
////
//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl("http://www.wanandroid.com/")
//                .addConverterFactory(GsonConverterFactory.create())
////                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
//                .client(client)
//                .build();
    }
}
