package com.art.orion.model.entity;

import java.util.List;
import java.util.Map;

public class Shoes {
    private int shoesId;
    private ProductInfo productInfo;
    private String color;
    private String category;
    private Map<Integer, Integer> sizes;
    private List<String> additionalImgPaths;

    public Shoes() {
    }

    public Shoes(int shoesId, ProductInfo productInfo, String color, String category,
                 Map<Integer, Integer> sizes, List<String> additionalImgPaths) {
        this.shoesId = shoesId;
        this.productInfo = productInfo;
        this.color = color;
        this.category = category;
        this.sizes = sizes;
        this.additionalImgPaths = additionalImgPaths;
    }

    public int getShoesId() {
        return shoesId;
    }

    public void setShoesId(int shoesId) {
        this.shoesId = shoesId;
    }

    public ProductInfo getProductInfo() {
        return productInfo;
    }

    public void setProductInfo(ProductInfo productInfo) {
        this.productInfo = productInfo;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
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
}

