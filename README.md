# YzsLib
       
        远程依赖方法在项目根目录下的build.gradle添加如下
        allprojects {
        		repositories {
        			...
        			<!--添加的话，就这一句-->
        			maven { url "https://jitpack.io" }
        		}
        	}


        在引用项目处添加
        dependencies {
        	        compile 'com.github.a243981326:YzsLib:0.0.2'
        	}
        
        
        目前最新版本为0.0.2，就是最新的release版本，之后引用修改版本号就可以 
        YzsLib开源交流群：331973212
        
        一个共享的资源库，本人会逐渐完善，让他越来越完美
        * 16.10.23 更新了NoticeView，公告条控件，支持绑定activity与fragment生命周期
        * 16.10.24 更新了base类
        * 16.11.3  更新控件nicespiner（美观又动画的自定义spinner），toggleButton（仿苹果滑动按钮）
        * 16.11.7  更新BaseActivity，支持4.4版本以上沉浸式
                   更新控件AutoSwipeRefreshLayout    自动刷新SwipeRefreshLayout
                   更新控件MultiImageView  显示1 ~ N 张图片的View仿微信朋友圈
                   更新控件ColorFilterImageView  实现图像根据按下抬起动作变化颜色
        * 16.11.8  更新控件AdaptiveWebView，实现控件支持高度自适应，动态测量出网页的高度
        * 16.11.15 更新控件QuantityView（购物车加减控件），更新工具类ArithmeticUtils精度运算工具类
                   更新style——activity，配合YzsBase使用实现沉浸式
        * 16.11.20 更新工具类，更新fragment替代库，增加glide加载辅助库，删除YzsBaseFragmentActivity，
                   添加6.0权限申请工具，添加界面loading效果到baseFragment与activity中，
                   添加recyclerview仿listview与gridview分割线，更新log工具，
                   封装eventbus到activity与fragment中
        * 16.11.21 增加YzsBaseWebActivity,更新整合Demo展示，增加StateButton——不用写selector的button