package com.art.orion.controller.command.impl;

import com.art.orion.controller.command.Command;
import com.art.orion.model.entity.Accessory;
import com.art.orion.model.service.ProductService;
import com.art.orion.util.ConfigManager;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

import static com.art.orion.util.Constant.ACCESSORIES;
import static com.art.orion.util.Constant.PRODUCT;

import static com.art.orion.util.Constant.CART;
import static com.art.orion.util.Constant.CATEGORY;

public class AddProductCommand implements Command {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public String execute(HttpServletRequest req) {
        HttpSession session = req.getSession();
        List<Object> cart = getCart(session);
        String category = req.getParameter(CATEGORY);
        String productId = req.getParameter(PRODUCT);
        int id = 0;
        try {
            id = Integer.parseInt(productId);
        } catch (NumberFormatException e) {
            logger.log(Level.ERROR, () -> String.format("Bad product id from %s = %s",
                    category, productId));
        }
        if (category.equals(ACCESSORIES)) {
            Accessory accessory = ProductService.getAccessoryById(id);
            if (accessory != null) {
                cart.add(accessory);
            }
        }
        session.setAttribute(CART, cart);
        logger.log(Level.INFO, () -> String.format("Add product in the cart from %s with id = %s",
                category, productId));
        return ConfigManager.getProperty("page." + category + "Redirect");
    }

    private List<Object> getCart(HttpSession session) {
        List<Object> cart = (ArrayList<Object>) session.getAttribute(CART);
        if (cart == null) {
            cart = new ArrayList<>();
        }
        return cart;
    }
}
