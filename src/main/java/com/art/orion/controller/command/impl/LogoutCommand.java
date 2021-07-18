package com.art.orion.controller.command.impl;

import com.art.orion.controller.command.Command;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import jakarta.servlet.http.HttpServletRequest;

import static com.art.orion.controller.command.PagePath.INDEX_PAGE;

/**
 * The command is responsible for logging out the user and clearing his session
 *
 * @author Aliaksandr Artsikhovich
 * @see Command
 */
public class LogoutCommand implements Command {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public String execute(HttpServletRequest req) {
        req.getSession().invalidate();
        logger.log(Level.INFO, "User is logout");
        return INDEX_PAGE;
    }
}
