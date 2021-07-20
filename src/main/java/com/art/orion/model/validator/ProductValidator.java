package com.art.orion.model.validator;

import java.math.BigDecimal;

public class ProductValidator {
    private static final String BRAND_REGEX = "[\\wА-я\\-+ ]{2,30}";
    private static final String MODEL_NAME_REGEX = "[\\wА-я\\- ()'/]{2,50}";

    private ProductValidator() {
    }

    public static boolean isBrandValid(String brand) {
        return brand != null && brand.matches(BRAND_REGEX) && brand.length() > 1 && brand.length() < 31;
    }

    public static boolean isModelNameValid(String modelName) {
        return modelName != null && modelName.matches(MODEL_NAME_REGEX)
                && modelName.length() > 1 && modelName.length() < 51;
    }

    public static boolean isCostValid(BigDecimal cost) {
        return cost != null && cost.compareTo(BigDecimal.ZERO) > 0;
    }
}
