package com.baoxiao;

import android.app.Application;
import cn.jpush.android.api.CustomPushNotificationBuilder;
import cn.jpush.android.api.JPushInterface;

public class MyApplication extends Application {
    @Override
    public void onCreate() {    	     
        super.onCreate();
        JPushInterface.init(this);
		CustomPushNotificationBuilder builder = new CustomPushNotificationBuilder(
				this,
				R.layout.customer_notitfication_layout,
				R.id.icon, R.id.title, 
				R.id.text);
		builder.layoutIconDrawable = R.drawable.logo;
		builder.developerArg0 = "developerArg2";
		JPushInterface.setPushNotificationBuilder(2, builder);
    }
}
