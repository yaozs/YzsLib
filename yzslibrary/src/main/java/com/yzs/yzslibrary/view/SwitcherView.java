package com.yzs.yzslibrary.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextSwitcher;
import android.widget.TextView;
import android.widget.ViewSwitcher;

import com.yzs.yzslibrary.R;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Author 姚智胜
 * Version V1.0版本
 * Description: 垂直滚动的广告栏
 * Date: 2016/10/20
 */
public class SwitcherView extends TextSwitcher implements ViewSwitcher.ViewFactory, View.OnTouchListener {

    private static final String TAG = "SwitcherView";

    private Handler handler = new Handler();
    private Timer timer;   //计时器
    /**
     * 数据源
     */
    private ArrayList<String> dataSource = new ArrayList<>();
    /**
     * 滚动的位置
     */
    private int currentIndex = 0;
    /**
     * 文字大小
     */
    private int textSize = 0;
    /**
     * 默认文字大小
     */
    private static final int defaultTextSize = 16;
    /**
     * 默认颜色
     */
    private int textColor = 0xFF000000;
    /**
     * 时间周期
     */
    private int timePeriod = 3000;
    /**
     * 是否可以执行更换滚动动作
     */
    private boolean flag = true;
    /**
     * 点击事件
     */
    private OnItemClick onItemClick = null;

    private TextView tView;
    /**
     * 暂停时间
     */
    private int time;

    public SwitcherView(Context context) {
        this(context, null);
    }

    public SwitcherView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.SwitcherView);
        //获取xml文件中属性，如果没有用默认属性
        textColor = ta.getColor(R.styleable.SwitcherView_switcherTextColor, textColor);
        timePeriod = ta.getInt(R.styleable.SwitcherView_switcherRollingTime, timePeriod);
        textSize = ta.getDimensionPixelSize(R.styleable.SwitcherView_switcherTextSize, sp2px(defaultTextSize));
        Log.i("----", textSize + "");
        textSize = px2sp(textSize);
        Log.i("----", textSize + "");
        ta.recycle();

        setOnTouchListener(this);
    }

    @Override
    public View makeView() {
        tView = new TextView(getContext());
        tView.setLayoutParams(new FrameLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        tView.setTextSize(TypedValue.COMPLEX_UNIT_SP, textSize);
        tView.setTextColor(textColor);
        tView.setSingleLine();
        tView.setGravity(Gravity.CENTER_VERTICAL);
        tView.setEllipsize(TextUtils.TruncateAt.END);
        return tView;
    }

    /**
     * 对数据进行赋值
     *
     * @param dataSource string数据集合
     */
    public void setResource(ArrayList<String> dataSource) {
        this.dataSource = dataSource;
    }

    TimerTask timerTask;

    private void updateTextSwitcher() {
        if (dataSource != null && dataSource.size() > 0) {
            String text = dataSource.get(currentIndex++);
            Log.e("text", text);
//            this.setText(dataSource.get(currentIndex++));
            this.setText(text);
            if (currentIndex > dataSource.size() - 1) {
                currentIndex = 0;
            }
        }
    }

    /**
     * 转动方法
     */
    public void startRolling() {
        if (timer == null) {
            this.setFactory(this);
            this.setInAnimation(getContext(), R.anim.m_switcher_vertical_in);
            this.setOutAnimation(getContext(), R.anim.m_switcher_vertical_out);
            timer = new Timer();
            timerTask = new TimerTask() {
                @Override
                public void run() {
                    Message message = new Message();
                    message.what = 1;
                    handler_run.sendMessage(message);
                }
            };
            timer.schedule(timerTask, 1000, time);
        }
    }

    /**
     * 获取指定item内容，根据需求进行封装修改
     *
     * @return 指定item内容
     */
    public String getCurrentItem() {
        if (dataSource != null && dataSource.size() > 0) {
            return dataSource.get(getCurrentIndex());
        } else {
            return "";
        }
    }

    /**
     * 获取点击序列号
     *
     * @return 点击序列号
     */
    public int getCurrentIndex() {
        int index = currentIndex - 1;
        if (index < 0) {
            index = dataSource.size() - 1;
        }
        return index;
    }

    /**
     * 停止销毁控件
     */
    public void onDestroy() {
        handler.removeCallbacksAndMessages(null);
        handler = null;
        if (timer != null) {
            timer.cancel();
            timerTask.cancel();
            timer = null;
            timerTask = null;
        }
        if (dataSource != null && dataSource.size() > 0) {
            dataSource.clear();
            dataSource = null;
        }
        tView = null;
    }

    //初始点击
    float startX, startY;
    //移动长度
    float x, y;
    //是否执行点击事件
    boolean go = true;
    //开始时间
    long startTime;

    @Override
    public boolean onTouch(View v, MotionEvent event) {

        //判断移动坐标

        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            //点击没有赋值时的反应，防止崩溃
            go = true;
            startX = event.getX();
            startY = event.getY();
            startTime = System.currentTimeMillis();
            flag = false;
        } else if (event.getAction() == MotionEvent.ACTION_MOVE) {
            x = event.getX() - startX;
            y = event.getY() - startY;
            long moveTime = (System.currentTimeMillis() - startTime);//移动时间毫秒
            Log.e("moveTime", moveTime + "");
            //移动时间小于500毫秒，横纵移动小于50执行点击事件
            if (moveTime > 500 || x > 50 || y > 50) {
                go = false;
            }
        } else if (event.getAction() == MotionEvent.ACTION_UP) {
            if (getCurrentIndex() != -1 && go) {
                onItemClick.Click(getCurrentIndex());
            }
            flag = true;
        }
        //true能够执行整个移动过程，false只能执行到一个
        return true;

    }

    public int sp2px(int spVal) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, spVal, getResources().getDisplayMetrics());
    }

    public int dp2px(int dpVal) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dpVal, getResources().getDisplayMetrics());

    }

    public int px2sp(float pxValue) {
        final float fontScale = getResources().getDisplayMetrics().scaledDensity;
        return (int) (pxValue / fontScale + 0.5f);
    }


    private Handler handler_run = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (flag) {
                updateTextSwitcher();
                startRolling();
            }
            // 要做的事情
            super.handleMessage(msg);
        }
    };


    /**
     * @param time 持续时间
     */
    public void start(int time) {
        this.time = time;
        startRolling();

    }

    /**
     * 重新启动
     */
    public void onResume() {
        if (timer != null) {
            timer.cancel();
        }
        timer = new Timer();
        timerTask = new TimerTask() {
            @Override
            public void run() {
                Message message = new Message();
                message.what = 1;
                handler_run.sendMessage(message);
            }
        };
        timer.schedule(timerTask, 1000, time);
    }

    public void onStop() {
        if (timer != null) {
            timer.cancel();
        }
    }

    /**
     * 定义点击接口
     */
    public interface OnItemClick {
        public void Click(int index);
    }

    public void setOnItemClick(OnItemClick onItemClick) {
        this.onItemClick = onItemClick;
    }
}