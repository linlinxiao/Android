package com.baoxiao.activity.homepage;

import java.io.File;
import java.util.ArrayList;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;
import com.baoxiao.BaseActivity;
import com.baoxiao.JiHuoA;
import com.baoxiao.R;
import com.baoxiao.activity.invitation.InvitationListA;
import com.baoxiao.activity.message.NewsConsultA;
import com.baoxiao.activity.mine.MyCardEditA3;
import com.baoxiao.activity.productshow.BaoXianProductA;
import com.baoxiao.activity.search.SearchA;
import com.baoxiao.activity.study.StudyA2;
import com.baoxiao.activity.zhaopinjingying.ZhaoPinJingYingA2;
import com.baoxiao.activity.zoujinpingan.ComingPingAnA2;
import com.baoxiao.download.UpdateManager;
import com.baoxiao.model.Userb;
import com.baoxiao.service.mine.MineService;
import com.baoxiao.service.userproduct.UserService;
import com.baoxiao.util.Define;
import com.baoxiao.util.GetSharedPreferencesValue;
import com.baoxiao.util.HttpConnection;
import com.baoxiao.util.ShareService;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

/**
 * '首页'界面布局
 *
 */
public class HomePageA extends BaseActivity implements OnClickListener,
OnPageChangeListener{
	private LinearLayout zouJinPingAn,zhaoPinJingYing,
						 baoXianProduct,study,newsConsult,
						 shop,mine,sameproduct;
	private TextView jiHuoState;
	private ImageView search;
	private Userb userb = new Userb();
	
	//上部图片滑动切换
	private RelativeLayout rl_viewPager;
	//定义ViewPager对象
	private ViewPager viewPager;
	
	//定义ViewPager适配器
	private ViewPagerAdapter vpAdapter;
	
	//定义一个ArrayList来存放View
	private ArrayList<View> views;

	//引导图片资源
//    private static final int[] pics = {R.drawable.switchimage,R.drawable.switchimage,R.drawable.switchimage,R.drawable.switchimage};
//    
    
    private static final int[] pics = {R.drawable.switchimage1,R.drawable.switchimage2,R.drawable.switchimage3,R.drawable.switchimage4};
	protected static final int NO_ACTIVE = 0;
	protected static final int VERSIONUPDATE = 1;
	protected static final int DOWNLOAD = 2;
	private Boolean isDestroyed = false;
	private String filename;
	
    //底部小点的图片
    private ImageView[] points;
    
  //记录当前选中位置
    private int currentIndex;
    private ProgressBar bar;
    private ProgressDialog dialog;
    private String userid;
    private SharedPreferences isActive,loading;
    private View tv_message_count,iv_message_count;
    
	@Override
	protected void onDestroy() {
		super.onDestroy();
		isDestroyed = true;
	}
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.homepage);
		
		isActive = getSharedPreferences("isActive", Activity.MODE_PRIVATE);
		loading = getSharedPreferences("loading", Activity.MODE_PRIVATE);
		userid = loading.getString("userid", null);

		initViewPager();
		initViewPagerData();
		bar = (ProgressBar) findViewById(R.id.pb_bar);
		zouJinPingAn = (LinearLayout) findViewById(R.id.zouJinPingAn);
		zhaoPinJingYing = (LinearLayout) findViewById(R.id.zhaoPinJingYing);
		baoXianProduct = (LinearLayout) findViewById(R.id.baoXianProduct);
		study = (LinearLayout) findViewById(R.id.study);
		newsConsult = (LinearLayout) findViewById(R.id.newsConsult);
		shop = (LinearLayout) findViewById(R.id.shop);
		mine = (LinearLayout) findViewById(R.id.mine);
		sameproduct = (LinearLayout) findViewById(R.id.sameproduct);
		jiHuoState = (TextView) findViewById(R.id.jiHuoState);
		search = (ImageView) findViewById(R.id.search);
		
		tv_message_count = findViewById(R.id.tv_message_count);
		iv_message_count = findViewById(R.id.iv_message_count);

		zouJinPingAn.setOnClickListener(this);
		zhaoPinJingYing.setOnClickListener(this);
		baoXianProduct.setOnClickListener(this);
		study.setOnClickListener(this);
		newsConsult.setOnClickListener(this);
		shop.setOnClickListener(this);
		mine.setOnClickListener(this);
		sameproduct.setOnClickListener(this);
		jiHuoState.setOnClickListener(this);
		search.setOnClickListener(this);
		new Thread(runnable).start();
		checkVersionUpdate();
		getIsActive();
	}
	@Override
	protected void onResume() {
		super.onResume();
		Map<String, ?> map = getSharedPreferences("message", Activity.MODE_PRIVATE).getAll();
		int size = map.size();
		if (size == 0) {
			iv_message_count.setVisibility(View.GONE);
			tv_message_count.setVisibility(View.GONE);
		}else{
			iv_message_count.setVisibility(View.VISIBLE);
			tv_message_count.setVisibility(View.VISIBLE);
			((TextView)tv_message_count).setText(size + "");
		}
	}
	
	private void getIsActive() {
		new Thread(new Runnable() {
			@Override
			public void run() {
				String equipmentId = UserService.getIMEI(HomePageA.this);
				JSONObject object = UserService.getIsActive(userid, equipmentId);
//				{"messageHelper":{"entity":null,"list":null,"message":"激活状态","result":"1"}}
				if (object == null) {return;}
				JSONObject messageHelper = null;
				try {
					messageHelper = new JSONObject(object.getString("messageHelper"));
				} catch (JSONException e) {
					e.printStackTrace();
				}
				if (messageHelper == null) {return;}
				String result;
				String message;
				try {
					result = messageHelper.getString("result");
					message = messageHelper.getString("message");
					isActive.edit().putString("result", result).commit();
					isActive.edit().putString("message", message).commit();
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}
		}).start();
	}
	private void checkVersionUpdate() {
//		UpdateManager manager = new UpdateManager(HomePageA.this);
//		// 检查软件更新
//		manager.checkUpdate();

		new Thread(new Runnable() {
			@Override
			public void run() {
				String result = null;
				String service = Define.Server;
				String url = service + "updata_info.xml";
				try {
					result = HttpConnection.GetContent(url);
				} catch (Throwable e) {
					e.printStackTrace();
				}
				Message message = handler.obtainMessage();
				message.obj = result;
				message.what = VERSIONUPDATE;
				handler.sendMessage(message);
			}
		}).start();
	}

	private void initViewPager() {
		rl_viewPager = (RelativeLayout) findViewById(R.id.rl_viewPager);
		rl_viewPager.setOnClickListener(this);
		
		//实例化ArrayList对象
		views = new ArrayList<View>();
		
		//实例化ViewPager
		viewPager = (ViewPager) findViewById(R.id.viewpager);
		
		//实例化ViewPager适配器
		vpAdapter = new ViewPagerAdapter(views);
	}
	
	private void initViewPagerData() {
		//定义一个布局并设置参数
		LinearLayout.LayoutParams mParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                														  LinearLayout.LayoutParams.MATCH_PARENT);
       
        //初始化引导图片列表
        for(int i=0; i<pics.length; i++) {
            ImageView iv = new ImageView(this);
            iv.setLayoutParams(mParams);
            iv.setImageResource(pics[i]);
            iv.setScaleType(ScaleType.FIT_XY);
            views.add(iv);
        } 
        
        //设置数据
        viewPager.setAdapter(vpAdapter);
        //设置监听
        viewPager.setOnPageChangeListener(this);
        
        //初始化底部小点
        initPoint();
	}

	/**
	 * 初始化底部小点
	 */
	private void initPoint(){
		LinearLayout linearLayout = (LinearLayout) findViewById(R.id.ll_point);       
		
        points = new ImageView[pics.length];

        //循环取得小点图片
        for (int i = 0; i < pics.length; i++) {
        	//得到一个LinearLayout下面的每一个子元素
        	points[i] = (ImageView) linearLayout.getChildAt(i);
        	//默认都设为灰色
        	points[i].setEnabled(true);
        	//给每个小点设置监听
        	points[i].setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					int position = (Integer)v.getTag();
				    setCurView(position);
				    setCurDot(position);
				}
			});
        	//设置位置tag，方便取出与当前位置对应
        	points[i].setTag(i);
        }
        
        //设置当面默认的位置
        currentIndex = 0;
        //设置为白色，即选中状态
        points[currentIndex].setEnabled(false);
	}
	
	@Override
	public void onClick(View v) {
        switch (v.getId()) {
		case R.id.baoXianProduct:
			Intent  intent = new Intent(this,BaoXianProductA.class);
			startActivity(intent);
			break;

		case R.id.newsConsult:
			Intent  intent2 = new Intent(this,NewsConsultA.class);
			startActivity(intent2);
			break;
		case R.id.zouJinPingAn:
			Intent  intent3 = new Intent(this,ComingPingAnA2.class);
			startActivity(intent3);
			break;
		case R.id.zhaoPinJingYing:
			Intent  intent4 = new Intent(this,ZhaoPinJingYingA2.class);
			startActivity(intent4);
			break;
		case R.id.study:
			Intent  intent5 = new Intent(this,StudyA2.class);
			startActivity(intent5);
			break;
		case R.id.shop:
//			Intent  intent6 = new Intent(this,ShopA.class);
//			startActivity(intent6);
//			startActivity(new Intent(HomePageA.this,Invitation2A.class));
//			startActivity(new Intent(HomePageA.this,Invitation2A2.class));
			startActivity(new Intent(HomePageA.this,InvitationListA.class));
			break;
		case R.id.mine:
			String type = GetSharedPreferencesValue.getType(HomePageA.this);
			if(type.equals("user")){
				Intent  intent7 = new Intent(this,MyCardEditA3.class);
				startActivity(intent7);
			}else{
				Toast.makeText(getApplicationContext(), "您是客户没有权限", Toast.LENGTH_SHORT).show();
			}
			break;
		case R.id.sameproduct:
//			Toast.makeText(getApplicationContext(), "暂无", Toast.LENGTH_SHORT).show();
//			Intent  intent8 = new Intent(this,ZhaoPinJingYing.class);
//			startActivity(intent8);
			ShareService.startShare(Define.Server + "","平安百宝箱软件下载", HomePageA.this);
//			ShareService.startShare("http://192.168.0.211:8080/baibx/"
//					+ "" + "share/share.jsp","平安百宝箱软件下载", HomePageA.this);
			break;
		case R.id.jiHuoState:
			Intent  intent9 = new Intent(this,JiHuoA.class);
			intent9.putExtra("IntentTag", "2");
			startActivity(intent9);
			finish();
			break;
		case R.id.search:
			Intent  intent10 = new Intent(this,SearchA.class);
			intent10.putExtra("sousuo", "home");
			startActivity(intent10);
			break;
		}
	}

	private Runnable runnable = new Runnable()
	{
		@Override
		public void run()
		{
			userb = MineService.MyAccountNews(userid);
			handler.sendEmptyMessage(NO_ACTIVE);
		}
	};
	
	private Handler handler = new Handler(new Handler.Callback() {
		@Override
		public boolean handleMessage(Message msg) {
			switch (msg.what){
			case NO_ACTIVE:
				if(!"未激活".equals(userb.getStatus())){
					jiHuoState.setText("平安百宝箱");
					jiHuoState.setEnabled(false);
				}
				break;
			case VERSIONUPDATE:
				final String result1 = (String)msg.obj;
				if(result1 != null){
					JSONObject object;
					try {
						object = new JSONObject(result1);
						final String path = object.getString("file_path");
						String version = object.getString("version");
						if (version != null && path != null) {
							loading.edit().putString("path", path).commit();
							loading.edit().putString("version", version).commit();
						}
						String message = object.getString("message");
//						showDialog(filepath,filename,version);
						String curr_version = getVersion();
						if (!curr_version.equals(version)) {
							UpdateManager manager = new UpdateManager(HomePageA.this,path);
							manager.showNoticeDialog(version,curr_version,message);

//							new AlertDialog.Builder(HomePageA.this)
//							.setTitle("提示")
//							.setMessage("当前版本" + curr_version +
//									",检测到最新版本" + version + ".")
//							.setNegativeButton("以后再说", null)
//							.setPositiveButton("立刻更新",
//							new DialogInterface.OnClickListener() {
//								@Override
//								public void onClick(DialogInterface arg0,int arg1) {
//									filename = path.substring(path.lastIndexOf('/') + 1);
//									if (Environment.getExternalStorageState()
//										.equals(Environment.MEDIA_MOUNTED)) {
//									Boolean iscomplete = HomePageA.this
//										.getSharedPreferences("download",Activity.MODE_PRIVATE)
//										.getBoolean(filename + "_iscomplete",false);
//									if (!iscomplete) {
//										bar.setVisibility(View.VISIBLE);
//										if (dialog == null) {
//											dialog = new ProgressDialog(HomePageA.this);
//										}dialog.show();
//										download(path,Environment.getExternalStorageDirectory());
//									} else {
//										noticeInstall();
//									}
//								} else {
//									Toast.makeText(HomePageA.this,"SDCard不存在或者写保护",
//										Toast.LENGTH_LONG).show();
//								}
//							}
//						}).show();						
						}	
						
					} catch (JSONException e1) {
						e1.printStackTrace();
					}
				}else{
					Toast.makeText(HomePageA.this, "数据获取失败，请确认网络连接！", Toast.LENGTH_LONG).show();
				}
				break;
			
			case DOWNLOAD:
				bar.setProgress(msg.getData().getInt("size"));
//				float num = (float) bar.getProgress()
//						/ (float) bar.getMax();
//				int result2 = (int) (num * 100);
//				resultView.setText(result2 + "%");
				if (bar.getProgress() == bar.getMax()) {
					bar.setVisibility(View.GONE);
					HomePageA.this.getSharedPreferences("download",
									Activity.MODE_PRIVATE).edit()
							.putBoolean(filename + "_iscomplete", true)
							.commit();
					if (!isDestroyed) {
						noticeInstall();
					}
				}
				break;

			default:
				break;
			}
			return false;
		}
	});
//	private void download(final String path, final File savedir) {
//		new Thread(new Runnable() {
//			@Override
//			public void run() {
//				FileDownloader loader = new FileDownloader(
//						HomePageA.this,path,savedir,8);
//				bar.setMax(loader.getFileSize());
//				try {
//					loader.download(new DownloadProgressListener() {
//						@Override
//						public void onDownloadSize(int size) {
//							Message msg = new Message();
//							msg.what = DOWNLOAD;
//							msg.getData().putInt("size", size);
//							handler.sendMessage(msg);
//						}
//					});
//				} catch (Exception e) {
//					handler.obtainMessage(-1).sendToTarget();
//				}
//			}
//		}).start();
//	}

	protected void noticeInstall() {
		new AlertDialog.Builder(HomePageA.this)
		.setTitle("下载完成")
		.setMessage("新版本下载完成，立即安装吗?")
		.setPositiveButton("立即安装",
			new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface arg0, int arg1) {
				Intent intent = new Intent();
				intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				intent.setAction(android.content.Intent.ACTION_VIEW);
				Uri uri = Uri.fromFile(new File(Environment
					.getExternalStorageDirectory()
					+ "/"
					+ filename));
				intent.setDataAndType(uri,
						"application/vnd.android.package-archive");
				startActivity(intent);
				System.exit(0);
				bar.setVisibility(View.GONE);
				if (dialog != null) {dialog.dismiss();}
			}
		}).setNegativeButton("以后再说", null).show();
	}

	public String getVersion(){
		PackageManager pm = getPackageManager();
		PackageInfo pi;
		try {
			pi = pm.getPackageInfo(getPackageName(), 0);
			String version = pi.versionName;
			return version;
		} catch (NameNotFoundException e) {
			e.printStackTrace();
			return "未知";
		}
	}

	/**
	 * 当滑动状态改变时调用
	 */
	@Override
	public void onPageScrollStateChanged(int arg0) {
	}

	/**
	 * 当前页面被滑动时调用
	 */
	@Override
	public void onPageScrolled(int arg0, float arg1, int arg2) {
	}

	/**
	 * 当新的页面被选中时调用
	 */
	@Override
	public void onPageSelected(int position) {
		//设置底部小点选中状态
        setCurDot(position);
	}
	
	/**
	 * 设置当前页面的位置
	 */
	private void setCurView(int position){
         if (position < 0 || position >= pics.length) {
             return;
         }
         viewPager.setCurrentItem(position);
     }
	
	 /**
     * 设置当前的小点的位置
     */
    private void setCurDot(int positon){
         if (positon < 0 || positon > pics.length - 1 || currentIndex == positon) {
             return;
         }
         points[positon].setEnabled(false);
         points[currentIndex].setEnabled(true);

         currentIndex = positon;
     }
//    @Override
//    public boolean onKeyDown(int keyCode, KeyEvent event) {
//    	if (keyCode == KeyEvent.KEYCODE_BACK) {
//			bar.setVisibility(View.GONE);
//			System.exit(0);
//		}
//    	return super.onKeyDown(keyCode, event);
//    }
//    /** 
//     * 菜单、返回键响应 
//     */  
//    @Override  
//    public boolean onKeyDown(int keyCode, KeyEvent event) {  
//        if(keyCode == KeyEvent.KEYCODE_BACK)  
//           {    
//               exitBy2Click();      //调用双击退出函数  
//           }  
//        return false;  
//    }  
//    /**
//     * 双击退出函数
//     */
//    private static Boolean isExit = false;
//    private void exitBy2Click() {
//        Timer tExit = null;
//        if (isExit == false) {
//            isExit = true;
//            Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
//            tExit = new Timer();
//            tExit.schedule(new TimerTask() {
//                @Override
//                public void run() {
//                    isExit = false;
//                }  
//            }, 2000);
//        } else {
//			bar.setVisibility(View.GONE);
//            finish();
//            System.exit(0);
//        }
//    }
    
	public void test(View v){
//		startActivity(new Intent(this,SynListViewImageActivity.class));
	}

}
