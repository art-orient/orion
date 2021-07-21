package com.art.orion.model.entity;

import java.math.BigDecimal;
import java.util.List;

/**
 * Class of model layer represents the set of product details
 *
 * @author Aliaksandr Artsikhovich
 * @version 1.0
 */
public class ProductDetails {
    /**
     * Brand name {@code String} of the product
     */
    private String brand;
    /**
     * Model name {@code String} of the product
     */
    private String modelName;
    /**
     * Description {@link List} of {@link String} of the product in Russian
     */
    private List<String> descriptionRu;
    /**
     * Description {@link List} of {@link String} of the product in English
     */
    private List<String> descriptionEn;
    /**
     * Path {@code String} to the image of the product
     */
    private String imgPath;
    /**
     * The {@link BigDecimal} cost of the product
     */
    private BigDecimal cost;
    /**
     * The status of the product
     */
    private boolean active;

    /**
     * Explicit default constructor
     */
    public ProductDetails() {
    }

    /**
     * The constructor with parameters
     */
    public ProductDetails(String brand, String modelName, List<String> descriptionRu, List<String> descriptionEn,
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

    public List<String> getDescriptionRu() {
        return descriptionRu;
    }

    public void setDescriptionRu(List<String> descriptionRu) {
        this.descriptionRu = descriptionRu;
    }

    public List<String> getDescriptionEn() {
        return descriptionEn;
    }

    public void setDescriptionEn(List<String> descriptionEn) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductDetails that = (ProductDetails) o;
        return active == that.active && brand.equals(that.brand) && modelName.equals(that.modelName)
                && descriptionRu.equals(that.descriptionRu) && descriptionEn.equals(that.descriptionEn)
                && imgPath.equals(that.imgPath) && cost.equals(that.cost);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = (brand == null) ? 0 : brand.hashCode();
        result = prime * result + ((modelName == null) ? 0 : modelName.hashCode());
        result = prime * result + ((descriptionRu == null) ? 0 : descriptionRu.hashCode());
        result = prime * result + ((descriptionEn == null) ? 0 : descriptionEn.hashCode());
        result = prime * result + ((descriptionEn == null) ? 0 : descriptionEn.hashCode());
        result = prime * result + ((cost == null) ? 0 : cost.hashCode());
        result = prime * result + (active ? prime : 0);
        return result;
    }
}
