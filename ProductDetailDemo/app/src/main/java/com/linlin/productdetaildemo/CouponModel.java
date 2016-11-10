package com.linlin.productdetaildemo;

import org.json.JSONObject;

/**
 * Created by linlin on 9/13/16.
 */
public class CouponModel extends BaseModel{
    private int CouponId;
    private String CouponName;
    private int Price;
    private int SendCount;
    private int UserLimitCount;
    private int OrderUseLimit;
    private String StartTime;
    private String ClosingTime;
    private String CanUseProducts;
    private int ObtainWay;
    private int NeedPoint;
    private boolean UseWithGroup;
    private boolean UseWithPanicBuying;
    private boolean UseWithFireGroup;
    private String LimitText;
    private String StartTimeText;
    private String ClosingTimeText;

    CouponModel(){}

    CouponModel(JSONObject jsonObject) {
        super(jsonObject);
    }

    public int getCouponId() {
        return CouponId;
    }

    public void setCouponId(int couponId) {
        CouponId = couponId;
    }

    public String getCouponName() {
        return CouponName;
    }

    public void setCouponName(String couponName) {
        CouponName = couponName;
    }

    public int getPrice() {
        return Price;
    }

    public void setPrice(int price) {
        Price = price;
    }

    public int getSendCount() {
        return SendCount;
    }

    public void setSendCount(int sendCount) {
        SendCount = sendCount;
    }

    public int getUserLimitCount() {
        return UserLimitCount;
    }

    public void setUserLimitCount(int userLimitCount) {
        UserLimitCount = userLimitCount;
    }

    public int getOrderUseLimit() {
        return OrderUseLimit;
    }

    public void setOrderUseLimit(int orderUseLimit) {
        OrderUseLimit = orderUseLimit;
    }

    public String getStartTime() {
        return StartTime;
    }

    public void setStartTime(String startTime) {
        StartTime = startTime;
    }

    public String getClosingTime() {
        return ClosingTime;
    }

    public void setClosingTime(String closingTime) {
        ClosingTime = closingTime;
    }

    public String getCanUseProducts() {
        return CanUseProducts;
    }

    public void setCanUseProducts(String canUseProducts) {
        CanUseProducts = canUseProducts;
    }

    public int getObtainWay() {
        return ObtainWay;
    }

    public void setObtainWay(int obtainWay) {
        ObtainWay = obtainWay;
    }

    public int getNeedPoint() {
        return NeedPoint;
    }

    public void setNeedPoint(int needPoint) {
        NeedPoint = needPoint;
    }

    public boolean isUseWithGroup() {
        return UseWithGroup;
    }

    public void setUseWithGroup(boolean useWithGroup) {
        UseWithGroup = useWithGroup;
    }

    public boolean isUseWithPanicBuying() {
        return UseWithPanicBuying;
    }

    public void setUseWithPanicBuying(boolean useWithPanicBuying) {
        UseWithPanicBuying = useWithPanicBuying;
    }

    public boolean isUseWithFireGroup() {
        return UseWithFireGroup;
    }

    public void setUseWithFireGroup(boolean useWithFireGroup) {
        UseWithFireGroup = useWithFireGroup;
    }

    public String getLimitText() {
        return LimitText;
    }

    public void setLimitText(String limitText) {
        LimitText = limitText;
    }

    public String getStartTimeText() {
        return StartTimeText;
    }

    public void setStartTimeText(String startTimeText) {
        StartTimeText = startTimeText;
    }

    public String getClosingTimeText() {
        return ClosingTimeText;
    }

    public void setClosingTimeText(String closingTimeText) {
        ClosingTimeText = closingTimeText;
    }
}
