package com.linlin.jsonapplication;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by linlin on 8/17/16.
 */
public class HttpImageThread extends Thread {

    private Handler handler;
    private ImageView imageView;
    private String url;

    public HttpImageThread(String url, Handler handler, ImageView imageView) {
        this.url = url;
        this.handler = handler;
        this.imageView = imageView;
    }

    @Override
    public void run() {
        try {
            URL httpUrl = new URL(url);
            try {
                URLConnection connection = httpUrl.openConnection();
                connection.setConnectTimeout(5000);
                InputStream inputStream = connection.getInputStream();
                final Bitmap bitmap = BitmapFactory.decodeStream(inputStream);

                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        imageView.setImageBitmap(bitmap);
                    }
                });
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }
}
