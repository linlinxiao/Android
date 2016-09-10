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
	// ������������
	private Animation xiangYouYiDong,xiangZuoYiDong,yiDongAndJianBian, jianBian, suoFang, suoFangBianDa,
			xuanZhuan, xiangXiaYiDong, xiangShangYiDong, suoFangAndXuanZhuan,
			suoFangAndYiDong;
	private AnimationSet set;

	private boolean tagFloating = true;
	// �����漰�����ĸ�����
	private LinearLayout main;
	private LinearLayout leftLinear;
	private LinearLayout rightLinear;
	
	//��ߵ�Բ���е����ݿؼ�
	private TextView floatText1,floatText2,floatText3;
	

	// ������
	private WindowManager wm = null;
	private WindowManager.LayoutParams wmParams = null;
	// �²�������
	private TextView productZeRen, zhiChanGuanLi, LiCaiYouShi, wenTiJieDa,
			fuJiaBaoXian, canBaoTiShi;
	// �²�С�������ֵ
	private TextView hushenfusmallfloat;

	// ��һ����
	private RelativeLayout jianKangZhongJiJinLinear,jianKangQingZhengBaoZhangJinBgLinear;
	private TextView jianKangTitle, jianKangtext1, jianKangtext2,
			yiWaiShenGuBaoZhangJin, jiBingShenGuBaoZhangJin;
	private ImageView jianKangAdd;
	private LinearLayout ll_hushenfu_zj;

	// �ڶ�����
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

	// ��������
	private LinearLayout yiWaiShenGuLinear, jiBingShenGuLinear;
	private TextView baoZhangTitle, baoZhangtext1, baoZhangtext2;

	// ���Ĳ���
	private LinearLayout yiWaiYiLiaoLinear, jiBingShangCanLinear;
	private TextView yiWaiTitle, yiWaitext1, yiWaitext2,
			yiWaiYiLiaoBaoZhangJin, jiBingShangCanBaoZhangJin;

	// ����
	private String sex;
	private String age;
	private String period;
	// �ȼ�
	private String level;
	// ����
	private String baoE;
	private String zhongJiBaoE;
	private String yiWaiBaoE;
	// ����
	private double zhuBaoFeiD, zhongJiBaoFeiD, huoMianBaoFeiD, totalBaoFeiD,yiWaiBaoFeiD;
	private View view = null;
	private View bottomView = null;
	private View bottomSmallView = null;
	private HuShenFuService hsfService;
	private ArrayList<HongLi> hongLiMap;

	// // ֵ

	private AlertDialog dialog;
	
	private boolean HomeFlag = false; 
	private String DialogFlag = "false";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// ���ú�����ȫ�����ޱ���
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.ps_hushenfushow);

		// ��ʼ���ؼ�
		initView();
		// ��ʼ������
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

		// ��ʼ��������ť
		initFloatView();

		hsfService = new HuShenFuService();
		// �ڶ����ֳ�ʼ��
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
		// �ۻ������
		// setLeiJiShengCunJin();
		// ����
		setHongLiBXJEData();
		floatText1.setText("��" + period + "��");
		floatText2.setText("ÿ��" + totalBaoFeiD+Define.Yuan);
		floatText3.setText("��"
				+ (Arithmetic4Double.sub(Arithmetic4Double.multi(
						Double.valueOf(period), totalBaoFeiD), huoMianBaoFeiD))
				+ "Ԫ");
	}

	private void initView() {

		// �������ʼ��
		main = (LinearLayout) findViewById(R.id.main);
		main.setOnClickListener(this);
		leftLinear = (LinearLayout) findViewById(R.id.leftLinear);
		rightLinear = (LinearLayout) findViewById(R.id.RightLinear);
		leftLinear.setOnClickListener(this);
		// rightLinear.setClickable(clickable)
		rightLinear.setOnClickListener(this);

		//��ߵ�Բ���е����ݿؼ�
		floatText1 = (TextView)findViewById(R.id.floatText1);
		floatText2 = (TextView)findViewById(R.id.floatText2);
		floatText3 = (TextView)findViewById(R.id.floatText3);

		// �ؼ��ĳ�ʼ���͵������
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
		jKQingZhengZhongJiJin = (TextView) findViewById(R.id.jKQingZhengZhongJiJin);

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
		// �����ĳ�ʼ��
		// λ�ƶ���
		xiangYouYiDong = AnimationUtils.loadAnimation(this,
				R.anim.ps_shouhuxshow_anim_translateleftin);
		xiangZuoYiDong = AnimationUtils.loadAnimation(this,
				R.anim.ps_shouhuxshow_anim_translaterightin);
		yiDongAndJianBian = AnimationUtils.loadAnimation(
				getApplicationContext(), R.anim.ps_shouhuxshow_anim_translateleftin);
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
		xiangShangYiDong = AnimationUtils.loadAnimation(
				getApplicationContext(),
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
			//�ڸ���ĳ����������
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
		// ��ҳ���е㲻ͬ������ѡ��ҳ��
		case R.id.wenTiJieDa:
			Intent intent5 = new Intent(this, ProductWenTiJDA.class);
			startActivity(intent5);
			break;
//		case R.id.fuJiaBaoXian:
//			Toast.makeText(getApplicationContext(), "������", Toast.LENGTH_LONG).show();
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

		// �ڶ�����linear����¼�
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
		// ����������
		setFloatingInvisible();
		dialog = new AlertDialog.Builder(this)
				.setTitle("�����ۻ������ȼ�")
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
		String items[] = hsfService.findHongLi(hongLiMap, start, end);
		DialogFlag = "true";

		AlertDialog dialog = new AlertDialog.Builder(this).setTitle("�����ۻ�����")
				.setItems(items, null)
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
		// ��ȡ��Ļ���

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
		// createRightFloatView();
//		createTopFloatView();
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
		view = inflater.inflate(R.layout.ps_hushenfu_center_float, null);
		Bitmap myBitmap = BitmapFactory.decodeResource(getResources(),
				R.drawable.img2_1);// ���ͼƬ��Դ
		// �����������ڳ�������
		wmParams.width = DensityUtil.dip2px(this, 50);
		wmParams.height = DensityUtil.dip2px(this, 30);

		wmParams.x = DensityUtil.dip2px(this, 96);
		wmParams.y = DensityUtil.dip2px(this, -10);
		// ������������
		wmParams.gravity = Gravity.CENTER_VERTICAL | Gravity.LEFT;
		wm.addView(view, wmParams);
	}

	/**
	 * �����±�С����ͼƬ
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

		// �����������ڳ�������
		// wmParams.width = 120;
		// wmParams.height = 180;
		wmParams.width = DensityUtil.dip2px(this, 100);
		wmParams.height = DensityUtil.dip2px(this, 30);
		Bitmap myBitmap = BitmapFactory.decodeResource(getResources(),
				R.drawable.shouhuxing_floatingcenter);// ���ͼƬ��Դ
		wmParams.x = width / 2 + wmParams.width / 8;

		wmParams.y = DensityUtil.dip2px(this, 240);

		// ������������
		wmParams.gravity = Gravity.LEFT | Gravity.TOP;
		wm.addView(bottomSmallView, wmParams);
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

		bottomView = inflater.inflate(R.layout.ps_hushenfu_bottom_float, null);

		// �²����������ʼ��TextView,�����ü���
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

	final int jianKang_step4 = 7;

	// �ֺ춯������
	final int fenHong_step1 = 8;

	final int fenHong_step2 = 9;
	final int fenHong_step3 = 14;
	final int fenHong_step4 = 13;
	final int fenHong_step5 = 12;
	final int fenHong_step6 = 11;
	final int fenHong_step7 = 10;

	// ���⶯������
	final int yiWai_step1 = 15;

	final int yiWai_step2 = 16;
	final int yiWai_step3 = 17;
	// �²���С������
	final int botoomSmallFloat = 18;

	// �²���������
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
			ll_hushenfu_zj.setVisibility(View.VISIBLE);
			jianKangAdd.startAnimation(suoFang);
			jianKangZhongJiJinLinear.setVisibility(View.VISIBLE);
			jianKangAdd.setVisibility(View.VISIBLE);
			jKZhongJiJin.setText(zhongJiBaoE + "��Ԫ");
			jKQingZhengZhongJiJin.setText(Arithmetic4Double.multi(
					Integer.valueOf(zhongJiBaoE), 0.2)
					+ "��Ԫ");
			// manQiBaoXianJinLinear1_Text1.setText(Arithmetic4Double.multi(
			// Double.valueOf(baoE), 2)
			// + "��Ԫ");
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

			//�ֺ��˻���Ŀ�Ķ���
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
			yiWaiYiLiaoBaoZhangJin.setText(yiWaiBaoE + "��Ԫ");
			jiBingShangCanBaoZhangJin.setText(yiWaiBaoE + "��Ԫ");
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
			//�ֺ��˻���Ŀ�Ķ���
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

//		 ��ֹһ����������һ��
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
		//����home�����غ���¼�
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
