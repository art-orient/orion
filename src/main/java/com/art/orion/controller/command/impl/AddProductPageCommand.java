package com.art.orion.controller.command.impl;

import com.art.orion.controller.command.Command;
import com.art.orion.util.ErrorMessageManager;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import jakarta.servlet.http.HttpServletRequest;

import static com.art.orion.controller.command.PagePath.ADD_PRODUCT_PAGE;
import static com.art.orion.controller.command.PagePath.PRODUCT_MANAGEMENT_PAGE;
import static com.art.orion.util.Constant.CATEGORY;
import static com.art.orion.util.Constant.ERROR;

public class AddProductPageCommand implements Command {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public String execute(HttpServletRequest req) {
        String category = req.getParameter(CATEGORY);
        if (category.isEmpty()) {
            req.setAttribute(ERROR, ErrorMessageManager.getMessage("msg.enterCategory"));
            return PRODUCT_MANAGEMENT_PAGE;
        }
        req.getSession().setAttribute(CATEGORY, category);
        logger.log(Level.DEBUG, "Go to add product page");
        return ADD_PRODUCT_PAGE;
    }
}
