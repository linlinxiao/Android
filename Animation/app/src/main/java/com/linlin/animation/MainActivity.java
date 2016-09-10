package com.linlin.animation;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    private ImageView imageView;
    private Animation loadAnimation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = (ImageView) findViewById(R.id.imageView);
    }

    public void clickedButton(View view) {
        switch (view.getId()) {
            case R.id.alphaBtn:
                loadAnimation = AnimationUtils.loadAnimation(this, R.anim.alpha_anim);
                loadAnimation.setDuration(1000);
                imageView.startAnimation(loadAnimation);
                break;
            case R.id.rotateBtn:
                loadAnimation = AnimationUtils.loadAnimation(this, R.anim.rotate_anim);
                loadAnimation.setDuration(1000);
                imageView.startAnimation(loadAnimation);
                break;
            case R.id.scaleBtn:
                loadAnimation = AnimationUtils.loadAnimation(this, R.anim.scale_anim);
                loadAnimation.setDuration(1000);
                loadAnimation.setFillAfter(true);
                imageView.startAnimation(loadAnimation);
                break;
            case R.id.translateBtn:
                loadAnimation = AnimationUtils.loadAnimation(this, R.anim.translate_anim);
                loadAnimation.setDuration(1000);
                loadAnimation.setFillAfter(true);
                imageView.startAnimation(loadAnimation);
                break;
            case R.id.continueBtn1:
                loadAnimation = AnimationUtils.loadAnimation(this, R.anim.continue1_anim);
                imageView.startAnimation(loadAnimation);
                break;
            case R.id.continueBtn2:
                loadAnimation = AnimationUtils.loadAnimation(this, R.anim.alpha_anim);
                loadAnimation.setDuration(1000);
                imageView.startAnimation(loadAnimation);
                loadAnimation.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        Animation anim = AnimationUtils.loadAnimation(MainActivity.this, R.anim.rotate_anim);
                        anim.setDuration(1000);
                        imageView.startAnimation(anim);
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });
                break;
            case R.id.cutBtn:
                Intent intent = new Intent(this, SecondActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.scale_anim, R.anim.rotate_anim);
                break;
            case R.id.frameBtn:
                imageView.setImageResource(R.drawable.frame_anim);
                break;
            case R.id.layoutBtn:
                break;
            case R.id.twinkleBtn:
                loadAnimation = new AlphaAnimation(0.0f, 1.0f);
                loadAnimation.setDuration(100);
                loadAnimation.setRepeatCount(Integer.MAX_VALUE);
                loadAnimation.setRepeatMode(Animation.REVERSE);
                imageView.startAnimation(loadAnimation);
                break;
            case R.id.shakeBtn:
                Intent intent1 = new Intent(this, ThirdActivity.class);
                startActivity(intent1);
                break;
        }
    }
}
