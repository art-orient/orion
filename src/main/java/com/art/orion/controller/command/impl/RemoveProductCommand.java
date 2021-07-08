package com.art.orion.controller.command.impl;

import com.art.orion.controller.command.Command;
import com.art.orion.model.entity.Accessory;
import com.art.orion.model.entity.Clothing;
import com.art.orion.model.entity.Shoes;
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
        int productId = 0;
        try {
            productId = Integer.parseInt(req.getParameter(PRODUCT));
        } catch (NumberFormatException e) {
            logger.log(Level.ERROR, e);
        }
        String category = req.getParameter(CATEGORY);
        logger.log(Level.DEBUG, () -> String.format("Category of product is %s", category));
        logger.log(Level.DEBUG, String.format("ID of product is %d", productId));
        if (category.equals(ACCESSORIES)) {
            Accessory accessory = ProductService.getAccessoryById(productId);
            cart.remove(accessory);
        } else if (category.equals(CLOTHING)) {
            Clothing clothing = ProductService.getClothingById(productId);
            cart.remove(clothing);
        } else if (category.equals(SHOES)) {
            Shoes shoes = ProductService.getShoesById(productId);
            cart.remove(shoes);
        }
        session.setAttribute(CART, cart);
        logger.log(Level.INFO, () -> "A product removed from the cart");
        return ConfigManager.getProperty("page.cartRedirect");
    }
}
