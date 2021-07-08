package com.art.orion.model.entity;

import java.util.List;
import java.util.Map;

public class Shoes {
    private int shoesId;
    private ProductDetails productDetails;
    private String color;
    private String category;
    private Map<Integer, Integer> sizes;
    private List<String> additionalImgPaths;

    public Shoes() {
    }

    public Shoes(int shoesId, ProductDetails productDetails, String color, String category,
                 Map<Integer, Integer> sizes, List<String> additionalImgPaths) {
        this.shoesId = shoesId;
        this.productDetails = productDetails;
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

    public ProductDetails getProductDetails() {
        return productDetails;
    }

    public void setProductInfo(ProductDetails productDetails) {
        this.productDetails = productDetails;
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

