package com.linlin.productdetaildemo;

import android.app.Application;
import android.content.res.Configuration;

/**
 * Created by linlin on 9/18/16.
 * 单例，每个程序都只有一个Application实例，如果要使用自定义的Application类，可以在manifest文件中进行注册
 */
public class MyApplication extends Application {

    private String appName = "移动云商城商品详情";

    //应用程序被创建的时候被调用
    @Override
    public void onCreate() {
        super.onCreate();
    }

    //内存紧张的时候调用，可以在这个方法里面做一些清理操作，以在内存不足的时候释放更多的资源来支持程序继续运行
    @Override
    public void onLowMemory() {
        super.onLowMemory();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    //当程序被用户正常终止的时候调用，其他方式程序终止时这个方法不一定会被调用，所以一些清理操作不应该放在这个方法
    @Override
    public void onTerminate() {
        super.onTerminate();
    }

    public String getAppName() {
        return appName;
    }
}
