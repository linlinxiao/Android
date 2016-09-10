package com.linlin.gestureoverlayview;

import android.gesture.Gesture;
import android.gesture.GestureLibraries;
import android.gesture.GestureLibrary;
import android.gesture.GestureOverlayView;
import android.gesture.Prediction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    private GestureOverlayView gestureOverlayView;
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final GestureLibrary gestureLibrary = GestureLibraries.fromRawResource(this, R.raw.gesture);
        gestureLibrary.load();

        gestureOverlayView = (GestureOverlayView) findViewById(R.id.gestureOverlayView);
        gestureOverlayView.addOnGesturePerformedListener(new GestureOverlayView.OnGesturePerformedListener() {
            @Override
            public void onGesturePerformed(GestureOverlayView gestureOverlayView, Gesture gesture) {
                ArrayList<Prediction> predictions = gestureLibrary.recognize(gesture);

                Log.i(TAG, "onGesturePerformed: "+predictions.toString());
                Prediction prediction = predictions.get(0);
                if (prediction.score >= 0.5) {
                    if (prediction.name.equals("exit")) {
                        finish();
                    } else if (prediction.name.equals("next")) {
                        Toast.makeText(MainActivity.this, "播放下一首", Toast.LENGTH_SHORT).show();
                    } else if (prediction.name.equals("previous")) {
                        Toast.makeText(MainActivity.this, "播放上一首", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(MainActivity.this, "手势无法识别", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}
