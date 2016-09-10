package com.linlin.styledefine;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by linlin on 8/19/16.
 */
public class TapBar extends RelativeLayout {
    private Button leftButton;
    private Button rightButton;
    private TextView titleTV;

    private String titleText;
    private int titleTextColor;
    private float titleTextSize;

    private String rightButtonText;
    private int rightButtonTextColor;
    private float rightButtonTextSize;
    private int rightButtonBackgroundColor;
    private int rightButtonBackgroundResid;

    private String leftButtonText;
    private int leftButtonTextColor;
    private float leftButtonTextSize;
    private int leftButtonBackgroundColor;

    private OnClickedTapBarListener onClickedTapBarListener;

    public void setOnClickedTapBarListener(OnClickedTapBarListener onClickedTapBarListener) {
        this.onClickedTapBarListener = onClickedTapBarListener;
    }

    interface OnClickedTapBarListener {
        public void onClickedLeft();
        public void onClickedRight();
    }
    
    
    public TapBar(Context context, AttributeSet attrs) {
        super(context, attrs);

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.TopBar);
        
        titleText = typedArray.getString(R.styleable.TopBar_titleText);
        titleTextColor = typedArray.getColor(R.styleable.TopBar_titleTextColor, 0);
        titleTextSize = typedArray.getDimension(R.styleable.TopBar_titleTextSize, 0);

        rightButtonText = typedArray.getString(R.styleable.TopBar_rightButtonText);
        rightButtonTextColor = typedArray.getColor(R.styleable.TopBar_rightButtonTextColor, 0);
        rightButtonTextSize = typedArray.getDimension(R.styleable.TopBar_rightButtonTextSize, 0);
//        rightButtonBackgroundColor = typedArray.getColor(R.styleable.TopBar_rightButtonBackgroundColor, 0);
        rightButtonBackgroundResid = typedArray.getResourceId(R.styleable.TopBar_rightButtonBackgroundColor, 0);

        leftButtonText = typedArray.getString(R.styleable.TopBar_leftButtonText);
        leftButtonTextColor = typedArray.getColor(R.styleable.TopBar_leftButtonTextColor, 0);
        leftButtonTextSize = typedArray.getDimension(R.styleable.TopBar_leftButtonTextSize, 0);
        leftButtonBackgroundColor = typedArray.getColor(R.styleable.TopBar_leftButtonBackgroundColor, 0);

        setBackgroundColor(0xfffffff3);

        leftButton = new Button(context);
        LayoutParams leftLayoutParams = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        leftLayoutParams.addRule(RelativeLayout.CENTER_VERTICAL,TRUE);
        leftLayoutParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT, TRUE);
        addView(leftButton, leftLayoutParams);
        leftButton.setGravity(Gravity.CENTER);
        leftButton.setText(leftButtonText);
        leftButton.setTextColor(leftButtonTextColor);
        leftButton.setTextSize(leftButtonTextSize);
        leftButton.setBackgroundColor(leftButtonBackgroundColor);
        leftButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onClickedTapBarListener != null) onClickedTapBarListener.onClickedLeft();
            }
        });

        rightButton = new Button(context);
        LayoutParams rightLayoutParams = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        rightLayoutParams.addRule(RelativeLayout.CENTER_VERTICAL,TRUE);
        rightLayoutParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT, TRUE);
        addView(rightButton, rightLayoutParams);
        rightButton.setGravity(Gravity.CENTER);
        rightButton.setText(rightButtonText);
        rightButton.setTextColor(rightButtonTextColor);
        rightButton.setTextSize(rightButtonTextSize);
//        rightButton.setBackgroundColor(rightButtonBackgroundColor);
        rightButton.setBackgroundResource(rightButtonBackgroundResid);
        rightButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onClickedTapBarListener != null) onClickedTapBarListener.onClickedRight();
            }
        });

        titleTV = new TextView(context);
        LayoutParams titleLayoutParams = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT);
        titleLayoutParams.addRule(RelativeLayout.CENTER_IN_PARENT,TRUE);
        addView(titleTV, titleLayoutParams);
        titleTV.setGravity(Gravity.CENTER);
        titleTV.setText(titleText);
        titleTV.setTextColor(titleTextColor);
        titleTV.setTextSize(titleTextSize);

        typedArray.recycle();
    }

    public void setLeftVisible(boolean flag) {
        if (flag) {
            leftButton.setVisibility(VISIBLE);
        } else {
            leftButton.setVisibility(GONE);
        }
    }
}
