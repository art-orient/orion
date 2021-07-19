package com.art.orion.controller.command.impl;

import com.art.orion.controller.command.Command;
import com.art.orion.model.entity.User;
import com.art.orion.exception.ServiceException;
import com.art.orion.model.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Optional;

import static com.art.orion.controller.command.PagePath.PROFILE_PAGE;
import static com.art.orion.util.Constant.USER;
import static com.art.orion.util.Constant.USERNAME;

/**
 * The command is responsible for displaying information about the user
 *
 * @author Aliaksandr Artsikhovich
 * @see Command
 */
public class ProfileCommand implements Command {
    private static final Logger logger = LogManager.getLogger();
    private final UserService userService;

    public ProfileCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String execute(HttpServletRequest req) {
        String username = (String) req.getSession().getAttribute(USERNAME);
        try {
            Optional<User> optionalUser = userService.findUserByUsername(username);
            if (optionalUser.isPresent()) {
                User user = optionalUser.get();
                req.setAttribute(USER, user);
            }
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e.getMessage(), e);
        }
        logger.log(Level.INFO, "Go to profile page");
        return PROFILE_PAGE;
    }
}
