package com.example.manifestdemo;

import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void onClick(View btn) {
    	Intent intent = new Intent();
    	intent.setClassName("com.example.manifest", "com.example.manifest.MainActivity");
    	startActivity(intent);
    }
}
