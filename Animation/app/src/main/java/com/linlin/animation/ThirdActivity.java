package com.linlin.animation;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ThirdActivity extends AppCompatActivity {

    private ListView listView;
    private String[] arr = {"北京", "上海", "深圳", "广州", "杭州", "长沙", "北京", "上海", "深圳", "广州", "杭州", "长沙"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        listView = (ListView) findViewById(R.id.listView);
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, arr);
        listView.setAdapter(adapter);

//        LayoutAnimationController controller = new LayoutAnimationController(AnimationUtils.loadAnimation(this, R.anim.continue1_anim));
        LayoutAnimationController controller = AnimationUtils.loadLayoutAnimation(this, R.anim.zoom_in);
        controller.setOrder(LayoutAnimationController.ORDER_RANDOM);
        listView.setLayoutAnimation(controller);
        listView.startLayoutAnimation();
    }
}
