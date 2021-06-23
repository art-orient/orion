package com.art.orion.controller.command.impl;

import com.art.orion.controller.command.Command;
import com.art.orion.controller.command.util.PasswordEncryptor;
import com.art.orion.model.entity.User;
import com.art.orion.model.service.UserService;
import com.art.orion.util.ConfigManager;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static com.art.orion.util.Constant.USERNAME;
import static com.art.orion.util.Constant.PASSWORD;
import static com.art.orion.util.Constant.ROLE;
import static com.art.orion.util.Constant.ERROR;

public class LoginUserCommand implements Command {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public String execute(HttpServletRequest req) {
        String username = req.getParameter(USERNAME);
        String password = req.getParameter(PASSWORD);
        String encryptedPassword = PasswordEncryptor.encryptPassword(password);
        if (UserService.validateCredentials(username, encryptedPassword)) {
            User user = UserService.getUser(username);
            HttpSession session = req.getSession();
            session.setAttribute(USERNAME, username);
            session.setAttribute(ROLE, user.getRole().name());
            logger.log(Level.INFO, "User successfully logged in");
            return ConfigManager.getProperty("page.index");
        } else {
            if (username != null) {
                req.setAttribute(ERROR, "msg.invalidCredentials");
                logger.log(Level.WARN, "Invalid credentials");
            }
        }
        return ConfigManager.getProperty("page.login");
    }
}