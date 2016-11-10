package com.linlin.productdetaildemo;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class FlowLayout extends ViewGroup
{

	public FlowLayout(Context context, AttributeSet attrs, int defStyle)
	{
		super(context, attrs, defStyle);
		//
	}

	public FlowLayout(Context context, AttributeSet attrs)
	{
		this(context, attrs, 0);
	}

	public FlowLayout(Context context)
	{
		this(context, null);
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec)
	{
		int sizeWidth = MeasureSpec.getSize(widthMeasureSpec);
		int modeWidth = MeasureSpec.getMode(widthMeasureSpec);
		int sizeHeight = MeasureSpec.getSize(heightMeasureSpec);
		int modeHeight = MeasureSpec.getMode(heightMeasureSpec);

		// wrap_content
		int width = 0;
		int height = 0;

		// 记录每一行的宽度与高度
		int lineWidth = 0;
		int lineHeight = 0;

		// 得到内部元素的个数
		int cCount = getChildCount();

		for (int i = 0; i < cCount; i++)
		{
			View child = getChildAt(i);
			// 测量子View的宽和高
			measureChild(child, widthMeasureSpec, heightMeasureSpec);
//			// 得到LayoutParams
//			MarginLayoutParams lp = (MarginLayoutParams) child
//					.getLayoutParams();

			// 子View占据的宽度
			int childWidth = child.getMeasuredWidth();
			// 子View占据的高度
			int childHeight = child.getMeasuredHeight();

			// 换行
			if (lineWidth + childWidth > sizeWidth - getPaddingLeft() - getPaddingRight())
			{
				// 对比得到最大的宽度
				width = Math.max(width, lineWidth);
				// 重置lineWidth
				lineWidth = childWidth;
				// 记录行高
				height += lineHeight;
				lineHeight = childHeight;
			} else
			// 未换行
			{
				// 叠加行宽
				lineWidth += childWidth;
				// 得到当前行最大的高度
				lineHeight = Math.max(lineHeight, childHeight);
			}
			// 最后一个控件
			if (i == cCount - 1)
			{
				width = Math.max(lineWidth, width);
				height += lineHeight;
			}
		}

		Log.e("TAG", "sizeWidth = " + sizeWidth);
		Log.e("TAG", "sizeHeight = " + sizeHeight);

		setMeasuredDimension(
				//
				modeWidth == MeasureSpec.EXACTLY ? sizeWidth : width + getPaddingLeft() + getPaddingRight(),
				modeHeight == MeasureSpec.EXACTLY ? sizeHeight : height + getPaddingTop()+ getPaddingBottom()//
		);

	}

	/**
	 * 存储所有的View
	 */
	private List<List<View>> mAllViews = new ArrayList<List<View>>();
	/**
	 * 每一行的高度
	 */
	private List<Integer> mLineHeight = new ArrayList<Integer>();

	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b)
	{
		mAllViews.clear();
		mLineHeight.clear();

		// 当前ViewGroup的宽度
		int width = getWidth();

		int lineWidth = 0;
		int lineHeight = 0;

		List<View> lineViews = new ArrayList<View>();

		int cCount = getChildCount();

		for (int i = 0; i < cCount; i++)
		{
			View child = getChildAt(i);
//			MarginLayoutParams lp = (LinearLayout.MarginLayoutParams) child
//					.getLayoutParams();

			int childWidth = child.getMeasuredWidth();
			int childHeight = child.getMeasuredHeight();

			// 如果需要换行
			if (childWidth + lineWidth > width - getPaddingLeft() - getPaddingRight())
			{
				// 记录LineHeight
				mLineHeight.add(lineHeight);
				// 记录当前行的Views
				mAllViews.add(lineViews);

				// 重置我们的行宽和行高
				lineWidth = 0;
				lineHeight = childHeight;
				// 重置我们的View集合
				lineViews = new ArrayList<View>();
			}
			lineWidth += childWidth;
			lineHeight = Math.max(lineHeight, childHeight);
			lineViews.add(child);

		}// for end
			// 处理最后一行
		mLineHeight.add(lineHeight);
		mAllViews.add(lineViews);

		// 设置子View的位置

		int left = getPaddingLeft();
		int top = getPaddingTop();

		// 行数
		int lineNum = mAllViews.size();

		for (int i = 0; i < lineNum; i++)
		{
			// 当前行的所有的View
			lineViews = mAllViews.get(i);
			lineHeight = mLineHeight.get(i);

			for (int j = 0; j < lineViews.size(); j++)
			{
				View child = lineViews.get(j);
				// 判断child的状态
				if (child.getVisibility() == View.GONE)
				{
					continue;
				}

//				MarginLayoutParams lp = (MarginLayoutParams) child
//						.getLayoutParams();

				int lc = left;
				int tc = top;
				int rc = lc + child.getMeasuredWidth();
				int bc = tc + child.getMeasuredHeight();

				// 为子View进行布局
				child.layout(lc, tc, rc, bc);

				left += child.getMeasuredWidth();
			}
			left = getPaddingLeft() ; 
			top += lineHeight ; 
		}

	}

//	/**
//	 * 与当前ViewGroup对应的LayoutParams
//	 */
//	@Override
//	public LayoutParams generateLayoutParams(AttributeSet attrs)
//	{
//		return new MarginLayoutParams(getContext(), attrs);
//	}

}
