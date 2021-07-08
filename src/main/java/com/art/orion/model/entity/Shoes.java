package com.art.orion.model.entity;

import java.util.List;
import java.util.Map;

import static com.art.orion.util.Constant.SHOES;

public class Shoes {
    private static final String CATEGORY = SHOES;
    private int shoesId;
    private String typeRu;
    private String typeEn;
    private ProductDetails productDetails;
    private String color;
    private Map<Integer, Integer> sizes;
    private List<String> additionalImgPaths;

    public Shoes() {
    }

    public Shoes(String typeRu, String typeEn, ProductDetails productDetails, String color) {
        this.typeRu = typeRu;
        this.typeEn = typeEn;
        this.productDetails = productDetails;
        this.color = color;
    }

    public Shoes(int shoesId,  String typeRu, String typeEn, ProductDetails productDetails,
                    String color) {
        this(typeRu, typeEn, productDetails, color);
        this.shoesId = shoesId;
    }

    public static String getCATEGORY() {
        return CATEGORY;
    }

    public int getShoesId() {
        return shoesId;
    }

    public void setShoesId(int shoesId) {
        this.shoesId = shoesId;
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

    public List<String> getAdditionalImgPaths() {
        return additionalImgPaths;
    }

    public void setAdditionalImgPaths(List<String> additionalImgPaths) {
        this.additionalImgPaths = additionalImgPaths;
    }

    public static String getCategory() {
        return CATEGORY;
    }

    @Override
    public int hashCode() {
        return shoesId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Shoes shoes = (Shoes) o;
        return shoesId == shoes.shoesId;
    }

    @Override
    public String toString() {
        return new StringBuilder("Shoes {shoesId = ").append(shoesId)
                .append(", typeRu = ").append(typeRu)
                .append(", typeEn = ").append(typeEn)
                .append(", productDetails = ").append(productDetails)
                .append(", color = ").append(color)
                .append('}').toString();
    }
}

