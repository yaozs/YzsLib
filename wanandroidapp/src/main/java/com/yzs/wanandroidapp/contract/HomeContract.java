package com.yzs.wanandroidapp.contract;

import com.yzs.wanandroidapp.bean.BannerBean;
import com.yzs.wanandroidapp.bean.HomeBean;
import com.yzs.yzsbaseactivitylib.basemvp.BaseModel;
import com.yzs.yzsbaseactivitylib.basemvp.BasePresenter;
import com.yzs.yzsbaseactivitylib.basemvp.BaseView;

import java.util.List;

import io.reactivex.Observable;

/**
 * Author: 姚智胜
 * Version: V1.0版本
 * Description:
 * Date: 2018/4/25
 */
public interface HomeContract {
    interface CHomeView extends BaseView {
        void showData(HomeBean.DataBean bean);

        void showBanner(List<BannerBean.DataBean> bannerList);
    }

    interface CHomeModel extends BaseModel {

        Observable<HomeBean> requestHomeListData(int page);

        Observable<BannerBean> requestBannerData();
    }

    abstract static class CHomePresenter extends BasePresenter<CHomeView, CHomeModel> {
        public abstract void getDataRequest(int page);

        public abstract void getBannerDataRequest();
    }
}
