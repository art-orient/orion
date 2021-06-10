package com.art.orion.model.entity;

import java.math.BigDecimal;

public class Accessory extends Product {
    private int availability;

    public Accessory() {
    }

    public Accessory(long id, String brand, String modelName, String description, String imgPath,
                     BigDecimal cost, int availability, boolean active) {
        this.id = id;
        this.brand = brand;
        this.modelName = modelName;
        this.description = description;
        this.imgPath = imgPath;
        this.cost = cost;
        this.availability = availability;
        this.active = active;
    }

    public int getAvailability() {
        return availability;
    }

    public void setAvailability(int availability) {
        this.availability = availability;
    }
}
