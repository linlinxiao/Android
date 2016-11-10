package com.linlin.productdetaildemo;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by linlin on 9/13/16.
 */
public class ProductDetailHttp extends Thread{

    private int productId = 0;
    private Handler handler;
    private static String urlStr = "http://2.3.1.ysctest.kuaidiantong.cn/Appshop/Appshophandler.ashx?action=getProductDetail&ProductId=";

    public ProductDetailHttp(Handler handler, int productId) {
        this.productId = productId;
        this.handler = handler;
        this.start();
    }

    @Override
    public void run() {
        try {
            String fullUrl = productId == 0 ? urlStr + "51" : urlStr + productId;
            URL url = new URL(fullUrl);
            try {
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                InputStream stream = connection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(stream));

                String string;
                StringBuffer stringBuffer = new StringBuffer();
                while ((string = bufferedReader.readLine()) != null) {
                    stringBuffer.append(string);
                }
                bufferedReader.close();
                stream.close();

                Message message = new Message();
                Bundle bundle = new Bundle();
                bundle.putString("result", stringBuffer.toString());
                message.setData(bundle);
                handler.sendMessage(message);

            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }
}
