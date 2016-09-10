package com.baoxiao.activity.productshow.hushenfu;

import java.util.ArrayList;
import com.baoxiao.R;
import com.baoxiao.activity.productshow.ImgAnimationA;
import com.baoxiao.activity.productshow.shouhux.ProductWenTiJDA;
import com.baoxiao.model.HongLi;
import com.baoxiao.service.productshow.HuShenFuService;
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
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class HuShenFuShowA extends Activity implements OnClickListener {
	// 动画集及动画
	private Animation xiangYouYiDong,xiangZuoYiDong,yiDongAndJianBian, jianBian, suoFang, suoFangBianDa,
			xuanZhuan, xiangXiaYiDong, xiangShangYiDong, suoFangAndXuanZhuan,
			suoFangAndYiDong;
	private AnimationSet set;

	private boolean tagFloating = true;
	// 主界面及其他四个界面
	private LinearLayout main;
	private LinearLayout leftLinear;
	private LinearLayout rightLinear;
	
	//左边的圆形中的数据控件
	private TextView floatText1,floatText2,floatText3;
	

	// 悬浮框
	private WindowManager wm = null;
	private WindowManager.LayoutParams wmParams = null;
	// 下部悬浮框
	private TextView productZeRen, zhiChanGuanLi, LiCaiYouShi, wenTiJieDa,
			fuJiaBaoXian, canBaoTiShi;
	// 下部小悬浮框的值
	private TextView hushenfusmallfloat;

	// 第一部分
	private RelativeLayout jianKangZhongJiJinLinear,jianKangQingZhengBaoZhangJinBgLinear;
	private TextView jianKangTitle, jianKangtext1, jianKangtext2,
			yiWaiShenGuBaoZhangJin, jiBingShenGuBaoZhangJin;
	private ImageView jianKangAdd;
	private LinearLayout ll_hushenfu_zj;

	// 第二部分
	private HongLi hongli;
	private LinearLayout fengHongLinear,huShenFuFenHongLinear1;
	private TextView huShenFuFenHongTitle,huShenFuFenHongText1,
	        huShenFuFenHongText2,jKZhongJiJin, jKQingZhengZhongJiJin;

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
			yiWaiYiLiaoBaoZhangJin, jiBingShangCanBaoZhangJin;

	// 数据
	private String sex;
	private String age;
	private String period;
	// 等级
	private String level;
	// 保额
	private String baoE;
	private String zhongJiBaoE;
	private String yiWaiBaoE;
	// 保费
	private double zhuBaoFeiD, zhongJiBaoFeiD, huoMianBaoFeiD, totalBaoFeiD,yiWaiBaoFeiD;
	private View view = null;
	private View bottomView = null;
	private View bottomSmallView = null;
	private HuShenFuService hsfService;
	private ArrayList<HongLi> hongLiMap;

	// // 值

	private AlertDialog dialog;
	
	private boolean HomeFlag = false; 
	private String DialogFlag = "false";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// 设置横屏，全屏，无标题
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.ps_hushenfushow);

		// 初始化控件
		initView();
		// 初始化数据
		sex = this.getIntent().getStringExtra("sex");
		age = this.getIntent().getStringExtra("age");
		period = this.getIntent().getStringExtra("period");
		level = this.getIntent().getStringExtra("level");
		baoE = this.getIntent().getStringExtra("baoE");
		zhongJiBaoE = this.getIntent().getStringExtra("zhongJiBaoE");
		yiWaiBaoE = this.getIntent().getStringExtra("yiWaiBaoE");
		zhuBaoFeiD = this.getIntent().getDoubleExtra("zhuBaoFeiD", 0);
		zhongJiBaoFeiD = this.getIntent().getDoubleExtra("zhongJiBaoFeiD", 0);
		huoMianBaoFeiD = this.getIntent().getDoubleExtra("huoMianBaoFeiD", 0);
		yiWaiBaoFeiD = this.getIntent().getDoubleExtra("YiWaiBaoFeiD", 0);
		totalBaoFeiD = Arithmetic4Double.add(Arithmetic4Double.add(
				Arithmetic4Double.add(zhuBaoFeiD, zhongJiBaoFeiD),
				huoMianBaoFeiD),yiWaiBaoFeiD);

		initAnim();

		// 初始化悬浮按钮
		initFloatView();

		hsfService = new HuShenFuService();
		// 第二部分初始化
		hongli = new HongLi();
		hongli.setSex(sex);
		hongli.setAge(Integer.valueOf(age));
		hongli.setYear(Integer.valueOf(period));
		hongli.setFenShu(Integer.valueOf(baoE));
		hongli.setLevel(level);
		hongLiMap = hsfService.findHongLi(hongli, this,
				Define.DB_Type_BaoFei_HuShenFu);
		levelHongLiTV.setText(HuShenFuService.getZhongShenFenHongLevel(HuShenFuService
				.getLevel(level)));
		// 累积生存金
		// setLeiJiShengCunJin();
		// 红利
		setHongLiBXJEData();
		floatText1.setText("交" + period + "年");
		floatText2.setText("每年" + totalBaoFeiD+Define.Yuan);
		floatText3.setText("共"
				+ (Arithmetic4Double.sub(Arithmetic4Double.multi(
						Double.valueOf(period), totalBaoFeiD), huoMianBaoFeiD))
				+ "元");
	}

	private void initView() {

		// 主界面初始化
		main = (LinearLayout) findViewById(R.id.main);
		main.setOnClickListener(this);
		leftLinear = (LinearLayout) findViewById(R.id.leftLinear);
		rightLinear = (LinearLayout) findViewById(R.id.RightLinear);
		leftLinear.setOnClickListener(this);
		// rightLinear.setClickable(clickable)
		rightLinear.setOnClickListener(this);

		//左边的圆形中的数据控件
		floatText1 = (TextView)findViewById(R.id.floatText1);
		floatText2 = (TextView)findViewById(R.id.floatText2);
		floatText3 = (TextView)findViewById(R.id.floatText3);

		// 控件的初始化和点击监听
		jianKangZhongJiJinLinear = (RelativeLayout) findViewById(R.id.jianKangZhongJiJinLinear);
		jianKangQingZhengBaoZhangJinBgLinear = (RelativeLayout) findViewById(R.id.jianKangQingZhengBaoZhangJinBgLinear);

		jianKangTitle = (TextView) findViewById(R.id.huShenFuJianKangTitle);
		jianKangtext1 = (TextView) findViewById(R.id.huShenFuJianKangText1);
		jianKangtext2 = (TextView) findViewById(R.id.huShenFuJianKangText2);
		yiWaiShenGuBaoZhangJin = (TextView) findViewById(R.id.yiWaiShenGuBaoZhangJin);
		jiBingShenGuBaoZhangJin = (TextView) findViewById(R.id.jiBingShenGuBaoZhangJin);

		jianKangAdd = (ImageView) findViewById(R.id.jianKangAdd);
		ll_hushenfu_zj = (LinearLayout) findViewById(R.id.ll_hushenfu_zj);
		ll_hushenfu_zj.setVisibility(View.INVISIBLE);

		jianKangZhongJiJinLinear.setVisibility(View.INVISIBLE);
		jianKangQingZhengBaoZhangJinBgLinear.setVisibility(View.INVISIBLE);
		jianKangTitle.setVisibility(View.INVISIBLE);
		jianKangtext1.setVisibility(View.INVISIBLE);
		jianKangtext2.setVisibility(View.INVISIBLE);
		jianKangAdd.setVisibility(View.INVISIBLE);

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
		jKQingZhengZhongJiJin = (TextView) findViewById(R.id.jKQingZhengZhongJiJin);

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

		// dsf
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
		yiWaiYiLiaoBaoZhangJin = (TextView) findViewById(R.id.yiWaiYiLiaoBaoZhangJin);
		jiBingShangCanBaoZhangJin = (TextView) findViewById(R.id.jiBingShangCanBaoZhangJin);
		yiWaiYiLiaoLinear = (LinearLayout) findViewById(R.id.yiWaiYiLiaoLinear);
		jiBingShangCanLinear = (LinearLayout) findViewById(R.id.jiBingShangCanLinear);
		yiWaiTitle.setVisibility(View.INVISIBLE);
		yiWaitext1.setVisibility(View.INVISIBLE);
		yiWaitext2.setVisibility(View.INVISIBLE);
		yiWaiYiLiaoLinear.setVisibility(View.INVISIBLE);
		jiBingShangCanLinear.setVisibility(View.INVISIBLE);
	}

	private void initAnim() {
		// 动画的初始化
		// 位移动画
		xiangYouYiDong = AnimationUtils.loadAnimation(this,
				R.anim.ps_shouhuxshow_anim_translateleftin);
		xiangZuoYiDong = AnimationUtils.loadAnimation(this,
				R.anim.ps_shouhuxshow_anim_translaterightin);
		yiDongAndJianBian = AnimationUtils.loadAnimation(
				getApplicationContext(), R.anim.ps_shouhuxshow_anim_translateleftin);
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
		xiangShangYiDong = AnimationUtils.loadAnimation(
				getApplicationContext(),
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

	// private void setLeiJiShengCunJin() {
	// // TODO Auto-generated method stub
	// baoDanLeiJiContent1_shengcunjin.setText(shxService
	// .getLeiJinFanHuanByAge(leiJinFanHuanJinMap, 80));
	// baoDanLeiJiContent2_shengcunjin.setText(shxService
	// .getLeiJinFanHuanByAge(leiJinFanHuanJinMap, 60));
	// baoDanLeiJiContent3_shengcunjin.setText(shxService
	// .getLeiJinFanHuanByAge(leiJinFanHuanJinMap, 35));
	// baoDanLeiJiContent4_shengcunjin.setText(shxService
	// .getLeiJinFanHuanByAge(leiJinFanHuanJinMap, 28));
	// baoDanLeiJiContent5_shengcunjin.setText(shxService
	// .getLeiJinFanHuanByAge(leiJinFanHuanJinMap, 22));
	// }

	// public static String step="1";

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		// 左右屏幕点击事件
		case R.id.RightLinear:
			next();
			if (view != null) {
				view.setVisibility(View.VISIBLE);
			}
			// Toast.makeText(getApplicationContext(), "click right",
			// Toast.LENGTH_SHORT).show();
			break;

		case R.id.leftLinear:
			previous();
			// Toast.makeText(getApplicationContext(), "click left",
			// Toast.LENGTH_SHORT).show();
			if (view != null) {
				view.setVisibility(View.VISIBLE);
			}
			break;

		case R.id.productZeRen:
			Intent intent2 = new Intent(this, ImgAnimationA.class);
			Bundle b2 = new Bundle();
			b2.putInt("maxPageCount", 6);
			b2.putString("url", "product/hushenfu/yanshi/productzeren");
			b2.putString("format", "gif");
			b2.putString("screenFlag","yanshi");
			b2.putString("tiaokuan", "hushenfu");
			intent2.putExtras(b2);
			startActivity(intent2);
			break;
		case R.id.zhiChanGuanLi:
			Intent intent3 = new Intent(this, ImgAnimationA.class);
			Bundle b3 = new Bundle();
			b3.putInt("maxPageCount", 19);
			b3.putString("url", "product/shouhux/yanshi/zichanguanli");
			b3.putString("format", "gif");
			b3.putString("screenFlag","jianjie");
			intent3.putExtras(b3);
			startActivity(intent3);
			break;
			//在该项，改成了责任免除
		case R.id.LiCaiYouShi:
			Intent intent4 = new Intent(this, ImgAnimationA.class);
			Bundle b4 = new Bundle();
			b4.putInt("maxPageCount", 10);
			b4.putString("url", "product/hushenfu/yanshi/zerenmianchu");
			b4.putString("format", "gif");
			b4.putString("screenFlag","yanshi");
			intent4.putExtras(b4);
			startActivity(intent4);
			break;
		// 该页面有点不同，视屏选择页面
		case R.id.wenTiJieDa:
			Intent intent5 = new Intent(this, ProductWenTiJDA.class);
			startActivity(intent5);
			break;
//		case R.id.fuJiaBaoXian:
//			Toast.makeText(getApplicationContext(), "无资料", Toast.LENGTH_LONG).show();
//			Intent intent6 = new Intent(this, WebViewA.class);
//			Bundle b6 = new Bundle();
//			b6.putInt("maxPageCount", 4);
//			b6.putString("url", "product/xinli/yanshi/fujiabaoxian");
//			b6.putString("format", "gif");
//			b6.putString("screenFlag","yanshi");
//			intent6.putExtras(b6);
//			startActivity(intent6);
//			break;
		case R.id.canBaoTiShi:
			Intent intent7 = new Intent(this, ImgAnimationA.class);
			Bundle b7 = new Bundle();
			b7.putInt("maxPageCount", 5);
			b7.putString("url", "product/hushenfu/yanshi/canbaotishi");
			b7.putString("format", "gif");
			b7.putString("screenFlag","yanshi");
			intent7.putExtras(b7);
			startActivity(intent7);
			break;

		// 第二部分linear点击事件
		case R.id.hongLiBaoXianJinE_2:
			showHongLiFanHuanLevel();
			break;
		case R.id.hongLiBaoXianJinELayout1_2:
			showHongLiFanHuan(90, 105);
			break;
		case R.id.hongLiBaoXianJinELayout2_2:
			showHongLiFanHuan(80, 89);
			break;
		case R.id.hongLiBaoXianJinELayout3_2:
			showHongLiFanHuan(70, 79);
			break;
		case R.id.hongLiBaoXianJinELayout4_2:
			showHongLiFanHuan(60, 69);
			break;
		case R.id.hongLiBaoXianJinELayout5_2:
			showHongLiFanHuan(Integer.valueOf(18), 59);
			break;
		default:
			break;
		}
	}

	public void DialogSelect(String dialogLevel) {
		hongli.setLevel(dialogLevel);
		levelHongLiTV.setText(hsfService.getZhongShenFenHongLevel(
				hsfService.getLevel(dialogLevel)));
		hongLiMap = hsfService.findHongLi(hongli, getApplicationContext(),
				Define.DB_Type_BaoFei_HuShenFu);
		setHongLiBXJEData();
		dialog.cancel();
		setFloatingVisible();
	}

	public void setHongLiBXJEData() {
		hongLiBaoXianJinE1_2.setText(hsfService.getHongLiByAge(hongLiMap, 90));
		hongLiBaoXianJinE2_2.setText(hsfService.getHongLiByAge(hongLiMap, 80));
		hongLiBaoXianJinE3_2.setText(hsfService.getHongLiByAge(hongLiMap, 70));
		hongLiBaoXianJinE4_2.setText(hsfService.getHongLiByAge(hongLiMap, 60));
		hongLiBaoXianJinE5_2.setText(hsfService.getHongLiByAge(hongLiMap, 50));
	}

	public void setFloatingVisible() {
		if (view != null) {
			view.setVisibility(View.VISIBLE);
		}
		if (step >= botoomSmallFloat) {
			if (bottomSmallView != null) {
				bottomSmallView.setVisibility(View.VISIBLE);
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
		if (bottomSmallView != null) {
			bottomSmallView.setVisibility(View.GONE);
		}
		if (bottomView != null) {
			bottomView.setVisibility(View.GONE);
		}
	}

	public void showHongLiFanHuanLevel() {
		final String[] items = new String[] { Define.Level_Low_display,
				Define.Level_Middle_display, Define.Level_High_display };
		DialogFlag = "true";
		// 隐藏悬浮框
		setFloatingInvisible();
		dialog = new AlertDialog.Builder(this)
				.setTitle("保单累积红利等级")
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
						// Toast.makeText(DialogActivity.this,"我一点也不喜欢海贼王",
						// Toast.LENGTH_SHORT).show();
					}
				}).setCancelable(false).show();
		dialog.setCanceledOnTouchOutside(false);
	}

	public void showHongLiFanHuan(int start, int end) {
		setFloatingInvisible();
		String items[] = hsfService.findHongLi(hongLiMap, start, end);
		DialogFlag = "true";

		AlertDialog dialog = new AlertDialog.Builder(this).setTitle("保单累积红利")
				.setItems(items, null)
				.setNegativeButton("取消", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						setFloatingVisible();
						// Toast.makeText(DialogActivity.this,"我一点也不喜欢海贼王",
						// Toast.LENGTH_SHORT).show();
					}
				}).setCancelable(false).show();
		dialog.setCanceledOnTouchOutside(false);
		// Window win = dialog.getWindow();
		// //
		// dialog.getListView().setBackgroundResource(R.color.FanHuanJinColor);

		// win.setBackgroundDrawableResource(R.color.FanHuanJinColor);
	}

	/**
	 * 初始化悬浮按钮
	 */
	private void initFloatView() {
		// 获取屏幕宽高

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
		// createRightFloatView();
//		createTopFloatView();
		createBottomSmallFloatView();
		createBottomFloatView();

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
		view = inflater.inflate(R.layout.ps_hushenfu_center_float, null);
		Bitmap myBitmap = BitmapFactory.decodeResource(getResources(),
				R.drawable.img2_1);// 获得图片资源
		// 设置悬浮窗口长宽数据
		wmParams.width = DensityUtil.dip2px(this, 50);
		wmParams.height = DensityUtil.dip2px(this, 30);

		wmParams.x = DensityUtil.dip2px(this, 96);
		wmParams.y = DensityUtil.dip2px(this, -10);
		// 调整悬浮窗口
		wmParams.gravity = Gravity.CENTER_VERTICAL | Gravity.LEFT;
		wm.addView(view, wmParams);
	}

	/**
	 * 创建下边小悬浮图片
	 */
	private void createBottomSmallFloatView() {
		Display display = getWindowManager().getDefaultDisplay();
		int height = display.getHeight();
		int width = display.getWidth();

		LayoutInflater inflater = (LayoutInflater) this
				.getSystemService(LAYOUT_INFLATER_SERVICE);
		bottomSmallView = inflater.inflate(
				R.layout.ps_hushenfu_bottom_smallfloat, null);
		hushenfusmallfloat = (TextView) bottomSmallView
				.findViewById(R.id.hushenfusmallfloat);

		bottomSmallView.setVisibility(View.GONE);

		// 设置悬浮窗口长宽数据
		// wmParams.width = 120;
		// wmParams.height = 180;
		wmParams.width = DensityUtil.dip2px(this, 100);
		wmParams.height = DensityUtil.dip2px(this, 30);
		Bitmap myBitmap = BitmapFactory.decodeResource(getResources(),
				R.drawable.shouhuxing_floatingcenter);// 获得图片资源
		wmParams.x = width / 2 + wmParams.width / 8;

		wmParams.y = DensityUtil.dip2px(this, 240);

		// 调整悬浮窗口
		wmParams.gravity = Gravity.LEFT | Gravity.TOP;
		wm.addView(bottomSmallView, wmParams);
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

		bottomView = inflater.inflate(R.layout.ps_hushenfu_bottom_float, null);

		// 下部分悬浮框初始化TextView,并设置监听
		productZeRen = (TextView) bottomView.findViewById(R.id.productZeRen);
		zhiChanGuanLi = (TextView) bottomView.findViewById(R.id.zhiChanGuanLi);
		LiCaiYouShi = (TextView) bottomView.findViewById(R.id.LiCaiYouShi);
		wenTiJieDa = (TextView) bottomView.findViewById(R.id.wenTiJieDa);
//		fuJiaBaoXian = (TextView) bottomView.findViewById(R.id.fuJiaBaoXian);
		canBaoTiShi = (TextView) bottomView.findViewById(R.id.canBaoTiShi);
		productZeRen.setOnClickListener(this);
		zhiChanGuanLi.setOnClickListener(this);
		LiCaiYouShi.setOnClickListener(this);
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

	// 保障动画步骤
	final int baoZhang_step1 = 1;
	final int baoZhang_step2 = 2;
	final int baoZhang_step3 = 3;

	// 健康动画步骤
	final int jianKang_step1 = 4;

	final int jianKang_step2 = 5;

	final int jianKang_step3 = 6;

	final int jianKang_step4 = 7;

	// 分红动画步骤
	final int fenHong_step1 = 8;

	final int fenHong_step2 = 9;
	final int fenHong_step3 = 14;
	final int fenHong_step4 = 13;
	final int fenHong_step5 = 12;
	final int fenHong_step6 = 11;
	final int fenHong_step7 = 10;

	// 意外动画步骤
	final int yiWai_step1 = 15;

	final int yiWai_step2 = 16;
	final int yiWai_step3 = 17;
	// 下部分小悬浮框
	final int botoomSmallFloat = 18;

	// 下部分悬浮框
	final int bottomFloat = 19;

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
			// step++;
			yiWaiShenGuLinear.startAnimation(yiDongAndJianBian);
			jiBingShenGuLinear.startAnimation(yiDongAndJianBian);
			yiWaiShenGuLinear.setVisibility(View.VISIBLE);
			jiBingShenGuLinear.setVisibility(View.VISIBLE);
			yiWaiShenGuBaoZhangJin.setText(baoE + "万元");
			jiBingShenGuBaoZhangJin.setText(baoE + "万元");
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
			jianKangZhongJiJinLinear.startAnimation(suoFangAndYiDong);
			ll_hushenfu_zj.setVisibility(View.VISIBLE);
			jianKangAdd.startAnimation(suoFang);
			jianKangZhongJiJinLinear.setVisibility(View.VISIBLE);
			jianKangAdd.setVisibility(View.VISIBLE);
			jKZhongJiJin.setText(zhongJiBaoE + "万元");
			jKQingZhengZhongJiJin.setText(Arithmetic4Double.multi(
					Integer.valueOf(zhongJiBaoE), 0.2)
					+ "万元");
			// manQiBaoXianJinLinear1_Text1.setText(Arithmetic4Double.multi(
			// Double.valueOf(baoE), 2)
			// + "万元");
			break;
		case jianKang_step3:
			// step++;
			jianKangQingZhengBaoZhangJinBgLinear.startAnimation(jianBian);
			jianKangQingZhengBaoZhangJinBgLinear.setVisibility(View.VISIBLE);
			break;
		case jianKang_step4:
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

			//分红账户条目的动画
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
			yiWaiYiLiaoBaoZhangJin.setText(yiWaiBaoE + "万元");
			jiBingShangCanBaoZhangJin.setText(yiWaiBaoE + "万元");
			break;
		case yiWai_step3:
			yiWaitext2.setVisibility(View.VISIBLE);
			yiWaitext2.startAnimation(yiDongAndJianBian);
			break;
		case botoomSmallFloat:

			hushenfusmallfloat.setText(
					String.valueOf(Arithmetic4Double.add(Integer.valueOf(baoE),
							Arithmetic4Double.multi(Integer.valueOf(yiWaiBaoE),
									2))));

			bottomSmallView.setVisibility(View.VISIBLE);
			bottomSmallView.startAnimation(xiangXiaYiDong);
			break;
		//
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

	// sadf
	public void previous() {

		switch (step) {
		case baoZhang_step1:
			step--;
			baoZhangTitle.setVisibility(View.INVISIBLE);
			baoZhangtext1.setVisibility(View.INVISIBLE);
			break;
		case baoZhang_step2:
			step--;
			yiWaiShenGuLinear.setVisibility(View.INVISIBLE);
			jiBingShenGuLinear.setVisibility(View.INVISIBLE);
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
			jianKangZhongJiJinLinear.setVisibility(View.INVISIBLE);
			ll_hushenfu_zj.setVisibility(View.INVISIBLE);
			jianKangAdd.setVisibility(View.INVISIBLE);
			break;
		case jianKang_step3:
			step--;
			jianKangQingZhengBaoZhangJinBgLinear.setVisibility(View.INVISIBLE);
			break;

		case jianKang_step4:
			step--;
			jianKangtext2.setVisibility(View.INVISIBLE);
			break;
		case fenHong_step1:
			step--;
			huShenFuFenHongTitle.setVisibility(View.INVISIBLE);
			huShenFuFenHongLinear1.setVisibility(View.INVISIBLE);
			huShenFuFenHongText2.setVisibility(View.INVISIBLE);
			break;
			//分红账户条目的动画
		case fenHong_step2:
			step--;
			fengHongLinear.setVisibility(View.VISIBLE);
			huShenFuFenHongLinear1.setVisibility(View.VISIBLE);
			zhongShenFenHongLinear.setVisibility(View.GONE);
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
		case botoomSmallFloat:
			step--;
			bottomSmallView.setVisibility(View.INVISIBLE);
			break;
		case bottomFloat:
			step--;
			bottomView.setVisibility(View.INVISIBLE);
			break;
		default:
//			step--;
			break;

		}

//		 防止一进入界面就下一步
		 if(step<1){
		 step=0;
		 }

	}

	@Override
	protected void onStart() {

		super.onStart();
	}

	@Override
	protected void onRestart() {
		//按下home键返回后的事件
//		if(HomeFlag){
			if (view != null) {
				view.setVisibility(View.VISIBLE);
			}
			// if(tagFloating){
			if (bottomSmallView != null) {
				bottomSmallView.setVisibility(View.VISIBLE);
			}
			if (bottomView != null) {
				bottomView.setVisibility(View.VISIBLE);
			}
//		}else{
//			if (view != null) {
//				view.setVisibility(View.GONE);
//			}
//			if (topView != null) {
//				topView.setVisibility(View.GONE);
//			}
//			if (bottomSmallView != null) {
//				bottomSmallView.setVisibility(View.GONE);
//			}
//			if (bottomView != null) {
//				bottomView.setVisibility(View.GONE);
//			}
//		}
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
		if (bottomSmallView != null) {
			bottomSmallView.setVisibility(View.GONE);
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
		if (bottomSmallView != null) {
			bottomSmallView.setVisibility(View.GONE);
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
		if (bottomSmallView != null) {
			wm.removeView(bottomSmallView);
		}

		if (bottomView != null) {
			wm.removeView(bottomView);
		}

		super.onDestroy();
	}

	@Override
	public void onBackPressed() {
//		super.onBackPressed();
//		Intent intent = new Intent(this, BaoXianProductA.class);		intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//		startActivity(intent);
		
		Intent intent4 = new Intent(this, HuShenFuPlanA.class);
		startActivity(intent4);
		finish();
		step = 0;
	}

//	@Override
//	public boolean onKeyDown(int keyCode, KeyEvent event) {
//		if(keyCode == KeyEvent.KEYCODE_HOME){
//			HomeFlag = true;
//		}
//		return super.onKeyDown(keyCode, event);
//	}
	
	

}
