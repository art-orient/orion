package com.art.orion.controller.command.impl;

import com.art.orion.controller.command.Command;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import jakarta.servlet.http.HttpServletRequest;

import static com.art.orion.controller.command.PagePath.REGISTRATION_PAGE;
import static com.art.orion.util.Constant.REGISTRATION_STATUS;

/**
 * The command is responsible for creating the registration status when registering a new user
 *
 * @author Aliaksandr Artsikhovich
 * @see Command
 */
public class CheckRegStatusCommand implements Command {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public String execute(HttpServletRequest req) {
        String registrationStatus = req.getParameter(REGISTRATION_STATUS);
        if (registrationStatus == null) {
            registrationStatus = "";
        }
        req.getSession().setAttribute(REGISTRATION_STATUS, registrationStatus);
        logger.log(Level.DEBUG, "Create registration status");
        return REGISTRATION_PAGE;
    }
}
