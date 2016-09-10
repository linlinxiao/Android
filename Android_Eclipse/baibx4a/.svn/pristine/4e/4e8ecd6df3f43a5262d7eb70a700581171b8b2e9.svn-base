package com.baoxiao.activity.pay;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;

import com.allinpay.appayassistex.APPayAssistEx;
import com.baoxiao.R;
import com.baoxiao.model.Userb;
import com.baoxiao.service.mine.MineService;
import com.baoxiao.service.pay.PayService;
import com.baoxiao.util.MessageHelper;

/**
 * '支付险种金额'布局界面
 *
 */
public class ProductPay extends Activity implements OnCheckedChangeListener{

	private ImageView imageView;
	private RadioGroup radioGroup;
	private RadioButton oneYearRD,twoYearRD;
	private CheckBox ifuse;
	private TextView zhifu,jinyuanb,dichong,jybaotext,days,tv_amount;
	private Userb userb = null;
	
	public static String orderAmount ; //PaaaCreator的静态方法中需要用到
	public static String userid  ;  	//准备好要传的static值
	public static String type; //11.19  要作为选择的支付1年还是2年判断标签传给罗毅
	public static Integer gold;
	MessageHelper message;
	
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.pay_baibao_product);
		tv_amount = (TextView) findViewById(R.id.tv_amount);
		imageView = (ImageView) findViewById(R.id.baoXianPayBack); 
		radioGroup=(RadioGroup) findViewById(R.id.feiyong); //找到实例
		oneYearRD=(RadioButton) findViewById(R.id.yinian);
		twoYearRD=(RadioButton) findViewById(R.id.liangnian);
		zhifu=(TextView) findViewById(R.id.money);			//找到文本控件
		jinyuanb =(TextView)findViewById(R.id.jinyuanb);
		dichong =(TextView)findViewById(R.id.dichong);
		jybaotext =(TextView)findViewById(R.id.jybaotext);
		days =(TextView)findViewById(R.id.days);
		ifuse = (CheckBox)findViewById(R.id.ifuse);//获取CheckBox实例
		radioGroup.setOnCheckedChangeListener(this);
		
		userid = getSharedPreferences("loading", Activity.MODE_PRIVATE).getString("userid", "");
		
		setText();
		
		
		
		imageView.setOnClickListener(new OnClickListener()  //按左上角回退
		{
			
			@Override
			public void onClick(View arg0)
			{
				finish();
			}
		});
		

//TODO 勾选使用金元宝，提交订单后，若回退back，会出现闪退.已经没有bug了(估计是手机内存不足造成)		
		//1.判断是否选择勾选使用金元宝
//TODO 用户支付后元宝总数应该还要扣除.这个功能还没有弄.    
        ifuse.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) { 
                if(isChecked){
                	//2.1 金元宝除100转换成可以抵扣的多少元
                	if (userb.getGold() != null) {
    					int a = userb.getGold(); //integer转换成int
    					gold = a; //用gold记录当前金元宝数
    					double b =(double)a /100; //int转换成double，即换算出现有金元宝可以抵扣多少元
                        zhifu.setText(String.valueOf(realmoney-b));
                        orderAmount = String.valueOf((int)((realmoney-b)*100));
                        return;
					}
                    zhifu.setText(String.valueOf((int)realmoney));
                    orderAmount = String.valueOf((int)realmoney*100);
                    return;
                }
                zhifu.setText(String.valueOf((int)realmoney));
                orderAmount = String.valueOf((int)realmoney*100);
            } 
        }); 
	}
	
	double realmoney = 0;
	
	public void onCheckedChanged(RadioGroup group, int checkedId) {
		 switch (group.getCheckedRadioButtonId()) {
             case R.id.yinian:
            	 zhifu.setText("300");
            	 realmoney = 300;
            	 type ="1"; //看选择的是哪个，如果是1年则令tab=1
            	 orderAmount = "30000";
                 tv_amount.setText(String.valueOf(realmoney));
                 break;
 
             case R.id.liangnian:
            	 zhifu.setText("480");
            	 realmoney = 480;
            	 orderAmount = "48000";
            	 type ="2";//看选择的是哪个，如果是2年则令tab=2 
                 tv_amount.setText(String.valueOf(realmoney));
            	 break;
		 }
	}
	
	//11.9 使用线程和handle执行带有网络的操作，获取用户金元宝数并显示出来.
	private void setText(){
		new Thread(new Runnable(){
			public void run(){
				String userid = getSharedPreferences("loading", 0).getString(
						"userid", null);
				userb = MineService.MyAccountNews(userid); //获取用户信息金元宝数
				Message msg = new Message();
				msg.what = 1;
				handler.sendMessage(msg);
			}
		}).start();
	}
	
	private Handler handler = new Handler(new Handler.Callback() {
		@Override
		public boolean handleMessage(Message msg) {
			switch (msg.what) {
			case 1:
				if (userb.getGold() != null) {
					jinyuanb.setText(userb.getGold() + "");
					int a = userb.getGold(); //integer转换成int
					double b =(double) a /100; //int转换成double并计算
					dichong.setText(String.valueOf(b));//double转换成字符串并显示出来
					jybaotext.setText(String.valueOf(b));
				}
				days.setText(userb.getRemainDay());
				break;

			default:
				break;
			}

			return false;
		}
	});
		
	
	public void startPayTest(View view) {
		JSONObject payData = PaaCreator.randomPaa();
		APPayAssistEx.startPay(this, payData.toString(), APPayAssistEx.MODE_PRODUCT);
	}
	public void tijiao(View v){
		startPayTest(v);
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (APPayAssistEx.REQUESTCODE == requestCode) {
			if (null != data) {
				String payRes = null;
				String payAmount = null;
				String payTime = null;
				String payOrderId = null;
				try {
					JSONObject resultJson = new JSONObject(data.getExtras().getString("result"));
					payRes = resultJson.getString(APPayAssistEx.KEY_PAY_RES);
					payAmount = resultJson.getString("payAmount");
					payTime = resultJson.getString("payTime");
					payOrderId = resultJson.getString("payOrderId");
				} catch (JSONException e) {
					e.printStackTrace();
				}
				if (null != payRes && payRes.equals(APPayAssistEx.RES_SUCCESS)) {
					showAppayRes("支付成功！");
					new Thread(networkTask).start();
					
					
//TODO 这里要传数据给后台 					
				}else {
					showAppayRes("支付失败！");
				}
				Log.d("payResult", "payRes: " + payRes + "  payAmount: " + payAmount + "  payTime: " + payTime + "  payOrderId: " + payOrderId);
			}
		}
		super.onActivityResult(requestCode, resultCode, data);
	}
	
	public void showAppayRes(String res) {
		new AlertDialog.Builder(this)
		.setMessage(res)
		.setPositiveButton("确定", null)
		.show();
	}
	
	Runnable networkTask = new Runnable(){
		public void run(){
			if(gold == 0){
				message = PayService.Pay(ProductPay.userid, ProductPay.type);
			}else{
				message = PayService.Pay(ProductPay.userid, ProductPay.type, ProductPay.gold);
			}
		}
	};

}

