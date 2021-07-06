package com.art.orion.controller.command.impl;

import com.art.orion.controller.command.Command;
import com.art.orion.model.service.CartService;
import com.art.orion.util.ConfigManager;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.art.orion.util.Constant.CART;
import static com.art.orion.util.Constant.GROUPED_CART;

public class CartCommand implements Command {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public String execute(HttpServletRequest req) {
        List<Object> products = (ArrayList<Object>) req.getSession().getAttribute(CART);
        if (products != null) {
            Map<Object, Long> groupedProducts = CartService.groupProducts(products);
            req.setAttribute(GROUPED_CART, new ArrayList<>(groupedProducts.entrySet()));
        }
        return ConfigManager.getProperty("page.cart");
    }
}
