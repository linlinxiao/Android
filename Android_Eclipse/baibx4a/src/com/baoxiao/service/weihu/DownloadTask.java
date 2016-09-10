package com.baoxiao.service.weihu;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import android.app.ProgressDialog;


public class DownloadTask{

	public static File getFile(String path,String filePath,ProgressDialog progressDialog) throws Exception{
		URL url = new URL(path);
		HttpURLConnection http = (HttpURLConnection) url.openConnection();
		http.setConnectTimeout(5000);
		http.setRequestMethod("GET");
		if(http.getResponseCode() == 200){
			int total = http.getContentLength();
			progressDialog.setMax(total);
			
			InputStream is = http.getInputStream();
			File file = new File(filePath);
			FileOutputStream fos = new FileOutputStream(file);
			byte[] buffer = new byte[1024];
			int len;
			int process = 0;
			while((len = is.read(buffer))!=-1){
				fos.write(buffer, 0, len);
				process += len;
				progressDialog.setProgress(process);
			}
			fos.flush();
			fos.close();
			is.close();
			return file;
		}
		return null;
	}
}
