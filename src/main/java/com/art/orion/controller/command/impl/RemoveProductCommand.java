package com.art.orion.controller.command.impl;

import com.art.orion.controller.command.Command;
import com.art.orion.model.entity.Accessory;
import com.art.orion.model.entity.Clothing;
import com.art.orion.model.entity.Shoes;
import com.art.orion.model.service.ProductService;
import com.art.orion.exception.ServiceException;
import com.art.orion.util.ConfigManager;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

import static com.art.orion.util.Constant.ACCESSORIES;
import static com.art.orion.util.Constant.CART;
import static com.art.orion.util.Constant.CATEGORY;
import static com.art.orion.util.Constant.CLOTHING;
import static com.art.orion.util.Constant.PRODUCT;
import static com.art.orion.util.Constant.SHOES;

public class RemoveProductCommand implements Command {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public String execute(HttpServletRequest req) {
        HttpSession session = req.getSession();
        List<Object> cart = (ArrayList<Object>) session.getAttribute(CART);
        int productId = -1;
        try {
            productId = Integer.parseInt(req.getParameter(PRODUCT));
        } catch (NumberFormatException e) {
            logger.log(Level.ERROR, e);
        }
        String category = req.getParameter(CATEGORY);
        logger.log(Level.DEBUG, () -> String.format("Category of product is %s", category));
        logger.log(Level.DEBUG, String.format("ID of product is %d", productId));
        try {
            switch (category) {
                case ACCESSORIES -> {
                    Accessory accessory = ProductService.getAccessoryById(productId);
                    cart.remove(accessory);
                }
                case CLOTHING -> {
                    Clothing clothing = ProductService.getClothingById(productId);
                    cart.remove(clothing);
                }
                case SHOES -> {
                    Shoes shoes = ProductService.getShoesById(productId);
                    cart.remove(shoes);
                }
                default -> logger.log(Level.WARN, () -> String.format("Invalid category - %s", category));
            }
        } catch (ServiceException e) {
            logger.log(Level.ERROR, () -> "Database access error when retrieving product by id");
        }
        session.setAttribute(CART, cart);
        logger.log(Level.INFO, () -> "A product removed from the cart");
        return ConfigManager.getProperty("page.cartRedirect");
    }
}
