package com.linlin.httppost;

import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Created by linlin on 8/14/16.
 */
public class HttpPostThread extends Thread {

    private static final String TAG = "tag";
    private String name;
    private String url;
    private String method;

    public HttpPostThread(String name, String url, String method) {
        this.name = name;
        this.url = url;
        this.method = method;

        start();
    }

    @Override
    public void run() {
        if (method.equalsIgnoreCase("get")) doGet();
        else doPost();
    }

    private void doPost() {
        try {
            URL u = new URL(this.url);
            try {
                HttpURLConnection connection = (HttpURLConnection) u.openConnection();
                connection.setRequestMethod("POST");
                connection.setConnectTimeout(5000);
                OutputStream outputStream = connection.getOutputStream();
                outputStream.write(("name="+this.name).getBytes());
                InputStream inputstream = connection.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputstream));
                StringBuffer buffer = new StringBuffer();
                String str;
                while ((str = reader.readLine()) != null){
                    buffer.append(str);
                }

                Log.i(TAG, "doPost: " + buffer.toString());
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    private void doGet() {
        try {
            //得到系统的属性信息
//            Properties properties = System.getProperties();
//            properties.list(System.out);
            URL u = null;
            try {
                //中文编码，解决输入中文乱码的问题
                u = new URL(this.url+"?name="+ URLEncoder.encode(this.name, "utf-8"));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            try {
                HttpURLConnection connection = (HttpURLConnection) u.openConnection();
                connection.setRequestMethod("GET");
                connection.setConnectTimeout(5000);
                InputStream inputstream = connection.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputstream));
                StringBuffer buffer = new StringBuffer();
                String str;
                while ((str = reader.readLine()) != null){
                    buffer.append(str);
                }

                Log.i(TAG, "doGet: " + buffer.toString());
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }
}
