package com.example.togglebutton;

import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ImageView;
import android.widget.ToggleButton;


public class MainActivity extends ActionBarActivity implements OnCheckedChangeListener{
	
	private ToggleButton togButton;//开关按钮
	private ImageView imgView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        initViews();
    }
    
    private void initViews() {
    	togButton = (ToggleButton) findViewById(R.id.toggleButton1);
    	togButton.setOnCheckedChangeListener(this);
    	
    	imgView = (ImageView) findViewById(R.id.imageView1);
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
	public void onCheckedChanged(CompoundButton button, boolean checked) {
		// TODO Auto-generated method stub
		imgView.setBackgroundResource(checked ? R.drawable.on : R.drawable.off);
		
		Intent intent = new Intent(this, CheckButtonActivity.class);
		startActivity(intent);
	}

	
}
