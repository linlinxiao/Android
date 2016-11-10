package com.linlin.basemodeltest;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.LinearLayout;

/**
 * Created by linlin on 9/28/16.
 */
public class CustomLinearLayout extends LinearLayout {
    private float scale;

    public CustomLinearLayout(Context context) {
        super(context);
    }

    public CustomLinearLayout(Context context, AttributeSet attrs) {
        super(context, attrs);

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.CustomLinearLayout);
        String widthToHeight = typedArray.getString(R.styleable.CustomLinearLayout_width_to_height);
        if (widthToHeight == null)return;
        String[] split = widthToHeight.split(":");
        if (split.length != 2) return;
        int widthto = new Integer(split[0]).intValue();
        int height = new Integer(split[1]).intValue();
        scale = widthto/(float)height;
    }

    public CustomLinearLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        if (scale == 0) return;
        int height = (int) (MeasureSpec.getSize(widthMeasureSpec)/scale);
        setMeasuredDimension(MeasureSpec.getSize(widthMeasureSpec), height);
        int heightSpec =  MeasureSpec.makeMeasureSpec(height, MeasureSpec.getMode(heightMeasureSpec));
        Log.i("tag", "onMeasure: height = " + height + "measureHeight = " + MeasureSpec.getSize(heightMeasureSpec));

        super.onMeasure(widthMeasureSpec, heightSpec);
//        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
    }
}
