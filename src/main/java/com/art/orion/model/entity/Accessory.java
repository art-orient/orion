package com.art.orion.model.entity;

public class Accessory {
    private int accessoryId;
    private ProductInfo productInfo;
    private int availability;

    public Accessory() {
    }

    public Accessory(int accessoryId, ProductInfo productInfo, int availability) {
        this.accessoryId = accessoryId;
        this.productInfo = productInfo;
        this.availability = availability;
    }

    public int getAccessoryId() {
        return accessoryId;
    }

    public void setAccessoryId(int accessoryId) {
        this.accessoryId = accessoryId;
    }

    public ProductInfo getProductInfo() {
        return productInfo;
    }

    public void setProductInfo(ProductInfo productInfo) {
        this.productInfo = productInfo;
    }

    public int getAvailability() {
        return availability;
    }

    public void setAvailability(int availability) {
        this.availability = availability;
    }
}
