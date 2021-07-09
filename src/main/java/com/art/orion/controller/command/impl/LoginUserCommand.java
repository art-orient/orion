package com.art.orion.controller.command.impl;

import com.art.orion.controller.command.Command;
import com.art.orion.controller.command.util.PasswordEncryptor;
import com.art.orion.model.entity.User;
import com.art.orion.model.service.ServiceException;
import com.art.orion.model.service.UserService;
import com.art.orion.util.ConfigManager;
import com.art.orion.util.ErrorMessageManager;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import static com.art.orion.util.Constant.LANGUAGE;
import static com.art.orion.util.Constant.USERNAME;
import static com.art.orion.util.Constant.PASSWORD;
import static com.art.orion.util.Constant.ROLE;
import static com.art.orion.util.Constant.ERROR;

public class LoginUserCommand implements Command {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public String execute(HttpServletRequest req) {
        String page = ConfigManager.getProperty("page.login");
        String username = req.getParameter(USERNAME);
        String password = req.getParameter(PASSWORD);
        String encryptedPassword = PasswordEncryptor.encryptPassword(password);
        try {
            if (UserService.validateCredentials(username, encryptedPassword)) {
                HttpSession session = req.getSession();
                String language = (String) session.getAttribute(LANGUAGE);
                req.getSession().invalidate();
                session = req.getSession();
                session.setAttribute(LANGUAGE, language);
                session.setAttribute(USERNAME, username);
                setUserRole(session, username);
                page = ConfigManager.getProperty("page.index");
            } else {
                if (username != null) {
                    req.setAttribute(ERROR, ErrorMessageManager.getMessage("msg.invalidCredentials"));
                    logger.log(Level.WARN, "Invalid credentials");
                }
            }
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e.getMessage(), e);
        }
        return page;
    }

    private void setUserRole (HttpSession session, String username) {
        try {
            User user = UserService.getUser(username);
            session.setAttribute(ROLE, user.getRole().name());
            logger.log(Level.INFO, "User successfully logged in");
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e.getMessage(), e);
        }
    }
}