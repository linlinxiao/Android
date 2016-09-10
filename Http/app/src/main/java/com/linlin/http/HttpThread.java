package com.linlin.http;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.os.Handler;
import android.util.Log;
import android.webkit.WebView;
import android.widget.ImageView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by linlin on 8/13/16.
 */
public class HttpThread extends Thread {

    private static final String TAG = "HttpThread";
    private String url;
    private WebView webView;
    private Handler handler;//用来回到主线程刷新UI的操作
    private ImageView imageView;

    private HttpThread(){}
    public HttpThread(String url, WebView webView, Handler handler) {
        this.url = url;
        this.webView = webView;
        this.handler = handler;
    }

    public HttpThread(String url, ImageView imageView, Handler handler) {
        this.url = url;
        this.imageView = imageView;
        this.handler = handler;
    }


    @Override
    public void run() {
        if (url == null) return;
        if (this.webView != null) loadHtml();
        if (this.imageView != null) loadImage();
    }

    private void loadHtml() {
        try {
            URL url1 = new URL(url);
            try {
                HttpURLConnection connection = (HttpURLConnection) url1.openConnection();
                connection.setRequestMethod("GET");
                connection.setConnectTimeout(5000);
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                final StringBuffer buffer = new StringBuffer();
                String str;
                while ((str = reader.readLine()) != null){
                    buffer.append(str);
                }
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        webView.loadData(buffer.toString(), "text/html;charset=utf-8", null);
                    }
                });
            }catch (IOException e){
                e.printStackTrace();
            }

        }catch (MalformedURLException e){
            e.printStackTrace();
        }
    }

    private void loadImage() {
        try {
            URL url1 = new URL(url);
            try {
                HttpURLConnection connection = (HttpURLConnection) url1.openConnection();
                connection.setRequestMethod("GET");
                connection.setConnectTimeout(5000);
                connection.setDoInput(true);
                InputStream inputStream = connection.getInputStream();

                if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
                    File parent = Environment.getExternalStorageDirectory();
                    String child = String.valueOf(System.currentTimeMillis());
                    File file = new File(parent, child);
                    FileOutputStream fileOutputStream = new FileOutputStream(file);

                    byte[] buffer = new byte[2 * 1024];
                    int length;
                    while ( (length = inputStream.read(buffer)) != -1) {
                        fileOutputStream.write(buffer, 0, length);
//                        fileOutputStream.write(buffer);
                    }

                    fileOutputStream.close();
                    inputStream.close();

                    Log.i(TAG, "loadImage: " + file.getAbsolutePath());
                    final Bitmap bitmap = BitmapFactory.decodeFile(file.getAbsolutePath());

                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            imageView.setImageBitmap(bitmap);
                        }
                    });
                }

            }catch (IOException e){
                e.printStackTrace();
            }

        }catch (MalformedURLException e){
            e.printStackTrace();
        }
    }
}
