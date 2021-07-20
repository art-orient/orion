package com.art.orion.model.validator;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class UserValidatorTest {

    @DataProvider(name = "validUsername")
    public static Object[][] validUsername() {
        return new Object[][] { { "admin" }, { "nata_li" }, { "me7" }, { "12345" }, { "art-orion" } };
    }

    @Test(dataProvider = "validUsername")
    public void isValidUsernamePositiveTest(String username) {
        Assert.assertFalse(UserValidator.isNotValidUsername(username));
    }

    @DataProvider(name = "invalidUsername")
    public static Object[][] invalidUsername() {
        return new Object[][] { { "5" }, { null }, { "#ggg$" }, { "tt+tt" }, { "русский" } };
    }

    @Test(dataProvider = "invalidUsername")
    public void isValidUsernameNegativeTest(String username) {
        Assert.assertTrue(UserValidator.isNotValidUsername(username));
    }

    @DataProvider(name = "validPassword")
    public static Object[][] validPassword() {
        return new Object[][] { { "admin" }, { "na,ta_li78" }, { "me7ERРУССКИЕ" }, { "12345" }, { "art-55@by" } };
    }

    @Test(dataProvider = "validPassword")
    public void isValidPasswordPositiveTest(String password) {
        Assert.assertFalse(UserValidator.isNotValidPassword(password));
    }

    @DataProvider(name = "invalidPassword")
    public static Object[][] invalidPassword() {
        return new Object[][] { { "55" }, { null }, { "may$" }, { "line two" }, { "два слова" } };
    }

    @Test(dataProvider = "invalidPassword")
    public void isValidPasswordNegativeTest(String password) {
        Assert.assertTrue(UserValidator.isNotValidPassword(password));
    }

    @DataProvider(name = "equalsPasswords")
    public static Object[][] equalsPasswords() {
        return new Object[][] { { "admin", "admin" }, { "qwerty", "qwerty" }, { "me7ERРУС", "me7ERРУС"},
                { "12345", "12345" }, { "art-55@by", "art-55@by" } };
    }

    @Test(dataProvider = "equalsPasswords")
    public void isEqualsPasswordsPositiveTest(String password, String confirmPassword) {
        Assert.assertFalse(UserValidator.isNotEqualsPasswords(password, confirmPassword));
    }

    @DataProvider(name = "notEqualsPasswords")
    public static Object[][] notEqualsPasswords() {
        return new Object[][] { { "admin", "ADMIN" }, { "qwerty", "qwert" }, { "me7ERРУС", "7ERРУС"},
                { "12345", "12245" }, { "art-55@by", "art-5@by" } };
    }

    @Test(dataProvider = "notEqualsPasswords")
    public void isNotEqualsdPasswordsNegativeTest(String password, String confirmPassword) {
        Assert.assertTrue(UserValidator.isNotEqualsPasswords(password, confirmPassword));
    }

    @DataProvider(name = "validName")
    public static Object[][] validName() {
        return new Object[][] { { "admin" }, { "Владимир" }, { "Иванов" }, { "Lopes-Novikova" }, { "ежик-в-тумане" } };
    }

    @Test(dataProvider = "validName")
    public void isValidNamePositiveTest(String name) {
        Assert.assertFalse(UserValidator.isNotValidName(name));
    }

    @DataProvider(name = "invalidName")
    public static Object[][] invalidName() {
        return new Object[][] { { "Ы" }, { null }, { "#Саша" }, { "Tanya+Vova" }, { "ежик в тумане" } };
    }

    @Test(dataProvider = "invalidName")
    public void isValidNameNegativeTest(String name) {
        Assert.assertTrue(UserValidator.isNotValidName(name));
    }

    @DataProvider(name = "validEmail")
    public static Object[][] validEmail() {
        return new Object[][] { { "admin@tut.by" }, { "natali@mail.ru" }, { "lesha@gmail.com" },
                { "12345@22.by" }, { "com.art@orion.by" } };
    }

    @Test(dataProvider = "validEmail")
    public void isValidEmailPositiveTest(String email) {
        Assert.assertFalse(UserValidator.isNotValidEmail(email));
    }

    @DataProvider(name = "invalidEmail")
    public static Object[][] invalidEmail() {
        return new Object[][] { { "admin@tut.55" }, { null }, { "ivan@us.u" }, { "point@no" }, { "^sign@mail.ry" } };
    }

    @Test(dataProvider = "invalidEmail")
    public void isValidEmailNegativeTest(String email) {
        Assert.assertTrue(UserValidator.isNotValidEmail(email));
    }
}
