package com.linlin.basemodeltest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    private HomeModel homeModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String jsonString = "{\"Result\":{\"advs\":[{\"pic\":\"http://image58.kuaidiantong.cn/Storage/master/banner/20140611140216_3739.jpg\",\"description\":\"靓丽衣行\",\"url\":\"page:OrderList\"},{\"pic\":\"http://image58.kuaidiantong.cn/Storage/master/banner/20140611140211_0376.jpg\",\"description\":\"\",\"url\":\"javascript:;\"},{\"pic\":\"http://image58.kuaidiantong.cn/Storage/master/banner/20140611140200_3020.jpg\",\"description\":\"\",\"url\":\"javascript:;\"}],\"navigates\":[{\"description\":\"test5\",\"pic\":\"http://2.4.ysctest.kuaidiantong.cn/Storage/master/navigate/201609210926228387270.jpg\",\"url\":\"http://www.baidu.com\"},{\"description\":\"test4\",\"pic\":\"http://2.4.ysctest.kuaidiantong.cn/Storage/master/navigate/201609210926047291200.jpg\",\"url\":\"http://www.baidu.com\"},{\"description\":\"test3\",\"pic\":\"http://2.4.ysctest.kuaidiantong.cn/Storage/master/navigate/201609210925434632230.jpg\",\"url\":\"http://www.baidu.com\"},{\"description\":\"test2\",\"pic\":\"http://2.4.ysctest.kuaidiantong.cn/Storage/master/navigate/201609210925176347670.jpeg\",\"url\":\"http://www.baidu.com\"},{\"description\":\"test1\",\"pic\":\"http://2.4.ysctest.kuaidiantong.cn/Storage/master/navigate/201609210924199777790.jpg\",\"url\":\"http://www.baidu.com\"}],\"tagProducts\":[{\"pid\":\"170\",\"name\":\"欧洲站尖头粗跟真皮擦色短靴及裸靴 英伦风中跟马丁靴女靴子潮\",\"pic\":\"http://image58.kuaidiantong.cn/Storage/master/product/thumbs180/180_201602291440510708370.png\",\"price\":\"429.00\",\"saleCounts\":\"0\",\"url\":\"http://2.4.ysctest.kuaidiantong.cn/AppShop/ProductDetails.aspx?productId=170\"},{\"pid\":\"171\",\"name\":\"包邮冰心女孩正品个性孔雀图舒适真皮女鞋手工原创擦色短靴999\",\"pic\":\"http://image58.kuaidiantong.cn/Storage/master/product/thumbs180/180_201602291442287745870.png\",\"price\":\"399.00\",\"saleCounts\":\"0\",\"url\":\"http://2.4.ysctest.kuaidiantong.cn/AppShop/ProductDetails.aspx?productId=171\"},{\"pid\":\"153\",\"name\":\"Meizu/魅族 MX4 Pro版移动联通双4G手机\",\"pic\":\"http://image58.kuaidiantong.cn/Storage/master/product/thumbs180/180_ba3e13561c7949ee9de4b433ce183136.jpg\",\"price\":\"2568.00\",\"saleCounts\":\"0\",\"url\":\"http://2.4.ysctest.kuaidiantong.cn/AppShop/ProductDetails.aspx?productId=153\"},{\"pid\":\"153\",\"name\":\"Meizu/魅族 MX4 Pro版移动联通双4G手机\",\"pic\":\"http://image58.kuaidiantong.cn/Storage/master/product/thumbs180/180_ba3e13561c7949ee9de4b433ce183136.jpg\",\"price\":\"2568.00\",\"saleCounts\":\"0\",\"url\":\"http://2.4.ysctest.kuaidiantong.cn/AppShop/ProductDetails.aspx?productId=153\"}],\"IsOpenMeiQiaService\":false,\"IsSuportPhoneRegister\":true,\"IsSuportEmailRegister\":true,\"IsValidEmail\":false,\"RegisterExtendInfo\":\"\",\"HomeTopicVersion\":\"1055\",\"HomeTopicPath\":\"http://2.4.ysctest.kuaidiantong.cn/Templates/appshop/data/default.txt\"}}";
        try {
            JSONObject jsonObject = new JSONObject(jsonString);
            homeModel = new HomeModel(jsonObject.optJSONObject("Result"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
