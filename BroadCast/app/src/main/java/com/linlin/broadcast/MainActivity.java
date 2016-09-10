package com.linlin.broadcast;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void btnClicked(View view) {
        switch (view.getId()) {
            //普通广播：同一级别的广播，接收是无序的
            //         高优先级的比低优先级的接收者更早接受到广播
            //         不能截断广播，不能传递数据
            //         动态注册的广播接收者优先级高于静态注册的
            case R.id.button:
                Intent intent = new Intent("com.linlin.BroadCast.Cast");
                intent.putExtra("message", "我是一个普通广播");
                sendBroadcast(intent);
                break;
            //有序广播：同一级别的广播，接收是无序的
            //         高优先级的比低优先级的接收者更早接受到广播
            //         可以截断广播，可以传递数据(高优先级截断低优先级的，高优先级向低优先级传递数据)
            //         动态注册的广播接收者优先级高于静态注册的
            case R.id.button2:
                Intent intent1 = new Intent(this, BCReceiver1.class);
                intent1.putExtra("message", "我是一个有序广播");
                sendOrderedBroadcast(intent1, null);
                break;
            //滞留广播：同一级别的广播，接收是无序的
            //         高优先级的比低优先级的接收者更早接受到广播
            //         不能截断广播，不能传递数据
            //         动态注册的广播接收者优先级高于静态注册的
            //         广播滞留，可以先发送再注册广播接收者
            case R.id.button3:
                Intent intent2 = new Intent("com.linlin.BroadCast.hahaha");
                intent2.putExtra("message", "我是一个滞留广播");
                sendStickyBroadcast(intent2);
                BCReceiver2 receiver = new BCReceiver2();
                IntentFilter filter = new IntentFilter("com.linlin.BroadCast.hahaha");
                registerReceiver(receiver, filter);
                break;
            default:
                break;
        }
    }
}
