package com.baoxiao.activity.productshow.hushenfu;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

import com.baoxiao.R;
import com.baoxiao.model.HongLi;
import com.baoxiao.provider.DBDAO;
import com.baoxiao.service.productshow.HuShenFuService;
import com.baoxiao.util.Arithmetic4Double;
import com.baoxiao.util.Define;

public class HuShenFuBaoFeiA extends Activity implements OnClickListener{
	//跳转按钮
	private ImageView psBaoFeiBack;
	private TextView psShouHuxBaoFeiProductShow;
	
	//文本变量
	private TextView shiJiTianShiName,zhuBaoE,
		zhongJiBaoE,yiWBaoE,zhuYear,zhongJiYear,yiWaiYear,
		huoMianPeriod,huoMianBaoE,huoMianYear,zhuBaoFei,
		zhongJiBaoFei,huoMianBaoFei,totalBaoFei,yiWaiBaoFei;
	
	//获取值的参数变量
	private String name;
	private String sex;
	private String age;
	//交费年期
	private String period;
	// 等级
    private String level;
	//保额
	private String baoE;
	private String zhongJBaoE;
	private String yiWaiBaoE;
	//保费
	private double zhuBaoFeiD,zhongJiBaoFeiD,huoMianBaoFeiD,yiWaiBaoFeiD,totalBaoFeiD;
	private SQLiteDatabase db;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
//		//设置无标题
//		requestWindowFeature(Window.FEATURE_NO_TITLE);
//		//设置横屏
//		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
		setContentView(R.layout.ps_hushenfubaofei);
		
		initView();
		initData();
		setData();
}
	//初始化控件
		private void initView() {
			psBaoFeiBack = (ImageView) findViewById(R.id.psBaoFeiBack);
			psShouHuxBaoFeiProductShow = (TextView) findViewById(R.id.psShouHuxBaoFeiProductShow);
			
			shiJiTianShiName = (TextView) findViewById(R.id.shiJiTianShiName);
			zhuBaoE = (TextView) findViewById(R.id.zhuBaoE);
			zhongJiBaoE = (TextView) findViewById(R.id.zhongJiBaoE);
			huoMianBaoE = (TextView) findViewById(R.id.huoMianBaoE);
			yiWBaoE = (TextView) findViewById(R.id.yiWaiBaoE);
			zhuYear = (TextView) findViewById(R.id.zhuYear);
			zhongJiYear = (TextView) findViewById(R.id.zhongJiYear);
			huoMianYear = (TextView) findViewById(R.id.huoMianYear);
			yiWaiYear = (TextView) findViewById(R.id.yiWaiYear);
			huoMianPeriod = (TextView) findViewById(R.id.huoMianPeriod);
			zhuBaoFei = (TextView) findViewById(R.id.zhuBaoFei);
			zhongJiBaoFei = (TextView) findViewById(R.id.zhongJiBaoFei);
			huoMianBaoFei = (TextView) findViewById(R.id.huoMianBaoFei);
			yiWaiBaoFei=(TextView) findViewById(R.id.yiWaiBaoFei);
			totalBaoFei = (TextView) findViewById(R.id.totalBaoFei);
			
			
			
			psBaoFeiBack.setOnClickListener(this);
			psShouHuxBaoFeiProductShow.setOnClickListener(this);
		}
	
	//初始化数据
		private void initData() {
			name= this.getIntent().getStringExtra("name");
			sex= this.getIntent().getStringExtra("sex");
			age= this.getIntent().getStringExtra("age");
			period= this.getIntent().getStringExtra("period");
			level = this.getIntent().getStringExtra("level");
			baoE= this.getIntent().getStringExtra("baoE");
			zhongJBaoE= this.getIntent().getStringExtra("zhongJiBaoE");
			yiWaiBaoE= this.getIntent().getStringExtra("yiWaiBaoE");
		}
	
	//数据的设置
	private void setData() {
		//标题的设置
		shiJiTianShiName.setText(name+getResources().getString(R.string.huShenFuBaoFei));
		//数据的设置
		zhuBaoE.setText(baoE+"万");
		zhongJiBaoE.setText(zhongJBaoE+"万");
		zhuYear.setText(period+"年");
		zhongJiYear.setText(period+"年");
	try{
		int yearHuoMian=Integer.valueOf(period);
		huoMianPeriod.setText(yearHuoMian+"年");
		yiWaiYear.setText(String.valueOf(yearHuoMian)+"年");
		yearHuoMian=yearHuoMian-1;
		huoMianYear.setText(String.valueOf(yearHuoMian)+"年");
		
		HuShenFuService huSFService=new HuShenFuService();
		
		HongLi hongli =new HongLi();
		hongli.setName(name);
		hongli.setSex(sex);
		hongli.setAge(Integer.valueOf(age));
		hongli.setYear(Integer.valueOf(period));
		hongli.setLevel(level);
		hongli.setBaoE(Integer.valueOf(baoE));
		if (db == null) {
			db = DBDAO.getSqliteDB(this);
		}
		zhuBaoFeiD=huSFService.findBaoFei(hongli, this, Define.DB_Type_BaoFei_HuShenFu,db);
		hongli.setFenShu(Integer.valueOf(zhongJBaoE));
		zhongJiBaoFeiD=huSFService.findZhongJiBaoFei(hongli, this, Define.DB_Type_FuJiaBaoFei_HuShenFuZhongJi,db);
		huoMianBaoFeiD=huSFService.findHuoMianBaoFei(hongli, this, Define.DB_Type_FuJiaBaoFei_HuShenFuHuoMian,zhuBaoFeiD,db);
		 
		hongli.setYear( Integer.valueOf(this.getIntent().getStringExtra("yiWaiXianPeroid")));
		hongli.setFenShu(Integer.valueOf(this.getIntent().getStringExtra("yiWaiBaoE")));
		yiWaiBaoFeiD=huSFService.findYiWaiBaoFei(hongli, this, Define.DB_Type_FuJiaBaoFei_HuShenFuYiWai,db);
		
		totalBaoFeiD=Arithmetic4Double.round(
				Arithmetic4Double.add(
				Arithmetic4Double.add(
				Arithmetic4Double.add(
						zhuBaoFeiD,zhongJiBaoFeiD),huoMianBaoFeiD),yiWaiBaoFeiD)
						, Define.Round2);
		
//		Toast.makeText(getApplicationContext(), "total="+totalBaoFeiD, Toast.LENGTH_SHORT).show();
		
		huoMianBaoE.setText(String.valueOf(zhuBaoFeiD)+"元");
		yiWBaoE.setText(String.valueOf(yiWaiBaoE)+"万");
		zhuBaoFei.setText(String.valueOf(zhuBaoFeiD)+"元");
		zhongJiBaoFei.setText(String.valueOf(zhongJiBaoFeiD)+"元");
	    huoMianBaoFei.setText(String.valueOf(huoMianBaoFeiD)+"元");
	    huoMianBaoFei.setText(String.valueOf(huoMianBaoFeiD)+"元");
	    yiWaiBaoFei.setText(String.valueOf(yiWaiBaoFeiD)+"元");
		totalBaoFei.setText(String.valueOf(totalBaoFeiD));
	}catch(Exception e){
		e.printStackTrace();
		e.getMessage();
	}
		
//		test
//		Toast.makeText(getApplicationContext(), "name="+this.getIntent().getStringExtra("name")+"  sex="+
//        this.getIntent().getStringExtra("sex")+" level="+this.getIntent().getStringExtra("level"), Toast.LENGTH_SHORT).show();
	}
	
	
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.psBaoFeiBack:
			finish();
			break;
		case R.id.psShouHuxBaoFeiProductShow:
			Intent intent = new Intent(HuShenFuBaoFeiA.this,HuShenFuShowA.class);
			intent.putExtra("name", name);
			intent.putExtra("sex", sex);
			intent.putExtra("age", age);
			intent.putExtra("period", period);
			intent.putExtra("baoE", baoE);
			intent.putExtra("zhongJiBaoE", zhongJBaoE);
			intent.putExtra("yiWaiBaoE", yiWaiBaoE);
			intent.putExtra("level", level);
			
			intent.putExtra("zhuBaoFeiD", zhuBaoFeiD);
			intent.putExtra("zhongJiBaoFeiD", zhongJiBaoFeiD);
			intent.putExtra("YiWaiBaoFeiD", yiWaiBaoFeiD);
			intent.putExtra("huoMianBaoFeiD", huoMianBaoFeiD);
			intent.putExtra("zhongJiBaoE", zhongJiBaoE.getText().toString()
					.replace(Define.Wan, ""));
			
			
			startActivity(intent);
			finish();
			break;

		default:
			break;
		}
	}
	
	

}
