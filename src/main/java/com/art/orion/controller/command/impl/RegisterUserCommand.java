package com.art.orion.controller.command.impl;

import com.art.orion.controller.command.Command;
import com.art.orion.controller.command.util.PasswordEncryptor;
import com.art.orion.model.entity.Role;
import com.art.orion.model.entity.User;
import com.art.orion.model.service.UserService;
import com.art.orion.model.validator.UserValidator;
import com.art.orion.util.ConfigManager;
import com.art.orion.util.ErrorMessageManager;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

import static com.art.orion.util.Constant.*;

public class RegisterUserCommand implements Command {
    static Logger logger = LogManager.getLogger();

    @Override
    public String execute(HttpServletRequest req) {
        String username = req.getParameter(USERNAME);
        String password = req.getParameter(PASSWORD);
        String confirmPassword = req.getParameter(CONFIRM_PASSWORD);
        String firstname = req.getParameter(FIRSTNAME);
        String lastname = req.getParameter(LASTNAME);
        String email = req.getParameter(EMAIL);
        UserValidator validator = new UserValidator();
        String registrationStatus;
        StringBuilder validationStatus = new StringBuilder();
        if (validator.isValidUser(username, password, confirmPassword, firstname, lastname, email,
                validationStatus)) {
            User user = new User(username, firstname, lastname, email);
            String encryptedPassword = PasswordEncryptor.encryptPassword(password);
            user.setPassword(encryptedPassword);
            if (UserService.isFirstUser()) {
                user.setRole(Role.ADMIN);
            } else{
                user.setRole(Role.CUSTOMER);
            }
            user.setActive(true);
            if (UserService.registerUser(user)) {
                registrationStatus = ErrorMessageManager.getMessage("msg.registrationSuccess");
                logger.log(Level.INFO, "User registered successfully");
            } else {
                registrationStatus = ErrorMessageManager.getMessage("msg.invalidData");
                logger.log(Level.INFO, "Incorrect data entered, user was not registered");
            }
        } else {
            registrationStatus = validationStatus.toString();
        }
        req.getSession().setAttribute(REGISTRATION_STATUS, registrationStatus);
        return ConfigManager.getProperty("page.registration");
    }
}
