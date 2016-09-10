package com.example.activity;

import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;


//Activity的生命周期，方法从上而下依次执行
public class MainActivity extends ActionBarActivity implements OnClickListener{
	
	static String TAG = "tag";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        Button btn = (Button) findViewById(R.id.button1);
        btn.setOnClickListener(this);
        
    }
    
    @Override
    protected void onRestart() {
    	// TODO Auto-generated method stub
    	super.onRestart();
    	
    	Log.i(TAG, "-------------------MainActivity onRestart()-----------------------");
    }

    @Override
    protected void onStart() {
    	// TODO Auto-generated method stub
    	super.onStart();
    	
    	Log.i(TAG, "-------------------MainActivity onStart()-----------------------");
    }
    
    @Override
    protected void onResume() {
    	// TODO Auto-generated method stub
    	super.onResume();
    	
    	Log.i(TAG, "-------------------MainActivity onResume()-----------------------");
    }
    
    @Override
    protected void onPause() {
    	// TODO Auto-generated method stub
    	super.onPause();
    	
    	Log.i(TAG, "-------------------MainActivity onPause()-----------------------");
    }
    
    @Override
    protected void onStop() {
    	// TODO Auto-generated method stub
    	super.onStop();
    	
    	Log.i(TAG, "-------------------MainActivity onStop()-----------------------");
    }
    
    @Override
    protected void onDestroy() {
    	// TODO Auto-generated method stub
    	super.onDestroy();
    	
    	Log.i(TAG, "-------------------MainActivity onDestroy()-----------------------");
    }

	@Override
	public void onClick(View button) {
		// TODO Auto-generated method stub
		Intent intent = new Intent(MainActivity.this, SecondActivity.class);
		MainActivity.this.startActivity(intent);
	}
}
