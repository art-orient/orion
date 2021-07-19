package com.art.orion.controller.command.impl;

import com.art.orion.controller.command.Command;
import com.art.orion.model.entity.Accessory;
import com.art.orion.model.entity.Clothing;
import com.art.orion.model.entity.Shoes;
import com.art.orion.model.service.ProductService;
import com.art.orion.exception.ServiceException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.art.orion.controller.command.PagePath.CART_REDIRECT_PAGE;
import static com.art.orion.util.Constant.ACCESSORIES;
import static com.art.orion.util.Constant.CART;
import static com.art.orion.util.Constant.CATEGORY;
import static com.art.orion.util.Constant.CLOTHING;
import static com.art.orion.util.Constant.PRODUCT;
import static com.art.orion.util.Constant.SHOES;

/**
 * The command is responsible for removing the product from the user's order
 *
 * @author Aliaksandr Artsikhovich
 * @see Command
 */
public class RemoveProductCommand implements Command {
    private static final Logger logger = LogManager.getLogger();
    private final ProductService productService;

    public RemoveProductCommand(ProductService productService) {
        this.productService = productService;
    }

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
        logger.log(Level.DEBUG, "ID of product is {}", productId);
        try {
            switch (category) {
                case ACCESSORIES -> {
                    Optional<Accessory> optionalAccessory = productService.findAccessoryById(productId);
                    if (optionalAccessory.isPresent()) {
                        Accessory accessory = optionalAccessory.get();
                        cart.remove(accessory);
                    }
                }
                case CLOTHING -> {
                    Optional<Clothing> optionalClothing = productService.findClothingById(productId);
                    if (optionalClothing.isPresent()) {
                        Clothing clothing = optionalClothing.get();
                        cart.remove(clothing);
                    }
                }
                case SHOES -> {
                    Optional<Shoes> optionalShoes = productService.findShoesById(productId);
                    if (optionalShoes.isPresent()) {
                        Shoes shoes = optionalShoes.get();
                        cart.remove(shoes);
                    }
                }
                default -> logger.log(Level.WARN, () -> String.format("Invalid category - %s", category));
            }
        } catch (ServiceException e) {
            logger.log(Level.ERROR, () -> "Database access error when retrieving product by id");
        }
        session.setAttribute(CART, cart);
        logger.log(Level.INFO, () -> "A product removed from the cart");
        return CART_REDIRECT_PAGE;
    }
}
