package com.art.orion.model.validator;

import com.art.orion.model.service.ServiceException;
import com.art.orion.model.service.UserService;
import com.art.orion.util.ErrorMessageManager;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class UserValidator {
    private static final Logger logger = LogManager.getLogger();
    private static final String USERNAME_REGEX = "[a-zA-Z\\d_\\-.]{3,30}";
    private static final String PASSWORD_REGEX = "[a-zA-ZА-я\\d\\p{Punct}]{5,40}";
    private static final String NAME_REGEX = "[a-zA-ZА-я-]{2,30}";

    public boolean isValidUser (String username, String password, String confirmPassword, String firstname,
                                String lastname, String email, StringBuilder validationStatus) {
        boolean isValidUser = true;
        try {
            if (UserService.checkIsUsernameBusy(username)) {
                validationStatus.append(ErrorMessageManager.getMessage("msg.nameExists")).append("\n");
                isValidUser = false;
            }
        } catch (ServiceException e) {
            validationStatus.append(e.getMessage());
            logger.log(Level.ERROR, e.getMessage(), e);
        }
        if (isNotValidUsername(username)) {
            validationStatus.append(ErrorMessageManager.getMessage("msg.notValidUsername")).append("\n");
            isValidUser = false;
        }
        if (isNotValidPassword(password)) {
            validationStatus.append(ErrorMessageManager.getMessage("msg.notValidPassword")).append("\n");
            isValidUser = false;
        }
        if (isNotEqualsPasswords(password, confirmPassword)) {
            validationStatus.append(ErrorMessageManager.getMessage("msg.notConfirmPassword")).append("\n");
            isValidUser = false;
        }
        if (isNotValidName(firstname)) {
            validationStatus.append(ErrorMessageManager.getMessage("msg.notValidFirstname")).append("\n");
            isValidUser = false;
        }
        if (isNotValidName(lastname)) {
            validationStatus.append(ErrorMessageManager.getMessage("msg.notValidLastname")).append("\n");
            isValidUser = false;
        }
        if (isNotValidEmail(email)) {
            validationStatus.append(ErrorMessageManager.getMessage("msg.notValidEmail"));
            isValidUser = false;
        }
        return isValidUser;
    }

    private boolean isNotValidUsername(String username) {
        boolean isNotValidUsername;
        if (username==null || username.isEmpty()) {
            isNotValidUsername = true;
        } else {
            isNotValidUsername = !username.matches(USERNAME_REGEX);
        }
        return isNotValidUsername;
    }

    private boolean isNotValidPassword(String password) {
        boolean isNotValidPassword;
        if (password==null || password.isEmpty()) {
            isNotValidPassword = true;
        } else {
            isNotValidPassword = !password.matches(PASSWORD_REGEX);
        }
        return isNotValidPassword;
    }

    private boolean isNotEqualsPasswords(String password, String confirmPassword) {
        return !password.equals(confirmPassword);
    }

    private boolean isNotValidName(String name) {
        return !name.matches(NAME_REGEX);
    }

    private boolean isNotValidEmail(String email) {
        return !EmailValidator.validate(email);
    }
}
