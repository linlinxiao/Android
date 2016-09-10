package com.linlin.meishop;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import http.CategoryJsonParser;
import http.GridListViewAdapter;
import http.HttpJsonThread;
import http.ListViewAdapter;
import http.RequestURLManager;
import model.Category;

public class CategoryActivity extends Activity implements HttpJsonThread.RefreshUIListener, AdapterView.OnItemClickListener {
    private ListView leftListView;
    private ListView rightListView;
    private Handler handler = new Handler();
    private TextView chanelTextView;
    private ArrayList<Category> categories;
    private View previousItem;

    public void setPreviousItem(View previousItem) {
        this.previousItem = previousItem;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setProgressBarIndeterminateVisibility(true);
        setContentView(R.layout.activity_category);

        leftListView = (ListView) findViewById(R.id.leftListView);
        leftListView.setOnItemClickListener(this);
        rightListView = (ListView) findViewById(R.id.rightListView);
        chanelTextView = (TextView) findViewById(R.id.chanelTextView);

        HttpJsonThread thread = new HttpJsonThread(RequestURLManager.CATEGORY_URL, handler, getLayoutInflater());
        thread.setParser(new CategoryJsonParser());
        thread.setRefreshUIListener(this);
        thread.start();
    }

    @Override
    public void refreshUI(Object data) {
        categories = (ArrayList<Category>) data;
        leftListView.setAdapter(new ListViewAdapter(categories, getLayoutInflater()));
        rightListView.setAdapter(new GridListViewAdapter(categories.get(0).getSubs(), getLayoutInflater()));
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        chanelTextView.setText("进入"+categories.get(position).getName()+"频道 >");
        previousItem = view;
        ((ListViewAdapter)leftListView.getAdapter()).setCurrentSelectedPosition(position);
        ((ListViewAdapter) leftListView.getAdapter()).notifyDataSetChanged();

        if (categories.get(position).getHasChildren().equals(Category.TRUE))
        rightListView.setAdapter(new GridListViewAdapter(categories.get(position).getSubs(), getLayoutInflater()));
    }

}
