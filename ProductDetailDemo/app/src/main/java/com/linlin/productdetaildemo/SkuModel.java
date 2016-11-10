package com.linlin.productdetaildemo;

import org.json.JSONObject;

/**
 * Created by linlin on 9/13/16.
 */
public class SkuModel {
    private JSONObject SkuItems;
    private JSONObject MemberPrices;
    private String SkuId;
    private int ProductId;
    private String SKU;
    private int Weight;
    private int Stock;
    private int WarningStock;
    private int CostPrice;
    private int SalePrice;
    private int StoreStock;
    private String ImageUrl;
    private String ThumbnailUrl40;
    private String ThumbnailUrl410;
    private int MaxStock;
    private int FreezeStock;


    public JSONObject getSkuItems() {
        return SkuItems;
    }

    public void setSkuItems(JSONObject skuItems) {
        SkuItems = skuItems;
    }

    public JSONObject getMemberPrices() {
        return MemberPrices;
    }

    public void setMemberPrices(JSONObject memberPrices) {
        MemberPrices = memberPrices;
    }

    public String getSkuId() {
        return SkuId;
    }

    public void setSkuId(String skuId) {
        SkuId = skuId;
    }

    public int getProductId() {
        return ProductId;
    }

    public void setProductId(int productId) {
        ProductId = productId;
    }

    public String getSKU() {
        return SKU;
    }

    public void setSKU(String SKU) {
        this.SKU = SKU;
    }

    public int getWeight() {
        return Weight;
    }

    public void setWeight(int weight) {
        Weight = weight;
    }

    public int getStock() {
        return Stock;
    }

    public void setStock(int stock) {
        Stock = stock;
    }

    public int getWarningStock() {
        return WarningStock;
    }

    public void setWarningStock(int warningStock) {
        WarningStock = warningStock;
    }

    public int getCostPrice() {
        return CostPrice;
    }

    public void setCostPrice(int costPrice) {
        CostPrice = costPrice;
    }

    public int getSalePrice() {
        return SalePrice;
    }

    public void setSalePrice(int salePrice) {
        SalePrice = salePrice;
    }

    public int getStoreStock() {
        return StoreStock;
    }

    public void setStoreStock(int storeStock) {
        StoreStock = storeStock;
    }

    public String getImageUrl() {
        return ImageUrl;
    }

    public void setImageUrl(String imageUrl) {
        ImageUrl = imageUrl;
    }

    public String getThumbnailUrl40() {
        return ThumbnailUrl40;
    }

    public void setThumbnailUrl40(String thumbnailUrl40) {
        ThumbnailUrl40 = thumbnailUrl40;
    }

    public String getThumbnailUrl410() {
        return ThumbnailUrl410;
    }

    public void setThumbnailUrl410(String thumbnailUrl410) {
        ThumbnailUrl410 = thumbnailUrl410;
    }

    public int getMaxStock() {
        return MaxStock;
    }

    public void setMaxStock(int maxStock) {
        MaxStock = maxStock;
    }

    public int getFreezeStock() {
        return FreezeStock;
    }

    public void setFreezeStock(int freezeStock) {
        FreezeStock = freezeStock;
    }
}
