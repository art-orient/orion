package com.art.orion.model.entity;

import static com.art.orion.util.Constant.ACCESSORIES;

public class Accessory {
    private static final String CATEGORY = ACCESSORIES;
    private int accessoryId;
    private String typeRu;
    private String typeEn;
    private ProductDetails productDetails;
    private int availability;

    public Accessory() {
    }

    public Accessory(String typeRu, String typeEn, ProductDetails productDetails,
                     int availability) {
        this.typeRu = typeRu;
        this.typeEn = typeEn;
        this.productDetails = productDetails;
        this.availability = availability;
    }

    public Accessory(int accessoryId,  String typeRu, String typeEn, ProductDetails productDetails,
                     int availability) {
        this(typeRu, typeEn, productDetails, availability);
        this.accessoryId = accessoryId;
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

    public static String getCategory() {
        return CATEGORY;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Accessory accessory = (Accessory) o;
        return accessoryId == accessory.accessoryId;
    }

    @Override
    public int hashCode() {
        return accessoryId;
    }
}
