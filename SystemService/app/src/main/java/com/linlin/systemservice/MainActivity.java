package com.linlin.systemservice;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void buttonClicked(View view) {
        switch (view.getId()) {
            case R.id.connectivityBtn:
                ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
                if (networkInfo != null) {
                    if (networkInfo.isConnected()) {
                        Toast.makeText(this, "网络已连接", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(this, "断开网络连接", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(this, "设备不支持网络服务", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.wifiBtn:
                break;
            case R.id.audioBtn:
                break;
            case R.id.pakageBtn:
                break;
            default:
                break;
        }
    }
}
