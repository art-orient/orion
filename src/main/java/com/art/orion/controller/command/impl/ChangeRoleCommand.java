package com.art.orion.controller.command.impl;

import com.art.orion.controller.command.Command;
import com.art.orion.exception.ServiceException;
import com.art.orion.model.entity.Role;
import com.art.orion.model.entity.User;
import com.art.orion.model.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Optional;

import static com.art.orion.controller.command.PagePath.USER_MANAGEMENT_REDIRECT_PAGE;
import static com.art.orion.util.Constant.PAGE;
import static com.art.orion.util.Constant.ROLE;
import static com.art.orion.util.Constant.USERNAME;

public class ChangeRoleCommand implements Command {
    private static final Logger logger = LogManager.getLogger();
    private final UserService userService;

    public ChangeRoleCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String execute(HttpServletRequest req) {
        String pageNumber = req.getParameter(PAGE);
        System.out.println("_______________role - " + pageNumber);
        req.setAttribute(PAGE, pageNumber);
        req.getSession().setAttribute(PAGE, pageNumber);
        String username = req.getParameter(USERNAME);
        String strRole = req.getParameter(ROLE);
        Role role = Role.valueOf(strRole);
        try {
            System.out.println("_______________role - get user " + username);
            Optional<User> optionalUser = userService.findUserByUsername(username);
            if (optionalUser.isPresent()) {
                User user = optionalUser.get();

                user.setRole(role);
                System.out.println(user.getUsername() + "___________________" + user.getRole());
                if (userService.updateUser(user)) {
                    logger.log(Level.INFO, () -> "User " + username + " got role = " + role);
                }
            }
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e);
        }
        return USER_MANAGEMENT_REDIRECT_PAGE;
    }
}
