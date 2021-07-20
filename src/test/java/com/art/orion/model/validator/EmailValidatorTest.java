package com.art.orion.model.validator;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class EmailValidatorTest {

    @DataProvider(name = "validEmail")
    public static Object[][] validEmail() {
        return new Object[][] { { "admin@tut.by" }, { "natali@mail.ru" }, { "lesha@gmail.com" }, { "12345@22.by" }, { "com.art@orion.by" } };
    }

    @Test(dataProvider = "validEmail")
    public void isValidEmailPositiveTest(String email) {
        Assert.assertTrue(EmailValidator.validate(email));
    }

    @DataProvider(name = "invalidEmail")
    public static Object[][] invalidEmail() {
        return new Object[][] { { "admin@tut.55" }, { null }, { "ivan@us.u" }, { "point@no" }, { "^sign@mail.ry" } };
    }

    @Test(dataProvider = "invalidEmail")
    public void isValidEmailNegativeTest(String email) {
        Assert.assertFalse(EmailValidator.validate(email));
    }
}
