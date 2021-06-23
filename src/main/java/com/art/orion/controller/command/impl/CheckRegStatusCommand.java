package com.art.orion.controller.command.impl;

import com.art.orion.controller.command.Command;
import com.art.orion.util.ConfigManager;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

import static com.art.orion.util.Constant.REGISTRATION_STATUS;

public class CheckRegStatusCommand implements Command {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public String execute(HttpServletRequest req) {
        String registrationStatus = req.getParameter(REGISTRATION_STATUS);
        if (registrationStatus == null) {
            registrationStatus = "";
        }
        req.getSession().setAttribute(REGISTRATION_STATUS, registrationStatus);
        logger.log(Level.DEBUG, "Create regisration status");
        return ConfigManager.getProperty("page.registration");
    }
}
