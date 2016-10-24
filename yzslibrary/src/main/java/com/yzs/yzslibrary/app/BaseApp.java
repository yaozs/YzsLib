package com.yzs.yzslibrary.app;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.res.Resources;

import java.util.Stack;

/**
 * Author: 姚智胜
 * Version: V1.0版本
 * Description:
 * Date: 2016/10/24
 */
public abstract class BaseApp extends Application {
    
    /** 数据常量     */
    public static final class Constant {
        /** 用户信息 */
        public static String userId = "";
        public static String token = "";
        /*获取当前系统的android版本号*/
        public static String versionCode = "1.0";
    }
    
    protected static Context applicationContext;
    protected static BaseApp instance;

    @Override
    public void onCreate() {  
    	applicationContext = getApplicationContext();  
    	instance = this;
    	onInitCreate();
    }  
      
    public static Context getAppContext(){
        return applicationContext;  
    }  
    
	public static BaseApp getInstance() {
		return instance;
	}
    
    public static Resources getAppResources(){
        return getAppResources();
    }
    
    /** 全局退出     */
    private static Stack<Activity> activityStack = new Stack<Activity>();
    
    // 弹出当前activity并销毁
    public static void popActivity(Activity activity) {
        if (null != activity) {
            activity.finish();
            if (null != activityStack) {
                activityStack.remove(activity);
            }
            activity = null;
        }
    }

    /**
     * 将当前Activity推入栈中
     * @param activity
     */
    public static void pushActivity(Activity activity) {
        if (null != activityStack) {
            activityStack.add(activity);
        }
    }

    /**
     * 退出栈中所有Activity
     */
    public static void clearAllActivity() {
        while (null != activityStack && !activityStack.isEmpty()) {
            Activity activity = activityStack.pop();
            if (null != activity) {
                activity.finish();
            }
        }
    }
    
    protected abstract void onInitCreate();
}
