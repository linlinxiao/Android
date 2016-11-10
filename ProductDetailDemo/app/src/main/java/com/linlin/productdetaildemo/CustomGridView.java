package com.linlin.productdetaildemo;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridView;

/**
 * Created by linlin on 8/23/16.
 */
public class CustomGridView extends GridView {
    public CustomGridView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    public CustomGridView(Context context) {
        super(context);
    }

    public CustomGridView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int childCount = getChildCount();
        int childWidth = DensityUtil.dip2px(getContext(),80) ;
        int childHeight = DensityUtil.dip2px(getContext(),100);
        int lastPadding = 10;
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
        super.onMeasure(expandSpec , heightMeasureSpec);

        //设置GridView的宽度
        setMeasuredDimension(childCount * childWidth + lastPadding, childHeight);
    }


}
