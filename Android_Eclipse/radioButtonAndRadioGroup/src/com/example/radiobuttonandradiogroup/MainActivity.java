package com.example.radiobuttonandradiogroup;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;


public class MainActivity extends ActionBarActivity implements OnCheckedChangeListener {
	
	//RadioGroup可以实现多选一
	private RadioGroup radioGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        initViews();
    }
    
    private void initViews() {
    	radioGroup = (RadioGroup) findViewById(R.id.radioGroup1);
    	radioGroup.setOnCheckedChangeListener(this);
    }

	@Override
	public void onCheckedChanged(RadioGroup radioGroup, int radioButtonId) {
		// TODO Auto-generated method stub
		switch (radioButtonId) {
		case R.id.radio0:
			Log.i("sex", "您选择的性别是男性。");
			break;
		case R.id.radio1:
			Log.i("sex", "您选择的性别是女性。");
			break;

		default:
			break;
		}
	}
    
}
