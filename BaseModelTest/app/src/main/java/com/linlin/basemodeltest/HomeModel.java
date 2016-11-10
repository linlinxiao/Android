package com.linlin.basemodeltest;

import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by linlin on 9/28/16.
 */
public class HomeModel extends BaseModel {
    private ArrayList<Advs> advs;
    private ArrayList<Navigates> navigates;
    private ArrayList<TagProducts> tagProducts;
    private boolean IsOpenMeiQiaService;
    private boolean IsSuportPhoneRegister;
    private boolean IsSuportEmailRegister;
    private boolean IsValidEmail;
    private String HomeTopicVersion;
    private String HomeTopicPath;

    public HomeModel(JSONObject jsonObject) {
        super(jsonObject);
    }

    public ArrayList<Advs> getAdvs() {
        return advs;
    }

    public void setAdvs(ArrayList<Advs> advs) {
        this.advs = advs;
    }

    public ArrayList<Navigates> getNavigates() {
        return navigates;
    }

    public void setNavigates(ArrayList<Navigates> navigates) {
        this.navigates = navigates;
    }

    public ArrayList<TagProducts> getTagProducts() {
        return tagProducts;
    }

    public void setTagProducts(ArrayList<TagProducts> tagProducts) {
        this.tagProducts = tagProducts;
    }

    public boolean isOpenMeiQiaService() {
        return IsOpenMeiQiaService;
    }

    public void setOpenMeiQiaService(boolean openMeiQiaService) {
        IsOpenMeiQiaService = openMeiQiaService;
    }

    public boolean isSuportPhoneRegister() {
        return IsSuportPhoneRegister;
    }

    public void setSuportPhoneRegister(boolean suportPhoneRegister) {
        IsSuportPhoneRegister = suportPhoneRegister;
    }

    public boolean isSuportEmailRegister() {
        return IsSuportEmailRegister;
    }

    public void setSuportEmailRegister(boolean suportEmailRegister) {
        IsSuportEmailRegister = suportEmailRegister;
    }

    public boolean isValidEmail() {
        return IsValidEmail;
    }

    public void setValidEmail(boolean validEmail) {
        IsValidEmail = validEmail;
    }

    public String getHomeTopicVersion() {
        return HomeTopicVersion;
    }

    public void setHomeTopicVersion(String homeTopicVersion) {
        HomeTopicVersion = homeTopicVersion;
    }

    public String getHomeTopicPath() {
        return HomeTopicPath;
    }

    public void setHomeTopicPath(String homeTopicPath) {
        HomeTopicPath = homeTopicPath;
    }
}
