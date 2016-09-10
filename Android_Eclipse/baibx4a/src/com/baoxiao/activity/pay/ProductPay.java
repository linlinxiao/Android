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
 * '֧�����ֽ��'���ֽ���
 *
 */
public class ProductPay extends Activity implements OnCheckedChangeListener{

	private ImageView imageView;
	private RadioGroup radioGroup;
	private RadioButton oneYearRD,twoYearRD;
	private CheckBox ifuse;
	private TextView zhifu,jinyuanb,dichong,jybaotext,days,tv_amount;
	private Userb userb = null;
	
	public static String orderAmount ; //PaaaCreator�ľ�̬��������Ҫ�õ�
	public static String userid  ;  	//׼����Ҫ����staticֵ
	public static String type; //11.19  Ҫ��Ϊѡ���֧��1�껹��2���жϱ�ǩ��������
	public static Integer gold;
	MessageHelper message;
	
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.pay_baibao_product);
		tv_amount = (TextView) findViewById(R.id.tv_amount);
		imageView = (ImageView) findViewById(R.id.baoXianPayBack); 
		radioGroup=(RadioGroup) findViewById(R.id.feiyong); //�ҵ�ʵ��
		oneYearRD=(RadioButton) findViewById(R.id.yinian);
		twoYearRD=(RadioButton) findViewById(R.id.liangnian);
		zhifu=(TextView) findViewById(R.id.money);			//�ҵ��ı��ؼ�
		jinyuanb =(TextView)findViewById(R.id.jinyuanb);
		dichong =(TextView)findViewById(R.id.dichong);
		jybaotext =(TextView)findViewById(R.id.jybaotext);
		days =(TextView)findViewById(R.id.days);
		ifuse = (CheckBox)findViewById(R.id.ifuse);//��ȡCheckBoxʵ��
		radioGroup.setOnCheckedChangeListener(this);
		
		userid = getSharedPreferences("loading", Activity.MODE_PRIVATE).getString("userid", "");
		
		setText();
		
		
		
		imageView.setOnClickListener(new OnClickListener()  //�����Ͻǻ���
		{
			
			@Override
			public void onClick(View arg0)
			{
				finish();
			}
		});
		

//TODO ��ѡʹ�ý�Ԫ�����ύ������������back�����������.�Ѿ�û��bug��(�������ֻ��ڴ治�����)		
		//1.�ж��Ƿ�ѡ��ѡʹ�ý�Ԫ��
//TODO �û�֧����Ԫ������Ӧ�û�Ҫ�۳�.������ܻ�û��Ū.    
        ifuse.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) { 
                if(isChecked){
                	//2.1 ��Ԫ����100ת���ɿ��Եֿ۵Ķ���Ԫ
                	if (userb.getGold() != null) {
    					int a = userb.getGold(); //integerת����int
    					gold = a; //��gold��¼��ǰ��Ԫ����
    					double b =(double)a /100; //intת����double������������н�Ԫ�����Եֿ۶���Ԫ
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
            	 type ="1"; //��ѡ������ĸ��������1������tab=1
            	 orderAmount = "30000";
                 tv_amount.setText(String.valueOf(realmoney));
                 break;
 
             case R.id.liangnian:
            	 zhifu.setText("480");
            	 realmoney = 480;
            	 orderAmount = "48000";
            	 type ="2";//��ѡ������ĸ��������2������tab=2 
                 tv_amount.setText(String.valueOf(realmoney));
            	 break;
		 }
	}
	
	//11.9 ʹ���̺߳�handleִ�д�������Ĳ�������ȡ�û���Ԫ��������ʾ����.
	private void setText(){
		new Thread(new Runnable(){
			public void run(){
				String userid = getSharedPreferences("loading", 0).getString(
						"userid", null);
				userb = MineService.MyAccountNews(userid); //��ȡ�û���Ϣ��Ԫ����
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
					int a = userb.getGold(); //integerת����int
					double b =(double) a /100; //intת����double������
					dichong.setText(String.valueOf(b));//doubleת�����ַ�������ʾ����
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
					showAppayRes("֧���ɹ���");
					new Thread(networkTask).start();
					
					
//TODO ����Ҫ�����ݸ���̨ 					
				}else {
					showAppayRes("֧��ʧ�ܣ�");
				}
				Log.d("payResult", "payRes: " + payRes + "  payAmount: " + payAmount + "  payTime: " + payTime + "  payOrderId: " + payOrderId);
			}
		}
		super.onActivityResult(requestCode, resultCode, data);
	}
	
	public void showAppayRes(String res) {
		new AlertDialog.Builder(this)
		.setMessage(res)
		.setPositiveButton("ȷ��", null)
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

