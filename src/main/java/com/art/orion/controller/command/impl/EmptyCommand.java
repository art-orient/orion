package com.art.orion.controller.command.impl;

import com.art.orion.controller.command.Command;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import jakarta.servlet.http.HttpServletRequest;

import static com.art.orion.controller.command.PagePath.ERROR_PAGE;
import static com.art.orion.util.Constant.CURRENT_PAGE;

public class EmptyCommand implements Command {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public String execute(HttpServletRequest req) {
        logger.log(Level.INFO, "redirect on error page");
        String errorPage = ERROR_PAGE;
        req.getSession().setAttribute(CURRENT_PAGE, errorPage);
        return errorPage;
    }
}
