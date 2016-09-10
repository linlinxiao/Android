package com.toast;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.button:
                showToast1();
                break;
            case R.id.button1:
                showToast2();
                break;
            case R.id.button2:
                showToast3();
                break;
            case R.id.button3:
                showToast4();
                break;
            default:
                break;
        }
    }

    private void showToast1() {
        Toast.makeText(this,"显示一个Toast",Toast.LENGTH_SHORT).show();
    }

    private void showToast2() {
        Toast toast = Toast.makeText(this,"显示一个Toast",Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }

    private void showToast3() {
        Toast toast = Toast.makeText(this,"显示一个Toast",Toast.LENGTH_SHORT);
        LinearLayout linearLayout = (LinearLayout) toast.getView();
        ImageView imageView = new ImageView(this);
        imageView.setImageResource(R.drawable.image);
        linearLayout.addView(imageView,0);
        toast.show();
    }

    private void showToast4() {
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.toast_layout, null);
        Toast toast = Toast.makeText(this,"显示一个Toast",Toast.LENGTH_SHORT);
        toast.setView(view);
        toast.show();
    }
}
