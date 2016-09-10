package com.example.calculator;

import android.support.v7.app.ActionBarActivity;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;


public class MainActivity extends Activity implements OnClickListener{
	
	private TextView sumTextView;
	private Button btn_c;
	private Button btn_del;
	private Button btn_div;
	private Button btn_mul;
	private Button btn_dot;
	private Button btn_equal;
	private Button btn_add;
	private Button btn_sub;
	private Button btn_7;
	private Button btn_8;
	private Button btn_9;
	private Button btn_4;
	private Button btn_5;
	private Button btn_6;
	private Button btn_1;
	private Button btn_2;
	private Button btn_3;
	private Button btn_0;

	private String numString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        initViews();
    }
    
    private void initViews() {
    	sumTextView = (TextView) findViewById(R.id.textview);
    	btn_0 = (Button) findViewById(R.id.btn_0);
    	btn_1 = (Button) findViewById(R.id.btn_1);
    	btn_2 = (Button) findViewById(R.id.btn_2);
    	btn_3 = (Button) findViewById(R.id.btn_3);
    	btn_4 = (Button) findViewById(R.id.btn_4);
    	btn_5 = (Button) findViewById(R.id.btn_5);
    	btn_6 = (Button) findViewById(R.id.btn_6);
    	btn_7 = (Button) findViewById(R.id.btn_7);
    	btn_8 = (Button) findViewById(R.id.btn_8);
    	btn_9 = (Button) findViewById(R.id.btn_9);
    	btn_c = (Button) findViewById(R.id.btn_c);
    	btn_del = (Button) findViewById(R.id.btn_del);
    	btn_div = (Button) findViewById(R.id.btn_div);
    	btn_mul = (Button) findViewById(R.id.btn_mul);
    	btn_dot = (Button) findViewById(R.id.btn_dot);
    	btn_equal = (Button) findViewById(R.id.btn_equal);
    	btn_add = (Button) findViewById(R.id.btn_add);
    	btn_sub = (Button) findViewById(R.id.btn_sub);

    	btn_0.setOnClickListener(this);
    	btn_1.setOnClickListener(this);
    	btn_2.setOnClickListener(this);
    	btn_3.setOnClickListener(this);
    	btn_4.setOnClickListener(this);
    	btn_5.setOnClickListener(this);
    	btn_6.setOnClickListener(this);
    	btn_7.setOnClickListener(this);
    	btn_8.setOnClickListener(this);
    	btn_9.setOnClickListener(this);
    	btn_c.setOnClickListener(this);
    	btn_del.setOnClickListener(this);
    	btn_div.setOnClickListener(this);
    	btn_mul.setOnClickListener(this);
    	btn_dot.setOnClickListener(this);
    	btn_equal.setOnClickListener(this);
    	btn_add.setOnClickListener(this);
    	btn_sub.setOnClickListener(this);
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
	public void onClick(View btn) {
		// TODO Auto-generated method stub
		
	}
}
