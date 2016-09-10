package com.baoxiao.activity.productshow.zhinengxing;

import java.util.ArrayList;
import com.baoxiao.R;
import com.baoxiao.activity.productshow.ImgAnimationA;
import com.baoxiao.model.HongLi;
import com.baoxiao.provider.DBDAO;
import com.baoxiao.service.productshow.ZhiNengXingService;
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
import android.widget.LinearLayout;
import android.widget.TextView;

public class ZhiNengXingShowA extends Activity implements OnClickListener{

	private Animation xiangYouYiDong, xiangZuoYiDong, yiDongAndJianBian,
			jianBian, suoFang, suoFangBianDa, xuanZhuan, xiangXiaYiDong,
			xiangShangYiDong, suoFangAndXuanZhuan, suoFangAndYiDong;
	private AnimationSet set;

	private boolean tagFloating = true;
	// �����漰�����ĸ�����
	private LinearLayout main;
	private LinearLayout leftLinear;
	private LinearLayout rightLinear;

	// ��ߵ�Բ���е����ݿؼ�
	private TextView floatText1, floatText2, floatText3;

	// ������
	private WindowManager wm = null;
	private WindowManager.LayoutParams wmParams = null;
	// �²�������
	private TextView productZeRen,zeRenMianChu,wanNengTeSe,liShiLiLv,zhiChanGuanLi,
		wanNengTouZi,fuJiaBaoXian,canBaoTiShi;

	// ��һ����
	private LinearLayout jianKangZhongJiJinLinear;
	private TextView jianKangTitle, jianKangtext1, jianKangtext2,
			yiWaiShenGuBaoZhangJin, jiBingShenGuBaoZhangJin;

	// �ڶ�����
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

	// ��������
	private LinearLayout yiWaiShenGuLinear, jiBingShenGuLinear;
	private TextView baoZhangTitle, baoZhangtext1, baoZhangtext2;

	// ���Ĳ���
	private LinearLayout yiWaiYiLiaoLinear, jiBingShangCanLinear;
	private TextView yiWaiTitle, yiWaitext1, yiWaitext2,
			yiWaiYiLiaoBaoZhangJin;

	// ����
	private String sex;
	private String age;
	private String period;
	// �ȼ�
	private String level;
	// ����
	private String baoE;
	private String zhongJiBaoE;
	private String yiLiaoBaoE;
	// ����
	private String zhuBaoFeiD;
	// ����
	private String huoMian;
	private View view = null;
	private View topView = null;
	private View bottomView = null;
	private ArrayList<HongLi> hongLiMap,zjFeiLv,hmFeiLv,wxxsFeiLv,zxFeiLv;

	// // ֵ

	private AlertDialog dialog;
	
	//���㱣���ֽ��ֵ����ر���
	private HongLi hongli;
	private ZhiNengXingService znx;
	//bFΪ�ڽ����ѣ�bEΪ���ձ���
	String baoDanXianJin;
	Double zhuXiangBaoZhang;
	private Boolean isfuJia;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// ���ú�����ȫ�����ޱ���
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.ps_zhinengxingshow);

		// ��ʼ���ؼ�
		initView();
		// ��ȡ���洫�ݹ�����ֵ
		getIntentValue();
		// ��ʼ������Ч��
		initAnim();
		// ��ʼ��������ť
		initFloatView();
		// ����HongLi�����ֵ
		setHongLiValue();
		// ���ý�����ʾ����
		setDataValue();
		// ��ȡ����
//		new Thread(DataValueRunnable).start();
		hongLiMap = setData();
		// ����
		setHongLiBXJEData();
	}
	
//	Runnable DataValueRunnable = new Runnable() {
//		@Override
//		public void run() {
//			hongLiMap = setData();
//			Message m = new Message();
//			m.what = 1;
//			handler.sendMessage(m);
//		}
//	};
	
//	Handler handler = new Handler(){
//
//		@Override
//		public void handleMessage(Message msg) {
//			super.handleMessage(msg);
//			if(msg.what == 1){
//				// ����
//				setHongLiBXJEData();
//			}
//		}
//		
//	};

	private void setDataValue() {
		levelHongLiTV.setText(znx
				.getWanNengZhangHuLevel(znx.getLevel(level)));
		
		floatText1.setText("��" + period + "��");
		floatText2.setText("ÿ��" + zhuBaoFeiD + Define.Yuan);
		floatText3.setText("��"
				+ (Arithmetic4Double.multi(
						Double.valueOf(period), Double.valueOf(zhuBaoFeiD)))
				+ "Ԫ");
	}

	private void setHongLiValue() {
		znx = new ZhiNengXingService();

		hongli = new HongLi();
		hongli.setAge(Integer.valueOf(age));
		hongli.setSex(sex);
		hongli.setBaoE(Integer.valueOf(Arithmetic4Double.doubleToString(Arithmetic4Double.multi(Double.valueOf(baoE),Define.WanD))));
		hongli.setYear(Integer.valueOf(period));
		hongli.setZhuBaoF(Integer.valueOf(zhuBaoFeiD));
		hongli.setZhongJiBaoE(Integer.valueOf(Arithmetic4Double.doubleToString(Arithmetic4Double.multi(Double.valueOf(zhongJiBaoE),Define.WanD))));
		hongli.setYiWaiBaoE(Integer.valueOf(Arithmetic4Double.doubleToString(Arithmetic4Double.multi(Double.valueOf(yiLiaoBaoE),Define.WanD))));
		hongli.setLevel(level);
	}

	private void getIntentValue() {
		sex = this.getIntent().getStringExtra("sex");
		age = this.getIntent().getStringExtra("age");
		period = this.getIntent().getStringExtra("period");
		level = this.getIntent().getStringExtra("level");
		baoE = this.getIntent().getStringExtra("baoE");
		zhongJiBaoE = this.getIntent().getStringExtra("zhongJiBaoE");
		yiLiaoBaoE = this.getIntent().getStringExtra("yiWaiYiLiBaoE");
		zhuBaoFeiD = this.getIntent().getStringExtra("zhuBaoF");
		huoMian = this.getIntent().getStringExtra("huoMian");
		isfuJia = getIntent().getBooleanExtra("isfuJia",false);
	}

	private void initView() {

		// �������ʼ��
		main = (LinearLayout) findViewById(R.id.main);
		main.setOnClickListener(this);
		leftLinear = (LinearLayout) findViewById(R.id.leftLinear);
		rightLinear = (LinearLayout) findViewById(R.id.RightLinear);
		leftLinear.setOnClickListener(this);
		rightLinear.setOnClickListener(this);

		// ��ߵ�Բ���е����ݿؼ�
		floatText1 = (TextView) findViewById(R.id.floatText1);
		floatText2 = (TextView) findViewById(R.id.floatText2);
		floatText3 = (TextView) findViewById(R.id.floatText3);

		// �ؼ��ĳ�ʼ���͵������
		// ��һ���ֳ�ʼ�� baoDanLeiJiTitle
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

		// �ڶ����ֳ�ʼ��
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
		// �Ա����¼�
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
		// �������ֳ�ʼ����������Ϊ���ɼ�
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

		// ���Ĳ��ֳ�ʼ����������Ϊ���ɼ�
		yiWaiTitle = (TextView) findViewById(R.id.huShenFuYiWaiTitle);
		yiWaitext1 = (TextView) findViewById(R.id.huShenFuYiWaiText1);
		yiWaitext2 = (TextView) findViewById(R.id.huShenFuYiWaiText2);
		yiWaiYiLiaoBaoZhangJin = (TextView) findViewById(R.id.yiWaiYiLiaoBaoZhangJin);
		yiWaiYiLiaoLinear = (LinearLayout) findViewById(R.id.yiWaiYiLiaoLinear);
		jiBingShangCanLinear = (LinearLayout) findViewById(R.id.jiBingShangCanLinear);
		yiWaiTitle.setVisibility(View.INVISIBLE);
		yiWaitext1.setVisibility(View.INVISIBLE);
		yiWaitext2.setVisibility(View.INVISIBLE);
		yiWaiYiLiaoLinear.setVisibility(View.INVISIBLE);
		jiBingShangCanLinear.setVisibility(View.INVISIBLE);


	}

	private void initAnim() {
		// �����ĳ�ʼ��
		// λ�ƶ���
		xiangYouYiDong = AnimationUtils.loadAnimation(this,
				R.anim.ps_shouhuxshow_anim_translateleftin);
		xiangZuoYiDong = AnimationUtils.loadAnimation(this,
				R.anim.ps_shouhuxshow_anim_translaterightin);
		yiDongAndJianBian = AnimationUtils.loadAnimation(
				getApplicationContext(),
				R.anim.ps_shouhuxshow_anim_translateleftin);
		// ���䶯��
		jianBian = AnimationUtils.loadAnimation(getApplicationContext(),
				R.anim.ps_shouhuxshow_anim_alpha);
		// ���Ŷ���
		suoFang = AnimationUtils.loadAnimation(getApplicationContext(),
				R.anim.ps_shouhuxshow_anim_scale);
		// ���Ŷ���,��С���
		suoFangBianDa = AnimationUtils.loadAnimation(getApplicationContext(),
				R.anim.ps_shouhuxshow_anim_scale_big);
		// ��ת����
		xuanZhuan = AnimationUtils.loadAnimation(getApplicationContext(),
				R.anim.ps_shouhuxshow_anim_rotate);
		// �����ƶ�
		xiangXiaYiDong = AnimationUtils.loadAnimation(getApplicationContext(),
				R.anim.ps_shouhuxshow_anim_xxtranslate);
		// �����ƶ�
		xiangShangYiDong = AnimationUtils
				.loadAnimation(getApplicationContext(),
						R.anim.ps_shouhuxshow_anim_xstranslate);
		// ���ź���ת
		suoFangAndXuanZhuan = AnimationUtils.loadAnimation(
				getApplicationContext(),
				R.anim.ps_shouhuxshow_anim_scale_rotate);
		// ���ź�λ��
		suoFangAndYiDong = AnimationUtils.loadAnimation(
				getApplicationContext(),
				R.anim.ps_shouhuxshow_anim_scale_translate);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		// ������Ļ����¼�
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

//			productZeRen,zeRenMianChu,wanNengTeSe,liShiLiLv,zhiChanGuanLi,
//			wanNengTouZi,fuJiaBaoXian,canBaoTiShi;
		case R.id.productZeRen:
			Intent intent = new Intent(this, ImgAnimationA.class);
			Bundle b = new Bundle();
			b.putInt("maxPageCount", 6);
			b.putString("url", "product/zhinengxing/yanshi/baoxianzeren");
			b.putString("format", "gif");
			b.putString("screenFlag", "yanshi");
			b.putString("tiaokuan", "zhinengxing");
			intent.putExtras(b);
			startActivity(intent);
			break;
		case R.id.zeRenMianChu:
//			Intent intent2 = new Intent(this, ImgAnimationA.class);
//			Bundle b2 = new Bundle();
//			b2.putInt("maxPageCount", 10);
//			b2.putString("url", "product/zhinengxing/yanshi/zerenmianchu");
//			b2.putString("format", "gif");
//			b2.putString("screenFlag", "yanshi");
//			intent2.putExtras(b2);
//			startActivity(intent2);
			break;
		case R.id.wanNengTeSe:
			Intent intent3 = new Intent(this, ImgAnimationA.class);
			Bundle b3 = new Bundle();
			b3.putInt("maxPageCount", 6);
			b3.putString("url", "product/zhinengxing/yanshi/wannengtese");
			b3.putString("format", "gif");
			b3.putString("screenFlag", "yanshi");
			intent3.putExtras(b3);
			startActivity(intent3);
			break;
		case R.id.liShiLiLv:
//			Intent intent4 = new Intent(this, ImgAnimationA.class);
//			Bundle b4 = new Bundle();
//			b4.putInt("maxPageCount", 19);
//			b4.putString("url", "product/zhinengxing/yanshi/zichanguanli");
//			b4.putString("format", "gif");
//			b4.putString("screenFlag", "jianjie");
//			intent4.putExtras(b4);
//			startActivity(intent4);
			break;
		case R.id.zhiChanGuanLi:
			Intent intent5 = new Intent(this, ImgAnimationA.class);
			Bundle b5 = new Bundle();
			b5.putInt("maxPageCount", 19);
			b5.putString("url", "product/shouhux/yanshi/zichanguanli");
			b5.putString("format", "gif");
			b5.putString("screenFlag", "jianjie");
			intent5.putExtras(b5);
			startActivity(intent5);
			break;
		case R.id.wanNengTouZi:
			Intent intent6 = new Intent(this, ImgAnimationA.class);
			Bundle b6 = new Bundle();
			b6.putInt("maxPageCount", 4);
			b6.putString("url", "product/zhinengxing/yanshi/wannenghetouzi");
			b6.putString("format", "gif");
			b6.putString("screenFlag", "yanshi");
			intent6.putExtras(b6);
			startActivity(intent6);
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
//			Toast.makeText(getApplicationContext(), "������", Toast.LENGTH_SHORT)
//					.show();
//			break;
		case R.id.canBaoTiShi:
			Intent intent8 = new Intent(this, ImgAnimationA.class);
			Bundle b8 = new Bundle();
			b8.putInt("maxPageCount", 6);
			b8.putString("url", "product/zhinengxing/yanshi/canbaotishi");
			b8.putString("format", "gif");
			b8.putString("screenFlag", "yanshi");
			intent8.putExtras(b8);
			startActivity(intent8);
			break;

		// �ڶ�����linear����¼�
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
			showHongLiFanHuan(23, 34);
			break;
		case R.id.hongLiBaoXianJinELayout5_2:
			showHongLiFanHuan(0, 22);
			break;
		default:
			break;
		}
	}

	public void DialogSelect(String dialogLevel) {
		hongli.setLevel(dialogLevel);
		levelHongLiTV
				.setText(znx.getWanNengZhangHuLevel(znx
						.getLevel(dialogLevel)));
		hongLiMap = setData();
		setHongLiBXJEData();
		dialog.cancel();
		setFloatingVisible();
	}

	public void setHongLiBXJEData() {
		hongLiBaoXianJinE1_2.setText(znx.getHongLiByAge(hongLiMap, 80));
		hongLiBaoXianJinE2_2.setText(znx.getHongLiByAge(hongLiMap, 60));
		hongLiBaoXianJinE3_2.setText(znx.getHongLiByAge(hongLiMap, 35));
		hongLiBaoXianJinE4_2.setText(znx.getHongLiByAge(hongLiMap, 25));
		hongLiBaoXianJinE5_2.setText(znx.getHongLiByAge(hongLiMap, 18));
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

		// ����������
		setFloatingInvisible();
		dialog = new AlertDialog.Builder(this)
				.setTitle("�����ֽ��ֵ�˻��ȼ�")
				.setSingleChoiceItems(items, 0,
						new DialogInterface.OnClickListener() {

							@Override
							public void onClick(DialogInterface dialog,
									int which) {
								switch (which) {
								case 0:
									String dialogLevel = Define.Level_Low;
									// ���ѡ���ķ������������ݣ������˳��Ի�����ʾ������
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
				.setNegativeButton("ȡ��", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						setFloatingVisible();
						// Toast.makeText(DialogActivity.this,"��һ��Ҳ��ϲ��������",
						// Toast.LENGTH_SHORT).show();
					}
				}).setCancelable(false).show();
		dialog.setCanceledOnTouchOutside(false);
	}

	public void showHongLiFanHuan(int start, int end) {
		setFloatingInvisible();
		// ������Ĺ�
		String items[] = znx.findHongLi(hongLiMap, start, end);
		// Toast.makeText(getApplicationContext(), "click hongLiBaoXianJinE_2",
		// Toast.LENGTH_SHORT).show();

		AlertDialog dialog = new AlertDialog.Builder(this)
				.setTitle("�����ֽ��ֵ�˻�").setItems(items, null)
				.setNegativeButton("ȡ��", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						setFloatingVisible();
						// Toast.makeText(DialogActivity.this,"��һ��Ҳ��ϲ��������",
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
	 * ��ʼ��������ť
	 */
	private void initFloatView() {
		// ��ȡWindowManager
		wm = (WindowManager) getApplicationContext().getSystemService("window");
		// ����LayoutParams(ȫ�ֱ�������ز���
		wmParams = new WindowManager.LayoutParams();

		wmParams.type = LayoutParams.TYPE_PHONE; // ����window type
		wmParams.format = PixelFormat.RGBA_8888; // ����ͼƬ��ʽ��Ч��Ϊ����͸��
		// ����Window flag
		wmParams.flags = LayoutParams.FLAG_NOT_TOUCH_MODAL
				| LayoutParams.FLAG_NOT_FOCUSABLE;

		wmParams.width = WindowManager.LayoutParams.WRAP_CONTENT;
		wmParams.height = WindowManager.LayoutParams.WRAP_CONTENT;
		// ����������ť
		createCenterFloatView();
		createBottomSmallFloatView();
		createBottomFloatView();

	}

	/**
	 * �����м�������ť
	 */
	private void createCenterFloatView() {
		Display display = getWindowManager().getDefaultDisplay();
		int height = display.getHeight();
		int width = display.getWidth();

		LayoutInflater inflater = (LayoutInflater) this
				.getSystemService(LAYOUT_INFLATER_SERVICE);
		view = inflater.inflate(R.layout.ps_zhinengxing_center_float, null);

		Bitmap myBitmap = BitmapFactory.decodeResource(getResources(),
				R.drawable.img2_1);// ���ͼƬ��Դ
		// �����������ڳ�������
		wmParams.width = DensityUtil.dip2px(this, 70);
		wmParams.height = DensityUtil.dip2px(this, 30);

		wmParams.x = DensityUtil.dip2px(this, 50);
		wmParams.y = DensityUtil.dip2px(this, -140);
		// ������������
		wmParams.gravity = Gravity.CENTER_VERTICAL | Gravity.LEFT;
		wm.addView(view, wmParams);
	}

	/**
	 * �����ϱ�С����ͼƬ
	 */
	private void createBottomSmallFloatView() {
		Display display = getWindowManager().getDefaultDisplay();
		int height = display.getHeight();
		int width = display.getWidth();

		LayoutInflater inflater = (LayoutInflater) this
				.getSystemService(LAYOUT_INFLATER_SERVICE);
		topView = inflater.inflate(R.layout.ps_zhinengxing_top_float, null);

		topView.setVisibility(View.GONE);

		wmParams.width = DensityUtil.dip2px(this, 100);
		wmParams.height = DensityUtil.dip2px(this, 30);
		Bitmap myBitmap = BitmapFactory.decodeResource(getResources(),
				R.drawable.shouhuxing_floatingcenter);// ���ͼƬ��Դ
		wmParams.x = DensityUtil.dip2px(this, 315);

		wmParams.y = DensityUtil.dip2px(this, 3);

		// ������������
		wmParams.gravity = Gravity.LEFT | Gravity.TOP;
		wm.addView(topView, wmParams);
	}

	/**
	 * �����±�������ť
	 */
	private void createBottomFloatView() {
		Display display = getWindowManager().getDefaultDisplay();
		int height = display.getHeight();
		int width = display.getWidth();

		LayoutInflater inflater = (LayoutInflater) this
				.getSystemService(LAYOUT_INFLATER_SERVICE);

		bottomView = inflater.inflate(R.layout.ps_zhinengxing_bottom_float, null);

		// �²����������ʼ��TextView,�����ü���
		productZeRen = (TextView) bottomView.findViewById(R.id.productZeRen);
		zeRenMianChu = (TextView) bottomView.findViewById(R.id.zeRenMianChu);
		wanNengTeSe = (TextView) bottomView.findViewById(R.id.wanNengTeSe);
		liShiLiLv = (TextView) bottomView.findViewById(R.id.liShiLiLv);
		zhiChanGuanLi = (TextView) bottomView.findViewById(R.id.zhiChanGuanLi);
		wanNengTouZi = (TextView) bottomView.findViewById(R.id.wanNengTouZi);
//		fuJiaBaoXian = (TextView) bottomView.findViewById(R.id.fuJiaBaoXian);
		canBaoTiShi = (TextView) bottomView.findViewById(R.id.canBaoTiShi);
		productZeRen.setOnClickListener(this);
		zeRenMianChu.setOnClickListener(this);
		wanNengTeSe.setOnClickListener(this);
		liShiLiLv.setOnClickListener(this);
		zhiChanGuanLi.setOnClickListener(this);
		wanNengTouZi.setOnClickListener(this);
//		fuJiaBaoXian.setOnClickListener(this);
		canBaoTiShi.setOnClickListener(this);

		LinearLayout floatBottom = (LinearLayout) bottomView
				.findViewById(R.id.floatBottom);
		bottomView.setVisibility(View.INVISIBLE);

		// �����������ڳ�������
		wmParams.width = width;
		wmParams.height = DensityUtil.dip2px(this, 30);
		;
		wmParams.x = width / 2 - wmParams.width / 2;
		wmParams.y = height - wmParams.height;

		// ������������
		wmParams.gravity = Gravity.LEFT | Gravity.TOP;
		// wm.addView(tv, wmParams);
		wm.addView(bottomView, wmParams);
	}

	// ���϶�������
	final int baoZhang_step1 = 1;
	final int baoZhang_step2 = 2;
	final int baoZhang_step3 = 3;

	// ������������
	final int jianKang_step1 = 4;

	final int jianKang_step2 = 5;

	final int jianKang_step3 = 6;

	// �ֺ춯������
	final int fenHong_step1 = 7;

	final int fenHong_step2 = 8;
	final int fenHong_step3 = 9;
	final int fenHong_step4 = 10;
	final int fenHong_step5 = 11;
	final int fenHong_step6 = 12;
	final int fenHong_step7 = 13;

	// ���⶯������
	final int yiWai_step1 = 14;

	final int yiWai_step2 = 15;
	final int yiWai_step3 = 16;

	// �²���С������
	final int bottomSmallFloat = 17;

	// �²���������
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
			// step++;
			yiWaiShenGuLinear.startAnimation(yiDongAndJianBian);
			jiBingShenGuLinear.startAnimation(yiDongAndJianBian);
			yiWaiShenGuLinear.setVisibility(View.VISIBLE);
			jiBingShenGuLinear.setVisibility(View.VISIBLE);
			yiWaiShenGuBaoZhangJin.setText(baoE + "��Ԫ");
			jiBingShenGuBaoZhangJin.setText(baoE + "��Ԫ");
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
			jianKangZhongJiJinLinear.setVisibility(View.VISIBLE);
			jKZhongJiJin.setText(zhongJiBaoE + "��Ԫ");
			// manQiBaoXianJinLinear1_Text1.setText(Arithmetic4Double.multi(
			// Double.valueOf(baoE), 2)
			// + "��Ԫ");
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

		// �ֺ��˻���Ŀ�Ķ���
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
			yiWaiYiLiaoLinear.startAnimation(xiangShangYiDong);
			if (isfuJia) {
				jiBingShangCanLinear.setVisibility(View.VISIBLE);
				jiBingShangCanLinear.startAnimation(xiangShangYiDong);
			}
			yiWaiYiLiaoBaoZhangJin.setText(yiLiaoBaoE + "��Ԫ");
			break;
		case yiWai_step3:
			yiWaitext2.setVisibility(View.VISIBLE);
			yiWaitext2.startAnimation(yiDongAndJianBian);
			break;
		// // �²�������
		case bottomFloat:
			bottomView.setVisibility(View.VISIBLE);
			bottomView.startAnimation(jianBian);
			break;
		default:
			step--;
			break;

		}
		// �������һ��
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
			// �ֺ��˻���Ŀ�Ķ���
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
//		case fenHong_step7:
//			step--;
//			fengHongLinear.setVisibility(View.VISIBLE);
//			hongLiBaoXianJinELayout5_2.setVisibility(View.GONE);
//			hongLiBaoXianJinELayout5_2.startAnimation(xiangZuoYiDong);
//			break;
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
			if (isfuJia) {
				jiBingShangCanLinear.setVisibility(View.INVISIBLE);
			}
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

		// ��ֹһ����������һ��
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
		// if(tagFloating){
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
//		super.onBackPressed();
//		Intent intent = new Intent(this, BaoXianProductA.class);
//		intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//		startActivity(intent);
		Intent intent4 = new Intent(this, ZhiNengXingPlanA.class);
		startActivity(intent4);
		finish();
		step = 0;
	}
	
	public ArrayList<HongLi> setData(){
		int ageFromUI=hongli.getAge();
		//���ʲ�ѯ
		getData();
		// �ؼ����ϳɱ��ļ���
		ArrayList<HongLi> zhongJiBaoFeiD = znx.findZhongJiBaoFei(hongli,
				this,zjFeiLv);
		
		//ҽ�Ʊ��ϳɱ��ļ���
		Double yiLiaoBaoZhang = znx.findYiLiaoBaoFei(String.valueOf(hongli.getYiWaiBaoE()), this);	
		
		//���Ᵽ�ϳɱ�
		ArrayList<HongLi> huoMianBaoZhang = null;
		HongLi huoMianHongLi = null;
		if(!"".equals(huoMian)){
			huoMianBaoZhang = znx.findHuoMianBaoFei(hongli,this,
					hmFeiLv,hongli.getYear(),wxxsFeiLv,hongli.getZhuBaoF());
		}else{
			huoMianBaoZhang = new ArrayList<HongLi>();
			for(int i=1;i<=hongli.getYear() - 1;i++){
				huoMianHongLi = new HongLi();
				huoMianHongLi.setHongli("0");
				huoMianBaoZhang.add(huoMianHongLi);
			}
		}
		//���ӱ��յ�ֵ=�ؼ����ϳɱ�+ҽ�Ʊ��ϳɱ�+���Ᵽ�ϳɱ�
		ArrayList<HongLi> fuJiaXian = znx.findFuJiaXianBaoFei(hongli,zhongJiBaoFeiD,yiLiaoBaoZhang,huoMianBaoZhang,hongli.getYear());
		
		//���ձ��ϳɱ��ļ���
		ArrayList<HongLi> chuShiFeiYong = new ArrayList<HongLi>();
		Double BeginValue = 0.0;
		int bF = Integer.valueOf(hongli.getZhuBaoF());
		int year = Integer.valueOf(hongli.getYear());
		for (int i = 1; i <= hongli.getYear(); i++) {
			HongLi hong = new HongLi();
			// �ڽ�����С��10000�ĳ�ʼ����
			if (bF <= 10000) {
					if (i == 1) {
							BeginValue = Arithmetic4Double.multi(bF, 0.5);
						} else if (i == 2) {
							BeginValue = Arithmetic4Double.multi(bF, 0.25);
						} else if (i == 3) {
							BeginValue = Arithmetic4Double.multi(bF, 0.15);
						} else if (i == 4 | i == 5) {
							BeginValue = Arithmetic4Double.multi(bF, 0.1);
						} else if (i >= 6) {
							BeginValue = Arithmetic4Double.multi(bF, 0.05);
						}
			}else{
					if (i == 1) {
						BeginValue = Arithmetic4Double.add(
								Arithmetic4Double.multi(10000, 0.5), 
								Arithmetic4Double.multi(
								Arithmetic4Double.sub(bF, 10000),0.03));
					} else if (i == 2) {
						BeginValue = Arithmetic4Double.add(
								Arithmetic4Double.multi(10000, 0.25),
								Arithmetic4Double.multi(
										Arithmetic4Double.sub(bF, 10000),0.03));
					} else if (i == 3) {
						BeginValue = Arithmetic4Double.add(
								Arithmetic4Double.multi(10000, 0.15), 
								Arithmetic4Double.multi(
										Arithmetic4Double.sub(bF, 10000),0.03));
					} else if (i == 4 || i == 5) {
						BeginValue = Arithmetic4Double.add(
								Arithmetic4Double.multi(10000, 0.1),
								Arithmetic4Double.multi(
										Arithmetic4Double.sub(bF, 10000),0.03));
					} else if (i >= 6) {
						BeginValue = Arithmetic4Double.add(
								Arithmetic4Double.multi(10000, 0.05), 
								Arithmetic4Double.multi(
										Arithmetic4Double.sub(bF, 10000),0.03));
					}
			}
			hong.setHongli(BeginValue.toString());
			chuShiFeiYong.add(hong);
		}	
		
		//�����ۻ����ѵ�ֵ
		ArrayList<HongLi> arrayHL = znx.findLeiJiBaoFei(bF,year);
		
		//�����ֽ𼯺�
		ArrayList<HongLi> bdxjArray = new ArrayList<HongLi>();
		HongLi bdxjHL = null;
		Double zxFL = 0.0;
		Double wanNeng = 0.0;
		int k = 0,dataSize = 104;
//		if(ageFromUI == 0){
//			dataSize = 103 - ageFromUI + 1;
//		}else{
//			dataSize = 103 - ageFromUI + 2;
//		}
		for(int l = ageFromUI; l <= dataSize; l++){
			bdxjHL = new HongLi();
			// ���յķ��ʣ�������ı仯���仯
			for (HongLi entity : zxFeiLv) {
				if (entity.getAge() == ageFromUI+k && entity.getSex().equals(hongli.getSex())) {
					zxFL = entity.getFeiLv();
					k++;
					break;
				}
			}
			if(l == ageFromUI){
				//���ձ��ϳɱ�=(�ڽ�����-��ʼ����-�����ձ��ϳɱ�/12)*������/1000
				zhuXiangBaoZhang = 
						znx.findZhuXianBaoFei(bF,chuShiFeiYong,fuJiaXian,zxFL);
				
				//�����һ��ĩ�ı����ֽ��ֵ
				baoDanXianJin = znx.findFirstBaoDanXianJin(bF,chuShiFeiYong,fuJiaXian,
						yiLiaoBaoZhang,hongli,this,year,huoMianBaoZhang,zhuXiangBaoZhang
						,zhongJiBaoFeiD);
			}
			//����ڶ����Ժ���ĩ�ı����ֽ��ֵ
			else {
				//18����ǰ�����ձ��ϼ�ֵ�ļ���
				if(l < 18){
					// �ڶ�������ձ��ϳɱ�
					// �õ�һ��ȵ����һ��ı����ֽ��ֵ���
					// ���ۻ�����-��һ��ĩ�ı����ֽ��ֵ-���������˻��ļ�ֵ-�����ձ��ϳɱ�/12��*�ڶ������շ���/1000
					//���������˻���ֵ
					if(k <= year){
					    wanNeng = Arithmetic4Double.sub(bF, Double
									.valueOf(chuShiFeiYong.get(k-1)
											.getHongli()));
					}else{
						wanNeng = 0.0;
					}
						zhuXiangBaoZhang = Arithmetic4Double.div(
								Arithmetic4Double.multi(
								Arithmetic4Double.sub(
								Arithmetic4Double.sub(
								Arithmetic4Double.sub(Double.valueOf(arrayHL.get(k-1).getHongli()), Double.valueOf(baoDanXianJin)),
								wanNeng
								),Arithmetic4Double.div(Double.valueOf(fuJiaXian.get(k-1).getHongli()),12)),
								zxFL),1000);
			}
				//18���Ժ�����ձ��ϼ�ֵ�ļ���
				else{
					if(k <= year){
					    wanNeng = Arithmetic4Double.sub(bF, Double
									.valueOf(chuShiFeiYong.get(k-1)
											.getHongli()));
					}else{
						wanNeng = 0.0;
					}
					//18�������ձ��ϼ�ֵ = ������ *���ձ���/1000
					zhuXiangBaoZhang = Arithmetic4Double.div(Arithmetic4Double.multi(zxFL, hongli.getBaoE()), 1000);
			}
			//����ڶ���ĩ�ı����ֽ��ֵ
			//�ֵ�һ���ºͺ����·ݵ�����
				if(zhuXiangBaoZhang>0){
					baoDanXianJin = znx.findSecondBaoDanXianJin(bF,fuJiaXian,
							hongli,this,zhuXiangBaoZhang,wanNeng,baoDanXianJin,k);	
				}else{
					zhuXiangBaoZhang = 0.0;
					baoDanXianJin = znx.findSecondBaoDanXianJin(bF,fuJiaXian,
							hongli,this,zhuXiangBaoZhang,wanNeng,baoDanXianJin,k);	
				}
			}
			bdxjHL.setHongli(baoDanXianJin);
			bdxjArray.add(bdxjHL);
		}
		
		ArrayList<HongLi> hlArray = new ArrayList<HongLi>();
		for (int i = 0; i < bdxjArray.size(); i++) {
			HongLi hl = new HongLi();
			if(i == 0){
				hl.setAge(ageFromUI);
			}else{
				hl.setAge(ageFromUI+i);
			}
			Double baoDanValue = Arithmetic4Double.round(Double.valueOf(bdxjArray.get(i).getHongli()),2);
			if(baoDanValue>0){
				hl.setHongli(Arithmetic4Double.doubleToString(baoDanValue));
			}else{
				hl.setHongli("0");
			}

			
			
			hlArray.add(hl);
		}
		
//		�޸�����
		for (HongLi entity : hlArray) {
			entity.setAge(entity.getAge()+1);
			
		}
		
		
		return hlArray;
	}


	private void getData() {
		//���շ���
		zxFeiLv = DBDAO.findZhiNengXingFuJiaXianBaoFei(hongli, this, Define.DB_Type_FuJiaBaoFei_ZhiNengXing);
		//�ؼ�����
		zjFeiLv = DBDAO.findZhiNengXingFuJiaXianBaoFei(hongli, this, Define.DB_Type_FuJiaBaoFei_ZhiNengXingZhongJi);
		//�������
		hmFeiLv = DBDAO.findZhiNengXingFuJiaXianBaoFei(hongli, this, Define.DB_Type_FuJiaBaoFei_ZhiNengXingHuoMian);
		//����Σ��ϵ��
		wxxsFeiLv = DBDAO.findZhiNengXingFuJiaXianBaoFei(hongli, this, Define.DB_Type_FuJiaBaoFei_ZhiNengXingWeiXianXiShu);
	}
}
