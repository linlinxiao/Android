package com.baoxiao.service.weihu;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import android.content.Context;
import com.baoxiao.model.UpdateInfo;

public class UpdateInforService {

	private Context context;

	public UpdateInforService(Context context) {
		this.context = context;
	}

	public UpdateInfo getUpdateInfo(int urlId) throws Exception{
		String path = context.getResources().getString(urlId);//拿到config.xml里面存放的地址
		URL url = new URL(path);
		HttpURLConnection http = (HttpURLConnection) url.openConnection();//开启一个http链接
		http.setConnectTimeout(5000);
		http.setRequestMethod("GET");
		InputStream is = http.getInputStream();
		
		return UpdateInfoParser.getUpdateInfo(is);//解析xml
		
	}
}
