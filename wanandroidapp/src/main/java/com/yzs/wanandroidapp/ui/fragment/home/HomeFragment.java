package com.yzs.wanandroidapp.ui.fragment.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.like.LikeButton;
import com.like.OnLikeListener;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;
import com.yzs.wanandroidapp.R;
import com.yzs.wanandroidapp.bean.BannerBean;
import com.yzs.wanandroidapp.bean.HomeBean;
import com.yzs.wanandroidapp.contract.HomeContract;
import com.yzs.wanandroidapp.model.HomeModel;
import com.yzs.wanandroidapp.presenter.HomePresenter;
import com.yzs.wanandroidapp.utils.GlideImageLoader;
import com.yzs.yzsbaseactivitylib.entity.BaseListType;
import com.yzs.yzsbaseactivitylib.util.LoadingDialogUtils;
import com.yzs.yzsbaseactivitylib.yzsbase.YzsBaseMvpListFragment;
import com.yzs.yzslibrary.util.TimeUtils;
import com.yzs.yzslibrary.util.ToastUtils;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;


/**
 * Author: 姚智胜
 * Version: V1.0版本
 * Description: 首页fragment
 * Date: 2018/4/20
 */
public class HomeFragment extends YzsBaseMvpListFragment<HomePresenter, HomeModel, HomeBean.DataBean.DatasBean>
        implements HomeContract.CHomeView {
    private Banner banner;

    public static HomeFragment newInstance() {

        Bundle args = new Bundle();
        HomeFragment fragment = new HomeFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int initItemLayout() {
        return R.layout.item_home_layout;
    }

    @Override
    protected void initSetting() {
        isOpenLoad(true, true);//是否开启加载和刷新
        setListType(BaseListType.LINEAR_LAYOUT_MANAGER, true);//设置展示方式
    }

    @Override
    protected void MyHolder(BaseViewHolder baseViewHolder, final HomeBean.DataBean.DatasBean homeBean) {
        baseViewHolder.setText(R.id.tv_home_author, homeBean.getAuthor());
        baseViewHolder.setText(R.id.tv_home_title, homeBean.getTitle());
        baseViewHolder.setText(R.id.tv_home_type, homeBean.getSuperChapterName() + "/" + homeBean.getChapterName());
        baseViewHolder.setText(R.id.tv_home_time, TimeUtils.milliseconds2String(homeBean.getPublishTime(),
                new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())));
        LikeButton likeButton = baseViewHolder.getView(R.id.cb_home_collect);
        if (homeBean.isCollect()) {
            likeButton.setLiked(true);
        } else {
            likeButton.setLiked(false);
        }
        likeButton.setOnLikeListener(new OnLikeListener() {
            @Override
            public void liked(LikeButton likeButton) {
                homeBean.setCollect(true);
            }

            @Override
            public void unLiked(LikeButton likeButton) {
                homeBean.setCollect(false);
            }
        });
    }

    @Override
    protected void initView(View view) {
        super.initView(view);
        setMvp(true);
    }

    @Override
    public void initPresenter() {
        mPresenter.setVM(this, mModel);
    }

    @Override
    protected void initLogic() {
        setPage(0);
        setmPageSize(20);
        View headView = LayoutInflater.from(_mActivity).inflate(R.layout.layout_head_home, null);
        banner = (Banner) headView.findViewById(R.id.banner_home);
        mAdapter.addHeaderView(headView);
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {

            }
        });
        autoRefresh();
    }

    @Override
    public boolean showToolBar() {
        return false;
    }

    /**
     * 详细使用
     */
    private void initBanner(List<BannerBean.DataBean> bannerList) {
        //设置banner样式
        banner.setBannerStyle(BannerConfig.NUM_INDICATOR);
        //设置图片加载器
        banner.setImageLoader(new GlideImageLoader());
        //设置图片集合
        banner.setImages(bannerList);
        //设置banner动画效果
        banner.setBannerAnimation(Transformer.DepthPage);
        //设置标题集合（当banner样式有显示title时）
//        banner.setBannerTitles(titles);
        //设置自动轮播，默认为true
        banner.isAutoPlay(true);
        //设置轮播时间
        banner.setDelayTime(3000);
        //设置指示器位置（当banner模式中有指示器时）
        banner.setIndicatorGravity(BannerConfig.CENTER);

        banner.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {
                ToastUtils.showShortToast(_mActivity, "---" + position + "------");
            }
        });
        //banner设置方法全部调用完毕时最后调用
        banner.start();
    }


    @Override
    public void showLoading(String s) {
        LoadingDialogUtils.showLoadingDialog(_mActivity, 1);
    }

    @Override
    public void stopLoading() {
        LoadingDialogUtils.cancelLoadingDialog();
    }

    @Override
    public void showErrorTip(String s) {
        ToastUtils.showShortToast(_mActivity, s);
    }

    @Override
    public void showData(HomeBean.DataBean bean) {
        autoListLoad(bean.getDatas(), "没有数据奥！", R.mipmap.icon);
    }

    @Override
    public void showBanner(List<BannerBean.DataBean> bannerList) {
        initBanner(bannerList);
    }

    @Override
    protected void refreshListener() {
        setPage(0);
        mPresenter.refresh();
    }

    @Override
    protected void loadMoreListener() {
        mPresenter.getDataRequest(getPage());
    }
}
