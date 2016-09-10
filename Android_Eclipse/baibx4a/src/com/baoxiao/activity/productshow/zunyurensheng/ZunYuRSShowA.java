package com.baoxiao.activity.productshow.zunyurensheng;

import java.util.ArrayList;
import java.util.Map;
import com.baoxiao.R;
import com.baoxiao.activity.productshow.ImgAnimationA;
import com.baoxiao.activity.productshow.shouhux.ProductWenTiJDA;
import com.baoxiao.model.Entity;
import com.baoxiao.model.HongLi;
import com.baoxiao.service.productshow.ZunYuRSService;
import com.baoxiao.util.Arithmetic4Double;
import com.baoxiao.util.Define;
import com.baoxiao.util.DensityUtil;
import com.baoxiao.util.E2DoubleUtil;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.text.TextPaint;
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
import android.widget.RelativeLayout;
import android.widget.TextView;

public class ZunYuRSShowA extends Activity implements OnClickListener {
		// 动画集及动画
		private Animation xiangYouYiDong,xiangZuoYiDong,yiDongAndJianBian, jianBian, suoFang, suoFangBianDa,
				xuanZhuan, xiangXiaYiDong, xiangXiaYiDongFast, xiangShangYiDong, suoFangAndXuanZhuan,
				suoFangAndYiDong;

		// 主界面及其他四个界面
		private LinearLayout main;
		private LinearLayout leftLinear;
		private LinearLayout rightLinear;

		// 悬浮框
		private WindowManager wm = null;
		private WindowManager.LayoutParams wmParams = null;
		// 下部悬浮框
		private TextView productZeRen, zhiChanGuanLi,liCaiYouShi,juCaiBaoTeSe, wenTiJieDa,
				 canBaoTiShi,scJin;

		// 第一部分
		private RelativeLayout fanHuanJinL;
		private LinearLayout yingJuYiShengTitle,text_1,text_2,gaoErHuanBen,yiWaiTitle;

		// 第二部分
		private HongLi hongli;
		private LinearLayout wanNengZhangHuLinaer,wanNengZhangHuLinaer2,huShenFuFenHongText2, fengHongLinear,shouYiWenJian,shengcunjinLinear;
		
		private LinearLayout zhongShenFenHongLinear, hongLiBaoXianJinELayout1_2,
				hongLiBaoXianJinELayout2_2, hongLiBaoXianJinELayout3_2,
				hongLiBaoXianJinELayout4_2, hongLiBaoXianJinELayout5_2,
				hongLiBaoXianJinE_2;
		private LinearLayout zhongShenFenHongLinear18and25,hongLiBaoXianJinE18and25,hongLiBaoXianJinELayout1_25,hongLiBaoXianJinELayout1_18;
		private TextView hongLiBaoXianJinE1_25, hongLiBaoXianJinE2_18,levelHongLi18and25;
		private TextView hongLiBaoXianJinE1_2, hongLiBaoXianJinE2_2,
				hongLiBaoXianJinE3_2, hongLiBaoXianJinE4_2, hongLiBaoXianJinE5_2;

		private TextView levelHongLiTV;

		// 第三部分
		private TextView baoZhangtext2;
		// 第四部分
		private LinearLayout before18,after18,zhuFuLinear;
		@SuppressWarnings("unused")
		private TextView fengXianBaoZhangJin
		        ,zhuFuJin;

		// 数据
		private String sex;
		private String age;
		private String period;
		private String year;
		// 等级
		private String level;
		// 保额
		private String baoE;
		// 保费
		private double zhuBaoFeiD, totalBaoFeiD;
		private View view = null;
		private View topView = null;
		private View bottomView = null;
		private ArrayList<Entity>  fanHuanJin;
		private Map<Integer, String> map;
		private TextView[] fanHuanJinList;
		private ZunYuRSService zyrsService;
		private ArrayList<HongLi> hongLiMap;
		private Boolean fanHuanJinAnimationIsFinish = true;

		// // 值

		private AlertDialog dialog;

		@Override
		protected void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			// 设置横屏，全屏，无标题
			requestWindowFeature(Window.FEATURE_NO_TITLE);
			setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
			getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
					WindowManager.LayoutParams.FLAG_FULLSCREEN);
			setContentView(R.layout.ps_zunyurenshengshow);

			// 初始化控件
			initView();
			// 初始化数据
			sex = this.getIntent().getStringExtra("sex");
			age = this.getIntent().getStringExtra("age");
			period = this.getIntent().getStringExtra("period");
			year = this.getIntent().getStringExtra("year");
			baoE = this.getIntent().getStringExtra("baoE");
			level = this.getIntent().getStringExtra("level");
			
			zhuBaoFeiD = this.getIntent().getDoubleExtra("zhuBaoFeiD", 0);
			totalBaoFeiD = Arithmetic4Double.round(
					Arithmetic4Double.multi(zhuBaoFeiD, Double.valueOf(period)),Define.Round2);
			
			
			scJin.setText(""+Arithmetic4Double.round(Arithmetic4Double.multi(totalBaoFeiD, 0.02),Define.Round2));

//			 Toast.makeText(getApplicationContext(), year+"!!",
//						 Toast.LENGTH_SHORT).show();

			initAnim();

			// 初始化悬浮按钮
			initFloatView();

			zyrsService = new ZunYuRSService();
			//获取数据
			map = zyrsService.findFanHuanJin(
					Double.valueOf(this.getIntent().getStringExtra("baoE")),
					Integer.valueOf(this.getIntent().getStringExtra("age")));
			fanHuanJinList = initFanHuanJin();
			// 第二部分初始化
			hongli = new HongLi();
			hongli.setSex(sex);
			hongli.setAge(Integer.valueOf(age));
			hongli.setYear(Integer.valueOf(period));
			hongli.setFenShu(Integer.valueOf(baoE));
			hongli.setLevel(level);
			
			hongLiMap = zyrsService.findHongLi(hongli,this,Define.DB_Type_BaoFei_ZunYuRenSheng);
			levelHongLiTV.setText(zyrsService.getWanNengZhangHuLevel(zyrsService.getLevel(level)));
			levelHongLi18and25.setText(zyrsService.getWanNengZhangHuLevel(zyrsService
					.getLevel(level)));
			// 累积生存金
			// setLeiJiShengCunJin();
			// 万能账户
			setHongLiBXJEData();
			setHongLiBXJEData18and25();
			//祝福金的数值设置
			zhuFuJin.setText(Arithmetic4Double.multi(Double.valueOf(baoE), 0.5)+Define.WanYuan);
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

			// zhongShenFenHong = (LinearLayout)
			// findViewById(R.id.zhongShenFenHong1_2);
			// zhongShenBaoZhang = (LinearLayout)
			// findViewById(R.id.zhongShenBaoZhang1_4);
			// zhongShenFanHuan = (LinearLayout)
			// findViewById(R.id.zhongShenFanHuan1_1);
			// tiQianYangLao = (LinearLayout) findViewById(R.id.tiQianYangLao1_3);
			//
			// zhongShenFenHong.setVisibility(View.INVISIBLE);
			// zhongShenBaoZhang.setVisibility(View.INVISIBLE);
			// zhongShenFanHuan.setVisibility(View.INVISIBLE);
			// tiQianYangLao.setVisibility(View.INVISIBLE);

			// 控件的初始化和点击监听
			// 第一部分初始化 
			yingJuYiShengTitle = (LinearLayout) findViewById(R.id.yingJuYiShengTitle);
			fanHuanJinL = (RelativeLayout) findViewById(R.id.fanHuanJinL);
			fanHuanJinL.setVisibility(View.INVISIBLE);
			
			text_1 = (LinearLayout) findViewById(R.id.text_1);
			text_1.setVisibility(View.INVISIBLE);
			text_2 = (LinearLayout) findViewById(R.id.text_2);
			text_2.setVisibility(View.GONE);
//			jianKangTitle = (TextView) findViewById(R.id.huShenFuJianKangTitle);
//			jianKangtext1 = (TextView) findViewById(R.id.huShenFuJianKangText1);
//			jianKangtext2 = (TextView) findViewById(R.id.huShenFuJianKangText2);
//			yangLaoJin = (TextView) findViewById(R.id.yangLaoJin);

			
			yingJuYiShengTitle.setVisibility(View.INVISIBLE);
			shengcunjinLinear = (LinearLayout) findViewById(R.id.shengcunjinLinear);
			shengcunjinLinear.setVisibility(View.INVISIBLE);
			wanNengZhangHuLinaer2 = (LinearLayout) findViewById(R.id.wanNengZhangHuLinaer2);
			wanNengZhangHuLinaer2.setVisibility(View.GONE);
//			jianKangTitle.setVisibility(View.INVISIBLE);
//			jianKangtext1.setVisibility(View.INVISIBLE);
//			jianKangtext2.setVisibility(View.INVISIBLE);

			// 第二部分初始化
			fengHongLinear = (LinearLayout) findViewById(R.id.fengHongLinear);
//			huShenFuFenHongText2 = (LinearLayout) findViewById(R.id.huShenFuFenHongText2);
			wanNengZhangHuLinaer = (LinearLayout) findViewById(R.id.wanNengZhangHuLinaer);
//			fengHongLinear.setVisibility(View.INVISIBLE);
//			huShenFuFenHongText2.setVisibility(View.INVISIBLE);
			wanNengZhangHuLinaer.setVisibility(View.INVISIBLE);

			shouYiWenJian = (LinearLayout) findViewById(R.id.shouYiWenJian);
//			shouYiWenJian2 = (TextView) findViewById(R.id.shouYiWenJian2);
			shouYiWenJian.setVisibility(View.INVISIBLE);
//			shouYiWenJian2.setVisibility(View.INVISIBLE);
			
			zhongShenFenHongLinear = (LinearLayout) findViewById(R.id.zhongShenFenHongLinear);
			
			
			
			// 以便点击事件
			hongLiBaoXianJinELayout1_2 = (LinearLayout) findViewById(R.id.hongLiBaoXianJinELayout1_2);
			hongLiBaoXianJinELayout2_2 = (LinearLayout) findViewById(R.id.hongLiBaoXianJinELayout2_2);
			hongLiBaoXianJinELayout3_2 = (LinearLayout) findViewById(R.id.hongLiBaoXianJinELayout3_2);
			hongLiBaoXianJinELayout4_2 = (LinearLayout) findViewById(R.id.hongLiBaoXianJinELayout4_2);
			hongLiBaoXianJinELayout5_2 = (LinearLayout) findViewById(R.id.hongLiBaoXianJinELayout5_2);

			zhongShenFenHongLinear18and25 = (LinearLayout) findViewById(R.id.zhongShenFenHongLinear18and25);
			hongLiBaoXianJinE18and25 = (LinearLayout) findViewById(R.id.hongLiBaoXianJinE18and25);
			hongLiBaoXianJinELayout1_25 = (LinearLayout) findViewById(R.id.hongLiBaoXianJinELayout1_25);
			hongLiBaoXianJinELayout1_18 = (LinearLayout) findViewById(R.id.hongLiBaoXianJinELayout1_18);
			levelHongLi18and25 = (TextView) findViewById(R.id.levelHongLi18and25);
			hongLiBaoXianJinE1_25 = (TextView) findViewById(R.id.hongLiBaoXianJinE1_25);
			hongLiBaoXianJinE2_18 = (TextView) findViewById(R.id.hongLiBaoXianJinE2_18);
			
			hongLiBaoXianJinE_2 = (LinearLayout) findViewById(R.id.hongLiBaoXianJinE_2);
			hongLiBaoXianJinE1_2 = (TextView) findViewById(R.id.hongLiBaoXianJinE1_2);
			hongLiBaoXianJinE2_2 = (TextView) findViewById(R.id.hongLiBaoXianJinE2_2);
			hongLiBaoXianJinE3_2 = (TextView) findViewById(R.id.hongLiBaoXianJinE3_2);
			hongLiBaoXianJinE4_2 = (TextView) findViewById(R.id.hongLiBaoXianJinE4_2);
			hongLiBaoXianJinE5_2 = (TextView) findViewById(R.id.hongLiBaoXianJinE5_2);
			
			scJin= (TextView) findViewById(R.id.scJin);
			

			zhongShenFenHongLinear.setOnClickListener(this);
			zhongShenFenHongLinear18and25.setOnClickListener(this);
			hongLiBaoXianJinE18and25.setOnClickListener(this);
			hongLiBaoXianJinELayout1_25.setOnClickListener(this);
			hongLiBaoXianJinELayout1_18.setOnClickListener(this);
			hongLiBaoXianJinE18and25.setVisibility(View.INVISIBLE);
			hongLiBaoXianJinELayout1_25.setVisibility(View.INVISIBLE);
			hongLiBaoXianJinELayout1_18.setVisibility(View.INVISIBLE);
			hongLiBaoXianJinE_2.setOnClickListener(this);
			hongLiBaoXianJinELayout1_2.setOnClickListener(this);
			hongLiBaoXianJinELayout2_2.setOnClickListener(this);
			hongLiBaoXianJinELayout3_2.setOnClickListener(this);
			hongLiBaoXianJinELayout4_2.setOnClickListener(this);
			hongLiBaoXianJinELayout5_2.setOnClickListener(this);
			hongLiBaoXianJinE_2.setVisibility(View.INVISIBLE);
			hongLiBaoXianJinELayout1_2.setVisibility(View.INVISIBLE);
			hongLiBaoXianJinELayout2_2.setVisibility(View.INVISIBLE);
			hongLiBaoXianJinELayout3_2.setVisibility(View.INVISIBLE);
			hongLiBaoXianJinELayout4_2.setVisibility(View.INVISIBLE);
			hongLiBaoXianJinELayout5_2.setVisibility(View.INVISIBLE);
			
			levelHongLiTV = (TextView) this.findViewById(R.id.levelHongLi);

			// 第三部分初始化，并设置为不可见
			gaoErHuanBen = (LinearLayout) findViewById(R.id.gaoErHuanBen);
//			gaoErHuanBen2 = (TextView) findViewById(R.id.gaoErHuanBen2);
			baoZhangtext2 = (TextView) findViewById(R.id.huShenFuBaoZhangText2);
			
			gaoErHuanBen.setVisibility(View.INVISIBLE);
//			gaoErHuanBen2.setVisibility(View.INVISIBLE);
			baoZhangtext2.setVisibility(View.INVISIBLE);

			// 第四部分初始化，并设置为不可见
			yiWaiTitle = (LinearLayout) findViewById(R.id.huShenFuYiWaiTitle);
//			huShenFuYiWaiText1 = (TextView) findViewById(R.id.huShenFuYiWaiText1);
			fengXianBaoZhangJin = (TextView) findViewById(R.id.fengXianBaoZhangJin);
			zhuFuJin = (TextView) findViewById(R.id.zhuFuJin);
			zhuFuLinear = (LinearLayout) findViewById(R.id.zhuFuLinear);
			after18 = (LinearLayout) findViewById(R.id.after18);
			before18 = (LinearLayout) findViewById(R.id.before18);
			zhuFuLinear.setVisibility(View.INVISIBLE);
			before18.setVisibility(View.GONE);
			yiWaiTitle.setVisibility(View.INVISIBLE);
//			huShenFuYiWaiText1.setVisibility(View.INVISIBLE);
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
			// 快速向下移动
			xiangXiaYiDongFast = AnimationUtils.loadAnimation(getApplicationContext(),
					R.anim.ps_shouhuxshow_anim_xxtranslate_fast);
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

		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			// 左右屏幕点击事件
			case R.id.RightLinear:
				next();
//				if (view != null) {
//					view.setVisibility(View.VISIBLE);
//				}
				break;

			case R.id.leftLinear:
				previous();
//				if (view != null) {
//					view.setVisibility(View.VISIBLE);
//				}
				break;
				
			case R.id.productZeRen:
				Intent intent2 = new Intent(this, ImgAnimationA.class);
				Bundle b2 = new Bundle();
				b2.putInt("maxPageCount", 8);
				b2.putString("url", "product/zunyurensheng/yanshi/chanpinzeren");
				b2.putString("format", "gif");
				b2.putString("screenFlag","yanshi");
				b2.putString("tiaokuan", "zunyurensheng");
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
			case R.id.liCaiYouShi:
				Intent intent4 = new Intent(this, ImgAnimationA.class);
				Bundle b4 = new Bundle();
				b4.putInt("maxPageCount", 9);
				b4.putString("url", "product/shijitianshi/yanshi/licaiyoushi");
				b4.putString("format", "gif");
				b4.putString("screenFlag","yanshi");
				intent4.putExtras(b4);
				startActivity(intent4);
				break;
			case R.id.juCaiBaoTeSe:
				Intent intent8 = new Intent(this, ImgAnimationA.class);
				Bundle b8 = new Bundle();
				b8.putInt("maxPageCount", 5);
				b8.putString("url", "product/zunyurensheng/yanshi/jucaibaozhanghutedian");
				b8.putString("format", "gif");
				b8.putString("screenFlag","yanshi");
				intent8.putExtras(b8);
				startActivity(intent8);
				break;
			// 该页面有点不同，视屏选择页面
			case R.id.wenTiJieDa:
				Intent intent5 = new Intent(this, ProductWenTiJDA.class);
				startActivity(intent5);
				break;
//			case R.id.fuJiaBaoXian:
//				Intent intent6 = new Intent(this, ImgAnimationA.class);
//				Bundle b6 = new Bundle();
//				b6.putInt("maxPageCount", 4);
//				b6.putString("url", "product/shijitianshi/yanshi/fujiabaoxian");
//				b6.putString("format", "gif");
//				b6.putString("screenFlag","yanshi");
//				intent6.putExtras(b6);
//				startActivity(intent6);
//				Toast.makeText(getApplicationContext(), "无资料", Toast.LENGTH_LONG).show();
//				break;
			case R.id.canBaoTiShi:
				Intent intent7 = new Intent(this, ImgAnimationA.class);
				Bundle b7 = new Bundle();
				b7.putInt("maxPageCount", 4);
				b7.putString("url", "product/zunyurensheng/yanshi/canbaotishi");
				b7.putString("format", "gif");
				b7.putString("screenFlag","yanshi");
				intent7.putExtras(b7);
				startActivity(intent7);
				break;

			// 第二部分linear点击事件
			case R.id.hongLiBaoXianJinE18and25:
				showHongLiFanHuanLevel18and25();
				break;
			case R.id.hongLiBaoXianJinELayout1_25:
				showHongLiFanHuan(23, 35);
				break;
			case R.id.hongLiBaoXianJinELayout1_18:
				showHongLiFanHuan(0, 22);
				break;
			case R.id.hongLiBaoXianJinE_2:
				showHongLiFanHuanLevel();
				break;
			case R.id.hongLiBaoXianJinELayout1_2:
				showHongLiFanHuan(80, 105);
				break;
			case R.id.hongLiBaoXianJinELayout2_2:
				showHongLiFanHuan(70, 79);
				break;
			case R.id.hongLiBaoXianJinELayout3_2:
				showHongLiFanHuan(60, 69);
				break;
			case R.id.hongLiBaoXianJinELayout4_2:
				showHongLiFanHuan(41, 59);
				break;
			case R.id.hongLiBaoXianJinELayout5_2:
				showHongLiFanHuan(0, 40);
				break;
			default:
				break;
			}
		}

		public void DialogSelect(String dialogLevel) {
			hongli.setLevel(dialogLevel);
			levelHongLiTV.setText(zyrsService.getWanNengZhangHuLevel(zyrsService
					.getLevel(dialogLevel)));
			hongLiMap = zyrsService.findHongLi(hongli,this,Define.DB_Type_BaoFei_ZunYuRenSheng);
			setHongLiBXJEData();
			dialog.cancel();
			setFloatingVisible();
		}
		public void DialogSelect18and25(String dialogLevel) {
			hongli.setLevel(dialogLevel);
			levelHongLi18and25.setText(zyrsService.getWanNengZhangHuLevel(zyrsService
					.getLevel(dialogLevel)));
			hongLiMap = zyrsService.findHongLi(hongli,this,Define.DB_Type_BaoFei_ZunYuRenSheng);
			setHongLiBXJEData18and25();
			dialog.cancel();
			setFloatingVisible();
		}

		private void setHongLiBXJEData18and25() {
			hongLiBaoXianJinE2_18.setText(zyrsService.getHongLiByAge(hongLiMap, 18));
			hongLiBaoXianJinE1_25.setText(zyrsService.getHongLiByAge(hongLiMap, 25));
		}

		public void setHongLiBXJEData() {
			hongLiBaoXianJinE1_2.setText(zyrsService.getHongLiByAge(hongLiMap, 80));
			hongLiBaoXianJinE2_2.setText(zyrsService.getHongLiByAge(hongLiMap, 70));
			hongLiBaoXianJinE3_2.setText(zyrsService.getHongLiByAge(hongLiMap, 60));
			hongLiBaoXianJinE4_2.setText(zyrsService.getHongLiByAge(hongLiMap, 50));
			hongLiBaoXianJinE5_2.setText(zyrsService.getHongLiByAge(hongLiMap, 40));
		}

		public void setFloatingVisible() {
			if (view != null) {
				view.setVisibility(View.VISIBLE);
			}
			if (step >= topFloat) {
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
					.setTitle("聚财宝万能账户累积红利等级")
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
		public void showHongLiFanHuanLevel18and25() {
			final String[] items = new String[] { Define.Level_Low_display,
					Define.Level_Middle_display, Define.Level_High_display };
			
			// 隐藏悬浮框
			setFloatingInvisible();
			dialog = new AlertDialog.Builder(this)
			.setTitle("聚财宝万能账户累积红利等级")
			.setSingleChoiceItems(items, 0,
					new DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog,
						int which) {
					switch (which) {
					case 0:
						String dialogLevel = Define.Level_Low;
						// 点击选项后的方法，更新数据，并且退出对话框，显示悬浮框
						DialogSelect18and25(dialogLevel);
						break;
					case 1:
						dialogLevel = Define.Level_Middle;
						DialogSelect18and25(dialogLevel);
						break;
					case 2:
						dialogLevel = Define.Level_High;
						DialogSelect18and25(dialogLevel);
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

		//万能账户列表数据查询方法
		public void showHongLiFanHuan(int start, int end) {
			setFloatingInvisible();
			String items[] = ZunYuRSService.findHongLi(hongLiMap, start, end);
			AlertDialog dialog = new AlertDialog.Builder(this).setTitle("聚财宝万能账户累积")
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
			createButtonLeft();
			createButtonRight();
			createTopFloatView();
			createBottomFloatView();

		}

		/**
		 * 创建中间悬浮按钮
		 */
		private void createCenterFloatView() {
			TextView tv = new TextView(this);
			tv.setTextSize(8);

			TextPaint tp = tv.getPaint();
			tp.setFakeBoldText(true);

			LayoutInflater inflater = (LayoutInflater) this
					.getSystemService(LAYOUT_INFLATER_SERVICE);
			view = inflater.inflate(R.layout.ps_zunyurs_center_float, null);

			TextView floatText1 = (TextView) view.findViewById(R.id.floatText1);
			TextView floatText2 = (TextView) view.findViewById(R.id.floatText2);
			TextView floatText3 = (TextView) view.findViewById(R.id.floatText3);
			floatText1.setText("交" + period + "年");
			floatText2.setText("每年" + E2DoubleUtil.E2Double(zhuBaoFeiD)+Define.Yuan);
			floatText3.setText("共"+
					E2DoubleUtil.E2Double(totalBaoFeiD)
					+ "元");

			wmParams.width = DensityUtil.dip2px(this, 70);
			wmParams.height = DensityUtil.dip2px(this, 40);

			// 调整悬浮窗口
			wmParams.gravity = Gravity.CENTER;
			wm.addView(view, wmParams);
		}

		/**
		 * 创建上边悬浮按钮
		 */
		private void createTopFloatView() {
			LayoutInflater inflater = (LayoutInflater) this
					.getSystemService(LAYOUT_INFLATER_SERVICE);
			topView = inflater.inflate(R.layout.ps_zunyurensheng_top_float, null);
			topView.setVisibility(View.GONE);

			wmParams.width = DensityUtil.dip2px(this, 100);
			wmParams.height = DensityUtil.dip2px(this, 120);

			wmParams.y = DensityUtil.dip2px(this, 30);

			// 调整悬浮窗口
			wmParams.gravity = Gravity.CENTER_HORIZONTAL | Gravity.TOP;
			wm.addView(topView, wmParams);
		}
		
		//设置左右悬浮按钮
			private View leftView = null;
			private View rightView = null;
			private TextView leftBtn = null;
			private TextView rightBtn = null;

		/**
		 * 创建左边按钮
		 */
		private void createButtonLeft()
		{
			 LayoutInflater inflater = (LayoutInflater) this
			 .getSystemService(LAYOUT_INFLATER_SERVICE);
			 leftView = inflater.inflate(R.layout.ps_shijitianshi_bottom_left,
			 null);
			 leftBtn = (TextView) leftView.findViewById(R.id.leftBtn);
			 leftBtn.setOnClickListener(this);
			 leftView.setVisibility(View.VISIBLE);
			 // 设置悬浮窗口长宽数据
			 wmParams.x = 0;
			 wmParams.y = 0;
			 wmParams.width = DensityUtil.dip2px(this, 40);
			 wmParams.height = DensityUtil.dip2px(this, 40);
			 // 调整悬浮窗口
			 wmParams.gravity = Gravity.LEFT | Gravity.CENTER_VERTICAL;
			 wm.addView(leftView, wmParams);
		}

		/**
		 * 创建右边按钮
		 */
		private void createButtonRight()
		{
			LayoutInflater inflater = (LayoutInflater) this
					.getSystemService(LAYOUT_INFLATER_SERVICE);
			rightView = inflater.inflate(R.layout.ps_shijitianshi_bottom_right,
					null);
			rightBtn = (TextView) rightView.findViewById(R.id.rightBtn);
			rightBtn.setOnClickListener(this);
			rightBtn.setVisibility(View.VISIBLE);
			// 设置悬浮窗口长宽数据
			// 以屏幕左上角为原点，设置x、y初始值
			wmParams.x = 0;
			wmParams.y = 0;
			wmParams.width = DensityUtil.dip2px(this, 40);
			wmParams.height = DensityUtil.dip2px(this, 40);
			// 调整悬浮窗口
			wmParams.gravity = Gravity.RIGHT | Gravity.CENTER_VERTICAL;
			// wm.addView(tv, wmParams);
			wm.addView(rightView, wmParams);
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

			bottomView = inflater.inflate(R.layout.ps_zunyurensheng_bottom_float, null);

			// 下部分悬浮框初始化TextView,并设置监听
			juCaiBaoTeSe = (TextView) bottomView.findViewById(R.id.juCaiBaoTeSe);
			productZeRen = (TextView) bottomView.findViewById(R.id.productZeRen);
			zhiChanGuanLi = (TextView) bottomView.findViewById(R.id.zhiChanGuanLi);
			liCaiYouShi = (TextView) bottomView.findViewById(R.id.liCaiYouShi);
			wenTiJieDa = (TextView) bottomView.findViewById(R.id.wenTiJieDa);
			canBaoTiShi = (TextView) bottomView.findViewById(R.id.canBaoTiShi);
			productZeRen.setOnClickListener(this);
			zhiChanGuanLi.setOnClickListener(this);
			liCaiYouShi.setOnClickListener(this);
			juCaiBaoTeSe.setOnClickListener(this);
			wenTiJieDa.setOnClickListener(this);
//			fuJiaBaoXian.setOnClickListener(this);
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
			wm.addView(bottomView, wmParams);
		}
		
		public TextView[] initFanHuanJin() {

			// 实例化一个10个TextView的数组
			TextView fanHuanJinS[] = new TextView[72];

			fanHuanJinS[0] = (TextView) findViewById(R.id.fan1);
			fanHuanJinS[1] = (TextView) findViewById(R.id.fan2);
			fanHuanJinS[2] = (TextView) findViewById(R.id.fan3);
			fanHuanJinS[3] = (TextView) findViewById(R.id.fan4);
			fanHuanJinS[4] = (TextView) findViewById(R.id.fan5);
			fanHuanJinS[5] = (TextView) findViewById(R.id.fan6);
			fanHuanJinS[6] = (TextView) findViewById(R.id.fan7);
			fanHuanJinS[7] = (TextView) findViewById(R.id.fan8);
			fanHuanJinS[8] = (TextView) findViewById(R.id.fan9);
			fanHuanJinS[9] = (TextView) findViewById(R.id.fan10);
			fanHuanJinS[10] = (TextView) findViewById(R.id.fan11);
			fanHuanJinS[11] = (TextView) findViewById(R.id.fan12);
			fanHuanJinS[12] = (TextView) findViewById(R.id.fan13);
			fanHuanJinS[13] = (TextView) findViewById(R.id.fan14);
			fanHuanJinS[14] = (TextView) findViewById(R.id.fan15);
			fanHuanJinS[15] = (TextView) findViewById(R.id.fan16);
			fanHuanJinS[16] = (TextView) findViewById(R.id.fan17);
			fanHuanJinS[17] = (TextView) findViewById(R.id.fan18);
			fanHuanJinS[18] = (TextView) findViewById(R.id.fan19);
			fanHuanJinS[19] = (TextView) findViewById(R.id.fan20);
			fanHuanJinS[20] = (TextView) findViewById(R.id.fan21);
			fanHuanJinS[21] = (TextView) findViewById(R.id.fan22);
			fanHuanJinS[22] = (TextView) findViewById(R.id.fan23);
			fanHuanJinS[23] = (TextView) findViewById(R.id.fan24);
			fanHuanJinS[24] = (TextView) findViewById(R.id.fan25);
			fanHuanJinS[25] = (TextView) findViewById(R.id.fan26);
			fanHuanJinS[26] = (TextView) findViewById(R.id.fan27);
			fanHuanJinS[27] = (TextView) findViewById(R.id.fan28);
			fanHuanJinS[28] = (TextView) findViewById(R.id.fan29);
			fanHuanJinS[29] = (TextView) findViewById(R.id.fan30);
			fanHuanJinS[30] = (TextView) findViewById(R.id.fan31);
			fanHuanJinS[31] = (TextView) findViewById(R.id.fan32);
			fanHuanJinS[32] = (TextView) findViewById(R.id.fan33);
			fanHuanJinS[33] = (TextView) findViewById(R.id.fan34);
			fanHuanJinS[34] = (TextView) findViewById(R.id.fan35);
			fanHuanJinS[35] = (TextView) findViewById(R.id.fan36);
			fanHuanJinS[36] = (TextView) findViewById(R.id.fan37);
			fanHuanJinS[37] = (TextView) findViewById(R.id.fan38);
			fanHuanJinS[38] = (TextView) findViewById(R.id.fan39);
			fanHuanJinS[39] = (TextView) findViewById(R.id.fan40);
			fanHuanJinS[40] = (TextView) findViewById(R.id.fan41);
			fanHuanJinS[41] = (TextView) findViewById(R.id.fan42);
			fanHuanJinS[42] = (TextView) findViewById(R.id.fan43);
			fanHuanJinS[43] = (TextView) findViewById(R.id.fan44);
			fanHuanJinS[44] = (TextView) findViewById(R.id.fan45);
			fanHuanJinS[45] = (TextView) findViewById(R.id.fan46);
			fanHuanJinS[46] = (TextView) findViewById(R.id.fan47);
			fanHuanJinS[47] = (TextView) findViewById(R.id.fan48);
			fanHuanJinS[48] = (TextView) findViewById(R.id.fan49);
			fanHuanJinS[49] = (TextView) findViewById(R.id.fan50);
			fanHuanJinS[50] = (TextView) findViewById(R.id.fan51);
			fanHuanJinS[51] = (TextView) findViewById(R.id.fan52);
			fanHuanJinS[52] = (TextView) findViewById(R.id.fan53);
			fanHuanJinS[53] = (TextView) findViewById(R.id.fan54);
			fanHuanJinS[54] = (TextView) findViewById(R.id.fan55);
			fanHuanJinS[55] = (TextView) findViewById(R.id.fan56);
			fanHuanJinS[56] = (TextView) findViewById(R.id.fan57);
			fanHuanJinS[57] = (TextView) findViewById(R.id.fan58);
			fanHuanJinS[58] = (TextView) findViewById(R.id.fan59);
			fanHuanJinS[59] = (TextView) findViewById(R.id.fan60);
			fanHuanJinS[60] = (TextView) findViewById(R.id.fan61);
			fanHuanJinS[61] = (TextView) findViewById(R.id.fan62);
			fanHuanJinS[62] = (TextView) findViewById(R.id.fan63);
			fanHuanJinS[63] = (TextView) findViewById(R.id.fan64);
			fanHuanJinS[64] = (TextView) findViewById(R.id.fan65);
			fanHuanJinS[65] = (TextView) findViewById(R.id.fan66);
			fanHuanJinS[66] = (TextView) findViewById(R.id.fan67);
			fanHuanJinS[67] = (TextView) findViewById(R.id.fan68);
			fanHuanJinS[68] = (TextView) findViewById(R.id.fan69);
			fanHuanJinS[69] = (TextView) findViewById(R.id.fan70);
			fanHuanJinS[70] = (TextView) findViewById(R.id.fan71);
			fanHuanJinS[71] = (TextView) findViewById(R.id.fan72);

			for (int i = 0; i < 72; i++) {
				// 设置不可见
				fanHuanJinS[i].setVisibility(View.INVISIBLE);
			}
			return fanHuanJinS;
		}


		// 健康动画步骤
		final int jianKang_step1 = 1;
		// 分红动画步骤
		final int fenHong_step1 = 2;
		// 保障动画步骤
		final int yiWai_step1 = 3;
		// 意外动画步骤
		final int baoZhang_step1 = 4;
		
		final int zuoxia_1 = 5;
		final int zuoxia_2 = 6; 
		
		//方块的动画
		final int jianKang_step2 = 7;

		final int jianKang_step3 = 8;
		//右下角出现
		
		final int youxia_1 = 9;
		final int youxia_2 = 10;
		
		final  int text_3 =11;
		final int wanneng = 12;
		final int wanneng2 = 13;
		//聚财宝万能账户18岁到25岁的条目动画
		final int fenHong_step2 = 14;
		final int fenHong_step9 = 16;
		final int fenHong_step10 = 15;
		
		
		//聚财宝万能账户40岁后的条目动画
		final int fenHong_step3 = 17;
		final int fenHong_step4 = 22;
		final int fenHong_step5 = 21;
		final int fenHong_step6 = 20;
		final int fenHong_step7 = 19;
		final int fenHong_step8 = 18;
		
		// 上部分悬浮框
		final int topFloat = 23;
		
//		final int yiWai_step2 = 24;
//		final int yiWai_step3 = 25;
//		final int step26 = 26;
//		final int step27 = 27;
		
		// 下部分悬浮框
		final int bottomFloat = 24;

		public static int step = 0;

		public void next() {
			step++;
			switch (step) {
			case wanneng2:
				wanNengZhangHuLinaer2.startAnimation(xiangXiaYiDong);
				wanNengZhangHuLinaer2.setVisibility(View.VISIBLE);
				wanNengZhangHuLinaer.setVisibility(View.GONE);
				break;
			case wanneng:
				wanNengZhangHuLinaer.startAnimation(xiangXiaYiDong);
				wanNengZhangHuLinaer.setVisibility(View.VISIBLE);
				break;
			case text_3:
				baoZhangtext2.startAnimation(xiangXiaYiDong);
				baoZhangtext2.setVisibility(View.VISIBLE);
				break;
			case youxia_2:
				shengcunjinLinear.startAnimation(xiangXiaYiDong);
				shengcunjinLinear.setVisibility(View.VISIBLE);
				break;
			case zuoxia_1:
				before18.startAnimation(xiangXiaYiDong);
				after18.setVisibility(View.GONE);
				before18.setVisibility(View.VISIBLE);
				break;
			case zuoxia_2:
				before18.setVisibility(View.GONE);
				after18.startAnimation(xiangXiaYiDong);
				after18.setVisibility(View.VISIBLE);
				break;
			case baoZhang_step1:
				// step++;
				
				shouYiWenJian.startAnimation(xiangZuoYiDong);
				shouYiWenJian.setVisibility(View.VISIBLE);
//				gaoErHuanBen2.startAnimation(xiangXiaYiDong);
//				gaoErHuanBen2.setVisibility(View.VISIBLE);
				break;
//			case step27:
////				baoZhangtext2.startAnimation(xiangShangYiDong);
////				baoZhangtext2.setVisibility(View.VISIBLE);
//				// step++;
//				break;
			case jianKang_step1:
				// step++;
				yiWaiTitle.startAnimation(xiangYouYiDong);
				yiWaiTitle.setVisibility(View.VISIBLE);
//				yingJuYiShengTitle.startAnimation(suoFangAndYiDong);
//				yingJuYiShengTitle.setVisibility(View.VISIBLE);
				break;
				//第一部分方块的显示动画
			case jianKang_step2:
				if (!fanHuanJinAnimationIsFinish) {
					step--;
					break;
				}
				fanHuanJinAnimationIsFinish = false;
				new Thread(new Runnable() {
					@Override
					public void run() {
						try {
							Thread.sleep(6000);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						fanHuanJinAnimationIsFinish = true;
					}
				}).start();
				fanHuanJinL.setVisibility(View.VISIBLE);
				text_1.setVisibility(View.VISIBLE);
				for(int i=0;i<fanHuanJinList.length;i++){
					fanHuanJinList[i].setVisibility(View.INVISIBLE);
				}
				//3号修改
				zyrsService.showFanHuanJin61(fanHuanJinList, map, xiangXiaYiDongFast,
						rightBtn,leftBtn);
				break;
				//第二部分方块的显示动画
			case jianKang_step3:
				if (!fanHuanJinAnimationIsFinish) {
					step--;
					break;
				}
				fanHuanJinAnimationIsFinish = false;
				new Thread(new Runnable() {
					@Override
					public void run() {
						try {
							Thread.sleep(2800);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						fanHuanJinAnimationIsFinish = true;
					}
				}).start();
				text_1.setVisibility(View.GONE);
				text_2.setVisibility(View.VISIBLE);
				zyrsService.showFanHuanJinMore61(fanHuanJinList, map,
						xiangXiaYiDongFast,rightBtn,leftBtn);
				break;
			case youxia_1:
				zhuFuLinear.setVisibility(View.VISIBLE);
				zhuFuLinear.startAnimation(xiangXiaYiDong);
				// step++;
//				wanNengZhangHuLinaer.startAnimation(jianBian);
//				wanNengZhangHuLinaer.setVisibility(View.VISIBLE);
				break;
			case topFloat:
				// step++;
				topView.setVisibility(View.VISIBLE);
				topView.startAnimation(xiangXiaYiDong);
				break;
			case fenHong_step1:
				
				yingJuYiShengTitle.startAnimation(xiangYouYiDong);
				yingJuYiShengTitle.setVisibility(View.VISIBLE);
//				shouYiWenJian.startAnimation(xiangShangYiDong);
//				shouYiWenJian.setVisibility(View.VISIBLE);
//				shouYiWenJian2.startAnimation(xiangShangYiDong);
//				shouYiWenJian2.setVisibility(View.VISIBLE);
				
				break;

			case fenHong_step2:
				fengHongLinear.setVisibility(View.GONE);
				zhongShenFenHongLinear18and25.setVisibility(View.VISIBLE);
				zhongShenFenHongLinear18and25.startAnimation(xiangZuoYiDong);
				
				//18到25岁的万能账户的动画
				hongLiBaoXianJinE18and25.setVisibility(View.VISIBLE);
				hongLiBaoXianJinE18and25.startAnimation(xiangZuoYiDong);
				break;
			case fenHong_step9:
				hongLiBaoXianJinELayout1_25.setVisibility(View.VISIBLE);
				hongLiBaoXianJinELayout1_25.startAnimation(xiangZuoYiDong);
				break;
			case fenHong_step10:
				hongLiBaoXianJinELayout1_18.setVisibility(View.VISIBLE);
				hongLiBaoXianJinELayout1_18.startAnimation(xiangZuoYiDong);
				break;
			case fenHong_step3:
				zhongShenFenHongLinear18and25.setVisibility(View.GONE);
				zhongShenFenHongLinear.setVisibility(View.VISIBLE);
				hongLiBaoXianJinE_2.setVisibility(View.VISIBLE);
				hongLiBaoXianJinE_2.startAnimation(suoFangBianDa);
				//40岁以后的万能账户的动画
				break;
			case fenHong_step4:
				hongLiBaoXianJinELayout1_2.setVisibility(View.VISIBLE);
				hongLiBaoXianJinELayout1_2.startAnimation(xiangZuoYiDong);
				break;
			case fenHong_step5:
				hongLiBaoXianJinELayout2_2.setVisibility(View.VISIBLE);
				hongLiBaoXianJinELayout2_2.startAnimation(xiangZuoYiDong);
				break;
			case fenHong_step6:
				hongLiBaoXianJinELayout3_2.setVisibility(View.VISIBLE);
				hongLiBaoXianJinELayout3_2.startAnimation(xiangZuoYiDong);
				break;
			case fenHong_step7:
				hongLiBaoXianJinELayout4_2.setVisibility(View.VISIBLE);
				hongLiBaoXianJinELayout4_2.startAnimation(xiangZuoYiDong);
				break;
			case fenHong_step8:
				hongLiBaoXianJinELayout5_2.setVisibility(View.VISIBLE);
				hongLiBaoXianJinELayout5_2.startAnimation(xiangZuoYiDong);
				break;
				
			case yiWai_step1:
				gaoErHuanBen.setVisibility(View.VISIBLE);
				gaoErHuanBen.startAnimation(xiangZuoYiDong);
//				yiWaiTitle.setVisibility(View.VISIBLE);
//				yiWaiTitle.startAnimation(jianBian);
//				huShenFuYiWaiText1.setVisibility(View.VISIBLE);
//				huShenFuYiWaiText1.startAnimation(jianBian);
				break;
//			case yiWai_step2:
//				after18.setVisibility(View.GONE);
//				before18.setVisibility(View.VISIBLE);
//				before18.startAnimation(xiangYouYiDong);
//				break;
//			case yiWai_step3:
//				before18.setVisibility(View.GONE);
//				after18.setVisibility(View.VISIBLE);
//				after18.startAnimation(xiangShangYiDong);
//				break;
//			case step26:
//
//				break;
		 // 下部悬浮框
			case bottomFloat:
				bottomView.setVisibility(View.VISIBLE);
				bottomView.startAnimation(jianBian);
				break;
			default:
				step--;
				break;

			}
			// 处理到最后一步

		}

		// 
		public void previous() {

			switch (step) {
			case wanneng2:
				step--;
				wanNengZhangHuLinaer.startAnimation(xiangXiaYiDong);
				wanNengZhangHuLinaer.setVisibility(View.VISIBLE);
				wanNengZhangHuLinaer2.setVisibility(View.GONE);
				break;
			case wanneng:
				step--;
				wanNengZhangHuLinaer.setVisibility(View.INVISIBLE);
				break;
			case text_3:
				step--;
				baoZhangtext2.setVisibility(View.INVISIBLE);
				break;
			case youxia_2:
				step--;
				shengcunjinLinear.setVisibility(View.INVISIBLE);
				break;
			case zuoxia_1:
				before18.setVisibility(View.INVISIBLE);
				step--;
				break;
			case zuoxia_2:
				after18.setVisibility(View.INVISIBLE);
				step--;
				break;
			case baoZhang_step1:
				step--;
				shouYiWenJian.setVisibility(View.INVISIBLE);
				break;
//			case step27:
//				step--;
//				baoZhangtext2.setVisibility(View.INVISIBLE);
//				break;
			case jianKang_step1:
				step--;
				yiWaiTitle.setVisibility(View.INVISIBLE);
				break;
				//第一部分方块的隐藏
			case jianKang_step2:
				step--;
//				fanHuanJinL.setVisibility(View.GONE);
				text_1.setVisibility(View.INVISIBLE);
				for(int i=0;i<fanHuanJinList.length;i++){
					fanHuanJinList[i].setVisibility(View.GONE);
				}
				break;
				//第二部分方块的隐藏
			case jianKang_step3:
				step--;
				text_2.setVisibility(View.GONE);
				text_1.setVisibility(View.VISIBLE);
				zyrsService.hideFanHuanJinMore61(fanHuanJinList, map,
						View.INVISIBLE);
				break;
			case youxia_1:
				step--;
				zhuFuLinear.setVisibility(View.INVISIBLE);
//				wanNengZhangHuLinaer.setVisibility(View.INVISIBLE);
				break;

			case topFloat:
				step--;
				topView.setVisibility(View.INVISIBLE);
				break;
			case fenHong_step1:
				step--;
				yingJuYiShengTitle.setVisibility(View.INVISIBLE);
//				shouYiWenJian.setVisibility(View.INVISIBLE);
//				shouYiWenJian2.setVisibility(View.INVISIBLE);
				
//				huShenFuFenHongText2.setVisibility(View.INVISIBLE);
//				fengHongLinear.setVisibility(View.INVISIBLE);
				break;
			case fenHong_step2:
				step--;
				fengHongLinear.setVisibility(View.VISIBLE);
				zhongShenFenHongLinear18and25.setVisibility(View.GONE);
				break;
			case fenHong_step9:
				step--;
				hongLiBaoXianJinELayout1_25.setVisibility(View.INVISIBLE);
				break;
			case fenHong_step10:
				step--;
				hongLiBaoXianJinELayout1_18.setVisibility(View.INVISIBLE);
				break;
			case fenHong_step3:
				step--;
				zhongShenFenHongLinear18and25.setVisibility(View.VISIBLE);
				zhongShenFenHongLinear.setVisibility(View.GONE);
				break;
			case fenHong_step4:
				step--;
				hongLiBaoXianJinELayout1_2.setVisibility(View.INVISIBLE);
				break;
			case fenHong_step5:
				step--;
				hongLiBaoXianJinELayout2_2.setVisibility(View.INVISIBLE);
				break;
			case fenHong_step6:
				step--;
				hongLiBaoXianJinELayout3_2.setVisibility(View.INVISIBLE);
				break;
			case fenHong_step7:
				step--;
				hongLiBaoXianJinELayout4_2.setVisibility(View.INVISIBLE);
				break;
			case fenHong_step8:
				step--;
				hongLiBaoXianJinELayout5_2.setVisibility(View.INVISIBLE);
				break;
			case yiWai_step1:
				step--;
				gaoErHuanBen.setVisibility(View.INVISIBLE);
				break;
//			case yiWai_step2:
//				step--;
//				yiWaiTitle.setVisibility(View.VISIBLE);
//				before18.setVisibility(View.INVISIBLE);
//				break;
//			case yiWai_step3:
//				step--;
//				before18.setVisibility(View.VISIBLE);
//				after18.setVisibility(View.GONE);
//				break;
//			case step26:
//				step--;
//				zhuFuLinear.setVisibility(View.INVISIBLE);
//				break;
			case bottomFloat:
				step--;
				bottomView.setVisibility(View.INVISIBLE);
				break;
			default:
//				step--;
				break;

			}

//			 防止一进入界面就下一步
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
			if (view != null) {
				view.setVisibility(View.VISIBLE);
			}
			if (topView != null) {
				topView.setVisibility(View.VISIBLE);
			}
			if (bottomView != null) {
				bottomView.setVisibility(View.VISIBLE);
			}
			if(leftBtn != null){
				leftBtn.setVisibility(View.GONE);
			}
			if(rightBtn != null){
				rightBtn.setVisibility(View.GONE);
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
			if(leftBtn != null){
				leftBtn.setVisibility(View.GONE);
			}
			if(rightBtn != null){
				rightBtn.setVisibility(View.GONE);
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
			if(leftBtn != null){
				leftBtn.setVisibility(View.GONE);
			}
			if(rightBtn != null){
				rightBtn.setVisibility(View.GONE);
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
			if(leftView != null){
				wm.removeView(leftView);
			}
			if(rightView != null){
				wm.removeView(rightView);
			}

			super.onDestroy();
		}

		@Override
		public void onBackPressed() {
//			Intent intent = new Intent(this, BaoXianProductA.class);
//			intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//			startActivity(intent);
			Intent intent4 = new Intent(this, ZunYuRenShengPlanA.class);
			startActivity(intent4);
			finish();
			step = 0;
		}

	}
