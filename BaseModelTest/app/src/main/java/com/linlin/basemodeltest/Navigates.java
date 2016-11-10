package com.linlin.basemodeltest;

import org.json.JSONObject;

/**
 * Created by linlin on 9/28/16.
 */
public class Navigates extends BaseModel {
    private String pic;

    public Navigates(JSONObject jsonObject) {
        super(jsonObject);
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    private String description;
    private String url;

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }
}
