package com.yzs.yzslibrarydemo.activity;

import android.os.Bundle;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.yzs.yzslibrary.base.activity.YzsBaseListActivity;
import com.yzs.yzslibrary.entity.EventCenter;
import com.yzs.yzslibrarydemo.R;
import com.yzs.yzslibrarydemo.entity.DemoBean;

import java.util.ArrayList;
import java.util.List;


/**
 * Author: 姚智胜
 * Version: V1.0版本
 * Description:
 * Date: 2016/11/18
 */
public class DemoActivity extends YzsBaseListActivity<DemoBean> {


    @Override
    protected void initContentView(Bundle bundle) {
        setContentView(R.layout.ac_demo);
    }

    @Override
    protected void initLayoutResId() {
        setLayoutResId(R.layout.item_demo);
    }

    @Override
    protected void initLogic() {
        setTitle("20行自己写的代码出现list界面");
        mRecyclerView.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void SimpleOnItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                Bundle bundle = new Bundle();
                bundle.putString("title", mAdapter.getItem(i).getName());
                readyGo(mAdapter.getItem(i).getClazz(), bundle);
            }
        });
        addData();
    }

    @Override
    protected void getBundleExtras(Bundle extras) {

    }

    @Override
    protected void onEventComing(EventCenter center) {

    }


    @Override
    protected void MyHolder(BaseViewHolder baseViewHolder, DemoBean demoBean) {
        baseViewHolder.setText(R.id.tv_item_demo, demoBean.getName());
    }

    private void addData() {
        List<DemoBean> list = new ArrayList<>();
        DemoBean bean = new DemoBean();
        bean.setClazz(FirstViewActivity.class);
        bean.setName("控件集合1");
        list.add(bean);
        bean = new DemoBean();
        bean.setClazz(AdaptiveWebViewActivity.class);
        bean.setName("自适应高度的webview+演示Loading");
        list.add(bean);
        bean = new DemoBean();
        bean.setClazz(StateButtonActivity.class);
        bean.setName("不用写selector的button+EventBus示范");
        list.add(bean);
        bean = new DemoBean();
        bean.setClazz(LikeIosDialogActivity.class);
        bean.setName("仿ios的dialog");
        list.add(bean);
        bean = new DemoBean();
        bean.setClazz(AndroidImageSliderActivity.class);
        bean.setName("AndroidImageSliderActivity");
        list.add(bean);
        bean = new DemoBean();
        bean.setClazz(UseAdapterActivity.class);
        bean.setName("UseAdapterActivity");
        list.add(bean);
        mAdapter.addData(list);
    }

//    private static final String TAG = "DemoActivity";
//    private RecyclerView recyclerView;
//    private DemoAdapter adapter;
//    private List<DemoBean> list = new ArrayList<>();
//
//    @Override
//    protected void initContentView(Bundle bundle) {
//        setContentView(R.layout.ac_demo);
//    }
//
//    @Override
//    protected void initView() {
//        back.setVisibility(View.GONE);
//        setTitle(R.string.app_name);
//        recyclerView = (RecyclerView) findViewById(R.id.recyclerview_ac_demo);
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//        adapter = new DemoAdapter(R.layout.item_demo, new ArrayList<DemoBean>());
//        recyclerView.addItemDecoration(new DividerItemDecoration(this, 1));
//        recyclerView.addOnItemTouchListener(new OnItemClickListener() {
//            @Override
//            public void SimpleOnItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
//                Bundle bundle = new Bundle();
//                bundle.putString("title", adapter.getItem(i).getName());
//                readyGo(adapter.getItem(i).getClazz(), bundle);
//            }
//        });
//        recyclerView.setAdapter(adapter);
//
//    }
//
//    @Override
//    protected void initLogic() {
//        DemoBean bean = new DemoBean();
//        bean.setClazz(FirstViewActivity.class);
//        bean.setName("控件集合1");
//        list.add(bean);
//        bean = new DemoBean();
//        bean.setClazz(AdaptiveWebViewActivity.class);
//        bean.setName("自适应高度的webview+演示Loading");
//        list.add(bean);
//        bean = new DemoBean();
//        bean.setClazz(StateButtonActivity.class);
//        bean.setName("不用写selector的button+EventBus示范");
//        list.add(bean);
//        bean = new DemoBean();
//        bean.setClazz(LikeIosDialogActivity.class);
//        bean.setName("仿ios的dialog");
//        list.add(bean);
//        bean = new DemoBean();
//        bean.setClazz(AndroidImageSliderActivity.class);
//        bean.setName("AndroidImageSliderActivity");
//        list.add(bean);
//        bean = new DemoBean();
//        bean.setClazz(UseAdapterActivity.class);
//        bean.setName("UseAdapterActivity");
//        list.add(bean);
//        adapter.addData(list);
//    }
//
//    @Override
//    protected void getBundleExtras(Bundle extras) {
//
//    }
//
//    /**
//     * 接收发送的消息并处理
//     *
//     * @param center 获取事件总线信息
//     */
//    @Override
//    protected void onEventComing(EventCenter center) {
//        if (center.getEventCode() == EventBusCode.EVENT_BUS_DEMO) {
//            setTitle("我接收到消息了");
//        }
//    }
}
