package com.example.button;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity implements OnClickListener {
	
	private Button loginBtn;
	private ImageButton ImgBtn;
	private Button btn3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        //1.内部匿名内监听实现
        loginBtn = (Button)findViewById(R.id.loginBtn);
        loginBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Log.i("tag", "login btn clicked");
			}
		});
        
        //2.外部类实现点击监听
        ImgBtn = (ImageButton) findViewById(R.id.imgBtn);
        ImgBtn.setOnClickListener(new MyOnclickListener(){
        	@Override
        	public void onClick(View arg0){
        		super.onClick(arg0);
        		
        		Toast.makeText(MainActivity.this, "image button clicked", 1).show();
        	}
        });
        
        //3.实现接口的方式在Activity类实现监听事件
        btn3 = (Button) findViewById(R.id.button3);
        btn3.setOnClickListener(this);
        
        //4.在布局文件通过android:onClick属性绑定点击事件
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		Toast.makeText(this, "button3 clicked", 1).show();
	}
}

class MyOnclickListener implements OnClickListener
{

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		Log.i("tag", "button clicked");
	}
}
