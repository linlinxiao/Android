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
	 * 分享方法
	 * 
	 * @param url
	 *            所分享内容的地址
	 * @param context上下文
	 */
	public static void startShare(String url, String content, Context context)
	{
		mController = UMServiceFactory.getUMSocialService("com.umeng.share");
		// 设置分享内容
		mController.setShareContent(content);
		// 设置分享图片, 参数2为图片的url地址 "http://www.baidu.com/img/bdlogo.png"
		mController.setShareMedia(new UMImage(context, R.drawable.bbx_icon));
		// 添加QQ平台
		UMQQSsoHandler qqSsoHandler = new UMQQSsoHandler((Activity) context,
				Define.txAppID, Define.txAppKey);
		qqSsoHandler.setTargetUrl(url);
		qqSsoHandler.addToSocialSDK();
		// 添加QQ空间平台
		QZoneSsoHandler qZoneSsoHandler = new QZoneSsoHandler(
				(Activity) context, Define.txAppID, Define.txAppKey);
		qZoneSsoHandler.setTargetUrl(url);
		qZoneSsoHandler.addToSocialSDK();
		// 添加微信平台
		UMWXHandler wxHandler = new UMWXHandler(context, Define.wxAppID,
				Define.wxAppSecret);
		wxHandler.setTargetUrl(url);
		wxHandler.addToSocialSDK();
		// 支持微信朋友圈
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
	 * @param image 分享的图片
	 * @param url 分享的链接地址
	 * @param content 分享副标题
	 * @param context 上下文
	 * @param wxtitle 分享标题
	 */
	public static void startShareWithImage(UMImage image,String url,String content,
			Context context,String wxtitle){
		mController = UMServiceFactory.getUMSocialService("com.umeng.share");
		// 设置分享内容
		mController.setShareContent(content);
		mController.setEntityName("平安百宝箱");
		// 设置分享图片, 参数2为图片的url地址 "http://www.baidu.com/img/bdlogo.png"
		mController.setShareMedia(image);
		// 添加QQ平台
		UMQQSsoHandler qqSsoHandler = new UMQQSsoHandler((Activity) context,
				Define.txAppID, Define.txAppKey);
		qqSsoHandler.setTargetUrl(url);
		qqSsoHandler.addToSocialSDK();
		// 添加QQ空间平台
		QZoneSsoHandler qZoneSsoHandler = new QZoneSsoHandler(
				(Activity) context, Define.txAppID, Define.txAppKey);
		qZoneSsoHandler.setTargetUrl(url);
		qZoneSsoHandler.addToSocialSDK();
		// 添加微信平台
		UMWXHandler wxHandler = new UMWXHandler(context, Define.wxAppID,
				Define.wxAppSecret);
		wxHandler.setTargetUrl(url);
		wxHandler.setTitle(wxtitle);
		wxHandler.addToSocialSDK();
		// 支持微信朋友圈
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
