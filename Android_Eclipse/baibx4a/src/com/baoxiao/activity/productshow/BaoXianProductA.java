package com.baoxiao.activity.productshow;

import java.util.ArrayList;
import java.util.HashMap;
import com.baoxiao.R;
import com.baoxiao.activity.pay.ProductPay;
import com.baoxiao.activity.productshow.baiwanrwx.BaiWanRWXA;
import com.baoxiao.activity.productshow.childrenPeace.ChildrenPingAnFuA;
import com.baoxiao.activity.productshow.hushenfu.HuShenFuA;
import com.baoxiao.activity.productshow.pinganfu.PingAnFuA;
import com.baoxiao.activity.productshow.shijitianshi.ShiJiTSA;
import com.baoxiao.activity.productshow.shouhux.ShouHuXA;
import com.baoxiao.activity.productshow.xinli.XinLiA;
import com.baoxiao.activity.productshow.xinsheng.XinShengA;
import com.baoxiao.activity.productshow.xinxiang.XinXiangA;
import com.baoxiao.activity.productshow.zhinengxing.ZhiNengXingA;
import com.baoxiao.activity.productshow.zhiyuerensheng.ZhiYueRenShengA;
import com.baoxiao.activity.productshow.zunyurensheng.ZunYuRenShengA;
import com.baoxiao.service.productshow.ProductService;
import com.baoxiao.util.CustomProgressDialog;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

/**
 * '���ղ�Ʒ�б���ʾ'���ֽ���
 *
 */
public class BaoXianProductA extends Activity implements OnClickListener,
		OnItemClickListener
{
	protected static final int GETPRODUCT = 0;
	private ListView listView;
	private ImageView baoXianProductBack, baoXianProductTop,
			baoXianProductDown;
	private TextView baoXianProductPay;
	private int[] images = new int[]{
			R.drawable.product_pinganfu,//0
			R.drawable.product_children_icon,//1
			R.drawable.product_shouhuxing,//2
			R.drawable.product_xinsheng,//3
			R.drawable.product_sjts,//4
			R.drawable.product_hushenfu,//5
			R.drawable.product_wbrwx,//6
			R.drawable.product_xinli,//7
			R.drawable.product_zhinengxing,//8
			R.drawable.product_zhiyuerensheng,//9
			R.drawable.product_xinxiang,//10
			R.drawable.product_zunhongrensheng//11
			};
//	private String[] titles = new String[]{
//	"ƽ����","�ٶ�ƽ����","�ػ���","��ʢ","������ʹ", "����","����������","����","������","��������","����","�������",
	
//	private String[] titles = new String[]{
//			"ƽ����","�ٶ�ƽ����",
//			"������","��������",
//			"������ʹ","����",
//			"��ʢ","����",
//			"����","�ػ���",
//			"�������","����������"};
	private String[] titles = new String[]{
			"ƽ����","�ٶ�ƽ����","�ػ���","��ʢ","������ʹ", "����",
			"����������","����","������","��������","����","�������"};
	private String[] contents = new String[]{
			"ƽ�����������콢��Ʒ",//0
			"������ȫ����ٶ���������",//1
			"������Ǯ���߶�ϵ��ٶ�����ͱ���",//2
			"����ʵ�õķֺ��ͽ�������",//3
			"������Ǯ�������ϵķֺ��ͱ���",//4
			"�ṩ��֢���ؼ����ϵķֺ��ͽ�������",//5
			"�������Ѿ���ӵ�а����ϵķ����ͱ���",//6
			"����һ�졢���ر��ϵ���ȫ����",//7
			"���㺢��һ�����������������",//8
			"����ȫ�桢������ȡ��������",//9
			"˫���������������ϵķֺ��ͱ���",//10
			"�и߶���ʿר��������ͱ���"//11
			};
	// 2��ʾ3������0��ʼ
	private ArrayList<HashMap<String, Object>> data = null;
	private String userId;
	private CustomProgressDialog cpd;
	private SimpleAdapter adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_PROGRESS);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.baoxianproduct);
		userId = getSharedPreferences("loading", Activity.MODE_PRIVATE)
				.getString("userid", "");
		listView = (ListView) findViewById(R.id.baoXianProduct);
		baoXianProductBack = (ImageView) findViewById(R.id.baoXianProductBack);
		baoXianProductTop = (ImageView) findViewById(R.id.baoXianProductTop);
		baoXianProductDown = (ImageView) findViewById(R.id.baoXianProductDown);
		baoXianProductPay = (TextView) findViewById(R.id.baoXianProductPay);

		createListAdapter();

		baoXianProductBack.setOnClickListener(this);
		baoXianProductTop.setOnClickListener(this);
		baoXianProductDown.setOnClickListener(this);
		baoXianProductPay.setOnClickListener(this);

		adapter = new SimpleAdapter(getApplicationContext(), data,
				R.layout.baoxianproductlist_item, new String[]
				{ "image","title","content","star","new","hot"}, new int[]
				{ R.id.baoXianProductPicture, R.id.baoXianProductTitle,
				R.id.baoXianProductContent,R.id.baoXianProductStar,
				R.id.iv_product_new,R.id.iv_product_hot });
		listView.setAdapter(adapter);
		listView.setOnItemClickListener(this);

		getProduct();
	}

	private void getProduct() {
		if (cpd == null) {
			cpd = new CustomProgressDialog(this, "����ƴ��������...", R.anim.frame_anim);
		}cpd.show();
		new Thread(new Runnable() {
			@Override
			public void run() {
				String messages = ProductService.getHttpProduct(userId);
				handler.sendMessage(handler.obtainMessage(GETPRODUCT, messages));
			}
		}).start();
	}
	private Handler handler = new Handler(new Handler.Callback() {
		@Override
		public boolean handleMessage(Message msg) {
			switch (msg.what) {
			case GETPRODUCT:
				cpd.dismiss();
				String messages = (String)msg.obj;
				if (messages ==null) {break;}
				if (!messages.contains("all")){break;}

				for (int i = 0; i < images.length; i++)
				{
					HashMap<String,Object> map = data.get(i);
					map.put("star", R.drawable.baoxianproductstaryellow);
					data.remove(i);
					data.add(i, map);
				}
				adapter.notifyDataSetChanged();
				break;
			
			default:
				break;
			}
			return false;
		}
	});
	public void createListAdapter()
	{
		setProgressBarVisibility(true);
		data = new ArrayList<HashMap<String, Object>>();
		HashMap<String, Object> map = null;
		for (int i = 0; i < images.length; i++)
		{
			map = new HashMap<String, Object>();
			map.put("image", images[i]);
			map.put("title", titles[i]);
			map.put("content", contents[i]);
			map.put("new", android.R.color.transparent);
			map.put("hot", R.drawable.hot);
			if (i == 0) {
				map.put("new", R.drawable.product_new);
			}
			if (i == 1) {
				map.put("new", R.drawable.product_new);
			}
			map.put("star", R.drawable.baoxianproductstar);
//			String messages = ProductService.getHttpProduct(userId);
//			if (messages.contains("all"))
//			{
//				map.put("star", R.drawable.baoxianproductstaryellow);
//			} else 
//			{
//				map.put("star", R.drawable.baoxianproductstar);
//			}
			data.add(map);
		}
	}

	@Override
	public void onClick(View v){
		switch (v.getId())
		{
		case R.id.baoXianProductBack:
			finish();
			break;
		case R.id.baoXianProductPay:
			Intent intent2 = new Intent(this, ProductPay.class);
			startActivity(intent2);
			break;
		case R.id.baoXianProductTop:
			// Intent intent3 = new Intent(this,HomePageA.class);
			// startActivity(intent3);
			Toast.makeText(getApplicationContext(), "�������", Toast.LENGTH_SHORT)
					.show();
			break;
		case R.id.baoXianProductDown:
			// Intent intent4 = new Intent(this,HomePageA.class);
			// startActivity(intent4);
			Toast.makeText(getApplicationContext(), "�������", Toast.LENGTH_SHORT)
					.show();
			break;

		default:
			break;
		}

	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, final int position,
			long id)
	{
		SharedPreferences isActive = getSharedPreferences("isActive", Activity.MODE_PRIVATE);
		String result = isActive.getString("result", null);
		if (result == null) {return;}
		String message = isActive.getString("message", null);
		if ("2".equals(result)) {
			new AlertDialog.Builder(this).setTitle("��ʾ").setMessage(message)
			.setPositiveButton("ȷ��", new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) {
					show(position);
				}
			})
			.show();
			return;
		}
		if ("1".equals(result)) {
			show(position);
			return;
		}
		new AlertDialog.Builder(this).setTitle("��ʾ").setMessage(message)
		.setPositiveButton("ȷ��", null).show();
//		1:����״̬
//		2:3���������ʱ��
//		3:�豸��ͬ
//		4:����
//		5:δ����
//		6:�û�������
//		7:�Ҳ���ע��ʱ��
	}

	private void show(int position) {
		switch (position){
		case 0:
//			ƽ����
			startActivity(new Intent(BaoXianProductA.this, PingAnFuA.class));
			break;
		case 1:
//			�ٶ�ƽ����
			startActivity(new Intent(BaoXianProductA.this, ChildrenPingAnFuA.class));
			break;
//			"�ػ���",
		case 2:
			startActivity(new Intent(BaoXianProductA.this,ShouHuXA.class));
			break;
//			"��ʢ",
		case 3:
			startActivity(new Intent(BaoXianProductA.this,XinShengA.class));
			break;
//			"������ʹ",
		case 4:
			startActivity(new Intent(BaoXianProductA.this,ShiJiTSA.class));
			break;
//			 "����"};
		case 5:
			startActivity(new Intent(BaoXianProductA.this,HuShenFuA.class));
			break;
//			"����������",
		case 6:
			startActivity(new Intent(BaoXianProductA.this,BaiWanRWXA.class));
			break;
//			"����",
		case 7:
			startActivity(new Intent(BaoXianProductA.this,XinLiA.class));
			break;
//			"������",
		case 8:
			startActivity(new Intent(BaoXianProductA.this, ZhiNengXingA.class));
			break;
//			"��������",
		case 9:
			startActivity(new Intent(BaoXianProductA.this,ZhiYueRenShengA.class));
			break;
//			"����",
		case 10:
			startActivity(new Intent(BaoXianProductA.this, XinXiangA.class));
			break;
//			"�������", 
		case 11:
			startActivity(new Intent(BaoXianProductA.this,ZunYuRenShengA.class));
			break;

		default:
			break;
		}
	}

}
