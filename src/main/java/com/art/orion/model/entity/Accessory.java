package com.art.orion.model.entity;

public class Accessory {
    private String typeRu;
    private String typeEn;
    private int accessoryId;
    private ProductDetails productDetails;
    private int availability;

    public Accessory() {
    }

    public Accessory(ProductDetails productDetails, String typeRu, String typeEn, int availability) {
        this.productDetails = productDetails;
        this.typeRu = typeRu;
        this.typeEn = typeEn;
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

    public String getTypeRu() {
        return typeRu;
    }

    public void setTypeRu(String typeRu) {
        this.typeRu = typeRu;
    }

    public String getTypeEn() {
        return typeEn;
    }

    public void setTypeEn(String typeEn) {
        this.typeEn = typeEn;
    }

    @Override
    public String toString() {
        return new StringBuilder("Accessory {accessoryId = ").append(accessoryId)
                .append(", typeRu = ").append(typeRu)
                .append(", typeEn = ").append(typeEn)
                .append(", productDetails = ").append(productDetails)
                .append(", availability = ").append(availability)
                .append('}').toString();
    }
}
