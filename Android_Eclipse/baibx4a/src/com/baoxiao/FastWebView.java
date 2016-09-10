package com.baoxiao;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.webkit.WebView;

/**
 * 自定义的WebView
 *
 */
public class FastWebView extends WebView {

    private boolean is_active = true;//是否激活状态
    private boolean is_gone = false;
    public FastWebView(Context context) {
        super(context);
    }
 
    public FastWebView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onWindowVisibilityChanged(int visibility) {
        super.onWindowVisibilityChanged(visibility);
        if (!is_active) {
        	return;
		}
        if(visibility == View.GONE) {
            try{
                WebView.class.getMethod("onPause").invoke(this);// stop flash
            }catch(Exception e) {
            }
            this.pauseTimers();
            this.is_gone = true;
        }else if(visibility == View.VISIBLE) {
            try{
                WebView.class.getMethod("onResume").invoke(this);// resume flash
            }catch(Exception e) {
            }
            this.resumeTimers();
            this.is_gone = false;
        }
    }
    @Override
    protected void onDetachedFromWindow() {
    	super.onDetachedFromWindow();
        if (!is_active) {
        	return;
		}
        if(this.is_gone) {
            try{
                this.destroy();
            }catch(Exception e) {
            }
        }
    }

	public void setIs_active(boolean is_active) {
		this.is_active = is_active;
	}
}
