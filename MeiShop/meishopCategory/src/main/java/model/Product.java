package model;

import java.util.List;

/**
 * Created by linlin on 8/23/16.
 */
public class Product {
    private String title;
    private List<Goods> goodsList;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Goods> getGoodsList() {
        return goodsList;
    }

    public void setGoodsList(List<Goods> goodsList) {
        this.goodsList = goodsList;
    }
}
