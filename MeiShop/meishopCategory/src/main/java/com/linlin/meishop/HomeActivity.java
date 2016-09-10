package com.linlin.meishop;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ViewFlipper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import adapter.HomeListViewAdapter;
import http.HttpImageThread;
import http.HttpJsonThread;
import http.RequestURLManager;
import model.Advertisement;
import model.Goods;
import model.HomeResult;
import model.Navigate;
import model.Product;
import model.Topic;

/**
 * Created by linlin on 8/23/16.
 */
public class HomeActivity extends Activity implements HttpJsonThread.RefreshUIListener{

    private ListView listView;
    private ViewFlipper flipper;

    public Handler getHandler() {
        return handler;
    }

    private Handler handler = new Handler();;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        listView = (ListView) findViewById(R.id.testListView);
        View header = getLayoutInflater().inflate(R.layout.home_list_header, null);
        flipper = (ViewFlipper) header.findViewById(R.id.viewFlipper);
        listView.addHeaderView(header);

        HttpJsonThread thread = new HttpJsonThread(RequestURLManager.HOME_GOODS_URL, handler, getLayoutInflater());
        thread.setParser(new HomeParser());
        thread.setRefreshUIListener(this);
        thread.start();
    }

    @Override
    public void refreshUI(Object data) {
        HomeResult result = (HomeResult) data;
        listView.setAdapter(new HomeListViewAdapter(result.getTagProducts(), this));
        addFlipperViews(result.getAdvs());
    }

    private void addFlipperViews(List<Advertisement> advs) {
        for (int i = 0; i< advs.size(); i++) {
            ImageView imageView = new ImageView(this);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            new HttpImageThread(advs.get(i).getPic(), handler, imageView).start();
            Log.i("tag", "addFlipperViews: "+advs.get(i).getUrl());
            flipper.addView(imageView);
        }
        flipper.setInAnimation(this, R.anim.right_in);
		flipper.setOutAnimation(this, R.anim.right_out);
		flipper.setFlipInterval(2000);// 三秒自动切换
		flipper.setAutoStart(true);

    }


}

class HomeParser implements HttpJsonThread.JsonParser {

    @Override
    public Object parseJson(String jsonString) {
        HomeResult result = new HomeResult();

        try {
            JSONObject jsonObject = new JSONObject(jsonString);
            JSONObject resultOjb = jsonObject.getJSONObject("result");


            ArrayList<Advertisement> advs = new ArrayList<>();
            JSONArray advsArr = resultOjb.getJSONArray("advs");
            for (int i = 0; i < advsArr.length(); i++) {
                JSONObject obj = advsArr.getJSONObject(i);
                Advertisement adv = new Advertisement();
                adv.setPic(obj.getString("pic"));
                adv.setDescription(obj.getString("description"));
                adv.setUrl(obj.getString("url"));

                advs.add(adv);
            }
            result.setAdvs(advs);

            ArrayList<Navigate> navigates = new ArrayList<>();
            JSONArray navigatesArr = resultOjb.getJSONArray("navigates");
            for (int i = 0; i < navigatesArr.length(); i++) {
                JSONObject obj = navigatesArr.getJSONObject(i);
                Navigate navigate = new Navigate();
                navigate.setPic(obj.getString("pic"));
                navigate.setDescription(obj.getString("description"));
                navigate.setUrl(obj.getString("url"));

                navigates.add(navigate);
            }
            result.setNavigates(navigates);

            ArrayList<Topic> topics = new ArrayList<>();
            JSONArray topicsArr = resultOjb.getJSONArray("topics");
            for (int i = 0; i < topicsArr.length(); i++) {
                JSONObject obj = topicsArr.getJSONObject(i);
                Topic topic = new Topic();
                topic.setPic(obj.getString("pic"));
                topic.setTid(obj.getString("tid"));
                topic.setUrl(obj.getString("url"));
                topic.setTitle(obj.getString("title"));

                topics.add(topic);
            }
            result.setTopics(topics);

            ArrayList<Product> products = new ArrayList<>();
            JSONArray productsArr = resultOjb.getJSONArray("tagProducts");
            for (int i = 0; i < productsArr.length(); i++) {
                JSONObject obj = productsArr.getJSONObject(i);
                Product product = new Product();
                product.setTitle(obj.getString("title"));

                ArrayList<Goods> goodsList = new ArrayList<>();
                JSONArray goodsListArr = obj.getJSONArray("goodslist");
                for (int j = 0; j < goodsListArr.length(); j++) {
                    JSONObject goodsObj = goodsListArr.getJSONObject(j);
                    Goods goods = new Goods();
                    goods.setTitle(goodsObj.getString("title"));
                    goods.setPic(goodsObj.getString("pic"));
                    goods.setCreate_time(goodsObj.getString("create_time"));
                    goods.setIs_compress(goodsObj.getInt("is_compress"));
                    goods.setItem_id(goodsObj.getString("item_id"));
                    goods.setLink(goodsObj.getString("link"));
                    goods.setOriginal_price(goodsObj.getString("original_price"));
                    goods.setPrice(goodsObj.getString("price"));

                    goodsList.add(goods);
                }

                product.setGoodsList(goodsList);
                products.add(product);
            }
            result.setTagProducts(products);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return result;
    }
}
