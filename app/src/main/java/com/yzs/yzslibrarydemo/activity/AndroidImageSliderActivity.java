package com.yzs.yzslibrarydemo.activity;

import android.content.Context;
import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;
import com.youth.banner.loader.ImageLoader;
import com.yzs.yzsbaseactivitylib.activity.YzsBaseActivity;
import com.yzs.yzsbaseactivitylib.entity.EventCenter;
import com.yzs.yzslibrary.util.ToastUtils;
import com.yzs.yzslibrarydemo.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: 姚智胜
 * Version: V1.0版本
 * Description:
 * Date: 2016/11/28
 */
public class AndroidImageSliderActivity extends YzsBaseActivity {
    private static final String TAG = "AndroidImageSliderActivity";
    private Banner banner;
    private List<String> images;

    @Override
    protected void initContentView(Bundle bundle) {
        setContentView(R.layout.ac_image_slider);
        images = new ArrayList<>();
    }

    @Override
    protected void initView() {
        banner = (Banner) findViewById(R.id.banner);

    }

    @Override
    protected void initLogic() {
        images.add("http://img2.imgtn.bdimg.com/it/u=819201812,3553302270&fm=214&gp=0.jpg");
        images.add("http://pic60.nipic.com/file/20150303/12216461_110913232000_2.jpg");
        images.add("http://pic44.nipic.com/20140717/12432466_121957328000_2.jpg");
        images.add("http://sucai.qqjay.com/qqjayxiaowo/201210/26/1.jpg");
        images.add("http://pic41.nipic.com/20140518/18521768_133448822000_2.jpg");
//        easyUse();
        allUse();
    }

    /**
     * 简单使用
     */
    private void easyUse() {
        //设置图片加载器
        banner.setImageLoader(new GlideImageLoader());
        //设置图片集合
        banner.setImages(images);
        //banner设置方法全部调用完毕时最后调用
        banner.start();
    }

    /**
     * 详细使用
     */
    private void allUse() {
        //设置banner样式
        banner.setBannerStyle(BannerConfig.NUM_INDICATOR);
        //设置图片加载器
        banner.setImageLoader(new GlideImageLoader());
        //设置图片集合
        banner.setImages(images);
        //设置banner动画效果
        banner.setBannerAnimation(Transformer.DepthPage);
        //设置标题集合（当banner样式有显示title时）
//        banner.setBannerTitles(titles);
        //设置自动轮播，默认为true
        banner.isAutoPlay(true);
        //设置轮播时间
        banner.setDelayTime(1500);
        //设置指示器位置（当banner模式中有指示器时）
        banner.setIndicatorGravity(BannerConfig.CENTER);

        banner.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {
                ToastUtils.showShortToast(AndroidImageSliderActivity.this,"---"+position+"------");
            }
        });
        //banner设置方法全部调用完毕时最后调用
        banner.start();
    }


    @Override
    protected void getBundleExtras(Bundle extras) {

    }

    @Override
    protected void onEventComing(EventCenter center) {

    }


    @Override
    public void onDestroy() {
        banner = null;
        super.onDestroy();

    }

    public class GlideImageLoader extends ImageLoader {
        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            /**
             注意：
             1.图片加载器由自己选择，这里不限制，只是提供几种使用方法
             2.返回的图片路径为Object类型，由于不能确定你到底使用的那种图片加载器，
             传输的到的是什么格式，那么这种就使用Object接收和返回，你只需要强转成你传输的类型就行，
             切记不要胡乱强转！
             */

            //Glide 加载图片简单用法
            Glide.with(context).load(path).into(imageView);

            //Picasso 加载图片简单用法
//            Picasso.with(context).load(path).into(imageView);

            //用fresco加载图片简单用法，记得要写下面的createImageView方法
//            Uri uri = Uri.parse((String) path);
//            imageView.setImageURI(uri);
        }

        //提供createImageView 方法，如果不用可以不重写这个方法，主要是方便自定义ImageView的创建
//        @Override
//        public ImageView createImageView(Context context) {
//            //使用fresco，需要创建它提供的ImageView，当然你也可以用自己自定义的具有图片加载功能的ImageView
//            SimpleDraweeView simpleDraweeView=new SimpleDraweeView(context);
//            return simpleDraweeView;
//        }
    }

//    怎么加载其他图片资源（资源文件、文件、Uri、assets、raw、ContentProvider、sd卡资源）？
//
//    答：列如！如果你使用的是glide，那么可以如下操作，其他图片图片加载框架可能有不同
//
//    //资源文件
//    Integer[] images={R.mipmap.a,R.mipmap.b,R.mipmap.c};
//    //Uri
//    Uri uri = resourceIdToUri(context, R.mipmap.ic_launcher);
//    Uri[] images={uri};
//    //文件对象
//    File[] images={"文件对象","文件对象"};
//    //raw 两种方式
//    String[] images={"Android.resource://com.frank.glide/raw/raw_1"};
//    String[] images={"android.resource://com.frank.glide/raw/"+R.raw.raw_1"};
////ContentProvider
//            String[] images={"content://media/external/images/media/139469"};
//    //assets
//    String[] images={"file:///android_asset/f003.gif"};
//    //sd卡资源
//    String[] images={"file://"+ Environment.getExternalStorageDirectory().getPath()+"/test.jpg"};
//
//    banner.setImages(images);//这里接收集合,上面写成集合太占地方，这个大家举一反三就行了啊


    //如果你需要考虑更好的体验，可以这么操作
    @Override
    protected void onStart() {
        super.onStart();
        //开始轮播
        banner.startAutoPlay();
    }

    @Override
    protected void onStop() {
        super.onStop();
        //结束轮播
        banner.stopAutoPlay();
    }


}
