package com.art.orion.controller.command.impl;

import com.art.orion.controller.command.Command;
import com.art.orion.controller.command.util.RequestParseNumberHelper;
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

import static com.art.orion.controller.command.PagePath.ACCESSORIES_REDIRECT_PAGE;
import static com.art.orion.controller.command.PagePath.CLOTHING_REDIRECT_PAGE;
import static com.art.orion.controller.command.PagePath.INDEX_PAGE;
import static com.art.orion.controller.command.PagePath.SHOES_REDIRECT_PAGE;
import static com.art.orion.util.Constant.ACCESSORIES;
import static com.art.orion.util.Constant.CLOTHING;
import static com.art.orion.util.Constant.PRODUCT;
import static com.art.orion.util.Constant.CART;
import static com.art.orion.util.Constant.CATEGORY;
import static com.art.orion.util.Constant.SHOES;

public class AddProductCommand implements Command {
    private static final Logger logger = LogManager.getLogger();
    private final ProductService productService;

    public AddProductCommand(ProductService productService) {
        this.productService = productService;
    }

    @Override
    public String execute(HttpServletRequest req) {
        HttpSession session = req.getSession();
        List<Object> cart = getCart(session);
        String category = req.getParameter(CATEGORY);
        String page = INDEX_PAGE;
        int id = RequestParseNumberHelper.getInt(req, PRODUCT);
        try {
            switch (category) {
                case ACCESSORIES -> {
                        Optional<Accessory> optionalAccessory = productService.findAccessoryById(id);
                        if (optionalAccessory.isPresent()) {
                            Accessory accessory = optionalAccessory.get();
                            cart.add(accessory);
                            page = ACCESSORIES_REDIRECT_PAGE;
                        }
                }
                case CLOTHING -> {
                    Optional<Clothing> optionalClothing = productService.findClothingById(id);
                    if (optionalClothing.isPresent()) {
                        Clothing clothing = optionalClothing.get();
                        cart.add(clothing);
                        page = CLOTHING_REDIRECT_PAGE;
                    }
                }
                case SHOES -> {
                    Optional<Shoes> optionalShoes = productService.findShoesById(id);
                    if (optionalShoes.isPresent()) {
                        Shoes shoes = optionalShoes.get();
                        cart.add(shoes);
                        page = SHOES_REDIRECT_PAGE;
                    }
                }
                default -> logger.log(Level.WARN, () -> String.format("Category %s is not exist", category));
            }
        } catch (ServiceException e) {
            logger.log(Level.ERROR, "Database access error when retrieving product by id");
        }
        session.setAttribute(CART, cart);
        logger.log(Level.INFO, () -> String.format("Add product in the cart from %s with id = %s",
                category, id));
        return page;
    }

    private List<Object> getCart(HttpSession session) {
        List<Object> cart = (ArrayList<Object>) session.getAttribute(CART);
        if (cart == null) {
            cart = new ArrayList<>();
        }
        return cart;
    }
}
