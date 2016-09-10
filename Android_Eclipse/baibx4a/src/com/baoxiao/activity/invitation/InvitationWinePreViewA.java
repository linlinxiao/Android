package com.baoxiao.activity.invitation;

import com.baoxiao.R;
import com.baoxiao.util.Define;
import com.baoxiao.util.ShareService;
import com.umeng.socialize.media.UMImage;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

public class InvitationWinePreViewA extends Activity{
	private WebView webView;
	private String url;

	@SuppressLint("SetJavaScriptEnabled")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.news_articleinfo);
		TextView tv_title = (TextView)findViewById(R.id.tv_title);
		tv_title.setText("�ƻ����뺯Ԥ��");
		findViewById(R.id.collection).setVisibility(View.GONE);
		
		webView = (WebView)findViewById(R.id.newsConsult);
		WebSettings webSettings = webView.getSettings();
		webSettings.setUseWideViewPort(true);
		webSettings.setLoadWithOverviewMode(true);
		webSettings.setJavaScriptEnabled(true);
//		String url = Define.Server + "invitationWineParty.action?userid=123";
		String userid = getSharedPreferences("loading", Activity.MODE_PRIVATE).getString("userid", "");
		url = Define.Server + "invitationWineParty.action?userid=" + userid;
		int type = getIntent().getIntExtra("type", 0);
		if (type == 3) {
			url = Define.Server + "invitationFinancialShow.action?userid=" + userid;
			tv_title.setText("�ۺϽ��ڴ�ҵ˵����Ԥ��");
		}else if(type == 5){
			url = Define.Server + "visitLetteShow.action?userid=" + userid;
			tv_title.setText("�ݷú�Ԥ��");
		}else if(type == 2){
			url = Define.Server + "oppShow.action?userid=" + userid;
			tv_title.setText("ƽ����ҵ˵����Ԥ��");
		}
		webView.loadUrl(url);
		webView.setWebViewClient(new WebViewClient(){
			public boolean shouldOverrideUrlLoading(WebView view, String url){
				InvitationWinePreViewA.this.url = url;
				view.loadUrl(url);
				return true;
            }
			@Override
			public void onPageFinished(WebView view, String url){
				super.onPageFinished(view, url);
			}
		});
		findViewById(R.id.share).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				SharedPreferences isActive = getSharedPreferences("isActive", Activity.MODE_PRIVATE);
				String result = isActive.getString("result", null);
				if (result == null) {return;}
				String message = isActive.getString("message", null);
				if ("2".equals(result)) {
					new AlertDialog.Builder(InvitationWinePreViewA.this).setTitle("��ʾ").setMessage(message)
					.setPositiveButton("ȷ��", new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface dialog, int which) {
							share();
						}
					})
					.show();
					return;
				}
				if ("1".equals(result)) {
					share();
					return;
				}
				new AlertDialog.Builder(InvitationWinePreViewA.this).setTitle("��ʾ").setMessage(message)
				.setPositiveButton("ȷ��", null).show();

			}

			private void share() {
//			    String name = getSharedPreferences("mycard", Activity.MODE_PRIVATE).getString("p1t1", null);
			    String name = getSharedPreferences("loading", Activity.MODE_PRIVATE).getString("name", null);
			    if (name == null) {name = "��΢΢";}
				if (url.contains("invitationWineParty.action?userid=")) {
					UMImage image = new UMImage(InvitationWinePreViewA.this, R.drawable.invitation_mould4);
					ShareService.startShareWithImage(image,url,
							"���ã�����" + name + "����л��һֱ�������ҵ����κͰ�������ϵ��������μ��Ҿٰ�Ŀͻ���л�ƻᡣ",
							InvitationWinePreViewA.this,
							"���˾ƻ����뺯"
							);
				}else if(url.contains("invitationFinancialShow.action?userid=")){
					UMImage image = new UMImage(InvitationWinePreViewA.this, R.drawable.invitation_mould4);
					ShareService.startShareWithImage(image,url,
							"���ã�����" + name + "����ϵ��������μ��ҹ�˾�ٰ���ۺϽ���ְҵ�����˴�ҵ˵���ᡣ",
							InvitationWinePreViewA.this,
							"ƽ���ۺϽ��ھ��������뺯"
							);
				}else if(url.contains("mycard.action?userid=")){
				    String head_image = getSharedPreferences("invitationWineParty", Activity.MODE_PRIVATE).getString("p1t1", null);
				    if (head_image == null) {
				    	head_image = "images/mycard/mycard_head.png";
					}
				    UMImage image = new UMImage(InvitationWinePreViewA.this, Define.Server + head_image);
					ShareService.startShareWithImage(image,url,"���ã�����" + name +"�������ҵĸ�����Ƭ�����һ�¿��Կ����ҵļ�飡", 
							InvitationWinePreViewA.this,"�ҵĸ��˼��-" + name);
				}else if(url.contains("visitLetteShow.action?userid=")){
					UMImage image = new UMImage(InvitationWinePreViewA.this, R.drawable.invitation_mould5);
					ShareService.startShareWithImage(image,url,
							"���ã�����" + name + "����ʶ�����ҵ����ң����ڴ��ܺ������档",
							InvitationWinePreViewA.this,
							"�ݷú�"
							);
				}else if(url.contains("oppShow.action?userid=")){
					UMImage image = new UMImage(InvitationWinePreViewA.this, R.drawable.invitation_mould2);
					ShareService.startShareWithImage(image,url,
							"���ã�����" + name + "����ϵ��������μ��ҹ�˾�ٰ�Ĵ�ҵ˵���ᡣ",
							InvitationWinePreViewA.this,
							"ƽ����ҵ˵�������뺯"
							);
				}				
			}
		});
	}
	public void news_back(View v){
		webView.loadUrl("about:blank");
		finish();
	}
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			webView.loadUrl("about:blank");
			finish();
			return false;
		}
		return super.onKeyDown(keyCode, event);
	}
	
}
