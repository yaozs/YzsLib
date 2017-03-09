package com.yzs.yzslibrarydemo.activity;

import android.os.Bundle;

import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.Indicators.PagerIndicator;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.daimajia.slider.library.Tricks.ViewPagerEx;
import com.orhanobut.logger.Logger;
import com.yzs.yzsbaseactivitylib.activity.YzsBaseActivity;
import com.yzs.yzsbaseactivitylib.entity.EventCenter;
import com.yzs.yzslibrarydemo.R;

/**
 * Author: 姚智胜
 * Version: V1.0版本
 * Description:
 * Date: 2016/11/28
 */
public class AndroidImageSliderActivity extends YzsBaseActivity {
    private static final String TAG = "AndroidImageSliderActivity";
    private SliderLayout mSliderLayout;
    private PagerIndicator indicator;

    @Override
    protected void initContentView(Bundle bundle) {
        setContentView(R.layout.ac_image_slider);
    }

    @Override
    protected void initView() {
        //容器
        mSliderLayout = (SliderLayout) findViewById(R.id.slider);
        //指示器，那些小点
        indicator = (PagerIndicator) findViewById(R.id.custom_indicator);
    }

    @Override
    protected void initLogic() {
        initSlider();
    }

    @Override
    protected void getBundleExtras(Bundle extras) {

    }

    @Override
    protected void onEventComing(EventCenter center) {

    }

    private void initSlider() {
        //SliderView有两种DefaultSliderView，TextSliderView用法一致，但是DefaultSliderView没有显示文字的功能
        //显示图片和文字
        TextSliderView textSliderView = new TextSliderView(this);
        textSliderView.setScaleType(BaseSliderView.ScaleType.CenterCrop);
        textSliderView.image("http://tupian.enterdesk.com/2013/mxy/12/10/15/3.jpg");
        textSliderView.description("新品推荐");
        textSliderView.setOnSliderClickListener(new BaseSliderView.OnSliderClickListener() {
            @Override
            public void onSliderClick(BaseSliderView baseSliderView) {
                showShortToast("新品推荐");

            }
        });
        TextSliderView textSliderView2 = new TextSliderView(this);
        textSliderView2.setScaleType(BaseSliderView.ScaleType.CenterCrop);
        textSliderView2.image("http://desk.fd.zol-img.com.cn/t_s960x600c5/g5/M00/01/0E/ChMkJlbKwaOIN8zJAAs5DadIS-IAALGbQPo5ngACzkl365.jpg");
        textSliderView2.description("时尚男装");

        textSliderView2.setOnSliderClickListener(new BaseSliderView.OnSliderClickListener() {
            @Override
            public void onSliderClick(BaseSliderView baseSliderView) {
                showShortToast("时尚男装");

            }
        });


        TextSliderView textSliderView3 = new TextSliderView(this);
        textSliderView3.setScaleType(BaseSliderView.ScaleType.CenterCrop);
        textSliderView3.image("http://m.360buyimg.com/mobilecms/s300x98_jfs/t1363/77/1381395719/60705/ce91ad5c/55dd271aN49efd216.jpg");
        textSliderView3.description("家电秒杀");


        textSliderView3.setOnSliderClickListener(new BaseSliderView.OnSliderClickListener() {
            @Override
            public void onSliderClick(BaseSliderView baseSliderView) {
                showShortToast("家电秒杀");

            }
        });


        //添加到容器中
        mSliderLayout.addSlider(textSliderView);
        mSliderLayout.addSlider(textSliderView2);
        mSliderLayout.addSlider(textSliderView3);

        //使用默认的指示器
        // mSliderLayout.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);

        mSliderLayout.setCustomIndicator(indicator);
        //阴影显示的动画效果
        mSliderLayout.setCustomAnimation(new DescriptionAnimation());
        //图片的转场效果
        // mSliderLayout.setPresetTransformer(SliderLayout.Transformer.RotateUp);
        mSliderLayout.setDuration(3000);

        mSliderLayout.addOnPageChangeListener(new ViewPagerEx.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

                Logger.d(TAG, "onPageScrolled");

            }

            @Override
            public void onPageSelected(int i) {

                Logger.d(TAG, "onPageSelected");
            }

            @Override
            public void onPageScrollStateChanged(int i) {

                Logger.d(TAG, "onPageScrollStateChanged");
            }
        });

    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        mSliderLayout.stopAutoCycle();
    }
}
