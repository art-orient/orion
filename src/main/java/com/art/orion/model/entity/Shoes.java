package com.art.orion.model.entity;

import static com.art.orion.util.Constant.SHOES;

/**
 * Bean class of model layer represents the shoes as the product
 *
 * @author Aliaksandr Artsikhovich
 * @version 1.0
 */
public class Shoes {
    /**
     * The marker {@link String} of product category
     */
    private static final String CATEGORY = SHOES;
    /**
     * Unique identification {@code int} value for the shoes
     */
    private int shoesId;
    /**
     * The {@link String} type of the shoes in Russian
     */
    private String typeRu;
    /**
     * The {@link String} type of the clothing in English
     */
    private String typeEn;
    /**
     * The {@link ProductDetails} set of product details
     */
    private ProductDetails productDetails;
    /**
     * The {@link String} color of the clothing
     */
    private String color;

    /**
     * Constructs a new product
     */
    public Shoes() {
    }

    /**
     * Constructs a new product use parameters
     */
    public Shoes(String typeRu, String typeEn, ProductDetails productDetails, String color) {
        this.typeRu = typeRu;
        this.typeEn = typeEn;
        this.productDetails = productDetails;
        this.color = color;
    }

    /**
     * Constructs a new product use parameters
     */
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

