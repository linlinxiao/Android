package com.linlin.productdetaildemo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by linlin on 9/13/16.
 */
public class JsonParser {
    static public ProductDetail parseJsonForResult(JSONObject result) {
        JSONObject resultObj = null;
        ProductDetail productDetail = new ProductDetail();

        try {
            resultObj = result.getJSONObject("Result");

            ProductDetail product = new ProductDetail(resultObj);

            productDetail.setStores(resultObj.getJSONArray("Stores"));
            productDetail.setFreight(resultObj.getInt("Freight"));
            productDetail.setProductName(resultObj.getString("ProductName"));
            productDetail.setMetaDescription(resultObj.getString("MetaDescription"));
            productDetail.setShortDescription(resultObj.getString("ShortDescription"));
            productDetail.setSaleCounts(resultObj.getString("SaleCounts"));
            productDetail.setShowSaleCounts(resultObj.getString("ShowSaleCounts"));
            productDetail.setWeight(resultObj.getString("Weight"));
            productDetail.setVistiCounts(resultObj.getString("VistiCounts"));
            productDetail.setCostPrice(resultObj.getString("CostPrice"));
            productDetail.setMarketPrice(resultObj.getString("MarketPrice"));
            productDetail.setIsfreeShipping(resultObj.getString("IsfreeShipping"));
            productDetail.setMaxSalePrice(resultObj.getString("MaxSalePrice"));
            productDetail.setMinSalePrice(resultObj.getString("MinSalePrice"));
            productDetail.setIsFavorite("IsFavorite");

            ArrayList<String> imageUrls = new ArrayList<>();
            addImageUrlToArray(resultObj.getString("ImageUrl1"), imageUrls);
            addImageUrlToArray(resultObj.getString("ImageUrl2"), imageUrls);
            addImageUrlToArray(resultObj.getString("ImageUrl3"), imageUrls);
            addImageUrlToArray(resultObj.getString("ImageUrl4"), imageUrls);
            addImageUrlToArray(resultObj.getString("ImageUrl5"), imageUrls);
            productDetail.setImageUrl(imageUrls);

            productDetail.setActivityUrl(resultObj.getString("ActivityUrl"));
            productDetail.setDefaultSku(generateSkuModel(resultObj.getJSONObject("DefaultSku")));
            productDetail.setStock(resultObj.getInt("Stock"));
            productDetail.setOrderPromotionInfo(resultObj.getString("OrderPromotionInfo"));

            JSONArray tempArr = resultObj.getJSONArray("SkuItem");
            ArrayList<SkuItemModel> skuItemModels = new ArrayList<SkuItemModel>();
            for (int i = 0; i < tempArr.length(); i++) {
                skuItemModels.add(generateSkuItemModel((JSONObject) tempArr.get(i)));
            }
            productDetail.setSkuItem(skuItemModels);


            JSONArray tempArr1 = resultObj.getJSONArray("Skus");
            ArrayList<SkuModel> skuModels = new ArrayList<SkuModel>();
            for (int j = 0; j < tempArr1.length(); j++) {
                skuModels.add(generateSkuModel((JSONObject) tempArr1.get(j)));
            }
            productDetail.setSkus(skuModels);

            JSONArray tempArr2 = resultObj.getJSONArray("GuessYouLikeProducts");
            ArrayList<ProductListItemModel> productListItemModels = new ArrayList<ProductListItemModel>();
            for (int j = 0; j < tempArr2.length(); j++) {
                productListItemModels.add(generateProductListItemModel((JSONObject) tempArr2.get(j)));
            }
            productDetail.setGuessYouLikeProducts(productListItemModels);

            productDetail.setReviewCount(resultObj.getInt("ReviewCount"));
            productDetail.setHasStores(resultObj.getBoolean("HasStores"));
            productDetail.setSupportPodrequest(resultObj.getBoolean("IsSupportPodrequest"));

            JSONArray tempArr3 = resultObj.getJSONArray("Coupons");
            ArrayList<CouponModel> couponModels = new ArrayList<>();
            for (int i = 0; i < tempArr3.length(); i++) {
                couponModels.add(generateCouponModel((JSONObject) tempArr3.get(i)));
            }
            productDetail.setCoupons(couponModels);

            productDetail.setConsultationCount(resultObj.getInt("ConsultationCount"));
            productDetail.setProductSendGiftsInfo(resultObj.getString("ProductSendGiftsInfo"));
            productDetail.setProductReduce(resultObj.getString("ProductReduce"));
            productDetail.setMobileExclusive(resultObj.getInt("MobileExclusive"));
            productDetail.setFightGroupActivityId(resultObj.getInt("FightGroupActivityId"));
            productDetail.setUnSale(resultObj.getBoolean("IsUnSale"));


        } catch (JSONException e) {
            e.printStackTrace();
        } finally {
            return productDetail;
        }


    }

    static private void addImageUrlToArray(String imageUrl, ArrayList<String> array){
        if (imageUrl.length() > 0){
            array.add(imageUrl);
        }
    }

    static private SkuModel generateSkuModel(JSONObject jsonObject) {
        if (jsonObject == null) return null;

        SkuModel skuModel = new SkuModel();
        try {
            skuModel.setSkuItems(jsonObject.getJSONObject("SkuItems"));
            skuModel.setMemberPrices(jsonObject.getJSONObject("MemberPrices"));
            skuModel.setSkuId(jsonObject.getString("SkuId"));
            skuModel.setProductId(jsonObject.getInt("ProductId"));
            skuModel.setSKU(jsonObject.getString("SKU"));
            skuModel.setWeight(jsonObject.getInt("Weight"));
            skuModel.setStock(jsonObject.getInt("Stock"));
            skuModel.setWarningStock(jsonObject.getInt("WarningStock"));
            skuModel.setCostPrice(jsonObject.getInt("CostPrice"));
            skuModel.setSalePrice(jsonObject.getInt("SalePrice"));
            skuModel.setStoreStock(jsonObject.getInt("StoreStock"));
            skuModel.setMaxStock(jsonObject.getInt("MaxStock"));
            skuModel.setFreezeStock(jsonObject.getInt("FreezeStock"));
        skuModel.setImageUrl(jsonObject.getString("ImageUrl"));
        skuModel.setThumbnailUrl40(jsonObject.getString("ThumbnailUrl40"));
        skuModel.setThumbnailUrl410(jsonObject.getString("ThumbnailUrl410"));

        } catch (JSONException e) {
            e.printStackTrace();
        } finally {
            return skuModel;

        }

    }

    static private SkuItemModel generateSkuItemModel(JSONObject jsonObject){
        if (jsonObject == null) return null;

        SkuItemModel skuItemModel = new SkuItemModel();
        try {
            skuItemModel.setAttributeId(jsonObject.getString("AttributeId"));
            skuItemModel.setAttributeName(jsonObject.getString("AttributeName"));

            JSONArray tempArr = jsonObject.getJSONArray("AttributeValue");
            ArrayList<AttributeModel> attributeModels = new ArrayList<AttributeModel>();
            for (int i = 0; i < tempArr.length(); i++) {
                attributeModels.add(generateAttributeModel((JSONObject) tempArr.get(i)));
            }
            skuItemModel.setAttributeValue(attributeModels);

        } catch (JSONException e) {
            e.printStackTrace();
        } finally {
            return skuItemModel;
        }

    }

    static private AttributeModel generateAttributeModel(JSONObject jsonObject) {
        if (jsonObject == null) return null;

        AttributeModel attributeModel = new AttributeModel();
        try {
            attributeModel.setImageUrl(jsonObject.getString("ImageUrl"));
            attributeModel.setUseAttributeImage(jsonObject.getString("UseAttributeImage"));
            attributeModel.setValue(jsonObject.getString("Value"));
            attributeModel.setValueId(jsonObject.getString("ValueId"));
        } catch (JSONException e) {
            e.printStackTrace();
        } finally {
            return attributeModel;
        }

    }

    static private ProductListItemModel generateProductListItemModel(JSONObject jsonObject) {
        if (jsonObject == null) return null;

        ProductListItemModel productListItem = new ProductListItemModel();
        try {
            productListItem.setProductId(jsonObject.getInt("ProductId"));
            productListItem.setProductName(jsonObject.getString("ProductName"));
            productListItem.setProductCode(jsonObject.getString("ProductCode"));
            productListItem.setSaleCounts(jsonObject.getInt("SaleCounts"));
            productListItem.setThumbnailUrl40(jsonObject.getString("ThumbnailUrl40"));
            productListItem.setThumbnailUrl60(jsonObject.getString("ThumbnailUrl60"));
            productListItem.setThumbnailUrl100(jsonObject.getString("ThumbnailUrl100"));
            productListItem.setThumbnailUrl160(jsonObject.getString("ThumbnailUrl160"));
            productListItem.setThumbnailUrl180(jsonObject.getString("ThumbnailUrl180"));
            productListItem.setSalePrice(jsonObject.getInt("SalePrice"));
            productListItem.setMarketPrice(jsonObject.getInt("MarketPrice"));
            productListItem.setShortDescription(jsonObject.getString("ShortDescription"));

        } catch (JSONException e) {
            e.printStackTrace();
        } finally {
            return productListItem;
        }
    }

    static private CouponModel generateCouponModel(JSONObject jsonObject) {
        if (jsonObject == null) return null;

        CouponModel couponModel = new CouponModel();
        try {
            couponModel.setCouponId(jsonObject.getInt("CouponId"));
            couponModel.setCouponName(jsonObject.getString("CouponName"));
            couponModel.setPrice(jsonObject.getInt("Price"));
            couponModel.setSendCount(jsonObject.getInt("SendCount"));
            couponModel.setUserLimitCount(jsonObject.getInt("UserLimitCount"));
            couponModel.setOrderUseLimit(jsonObject.getInt("OrderUseLimit"));
            couponModel.setStartTime(jsonObject.getString("StartTime"));
            couponModel.setClosingTime(jsonObject.getString("ClosingTime"));
            couponModel.setCanUseProducts(jsonObject.getString("CanUseProducts"));
            couponModel.setObtainWay(jsonObject.getInt("ObtainWay"));
            couponModel.setNeedPoint(jsonObject.getInt("NeedPoint"));
            couponModel.setUseWithGroup(jsonObject.getBoolean("UseWithGroup"));
            couponModel.setUseWithPanicBuying(jsonObject.getBoolean("UseWithPanicBuying"));
            couponModel.setUseWithFireGroup(jsonObject.getBoolean("UseWithFireGroup"));
            couponModel.setLimitText(jsonObject.getString("LimitText"));
            couponModel.setStartTimeText(jsonObject.getString("StartTimeText"));
            couponModel.setClosingTimeText(jsonObject.getString("ClosingTimeText"));
        } catch (JSONException e) {
            e.printStackTrace();
        } finally {
            return couponModel;
        }

    }

}


