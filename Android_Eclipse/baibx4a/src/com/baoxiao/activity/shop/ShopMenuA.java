package com.baoxiao.activity.shop;

import com.baoxiao.R;
import com.baoxiao.model.Userb;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * '礼品商城菜单'布局界面
 *
 */
public class ShopMenuA extends Activity implements OnClickListener
{

	private TextView userName,myOrder,myShopCart,freeGold;
	private ImageView mineBack;
	private Userb userb = null;
	private String userId;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.shop_menu);

		initView();
		setText();
	}

	private void setText() {
		userName.setText(userId);
	}

	private void initView()
	{
		userName = (TextView) findViewById(R.id.userName);
		myOrder = (TextView) findViewById(R.id.myOrder);
		myShopCart = (TextView) findViewById(R.id.myShopCart);
		freeGold = (TextView) findViewById(R.id.freeGold);
		mineBack = (ImageView) findViewById(R.id.mineBack);
		
		myOrder.setOnClickListener(this);
		myShopCart.setOnClickListener(this);
		freeGold.setOnClickListener(this);
		mineBack.setOnClickListener(this);
		
		// 获取登录用户id
		userId = getSharedPreferences("loading", Activity.MODE_PRIVATE)
				.getString("userid", "");
	}

	@Override
	public void onClick(View v)
	{
		switch (v.getId())
		{
		case R.id.myOrder:
			Intent intent = new Intent(this, MyOrderA.class);
			startActivity(intent);
			break;
		case R.id.myShopCart:
			Intent intent2 = new Intent(this, MyShopCartA.class);
			startActivity(intent2);
			break;
		case R.id.freeGold:
			Intent intent3 = new Intent(this, FreeGoldA.class);
			startActivity(intent3);
			break;
		case R.id.mineBack:
			finish();
			break;
		default:
			break;
		}
	}

}
