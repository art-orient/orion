package com.art.orion.model.validator;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.math.BigDecimal;

public class ProductValidatorTest {

    @DataProvider(name = "validBrand")
    public static Object[][] validBrand() {
        return new Object[][] { { "Asics" }, { "Mizuno" }, { "-Hoka_" }, { "12345" }, { "KV+" } };
    }

    @Test(dataProvider = "validBrand")
    public void isValidBrandPositiveTest(String brand) {
        Assert.assertTrue(ProductValidator.isBrandValid(brand));
    }

    @DataProvider(name = "invalidBrand")
    public static Object[][] invalidBrand() {
        return new Object[][] { { "c" }, { null }, { "#ggg$" }, { "<tttt>" }, { "@sign" } };
    }

    @Test(dataProvider = "invalidBrand")
    public void isValidBrandNegativeTest(String brand) {
        Assert.assertFalse(ProductValidator.isBrandValid(brand));
    }

    @DataProvider(name = "validModelName")
    public static Object[][] validModelName() {
        return new Object[][] { { "Sulawesi SG-C10" }, { "Nautical Blu" }, { "DTS3 M BUGrip GTX Carbon/Black" },
                { "Dare2b Cogent" }, { "Mizuno Running Backpack 10L" } };
    }

    @Test(dataProvider = "validModelName")
    public void isValidModelNamePositiveTest(String modelName) {
        Assert.assertTrue(ProductValidator.isModelNameValid(modelName));
    }

    @DataProvider(name = "invalidModelName")
    public static Object[][] invalidModelName() {
        return new Object[][] { { "com.art.orion" }, { null }, { "ABC+DFE" }, { "<tttt>" }, { "@sign" } };
    }

    @Test(dataProvider = "invalidModelName")
    public void isValidModelNameNegativeTest(String modelName) {
        Assert.assertFalse(ProductValidator.isModelNameValid(modelName));
    }

    @DataProvider(name = "validCost")
    public static Object[][] validCost() {
        return new Object[][] { { new BigDecimal (1) }, { new BigDecimal (1000) }, { new BigDecimal ("10.00")},
                { new BigDecimal (324) }, { new BigDecimal ("55.55") } };
    }

    @Test(dataProvider = "validCost")
    public void isValidCostPositiveTest(BigDecimal cost) {
        Assert.assertTrue(ProductValidator.isCostValid(cost));
    }

    @DataProvider(name = "invalidCost")
    public static Object[][] invalidCost() {
        return new Object[][] { { new BigDecimal (0) }, { null }, {BigDecimal.valueOf(-10.00)},
                { new BigDecimal (-324) }, {BigDecimal.valueOf(-555.55)} };
    }

    @Test(dataProvider = "invalidCost")
    public void isValidCostNegativeTest(BigDecimal cost) {
        Assert.assertFalse(ProductValidator.isCostValid(cost));
    }
}
