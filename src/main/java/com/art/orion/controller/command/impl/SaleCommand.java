package com.art.orion.controller.command.impl;

import com.art.orion.controller.command.Command;
import com.art.orion.model.entity.Accessory;
import com.art.orion.model.service.ProductService;
import com.art.orion.util.ConfigManager;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

import static com.art.orion.util.Constant.PRODUCTS;

public class SaleCommand implements Command {
    private static final Logger logger = LogManager.getLogger();
    private final static String DIRECTORY = "/images/shoes/";

    @Override
    public String execute(HttpServletRequest req) {
        logger.log(Level.DEBUG,"Go to page sale");
        List<Accessory> accessories = ProductService.searchAccessories(5, 0);
        req.getSession().setAttribute(PRODUCTS, accessories);
        req.getSession().setAttribute("directory", DIRECTORY);
        return ConfigManager.getProperty("page.sale");
    }
}