package com.baoxiao.activity.productshow.shijitianshi;

import java.util.ArrayList;
import java.util.Timer;
import com.baoxiao.R;
import com.baoxiao.activity.productshow.ImgAnimationA;
import com.baoxiao.model.Entity;
import com.baoxiao.model.HongLi;
import com.baoxiao.service.productshow.ShouHuXService;
import com.baoxiao.util.Arithmetic4Double;
import com.baoxiao.util.Define;
import com.baoxiao.util.DensityUtil;
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
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class ShiJiTSShowA extends Activity implements OnClickListener
{
	// 动画集及动画 
	private Animation xiangYouYiDong, xiangZuoYiDong, xiangXiaYiDong,
			xiangShangYiDong, yiDongAndJianBian, 
			jianBian, suoFang,
			suoFangBianDa, xuanZhuan, suoFangAndXuanZhuan, suoFangAndYiDong;
	private AnimationSet set;

	private boolean tagFloating = true;
	// 主界面及其他四个界面
	private LinearLayout main;
	private LinearLayout leftLinear;
	private LinearLayout rightLinear;
	private LinearLayout zhongShenBaoZhang, zhongShenFanHuan, zhongShenFenHong,
			tiQianYangLao;

	//设置左右悬浮按钮
	private View leftView = null;
	private View rightView = null;
	private TextView leftBtn;
	private TextView rightBtn;

	
	// 悬浮框
	private WindowManager wm = null;
	private WindowManager.LayoutParams wmParams = null;
	//上部悬浮框
//	private TextView topText1,topText2,topText3,topText4,topText5;
	// 下部悬浮框
	private TextView productZeRen, zhiChanGuanLi, LiCaiYouShi, wenTiJieDa,
			fuJiaBaoXian, canBaoTiShi;

	// 第一部分
	private LinearLayout ZhongShenFanHuan2_1;
	private LinearLayout LeiJinFanHuanShou4_1;
	private RelativeLayout fanHuanJinL;
	private LinearLayout baoDanLeiJiContent1, baoDanLeiJiContent2,
			baoDanLeiJiContent3, baoDanLeiJiContent4, baoDanLeiJiContent5;
	private TextView ZhongShenFanHuan2_text, baoDanLeiJiContent1_shengcunjin,
			baoDanLeiJiContent2_shengcunjin, baoDanLeiJiContent3_shengcunjin,
			baoDanLeiJiContent4_shengcunjin, baoDanLeiJiContent5_shengcunjin;
	private LinearLayout baoDanLeiJiTitle;

	// 第二部分
	private HongLi hongli;
	private LinearLayout baoDanFenHongJinLinear1, zhongShenFenHongLinear,
			hongLiBaoXianJinELayout1_2, hongLiBaoXianJinELayout2_2,
			hongLiBaoXianJinELayout3_2, hongLiBaoXianJinELayout4_2,
			hongLiBaoXianJinELayout5_2, hongLiBaoXianJinElayout6_2,
			hongLiBaoXianJinE_2;
	private TextView hongLiBaoXianJinE1_2, hongLiBaoXianJinE2_2,
			hongLiBaoXianJinE3_2, hongLiBaoXianJinE4_2, hongLiBaoXianJinE5_2;

	private TextView levelHongLiTV;

	// 第三部分
	// private TextView tiQianYangLaoText2_3,tiQianYangLaoText4_3,
	// tiQianYangLaoText6_3;
	// private ImageView tiQianYangLao3_3,tiQianYangLao5_3;
	private LinearLayout renShouBaoXianJinLinear1, renShouBaoXianJinLinear2,
			reShouBaoXianJinLinear3, reShouBaoXianJinLinear4,
			tiQianYangLaoLinear7_3;
	private TextView shenGuBaoZhangJin, fanHuanZhuXianBaoFei,
			renShouBaoXianJinLinear1_Text1, renShouBaoXianJinText_2;

	// 第四部分
	private LinearLayout jianKangGuanAiJinRetative1;
	private LinearLayout jianKangGuanAiJinLinear1, zhongShenBaoZhangLinear2_4,
			zhongShenBaoZhangLinear3_4, zhongShenBaoZhangLinear4_4,
			zhongShenBaoZhangLinear5_4, zhongShenBaoZhangLinear6_4;
	private TextView zhongShenZhongJiBaoZhang, qian22ShenGuBaoZhangJin,
			jiBingShenGuBaoZhangJin, yiWaiShenGuBoaZhangJin,
			suiHouShenGuBaoZhangJin;

	private TextView fanHuanJinT;
	private TextView leiJiFanHuanJinT;
	private TextView totalT;
	private TextView hongLiT;
	private LinearLayout view1_ll;
	private Timer timer;

	// 数据
	private String sex;
	private String age;
	private String period;
	private String level;
	private String baoE;
	private String zhongBaoE;
	private String zhongJiBaoE;
	private double zhuBaoFeiD, zhongJiBaoFeiD, huoMianBaoFeiD, totalBaoFeiD;
	private View view = null;
	private View topView = null;
	private View bottomView = null;
	private ShouHuXService shxService;
//	private Map<Integer, String> map;
	private TextView[] fanHuanJinList;
	private ArrayList<Entity> leiJinFanHuanJinMap, fanHuanJin;
	private ArrayList<HongLi> hongLiMap;

	// 值
	private double fanHuanZhuXian60D_3;
	private double zhongShenZongjiD_1_4;
	private double shenGuMore60_2_4;
	private double yiWaiShenGu22_3_4;
	private double jiBingShenGu22_4_4;
	private double shenGuLess22_5_4;

	private AlertDialog dialog;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		// 设置横屏，全屏，无标题
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.ps_shijitianshishow2);

		sex = this.getIntent().getStringExtra("sex");
		age = this.getIntent().getStringExtra("age");
		period = this.getIntent().getStringExtra("period");
		level = this.getIntent().getStringExtra("level");
		baoE = this.getIntent().getStringExtra("baoE");
		zhuBaoFeiD = this.getIntent().getDoubleExtra("zhuBaoFeiD", 0);
		zhongJiBaoFeiD = this.getIntent().getDoubleExtra("zhongJiBaoFeiD", 0);
		huoMianBaoFeiD = this.getIntent().getDoubleExtra("huoMianBaoFeiD", 0);
		zhongJiBaoE = this.getIntent().getStringExtra("zhongJiBaoE");

		totalBaoFeiD = Arithmetic4Double.add(
				Arithmetic4Double.add(zhuBaoFeiD, zhongJiBaoFeiD),
				huoMianBaoFeiD);

		fanHuanZhuXian60D_3 = Arithmetic4Double.multi(Double.valueOf(period),
				zhuBaoFeiD);
		zhongShenZongjiD_1_4 = Arithmetic4Double.multi(
				Double.valueOf(zhongJiBaoE), 5);
		shenGuMore60_2_4 = Arithmetic4Double.add(Arithmetic4Double.multi(
				Double.valueOf(baoE), 3),
				Arithmetic4Double.div(Arithmetic4Double.multi(
						Double.valueOf(period), zhongJiBaoFeiD), Define.WanD,
						Define.Round2));
		yiWaiShenGu22_3_4 = Arithmetic4Double.add(Arithmetic4Double.multi(
				Double.valueOf(baoE), 6), Arithmetic4Double.div(
				Arithmetic4Double.multi(Double.valueOf(period),
						(Arithmetic4Double.add(zhuBaoFeiD, zhongJiBaoFeiD))),
				Define.WanD, Define.Round2));
		jiBingShenGu22_4_4 = Arithmetic4Double.add(Arithmetic4Double.multi(
				Double.valueOf(baoE), 3), Arithmetic4Double.div(
				Arithmetic4Double.multi(Double.valueOf(period),
						(Arithmetic4Double.add(zhuBaoFeiD, zhongJiBaoFeiD))),
				Define.WanD, Define.Round2));
		shenGuLess22_5_4 = Arithmetic4Double.multi(Double.valueOf(period),
				Arithmetic4Double.div((zhuBaoFeiD + zhongJiBaoFeiD),
						Define.WanD, Define.Round2));

		shxService = new ShouHuXService();
//		map = shxService.findFanHuanJin(
//				Double.valueOf(this.getIntent().getStringExtra("baoE")),
//				Integer.valueOf(this.getIntent().getStringExtra("age")));
		
		fanHuanJin = shxService.findFanHuanJin4ShiJiTS(
				Double.valueOf(this.getIntent().getStringExtra("baoE")),
				Integer.valueOf(this.getIntent().getStringExtra("age")));
		fanHuanJinList = initFanHuanJin(fanHuanJin);
		leiJinFanHuanJinMap = shxService.findLeiJiFanHuanJin4ShiJiTS(
				Double.valueOf(this.getIntent().getStringExtra("baoE")),
				Integer.valueOf(this.getIntent().getStringExtra("age")));

		// 动画的初始化
		// 位移动画
		xiangYouYiDong = AnimationUtils.loadAnimation(this,
				R.anim.ps_shouhuxshow_anim_translateleftin);
		xiangZuoYiDong = AnimationUtils.loadAnimation(this,
				R.anim.ps_shouhuxshow_anim_translaterightin);
		xiangXiaYiDong = AnimationUtils.loadAnimation(getApplicationContext(),
				R.anim.ps_shouhuxshow_anim_xxtranslate);
		xiangShangYiDong = AnimationUtils
				.loadAnimation(getApplicationContext(),
						R.anim.ps_shouhuxshow_anim_xstranslate);
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
		// 缩放和旋转
		suoFangAndXuanZhuan = AnimationUtils.loadAnimation(
				getApplicationContext(),
				R.anim.ps_shouhuxshow_anim_scale_rotate);
		// 缩放和位移
		suoFangAndYiDong = AnimationUtils.loadAnimation(
				getApplicationContext(),
				R.anim.ps_shouhuxshow_anim_scale_translate);

		// 主界面初始化
		main = (LinearLayout) findViewById(R.id.main);
		main.setOnClickListener(this);
		leftLinear = (LinearLayout) findViewById(R.id.leftLinear);
		rightLinear = (LinearLayout) findViewById(R.id.RightLinear);
		leftLinear.setOnClickListener(this);
		// rightLinear.setClickable(clickable)
		rightLinear.setOnClickListener(this);
		zhongShenFenHong = (LinearLayout) findViewById(R.id.zhongShenFenHong1_2);
		zhongShenBaoZhang = (LinearLayout) findViewById(R.id.zhongShenBaoZhang1_4);
		zhongShenFanHuan = (LinearLayout) findViewById(R.id.zhongShenFanHuan1_1);
		tiQianYangLao = (LinearLayout) findViewById(R.id.tiQianYangLao1_3);

		zhongShenFenHong.setVisibility(View.INVISIBLE);
		zhongShenBaoZhang.setVisibility(View.INVISIBLE);
		zhongShenFanHuan.setVisibility(View.INVISIBLE);
		tiQianYangLao.setVisibility(View.INVISIBLE);

		// 控件的初始化和点击监听
		// 第一部分初始化 baoDanLeiJiTitle
		fanHuanJinL = (RelativeLayout) findViewById(R.id.fanHuanJinL);
		fanHuanJinL.setVisibility(View.INVISIBLE);

		ZhongShenFanHuan2_1 = (LinearLayout) findViewById(R.id.zhongShenFanHuan2_1);
		ZhongShenFanHuan2_1.setVisibility(View.INVISIBLE);

		//累积生存金界面初始化
		LeiJinFanHuanShou4_1 = (LinearLayout) findViewById(R.id.leiJinFanHuanShou);
		LeiJinFanHuanShou4_1.setVisibility(View.GONE);
		baoDanLeiJiTitle = (LinearLayout) findViewById(R.id.baoDanLeiJiTitle);
		baoDanLeiJiTitle.setVisibility(View.INVISIBLE);

		baoDanLeiJiContent1 = (LinearLayout) findViewById(R.id.baoDanLeiJiContent1);
		baoDanLeiJiContent2 = (LinearLayout) findViewById(R.id.baoDanLeiJiContent2);
		baoDanLeiJiContent3 = (LinearLayout) findViewById(R.id.baoDanLeiJiContent3);
		baoDanLeiJiContent4 = (LinearLayout) findViewById(R.id.baoDanLeiJiContent4);
		baoDanLeiJiContent5 = (LinearLayout) findViewById(R.id.baoDanLeiJiContent5);
		baoDanLeiJiContent1.setVisibility(View.INVISIBLE);
		baoDanLeiJiContent2.setVisibility(View.INVISIBLE);
		baoDanLeiJiContent3.setVisibility(View.INVISIBLE);
		baoDanLeiJiContent4.setVisibility(View.INVISIBLE);
		baoDanLeiJiContent5.setVisibility(View.INVISIBLE);

		ZhongShenFanHuan2_text = (TextView) findViewById(R.id.ZhongShenFanHuan2_text);
		baoDanLeiJiContent1_shengcunjin = (TextView) findViewById(R.id.baoDanLeiJiContent1_shengcunjin);
		baoDanLeiJiContent2_shengcunjin = (TextView) findViewById(R.id.baoDanLeiJiContent2_shengcunjin);
		baoDanLeiJiContent3_shengcunjin = (TextView) findViewById(R.id.baoDanLeiJiContent3_shengcunjin);
		baoDanLeiJiContent4_shengcunjin = (TextView) findViewById(R.id.baoDanLeiJiContent4_shengcunjin);
		baoDanLeiJiContent5_shengcunjin = (TextView) findViewById(R.id.baoDanLeiJiContent5_shengcunjin);

		setLeiJiShengCunJin();

		baoDanLeiJiContent1.setOnClickListener(this);
		baoDanLeiJiContent2.setOnClickListener(this);
		baoDanLeiJiContent3.setOnClickListener(this);
		baoDanLeiJiContent4.setOnClickListener(this);
		baoDanLeiJiContent5.setOnClickListener(this);

		// 初始化悬浮按钮
		initFloatView();
//	    topText1 = (TextView) topView.findViewById(R.id.count1);
//		topText2 = (TextView) topView.findViewById(R.id.count2);
//		topText3 = (TextView) topView.findViewById(R.id.count3);
//		topText4 = (TextView) topView.findViewById(R.id.count4);
//		topText5 = (TextView) topView.findViewById(R.id.count5);

		// 第二部分初始化
		hongli = new HongLi();
		hongli.setSex(sex);
		hongli.setAge(Integer.valueOf(age));
		hongli.setYear(Integer.valueOf(period));
		hongli.setLevel(level);
		hongli.setFenShu(Integer.valueOf(baoE));
		hongLiMap = shxService.findHongLi4ShiJiTianShi(hongli, this,
				Define.DB_Type_BaoFei_ShiJiTianShi);

		baoDanFenHongJinLinear1 = (LinearLayout) findViewById(R.id.baoDanFenHongJinLinear1);
		zhongShenFenHongLinear = (LinearLayout) findViewById(R.id.zhongShenFenHongLinear);
		hongLiBaoXianJinE_2 = (LinearLayout) findViewById(R.id.hongLiBaoXianJinE_2);
		hongLiBaoXianJinELayout1_2 = (LinearLayout) findViewById(R.id.hongLiBaoXianJinELayout1_2);
		hongLiBaoXianJinELayout2_2 = (LinearLayout) findViewById(R.id.hongLiBaoXianJinELayout2_2);
		hongLiBaoXianJinELayout3_2 = (LinearLayout) findViewById(R.id.hongLiBaoXianJinELayout3_2);
		hongLiBaoXianJinELayout4_2 = (LinearLayout) findViewById(R.id.hongLiBaoXianJinELayout4_2);
		hongLiBaoXianJinELayout5_2 = (LinearLayout) findViewById(R.id.hongLiBaoXianJinELayout5_2);
		hongLiBaoXianJinElayout6_2 = (LinearLayout) findViewById(R.id.hongLiBaoXianJinE6_2);
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

		setHongLiBXJEData();

		baoDanFenHongJinLinear1.setVisibility(View.INVISIBLE);
		hongLiBaoXianJinElayout6_2.setVisibility(View.INVISIBLE);

		levelHongLiTV = (TextView) this.findViewById(R.id.levelHongLi);
		levelHongLiTV.setText(shxService.getZhongShenFenHongLevel(shxService
				.getLevel(level)));

		// 第三部分初始化，并设置为不可见
		fanHuanZhuXianBaoFei = (TextView) findViewById(R.id.fanHuanZhuXianBaoFei);
		shenGuBaoZhangJin = (TextView) findViewById(R.id.shenGuBaoZhangJin);
		renShouBaoXianJinLinear1 = (LinearLayout) findViewById(R.id.renShouBaoXianJinLinear1);
		renShouBaoXianJinLinear2 = (LinearLayout) findViewById(R.id.renShouBaoXianJinLinear2);
		reShouBaoXianJinLinear3 = (LinearLayout) findViewById(R.id.renShouBaoXianJinLinear3);
		reShouBaoXianJinLinear4 = (LinearLayout) findViewById(R.id.renShouBaoXianJinLinear4);
		renShouBaoXianJinLinear1_Text1 = (TextView) findViewById(R.id.renShouBaoXianJinLinear1_Text1);
		renShouBaoXianJinText_2 = (TextView) findViewById(R.id.renShouBaoXianJinText_2);
		renShouBaoXianJinLinear2.setVisibility(view.INVISIBLE);
		renShouBaoXianJinText_2.setVisibility(view.INVISIBLE);
		tiQianYangLaoLinear7_3 = (LinearLayout) findViewById(R.id.tiQianYangLao7_3);
		tiQianYangLaoLinear7_3.setVisibility(View.INVISIBLE);

		// 第四部分初始化，并设置为不可见
		jianKangGuanAiJinRetative1 = (LinearLayout) findViewById(R.id.jianKangGuanAiJinRetative1);
		jianKangGuanAiJinLinear1 = (LinearLayout) findViewById(R.id.jianKangGuanAiJinLinear1);
		zhongShenBaoZhangLinear2_4 = (LinearLayout) findViewById(R.id.zhongShenBaoZhang2_4);
		zhongShenBaoZhangLinear3_4 = (LinearLayout) findViewById(R.id.zhongShenBaoZhang3_4);
		zhongShenBaoZhangLinear4_4 = (LinearLayout) findViewById(R.id.zhongShenBaoZhang4_4);
		zhongShenBaoZhangLinear5_4 = (LinearLayout) findViewById(R.id.zhongShenBaoZhang5_4);
		zhongShenBaoZhangLinear6_4 = (LinearLayout) findViewById(R.id.zhongShenBaoZhang6_4);
		zhongShenBaoZhangLinear2_4.setVisibility(View.INVISIBLE);
		jianKangGuanAiJinRetative1.setVisibility(View.INVISIBLE);
		jianKangGuanAiJinLinear1.setVisibility(View.INVISIBLE);

		zhongShenZhongJiBaoZhang = (TextView) findViewById(R.id.zhongShenZhongJiBaoZhang);
		qian22ShenGuBaoZhangJin = (TextView) findViewById(R.id.qian22ShenGuBaoZhangJin);
		jiBingShenGuBaoZhangJin = (TextView) findViewById(R.id.jiBingShenGuBaoZhangJin);
		yiWaiShenGuBoaZhangJin = (TextView) findViewById(R.id.yiWaiShenGuBoaZhangJin);
		suiHouShenGuBaoZhangJin = (TextView) findViewById(R.id.suiHouShenGuBaoZhangJin);

		setCount();
	}


	// 设置累积生存金
	private void setLeiJiShengCunJin() {
		baoDanLeiJiContent1_shengcunjin.setText(shxService
				.getLeiJinFanHuanByAge(leiJinFanHuanJinMap, 80));
		baoDanLeiJiContent2_shengcunjin.setText(shxService
				.getLeiJinFanHuanByAge(leiJinFanHuanJinMap, 60));
		baoDanLeiJiContent3_shengcunjin.setText(shxService
				.getLeiJinFanHuanByAge(leiJinFanHuanJinMap, 35));
		baoDanLeiJiContent4_shengcunjin.setText(shxService
				.getLeiJinFanHuanByAge(leiJinFanHuanJinMap, 25));
		baoDanLeiJiContent5_shengcunjin.setText(shxService
				.getLeiJinFanHuanByAge(leiJinFanHuanJinMap, 18));
	}
	
	private void setHongLiBXJEData() {
		hongLiBaoXianJinE1_2.setText(shxService.getHongLiByAge(hongLiMap, 80));
		hongLiBaoXianJinE2_2.setText(shxService.getHongLiByAge(hongLiMap, 60));
		hongLiBaoXianJinE3_2.setText(shxService.getHongLiByAge(hongLiMap, 35));
		hongLiBaoXianJinE4_2.setText(shxService.getHongLiByAge(hongLiMap, 25));
		hongLiBaoXianJinE5_2.setText(shxService.getHongLiByAge(hongLiMap, 18));
	}
	
	//设置累积生存金和红利的总和
	private void setCount(){
		Double b = Arithmetic4Double.add(
		Double.valueOf(shxService
				.getLeiJinFanHuanByAge(leiJinFanHuanJinMap, 80)),
				Double.valueOf(shxService.getHongLiByAge(hongLiMap, 80)));
		Double b2 = Arithmetic4Double.add(
				Double.valueOf(shxService
						.getLeiJinFanHuanByAge(leiJinFanHuanJinMap, 60)),
						Double.valueOf(shxService.getHongLiByAge(hongLiMap, 60)));
		Double b3 = Arithmetic4Double.add(
				Double.valueOf(shxService
						.getLeiJinFanHuanByAge(leiJinFanHuanJinMap, 35)),
						Double.valueOf(shxService.getHongLiByAge(hongLiMap, 35)));
		Double b4 = Arithmetic4Double.add(
				Double.valueOf(shxService
						.getLeiJinFanHuanByAge(leiJinFanHuanJinMap, 25)),
						Double.valueOf(shxService.getHongLiByAge(hongLiMap, 25)));
		Double b5 = Arithmetic4Double.add(
				Double.valueOf(shxService
						.getLeiJinFanHuanByAge(leiJinFanHuanJinMap, 18)),
						Double.valueOf(shxService.getHongLiByAge(hongLiMap, 18)));
//		topText1.setText(b.toString()+"祝寿"); 
//		topText2.setText(b2.toString()+"养老"); 
//		topText3.setText(b3.toString()+"养老"); 
//		topText4.setText(b4.toString()+"婚嫁"); 
//		topText5.setText(b5.toString()+"教育"); 
//		topText1.setText(b.toString()+"可给孩子做祝寿金"); 
//		topText2.setText(b2.toString()+"可给孩子做养老金"); 
//		topText3.setText(b3.toString()+"可给自己做养老金"); 
//		topText4.setText(b4.toString()+"可给孩子做婚嫁金"); 
//		topText5.setText(b5.toString()+"可给孩子做教育金"); 
	}

	// public static String step="1";

	@Override
	public void onClick(View v)
	{
		switch (v.getId())
		{
		// 左右屏幕点击事件
		case R.id.rightBtn:
			next();
			if (view != null)
			{
				view.setVisibility(View.VISIBLE);
			}
			// Toast.makeText(getApplicationContext(), "click right",
			// Toast.LENGTH_SHORT).show();
			break;

		case R.id.leftBtn:
			previous();
			// Toast.makeText(getApplicationContext(), "click left",
			// Toast.LENGTH_SHORT).show();
			if (view != null)
			{
				view.setVisibility(View.VISIBLE);
			}
			break;

		// 下部悬浮框TextView点击事件
		case R.id.productZeRen:
			Intent intent2 = new Intent(this, ImgAnimationA.class);
			Bundle b2 = new Bundle();
			b2.putInt("maxPageCount", 4);
			b2.putString("url", "product/shijitianshi/yanshi/productzeren");
			b2.putString("format", "gif");
			b2.putString("screenFlag", "jianjie");
			intent2.putExtras(b2);
			startActivity(intent2);
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
		case R.id.LiCaiYouShi:
			Intent intent4 = new Intent(this, ImgAnimationA.class);
			Bundle b4 = new Bundle();
			b4.putInt("maxPageCount", 9);
			b4.putString("url", "product/shijitianshi/yanshi/licaiyoushi");
			b4.putString("format", "gif");
			b4.putString("screenFlag", "jianjie");
			intent4.putExtras(b4);
			startActivity(intent4);
			break;
		// 该页面有点不同，视屏选择页面
		case R.id.wenTiJieDa:
			Intent intent5 = new Intent(this, ShiJiTianShiTiaoKuanA.class);
			startActivity(intent5);
			break;
//		case R.id.fuJiaBaoXian:
//			// Intent intent6 = new Intent(this, WebViewA.class);
//			// Bundle b6 = new Bundle();
//			// b6.putInt("maxPageCount", 4);
//			// b6.putString("url", "product/shijitianshi/yanshi/fujiabaoxian");
//			// b6.putString("format", "gif");
//			// b6.putString("screenFlag","yanshi");
//			// intent6.putExtras(b6);
//			// startActivity(intent6);
//			Toast.makeText(getApplicationContext(), "无资料", Toast.LENGTH_LONG)
//					.show();
//			break;
		case R.id.canBaoTiShi:
			Intent intent7 = new Intent(this, ImgAnimationA.class);
			Bundle b7 = new Bundle();
			b7.putInt("maxPageCount", 8);
			b7.putString("url", "product/shijitianshi/yanshi/canbaotishi");
			b7.putString("format", "gif");
			b7.putString("screenFlag", "jianjie");
			intent7.putExtras(b7);
			startActivity(intent7);
			break;

		// 第一部分Linear点击事件
		case R.id.baoDanLeiJiContent1:
			showLeiJiFanHuan(61, 105);
			break;
		case R.id.baoDanLeiJiContent2:
			showLeiJiFanHuan(36, 60);
			break;
		case R.id.baoDanLeiJiContent3:
			showLeiJiFanHuan(29, 35);
			break;
		case R.id.baoDanLeiJiContent4:
			showLeiJiFanHuan(23, 28);
			break;
		case R.id.baoDanLeiJiContent5:
			showLeiJiFanHuan(Integer.valueOf(age) + 3, 22);
			break;

		// 第二部分linear点击事件
		case R.id.hongLiBaoXianJinE_2:
			showHongLiFanHuanLevel();
			break;
		case R.id.hongLiBaoXianJinELayout1_2:
			showHongLiFanHuan(80, 105);
			break;
		case R.id.hongLiBaoXianJinELayout2_2:
			showHongLiFanHuan(60, 79);
			break;
		case R.id.hongLiBaoXianJinELayout3_2:
			showHongLiFanHuan(35, 59);
			break;
		case R.id.hongLiBaoXianJinELayout4_2:
			showHongLiFanHuan(22, 34);
			break;
		case R.id.hongLiBaoXianJinELayout5_2:
			showHongLiFanHuan(Integer.valueOf(age), 21);
			break;
		default:
			break;
		}

	}

	public void DialogSelect(String dialogLevel)
	{
		hongli.setLevel(dialogLevel);
		levelHongLiTV.setText(shxService.getZhongShenFenHongLevel(shxService
				.getLevel(dialogLevel)));
		hongLiMap = shxService.findHongLi4ShiJiTianShi(hongli, getApplicationContext(),
				Define.DB_Type_BaoFei_ShiJiTianShi);
		setHongLiBXJEData();
		setCount();
		dialog.cancel();
		setFloatingVisible();
	}

	public void setFloatingVisible()
	{
		if (view != null)
		{
			view.setVisibility(View.VISIBLE);
		}
		if (step >= topFloat)
		{
			if (topView != null)
			{
				topView.setVisibility(View.VISIBLE);
			}
		}

		if (step == bottomFloat)
		{
			if (bottomView != null)
			{
				bottomView.setVisibility(View.VISIBLE);
			}
		}
	}

	public void setFloatingInvisible()
	{
		if (view != null)
		{
			view.setVisibility(View.GONE);
		}
		if (topView != null)
		{
			topView.setVisibility(View.GONE);
		}
		if (bottomView != null)
		{
			bottomView.setVisibility(View.GONE);
		}
		if(leftBtn != null){
			leftBtn.setVisibility(View.GONE);
		}
		if(rightBtn != null){
			rightBtn.setVisibility(View.GONE);
		}
	}

	public void showLeiJiFanHuan(int start, int end)
	{
		// tagFloating=false;
		setFloatingInvisible();
		String items[] = shxService.findLeiJiFanHuanJin(leiJinFanHuanJinMap,
				start, end);
		// Toast.makeText(getApplicationContext(), "click hongLiBaoXianJinE_2",
		// Toast.LENGTH_SHORT).show();
		AlertDialog dialog = new AlertDialog.Builder(this).setTitle("保单生存金")
				.setItems(items, null)
				.setNegativeButton("取消", new DialogInterface.OnClickListener()
				{
					@Override
					public void onClick(DialogInterface dialog, int which)
					{
						setFloatingVisible();
						// Toast.makeText(DialogActivity.this,"我一点也不喜欢海贼王",
						// Toast.LENGTH_SHORT).show();
						// tagFloating=true;
					}
				}).setCancelable(false).show();
		dialog.setCanceledOnTouchOutside(false);
	}

	public void showHongLiFanHuanLevel()
	{
		final String[] items = new String[]
		{ Define.Level_Low_display, Define.Level_Middle_display,
				Define.Level_High_display };

		// 隐藏悬浮框
		setFloatingInvisible();
		dialog = new AlertDialog.Builder(this)
				.setTitle("保单累积红利等级")
				.setSingleChoiceItems(items, 0,
						new DialogInterface.OnClickListener()
						{

							@Override
							public void onClick(DialogInterface dialog,
									int which)
							{
								switch (which)
								{
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
				.setNegativeButton("取消", new DialogInterface.OnClickListener()
				{
					@Override
					public void onClick(DialogInterface dialog, int which)
					{
						setFloatingVisible();
					}
				}).setCancelable(false).show();
		dialog.setCanceledOnTouchOutside(false);
	}

	public void showHongLiFanHuan(int start, int end) {
		setFloatingInvisible();
		String items[] = shxService.findHongLi(hongLiMap, start, end);
		AlertDialog dialog = new AlertDialog.Builder(this).setTitle("保单累积红利")
				.setItems(items, null)
				.setNegativeButton("取消", new DialogInterface.OnClickListener()
				{
					@Override
					public void onClick(DialogInterface dialog, int which)
					{
						setFloatingVisible();
					}
				}).setCancelable(false).show();
		dialog.setCanceledOnTouchOutside(false);
	}

	/**
	 * 初始化悬浮按钮
	 */
	private void initFloatView()
	{
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
		createTopFloatView();
		createBottomFloatView();
		createButtonLeft();
		createButtonRight();
	}

	/**
	 * 创建中间悬浮按钮
	 */
	private void createCenterFloatView()
	{
		TextView tv = new TextView(this);
		tv.setTextSize(8);

		TextPaint tp = tv.getPaint();
		tp.setFakeBoldText(true);

		LayoutInflater inflater = (LayoutInflater) this
				.getSystemService(LAYOUT_INFLATER_SERVICE);
		view = inflater.inflate(R.layout.ps_shijitianshi_center_float, null);

		TextView floatText1 = (TextView) view.findViewById(R.id.floatText1);
		TextView floatText2 = (TextView) view.findViewById(R.id.floatText2);
		TextView floatText3 = (TextView) view.findViewById(R.id.floatText3);
		floatText1.setText("交" + period + "年");
		floatText2.setText("每年" + totalBaoFeiD);
		floatText3.setText("共"
				+ (Arithmetic4Double.sub(Arithmetic4Double.multi(
						Double.valueOf(period), totalBaoFeiD), huoMianBaoFeiD))
				+ "元");

		wmParams.width = DensityUtil.dip2px(this, 145);
		wmParams.height = DensityUtil.dip2px(this, 68);

		// 调整悬浮窗口
		wmParams.gravity = Gravity.CENTER;
		
		wm.addView(view, wmParams);
	}

	/**
	 * 创建上边悬浮按钮
	 */
	private void createTopFloatView()
	{
		LayoutInflater inflater = (LayoutInflater) this
				.getSystemService(LAYOUT_INFLATER_SERVICE);
		topView = inflater.inflate(R.layout.ps_shijitianshi_top_float, null);
		topView.setVisibility(View.GONE);
		
		View lh = inflater.inflate( R.layout.ps_shijitianshi_lj_fan_huan, null);
		lh.measure(0, 0);//主动通知系统去测量
		int viewHeight = lh.getMeasuredHeight();
		
//		wmParams.width = DensityUtil.dip2px(this, 100);
//		wmParams.height = DensityUtil.dip2px(this, viewHeight/2-viewHeight/8);
//
//		wmParams.y = DensityUtil.dip2px(this, 30);
		
		wmParams.width = DensityUtil.dip2px(this,100);
		wmParams.height =  DensityUtil.dip2px(this,120);
		wmParams.y = DensityUtil.dip2px(this,27);;

		// 调整悬浮窗口
		wmParams.gravity = Gravity.CENTER_HORIZONTAL | Gravity.TOP;
		wm.addView(topView, wmParams);
	}


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
	private void createBottomFloatView()
	{
		Display display = getWindowManager().getDefaultDisplay();
		int height = display.getHeight();
		int width = display.getWidth();

		LayoutInflater inflater = (LayoutInflater) this
				.getSystemService(LAYOUT_INFLATER_SERVICE);

		bottomView = inflater.inflate(R.layout.ps_shijitianshi_bottom_float,
				null);

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

		// 以屏幕左上角为原点，设置x、y初始值
		wmParams.x = width / 2 - wmParams.width / 2;
		wmParams.y = height - wmParams.height;

		// 调整悬浮窗口
		wmParams.gravity = Gravity.LEFT | Gravity.TOP;
		wm.addView(bottomView, wmParams);
	}

	public TextView[] initFanHuanJin( ArrayList<Entity> map)
	{

		// 实例化一个10个TextView的数组
		TextView fanHuanJinS[] = new TextView[35];

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
		// fanHuanJinS[35] = (TextView) findViewById(R.id.fan36);
		// fanHuanJinS[36] = (TextView) findViewById(R.id.fan37);
		// fanHuanJinS[37] = (TextView) findViewById(R.id.fan38);
		// fanHuanJinS[38] = (TextView) findViewById(R.id.fan39);
		// fanHuanJinS[39] = (TextView) findViewById(R.id.fan40);
		// fanHuanJinS[40] = (TextView) findViewById(R.id.fan41);
		// fanHuanJinS[41] = (TextView) findViewById(R.id.fan42);
		// fanHuanJinS[42] = (TextView) findViewById(R.id.fan43);
		// fanHuanJinS[43] = (TextView) findViewById(R.id.fan44);
		// fanHuanJinS[44] = (TextView) findViewById(R.id.fan45);
		// fanHuanJinS[45] = (TextView) findViewById(R.id.fan46);
		// fanHuanJinS[46] = (TextView) findViewById(R.id.fan47);
		// fanHuanJinS[47] = (TextView) findViewById(R.id.fan48);
		// fanHuanJinS[48] = (TextView) findViewById(R.id.fan49);
		// fanHuanJinS[49] = (TextView) findViewById(R.id.fan50);
		// fanHuanJinS[50] = (TextView) findViewById(R.id.fan51);
		// fanHuanJinS[51] = (TextView) findViewById(R.id.fan52);
		// fanHuanJinS[52] = (TextView) findViewById(R.id.fan53);
		// fanHuanJinS[53] = (TextView) findViewById(R.id.fan54);
		// fanHuanJinS[54] = (TextView) findViewById(R.id.fan55);
		// fanHuanJinS[55] = (TextView) findViewById(R.id.fan56);
		// fanHuanJinS[56] = (TextView) findViewById(R.id.fan57);
		// fanHuanJinS[57] = (TextView) findViewById(R.id.fan58);
		// fanHuanJinS[58] = (TextView) findViewById(R.id.fan59);
		// fanHuanJinS[59] = (TextView) findViewById(R.id.fan60);
		// fanHuanJinS[60] = (TextView) findViewById(R.id.fan61);
		// fanHuanJinS[61] = (TextView) findViewById(R.id.fan62);
		// fanHuanJinS[62] = (TextView) findViewById(R.id.fan63);
		// fanHuanJinS[63] = (TextView) findViewById(R.id.fan64);
		// fanHuanJinS[64] = (TextView) findViewById(R.id.fan65);
		// fanHuanJinS[65] = (TextView) findViewById(R.id.fan66);
		// fanHuanJinS[66] = (TextView) findViewById(R.id.fan67);
		// fanHuanJinS[67] = (TextView) findViewById(R.id.fan68);
		// fanHuanJinS[68] = (TextView) findViewById(R.id.fan69);
		// fanHuanJinS[69] = (TextView) findViewById(R.id.fan70);
		// fanHuanJinS[70] = (TextView) findViewById(R.id.fan71);
		// fanHuanJinS[71] = (TextView) findViewById(R.id.fan72);

		for (int i = 0; i <= 34; i++)
		{
			// 设置不可见
			fanHuanJinS[i].setVisibility(View.INVISIBLE);
//			fanHuanJinS[i].setAnimation(xiangXiaYiDong);
//			new Thread(new Runnable(){
//				@Override
//				public void run() {
//					try {
//						Thread.sleep(1000);
//					} catch (InterruptedException e) {
//						e.printStackTrace();
//					}
//				}
//			}).start();
		}
		
		
		TextView fanHuanJinTestView[] = new TextView[map.size()];
		try{
		for(int j=0;j<fanHuanJinTestView.length;j++){
			fanHuanJinTestView[j]=fanHuanJinS[j];
		}
		}catch(Exception e){
			e.printStackTrace();
		}
		return fanHuanJinTestView;
	}

	final int ZhongShenFenHong1_2 = 3;
	final int ZhongShenFanHuan1_1 = 2;
	final int ZhongShenBaoZhang1_4 = 4;
	final int TiQianYangLao1_3 = 1;

	final int renShouBaoXianJinLinear2_Step = 5;
	final int renShouBaoXianJinText_2_Step = 6;

	final int ZhongShenFanHuanTex2_1 = 7;

	final int FanHuanJinShow3_1 = 8;
	//保单累积生存金的条目动画步骤
	final int FanHuanJinShow5_1 = 9;
	final int FanHuanJinShow6_1 = 14;
	final int FanHuanJinShow7_1 = 13;
	final int FanHuanJinShow8_1 = 12;
	final int FanHuanJinShow9_1 = 11;
	final int FanHuanJinShow10_1 = 10;

	final int baoDanFenHongJinLinear1_step = 15;
	final int hongLiBaoXianJinE6_2 = 16;
	//红利的条目动画步骤
	final int HongLiShow1_2 = 17;
	final int HongLiShow2_2 = 22;
	final int HongLiShow3_2 = 21;
	final int HongLiShow4_2 = 20;
	final int HongLiShow5_2 = 19;
	final int HongLiShow6_2 = 18;

	// 上部分悬浮框
	final int topFloat = 23;

	final int jianKangGuanAiJinRetative1_step = 24;

	final int reShouBaoXianJinLinear3_step = 25;
	final int reShouBaoXianJinLinear4_step = 26;

	final int jianKangGuanAiJinLinear1_step = 27;

	// 下部分悬浮框
	final int bottomFloat = 28;

	public static int step = 0;

	public void next()
	{
		switch (++step)
		{
		case TiQianYangLao1_3:
			// step++;
			tiQianYangLao.startAnimation(xiangYouYiDong);
			tiQianYangLao.setVisibility(View.VISIBLE);
			break;
		case ZhongShenFanHuan1_1:
			// step++;
			zhongShenFanHuan.startAnimation(xiangYouYiDong);
			zhongShenFanHuan.setVisibility(View.VISIBLE);
			break;
		case ZhongShenFenHong1_2:
			zhongShenFenHong.startAnimation(xiangZuoYiDong);
			zhongShenFenHong.setVisibility(View.VISIBLE);
			// step++;
			break;
		case ZhongShenBaoZhang1_4:
			// step++;
			zhongShenBaoZhang.startAnimation(xiangZuoYiDong);
			zhongShenBaoZhang.setVisibility(View.VISIBLE);
			break;
		case renShouBaoXianJinLinear2_Step:
			renShouBaoXianJinLinear2.startAnimation(xiangShangYiDong);
			renShouBaoXianJinLinear2.setVisibility(View.VISIBLE);
			break;
		case renShouBaoXianJinText_2_Step:
			renShouBaoXianJinText_2.startAnimation(jianBian);
			renShouBaoXianJinText_2.setVisibility(View.VISIBLE);
			break;
		case ZhongShenFanHuanTex2_1:
			// step++;
			ZhongShenFanHuan2_1.startAnimation(jianBian);
			ZhongShenFanHuan2_1.setVisibility(View.VISIBLE);
			break;
		case FanHuanJinShow3_1:
			// step++;
			fanHuanJinL.setVisibility(View.VISIBLE);
			for(int i=0;i<fanHuanJinList.length;i++){
				fanHuanJinList[i].setVisibility(View.INVISIBLE);
			}
			shxService.showShiJiTSFanHuanJin(fanHuanJinList, fanHuanJin,
						xiangXiaYiDong, View.VISIBLE,rightBtn,leftBtn);

			ZhongShenFanHuan2_text.setText("生存金可随意领取，也可以存在保险公司，利滚利、钱生钱");
			ZhongShenFanHuan2_1.startAnimation(xiangShangYiDong);

			break;
			//保单累积生存金的条目的动画
		case FanHuanJinShow5_1:
			fanHuanJinL.setVisibility(View.GONE);
			LeiJinFanHuanShou4_1.setVisibility(View.VISIBLE);
//			LeiJinFanHuanShou4_1.startAnimation(jianBian);
			baoDanLeiJiTitle.setVisibility(View.VISIBLE);
			baoDanLeiJiTitle.startAnimation(xiangShangYiDong);
			break;
		case FanHuanJinShow6_1:
			baoDanLeiJiContent1.setVisibility(View.VISIBLE);
			baoDanLeiJiContent1.startAnimation(xiangYouYiDong);
			break;
		case FanHuanJinShow7_1:
			baoDanLeiJiContent2.setVisibility(View.VISIBLE);
			baoDanLeiJiContent2.startAnimation(xiangYouYiDong);
			break;
		case FanHuanJinShow8_1:
			baoDanLeiJiContent3.setVisibility(View.VISIBLE);
			baoDanLeiJiContent3.startAnimation(xiangYouYiDong);
			break;
		case FanHuanJinShow9_1:
			baoDanLeiJiContent4.setVisibility(View.VISIBLE);
			baoDanLeiJiContent4.startAnimation(xiangYouYiDong);
			break;
		case FanHuanJinShow10_1:
			baoDanLeiJiContent5.setVisibility(View.VISIBLE);
			baoDanLeiJiContent5.startAnimation(xiangYouYiDong);
			break;

		case baoDanFenHongJinLinear1_step:
			baoDanFenHongJinLinear1.setVisibility(View.VISIBLE);
			baoDanFenHongJinLinear1.startAnimation(xiangXiaYiDong);
			break;
		case hongLiBaoXianJinE6_2:
			hongLiBaoXianJinElayout6_2.setVisibility(View.VISIBLE);
			hongLiBaoXianJinElayout6_2.startAnimation(xiangZuoYiDong);
			break;
		case HongLiShow1_2:
			zhongShenFenHongLinear.setVisibility(View.VISIBLE);
			hongLiBaoXianJinE_2.setVisibility(View.VISIBLE);
			zhongShenFenHongLinear.startAnimation(suoFangBianDa);
			baoDanFenHongJinLinear1.setVisibility(View.GONE);
			break;

		case HongLiShow2_2:
			hongLiBaoXianJinELayout1_2.setVisibility(View.VISIBLE);
			hongLiBaoXianJinELayout1_2.startAnimation(xiangZuoYiDong);
			break;
		case HongLiShow3_2:
			hongLiBaoXianJinELayout2_2.setVisibility(View.VISIBLE);
			hongLiBaoXianJinELayout2_2.startAnimation(xiangZuoYiDong);
			break;
		case HongLiShow4_2:
			hongLiBaoXianJinELayout3_2.setVisibility(View.VISIBLE);
			hongLiBaoXianJinELayout3_2.startAnimation(xiangZuoYiDong);
			break;
		case HongLiShow5_2:
			hongLiBaoXianJinELayout4_2.setVisibility(View.VISIBLE);
			hongLiBaoXianJinELayout4_2.startAnimation(xiangZuoYiDong);
			break;
		case HongLiShow6_2:
			hongLiBaoXianJinELayout5_2.setVisibility(View.VISIBLE);
			hongLiBaoXianJinELayout5_2.startAnimation(xiangZuoYiDong);
			break;

		case topFloat:
			topView.setVisibility(View.VISIBLE);
			topView.startAnimation(xiangXiaYiDong);
			break;

		case jianKangGuanAiJinRetative1_step:
			jianKangGuanAiJinRetative1.setVisibility(View.VISIBLE);
			jianKangGuanAiJinRetative1.startAnimation(suoFangBianDa);
			// c万元的数值设置
			zhongShenZhongJiBaoZhang.setText(zhongJiBaoE + "万元");
			zhongShenBaoZhangLinear2_4.setVisibility(View.VISIBLE);
			zhongShenBaoZhangLinear2_4.startAnimation(xiangZuoYiDong);
			break;

		// 三个图片的步骤
		case reShouBaoXianJinLinear3_step:
			shenGuBaoZhangJin.setText(Arithmetic4Double.round(
					Arithmetic4Double.multi(Double.valueOf(baoE), 3),
					Define.Round2) + "万元");
			renShouBaoXianJinLinear1.setVisibility(View.GONE);
			reShouBaoXianJinLinear3.setVisibility(View.VISIBLE);
			reShouBaoXianJinLinear3.startAnimation(xiangXiaYiDong);
			break;
		case reShouBaoXianJinLinear4_step:
			reShouBaoXianJinLinear3.setVisibility(View.GONE);
			reShouBaoXianJinLinear4.setVisibility(View.VISIBLE);
			reShouBaoXianJinLinear4.startAnimation(xiangShangYiDong);
			break;

		case jianKangGuanAiJinLinear1_step:
			tiQianYangLaoLinear7_3.setVisibility(View.VISIBLE);
			jianKangGuanAiJinLinear1.setVisibility(View.VISIBLE);
			tiQianYangLaoLinear7_3.startAnimation(xiangShangYiDong);
			jianKangGuanAiJinLinear1.startAnimation(jianBian);
			break;
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

	// zhongShenBaoZhang = (LinearLayout)
	// findViewById(R.id.zhongShenBaoZhang1_4);
	// zhongShenFanHuan = (LinearLayout) findViewById(R.id.zhongShenFanHuan1_1);
	// tiQianYangLao = (LinearLayout) findViewById(R.id.tiQianYangLao1_3);

	public void previous()
	{

		switch (step)
		{
		case TiQianYangLao1_3:
			step--;
			tiQianYangLao.setVisibility(View.INVISIBLE);
			break;
		case ZhongShenFanHuan1_1:
			step--;
			zhongShenFanHuan.setVisibility(View.INVISIBLE);
			break;
		case ZhongShenFenHong1_2:
			step--;
			zhongShenFenHong.setVisibility(View.INVISIBLE);
			break;
		case ZhongShenBaoZhang1_4:
			step--;
			zhongShenBaoZhang.setVisibility(View.INVISIBLE);
			break;
		case renShouBaoXianJinLinear2_Step:
			step--;
			renShouBaoXianJinLinear2.setVisibility(View.INVISIBLE);
			break;
		case renShouBaoXianJinText_2_Step:
			step--;
			renShouBaoXianJinText_2.setVisibility(View.INVISIBLE);
			break;
		case ZhongShenFanHuanTex2_1:
			step--;
			ZhongShenFanHuan2_1.setVisibility(View.INVISIBLE);
			break;

		case FanHuanJinShow3_1:
			step--;
			// shxService.showFanHuanJin22(fanHuanJinList, map,
			// yiDongAndJianBian,
			// View.VISIBLE);
			//new ShouHuXService().timer1.cancel();
			fanHuanJinL.setVisibility(View.INVISIBLE);
			// fanHuanJinL.setVisibility(View.INVISIBLE);
			ZhongShenFanHuan2_text.setText("每3年领取一次，领取额度高达保额的12%");
			// shxService.showFanHuanJinMore22(fanHuanJinList, map,
			// yiDongAndJianBian, View.INVISIBLE);
			break;
			//保单累积生存金的条目的动画后退演示
		case FanHuanJinShow5_1:
			step--;
			LeiJinFanHuanShou4_1.setVisibility(View.GONE);
			fanHuanJinL.setVisibility(View.VISIBLE);
			
//			shxService.showShiJiTSFanHuanJin(fanHuanJinList, fanHuanJin,
//					xiangXiaYiDong, View.VISIBLE);
			// shxService.showFanHuanJin22(fanHuanJinList, map,
			// yiDongAndJianBian,
			// View.VISIBLE);
			// shxService.showFanHuanJinMore22(fanHuanJinList, map,
			// yiDongAndJianBian, View.VISIBLE);
			break;
		case FanHuanJinShow6_1:
			step--;
			baoDanLeiJiContent1.setVisibility(View.INVISIBLE);
			break;
		case FanHuanJinShow7_1:
			step--;
			baoDanLeiJiContent2.setVisibility(View.INVISIBLE);
			break;
		case FanHuanJinShow8_1:
			step--;
			baoDanLeiJiContent3.setVisibility(View.INVISIBLE);
			break;
		case FanHuanJinShow9_1:
			step--;
			baoDanLeiJiContent4.setVisibility(View.INVISIBLE);
			break;
		case FanHuanJinShow10_1:
			step--;
			baoDanLeiJiContent5.setVisibility(View.INVISIBLE);
			break;
		case baoDanFenHongJinLinear1_step:
			step--;
			baoDanFenHongJinLinear1.setVisibility(View.INVISIBLE);
			break;
		case hongLiBaoXianJinE6_2:
			step--;
			hongLiBaoXianJinElayout6_2.setVisibility(View.INVISIBLE);
			break;
		case HongLiShow1_2:
			step--;
			zhongShenFenHongLinear.setVisibility(View.GONE);
			baoDanFenHongJinLinear1.setVisibility(View.VISIBLE);
			break;
		case HongLiShow2_2:
			step--;
			hongLiBaoXianJinELayout1_2.setVisibility(View.INVISIBLE);
			break;
		case HongLiShow3_2:
			step--;
			hongLiBaoXianJinELayout2_2.setVisibility(View.INVISIBLE);
			break;
		case HongLiShow4_2:
			step--;
			hongLiBaoXianJinELayout3_2.setVisibility(View.INVISIBLE);
			break;
		case HongLiShow5_2:
			step--;
			hongLiBaoXianJinELayout4_2.setVisibility(View.INVISIBLE);
			break;
		case HongLiShow6_2:
			step--;
			hongLiBaoXianJinELayout5_2.setVisibility(View.INVISIBLE);
			break;

		case topFloat:
			step--;
			topView.setVisibility(View.INVISIBLE);
			break;

		case jianKangGuanAiJinRetative1_step:
			step--;
			jianKangGuanAiJinRetative1.setVisibility(View.INVISIBLE);
			// zhongShenZhongJiBaoZhang.setText(String.valueOf(zhongShenZongjiD_1_4)+"万元");
			zhongShenBaoZhangLinear2_4.setVisibility(View.INVISIBLE);
			break;

		// 最后的三个图片的步骤
		case reShouBaoXianJinLinear3_step:
			step--;
			renShouBaoXianJinLinear1.setVisibility(View.VISIBLE);
			reShouBaoXianJinLinear3.setVisibility(View.GONE);
			break;
		case reShouBaoXianJinLinear4_step:
			step--;
			reShouBaoXianJinLinear3.setVisibility(View.VISIBLE);
			reShouBaoXianJinLinear4.setVisibility(View.GONE);
			break;
		case jianKangGuanAiJinLinear1_step:
			step--;
			tiQianYangLaoLinear7_3.setVisibility(View.INVISIBLE);
			jianKangGuanAiJinLinear1.setVisibility(View.INVISIBLE);
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
	protected void onStart()
	{

		super.onStart();
	}

	@Override
	protected void onRestart()
	{
		if (view != null)
		{
			view.setVisibility(View.VISIBLE);
		}
		if (topView != null)
		{
			topView.setVisibility(View.VISIBLE);
		}
		if (bottomView != null)
		{
			bottomView.setVisibility(View.VISIBLE);
		}
		if(leftView != null){
			leftView.setVisibility(View.VISIBLE);
		}
		if(rightView != null){
			rightView.setVisibility(View.VISIBLE);
		}
		super.onRestart();
	}

	@Override
	protected void onResume()
	{

		super.onResume();

	}

	@Override
	protected void onPause()
	{
		if (view != null)
		{
			view.setVisibility(View.GONE);
		}

		if (topView != null)
		{
			topView.setVisibility(View.GONE);
		}
		if (bottomView != null)
		{
			bottomView.setVisibility(View.GONE);
		}
		if(leftView != null){
			leftView.setVisibility(View.GONE);
		}
		if(rightView != null){
			rightView.setVisibility(View.GONE);
		}
		super.onPause();
	}

	@Override
	protected void onStop()
	{
		if (view != null)
		{
			view.setVisibility(View.GONE);
		}
		if (topView != null)
		{
			topView.setVisibility(View.GONE);
		}
		if (bottomView != null)
		{
			bottomView.setVisibility(View.GONE);
		}
		if(leftView != null){
			leftView.setVisibility(View.GONE);
		}
		if(rightView != null){
			rightView.setVisibility(View.GONE);
		}

		super.onStop();
	}

	@Override
	protected void onDestroy()
	{
		if (view != null)
		{
			wm.removeView(view);
		}
		if (topView != null)
		{
			wm.removeView(topView);
		}

		if (bottomView != null)
		{
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
	public void onBackPressed()
	{
//		super.onBackPressed();
//		Intent intent = new Intent(this, BaoXianProductA.class);
//		intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//		startActivity(intent);
		Intent intent4 = new Intent(this, ShiJiTSPlanA.class);
		startActivity(intent4);
		finish();
		step = 0;
	}

}
