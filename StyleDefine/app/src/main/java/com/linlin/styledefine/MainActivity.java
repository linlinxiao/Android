package com.linlin.styledefine;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements TapBar.OnClickedTapBarListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TapBar topBar = (TapBar) findViewById(R.id.topBar);
        topBar.setOnClickedTapBarListener(this);
    }

    @Override
    public void onClickedLeft() {
        Toast.makeText(this, "您点击了左边", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClickedRight() {
        Toast.makeText(this, "这次您点击了右边", Toast.LENGTH_SHORT).show();
    }
}
