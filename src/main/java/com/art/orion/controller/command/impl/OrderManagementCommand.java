package com.art.orion.controller.command.impl;

import com.art.orion.controller.command.Command;
import com.art.orion.controller.command.util.Paginator;
import com.art.orion.exception.ServiceException;
import com.art.orion.model.entity.Order;
import com.art.orion.model.service.OrderService;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

import static com.art.orion.controller.command.PagePath.ORDER_MANAGEMENT_PAGE;
import static com.art.orion.controller.command.PagePath.ORDER_MANAGEMENT_REDIRECT_PAGE;
import static com.art.orion.controller.command.util.Paginator.LIMIT;
import static com.art.orion.util.Constant.NUMBER_ORDERS;
import static com.art.orion.util.Constant.NUMBER_PAGES;
import static com.art.orion.util.Constant.ORDERS;
import static com.art.orion.util.Constant.PAGE;

/**
 * The command is responsible for managing all orders
 *
 * @author Aliaksandr Artsikhovich
 * @see Command
 */
public class OrderManagementCommand implements Command {
    private static final Logger logger = LogManager.getLogger();
    private final OrderService orderService;

    public OrderManagementCommand(OrderService orderService) {
        this.orderService = orderService;
    }

    @Override
    public String execute(HttpServletRequest req) {
        logger.log(Level.INFO, "Go to order management page");
        String pageNumber = req.getParameter(PAGE);
        req.getSession().setAttribute(PAGE, pageNumber);
        int offset = Paginator.preparePagination(req);
        String page = ORDER_MANAGEMENT_PAGE;
        try {
            List<Order> orders = orderService.findAllOrders(LIMIT, offset);
            req.setAttribute(ORDERS, orders);
            page = checkNumberPages(req);
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e);
        }
        return page;
    }

    private String checkNumberPages(HttpServletRequest req) throws ServiceException {
        int numberOrders = orderService.countNumberAllOrders();
        req.setAttribute(NUMBER_ORDERS, numberOrders);
        int numberPages = Paginator.findNumberPages(numberOrders);
        req.setAttribute(NUMBER_PAGES, numberPages);
        int sessionPageNumber = (int) req.getSession().getAttribute(PAGE);
        String page = ORDER_MANAGEMENT_PAGE;
        if (sessionPageNumber > numberPages) {
            req.getSession().setAttribute(PAGE, numberPages);
            page = ORDER_MANAGEMENT_REDIRECT_PAGE;
        }
        return page;
    }
}
