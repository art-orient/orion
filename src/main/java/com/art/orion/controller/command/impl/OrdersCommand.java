package com.art.orion.controller.command.impl;

import com.art.orion.controller.command.Command;
import com.art.orion.controller.command.util.Paginator;
import com.art.orion.model.entity.Order;
import com.art.orion.model.service.OrderService;
import com.art.orion.exception.ServiceException;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

import static com.art.orion.controller.command.PagePath.ORDERS_PAGE;
import static com.art.orion.controller.command.PagePath.ORDERS_REDIRECT_PAGE;
import static com.art.orion.controller.command.util.Paginator.LIMIT;
import static com.art.orion.util.Constant.NUMBER_ORDERS;
import static com.art.orion.util.Constant.NUMBER_PAGES;
import static com.art.orion.util.Constant.ORDERS;
import static com.art.orion.util.Constant.PAGE;
import static com.art.orion.util.Constant.USERNAME;

public class OrdersCommand implements Command {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public String execute(HttpServletRequest req) {
        String username = (String) req.getSession().getAttribute(USERNAME);
        String page = ORDERS_PAGE;
        int offset = Paginator.preparePagination(req);
        try {
            List<Order> orders = OrderService.getUserOrders(username, LIMIT, offset);
            req.setAttribute(ORDERS, orders);
            int numberOrders = OrderService.countNumberOrders(username);
            req.setAttribute(NUMBER_ORDERS, numberOrders);
            int numberPages = Paginator.findNumberPages(numberOrders);
            req.setAttribute(NUMBER_PAGES, numberPages);
            int pageNumber = (int) req.getSession().getAttribute(PAGE);
            if (pageNumber > numberPages) {
                req.getSession().setAttribute(PAGE, numberPages);
                page = ORDERS_REDIRECT_PAGE;
            }
        } catch (ServiceException e) {
            logger.log(Level.ERROR,e);
        }
        return page;
    }
}
