package com.linlin.basemodeltest;

import org.json.JSONObject;

/**
 * Created by linlin on 9/28/16.
 */
public class TagProducts extends BaseModel {
    private String pid;
    private String name;
    private String pic;
    private String price;
    private String saleCounts;
    private String url;

    public TagProducts(JSONObject jsonObject) {
        super(jsonObject);
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getSaleCounts() {
        return saleCounts;
    }

    public void setSaleCounts(String saleCounts) {
        this.saleCounts = saleCounts;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
