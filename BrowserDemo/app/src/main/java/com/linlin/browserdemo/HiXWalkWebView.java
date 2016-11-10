package com.linlin.browserdemo;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.net.http.SslError;
import android.os.Build;
import android.os.Handler;
import android.support.v4.app.ActivityCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.widget.DatePicker;

import org.xwalk.core.XWalkJavascriptResult;
import org.xwalk.core.XWalkResourceClient;
import org.xwalk.core.XWalkSettings;
import org.xwalk.core.XWalkUIClient;
import org.xwalk.core.XWalkView;

@SuppressLint("SetJavaScriptEnabled")
public class HiXWalkWebView extends XWalkView {

	private Context mctx;
	private OnWebViewEvent onWebViewEvent;

	public HiXWalkWebView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs);
		mctx = context;
		init();
	}

	public HiXWalkWebView(Context context, AttributeSet attrs) {
		super(context, attrs);
		mctx = context;
		init();
	}

	public HiXWalkWebView(Context context) {
		super(context);
		mctx = context;
		init();
	}

	Handler webHandler = new Handler();

	/**
	 * 是否显示加戴提示
	 */
	private boolean showLoadingTips = true;

	/**
	 * 是否显示加戴提示
	 */
	public void setShowLoadingTips(boolean v) {
		showLoadingTips = v;
	}

	private void init() {

		setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);

		if (isInEditMode())
			return;

		// initLoadingAlert();
		XWalkSettings settings = getSettings();
		settings.setJavaScriptEnabled(true);
		settings.setJavaScriptCanOpenWindowsAutomatically(true);

		//启用数据库
		String dir = mctx.getApplicationContext().getDir("database", Context.MODE_PRIVATE).getPath();

		settings.setDomStorageEnabled(true) ;

		if(Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT) {
			if (!settings.getLayoutAlgorithm().equals(WebSettings.LayoutAlgorithm.SINGLE_COLUMN))
				settings.setLayoutAlgorithm(XWalkSettings.LayoutAlgorithm.SINGLE_COLUMN);
		}else {
			settings.setUseWideViewPort(true);
			settings.setLoadWithOverviewMode(true);
		}
		settings.setBuiltInZoomControls(true);
		settings.setSupportZoom(false);
		settings.setAllowFileAccess(true);

		setResourceClient(new XWalkResourceClient(this){
			@Override
			public void onLoadStarted(XWalkView view, String url) {
				if(onWebViewEvent != null){
					onWebViewEvent.closeMQ();
				}

				if (url.startsWith("http") || url.startsWith("file")) {
					if (showLoadingTips)
						showLoading();
					super.onLoadStarted(view, url);
				} else {
					Intent in = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
					getContext().startActivity(in);
				}
			}

			@Override
			public void onLoadFinished(XWalkView view, String url) {
				closeLoading();
				if(onWebViewEvent != null){
					onWebViewEvent.isShowMQ(url);
				}
				onPageInited("hiReady");
				super.onLoadFinished(view, url);
			}

			@Override
			public boolean shouldOverrideUrlLoading(XWalkView view, String url) {
				if (url.startsWith("http://wpa.qq.com")) {
					Intent in = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
					getContext().startActivity(in);
				} else {

//					view.loadUrl("http://demo2.vshop.kuaidiantong.cn/AppShop/ProductReview?productId=12");
					view.load(url, null);
				}

//				view.loadUrl(url);
				return true;			}

			@Override
			public void onReceivedLoadError(XWalkView view, int errorCode, String description, String failingUrl) {
				closeLoading();
				view.load("file:///android_asset/www/ErrorPage.htm", null);
			}

			@Override
			public void onReceivedSslError(XWalkView view, ValueCallback<Boolean> callback, SslError error) {
				super.onReceivedSslError(view, callback, error);
			}
		});

		setUIClient(new XWalkUIClient(this){
			@Override
			protected Object getBridge() {
				return super.getBridge();
			}

			@Override
			public void openFileChooser(XWalkView view, ValueCallback<Uri> uploadFile, String acceptType, String capture) {
				super.openFileChooser(view, uploadFile, acceptType, capture);
			}

			@Override
			public boolean onJsPrompt(XWalkView view, String url, String message, String defaultValue, XWalkJavascriptResult result) {
				return super.onJsPrompt(view, url, message, defaultValue, result);
			}

			@Override
			public boolean onJsConfirm(XWalkView view, String url, String message, XWalkJavascriptResult result) {

				Dialog b2 = new AlertDialog.Builder(view.getContext())
						.setTitle(R.string.app_name)
						.setMessage(message)
						.setPositiveButton(R.string.button_title_ok,
								new DialogInterface.OnClickListener() {
									public void onClick(DialogInterface dialog,
														int which) {
//										result.confirm();
									}
								})
						.setNegativeButton(R.string.button_title_cancel,
								new DialogInterface.OnClickListener() {
									public void onClick(DialogInterface dialog,
														int which) {
//										result.cancel();
									}
								}).create();

				b2.setCancelable(false);
				b2.show();
				return true;
			}

			@Override
			public boolean onJsAlert(XWalkView view, String url, String message, XWalkJavascriptResult result) {
				Dialog b2 = new AlertDialog.Builder(view.getContext())
						.setTitle(R.string.app_name)
						.setMessage(message)
						.setPositiveButton(R.string.button_title_ok,
								new DialogInterface.OnClickListener() {
									public void onClick(DialogInterface dialog,
														int which) {
//										result.confirm();
									}
								}).create();

				b2.setCancelable(false);
				b2.show();
				return true;
			}
		});

		addJavascriptInterface(this, "HiCmd");
		removeJavascriptInterface("searchBoxJavaBredge_");

	}

	public void onPageInited(String jsonStr) {
//		if (jsonStr != null && jsonStr.length() != 0)
//			this.loadUrl("javascript:onPageInited('"+jsonStr+"')");
//		else 
//			this.loadUrl("javascript:onPageInited()");
	}

	public void setOnWebViewEvent(OnWebViewEvent onWebViewEvent) {
		this.onWebViewEvent = onWebViewEvent;
	}

	public void showLoading() {
		if (onWebViewEvent != null)
			onWebViewEvent.showLoading();
	}

	public void closeLoading() {
		if (onWebViewEvent != null)
			onWebViewEvent.hiddenLoading();
	}




	public void ScanCallBack(String code) {
		this.load("javascript:scanResult('" + code + "')", null);
	}

	@JavascriptInterface
	public void webSacnCall(final String flag, final String url) {
		webHandler.post(new Runnable() {

			@Override
			public void run() {
				if (onWebViewEvent != null)
					onWebViewEvent.onCameraCall(flag, url);
			}

		});
	}

	/**
	 * 调用商品详情
	 * @param id
	 */
	@JavascriptInterface
	public void webShowProduct(final String id) {
		webHandler.post(new Runnable() {

			@Override
			public void run() {
				if (id == null || id.isEmpty())
					return;

//				Intent intent = new Intent(getContext(), ProductDetailActivity.class);
//				intent.putExtra(ProductDetailActivity.INTENT_PARAM_ID, id);
//				getContext().startActivity(intent);
			}

		});
	}

	/**
	 * 关闭当前窗体
	 */
	@JavascriptInterface
	public void webClose() {
		webHandler.post(new Runnable() {

			@Override
			public void run() {
				((Activity) mctx).finish();
			}

		});
	}


	@JavascriptInterface
	public void webGroupOrderFinishSuccess(final int status  ,final int personleft, final String groupdetial){
		webHandler.post(new Runnable() {

			@Override
			public void run() {

//				Intent intent = new Intent(getContext(), FinishGroupOrderActivity.class);
//
//				intent.putExtra(FinishGroupOrderActivity.INTENT_PARAM_STATUS, status);
//				intent.putExtra(FinishGroupOrderActivity.INTENT_PARAM_LEFT, personleft);
//				intent.putExtra(FinishGroupOrderActivity.INTENT_PARAM_PRODUCT_DETIAL, groupdetial);
//				getContext().startActivity(intent);
//				((Activity) mctx).finish();
			}

		});
	}


	public interface OnWebViewEvent {
		void onOpenPage(String Url);

		void onSetTitle(String title);

		void onCameraCall(String flag, String postUrl);

		void onLogin(String callBack, String params);

		void onRegister(String callBack, String params);

		void payOrder(String orderInfoString, String callbackFun);

		void wxPayOrder(String orderInfoString, String callbackFun);

		void onScan(String calBack);

		void showLoading();

		void hiddenLoading();

		void updateState(String params);

		void clearLogin();

		void goHome(int page);

		void share(String content);

		void onOpenFile(ValueCallback<Uri> uploadFile);

		void onOpenFile1(ValueCallback<Uri[]> uploadFile, WebChromeClient.FileChooserParams fileChooserParams);

		void onFindProductByCoupon(String couponId);

		void isShowMQ(String url);

		void closeMQ();

		void goFightGroupList();//跳转到火拼团

		void goProductList();//跳转到商品列表

		void goShake();//跳转到摇一摇

		void goOrderList();//跳转到订单列表
	}

	@JavascriptInterface
	public void webSetTitle(final String title) {
		webHandler.post(new Runnable() {
			@Override
			public void run() {
				if (onWebViewEvent != null)
					onWebViewEvent.onSetTitle(title);
			}

		});
	}

	@JavascriptInterface
	public void webFindProductByCouponId(final String couponId)
	{
		webHandler.post(new Runnable() {
			@Override
			public void run() {
				if(onWebViewEvent != null)
					onWebViewEvent.onFindProductByCoupon(couponId);
			}
		});
	}

	@JavascriptInterface
	public void webShare(final String Content) {
		webHandler.post(new Runnable() {
			@Override
			public void run() {
				if (onWebViewEvent != null)
					onWebViewEvent.share(Content);
			}

		});

	}

	@JavascriptInterface
	public void webGoHome(final int page) {
		webHandler.post(new Runnable() {
			@Override
			public void run() {
				if (onWebViewEvent != null)
					onWebViewEvent.goHome(page);
			}

		});

	}

//	@JavascriptInterface
//	public String webGetSession() {
//		return Preferences.getAccessToken();
//	}

	@JavascriptInterface
	public void webLogin(final String callBack) {
		webHandler.post(new Runnable() {
			@Override
			public void run() {
				if (onWebViewEvent != null)
					onWebViewEvent.onLogin(callBack, "");
			}

		});
	}
	@JavascriptInterface
	public void webRegister(final String callBack) {
		webHandler.post(new Runnable() {
			@Override
			public void run() {
				if (onWebViewEvent != null)
					onWebViewEvent.onRegister(callBack, "");
			}

		});

	}

	@JavascriptInterface
	public void webShowCategory() {
		webHandler.post(new Runnable() {
			@Override
			public void run() {
				if (onWebViewEvent != null)
					onWebViewEvent.goHome(1);//分类导航
			}

		});
	}

	@JavascriptInterface
	public void webShowMerberCenter() {
		webHandler.post(new Runnable() {
			@Override
			public void run() {
				if (onWebViewEvent != null)
					onWebViewEvent.goHome(3);//会员中心
			}

		});
	}

	@JavascriptInterface
	public void webShowProductList() {
		webHandler.post(new Runnable() {
			@Override
			public void run() {
				if (onWebViewEvent != null)
					onWebViewEvent.goProductList();
			}

		});
	}

	@JavascriptInterface
	public void webShowFightGroupList() {
		webHandler.post(new Runnable() {
			@Override
			public void run() {
				if (onWebViewEvent != null)
					onWebViewEvent.goFightGroupList();
			}

		});
	}

	@JavascriptInterface
	public void webShowShake() {
		webHandler.post(new Runnable() {
			@Override
			public void run() {
				if (onWebViewEvent != null)
					onWebViewEvent.goShake();
			}

		});
	}

	@JavascriptInterface
	public void webShowOrderList() {
		webHandler.post(new Runnable() {
			@Override
			public void run() {
				if (onWebViewEvent != null)
					onWebViewEvent.goOrderList();
			}

		});
	}


	@JavascriptInterface
	public void webPayOrder(final String orderInfoString, final String callbackFun) {
		webHandler.post(new Runnable() {
			@Override
			public void run() {
				if (onWebViewEvent != null)
					onWebViewEvent.payOrder(orderInfoString, callbackFun);
			}

		});

	}

	@JavascriptInterface
	public void webWxPayOrder(final String orderInfoString, final String callbackFun) {
		webHandler.post(new Runnable() {
			@Override
			public void run() {
				if (onWebViewEvent != null)
					onWebViewEvent.wxPayOrder(orderInfoString, callbackFun);
			}

		});

	}

	@JavascriptInterface
	public void webClearLogin() {
		webHandler.post(new Runnable() {
			@Override
			public void run() {
				if (onWebViewEvent != null)
					onWebViewEvent.clearLogin();
			}

		});

	}

	@JavascriptInterface
	public void webUpdateState(final String params) {
		webHandler.post(new Runnable() {
			@Override
			public void run() {
				if (onWebViewEvent != null)
					onWebViewEvent.updateState(params);
			}

		});

	}

	@JavascriptInterface
	public void webOpenPage(final String Url) {
		webHandler.post(new Runnable() {
			@Override
			public void run() {
				Log.v("ooo",Url);
				if (onWebViewEvent != null)
					onWebViewEvent.onOpenPage(Url);
			}

		});

	}

	@JavascriptInterface
	public String getJquery() {
		String res = "";
//		try {
//			InputStream in = getResources().getAssets().open(
//					"jquery-1.7.1.min.js");
//			int len = in.available();
//			byte[] buffer = new byte[len];
//			in.read(buffer);
//			res = EncodingUtils.getString(buffer, "UTF-8");
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
		return res;
	}

	@JavascriptInterface
	public String getJqueryMobile() {
		String res = "";
//		try {
//			InputStream in = getResources().getAssets().open(
//					"jquery.mobile-1.3.0.min.js");
//			int len = in.available();
//			byte[] buffer = new byte[len];
//			in.read(buffer);
//			res = EncodingUtils.getString(buffer, "UTF-8");
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
		return res;
	}

	@JavascriptInterface
	public String getJqueryCss() {
		String res = "";
//		try {
//			InputStream in = getResources().getAssets().open(
//					"jquery.mobile-1.3.0.min.css");
//			int len = in.available();
//			byte[] buffer = new byte[len];
//			in.read(buffer);
//			res = EncodingUtils.getString(buffer, "UTF-8");
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
		return res;
	}

	/**
	 *  拨打电话
	 * */
	@JavascriptInterface
	public void webCallPhoneNumber(final String phoneNumber) {
		Dialog b2 = new AlertDialog.Builder(HiXWalkWebView.this.getContext())
				.setMessage(getContext().getString(R.string.dialog_title_dial) + phoneNumber)
				.setPositiveButton(R.string.button_title_ok, new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						webHandler.post(new Runnable() {

							@Override
							public void run() {
								Intent myIntentDial = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + phoneNumber));
								if (ActivityCompat.checkSelfPermission(mctx, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
									// TODO: Consider calling
									//    ActivityCompat#requestPermissions
									// here to request the missing permissions, and then overriding
									//   public void onRequestPermissionsResult(int requestCode, String[] permissions,
									//                                          int[] grantResults)
									// to handle the case where the user grants the permission. See the documentation
									// for ActivityCompat#requestPermissions for more details.
									mctx.startActivity(myIntentDial);
									return;
								}
							}

						});

					}
				})
        .setNegativeButton(R.string.button_title_cancel, null)
        .create();
        b2.setCancelable(false);
        b2.show();	
	}
	
	/**
	 * 显示原生日期控件
	 * @param callback 回调函数名
	 * */
	@JavascriptInterface
	public void webSelectDate(final String intDate, final String callback){
		String birthStr = "1980-01-01";
		if (intDate != null && intDate.trim().length() > 0)
			birthStr = intDate;//"1980-1-1";
		int year = 1980;
		int month = 1;
		int day = 1;
		try {
			year = Integer.valueOf(birthStr.substring(0, birthStr.indexOf("-")));
			month = Integer.valueOf(birthStr.substring(birthStr.indexOf("-")+1, birthStr.lastIndexOf("-")));
			day = Integer.valueOf(birthStr.substring(birthStr.lastIndexOf("-")+1));
		} catch (Exception e) {
			year = 1980;
			month = 1;
			day = 1;
			e.printStackTrace();
		}
		Dialog dialog = new DatePickerDialog(mctx,
				new DatePickerDialog.OnDateSetListener() {
					public void onDateSet(DatePicker dp, int year,
										  int month, int dayOfMonth) {
						String date = "";
						if ((month + 1) < 10)
							date = year + "-0" + (month + 1);
						else
							date = year + "-" + (month + 1);
						if (dayOfMonth < 10)
							date = date + "-0" + dayOfMonth;
						else
							date = date + "-" + dayOfMonth;
						String retureStr = "javascript:"+callback+"('"+date+"')";
						HiXWalkWebView.this.load(retureStr, null);
//							handler.obtainMessage(10, retureStr).sendToTarget();
						
//							birth.setText(date);
//							MyWebView.this.loadUrl("javascript:SetDate"+"('{\"date\":"+"\""+date+"\"}')");
						
					}
				}, year, // 传入年份
				month-1, // 传入月份
				day // 传入天数
		);
		dialog.show();
	}	
}
