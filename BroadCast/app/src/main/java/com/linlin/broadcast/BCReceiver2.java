package com.linlin.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by linlin on 8/16/16.
 */
public class BCReceiver2 extends BroadcastReceiver {

    private static final String TAG = "BCReceiver1";
    @Override
    public void onReceive(Context context, Intent intent) {
        String message = intent.getStringExtra("message");
        Toast.makeText(context, message + "BCReceiver1", Toast.LENGTH_SHORT).show();
        Log.i(TAG, "onReceive: " + "BCReceiver2");
    }
}
