package com.art.orion.controller.command.impl;

import com.art.orion.controller.command.Command;
import com.art.orion.model.entity.User;
import com.art.orion.model.service.UserService;
import com.art.orion.util.ConfigManager;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static com.art.orion.util.Constant.USER;
import static com.art.orion.util.Constant.USERNAME;
public class ProfileCommand implements Command {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public String execute(HttpServletRequest req) {
        String username = (String) req.getSession().getAttribute(USERNAME);
        User user = UserService.getUser(username);
        req.setAttribute(USER, user);
        logger.log(Level.INFO, "Go to profile page");
        return ConfigManager.getProperty("page.profile");
    }
}
