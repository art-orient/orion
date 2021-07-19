package com.art.orion.model.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmailValidator {

    private EmailValidator() {
    }

    private static final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[A-Z\\d._%+-]+@[A-Z\\d.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    public static boolean validate(String email) {
        boolean isValidEmail;
        if (email == null) {
            isValidEmail = false;
        } else {
            Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(email);
            isValidEmail = matcher.matches();
        }
        return isValidEmail;
    }
}

