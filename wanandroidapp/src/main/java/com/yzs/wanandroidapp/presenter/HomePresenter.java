package com.yzs.wanandroidapp.presenter;


import com.yzs.wanandroidapp.bean.BannerBean;
import com.yzs.wanandroidapp.bean.HomeBean;
import com.yzs.wanandroidapp.contract.HomeContract;


import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Author: 姚智胜
 * Version: V1.0版本
 * Description: 首页presenter
 * Date: 2018/4/25
 */
public class HomePresenter extends HomeContract.CHomePresenter {

    @Override
    public void getDataRequest(int page) {

        mModel.requestHomeListData(page).subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<HomeBean>() {
            @Override
            public void onSubscribe(@NonNull Disposable disposable) {
                mView.showLoading("");
            }

            @Override
            public void onNext(@NonNull HomeBean homeBean) {
                mView.showData(homeBean.getData());
            }

            @Override
            public void onError(@NonNull Throwable throwable) {
                mView.showErrorTip(throwable.getMessage());
            }

            @Override
            public void onComplete() {
                mView.stopLoading();
            }
        });
    }

    @Override
    public void refresh() {
        mModel.refresh().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<Object>() {
            @Override
            public void onNext(@NonNull Object o) {
                if (o instanceof BannerBean) {
                    BannerBean bannerBean = (BannerBean) o;
                    mView.showBanner(bannerBean.getData());
                } else if (o instanceof HomeBean) {
                    HomeBean homeBean = (HomeBean) o;
                    mView.showData(homeBean.getData());
                }
            }

            @Override
            public void onError(@NonNull Throwable throwable) {
                mView.showErrorTip(throwable.getMessage());
            }

            @Override
            public void onComplete() {
                mView.stopLoading();
            }

            @Override
            public void onSubscribe(@NonNull Disposable disposable) {
                mView.showLoading("");
            }
        });
    }
}
