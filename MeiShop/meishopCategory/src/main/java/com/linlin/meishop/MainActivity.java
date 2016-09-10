package com.linlin.meishop;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TabHost;

/**
 * Created by linlin on 8/23/16.
 */
public class MainActivity extends TabActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab);

        TabHost tabHost = getTabHost();

        Intent intent1 = new Intent(this, HomeActivity.class);
        TabHost.TabSpec tabSpec1 = tabHost.newTabSpec("1").setIndicator("首页").setContent(intent1);
        tabHost.addTab(tabSpec1);

        Intent intent2 = new Intent(this, CategoryActivity.class);
        TabHost.TabSpec tabSpec2 = tabHost.newTabSpec("2").setIndicator("分类").setContent(intent2);
        tabHost.addTab(tabSpec2);

        tabHost.setCurrentTab(0);
    }
}
