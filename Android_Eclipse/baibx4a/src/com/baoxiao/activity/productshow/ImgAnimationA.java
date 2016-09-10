package com.baoxiao.activity.productshow;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.Toast;

import com.baoxiao.R;
import com.baoxiao.activity.productshow.hushenfu.HuShenFuTiaoKuanA;
import com.baoxiao.activity.productshow.pinganfu.PingAnFuTiaoKuanA;
import com.baoxiao.activity.productshow.shouhux.ShouHuXTiaoKuanA;
import com.baoxiao.activity.productshow.xinli.XinLiTiaoKuanA;
import com.baoxiao.activity.productshow.xinsheng.XinShengTiaoKuanA;
import com.baoxiao.activity.productshow.xinxiang.XinXiangTiaoKuanA;
import com.baoxiao.activity.productshow.yingjuys.YingJuYiShengTiaoKuanA;
import com.baoxiao.util.Define;
import com.baoxiao.util.DepthPageTransformer;

/**
 * ��������ͼƬʵ�����һ���Ч���ķ���ʵ����
 *
 */
public class ImgAnimationA extends Activity
{

	private ViewPager viewPager;
	private LayoutInflater inflater;
	private int maxPageCount;
	private String urlStr = "", format = "", screenFlag = "", tiaokuan = "";
	private String[] imgUrl = null;
	private View item;
	private List<View> list;
	private MyAdapter adapter;

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_img);
		viewPager = (ViewPager) this.findViewById(R.id.view_pager);
		Intent intent = getIntent();
		Bundle bundle = intent.getExtras();
		maxPageCount = bundle.getInt("maxPageCount");
		urlStr = bundle.getString("url");
		format = bundle.getString("format");
		tiaokuan = bundle.getString("tiaokuan");
		imgUrl = new String[maxPageCount];
		list = new ArrayList<View>();
		inflater = LayoutInflater.from(this);
		// ��ͼƬ��ַ��ŵ���������
		// �������item ��ÿһ��viewPager����һ��item��
		for (int i = 0; i < maxPageCount; i++)
		{
			imgUrl[i] = getUrl(i + 1);
			item = inflater.inflate(R.layout.activity_img_item, null);
			list.add(item);
		}

		// ������������ ����װ���������ݽ�ȥ
		adapter = new MyAdapter(this,list, imgUrl);
		viewPager.setAdapter(adapter);
		viewPager.setPageTransformer(true, new DepthPageTransformer());
		viewPager.setOnPageChangeListener(new MyListener());

	}

	/**
	 * ���������������첽����ͼƬ
	 * 
	 */
	int p = 0;
	int s = 0;

	private class MyListener implements OnPageChangeListener
	{
		// 1��ʱ���ʾ���ڻ�����2��ʱ���ʾ��������ˣ�0��ʱ���ʾʲô��û��
		@Override
		public void onPageScrollStateChanged(int state)
		{
			if (state == 1)
			{

			}
			if (state == 2)
			{

			}
			if (state == 0)
			{
				if (p != -1 && s != -1 && s == p)
				{
					// Intent intent = new Intent(ImgAnimationA.this,
					// ShiJiTSA.class);
					// startActivity(intent);
					if (p == 0)
					{
						overridePendingTransition(R.anim.right_in,
								R.anim.right_out);
						ImgAnimationA.this.finish();
					} else
					{
						if (tiaokuan != null)
						{
							if (tiaokuan.equals("shouhux"))
							{
								Intent intent = new Intent(ImgAnimationA.this,
										ShouHuXTiaoKuanA.class);
								startActivity(intent);
							} else if (tiaokuan.equals("hushenfu"))
							{
								Intent intent = new Intent(ImgAnimationA.this,
										HuShenFuTiaoKuanA.class);
								startActivity(intent);
							} else if (tiaokuan.equals("pinganfu"))
							{
								Intent intent = new Intent(ImgAnimationA.this,
										PingAnFuTiaoKuanA.class);
								startActivity(intent);
							} else if (tiaokuan.equals("xinli"))
							{
								Intent intent = new Intent(ImgAnimationA.this,
										XinLiTiaoKuanA.class);
								startActivity(intent);
							} else if (tiaokuan.equals("xinxiang"))
							{
								Intent intent = new Intent(ImgAnimationA.this,
										XinXiangTiaoKuanA.class);
								startActivity(intent);
							} else if (tiaokuan.equals("xinsheng"))
							{
								Intent intent = new Intent(ImgAnimationA.this,
										XinShengTiaoKuanA.class);
								startActivity(intent);
							} else if (tiaokuan.equals("yingjuyisheng"))
							{
								Intent intent = new Intent(ImgAnimationA.this,
										YingJuYiShengTiaoKuanA.class);
								startActivity(intent);
							}else if (tiaokuan.equals("baiwanrenwoxing"))
							{
//								Intent intent = new Intent(ImgAnimationA.this,
//										YingJuYiShengTiaoKuanA.class);
//								startActivity(intent);
								Toast.makeText(getApplicationContext(), "�����������", Toast.LENGTH_LONG).show();
							}
							finish();
						} else
						{
							ImgAnimationA.this.finish();
						}
					}

				}
				s = p;
			}

		}

		// boolean i = false;
		@Override
		public void onPageScrolled(int arg0, float arg1, int arg2)
		{
			// TODO Auto-generated method stub
			// transformer.setPosition(arg0);
			// transformer.setDegree(arg1);

		}

		@Override
		public void onPageSelected(int position)
		{
			p = position;
		}
	}

	// ��ȡurl�ķ���
	public String getUrl(int x)
	{
		String url = Define.Server + "images/" + urlStr + maxPageCount + "/"
				+ x + "." + format;
		return url;
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event)
	{
		if (keyCode == KeyEvent.KEYCODE_BACK)
		{
			overridePendingTransition(R.anim.right_in, R.anim.right_out);
			finish();
		}
		return super.onKeyDown(keyCode, event);
	}

	@Override
	protected void onResume()
	{
		// TODO �Զ����ɵķ������
		/**
		 * �ڽ���webview֮ǰ�ж��Ǻ�����������
		 */
		if (getIntent().getExtras().getString("screenFlag").equals("jianjie"))
		{
			setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		} else
		{
			setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

		}
		super.onResume();
	}
}
