package model;

import java.util.ArrayList;

/**
 * Created by linlin on 8/18/16.
 */
public class Category {
    private int cid;
    private String name;
    private String icon;
    private String hasChildren;
    private String description;
    private ArrayList<Category> subs;

    static public String TRUE = "true";
    static public String FALSE = "false";


    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getHasChildren() {
        return hasChildren;
    }

    public void setHasChildren(String hasChildren) {
        this.hasChildren = hasChildren;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ArrayList<Category> getSubs() {
        return subs;
    }

    public void setSubs(ArrayList<Category> subs) {
        this.subs = subs;
    }
}
