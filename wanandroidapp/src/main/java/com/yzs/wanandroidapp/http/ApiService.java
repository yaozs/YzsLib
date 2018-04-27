package com.yzs.wanandroidapp.http;


import com.yzs.wanandroidapp.bean.BannerBean;
import com.yzs.wanandroidapp.bean.HomeBean;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Author: 姚智胜
 * Version: V1.0版本
 * Description: 创建 用于描述网络请求 的接口
 * Date: 2018/4/23
 */
public interface ApiService {
    /**
     * 获取feed文章列表
     *
     * @param num 页数
     * @return feed文章列表数据
     */
    @GET("article/list/{num}/json")
    Observable<HomeBean> getFeedArticleList(@Path("num") int num);

    /**
     * 获取banner
     *
     * @return BannerBean 轮播图
     */
    @GET("banner/json")
    Observable<BannerBean> getBanner();

}
