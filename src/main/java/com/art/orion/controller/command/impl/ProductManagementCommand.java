package com.art.orion.controller.command.impl;

import com.art.orion.controller.command.Command;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import jakarta.servlet.http.HttpServletRequest;

import static com.art.orion.controller.command.PagePath.PRODUCT_MANAGEMENT_PAGE;

/**
 * The command is responsible for going to the product management page
 *
 * @author Aliaksandr Artsikhovich
 * @see Command
 */
public class ProductManagementCommand implements Command {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public String execute(HttpServletRequest req) {
        logger.log(Level.INFO, "Go to product management page");
        return PRODUCT_MANAGEMENT_PAGE;
    }
}
