package com.art.orion.model.entity;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public class Shoes extends Product {
    private String color;
    private Map<Integer, Integer> sizes;
    private List<String> additionalImgPaths;

    public Shoes() {
    }

    public Shoes(long id, String brand, String modelName, String color, String description, String imgPath,
                    BigDecimal cost, boolean active) {
        this.id = id;
        this.brand = brand;
        this.modelName = modelName;
        this.description = description;
        this.imgPath = imgPath;
        this.cost = cost;
        this.active = active;
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
}

