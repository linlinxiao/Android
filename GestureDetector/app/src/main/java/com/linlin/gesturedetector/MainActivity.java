package com.linlin.gesturedetector;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity implements View.OnTouchListener {

    private ImageView imageView;
    private GestureDetector gestureDetector;

    class MyGestrueListener extends GestureDetector.SimpleOnGestureListener {
        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            if (e1.getX() - e2.getX() > 0){
                Toast.makeText(MainActivity.this, "向左滑动了", Toast.LENGTH_SHORT).show();
            }else if (e1.getX() - e2.getX() < 0){
                Toast.makeText(MainActivity.this, "向右滑动了", Toast.LENGTH_SHORT).show();
            }
            return super.onFling(e1, e2, velocityX, velocityY);
        }


        @Override
        public boolean onDown(MotionEvent e) {
            return super.onDown(e);
        }

        @Override
        public void onShowPress(MotionEvent e) {
            Toast.makeText(MainActivity.this, "点击", Toast.LENGTH_SHORT).show();

            super.onShowPress(e);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = (ImageView) findViewById(R.id.imageView);
        imageView.setOnTouchListener(this);

        gestureDetector = new GestureDetector(this, new MyGestrueListener());

    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        gestureDetector.onTouchEvent(event);
        return true;
    }
}
