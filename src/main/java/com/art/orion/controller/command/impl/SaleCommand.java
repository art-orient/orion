package com.art.orion.controller.command.impl;

import com.art.orion.controller.command.Command;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static com.art.orion.controller.command.PagePath.SALE_PAGE;

public class SaleCommand implements Command {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public String execute(HttpServletRequest req) {
        logger.log(Level.DEBUG,"Go to page sale");
        return SALE_PAGE;
    }
}