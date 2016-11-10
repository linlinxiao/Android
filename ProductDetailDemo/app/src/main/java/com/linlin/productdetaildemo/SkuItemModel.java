package com.linlin.productdetaildemo;

import java.util.List;

/**
 * Created by linlin on 9/13/16.
 */
public class SkuItemModel {
    private String AttributeName;
    private String AttributeId;
    private List<AttributeModel> AttributeValue;

    public String getAttributeName() {
        return AttributeName;
    }

    public void setAttributeName(String attributeName) {
        AttributeName = attributeName;
    }

    public String getAttributeId() {
        return AttributeId;
    }

    public void setAttributeId(String attributeId) {
        AttributeId = attributeId;
    }

    public List<AttributeModel> getAttributeValue() {
        return AttributeValue;
    }

    public void setAttributeValue(List<AttributeModel> attributeValue) {
        AttributeValue = attributeValue;
    }
}
