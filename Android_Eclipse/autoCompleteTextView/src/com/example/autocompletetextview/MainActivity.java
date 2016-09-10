package com.example.autocompletetextview;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.MultiAutoCompleteTextView;
import android.widget.MultiAutoCompleteTextView.Tokenizer;


public class MainActivity extends ActionBarActivity {
	
	//自动匹配不全
	private AutoCompleteTextView acTextView;
	private MultiAutoCompleteTextView macTextView;
	private String[] list = {"北京１", "beijing2", "beijing3", "shanghai1", "shanghai2"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        initViews();
    }
    
    private void initViews(){
    	//单词匹配
    	acTextView = (AutoCompleteTextView) findViewById(R.id.autoCompleteTextView1);
    	ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, list);
    	acTextView.setAdapter(adapter);
    	
    	//多选匹配
    	macTextView = (MultiAutoCompleteTextView) findViewById(R.id.autoCompleteTextView2);
    	ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, list);
    	macTextView.setAdapter(adapter2);
    	//设置逗号分隔符
    	macTextView.setTokenizer(new MultiAutoCompleteTextView.CommaTokenizer());
    	
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
}
