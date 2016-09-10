package linlin.com.notification;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private NotificationManager notificationManager;
    private int notification_ID = 110;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.sendBtn:
                sendNotification();
                break;
            case R.id.cancelBtn:
                notificationManager.cancel(notification_ID);
                break;
        }
    }

    //发送、取消本地通知
    private void sendNotification() {
        Notification.Builder builder = new Notification.Builder(this);
        builder.setSmallIcon(R.drawable.ic_launcher);
        builder.setTicker("我来了");//设置通知标题
        builder.setContentTitle("通知栏标题");//设置通知栏的标题
        builder.setContentText("通知栏类容");//设置通知栏内容
        builder.setWhen(System.currentTimeMillis());//设置通知时间
        builder.setDefaults(Notification.DEFAULT_LIGHTS);//闪关灯提示
        builder.setDefaults(Notification.DEFAULT_SOUND);//声音提示
        builder.setDefaults(Notification.DEFAULT_VIBRATE);//震动提示
//        builder.setDefaults(Notification.DEFAULT_ALL);//使用全部提示
        Intent intent = new Intent(this, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);
        builder.setContentIntent(pendingIntent);
        Notification notification = builder.build();
        notificationManager.notify(notification_ID, notification);
    }
}
