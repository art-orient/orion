package com.art.orion.controller.command.impl;

import com.art.orion.controller.command.Command;
import com.art.orion.model.entity.Order;
import com.art.orion.model.service.CartService;
import com.art.orion.util.ConfigManager;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import static com.art.orion.util.Constant.CART;
import static com.art.orion.util.Constant.USERNAME;
public class MakeOrderCommand implements Command {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public String execute(HttpServletRequest req) {
        HttpSession session = req.getSession();
        Order order = createOrder(session);

        return ConfigManager.getProperty("page.cartRedirect");
    }

    private Order createOrder(HttpSession session) {
        String username = (String) session.getAttribute(USERNAME);
        List<Object> cart = (ArrayList<Object>) session.getAttribute(CART);
        Date date = new Date();
        Map<Object, Long> products = CartService.groupProducts(cart);
        BigDecimal totalCost = CartService.findTotalCost(cart);
        return new Order(username, date, products, totalCost, false);
    }
}
