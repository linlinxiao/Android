package com.baoxiao.util;

import android.app.Activity;
import android.content.Context;

import com.baoxiao.R;
import com.umeng.socialize.controller.UMServiceFactory;
import com.umeng.socialize.controller.UMSocialService;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.sso.QZoneSsoHandler;
import com.umeng.socialize.sso.SinaSsoHandler;
import com.umeng.socialize.sso.SmsHandler;
import com.umeng.socialize.sso.UMQQSsoHandler;
import com.umeng.socialize.weixin.controller.UMWXHandler;

public class ShareService
{
	private static UMSocialService mController;

	/**
	 * ������
	 * 
	 * @param url
	 *            ���������ݵĵ�ַ
	 * @param context������
	 */
	public static void startShare(String url, String content, Context context)
	{
		mController = UMServiceFactory.getUMSocialService("com.umeng.share");
		// ���÷�������
		mController.setShareContent(content);
		// ���÷���ͼƬ, ����2ΪͼƬ��url��ַ "http://www.baidu.com/img/bdlogo.png"
		mController.setShareMedia(new UMImage(context, R.drawable.bbx_icon));
		// ���QQƽ̨
		UMQQSsoHandler qqSsoHandler = new UMQQSsoHandler((Activity) context,
				Define.txAppID, Define.txAppKey);
		qqSsoHandler.setTargetUrl(url);
		qqSsoHandler.addToSocialSDK();
		// ���QQ�ռ�ƽ̨
		QZoneSsoHandler qZoneSsoHandler = new QZoneSsoHandler(
				(Activity) context, Define.txAppID, Define.txAppKey);
		qZoneSsoHandler.setTargetUrl(url);
		qZoneSsoHandler.addToSocialSDK();
		// ���΢��ƽ̨
		UMWXHandler wxHandler = new UMWXHandler(context, Define.wxAppID,
				Define.wxAppSecret);
		wxHandler.setTargetUrl(url);
		wxHandler.addToSocialSDK();
		// ֧��΢������Ȧ
		UMWXHandler wxCircleHandler = new UMWXHandler(context, Define.wxAppID,
				Define.wxAppSecret);
		wxCircleHandler.setTargetUrl(url);
		wxCircleHandler.setToCircle(true);
		wxCircleHandler.addToSocialSDK();

		SmsHandler smsHandler = new SmsHandler();
		smsHandler.addToSocialSDK();
		mController.getConfig().setSsoHandler(new SinaSsoHandler());
		mController.openShare((Activity) context, false);
	}
	

	/**
	 * @param image �����ͼƬ
	 * @param url ��������ӵ�ַ
	 * @param content ��������
	 * @param context ������
	 * @param wxtitle �������
	 */
	public static void startShareWithImage(UMImage image,String url,String content,
			Context context,String wxtitle){
		mController = UMServiceFactory.getUMSocialService("com.umeng.share");
		// ���÷�������
		mController.setShareContent(content);
		mController.setEntityName("ƽ���ٱ���");
		// ���÷���ͼƬ, ����2ΪͼƬ��url��ַ "http://www.baidu.com/img/bdlogo.png"
		mController.setShareMedia(image);
		// ���QQƽ̨
		UMQQSsoHandler qqSsoHandler = new UMQQSsoHandler((Activity) context,
				Define.txAppID, Define.txAppKey);
		qqSsoHandler.setTargetUrl(url);
		qqSsoHandler.addToSocialSDK();
		// ���QQ�ռ�ƽ̨
		QZoneSsoHandler qZoneSsoHandler = new QZoneSsoHandler(
				(Activity) context, Define.txAppID, Define.txAppKey);
		qZoneSsoHandler.setTargetUrl(url);
		qZoneSsoHandler.addToSocialSDK();
		// ���΢��ƽ̨
		UMWXHandler wxHandler = new UMWXHandler(context, Define.wxAppID,
				Define.wxAppSecret);
		wxHandler.setTargetUrl(url);
		wxHandler.setTitle(wxtitle);
		wxHandler.addToSocialSDK();
		// ֧��΢������Ȧ
		UMWXHandler wxCircleHandler = new UMWXHandler(context, Define.wxAppID,
				Define.wxAppSecret);
		wxCircleHandler.setTargetUrl(url);
		wxCircleHandler.setToCircle(true);
		wxCircleHandler.addToSocialSDK();

		SmsHandler smsHandler = new SmsHandler();
		smsHandler.addToSocialSDK();
		mController.getConfig().setSsoHandler(new SinaSsoHandler());
		mController.openShare((Activity) context, false);
	}
}
