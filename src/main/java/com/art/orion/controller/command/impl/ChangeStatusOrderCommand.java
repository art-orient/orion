package com.art.orion.controller.command.impl;

import com.art.orion.controller.command.Command;
import com.art.orion.controller.command.util.RequestParseNumberHelper;
import com.art.orion.exception.ServiceException;
import com.art.orion.model.entity.Order;
import com.art.orion.model.service.OrderService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Optional;

import static com.art.orion.controller.command.PagePath.ORDER_MANAGEMENT_REDIRECT_PAGE;
import static com.art.orion.util.Constant.ORDER_ID;
import static com.art.orion.util.Constant.PAGE;

/**
 * The command is responsible for changing the status of the order
 *
 * @author Aliaksandr Artsikhovich
 * @see Command
 */
public class ChangeStatusOrderCommand implements Command {
    private static final Logger logger = LogManager.getLogger();
    private final OrderService orderService;

    public ChangeStatusOrderCommand(OrderService orderService) {
        this.orderService = orderService;
    }

    @Override
    public String execute(HttpServletRequest req) {
        logger.log(Level.DEBUG, "Go to change status order command");
        HttpSession session = req.getSession();
        String pageNumber = req.getParameter(PAGE);
        session.setAttribute(PAGE, pageNumber);
        int orderId = RequestParseNumberHelper.getInt(req, ORDER_ID);
        try {
            Optional<Order> optionalOrder = orderService.findOrderById(orderId);
            if (optionalOrder.isPresent()) {
                Order order = optionalOrder.get();
                order.setConfirmationStatus(true);
                if (orderService.updateOrder(order)) {
                    logger.log(Level.INFO, () -> "Order " + orderId + " is confirmed");
                }
            }
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e);
        }
        return ORDER_MANAGEMENT_REDIRECT_PAGE;
    }
}
