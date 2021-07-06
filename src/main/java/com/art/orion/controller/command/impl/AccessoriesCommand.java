package com.art.orion.controller.command.impl;

import com.art.orion.controller.command.Command;
import com.art.orion.model.entity.Accessory;
import com.art.orion.model.service.ProductService;
import com.art.orion.util.ConfigManager;
import com.art.orion.util.Constant;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import jakarta.servlet.http.HttpServletRequest;

import static com.art.orion.util.Constant.PRODUCTS;

import java.util.List;

public class AccessoriesCommand implements Command {
    private static final Logger logger = LogManager.getLogger();
    private final static String DIRECTORY = "/images/accessories/";

    @Override
    public String execute(HttpServletRequest req) {
        logger.log(Level.DEBUG,"Go to page accessories");
        List<Accessory> accessories = ProductService.searchAccessories();
        req.getSession().setAttribute(PRODUCTS, accessories);
        req.getSession().setAttribute("directory", DIRECTORY);
        return ConfigManager.getProperty("page.accessories");
    }
}
