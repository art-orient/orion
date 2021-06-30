package com.art.orion.model.entity;

public class Accessory {
    private int accessoryId;
    private ProductDetails productDetails;
    private int availability;

    public Accessory() {
    }

    public Accessory(ProductDetails productDetails, int availability) {
        this.productDetails = productDetails;
        this.availability = availability;
    }

    public int getAccessoryId() {
        return accessoryId;
    }

    public void setAccessoryId(int accessoryId) {
        this.accessoryId = accessoryId;
    }

    public ProductDetails getProductDetails() {
        return productDetails;
    }

    public void setProductDetails(ProductDetails productDetails) {
        this.productDetails = productDetails;
    }

    public int getAvailability() {
        return availability;
    }

    public void setAvailability(int availability) {
        this.availability = availability;
    }

    @Override
    public String toString() {
        return new StringBuilder("Accessory {accessoryId = ").append(accessoryId)
                .append(", productDetails = ").append(productDetails)
                .append(", availability = ").append(availability)
                .append('}').toString();
    }
}
