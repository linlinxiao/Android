package com.baoxiao.activity.productshow.zhiyuerensheng;

import java.util.ArrayList;
import java.util.HashMap;

import com.baoxiao.R;
import com.baoxiao.activity.productshow.ImgAnimationA;
import com.baoxiao.model.HongLi;
import com.baoxiao.provider.DBDAO;
import com.baoxiao.service.productshow.ZhiYueRenShengService;
import com.baoxiao.util.Arithmetic4Double;
import com.baoxiao.util.Define;
import com.baoxiao.util.DensityUtil;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.view.WindowManager.LayoutParams;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ZhiYueRenShengShowA extends Activity implements OnClickListener {
	private Animation xiangYouYiDong, xiangZuoYiDong, yiDongAndJianBian,
			jianBian, suoFang, suoFangBianDa, xuanZhuan, xiangXiaYiDong,
			xiangShangYiDong, suoFangAndXuanZhuan, suoFangAndYiDong;

	// 主界面及其他四个界面
	private LinearLayout main;
	private LinearLayout leftLinear;
	private LinearLayout rightLinear;
	
	// 左边的圆形中的数据控件
	private TextView floatText1, floatText2, floatText3,bAndY;
      
	// 悬浮框
	private WindowManager wm = null;
	private WindowManager.LayoutParams wmParams = null;
	// 下部悬浮框
	private TextView productZeRen, zhiChanGuanLi, LiCaiYouShi, zeRenMianChu,
			wenTiJieDa, fuJiaBaoXian, canBaoTiShi,wanNengTouZi;

	// 第一部分
	private LinearLayout jianKangZhongJiJinLinear;
	private TextView jianKangTitle, jianKangtext1, jianKangtext2,
			yiWaiShenGuBaoZhangJin, jiBingShenGuBaoZhangJin;

	// 第二部分
	private LinearLayout fengHongLinear, huShenFuFenHongLinear1;
	private TextView huShenFuFenHongTitle, huShenFuFenHongText1, jKZhongJiJin,
			huShenFuFenHongText2;

	private LinearLayout zhongShenFenHongLinear, hongLiBaoXianJinELayout1_2,
			hongLiBaoXianJinELayout2_2, hongLiBaoXianJinELayout3_2,
			hongLiBaoXianJinELayout4_2, hongLiBaoXianJinELayout5_2,
			hongLiBaoXianJinE_2;
	private TextView hongLiBaoXianJinE1_2, hongLiBaoXianJinE2_2,
			hongLiBaoXianJinE3_2, hongLiBaoXianJinE4_2, hongLiBaoXianJinE5_2;

	private TextView levelHongLiTV;

	// 第三部分
	private LinearLayout yiWaiShenGuLinear, jiBingShenGuLinear;
	private TextView baoZhangTitle, baoZhangtext1, baoZhangtext2;

	// 第四部分
	private LinearLayout yiWaiYiLiaoLinear, jiBingShangCanLinear;
	private TextView yiWaiTitle, yiWaitext1, yiWaitext2,
	yiWaiYiShangHaiZhangJin,yiWaiYiLiaoBaoZhangJin;

	// 数据
	private String sex;
	private String age;
	private String period;
	// 等级
	private String level;
	// 保额
	private String baoE;
	private String zhongJiBaoE;
	private String yiLiaoBaoE;
	private String shangHaiBaoE;
	// 保费
	private String zhuBaoFeiD;
	// 豁免
	private String huoMian;
	private View view = null;
	private View topView = null;
	private View bottomView = null;
	private HashMap<String, HongLi> zxFeiLv,zjFeiLv,hmFeiLv;
	private ArrayList<HongLi> hongLiMap,wxxsFeiLv;

	// // 值
	private AlertDialog dialog;

	// 计算保单现金价值的相关变量
	private HongLi hongli;
	private ZhiYueRenShengService zyrs;
	String baoDanXianJin;
	Double zhuXiangBaoZhang;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// 设置横屏，全屏，无标题
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.ps_zhiyuerenshengshow);

		// 初始化控件
		initView();
		// 获取界面传递过来的值
		getIntentValue();
		// 初始化动画效果
		initAnim();
		// 初始化悬浮按钮
		initFloatView();
		// 设置HongLi对象的值
		setHongLiValue();
		// 设置界面显示数据
		setDataValue();
		// 获取数据
//		new Thread(DataValueRunnable).start();
		hongLiMap = setData();
		// 红利
		setHongLiBXJEData();
	}

	private void initView() {
		// 主界面初始化
		main = (LinearLayout) findViewById(R.id.main);
		main.setOnClickListener(this);
		leftLinear = (LinearLayout) findViewById(R.id.leftLinear);
		rightLinear = (LinearLayout) findViewById(R.id.RightLinear);
		leftLinear.setOnClickListener(this);
		rightLinear.setOnClickListener(this);
		
		// 左边的圆形中的数据控件
		floatText1 = (TextView) findViewById(R.id.floatText1);
		floatText2 = (TextView) findViewById(R.id.floatText2);
		floatText3 = (TextView) findViewById(R.id.floatText3);

		// 控件的初始化和点击监听
		// 第一部分初始化 baoDanLeiJiTitle
		jianKangZhongJiJinLinear = (LinearLayout) findViewById(R.id.jianKangZhongJiJinLinear);

		jianKangTitle = (TextView) findViewById(R.id.huShenFuJianKangTitle);
		jianKangtext1 = (TextView) findViewById(R.id.huShenFuJianKangText1);
		jianKangtext2 = (TextView) findViewById(R.id.huShenFuJianKangText2);
		yiWaiShenGuBaoZhangJin = (TextView) findViewById(R.id.yiWaiShenGuBaoZhangJin);
		jiBingShenGuBaoZhangJin = (TextView) findViewById(R.id.jiBingShenGuBaoZhangJin);

		jianKangZhongJiJinLinear.setVisibility(View.INVISIBLE);
		jianKangTitle.setVisibility(View.INVISIBLE);
		jianKangtext1.setVisibility(View.INVISIBLE);
		jianKangtext2.setVisibility(View.INVISIBLE);

		// 第二部分初始化
		fengHongLinear = (LinearLayout) findViewById(R.id.fengHongLinear);
		huShenFuFenHongLinear1 = (LinearLayout) findViewById(R.id.huShenFuFenHongLinear1);
		huShenFuFenHongText2 = (TextView) findViewById(R.id.huShenFuFenHongText2);
		huShenFuFenHongTitle = (TextView) findViewById(R.id.huShenFuFenHongTitle);
		huShenFuFenHongText1 = (TextView) findViewById(R.id.huShenFuFenHongText1);
		huShenFuFenHongText2.setVisibility(View.INVISIBLE);
		huShenFuFenHongTitle.setVisibility(View.INVISIBLE);
		huShenFuFenHongText1.setVisibility(View.INVISIBLE);

		jKZhongJiJin = (TextView) findViewById(R.id.jKZhongJiJin);

		zhongShenFenHongLinear = (LinearLayout) findViewById(R.id.zhongShenFenHongLinear);
		// 以便点击事件
		hongLiBaoXianJinE_2 = (LinearLayout) findViewById(R.id.hongLiBaoXianJinE_2);
		hongLiBaoXianJinELayout1_2 = (LinearLayout) findViewById(R.id.hongLiBaoXianJinELayout1_2);
		hongLiBaoXianJinELayout2_2 = (LinearLayout) findViewById(R.id.hongLiBaoXianJinELayout2_2);
		hongLiBaoXianJinELayout3_2 = (LinearLayout) findViewById(R.id.hongLiBaoXianJinELayout3_2);
		hongLiBaoXianJinELayout4_2 = (LinearLayout) findViewById(R.id.hongLiBaoXianJinELayout4_2);
		hongLiBaoXianJinELayout5_2 = (LinearLayout) findViewById(R.id.hongLiBaoXianJinELayout5_2);
		hongLiBaoXianJinE_2.setVisibility(View.INVISIBLE);
		hongLiBaoXianJinELayout1_2.setVisibility(View.INVISIBLE);
		hongLiBaoXianJinELayout2_2.setVisibility(View.INVISIBLE);
		hongLiBaoXianJinELayout3_2.setVisibility(View.INVISIBLE);
		hongLiBaoXianJinELayout4_2.setVisibility(View.INVISIBLE);
		hongLiBaoXianJinELayout5_2.setVisibility(View.INVISIBLE);

		hongLiBaoXianJinE1_2 = (TextView) findViewById(R.id.hongLiBaoXianJinE1_2);
		hongLiBaoXianJinE2_2 = (TextView) findViewById(R.id.hongLiBaoXianJinE2_2);
		hongLiBaoXianJinE3_2 = (TextView) findViewById(R.id.hongLiBaoXianJinE3_2);
		hongLiBaoXianJinE4_2 = (TextView) findViewById(R.id.hongLiBaoXianJinE4_2);
		hongLiBaoXianJinE5_2 = (TextView) findViewById(R.id.hongLiBaoXianJinE5_2);

		zhongShenFenHongLinear.setOnClickListener(this);
		hongLiBaoXianJinE_2.setOnClickListener(this);
		hongLiBaoXianJinELayout1_2.setOnClickListener(this);
		hongLiBaoXianJinELayout2_2.setOnClickListener(this);
		hongLiBaoXianJinELayout3_2.setOnClickListener(this);
		hongLiBaoXianJinELayout4_2.setOnClickListener(this);
		hongLiBaoXianJinELayout5_2.setOnClickListener(this);

		levelHongLiTV = (TextView) this.findViewById(R.id.levelHongLi);

		// 第三部分初始化，并设置为不可见
		yiWaiShenGuLinear = (LinearLayout) findViewById(R.id.huShenFuYiWaiBaoZhangLinear);
		jiBingShenGuLinear = (LinearLayout) findViewById(R.id.huShenFuJiBingBaoZhangLinear);
		baoZhangTitle = (TextView) findViewById(R.id.huShenFuBaoZhangTitle);
		baoZhangtext1 = (TextView) findViewById(R.id.huShenFuBaoZhangText1);
		baoZhangtext2 = (TextView) findViewById(R.id.huShenFuBaoZhangText2);
		yiWaiShenGuLinear.setVisibility(View.INVISIBLE);
		jiBingShenGuLinear.setVisibility(View.INVISIBLE);
		baoZhangTitle.setVisibility(View.INVISIBLE);
		baoZhangtext1.setVisibility(View.INVISIBLE);
		baoZhangtext2.setVisibility(View.INVISIBLE);

		// 第四部分初始化，并设置为不可见
		yiWaiTitle = (TextView) findViewById(R.id.huShenFuYiWaiTitle);
		yiWaitext1 = (TextView) findViewById(R.id.huShenFuYiWaiText1);
		yiWaitext2 = (TextView) findViewById(R.id.huShenFuYiWaiText2);
		yiWaiYiShangHaiZhangJin = (TextView) findViewById(R.id.yiWaiYiShangHaiZhangJin);
		yiWaiYiLiaoBaoZhangJin = (TextView) findViewById(R.id.yiWaiYiLiaoBaoZhangJin);
		yiWaiYiLiaoLinear = (LinearLayout) findViewById(R.id.yiWaiYiLiaoLinear);
		jiBingShangCanLinear = (LinearLayout) findViewById(R.id.jiBingShangCanLinear);
		yiWaiTitle.setVisibility(View.INVISIBLE);
		yiWaitext1.setVisibility(View.INVISIBLE);
		yiWaitext2.setVisibility(View.INVISIBLE);
		yiWaiYiLiaoLinear.setVisibility(View.INVISIBLE);
		jiBingShangCanLinear.setVisibility(View.INVISIBLE);

	}

	private void getIntentValue() {
		sex = this.getIntent().getStringExtra("sex");
		age = this.getIntent().getStringExtra("age");
		period = this.getIntent().getStringExtra("period");
		level = this.getIntent().getStringExtra("level");
		baoE = this.getIntent().getStringExtra("baoE");
		zhongJiBaoE = this.getIntent().getStringExtra("zhongJiBaoE");
		yiLiaoBaoE = this.getIntent().getStringExtra("yiWaiYiLiBaoE");
		shangHaiBaoE = getIntent().getStringExtra("yiWaiShangHaiBaoE");
		zhuBaoFeiD = this.getIntent().getStringExtra("zhuBaoF");
		huoMian = this.getIntent().getStringExtra("huoMian");
	}
	
	private void initAnim() {
		// 动画的初始化
		// 位移动画
		xiangYouYiDong = AnimationUtils.loadAnimation(this,
				R.anim.ps_shouhuxshow_anim_translateleftin);
		xiangZuoYiDong = AnimationUtils.loadAnimation(this,
				R.anim.ps_shouhuxshow_anim_translaterightin);
		yiDongAndJianBian = AnimationUtils.loadAnimation(
				getApplicationContext(),
				R.anim.ps_shouhuxshow_anim_translateleftin);
		// 渐变动画
		jianBian = AnimationUtils.loadAnimation(getApplicationContext(),
				R.anim.ps_shouhuxshow_anim_alpha);
		// 缩放动画
		suoFang = AnimationUtils.loadAnimation(getApplicationContext(),
				R.anim.ps_shouhuxshow_anim_scale);
		// 缩放动画,由小变大
		suoFangBianDa = AnimationUtils.loadAnimation(getApplicationContext(),
				R.anim.ps_shouhuxshow_anim_scale_big);
		// 旋转动画
		xuanZhuan = AnimationUtils.loadAnimation(getApplicationContext(),
				R.anim.ps_shouhuxshow_anim_rotate);
		// 向下移动
		xiangXiaYiDong = AnimationUtils.loadAnimation(getApplicationContext(),
				R.anim.ps_shouhuxshow_anim_xxtranslate);
		// 向上移动
		xiangShangYiDong = AnimationUtils
				.loadAnimation(getApplicationContext(),
						R.anim.ps_shouhuxshow_anim_xstranslate);
		// 缩放和旋转
		suoFangAndXuanZhuan = AnimationUtils.loadAnimation(
				getApplicationContext(),
				R.anim.ps_shouhuxshow_anim_scale_rotate);
		// 缩放和位移
		suoFangAndYiDong = AnimationUtils.loadAnimation(
				getApplicationContext(),
				R.anim.ps_shouhuxshow_anim_scale_translate);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		// 左右屏幕点击事件
		case R.id.RightLinear:
			next();
			if (view != null) {
				view.setVisibility(View.VISIBLE);
			}
			break;

		case R.id.leftLinear:
			previous();
			if (view != null) {
				view.setVisibility(View.VISIBLE);
			}
			break;

		case R.id.productZeRen:
			Intent intent2 = new Intent(this, ImgAnimationA.class);
			Bundle b2 = new Bundle();
			b2.putInt("maxPageCount", 6);
			b2.putString("url", "product/zhiyuerensheng/yanshi/chanpinzeren");
			b2.putString("format", "gif");
			b2.putString("screenFlag", "yanshi");
			b2.putString("tiaokuan", "zhiyuerensheng");
			intent2.putExtras(b2);
			startActivity(intent2);
			break;
		case R.id.zeRenMianChu:
//			Intent intent8 = new Intent(this, ImgAnimationA.class);
//			Bundle b8 = new Bundle();
//			b8.putInt("maxPageCount", 10);
//			b8.putString("url", "product/hushenfu/yanshi/zerenmianchu");
//			b8.putString("format", "gif");
//			b8.putString("screenFlag", "yanshi");
//			intent8.putExtras(b8);
//			startActivity(intent8);
			break;
		// 在该项，改成了万能特色
		case R.id.LiCaiYouShi:
			Intent intent4 = new Intent(this, ImgAnimationA.class);
			Bundle b4 = new Bundle();
			b4.putInt("maxPageCount", 5);
			b4.putString("url", "product/zhiyuerensheng/yanshi/wannengtese");
			b4.putString("format", "gif");
			b4.putString("screenFlag", "yanshi");
			intent4.putExtras(b4);
			startActivity(intent4);
			break;
		//历史利率
		case R.id.wenTiJieDa:
			break;
		case R.id.zhiChanGuanLi:
			Intent intent3 = new Intent(this, ImgAnimationA.class);
			Bundle b3 = new Bundle();
			b3.putInt("maxPageCount", 19);
			b3.putString("url", "product/shouhux/yanshi/zichanguanli");
			b3.putString("format", "gif");
			b3.putString("screenFlag", "jianjie");
			intent3.putExtras(b3);
			startActivity(intent3);
			break;
		case R.id.wanNengTouZi:
			Intent intent5 = new Intent(this, ImgAnimationA.class);
			Bundle b5 = new Bundle();
			b5.putInt("maxPageCount", 4);
			b5.putString("url", "product/zhiyuerensheng/yanshi/wannenghetouzi");
			b5.putString("format", "gif");
			b5.putString("screenFlag", "yanshi");
			intent5.putExtras(b5);
			startActivity(intent5);
			
			break;
			
//		case R.id.fuJiaBaoXian:
//			// Intent intent6 = new Intent(this, WebViewA.class);
//			// Bundle b6 = new Bundle();
//			// b6.putInt("maxPageCount", 4);
//			// b6.putString("url", "product/xinli/yanshi/fujiabaoxian");
//			// b6.putString("format", "gif");
//			// b6.putString("screenFlag","yanshi");
//			// intent6.putExtras(b6);
//			// startActivity(intent6);
//			Toast.makeText(getApplicationContext(), "无资料", Toast.LENGTH_SHORT)
//					.show();
//			break;
		case R.id.canBaoTiShi:
			Intent intent7 = new Intent(this, ImgAnimationA.class);
			Bundle b7 = new Bundle();
			b7.putInt("maxPageCount", 5);
			b7.putString("url", "product/zhiyuerensheng/yanshi/canbaotishi");
			b7.putString("format", "gif");
			b7.putString("screenFlag", "yanshi");
			intent7.putExtras(b7);
			startActivity(intent7);
			break;

		// 第二部分linear点击事件
		case R.id.hongLiBaoXianJinE_2:
			showHongLiFanHuanLevel();
			break;
		case R.id.hongLiBaoXianJinELayout1_2:
			showHongLiFanHuan(80, 105);
			break;
		case R.id.hongLiBaoXianJinELayout2_2:
			showHongLiFanHuan(80, 90);
			break;
		case R.id.hongLiBaoXianJinELayout3_2:
			showHongLiFanHuan(60, 70);
			break;
		case R.id.hongLiBaoXianJinELayout4_2:
			showHongLiFanHuan(50, 60);
			break;
		case R.id.hongLiBaoXianJinELayout5_2:
			showHongLiFanHuan(0, 50);
			break;
		default:
			break;
		}
	}

	public void DialogSelect(String dialogLevel) {
		hongli.setLevel(dialogLevel);
		levelHongLiTV.setText(zyrs.getWanNengZhangHuLevel(zyrs
				.getLevel(dialogLevel)));
		hongLiMap = setData();
		setHongLiBXJEData();
		dialog.cancel();
		setFloatingVisible();
	}
//TODO 12.14 zjy 改了这里，去掉小数点后面数字.
	public void setHongLiBXJEData() {
		hongLiBaoXianJinE1_2.setText(Arithmetic4Double.doubleToString(Double.parseDouble(zyrs.getHongLiByAge(hongLiMap, 90))));
		hongLiBaoXianJinE2_2.setText(Arithmetic4Double.doubleToString(Double.parseDouble(zyrs.getHongLiByAge(hongLiMap, 80))));
		hongLiBaoXianJinE3_2.setText(Arithmetic4Double.doubleToString(Double.parseDouble(zyrs.getHongLiByAge(hongLiMap, 70))));
		hongLiBaoXianJinE4_2.setText(Arithmetic4Double.doubleToString(Double.parseDouble(zyrs.getHongLiByAge(hongLiMap, 60))));
		hongLiBaoXianJinE5_2.setText(Arithmetic4Double.doubleToString(Double.parseDouble(zyrs.getHongLiByAge(hongLiMap, 50))));
	}

	public void setFloatingVisible() {
		if (view != null) {
			view.setVisibility(View.VISIBLE);
		}
		if (step >= bottomSmallFloat) {
			if (topView != null) {
				topView.setVisibility(View.VISIBLE);
			}
		}

		if (step == bottomFloat) {
			if (bottomView != null) {
				bottomView.setVisibility(View.VISIBLE);
			}
		}
	}

	public void setFloatingInvisible() {
		if (view != null) {
			view.setVisibility(View.GONE);
		}
		if (topView != null) {
			topView.setVisibility(View.GONE);
		}
		if (bottomView != null) {
			bottomView.setVisibility(View.GONE);
		}
	}

	public void showHongLiFanHuanLevel() {
		final String[] items = new String[] { Define.Level_Low_display,
				Define.Level_Middle_display, Define.Level_High_display };

		// 隐藏悬浮框
		setFloatingInvisible();
		dialog = new AlertDialog.Builder(this)
				.setTitle("保单现金价值账户等级")
				.setSingleChoiceItems(items, 0,
						new DialogInterface.OnClickListener() {

							@Override
							public void onClick(DialogInterface dialog,
									int which) {
								switch (which) {
								case 0:
									String dialogLevel = Define.Level_Low;
									// 点击选项后的方法，更新数据，并且退出对话框，显示悬浮框
									DialogSelect(dialogLevel);
									break;
								case 1:
									dialogLevel = Define.Level_Middle;
									DialogSelect(dialogLevel);
									break;
								case 2:
									dialogLevel = Define.Level_High;
									DialogSelect(dialogLevel);
									break;
								}
							}
						})
				.setNegativeButton("取消", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						setFloatingVisible();
					}
				}).setCancelable(false).show();
		dialog.setCanceledOnTouchOutside(false);
	}

	public void showHongLiFanHuan(int start, int end) {
		setFloatingInvisible();
		String items[] = zyrs.findHongLi(hongLiMap, start, end);

		AlertDialog dialog = new AlertDialog.Builder(this).setTitle("保单现金价值账户")
				.setItems(items, null)
				.setNegativeButton("取消", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						setFloatingVisible();
					}
				}).setCancelable(false).show();
		dialog.setCanceledOnTouchOutside(false);
	}

	/**
	 * 初始化悬浮按钮
	 */
	private void initFloatView() {
		// 获取WindowManager
		wm = (WindowManager) getApplicationContext().getSystemService("window");
		// 设置LayoutParams(全局变量）相关参数
		wmParams = new WindowManager.LayoutParams();

		wmParams.type = LayoutParams.TYPE_PHONE; // 设置window type
		wmParams.format = PixelFormat.RGBA_8888; // 设置图片格式，效果为背景透明
		// 设置Window flag
		wmParams.flags = LayoutParams.FLAG_NOT_TOUCH_MODAL
				| LayoutParams.FLAG_NOT_FOCUSABLE;

		wmParams.width = WindowManager.LayoutParams.WRAP_CONTENT;
		wmParams.height = WindowManager.LayoutParams.WRAP_CONTENT;
		// 创建悬浮按钮
		createCenterFloatView();
		createBottomSmallFloatView();
		createBottomFloatView();
	}
	
	private void setHongLiValue() {
		zyrs = new ZhiYueRenShengService();

		hongli = new HongLi();
		hongli.setAge(Integer.valueOf(age));
		hongli.setSex(sex);
		hongli.setBaoE(Integer.valueOf(Arithmetic4Double
				.doubleToString(Arithmetic4Double.multi(Double.valueOf(baoE),
						Define.WanD))));
		hongli.setYear(Integer.valueOf(period));
		hongli.setZhuBaoF(Integer.valueOf(zhuBaoFeiD));
		hongli.setZhongJiBaoE(Integer.valueOf(Arithmetic4Double
				.doubleToString(Arithmetic4Double.multi(
						Double.valueOf(zhongJiBaoE), Define.WanD))));
		hongli.setYiWaiBaoE(Integer.valueOf(Arithmetic4Double
				.doubleToString(Arithmetic4Double.multi(
						Double.valueOf(yiLiaoBaoE), Define.WanD))));
		hongli.setShangHaiBaoE(Integer.valueOf(Arithmetic4Double
				.doubleToString(Arithmetic4Double.multi(
						Double.valueOf(shangHaiBaoE), Define.WanD))));
		hongli.setLevel(level);
	}
	
//	Runnable DataValueRunnable = new Runnable() {
//		@Override
//		public void run() {
//			Message m = new Message();
//			hongLiMap = setData();
//			m.what = 1;
//			handler.sendMessage(m);
//		}
//	};
//	
//	Handler handler = new Handler(){
//
//		@Override
//		public void handleMessage(Message msg) {
//			super.handleMessage(msg);
//			if(msg.what == 1){
//				// 红利
//				setHongLiBXJEData();
//			}
//		}
//		
//	};
	
	private void setDataValue() {
		levelHongLiTV.setText(zyrs.getWanNengZhangHuLevel(zyrs.getLevel(level)));
		
		floatText1.setText("交" + period + "年");
		floatText2.setText("每年" + zhuBaoFeiD + Define.Yuan);
		floatText3.setText("共"
				+ (Arithmetic4Double.multi(Double.valueOf(period),
						Double.valueOf(zhuBaoFeiD))) + "元");
	}

	/**
	 * 创建中间悬浮按钮
	 */
	private void createCenterFloatView() {
		Display display = getWindowManager().getDefaultDisplay();
		int height = display.getHeight();
		int width = display.getWidth();

		LayoutInflater inflater = (LayoutInflater) this
				.getSystemService(LAYOUT_INFLATER_SERVICE);
		//------左边《获得利益》悬浮件！ 
		view = inflater.inflate(R.layout.ps_hushenfu_center_float, null);
		
		// 设置悬浮窗口长宽数据
		wmParams.width = DensityUtil.dip2px(this, 50);
		wmParams.height = DensityUtil.dip2px(this, 30);

		wmParams.x = DensityUtil.dip2px(this, 95);
		wmParams.y = DensityUtil.dip2px(this, -10);
		// 调整悬浮窗口
		wmParams.gravity = Gravity.CENTER_VERTICAL | Gravity.LEFT;
		wm.addView(view, wmParams);
	}

	/**
	 * 创建上边小悬浮图片
	 */
	private void createBottomSmallFloatView() {
		Display display = getWindowManager().getDefaultDisplay();
		int height = display.getHeight();
		int width = display.getWidth();

		LayoutInflater inflater = (LayoutInflater) this
				.getSystemService(LAYOUT_INFLATER_SERVICE);
		//------中间《两者相加意外保障额度高达》悬浮件！
		topView = inflater.inflate(R.layout.ps_zhiyuerensheng_top_float, null);
		bAndY = (TextView) topView.findViewById(R.id.bAndY);
		bAndY.setText("高达"+Arithmetic4Double.add(Double.valueOf(baoE), Double.valueOf(shangHaiBaoE))+"万元");
			
		
		topView.setVisibility(View.GONE);

		wmParams.width = DensityUtil.dip2px(this, 90);
		wmParams.height = DensityUtil.dip2px(this, 30);
		Bitmap myBitmap = BitmapFactory.decodeResource(getResources(),
				R.drawable.shouhuxing_floatingcenter);// 获得图片资源
		wmParams.x = width / 2 + wmParams.width / 8;

		wmParams.y = DensityUtil.dip2px(this, 230);

		// 调整悬浮窗口
		wmParams.gravity = Gravity.LEFT | Gravity.TOP;
		wm.addView(topView, wmParams);
	}

	/**
	 * 创建下边悬浮按钮
	 */
	private void createBottomFloatView() {
		Display display = getWindowManager().getDefaultDisplay();
		int height = display.getHeight();
		int width = display.getWidth();

		LayoutInflater inflater = (LayoutInflater) this
				.getSystemService(LAYOUT_INFLATER_SERVICE);

		bottomView = inflater.inflate(R.layout.ps_zhiyuerensheng_bottom_float, null);

		// 下部分悬浮框初始化TextView,并设置监听
		productZeRen = (TextView) bottomView.findViewById(R.id.productZeRen);
		zeRenMianChu = (TextView) bottomView.findViewById(R.id.zeRenMianChu);
		LiCaiYouShi = (TextView) bottomView.findViewById(R.id.LiCaiYouShi);
		wenTiJieDa = (TextView) bottomView.findViewById(R.id.wenTiJieDa);
		zhiChanGuanLi = (TextView) bottomView.findViewById(R.id.zhiChanGuanLi);
		wanNengTouZi = (TextView) bottomView.findViewById(R.id.wanNengTouZi);
//		fuJiaBaoXian = (TextView) bottomView.findViewById(R.id.fuJiaBaoXian);
		canBaoTiShi = (TextView) bottomView.findViewById(R.id.canBaoTiShi);
		productZeRen.setOnClickListener(this);
		zhiChanGuanLi.setOnClickListener(this);
		wanNengTouZi.setOnClickListener(this);
		LiCaiYouShi.setOnClickListener(this);
		zeRenMianChu.setOnClickListener(this);
		wenTiJieDa.setOnClickListener(this);
//		fuJiaBaoXian.setOnClickListener(this);
		canBaoTiShi.setOnClickListener(this);

		LinearLayout floatBottom = (LinearLayout) bottomView
				.findViewById(R.id.floatBottom);
		bottomView.setVisibility(View.INVISIBLE);

		// 设置悬浮窗口长宽数据
		wmParams.width = width;
		wmParams.height = DensityUtil.dip2px(this, 30);
		;
		wmParams.x = width / 2 - wmParams.width / 2;
		wmParams.y = height - wmParams.height;

		// 调整悬浮窗口
		wmParams.gravity = Gravity.LEFT | Gravity.TOP;
		// wm.addView(tv, wmParams);
		wm.addView(bottomView, wmParams);
	}

	final int baoZhang_step1 = 4;
	final int baoZhang_step2 = 5;
	final int baoZhang_step3 = 3;
	final int jianKang_step1 = 1;
	final int jianKang_step2 = 2;
	final int jianKang_step3 = 6;
	final int fenHong_step1 = 7;
	final int fenHong_step2 = 8;
	final int fenHong_step3 = 13;
	final int fenHong_step4 = 12;
	final int fenHong_step5 = 11;
	final int fenHong_step6 = 10;
	final int fenHong_step7 = 9;
	final int yiWai_step1 = 14;
	final int yiWai_step2 = 15;
	final int yiWai_step3 = 16;
	// 下部分小悬浮框
	final int bottomSmallFloat = 17;
	// 下部分悬浮框
	final int bottomFloat = 18;

	public static int step = 0;

	public void next() {
		switch (++step) {
		case baoZhang_step1:
			// step++;
			baoZhangTitle.startAnimation(xiangXiaYiDong);
			baoZhangtext1.startAnimation(xiangXiaYiDong);
			baoZhangTitle.setVisibility(View.VISIBLE);
			baoZhangtext1.setVisibility(View.VISIBLE);
			break;
		case baoZhang_step2:
			jianKangZhongJiJinLinear.startAnimation(suoFangAndYiDong);
			jianKangZhongJiJinLinear.setVisibility(View.VISIBLE);
			jKZhongJiJin.setText(zhongJiBaoE + "万元");
			break;
		case baoZhang_step3:
			baoZhangtext2.startAnimation(xiangShangYiDong);
			baoZhangtext2.setVisibility(View.VISIBLE);
			// step++;
			break;
		case jianKang_step1:
			// step++;
			jianKangTitle.startAnimation(suoFang);
			jianKangtext1.startAnimation(suoFang);
			jianKangTitle.setVisibility(View.VISIBLE);
			jianKangtext1.setVisibility(View.VISIBLE);
			break;
		case jianKang_step2:
			yiWaiShenGuLinear.startAnimation(yiDongAndJianBian);
			jiBingShenGuLinear.startAnimation(yiDongAndJianBian);
			yiWaiShenGuLinear.setVisibility(View.VISIBLE);
			jiBingShenGuLinear.setVisibility(View.VISIBLE);
			yiWaiShenGuBaoZhangJin.setText(baoE + "万元");
			jiBingShenGuBaoZhangJin.setText(baoE + "万元");
			break;
		case jianKang_step3:
			// step++;
			jianKangtext2.startAnimation(xiangShangYiDong);
			jianKangtext2.setVisibility(View.VISIBLE);
			break;
		case fenHong_step1:
			huShenFuFenHongLinear1.setVisibility(View.VISIBLE);
			huShenFuFenHongText1.setVisibility(View.VISIBLE);
			huShenFuFenHongText2.setVisibility(View.VISIBLE);
			huShenFuFenHongTitle.setVisibility(View.VISIBLE);
			huShenFuFenHongTitle.startAnimation(xiangZuoYiDong);
			huShenFuFenHongText1.startAnimation(xiangZuoYiDong);
			huShenFuFenHongText2.startAnimation(AnimationUtils.loadAnimation(
					this, R.anim.in_from_right));
			break;

		// 分红账户条目的动画
		case fenHong_step2:
			fengHongLinear.setVisibility(View.GONE);
			huShenFuFenHongLinear1.setVisibility(View.GONE);
			zhongShenFenHongLinear.setVisibility(View.VISIBLE);
			hongLiBaoXianJinE_2.setVisibility(View.VISIBLE);

			hongLiBaoXianJinE_2.startAnimation(xiangZuoYiDong);
			zhongShenFenHongLinear.startAnimation(suoFangBianDa);
			break;
		case fenHong_step3:
			hongLiBaoXianJinELayout1_2.setVisibility(View.VISIBLE);
			hongLiBaoXianJinELayout1_2.startAnimation(xiangZuoYiDong);
			break;
		case fenHong_step4:
			hongLiBaoXianJinELayout2_2.setVisibility(View.VISIBLE);
			hongLiBaoXianJinELayout2_2.startAnimation(xiangZuoYiDong);
			break;
		case fenHong_step5:
			hongLiBaoXianJinELayout3_2.setVisibility(View.VISIBLE);
			hongLiBaoXianJinELayout3_2.startAnimation(xiangZuoYiDong);
			break;
		case fenHong_step6:
			hongLiBaoXianJinELayout4_2.setVisibility(View.VISIBLE);
			hongLiBaoXianJinELayout4_2.startAnimation(xiangZuoYiDong);
			break;
		case fenHong_step7:
			hongLiBaoXianJinELayout5_2.setVisibility(View.VISIBLE);
			hongLiBaoXianJinELayout5_2.startAnimation(xiangZuoYiDong);
			break;
		case bottomSmallFloat:
			topView.setVisibility(View.VISIBLE);
			topView.startAnimation(xiangXiaYiDong);
			break;
		case yiWai_step1:
			yiWaiTitle.setVisibility(View.VISIBLE);
			yiWaitext1.setVisibility(View.VISIBLE);
			yiWaiTitle.startAnimation(jianBian);
			yiWaitext1.startAnimation(jianBian);
			break;
		case yiWai_step2:
			yiWaiYiLiaoLinear.setVisibility(View.VISIBLE);
			jiBingShangCanLinear.setVisibility(View.VISIBLE);
			yiWaiYiLiaoLinear.startAnimation(xiangShangYiDong);
			jiBingShangCanLinear.startAnimation(xiangShangYiDong);
			yiWaiYiLiaoBaoZhangJin.setText(yiLiaoBaoE + "万元");
			yiWaiYiShangHaiZhangJin.setText(shangHaiBaoE + "万元");
			break;
		case yiWai_step3:
			yiWaitext2.setVisibility(View.VISIBLE);
			yiWaitext2.startAnimation(yiDongAndJianBian);
			break;
		// // 下部悬浮框
		case bottomFloat:
			bottomView.setVisibility(View.VISIBLE);
			bottomView.startAnimation(jianBian);
			break;
		default:
			step--;
			break;

		}
		// 处理到最后一步
		// TODO

	}

	public void previous() {

		switch (step) {
		case baoZhang_step1:
			step--;
			baoZhangTitle.setVisibility(View.INVISIBLE);
			baoZhangtext1.setVisibility(View.INVISIBLE);
			break;
		case baoZhang_step2:
			step--;
			jianKangZhongJiJinLinear.setVisibility(View.INVISIBLE);
			jianKangZhongJiJinLinear.setVisibility(View.INVISIBLE);
			break;
		case baoZhang_step3:
			step--;
			baoZhangtext2.setVisibility(View.INVISIBLE);
			break;
		case jianKang_step1:
			step--;
			jianKangTitle.setVisibility(View.INVISIBLE);
			jianKangtext1.setVisibility(View.INVISIBLE);
			break;
		case jianKang_step2:
			step--;
			yiWaiShenGuLinear.setVisibility(View.INVISIBLE);
			jiBingShenGuLinear.setVisibility(View.INVISIBLE);
			break;
		case jianKang_step3:
			step--;
			jianKangtext2.setVisibility(View.INVISIBLE);
			break;
		case fenHong_step1:
			step--;
			huShenFuFenHongText1.setVisibility(View.INVISIBLE);
			huShenFuFenHongText2.setVisibility(View.INVISIBLE);
			huShenFuFenHongTitle.setVisibility(View.INVISIBLE);
			break;
		// 分红账户条目的动画
		case fenHong_step2:
			step--;
			fengHongLinear.setVisibility(View.VISIBLE);
			huShenFuFenHongLinear1.setVisibility(View.VISIBLE);
			zhongShenFenHongLinear.setVisibility(View.GONE);
			hongLiBaoXianJinE_2.setVisibility(View.GONE);
			break;
		case fenHong_step3:
			step--;
			hongLiBaoXianJinELayout1_2.setVisibility(View.INVISIBLE);
			break;
		case fenHong_step4:
			step--;
			hongLiBaoXianJinELayout2_2.setVisibility(View.INVISIBLE);
			break;
		case fenHong_step5:
			step--;
			hongLiBaoXianJinELayout3_2.setVisibility(View.INVISIBLE);
			break;
		case fenHong_step6:
			step--;
			hongLiBaoXianJinELayout4_2.setVisibility(View.INVISIBLE);
			break;
		case fenHong_step7:
			step--;
			hongLiBaoXianJinELayout5_2.setVisibility(View.INVISIBLE);
			break;
		case bottomSmallFloat:
			step--;
			topView.setVisibility(View.INVISIBLE);
			break;
		case yiWai_step1:
			step--;
			yiWaiTitle.setVisibility(View.INVISIBLE);
			yiWaitext1.setVisibility(View.INVISIBLE);
			break;
		case yiWai_step2:
			step--;
			yiWaiYiLiaoLinear.setVisibility(View.INVISIBLE);
			jiBingShangCanLinear.setVisibility(View.INVISIBLE);
			break;
		case yiWai_step3:
			step--;
			yiWaitext2.setVisibility(View.INVISIBLE);
			break;
		case bottomFloat:
			step--;
			bottomView.setVisibility(View.INVISIBLE);
			break;
		default:
			// step--;
			break;

		}

		// 防止一进入界面就下一步
		if (step < 1) {
			step = 0;
		}

	}

	@Override
	protected void onStart() {

		super.onStart();
	}

	@Override
	protected void onRestart() {
		if (view != null) {
			view.setVisibility(View.VISIBLE);
		}
		if (topView != null) {
			topView.setVisibility(View.VISIBLE);
		}
		if (bottomView != null) {
			bottomView.setVisibility(View.VISIBLE);
		}
		super.onRestart();
	}

	@Override
	protected void onResume() {

		super.onResume();

	}

	@Override
	protected void onPause() {
		if (view != null) {
			view.setVisibility(View.GONE);
		}

		if (topView != null) {
			topView.setVisibility(View.GONE);
		}
		if (bottomView != null) {
			bottomView.setVisibility(View.GONE);
		}
		super.onPause();
	}

	@Override
	protected void onStop() {
		if (view != null) {
			view.setVisibility(View.GONE);
		}
		if (topView != null) {
			topView.setVisibility(View.GONE);
		}
		if (bottomView != null) {
			bottomView.setVisibility(View.GONE);
		}

		super.onStop();
	}

	@Override
	protected void onDestroy() {
		if (view != null) {
			wm.removeView(view);
		}
		if (topView != null) {
			wm.removeView(topView);
		}

		if (bottomView != null) {
			wm.removeView(bottomView);
		}

		super.onDestroy();
	}

	@Override
	public void onBackPressed() {
		Intent intent4 = new Intent(this,ZhiYueRenShengPlanA.class);
		startActivity(intent4);
		finish();
		step = 0;
	}

	public ArrayList<HongLi> setData() {
		int ageFromUI = hongli.getAge();
		//获取数据库中的数据，用集合封装
		getData();
		//意外伤害 
		Double shangHaiValue = zyrs.getYiWaiShangHai(hongli);
		//意外医疗 
		Double yiLiaoValue = zyrs.getYiWaiYiLiao(hongli);
		//豁免
		ArrayList<HongLi> huoMianValue = zyrs.getHuoMian(hongli,hmFeiLv,wxxsFeiLv,huoMian);
		//初始费用
		ArrayList<HongLi> chuShiValue = zyrs.getChuShi(hongli);
		//进入万能账户的值
		ArrayList<HongLi> wanNengZhangHu = zyrs.getWanNengZhangHu(hongli,chuShiValue);
		
		//第一年保单年初价值
		double firstNianChuValue = zyrs.getFirstNianChuValue(wanNengZhangHu);
		//(K6-N6-DU6/12-DV6/12)*(1+$B$2)
		//第一年第12个月末的保单现金价值
		double firstBaoDanXianJinValue = zyrs.getFirstBaoDanXianJin(hongli,firstNianChuValue,huoMianValue,
				shangHaiValue,yiLiaoValue,zxFeiLv,zjFeiLv);
		//第二年以后的第2个月末保单现金价值
		ArrayList<HongLi> afterBaoDanXianJinValue = zyrs.getAfterBaoDanXianJin(hongli,huoMianValue,
				firstBaoDanXianJinValue,shangHaiValue,yiLiaoValue,zxFeiLv,zjFeiLv,wanNengZhangHu);
		
		ArrayList<HongLi> al = new ArrayList<HongLi>();
		HongLi h = null;
		int k = 0;
		for(int i = ageFromUI;i<=104;i++){
			h = new HongLi();
			h.setAge(i);
			//第一年的年末保单现金价值
			if(i == ageFromUI){
				if(firstBaoDanXianJinValue > 0){
					h.setHongli(String.valueOf(Arithmetic4Double.doubleToString(firstBaoDanXianJinValue)));
				}else{
					h.setHongli("0");
				}
			}
			//第二年以后的年末保单现金价值
			else{
				double hli=Double.valueOf(afterBaoDanXianJinValue.get(k).getHongli());
				if(hli > 0){
						h.setHongli(Arithmetic4Double.doubleToString(hli));
				}else{
						h.setHongli("0");
				}
				k++;
			}
			
//			double hl=Double.valueOf(h.getHongli()).doubleValue();
//			h.setHongli(String.valueOf(Arithmetic4Double.round(hl, 0)));
			al.add(h);
		}
		
//		修改虚岁
		for (HongLi entity : al) {
			entity.setAge(entity.getAge()+1);
			
		}
		
		return al;
	}

	private void getData() {
		//主险费率
		zxFeiLv = DBDAO.findZYRSFuJiaXianValueMap(hongli,this,Define.DB_Type_FuJiaBaoFei_ZhiYueRenSheng);
		//重疾费率
		zjFeiLv = DBDAO.findZYRSFuJiaXianValueMap(hongli,this,Define.DB_Type_FuJiaBaoFei_ZhiYueRenShengZhongJi);
		//豁免费率
		hmFeiLv = DBDAO.findZYRSFuJiaXianValueMap(hongli,this,Define.DB_Type_FuJiaBaoFei_ZhiYueRenShengHuoMian);
		//豁免危险系数
		wxxsFeiLv = DBDAO.findZYRSFuJiaXianValue(hongli,this,Define.DB_Type_FuJiaBaoFei_ZhiYueRenShengWeiXianXiShu);
	}
}
