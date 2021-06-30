package com.art.orion.model.validator;

import java.math.BigDecimal;

public class ProductValidator {
    private static final String BRAND_REGEX = "[A-Za-zА-я\\d_\\- ]{2,30}";
    private static final String MODEL_NAME_REGEX = "[A-Za-zА-я\\d_\\- ()']{2,50}";

    public static boolean isProductValid(String brand, String modelName, BigDecimal cost) {
        return isBrandValid(brand) && isModelNameValid(modelName) && isCostValid(cost);
    }

    private static boolean isBrandValid(String brand) {
        return brand.matches(BRAND_REGEX);
    }

    private static boolean isModelNameValid(String modelName) {
        return modelName.matches(MODEL_NAME_REGEX);
    }

    private static boolean isCostValid(BigDecimal cost) {
        return cost.compareTo(BigDecimal.ZERO) > 0;
    }
}
