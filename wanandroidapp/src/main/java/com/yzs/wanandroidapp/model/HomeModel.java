package com.yzs.wanandroidapp.model;

import com.yzs.wanandroidapp.bean.BannerBean;
import com.yzs.wanandroidapp.bean.HomeBean;
import com.yzs.wanandroidapp.contract.HomeContract;
import com.yzs.wanandroidapp.http.Api;

import io.reactivex.Observable;

/**
 * Author: 姚智胜
 * Version: V1.0版本
 * Description: 首页model
 * Date: 2018/4/25
 */
public class HomeModel implements HomeContract.CHomeModel {

    @Override
    public Observable<HomeBean> requestHomeListData(int page) {
        return Api.getDefault().getFeedArticleList(page);
    }

    @Override
    public Observable<BannerBean> requestBannerData() {
        return Api.getDefault().getBanner();
    }

    @Override
    public Observable<Object>  refresh() {
        return Observable.merge(requestBannerData(), requestHomeListData(0));
    }

    @Override
    public void onDestroy() {

    }
}
