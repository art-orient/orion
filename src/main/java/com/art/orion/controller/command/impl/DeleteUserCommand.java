package com.art.orion.controller.command.impl;

import com.art.orion.controller.command.Command;
import com.art.orion.exception.ServiceException;
import com.art.orion.model.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static com.art.orion.controller.command.PagePath.USER_MANAGEMENT_REDIRECT_PAGE;
import static com.art.orion.util.Constant.ERROR;
import static com.art.orion.util.Constant.PAGE;
import static com.art.orion.util.Constant.USERNAME;

/**
 * The command is responsible for deleting a user
 *
 * @author Aliaksandr Artsikhovich
 * @see Command
 */
public class DeleteUserCommand implements Command {
    private static final Logger logger = LogManager.getLogger();
    private final UserService userService;

    public DeleteUserCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String execute(HttpServletRequest req) {
        HttpSession session = req.getSession();
        session.removeAttribute(ERROR);
        String pageNumber = req.getParameter(PAGE);
        session.setAttribute(PAGE, pageNumber);
        String username = req.getParameter(USERNAME);
        try {
            if (userService.deleteUser(username)) {
                logger.log(Level.INFO, () -> "User " + username + " is deleted");
            }
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e);
        }
        return USER_MANAGEMENT_REDIRECT_PAGE;
    }
}
