package com.linlin.http;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    private WebView webView;
    private ImageView imageView;
    private Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = (ImageView) findViewById(R.id.imageView);
        webView = (WebView) findViewById(R.id.webView);
//        HttpThread thread = new HttpThread("http://www.baidu.com", webView, handler);
        HttpThread thread = new HttpThread("http://pic3.nipic.com/20090709/2893198_075124038_2.jpg", imageView, handler);

        thread.start();
    }
}
