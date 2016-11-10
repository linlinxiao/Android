package com.linlin.productdetaildemo;

import android.animation.ObjectAnimator;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class MainActivity extends AppCompatActivity implements View.OnTouchListener, AdapterView.OnItemClickListener, View.OnClickListener, SkuListAdapter.OnSkuItemSelectedListener {

    private View skuView;

    private TextView originPriceTV;
    private ScrollView topScrollView;
    private ViewPager goods_detail;
    private ProgressDialog progressDialog;
    private ViewFlipper viewFlipper;
    private TextView titleTV;
    private TextView saleCountTV;
    private TextView freightTV;
    private GridView productListGridView;
    private TextView priceTV;
    private TextView pageCountTV;
    private ListView skuListView;
    private RelativeLayout bottomBar;
    private Button addCountBtn;
    private Button reduceCountBtn;
    private TextView goodsCountTV;
    private TextView selectedGoodSkuTV;
    private TextView skuPriceTV;
    private TextView skuStockTV;
    private ImageView skuImageView;

    private static final String TAG = "MainActivity";
    private boolean isUpEnable = false;

    private Handler handle = new MyHandle();
    private ProductDetail productDetail;

    private float startY;//手指接触到屏幕的起始点
    private int productId = 0;

    private HashMap<String,String> skuNameMap = new HashMap<>();
    private HashMap<String,String> skuIdMap = new HashMap<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = getIntent();
        if (intent != null) {
            productId = intent.getIntExtra("productId",0);
        }

        initViews();

        getData();

        Log.i(TAG, "onCreate: app name"+((MyApplication)getApplication()).getAppName());
    }

    private void initViews() {
        originPriceTV = (TextView) findViewById(R.id.originPriceTV);
        originPriceTV.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);//文字添加下划线
        topScrollView = (ScrollView) findViewById(R.id.scrollView);
        topScrollView.setOnTouchListener(this);
        viewFlipper = (ViewFlipper) findViewById(R.id.viewFlipper);
        titleTV = (TextView) findViewById(R.id.titleTV);
        pageCountTV = (TextView) findViewById(R.id.pageCountTV);
        freightTV = (TextView) findViewById(R.id.freightTV);
        productListGridView = (GridView) findViewById(R.id.productListGridView);
        productListGridView.setOnItemClickListener(this);
        priceTV = (TextView) findViewById(R.id.priceTV);
        saleCountTV = (TextView) findViewById(R.id.saleCountTV);
        addBottomBar();
    }

    private void addBottomBar() {
        if (bottomBar == null) {
            bottomBar = (RelativeLayout) getLayoutInflater().inflate(R.layout.bottom_bar, null);
            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            layoutParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
            addContentView(bottomBar, layoutParams);
        }
        bottomBar.bringToFront();
    }

    private void getData() {
        progressDialog = new ProgressDialog(this);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.show();
        new ProductDetailHttp(handle,productId);
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {

        if (motionEvent.getAction() == MotionEvent.ACTION_DOWN){
            startY = motionEvent.getY();
        }
        else if (motionEvent.getAction() == MotionEvent.ACTION_UP){

            if (view instanceof WebView && view.getScrollY() > 0) {

                Log.i("tag", "onTouch: getScroll Y = "+view.getScrollY());
                view.onTouchEvent(motionEvent);

                return true;
            }
            else if (startY - motionEvent.getY() > 5 && view == topScrollView && topScrollView.getScrollY()+topScrollView.getHeight() >= topScrollView.getChildAt(0).getMeasuredHeight() && !isUpEnable) {
               //向上滑动
                addViewPager();
                float height = (float) getWindow().getDecorView().getHeight();
                ObjectAnimator.ofFloat(topScrollView, "translationY", 0F, -height).setDuration(1000).start();
                ObjectAnimator.ofFloat(goods_detail, "translationY", (float) goods_detail.getHeight(), 0F).setDuration(1000).start();
                isUpEnable = true;
                addBottomBar();
            }
            else if(motionEvent.getY() - startY > 5 && view instanceof WebView && view.getScrollY() <= 0 && isUpEnable){
                //向下滑动
                float height = (float) getWindow().getDecorView().getHeight();
                ObjectAnimator.ofFloat(topScrollView, "translationY", -height, 0F).setDuration(1000).start();
                ObjectAnimator.ofFloat(goods_detail, "translationY", 0F, (float) goods_detail.getHeight()).setDuration(1000).start();
                isUpEnable = false;
                addBottomBar();
            }
        }

        return false;
    }

    public void addViewPager() {
        if (goods_detail == null){
            goods_detail = (ViewPager) getLayoutInflater().inflate(R.layout.goods_detail, null);
            goods_detail.setOnTouchListener(this);
            RelativeLayout.LayoutParams layoutParams =  new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            layoutParams.bottomMargin = 49;
            layoutParams.addRule(RelativeLayout.ALIGN_PARENT_TOP);
            goods_detail.setAdapter(new ViewPagerAdapter(MainActivity.this, productId));
            addContentView(goods_detail, layoutParams);
        }
    }

    public void chooseSku(View view) {
        initSkuView();

        skuView.setVisibility(View.VISIBLE);

        ObjectAnimator.ofFloat(skuView, "alpha", 0F, 1F).setDuration(800).start();
    }

    private void initSkuView(){
        if (skuView == null) {
            skuView = getLayoutInflater().inflate(R.layout.sku_choose,null);
            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            layoutParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
            layoutParams.bottomMargin = 49;
            addContentView(skuView, layoutParams);
            skuListView = (ListView) skuView.findViewById(R.id.skuListView);
            skuListView.addFooterView(getLayoutInflater().inflate(R.layout.goods_count, null));

            SkuListAdapter skuAdapter = new SkuListAdapter((ArrayList<SkuItemModel>) productDetail.getSkuItem(),MainActivity.this);
            skuAdapter.setOnSkuItemSelectedListener(this);
            skuListView.setAdapter(skuAdapter);

            addCountBtn = (Button) skuView.findViewById(R.id.addCountBtn);
            addCountBtn.setOnClickListener(this);
            reduceCountBtn = (Button) skuView.findViewById(R.id.reduceCountBtn);
            reduceCountBtn.setOnClickListener(this);
            goodsCountTV = (TextView) skuView.findViewById(R.id.goodCountTV);
            goodsCountTV.setTag("1");

            selectedGoodSkuTV = (TextView) skuView.findViewById(R.id.selectedCountTV);
            skuPriceTV = (TextView) skuView.findViewById(R.id.priceTV);
            skuPriceTV.setText("￥"+productDetail.getDefaultSku().getSalePrice());
            skuStockTV = (TextView) skuView.findViewById(R.id.stockTV);
            skuStockTV.setText("库存："+productDetail.getDefaultSku().getStock());
            skuImageView = (ImageView) skuView.findViewById(R.id.skuImageView);
        }
    }

    public void cancelSku(View view) {
        skuView.setVisibility(View.INVISIBLE);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        ProduceListAdapter.Holder holder = (ProduceListAdapter.Holder) view.getTag();
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("productId", holder.getProductListItemModel().getProductId());
        startActivity(intent);
    }

    @Override
    public void onClick(View v) {
        int count = Integer.parseInt((String)goodsCountTV.getTag());
        switch (v.getId()) {
            case R.id.addCountBtn:
            {
                goodsCountTV.setText(String.valueOf(count+1));
                goodsCountTV.setTag(goodsCountTV.getText());
            }
                break;
            case R.id.reduceCountBtn:
            {
                if (count == 0) return;
                goodsCountTV.setText(String.valueOf(count-1));
                goodsCountTV.setTag(goodsCountTV.getText());
            }
                break;
            default:
                break;
        }
    }

    //选择规格
    @Override
    public void onSkuItemSelected(AttributeModel attributeModel, SkuItemModel skuItemModel) {
        skuNameMap.put(skuItemModel.getAttributeName(), attributeModel.getValue());
        skuIdMap.put(skuItemModel.getAttributeId(),attributeModel.getValueId());
        selectedGoodSkuTV.setText("已选："+skuString());
        SkuModel findedSkuModel = findSelectedSkuModel();
        if (findedSkuModel != null){
            skuPriceTV.setText("￥"+findedSkuModel.getSalePrice());
            skuStockTV.setText("库存："+findedSkuModel.getStock());
        }

    }

    //查找已选择的规格
    private SkuModel findSelectedSkuModel(){
        ArrayList<SkuModel> skuModels = (ArrayList<SkuModel>) productDetail.getSkus();
//        if (skuIdMap.size() <= skuModels.size()) return null;

        SkuModel findedSkuModel = null;
        for (SkuModel skuModel : skuModels){
            int index = 0;
            for (String valueId : skuIdMap.values()) {
                if (!skuModel.getSkuId().contains(valueId))
                {
                    findedSkuModel = null;
                    break;
                }
                else{
                    findedSkuModel = skuModel;
                    index++;
                    if (index == skuIdMap.size()) return findedSkuModel;
                    continue;
                }
            }
        }

        return findedSkuModel;
    }

    private String skuString(){
        StringBuffer buffer = new StringBuffer();
        for (String key:skuNameMap.keySet()){
            buffer.append("\"");
            buffer.append(skuNameMap.get(key));
            buffer.append("\"");
        }

        return buffer.toString();
    }

    class MyHandle extends Handler {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            if (progressDialog.isShowing()) {
                progressDialog.dismiss();
            }

            Bundle data = msg.getData();
            String jsonString = data.getString("result");
            try {
                JSONObject jsonObject = new JSONObject(jsonString);
                productDetail = JsonParser.parseJsonForResult(jsonObject);
                refreshUI();
            } catch (JSONException e) {
                e.printStackTrace();
                Toast.makeText(MainActivity.this, "Json解析错误", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void refreshUI() {
        originPriceTV.setText("￥"+productDetail.getMarketPrice());
        setFlipperImages(productDetail.getImageUrl());
        priceTV.setText("￥"+productDetail.getDefaultSku().getSalePrice());
        titleTV.setText(productDetail.getProductName());
        saleCountTV.setText(""+productDetail.getSaleCounts());
        freightTV.setText(""+productDetail.getFreight());
        productListGridView.layout(productListGridView.getLeft(), productListGridView.getTop(),productListGridView.getLeft()+80*productDetail.getGuessYouLikeProducts().size(),productListGridView.getBottom());
        productListGridView.setAdapter(new ProduceListAdapter((ArrayList<ProductListItemModel>) productDetail.getGuessYouLikeProducts(), MainActivity.this));
    }

    private void setFlipperImages(final List<String> images) {
        if (images.size() == 0) return;

        pageCountTV.setText("1/"+images.size());
        for (int i = 0; i < images.size(); i++) {
            ImageView imageView = new ImageView(this);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            new HttpImageThread(images.get(i), handle, imageView).start();
            imageView.setTag(""+(i+1));
            viewFlipper.addView(imageView);
        }
        viewFlipper.setInAnimation(AnimationUtils.loadAnimation(this, R.anim.right_in));
        viewFlipper.setOutAnimation(AnimationUtils.loadAnimation(this, R.anim.left_out));
        viewFlipper.setFlipInterval(3000);
        viewFlipper.setAutoStart(true);
        viewFlipper.setFilterTouchesWhenObscured(true);
        viewFlipper.setOnTouchListener(new View.OnTouchListener() {
            float startX = 0;
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    startX = event.getX();
                }
                if (event.getAction() == MotionEvent.ACTION_UP){
                    if (images.size() <= 1)return true;
                    if (event.getX() - startX > 5) {
                        viewFlipper.setInAnimation(AnimationUtils.loadAnimation(MainActivity.this, R.anim.left_in));
                        viewFlipper.setOutAnimation(AnimationUtils.loadAnimation(MainActivity.this, R.anim.right_out));
                        viewFlipper.showPrevious();
                        pageCountTV.setText(viewFlipper.getCurrentView().getTag()+ "/"+images.size());
                    } else if (startX - event.getX() > 5){
                        viewFlipper.setInAnimation(AnimationUtils.loadAnimation(MainActivity.this, R.anim.right_in));
                        viewFlipper.setOutAnimation(AnimationUtils.loadAnimation(MainActivity.this, R.anim.left_out));
                        viewFlipper.showNext();
                        pageCountTV.setText(viewFlipper.getCurrentView().getTag()+"/"+images.size());
                    }
                }
                return true;
            }
        });
    }


}
