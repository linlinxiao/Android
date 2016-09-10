package com.linlin.service;

import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private Intent intent1;
    private Intent intent2;
    private MyService service;

    private boolean isBinded;
    private ServiceConnection conn = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            Log.i(TAG, "onServiceConnected: ");
            service = (MyService)((MyBinder)iBinder).getService();
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            Log.i(TAG, "onServiceDisconnected: ");
            service = null;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }

    public void buttonClicked(View view) {
        switch (view.getId()) {
            case R.id.startBtn:
                intent1 = new Intent(this, MyService.class);
                startService(intent1);
                break;
            case R.id.stopBtn:
                stopService(intent1);
                break;
            case R.id.bindBtn:
                intent2 = new Intent(this, MyService.class);
                isBinded = bindService(intent2, conn, Service.BIND_AUTO_CREATE);
                break;
            case R.id.unbindBtn:
                if (!isBinded) return;
                unbindService(conn);
                isBinded = false;
                break;
            case R.id.playBtn:
                if (service == null) return;
                service.play(this);
                break;
            case R.id.pauseBtn:
                if (service == null) return;
                service.pause(this);
                break;
            case R.id.nextBtn:
                if (service == null) return;
                service.next(this);
                break;
            case R.id.previousBtn:
                if (service == null) return;
                service.previous(this);
                break;
            default:
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (isBinded) unbindService(conn);
    }
}
