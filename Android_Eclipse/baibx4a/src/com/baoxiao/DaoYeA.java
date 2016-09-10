package com.baoxiao;

import java.util.ArrayList;
import java.util.List;
import com.chechezhi.ui.guide.AbsGuideActivity;
import com.chechezhi.ui.guide.SingleElement;
import com.chechezhi.ui.guide.SinglePage;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

/**
 * 引导页面
 *
 */
public class DaoYeA extends AbsGuideActivity{

    @Override
    public List<SinglePage> buildGuideContent() {
        // prepare the information for our guide
		SharedPreferences sp = getSharedPreferences("loading", 0);
        Boolean flag = sp.getBoolean("loading_flag", false);	
	    //如果是首次安装登录，则引导页面出现。
        if(flag){
	    	Intent intent = new Intent(this,WelcomeA.class);
	    	startActivity(intent);
	    	finish();
	    }

        List<SinglePage> guideContent = new ArrayList<SinglePage>();

        SinglePage page01 = new SinglePage();
        page01.mBackground = getResources().getDrawable(R.drawable.daoye_bg_1);
//        SingleElement e01 = new SingleElement(200, 200, 400, 400, 0.0f, 1.0f, BitmapFactory.decodeResource(
//                getResources(), R.drawable.ic_stuff));
//        SingleElement e02 = new SingleElement(700, 800, 700, 100, 0.0f, 1.0f, BitmapFactory.decodeResource(
//                getResources(), R.drawable.ic_stuff));
//        page01.mElementsList.add(e01);
//        page01.mElementsList.add(e02);
        guideContent.add(page01);

        SinglePage page02 = new SinglePage();
        page02.mBackground = getResources().getDrawable(R.drawable.daoye_bg_2);
//        SingleElement e03 = new SingleElement(400, 400, -100, -100, 1.0f, 0.0f, BitmapFactory.decodeResource(
//                getResources(), R.drawable.ic_stuff));
//        SingleElement e04 = new SingleElement(700, 100, 700, -200, 1.0f, 0.0f, BitmapFactory.decodeResource(
//                getResources(), R.drawable.ic_stuff));
//        page02.mElementsList.add(e03);
//        page02.mElementsList.add(e04);
        guideContent.add(page02);

        SinglePage page03 = new SinglePage();
        page03.mBackground = getResources().getDrawable(R.drawable.daoye_bg_3);
//        SingleElement e05 = new SingleElement(-100, 2000, 100, 100, 1.0f, 1.0f, BitmapFactory.decodeResource(
//                getResources(), R.drawable.ic_stuff));
//        SingleElement e06 = new SingleElement(100, 2000, 300, 120, 1.0f, 1.0f, BitmapFactory.decodeResource(
//                getResources(), R.drawable.ic_stuff));
//        SingleElement e07 = new SingleElement(200, 2000, 600, 140, 1.0f, 1.0f, BitmapFactory.decodeResource(
//                getResources(), R.drawable.ic_stuff));
//        SingleElement e08 = new SingleElement(300, 2000, 900, 160, 1.0f, 1.0f, BitmapFactory.decodeResource(
//                getResources(), R.drawable.ic_stuff));
//        page03.mElementsList.add(e05);
//        page03.mElementsList.add(e06);
//        page03.mElementsList.add(e07);
//        page03.mElementsList.add(e08);
        guideContent.add(page03);

        SinglePage page04 = new SinglePage();
        page04.mBackground = getResources().getDrawable(R.drawable.daoye_bg_4);
//        SingleElement e09 = new SingleElement(100, 100, 3000, 3000, 1.0f, 1.0f, BitmapFactory.decodeResource(
//                getResources(), R.drawable.ic_stuff));
//        SingleElement e10 = new SingleElement(300, 120, 3000, 3000, 1.0f, 1.0f, BitmapFactory.decodeResource(
//                getResources(), R.drawable.ic_stuff));
//        SingleElement e11 = new SingleElement(600, 140, 3000, 3000, 1.0f, 1.0f, BitmapFactory.decodeResource(
//                getResources(), R.drawable.ic_stuff));
//        SingleElement e12 = new SingleElement(900, 160, 3000, 3000, 1.0f, 1.0f, BitmapFactory.decodeResource(
//                getResources(), R.drawable.ic_stuff));
//        page04.mElementsList.add(e09);
//        page04.mElementsList.add(e10);
//        page04.mElementsList.add(e11);
//        page04.mElementsList.add(e12);
        guideContent.add(page04);

        SinglePage page05 = new SinglePage();
        page05.mCustomFragment = new EntryFragment();
        guideContent.add(page05);

        return guideContent;
    }

    @Override
    public Bitmap dotDefault() {
        return BitmapFactory.decodeResource(getResources(), R.drawable.ic_dot_default);
    }

    @Override
    public Bitmap dotSelected() {
        return BitmapFactory.decodeResource(getResources(), R.drawable.ic_dot_selected);
    }

    @Override
    public boolean drawDot() {
        return true;
    }

    public void entryApp() {
        // Time to entry your app! We just finish the activity, replace it with
        // your code.
    	startActivity(new Intent(DaoYeA.this,WelcomeA.class));
        finish();
    }

    /**
     * You need provide an id to the pager. You could define an id in
     * values/ids.xml and use it.
     */
    @Override
    public int getPagerId() {
        return R.id.guide_container;
    }
}
