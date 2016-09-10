package com.example.manifest;

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


    public void onClick(View view){
    	Intent intent = new Intent();
    	intent.setAction("com.example.secondActivity");
    	intent.addCategory("android.intent.category.DEFAULT");
    	startActivity(intent);
    }
}
