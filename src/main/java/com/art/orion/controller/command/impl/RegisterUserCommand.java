package com.art.orion.controller.command.impl;

import com.art.orion.controller.command.Command;
import com.art.orion.controller.command.util.PasswordEncryptor;
import com.art.orion.model.entity.Role;
import com.art.orion.model.entity.User;
import com.art.orion.exception.ServiceException;
import com.art.orion.model.service.UserService;
import com.art.orion.model.validator.UserValidator;
import com.art.orion.util.ErrorMessageManager;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import jakarta.servlet.http.HttpServletRequest;

import static com.art.orion.controller.command.PagePath.CHECK_REG_STATUS_PAGE;
import static com.art.orion.util.Constant.USERNAME;
import static com.art.orion.util.Constant.PASSWORD;
import static com.art.orion.util.Constant.CONFIRM_PASSWORD;
import static com.art.orion.util.Constant.FIRSTNAME;
import static com.art.orion.util.Constant.LASTNAME;
import static com.art.orion.util.Constant.EMAIL;
import static com.art.orion.util.Constant.REGISTRATION_STATUS;

/**
 * The command is responsible for registering a new user
 *
 * @author Aliaksandr Artsikhovich
 * @see Command
 */
public class RegisterUserCommand implements Command {
    private static final Logger logger = LogManager.getLogger();
    private final UserService userService;

    public RegisterUserCommand(UserService userService) {
        this.userService = userService;
    }

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
        boolean isUsernameBusy = false;
        try {
            isUsernameBusy = userService.checkIsUsernameBusy(username, validationStatus);
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e);
        }
        if (validator.isValidUser(username, password, confirmPassword, firstname, lastname, email, validationStatus)
            && !isUsernameBusy) {
            User user = new User(username, firstname, lastname, email);
            String encryptedPassword = PasswordEncryptor.encryptPassword(password);
            user.setPassword(encryptedPassword);
            setRoleForClient(user);
            user.setActive(true);
            try {
                if (userService.registerUser(user)) {
                    registrationStatus = ErrorMessageManager.getMessage("msg.registrationSuccess");
                    logger.log(Level.INFO, "User registered successfully");
                } else {
                    registrationStatus = ErrorMessageManager.getMessage("msg.invalidData");
                    logger.log(Level.INFO, "Incorrect data entered, user was not registered");
                }
            } catch (ServiceException e) {
                logger.log(Level.ERROR, e.getMessage(), e);
                registrationStatus = ErrorMessageManager.getMessage("msg.registrationError");
            }
        } else {
            registrationStatus = validationStatus.toString();
        }
        req.setAttribute(REGISTRATION_STATUS, registrationStatus);
        return CHECK_REG_STATUS_PAGE;
    }

    private void setRoleForClient(User user) {
        user.setRole(Role.CUSTOMER);
        try {
            if (userService.isFirstUser()) {
                user.setRole(Role.ADMIN);
            }
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e.getMessage(), e);
        }
    }
}
