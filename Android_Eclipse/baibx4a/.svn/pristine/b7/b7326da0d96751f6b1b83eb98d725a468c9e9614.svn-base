package com.baoxiao.activity.zoujinpingan;

import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.SoftReference;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.baoxiao.FastWebShowA2;
import com.baoxiao.R;
import com.baoxiao.WebShowA;
import com.baoxiao.service.userproduct.UserService;
import com.baoxiao.util.Define;
import com.baoxiao.util.MessageHelper;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class ComingPingAnA2 extends Activity{
	private ListView invitation_wine_list;
	
	public class myAdapter extends BaseAdapter {
		
		private LayoutInflater mInflater;
		private Context context;
		private List<Map<String, Object>> list;
		public myAdapter(Context context, List<Map<String, Object>> list) {
			super();
			this.context = context;
			this.list = list;
		}
		@Override
		public int getCount() {
			return list.size();
		}
		@Override
		public Object getItem(int position) {
			return null;
		}
		@Override
		public long getItemId(int position) {
			return 0;
		}
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
            final ViewHolder holder;
//            if (convertView == null) {
            	mInflater = getLayoutInflater();
            	convertView = mInflater.inflate(R.layout.invitation_list_item,null);
                holder = new ViewHolder();
                holder.iv_in_wi_title = (ImageView) convertView.findViewById(R.id.iv_in_wi_title);
                holder.tv_in_wi_title = (TextView) convertView.findViewById(R.id.tv_in_wi_title);
                holder.tv_in_wi_content = (TextView) convertView.findViewById(R.id.tv_in_wi_content);
                holder.iv_right_icon = (ImageView) convertView.findViewById(R.id.iv_right_icon);
                convertView.setTag(holder);
//            }else{
//            	holder = (ViewHolder)convertView.getTag();
//            }
            if (list.get(position).containsKey("image_id")) {
               	holder.iv_in_wi_title.setImageDrawable(getResources()
               		.getDrawable((Integer)(list.get(position).get("image_id"))));
               	
			}else if(list.get(position).containsKey("image")){
				
				if (list.get(position).containsKey("videoTime")) {
					if ("1".equals(list.get(position).get("videoTime"))) {					
						holder.iv_right_icon.setImageDrawable(getResources().getDrawable(R.drawable.video));
					}else{
						holder.iv_right_icon.setImageDrawable(null);
					}
				}
				//异步加载图片
				String url = Define.Server + (String) list.get(position).get("image");
				holder.iv_in_wi_title.setTag(url);
	            Drawable cachedImage = new AsyncImageLoader().loadDrawable(url, new ImageCallback() {
	                public void imageLoaded(Drawable imageDrawable, String imageUrl) {
//	                	holder.iv_in_wi_title.setImageDrawable(imageDrawable);
	                    ImageView imageViewByTag = (ImageView) invitation_wine_list.findViewWithTag(imageUrl);  
	                    if (imageViewByTag != null) {
	                        imageViewByTag.setImageDrawable(imageDrawable);
	                    }
	                }
	            });
	            if (cachedImage != null) {
	            	holder.iv_in_wi_title.setImageDrawable(cachedImage);
	            }
			}
            holder.tv_in_wi_title.setText((String)(list.get(position).get("title")));
            holder.tv_in_wi_content.setText((String)(list.get(position).get("subTitle")));
           	return convertView;
		}

		public final class ViewHolder{
		    public ImageView iv_in_wi_title;
		    public TextView tv_in_wi_title;
		    public TextView  tv_in_wi_content;
		    public ImageView iv_right_icon;
	    }
		
		
	}
	public class AsyncImageLoader {
	     private HashMap<String, SoftReference<Drawable>> imageCache;
         public AsyncImageLoader() {
             imageCache = new HashMap<String, SoftReference<Drawable>>();
         }
         public Drawable loadDrawable(final String imageUrl, final ImageCallback imageCallback) {
             if (imageCache.containsKey(imageUrl)) {
                 SoftReference<Drawable> softReference = imageCache.get(imageUrl);
                 Drawable drawable = softReference.get();
                 if (drawable != null) {
                     return drawable;
                 }
             }
             final Handler handler = new Handler() {
                 public void handleMessage(Message message) {
                     imageCallback.imageLoaded((Drawable) message.obj, imageUrl);
                 }
             };
             new Thread() {
                 @Override
                 public void run() {
                     Drawable drawable = loadImageFromUrl(imageUrl);
                     imageCache.put(imageUrl, new SoftReference<Drawable>(drawable));
                     Message message = handler.obtainMessage(0, drawable);
                     handler.sendMessage(message);
                 }
             }.start();
             return null;
         }
         public Drawable loadImageFromUrl(String url) {  
        	 URL m;  
        	 InputStream i = null;  
        	 try {  
        		 m = new URL(url);  
        		 i = (InputStream) m.getContent();  
        	 } catch (MalformedURLException e1) {  
        		 e1.printStackTrace();  
        	 } catch (IOException e) {  
        		 e.printStackTrace();  
        	 }  
        	 Drawable d = Drawable.createFromStream(i, "src");  
        	 return d;  
        }  
	}  
    public interface ImageCallback {  
        public void imageLoaded(Drawable imageDrawable, String imageUrl);  
    }  

	protected static final int GETURL = 0;
	private List<Map<String, Object>> list;
	private myAdapter adapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.invitation_list);
		((TextView)findViewById(R.id.tv_title)).setText("走进平安");		
		getUrl();
	}
	private void getUrl() {
		new Thread(new Runnable() {
			@Override
			public void run() {
				Message msg = new Message();
				MessageHelper message = UserService.getIntoPeaceUrl();
				msg.obj = message;
				msg.what = GETURL;
				handler.sendMessage(msg);
			}
		}).start();
	}
	private Handler handler = new Handler(new Handler.Callback() {
		@Override
		public boolean handleMessage(Message msg) {
			MessageHelper message = (MessageHelper)msg.obj;
			switch (msg.what) {
			case GETURL:
				if (message.getResult().equals(Define.S)) {
					try {
						JSONArray array = new JSONArray(new JSONObject(
								new JSONObject(message.getResouce())
								.getString("messageHelper"))
								.getString("entity"));
//	{"messageHelper":{"entity":
//	[{"id":1,"image":"images\/recruit\/recruitment_4.png",
//		"outlink":"http:\/\/www.rabbitpre.com\/m\/iAF32uS#\r\n",
//		"publishTime":"","referee":"",
//		"subTitle":"要创业，来平安!",
//		"title":"创业说明会","type":"recruit","videoTime":""},
//	 {"id":2,"image":"images\/recruit\/recruitment_5.png","outlink":"http:\/\/www.rabbitpre.com\/m\/Q7FBnuc","publishTime":"","referee":"","subTitle":"做保险就是帮助别人，成就自己。","title":"做保险十大理由","type":"recruit","videoTime":""},{"id":3,"image":"images\/recruit\/recruitment_6.png","outlink":"http:\/\/www.rabbitpre.com\/m\/yQfE7jj#","publishTime":"","referee":"","subTitle":"详述了平安大学和平安的培训体系","title":"平安大学和培训","type":"recruit","videoTime":""},{"id":4,"image":"images\/recruit\/recruitment_7.png","outlink":"http:\/\/v.youku.com\/v_show\/id_XMTU2NDYxNTI5Ng==.html","publishTime":null,"referee":null,"subTitle":"视频详述了女性进入平安之后，发生的蜕变。","title":"来平安做女神","type":"recruit","videoTime":null},{"id":5,"image":"images\/recruit\/recruitment_8.png","outlink":"http:\/\/v.youku.com\/v_show\/id_XMTU5NTEwMTkwOA==.html","publishTime":null,"referee":null,"subTitle":"视频介绍了平安的工作及如何在平安实现创业梦想。","title":"平安线上创业说明会","type":"recruit","videoTime":null},{"id":6,"image":"images\/recruit\/recruitment_9.png","outlink":"http:\/\/v.youku.com\/v_show\/id_XMTU5NTEwMTgyNA==.html","publishTime":null,"referee":null,"subTitle":"讲解平安的工作模式。","title":"平安的工作","type":"recruit","videoTime":null},{"id":7,"image":"images\/recruit\/recruitment_10.png","outlink":"http:\/\/v.youku.com\/v_show\/id_XMTU1ODk0ODgyOA==.html","publishTime":"","referee":"","subTitle":"讲解平安的优才计划。","title":"平安优才计划","type":"recruit","videoTime":""}],"list":null,"message":"","result":"S"}}
						
						list = new ArrayList<Map<String,Object>>();
						for (int i = 0; i < array.length(); i++) {
							String image = array.getJSONObject(i).getString("image");
							String outlink = array.getJSONObject(i).getString("outlink");
							String subTitle = array.getJSONObject(i).getString("subTitle");
							String title = array.getJSONObject(i).getString("title");
							String videoTime = array.getJSONObject(i).getString("videoTime");
							Map<String, Object> map = new HashMap<String, Object>();
							map.put("image", image);
							map.put("outlink", outlink);
							map.put("title", title);
							map.put("subTitle", subTitle);
							map.put("videoTime", videoTime);
							list.add(map);
						}
						adapter = new myAdapter(ComingPingAnA2.this, list);
						invitation_wine_list = (ListView)findViewById(R.id.invitation_wine_list);
						invitation_wine_list.setAdapter(adapter);
						invitation_wine_list.setOnItemClickListener(new OnItemClickListener() {
							@Override
							public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
					    		String url = (String)list.get(position).get("outlink");
					    		String shareName = (String)list.get(position).get("title");
					    		startActivity(new Intent(ComingPingAnA2.this,FastWebShowA2.class)
					    			.putExtra("url", url)
					    			.putExtra("shareName", shareName));
							}
						});
					} catch (JSONException e) {
						e.printStackTrace();
					}
				}
				break;

			default:
				break;
			}
			return false;
		}
	});

	public void invitation_list_back(View v){
		finish();
	}
}
