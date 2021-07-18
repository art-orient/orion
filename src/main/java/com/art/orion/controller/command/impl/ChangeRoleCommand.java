package com.art.orion.controller.command.impl;

import com.art.orion.controller.command.Command;
import com.art.orion.exception.ServiceException;
import com.art.orion.model.entity.Role;
import com.art.orion.model.entity.User;
import com.art.orion.model.service.UserService;
import com.art.orion.util.ErrorMessageManager;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Optional;

import static com.art.orion.controller.command.PagePath.USER_MANAGEMENT_REDIRECT_PAGE;
import static com.art.orion.util.Constant.ERROR;
import static com.art.orion.util.Constant.PAGE;
import static com.art.orion.util.Constant.ROLE;
import static com.art.orion.util.Constant.USERNAME;

/**
 * The command is responsible for changing the role of the user
 *
 * @author Aliaksandr Artsikhovich
 * @see Command
 */
public class ChangeRoleCommand implements Command {
    private static final Logger logger = LogManager.getLogger();
    private final UserService userService;

    public ChangeRoleCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String execute(HttpServletRequest req) {
        HttpSession session = req.getSession();
        session.removeAttribute(ERROR);
        String pageNumber = req.getParameter(PAGE);
        session.setAttribute(PAGE, pageNumber);
        String username = req.getParameter(USERNAME);
        String strRole = req.getParameter(ROLE);
        if (strRole.isEmpty()) {
            session.setAttribute(ERROR, ErrorMessageManager.getMessage("msg.chooseRole"));
        } else {
            changeRole(session, username, strRole);
        }
        return USER_MANAGEMENT_REDIRECT_PAGE;
    }

    private void changeRole(HttpSession session, String username, String strRole) {
        Role role = Role.valueOf(strRole);
        try {
            Optional<User> optionalUser = userService.findUserByUsername(username);
            if (optionalUser.isPresent()) {
                User user = optionalUser.get();
                if (user.getRole() == role) {
                    session.setAttribute(ERROR, ErrorMessageManager.getMessage("msg.sameRole"));
                } else {
                    user.setRole(role);
                    if (userService.updateUser(user)) {
                        logger.log(Level.INFO, () -> "User " + username + " got role = " + role);
                    }
                }
            }
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e);
        }
    }
}
