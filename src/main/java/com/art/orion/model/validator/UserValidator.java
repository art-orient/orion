package com.art.orion.model.validator;

public class UserValidator {
    private static final String USERNAME_REGEX = "[\\w-]{3,30}";
    private static final String PASSWORD_REGEX = "[a-zA-ZА-я\\d\\p{Punct}]{5,40}";
    private static final String NAME_REGEX = "[a-zA-ZА-я-]{2,30}";

    private UserValidator() {
    }

    public static boolean isNotValidUsername(String username) {
        return username == null || username.isEmpty() || !username.matches(USERNAME_REGEX);
    }

    public static boolean isNotValidPassword(String password) {
        return password == null || password.isEmpty() || !password.matches(PASSWORD_REGEX);
    }

    public static boolean isNotEqualsPasswords(String password, String confirmPassword) {
        return !password.equals(confirmPassword);
    }

    public static boolean isNotValidName(String name) {
        return name == null || name.isEmpty() || !name.matches(NAME_REGEX);
    }

    public static boolean isNotValidEmail(String email) {
        return !EmailValidator.validate(email);
    }
}
