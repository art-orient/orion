package com.art.orion.model.entity;

import java.util.Map;

public class Clothing {
    private int clothingId;
    private ProductDetails productInfo;
    private String color;
    private Map<Integer, Integer> sizes;

    public Clothing() {
    }

    public Clothing(int clothingId, ProductDetails productInfo, String color, Map<Integer, Integer> sizes) {
        this.clothingId = clothingId;
        this.productInfo = productInfo;
        this.color = color;
        this.sizes = sizes;
    }

    public int getClothingId() {
        return clothingId;
    }

    public void setClothingId(int clothingId) {
        this.clothingId = clothingId;
    }

    public ProductDetails getProductInfo() {
        return productInfo;
    }

    public void setProductInfo(ProductDetails productInfo) {
        this.productInfo = productInfo;
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
}
