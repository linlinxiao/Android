package com.linlin.service;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;


public class MyService extends Service{

    private static final String TAG = "MyService";

    public MyService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();

        Log.i(TAG, "onCreate: ");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i(TAG, "onStartCommand: ");
        return super.onStartCommand(intent, flags, startId);
    }



    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.

        Log.i(TAG, "onBind: ");
        return new MyBinder(this);

    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.i(TAG, "onUnbind: ");
        return super.onUnbind(intent);
    }

    @Override
    public void onDestroy() {
        Log.i(TAG, "onDestroy: ");
        super.onDestroy();
    }


    public void play(Context context) {
        Toast.makeText(context, "播放", Toast.LENGTH_SHORT).show();
    }

    public void pause(Context context) {
        Toast.makeText(context, "暂停", Toast.LENGTH_SHORT).show();
    }

    public void next(Context context) {
        Toast.makeText(context, "下一首", Toast.LENGTH_SHORT).show();
    }

    public void previous(Context context) {
        Toast.makeText(context, "上一首", Toast.LENGTH_SHORT).show();
    }
}

class MyBinder extends Binder {
    private Service service;

    public MyBinder(Service s){
        service = s;
    }


    public Service getService() {
        return service;
    }
}
