package com.yzs.wanandroidapp.model;

import com.google.gson.Gson;
import com.yzs.wanandroidapp.bean.BannerBean;
import com.yzs.wanandroidapp.bean.HomeBean;
import com.yzs.wanandroidapp.contract.HomeContract;
import com.yzs.wanandroidapp.http.Api;
import com.yzs.yzslibrary.util.LogUtils;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * Author: 姚智胜
 * Version: V1.0版本
 * Description:
 * Date: 2018/4/25
 */
public class HomeModel implements HomeContract.CHomeModel {

    @Override
    public Observable<HomeBean> requestHomeListData(int page) {
        return Api.getDefault().getFeedArticleList(page).map(new Function<HomeBean, HomeBean>() {
            @Override
            public HomeBean apply(@NonNull HomeBean homeBean) throws Exception {
                return homeBean;
            }
        });
//                subscribeOn(Schedulers.io()).
//                observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<HomeBean>() {
//            @Override
//            public void onSubscribe(@NonNull Disposable disposable) {
//
//            }
//
//            @Override
//            public void onNext(@NonNull HomeBean homeBean) {
//                LogUtils.e(new Gson().toJson(homeBean));
//            }
//
//            @Override
//            public void onError(@NonNull Throwable throwable) {
//
//            }
//
//            @Override
//            public void onComplete() {
//
//            }
//        });
    }

    @Override
    public Observable<BannerBean> requestBannerData() {
        return Api.getDefault().getBanner().map(new Function<BannerBean, BannerBean>() {
            @Override
            public BannerBean apply(@NonNull BannerBean bannerBean) throws Exception {
                return bannerBean;
            }
        });
    }

    @Override
    public void onDestroy() {

    }
}
