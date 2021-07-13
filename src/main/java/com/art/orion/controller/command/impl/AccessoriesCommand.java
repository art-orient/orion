package com.art.orion.controller.command.impl;

import com.art.orion.controller.command.Command;
import com.art.orion.controller.command.util.Paginator;
import com.art.orion.model.entity.Accessory;
import com.art.orion.model.entity.ProductCategory;
import com.art.orion.model.entity.Role;
import com.art.orion.model.service.ProductService;
import com.art.orion.model.service.ServiceException;
import com.art.orion.util.ConfigManager;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import jakarta.servlet.http.HttpServletRequest;

import static com.art.orion.controller.command.util.Paginator.LIMIT;
import static com.art.orion.util.Constant.NUMBER_PAGES;
import static com.art.orion.util.Constant.NUMBER_PRODUCTS;
import static com.art.orion.util.Constant.PRODUCTS;
import static com.art.orion.util.Constant.ROLE;

import java.util.List;

public class AccessoriesCommand implements Command {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public String execute(HttpServletRequest req) {
        logger.log(Level.DEBUG,"Go to page accessories");
        int offset = Paginator.preparePagination(req);
        try {
            String role = (String) req.getSession().getAttribute(ROLE);
            boolean isAdmin = role == Role.ADMIN.name();
            List<Accessory> accessories = ProductService.searchAccessories(LIMIT, offset, isAdmin);
            req.getSession().setAttribute(PRODUCTS, accessories);
            int numberProducts = ProductService.countNumberProducts(ProductCategory.ACCESSORIES, isAdmin);
            req.setAttribute(NUMBER_PRODUCTS, numberProducts);
            int numberPages = Paginator.findNumberPages(numberProducts);
            req.setAttribute(NUMBER_PAGES, numberPages);
        } catch (ServiceException e) {
            logger.log(Level.ERROR, "Database access error when searching for accessories", e);
        }
        return ConfigManager.getProperty("page.accessories");
    }
}