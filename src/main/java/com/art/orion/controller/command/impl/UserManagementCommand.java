package com.art.orion.controller.command.impl;

import com.art.orion.controller.command.Command;
import com.art.orion.controller.command.util.Paginator;
import com.art.orion.exception.ServiceException;
import com.art.orion.model.entity.User;
import com.art.orion.model.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

import static com.art.orion.controller.command.PagePath.USER_MANAGEMENT_PAGE;
import static com.art.orion.controller.command.PagePath.USER_MANAGEMENT_REDIRECT_PAGE;
import static com.art.orion.controller.command.util.Paginator.USER_LIMIT;
import static com.art.orion.util.Constant.NUMBER_PAGES;
import static com.art.orion.util.Constant.NUMBER_USERS;
import static com.art.orion.util.Constant.PAGE;
import static com.art.orion.util.Constant.USERS;

public class UserManagementCommand implements Command {
    private static final Logger logger = LogManager.getLogger();
    private final UserService userService;

    public UserManagementCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String execute(HttpServletRequest req) {
        logger.log(Level.INFO, "Go to user management page");
        String pageNumber = req.getParameter(PAGE);
        req.getSession().setAttribute(PAGE, pageNumber);
        int offset = Paginator.preparePagination(req);
        String page = USER_MANAGEMENT_PAGE;
        try {
            List<User> users = userService.findUsers(USER_LIMIT, offset);
            req.setAttribute(USERS, users);
            int numberUsers = userService.countUsers();
            req.setAttribute(NUMBER_USERS , numberUsers);
            int numberPages = Paginator.findUserNumberPages(numberUsers);
            req.setAttribute(NUMBER_PAGES, numberPages);
            int sessionPageNumber = (int) req.getSession().getAttribute(PAGE);
            if (sessionPageNumber > numberPages) {
                req.getSession().setAttribute(PAGE, numberPages);
                page = USER_MANAGEMENT_REDIRECT_PAGE;
            }
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e);
        }
        return page;
    }
}
