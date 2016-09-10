package com.baoxiao.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class NetworkTool {
	
	/**
	* getting  string content by URL.
	* @param url
	* @return
	* @throws Exception
	*/
	public static String getContent(String url) throws Exception{
	    StringBuilder sb = new StringBuilder();
	    
	    HttpClient client = new DefaultHttpClient();
	    HttpParams httpParams = client.getParams();
	    HttpConnectionParams.setConnectionTimeout(httpParams, 10*1000);
	    HttpConnectionParams.setSoTimeout(httpParams, 10*1000);
//	    url="http://www.baidu.com";
	    HttpResponse response = client.execute(new HttpGet(url));
	    HttpEntity entity = response.getEntity();
	    if (entity != null) {
	        BufferedReader reader = new BufferedReader(new InputStreamReader(entity.getContent(), "UTF-8"), 8192);
	        
	        String line = null;
	        while ((line = reader.readLine())!= null){
	            sb.append(line + "\n");
	        }
	        reader.close();
	    }
	    return sb.toString();
	} 
	
    /***
     * check network whether available.
     * @param activity the standard android Activity class.
     * @return true  means network is  available,false means not available. 
     */
	public static boolean isOnline(Activity activity) {
	    ConnectivityManager cm =
	        (ConnectivityManager) activity.getSystemService(Context.CONNECTIVITY_SERVICE);
	    NetworkInfo netInfo = cm.getActiveNetworkInfo();
//	    if (netInfo != null && netInfo.isConnected()) {
	    	if (netInfo != null && netInfo.isConnectedOrConnecting()) {
	        return true;
	    }
	    return false;
	}
	
	

		/**
		 * 
		 * @param url 发送请求的URL
		 * @param params 请求参数
		 * @return 服务器响应字符串
		 * @throws Exception
		 */
		public static String postRequest(String url
			, Map<String ,String> rawParams)
		{
			HttpClient httpClient= new DefaultHttpClient();
			try{
				// 创建HttpPost对象。
				HttpPost post = new HttpPost(url);
				// 如果传递参数个数比较多的话可以对传递的参数进行封装
				List<NameValuePair> params = new ArrayList<NameValuePair>();
				for(String key : rawParams.keySet())
				{
					//封装请求参数
					params.add(new BasicNameValuePair(key , rawParams.get(key)));
				}
				// 设置请求参数
				post.setEntity(new UrlEncodedFormEntity(
					params,HTTP.UTF_8));
				// 发送POST请求
				HttpResponse httpResponse = httpClient.execute(post);
				// 如果服务器成功地返回响应
				int i = httpResponse.getStatusLine().getStatusCode();
				if (httpResponse.getStatusLine()
					.getStatusCode() == 200)
				{
					// 获取服务器响应字符串
					String result = EntityUtils
						.toString(httpResponse.getEntity());
					return result;
				}
			}catch(Exception e){
				e.printStackTrace();
			}finally{
				httpClient.getConnectionManager().shutdown();
			}
			return null;
		}


   

}
