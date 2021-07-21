package com.art.orion.model.entity;

import static com.art.orion.util.Constant.CLOTHING;

/**
 * Bean class of model layer represents the clothing as the product
 *
 * @author Aliaksandr Artsikhovich
 * @version 1.0
 */
public class Clothing {
    /**
     * The marker {@link String} of product category
     */
    private static final String CATEGORY = CLOTHING;
    /**
     * Unique identification {@code int} value for the clothing
     */
    private int clothingId;
    /**
     * The {@link String} type of the clothing in Russian
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
    public Clothing() {
    }

    /**
     * Constructs a new product use parameters
     */
    public Clothing(String typeRu, String typeEn, ProductDetails productDetails, String color) {
        this.typeRu = typeRu;
        this.typeEn = typeEn;
        this.productDetails = productDetails;
        this.color = color;
    }

    /**
     * Constructs a new product use parameters
     */
    public Clothing(int clothingId,  String typeRu, String typeEn, ProductDetails productDetails,
                     String color) {
        this(typeRu, typeEn, productDetails, color);
        this.clothingId = clothingId;
    }

    public int getClothingId() {
        return clothingId;
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Clothing clothing = (Clothing) o;
        return clothingId == clothing.clothingId;
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
