package com.art.orion.controller.command.impl;

import com.art.orion.controller.command.Command;
import com.art.orion.util.ConfigManager;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import jakarta.servlet.http.HttpServletRequest;

import static com.art.orion.util.Constant.CURRENT_PAGE;

public class EmptyCommand implements Command {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public String execute(HttpServletRequest req) {
        logger.log(Level.INFO, "redirect on error page");
        String errorPage = ConfigManager.getProperty("page.error");
        req.getSession().setAttribute(CURRENT_PAGE, errorPage);
        return errorPage;
    }
}
