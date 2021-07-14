package com.art.orion.controller.command.impl;

import com.art.orion.controller.command.Command;
import com.art.orion.model.service.CartService;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.art.orion.controller.command.PagePath.CART_PAGE;
import static com.art.orion.util.Constant.CART;
import static com.art.orion.util.Constant.ERROR;
import static com.art.orion.util.Constant.GROUPED_CART;
import static com.art.orion.util.Constant.NUMBER;
import static com.art.orion.util.Constant.TOTAL_COST;

public class CartCommand implements Command {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public String execute(HttpServletRequest req) {
        List<Object> products = (ArrayList<Object>) req.getSession().getAttribute(CART);
        if (products != null) {
            Map<Object, Long> groupedProducts = CartService.groupProducts(products);
            req.setAttribute(GROUPED_CART, new ArrayList<>(groupedProducts.entrySet()));
            BigDecimal totalCost = CartService.findTotalCost(products);
            req.setAttribute(TOTAL_COST, totalCost);
            req.setAttribute(NUMBER, products.size());
            req.setAttribute(ERROR, req.getParameter(ERROR));
        }
        logger.log(Level.DEBUG, () -> "Created a cart");
        return CART_PAGE;
    }
}
