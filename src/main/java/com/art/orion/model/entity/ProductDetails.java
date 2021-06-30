package com.art.orion.model.entity;

import java.math.BigDecimal;

public class ProductDetails {
    private String brand;
    private String modelName;
    private String descriptionRu;
    private String descriptionEn;
    private String imgPath;
    private BigDecimal cost;
    private boolean active;

    public ProductDetails() {
    }

    public ProductDetails(String brand, String modelName, String descriptionRu, String descriptionEn,
                          String imgPath, BigDecimal cost, boolean active) {
        this.brand = brand;
        this.modelName = modelName;
        this.descriptionRu = descriptionRu;
        this.descriptionEn = descriptionEn;
        this.imgPath = imgPath;
        this.cost = cost;
        this.active = active;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public String getDescriptionRu() {
        return descriptionRu;
    }

    public void setDescriptionRu(String descriptionRu) {
        this.descriptionRu = descriptionRu;
    }

    public String getDescriptionEn() {
        return descriptionEn;
    }

    public void setDescriptionEn(String descriptionEn) {
        this.descriptionEn = descriptionEn;
    }

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public String toString() {
        return new StringBuilder("ProductDetails {brand = ").append(brand)
                .append(", modelName = ").append(modelName)
                .append(", descriptionRu = ").append(descriptionRu)
                .append(", descriptionEn = ").append(descriptionEn)
                .append(", imgPath = ").append(imgPath)
                .append(", cost = ").append(cost)
                .append(", active=").append(active)
                .append('}').toString();
    }
}
