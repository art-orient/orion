package com.art.orion.controller.command.util;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class PasswordEncryptorTest {

    @DataProvider(name = "encryptPassword")
    public Object[][] createEncryptPositiveData() {
        return new Object[][]{
                {"kostia", "3d2a96334b8c0ae558ef2a5f2bec61bd8aa786b7b980401dc6a40b41731ad75c"},
                {"pavel", "b4b6e5deeec1253972cd0ec230e2951c5b2518c19cf9aa4198ee8731fee58795"},
                {"vova1", "e889cf90c39f0447c4c4ce6b8623c437f861a84bad8096bd5332065bd300895c"},
                {"fedor", "30318e793178c2e1ae3210ade3963da999641e1bf64189b51477f962d8fb9aee"},
                {"artem", "ffb622df7e9926a3b77284dcb024a2ca667e1c474dd644321e90a16330107519"},
        };
    }

    @Test(dataProvider = "encryptPassword")
    public void encryptPasswordPositiveTest(String password, String expected) {
        String actual = PasswordEncryptor.encryptPassword(password);
        Assert.assertEquals(actual, expected);
    }

    @DataProvider(name = "badEncryptPassword")
    public Object[][] createEncryptNegativeData() {
        return new Object[][]{
                {"1234567A", "3c4c0aec843b3d5164285a367f92794eee300bafdd1ee56407bd680f0898bb44"},
                {"", "ffb622df7e9926a3b77284dcb024a2ca667e1c474dd644321e90a16330107519"},
                {"1234", "b1b3773a05c0ed0176787a4f1574ff0075f7521ee"},
                {"qwerty", "qwerty"},
                {"password", "e889cf90c39f0447c4c4ce6b8623c437f861a84bad8096bd5332065bd300895c"},
        };
    }

    @Test(dataProvider = "badEncryptPassword")
    public void encryptNegativeTest(String password, String expected) {
        String actual = PasswordEncryptor.encryptPassword(password);
        Assert.assertNotEquals(actual, expected);
    }
}
