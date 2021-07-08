package com.art.orion.model.entity;

import java.util.Map;

import static com.art.orion.util.Constant.CLOTHING;

public class Clothing {
    private static final String CATEGORY = CLOTHING;
    private int clothingId;
    private String typeRu;
    private String typeEn;
    private ProductDetails productDetails;
    private String color;
    private Map<Integer, Integer> sizes;

    public Clothing() {
    }

    public Clothing(String typeRu, String typeEn, ProductDetails productDetails, String color) {
        this.typeRu = typeRu;
        this.typeEn = typeEn;
        this.productDetails = productDetails;
        this.color = color;
    }

    public Clothing(int clothingId,  String typeRu, String typeEn, ProductDetails productDetails,
                     String color) {
        this(typeRu, typeEn, productDetails, color);
        this.clothingId = clothingId;
    }

    public int getClothingId() {
        return clothingId;
    }

    public void setClothingId(int clothingId) {
        this.clothingId = clothingId;
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

    public ProductDetails getProductDetails() {
        return productDetails;
    }

    public void setProductDetails(ProductDetails productDetails) {
        this.productDetails = productDetails;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Map<Integer, Integer> getSizes() {
        return sizes;
    }

    public void setSizes(Map<Integer, Integer> sizes) {
        this.sizes = sizes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Clothing clothing = (Clothing) o;
        return clothingId == clothing.clothingId;
    }

    public static String getCategory() {
        return CATEGORY;
    }

    @Override
    public int hashCode() {
        return clothingId;
    }

    @Override
    public String toString() {
        return new StringBuilder("Clothing {clothingId = ").append(clothingId)
                .append(", typeRu = ").append(typeRu)
                .append(", typeEn = ").append(typeEn)
                .append(", productDetails = ").append(productDetails)
                .append(", color = ").append(color)
                .append('}').toString();
    }
}
