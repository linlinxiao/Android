package com.linlin.productdetaildemo;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;

/**
 * Created by linlin on 9/13/16.
 */
public class ProductDetail extends BaseModel {
    private String ProductName;
    private JSONArray Stores;
    private int Freight;
    private String MetaDescription;
    private String ShortDescription;
    private String SaleCounts;
    private String ShowSaleCounts;
    private String Weight;
    private String VistiCounts;
    private String CostPrice;
    private String MarketPrice;
    private String IsfreeShipping;
    private String MaxSalePrice;
    private String MinSalePrice;
    private String IsFavorite;
    private List<String> ImageUrl;
    private String ActivityUrl;
    private SkuModel DefaultSku;
    private int Stock;
    private String OrderPromotionInfo;
    private List<SkuItemModel> SkuItem;
    private List<SkuModel> Skus;
    private List<ProductListItemModel> GuessYouLikeProducts;
    private int ReviewCount;
    private boolean HasStores;
    private boolean IsSupportPodrequest;
    private List<CouponModel> Coupons;//优惠券
    private int ConsultationCount;
    private String ProductSendGiftsInfo;
    private String ProductReduce;
    private int MobileExclusive;
    private int FightGroupActivityId;
    private boolean IsUnSale;

    public ProductDetail(JSONObject jsonObject){
        super(jsonObject);
    }

    public ProductDetail(){};

    public String getProductName() {
        return ProductName;
    }

    public void setProductName(String productName) {
        ProductName = productName;
    }

    public JSONArray getStores() {
        return Stores;
    }

    public void setStores(JSONArray stores) {
        Stores = stores;
    }

    public int getFreight() {
        return Freight;
    }

    public void setFreight(int freight) {
        Freight = freight;
    }

    public String getMetaDescription() {
        return MetaDescription;
    }

    public void setMetaDescription(String metaDescription) {
        MetaDescription = metaDescription;
    }

    public String getShortDescription() {
        return ShortDescription;
    }

    public void setShortDescription(String shortDescription) {
        ShortDescription = shortDescription;
    }

    public String getSaleCounts() {
        return SaleCounts;
    }

    public void setSaleCounts(String saleCounts) {
        SaleCounts = saleCounts;
    }

    public String getShowSaleCounts() {
        return ShowSaleCounts;
    }

    public void setShowSaleCounts(String showSaleCounts) {
        ShowSaleCounts = showSaleCounts;
    }

    public String getWeight() {
        return Weight;
    }

    public void setWeight(String weight) {
        Weight = weight;
    }

    public String getVistiCounts() {
        return VistiCounts;
    }

    public void setVistiCounts(String vistiCounts) {
        VistiCounts = vistiCounts;
    }

    public String getCostPrice() {
        return CostPrice;
    }

    public void setCostPrice(String costPrice) {
        CostPrice = costPrice;
    }

    public String getMarketPrice() {
        return MarketPrice;
    }

    public void setMarketPrice(String marketPrice) {
        MarketPrice = marketPrice;
    }

    public String getIsfreeShipping() {
        return IsfreeShipping;
    }

    public void setIsfreeShipping(String isfreeShipping) {
        IsfreeShipping = isfreeShipping;
    }

    public String getMaxSalePrice() {
        return MaxSalePrice;
    }

    public void setMaxSalePrice(String maxSalePrice) {
        MaxSalePrice = maxSalePrice;
    }

    public String getMinSalePrice() {
        return MinSalePrice;
    }

    public void setMinSalePrice(String minSalePrice) {
        MinSalePrice = minSalePrice;
    }

    public String getIsFavorite() {
        return IsFavorite;
    }

    public void setIsFavorite(String isFavorite) {
        IsFavorite = isFavorite;
    }

    public List<String> getImageUrl() {
        return ImageUrl;
    }

    public void setImageUrl(List<String> imageUrl) {
        ImageUrl = imageUrl;
    }

    public String getActivityUrl() {
        return ActivityUrl;
    }

    public void setActivityUrl(String activityUrl) {
        ActivityUrl = activityUrl;
    }

    public SkuModel getDefaultSku() {
        return DefaultSku;
    }

    public void setDefaultSku(SkuModel defaultSku) {
        DefaultSku = defaultSku;
    }

    public int getStock() {
        return Stock;
    }

    public void setStock(int stock) {
        Stock = stock;
    }

    public String getOrderPromotionInfo() {
        return OrderPromotionInfo;
    }

    public void setOrderPromotionInfo(String orderPromotionInfo) {
        OrderPromotionInfo = orderPromotionInfo;
    }

    public List<SkuItemModel> getSkuItem() {
        return SkuItem;
    }

    public void setSkuItem(List<SkuItemModel> skuItem) {
        SkuItem = skuItem;
    }

    public List<SkuModel> getSkus() {
        return Skus;
    }

    public void setSkus(List<SkuModel> skus) {
        Skus = skus;
    }

    public List<ProductListItemModel> getGuessYouLikeProducts() {
        return GuessYouLikeProducts;
    }

    public void setGuessYouLikeProducts(List<ProductListItemModel> guessYouLikeProducts) {
        GuessYouLikeProducts = guessYouLikeProducts;
    }

    public int getReviewCount() {
        return ReviewCount;
    }

    public void setReviewCount(int reviewCount) {
        ReviewCount = reviewCount;
    }

    public boolean isHasStores() {
        return HasStores;
    }

    public void setHasStores(boolean hasStores) {
        HasStores = hasStores;
    }

    public boolean isSupportPodrequest() {
        return IsSupportPodrequest;
    }

    public void setSupportPodrequest(boolean supportPodrequest) {
        IsSupportPodrequest = supportPodrequest;
    }

    public List<CouponModel> getCoupons() {
        return Coupons;
    }

    public void setCoupons(List<CouponModel> coupons) {
        Coupons = coupons;
    }

    public int getConsultationCount() {
        return ConsultationCount;
    }

    public void setConsultationCount(int consultationCount) {
        ConsultationCount = consultationCount;
    }

    public String getProductSendGiftsInfo() {
        return ProductSendGiftsInfo;
    }

    public void setProductSendGiftsInfo(String productSendGiftsInfo) {
        ProductSendGiftsInfo = productSendGiftsInfo;
    }

    public String getProductReduce() {
        return ProductReduce;
    }

    public void setProductReduce(String productReduce) {
        ProductReduce = productReduce;
    }

    public int getMobileExclusive() {
        return MobileExclusive;
    }

    public void setMobileExclusive(int mobileExclusive) {
        MobileExclusive = mobileExclusive;
    }

    public int getFightGroupActivityId() {
        return FightGroupActivityId;
    }

    public void setFightGroupActivityId(int fightGroupActivityId) {
        FightGroupActivityId = fightGroupActivityId;
    }

    public boolean isUnSale() {
        return IsUnSale;
    }

    public void setUnSale(boolean unSale) {
        IsUnSale = unSale;
    }
}
