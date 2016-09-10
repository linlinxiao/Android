package com.baoxiao.util;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import com.baoxiao.R;


/**
 * @Description:�Զ���Ի���
 * @author http://blog.csdn.net/finddreams
 */
public class CustomProgressDialog extends ProgressDialog
{

	private AnimationDrawable mAnimation;
	// private Context mContext;
	private ImageView mImageView;
	private String mLoadingTip;
	private TextView mLoadingTv;
	private int mResid;

	public CustomProgressDialog(Context context, String content, int id)
	{
		super(context);
		// this.mContext = context;
		this.mLoadingTip = content;
		this.mResid = id;
		setCanceledOnTouchOutside(true);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		initView();
		initData();
	}

	private void initData()
	{
		
		mImageView.setBackgroundResource(mResid);
		// ͨ��ImageView�����õ�������ʾ��AnimationDrawable
		mAnimation = (AnimationDrawable) mImageView.getBackground();
		// Ϊ�˷�ֹ��onCreate������ֻ��ʾ��һ֡�Ľ������֮һ
		mImageView.post(new Runnable()
		{
			@Override
			public void run()
			{
				mAnimation.start();

			}
		});
		mLoadingTv.setText(mLoadingTip);

	}

	public void setContent(String str)
	{
		mLoadingTv.setText(str);
	}

	private void initView()
	{
		setContentView(R.layout.progress_dialog);
		mLoadingTv = (TextView) findViewById(R.id.loadingTv);
		mImageView = (ImageView) findViewById(R.id.loadingIv);
	}

}
