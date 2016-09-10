package com.example.scrollview;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends ActionBarActivity {
	private ScrollView scrollView;
	private TextView textView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		textView = (TextView) findViewById(R.id.textView);
		scrollView = (ScrollView) findViewById(R.id.scrollView1);
		scrollView.setOnTouchListener(new OnTouchListener() {

			@Override
			public boolean onTouch(View view, MotionEvent event) {
				// TODO Auto-generated method stub
				switch (event.getAction()) {
				case MotionEvent.ACTION_MOVE:
					if (scrollView.getScrollY() <= 0) {
						Log.i("滑动", "滑到了顶部");
					} else if (scrollView.getScrollY() + scrollView.getHeight() >= scrollView
							.getChildAt(0).getMeasuredHeight()) {
						Log.i("滑动", "滑到了底部");
						textView.append(getResources().getString(
								R.string.content));
					}
					break;

				default:
					break;
				}
				return false;
			}
		});
	}

	public void onClick(View view) {
		//scrollTo始终是以(0,0)作为滑动的起点,而scrollBy则是以上一次滑动的终点作为起点。
		switch (view.getId()) {
		case R.id.up:
			scrollView.scrollBy(0, -30);
			break;
		case R.id.down:
			scrollView.scrollBy(0, 30);
			break;

		default:
			break;
		}
	}
}
