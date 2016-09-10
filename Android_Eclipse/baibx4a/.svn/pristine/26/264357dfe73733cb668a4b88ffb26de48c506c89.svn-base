package com.baoxiao.jpush;

import android.app.Activity;
import android.app.Notification;
import android.app.Notification.Builder;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import com.baoxiao.DaoYeA;
import com.baoxiao.R;

import java.lang.reflect.Field;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import cn.jpush.android.api.JPushInterface;

/**
 * 自定义接收器
 * 
 * 如果不定义这个 Receiver，则：
 * 1) 默认用户会打开主界面
 * 2) 接收不到自定义消息
 */
public class MyReceiver extends BroadcastReceiver {
	private static final String TAG = "JPush";

	@Override
	public void onReceive(Context context, Intent intent) {
        Bundle bundle = intent.getExtras();
		Log.d(TAG, "[MyReceiver] onReceive - " + intent.getAction() + ", extras: " + printBundle(bundle));
		
        if (JPushInterface.ACTION_REGISTRATION_ID.equals(intent.getAction())) {
            String regId = bundle.getString(JPushInterface.EXTRA_REGISTRATION_ID);
            Log.d(TAG, "[MyReceiver] 接收Registration Id : " + regId);
            //send the Registration Id to your server...
        } else if (JPushInterface.ACTION_MESSAGE_RECEIVED.equals(intent.getAction())) {
        	Log.d(TAG, "[MyReceiver] 接收到推送下来的自定义消息: " + bundle.getString(JPushInterface.EXTRA_MESSAGE));
        	processCustomMessage(context, bundle);
        
        } else if (JPushInterface.ACTION_NOTIFICATION_RECEIVED.equals(intent.getAction())) {
            Log.d(TAG, "[MyReceiver] 接收到推送下来的通知");
            int notifactionId = bundle.getInt(JPushInterface.EXTRA_NOTIFICATION_ID);
            Log.d(TAG, "[MyReceiver] 接收到推送下来的通知的ID: " + notifactionId);
        	
            String msg_id = bundle.getString("cn.jpush.android.MSG_ID");
            String alert = bundle.getString("cn.jpush.android.ALERT");
            String notification_content_title = bundle.getString("cn.jpush.android.NOTIFICATION_CONTENT_TITLE");
            String link = "";
			try {
				JSONObject json = new JSONObject(bundle.getString(JPushInterface.EXTRA_EXTRA));
				link = json.optString("link");
			} catch (JSONException e) {
				e.printStackTrace();
			}
            Set<String> set = new HashSet<String>();
            set.add(alert);
            set.add(notification_content_title);
            set.add(link);

            SharedPreferences message = context.getSharedPreferences("message", Activity.MODE_PRIVATE);
            message.edit().putStringSet(msg_id, set).commit();
            
        } else if (JPushInterface.ACTION_NOTIFICATION_OPENED.equals(intent.getAction())) {
            Log.d(TAG, "[MyReceiver] 用户点击打开了通知");
            
        	//打开自定义的Activity
        	Intent i = new Intent(context, TestActivity.class);
        	i.putExtras(bundle);
        	//i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        	i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP );
        	context.startActivity(i);
        	
        } else if (JPushInterface.ACTION_RICHPUSH_CALLBACK.equals(intent.getAction())) {
            Log.d(TAG, "[MyReceiver] 用户收到到RICH PUSH CALLBACK: " + bundle.getString(JPushInterface.EXTRA_EXTRA));
            //在这里根据 JPushInterface.EXTRA_EXTRA 的内容处理代码，比如打开新的Activity， 打开一个网页等..
        } else if(JPushInterface.ACTION_CONNECTION_CHANGE.equals(intent.getAction())) {
        	boolean connected = intent.getBooleanExtra(JPushInterface.EXTRA_CONNECTION_CHANGE, false);
        	Log.w(TAG, "[MyReceiver]" + intent.getAction() +" connected state change to "+connected);
        } else {
        	Log.d(TAG, "[MyReceiver] Unhandled intent - " + intent.getAction());
        }
        
//        sendBadgeNumber(context);
	}

	// 打印所有的 intent extra 数据
	private static String printBundle(Bundle bundle) {
		StringBuilder sb = new StringBuilder();
		for (String key : bundle.keySet()) {
			if (key.equals(JPushInterface.EXTRA_NOTIFICATION_ID)) {
				sb.append("\nkey:" + key + ", value:" + bundle.getInt(key));
			}else if(key.equals(JPushInterface.EXTRA_CONNECTION_CHANGE)){
				sb.append("\nkey:" + key + ", value:" + bundle.getBoolean(key));
			} else if (key.equals(JPushInterface.EXTRA_EXTRA)) {
				if (bundle.getString(JPushInterface.EXTRA_EXTRA).isEmpty()) {
					Log.i(TAG, "This message has no Extra data");
					continue;
				}
				try {
					JSONObject json = new JSONObject(bundle.getString(JPushInterface.EXTRA_EXTRA));
					Iterator<String> it =  json.keys();

					while (it.hasNext()) {
						String myKey = it.next().toString();
						sb.append("\nkey:" + key + ", value: [" +
								myKey + " - " +json.optString(myKey) + "]");
					}
				} catch (JSONException e) {
					Log.e(TAG, "Get message extra JSON error!");
				}
			} else {
				sb.append("\nkey:" + key + ", value:" + bundle.getString(key));
			}
		}
		return sb.toString();
	}
	
	private void processCustomMessage(Context context, Bundle bundle) {
//		if (MainActivity.isForeground) {
//			String message = bundle.getString(JPushInterface.EXTRA_MESSAGE);
//			String extras = bundle.getString(JPushInterface.EXTRA_EXTRA);
//			Intent msgIntent = new Intent(MainActivity.MESSAGE_RECEIVED_ACTION);
//			msgIntent.putExtra(MainActivity.KEY_MESSAGE, message);
//			if (!ExampleUtil.isEmpty(extras)) {
//				try {
//					JSONObject extraJson = new JSONObject(extras);
//					if (null != extraJson && extraJson.length() > 0) {
//						msgIntent.putExtra(MainActivity.KEY_EXTRAS, extras);
//					}
//				} catch (JSONException e) {
//				}
//			}
//		}
	}
	
	private void sendBadgeNumber(Context context) {
		String number = "35";
		if (TextUtils.isEmpty(number)) {
			number = "0";
		} else {
			int numInt = Integer.valueOf(number);
			number = String.valueOf(Math.max(0, Math.min(numInt, 99)));
		}
		String lancherActivityClassName = DaoYeA.class.getName();
		if (Build.MANUFACTURER.equalsIgnoreCase("Xiaomi")) {
			sendToXiaoMi(context, number,lancherActivityClassName);
		} else if (Build.MANUFACTURER.equalsIgnoreCase("samsung")) {
			sendToSony(context, number,lancherActivityClassName);
		} else if (Build.MANUFACTURER.toLowerCase().contains("sony")) {
			sendToSamsumg(context, number,lancherActivityClassName);
		} else {
			Toast.makeText(context, "Not Support", Toast.LENGTH_LONG).show();
		}
	}
	private void sendToXiaoMi(Context context,String number,String lancherActivityClassName) {
		NotificationManager nm = (NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);
		Notification notification = null;
		boolean isMiUIV6 = true;
		try {
			Builder builder = new Builder(context); 
			builder.setContentTitle("您有"+number+"未读消息");
			builder.setTicker("您有"+number+"未读消息");
			builder.setAutoCancel(true);
			builder.setSmallIcon(R.drawable.btn_normal);
			builder.setDefaults(Notification.DEFAULT_LIGHTS);
			notification = builder.build(); 
			Class<?> miuiNotificationClass = Class.forName("android.app.MiuiNotification");
			Object miuiNotification = miuiNotificationClass.newInstance();
			Field field = miuiNotification.getClass().getDeclaredField("messageCount");
			field.setAccessible(true);
			field.set(miuiNotification, number);// 设置信息数
//			field.set(miuiNotification, Integer.valueOf(number));// 设置信息数 
			field = notification.getClass().getField("extraNotification"); 
			field.setAccessible(true);
        field.set(notification, miuiNotification);  
        Toast.makeText(context, "Xiaomi=>isSendOk=>1", Toast.LENGTH_LONG).show();
		}catch (Exception e) {
		    e.printStackTrace();
		    //miui 6之前的版本
		    isMiUIV6 = false;
    		    Intent localIntent = new Intent("android.intent.action.APPLICATION_MESSAGE_UPDATE");
    		    localIntent.putExtra("android.intent.extra.update_application_component_name",context.getPackageName() + "/"+ lancherActivityClassName);
    		    localIntent.putExtra("android.intent.extra.update_application_message_text",number);
    		    context.sendBroadcast(localIntent);
		}
		finally
		{
          if(notification!=null && isMiUIV6 )
		   {
		       //miui6以上版本需要使用通知发送
			nm.notify(101010, notification); 
		   }
		}
	}
	private void sendToSony(Context context,String number,String lancherActivityClassName) {
		boolean isShow = true;
		if ("0".equals(number)) {
			isShow = false;
		}
		Intent localIntent = new Intent();
		localIntent.putExtra("com.sonyericsson.home.intent.extra.badge.SHOW_MESSAGE",isShow);//是否显示
		localIntent.setAction("com.sonyericsson.home.action.UPDATE_BADGE");
		localIntent.putExtra("com.sonyericsson.home.intent.extra.badge.ACTIVITY_NAME",lancherActivityClassName );//启动页
		localIntent.putExtra("com.sonyericsson.home.intent.extra.badge.MESSAGE", number);//数字
		localIntent.putExtra("com.sonyericsson.home.intent.extra.badge.PACKAGE_NAME",context.getPackageName());//包名
		context.sendBroadcast(localIntent);

		Toast.makeText(context, "Sony," + "isSendOk", Toast.LENGTH_LONG).show();
	}

	private void sendToSamsumg(Context context,String number,String lancherActivityClassName) 
	{
		Intent localIntent = new Intent("android.intent.action.BADGE_COUNT_UPDATE");
		localIntent.putExtra("badge_count", number);//数字
		localIntent.putExtra("badge_count_package_name", context.getPackageName());//包名
		localIntent.putExtra("badge_count_class_name",lancherActivityClassName); //启动页
		context.sendBroadcast(localIntent);
		Toast.makeText(context, "Samsumg," + "isSendOk", Toast.LENGTH_LONG).show();
	}
}
