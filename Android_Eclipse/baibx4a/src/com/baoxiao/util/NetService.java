package com.baoxiao.util;

import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;


/**
 *通信类，
 **/
public class NetService  {
//	private static String url;
//	private static MessageHelper  result;
	
	
	
//	public  static Object[] getDataFromServer(int currentPage,String type,String typeContact,String keyWord) throws Exception{
//		Object[] datas=new Object[2];
//		     StringBuilder sb=new StringBuilder();
//		     ArrayList<Url> list=new ArrayList<Url>();
//		        String ServerAddress=Definition.GotData_SERVER+"type="+type+"&typeContact="+typeContact+"&keyWord="+keyWord+"&curPage="+currentPage;
//				sb.append( NetworkTool.getContent(ServerAddress));
//			
////				JSONObject totalCount= new JSONObject(sb.toString());
////				JSONObject UrlList= new JSONObject(sb.toString());
////				 datas[0]=totalCount.getString("totalCount");
////				 if(totalCount.getString("totalCount").equals("0")){
////					 datas[1]=list;
////						return datas; 
////				 }
//				 
//				 
//				JSONArray array = new JSONArray(sb.toString());
//				
//				
////				if (array.length()==0) {
////					datas[1]=list;
////					return datas;
////				}
//		 for(int j=0;j<array.length();j++){
//			 JSONObject obj = array.getJSONObject(j);
//			 if(j==9){
//				Log.d("test",obj.getString("title")) ;
//			 }
////			 鑾峰彇绗竴鍏冪礌鐨勬?鏉℃暟
//			 if(j==0){  
//				 datas[0]= obj.getString("totalCount"); 
//				 if(obj.getString("totalCount").equals("0")){
//					 datas[1]=list;
//						return datas; 
//				 }
//			 }else{  //鑾峰彇list 
//			 Url url=new Url();
//			 url.setTitle(obj.getString("title"));
//			 url.setPhone(obj.getString("phone"));
//			 url.setEmail(obj.getString("email"));
//			 url.setAlexa(Integer.parseInt(obj.getString("alexa")));
//			 url.setUrl(obj.getString("url"));
//			 url.setZip(obj.getString("zip"));
//			 url.setQq(obj.getString("qq"));
//			 url.setMobile(obj.getString("mobile"));
//			 url.setAddress(obj.getString("address"));
//			 url.setFirstPage(obj.getString("fPage"));
//			 url.setFax(obj.getString("fax"));
//			 
//			 
//			 
//			 list.add(url);
//			 }
//		 }
//		 datas[1]=list;
//		return datas;
//		
//	}
	
	
//	public static   MessageHelper startService(Context context,String url){
//		setUrl(url);
//		context.startService(new Intent(context,NetService.class));
//		return getResult();
//	}
//	
	
//  entity 和list 没有解析，返回了 json 数据保存在resouce属性
	public  static MessageHelper  getDataFromServer(String url){
		
		StringBuilder sb=new StringBuilder();
		MessageHelper rst=new MessageHelper();
		try{
			sb.append(NetworkTool.getContent(url));
			JSONObject root= new JSONObject(sb.toString());
			JSONObject messageHelper=root.getJSONObject("messageHelper");
			rst.setResult(messageHelper.getString("result"));
			rst.setMessage(messageHelper.getString("message"));
			rst.setResouce(sb.toString());
		}catch(Exception e){
			
			e.printStackTrace();
		}
		return rst;
	}
//  entity 和list 没有解析，返回了 json 数据保存在resouce属性
	public static JSONObject getDataFromServer2(String url){
		StringBuilder sb = new StringBuilder();
		JSONObject root = null;
		try {
			sb.append(NetworkTool.getContent(url));
			root = new JSONObject(sb.toString());
		} catch (JSONException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return root;
	}
//  entity 和list 没有解析，返回了 json 数据保存在resouce属性
	public static JSONObject getDataFromServerByPost(String url,Map<String ,String> rawParams){
		StringBuilder sb = new StringBuilder();
		JSONObject root = null;
		try {
			sb.append(NetworkTool.postRequest(url,rawParams));
			root = new JSONObject(sb.toString());
		} catch (JSONException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return root;
	}

//  entity 和list 没有解析，返回了 json 数据保存在resouce属性
	public  static MessageHelper  postDataFromServer(String url,Map<String ,String> rawParams){
		
		StringBuilder sb=new StringBuilder();
		MessageHelper rst=new MessageHelper();
		try{
			sb.append(NetworkTool.postRequest(url, rawParams));
			JSONObject root= new JSONObject(sb.toString());
			JSONObject messageHelper=root.getJSONObject("messageHelper");
			rst.setResult(messageHelper.getString("result"));
			rst.setMessage(messageHelper.getString("message"));
			rst.setResouce(sb.toString());
		}catch(Exception e){
			
			e.printStackTrace();
		}
		return rst;
	}
//	@Override
//	public void onCreate() {
//		super.onCreate();
//	}
//
//	@Override
//	public void onStart(Intent intent, int startId) {
//		setResult(getDataFromServer(url));
//	}
//
//	@Override
//	public void onDestroy() {
//		super.onDestroy();
//	}
//
//	@Override
//	public IBinder onBind(Intent intent) {
//		return null;
//	}
//	
//	
//	
//	
//	public static String getUrl() {
//		return url;
//	}
//
//	public static void setUrl(String url) {
//		NetService.url = url;
//	}
//
//	public static MessageHelper getResult() {
//		return result;
//	}
//
//	public static void setResult(MessageHelper result) {
//		NetService.result = result;
//	}


	
}
