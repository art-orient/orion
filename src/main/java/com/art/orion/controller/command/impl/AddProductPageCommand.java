package com.art.orion.controller.command.impl;

import com.art.orion.controller.command.Command;
import com.art.orion.util.ConfigManager;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import jakarta.servlet.http.HttpServletRequest;

public class AddProductPageCommand implements Command {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public String execute(HttpServletRequest req) {
        String category = req.getParameter("category");
        if (category.isEmpty()) {
            return ConfigManager.getProperty("page.productManagement");
        }
        req.getSession().setAttribute("category", category);
        logger.log(Level.DEBUG, "Go to add product page");
        return ConfigManager.getProperty("page.addProduct");
    }
}
