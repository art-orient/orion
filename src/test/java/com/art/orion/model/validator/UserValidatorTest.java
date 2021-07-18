package com.art.orion.model.validator;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class UserValidatorTest {
    UserValidator userValidator = new UserValidator();

    @DataProvider(name = "validUsername")
    public static Object[][] validUsername() {
        return new Object[][] { { "admin" }, { "natali" }, { "lesha" }, { "12345" }, { "com.art.orion" } };
    }

    @Test(dataProvider = "validUsername")
    public void isValidUserPositiveUsernameTest(String username) {
        Assert.assertFalse(userValidator.isNotValidUsername(username));
    }

    @DataProvider(name = "invalidUsername")
    public static Object[][] invalidUsername() {
        return new Object[][] { { "5" }, { null }, { "#ggg$" }, { "tt+tt" }, { "@sign" } };
    }

    @Test(dataProvider = "invalidUsername")
    public void isValidLoginNegativeTest(String username) {
        Assert.assertTrue(userValidator.isNotValidUsername(username));
    }
}
