package com.linlin.jsonapplication;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private ListView listView;
    private Handler handler = new Handler();
    private static final String TAG = "MainActivity";
    private View previousView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView) findViewById(R.id.listView);
        listView.setOnItemClickListener(this);
        new HttpJsonThread("http://192.168.0.106:8080/JsonServer/JsonServlet", handler, getLayoutInflater(), listView).start();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Log.i(TAG, "onItemSelected: " + view.toString());
        Toast.makeText(this, "我被点击了", Toast.LENGTH_SHORT).show();
        refreshCurrentItem(view);
        if (previousView != null) refreshPreviousItem(previousView);
        previousView = view;

    }

    private void refreshCurrentItem(View view) {
        ImageView imageView = (ImageView) view.findViewById(R.id.imageView);
        imageView.setBackgroundResource(android.R.color.holo_red_light);
    }
    private void refreshPreviousItem(View view) {
        ImageView imageView = (ImageView) view.findViewById(R.id.imageView);
        imageView.setBackgroundResource(android.R.color.white);
    }
}
