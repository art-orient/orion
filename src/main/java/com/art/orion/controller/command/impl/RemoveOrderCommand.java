package com.art.orion.controller.command.impl;

import com.art.orion.controller.command.Command;
import com.art.orion.controller.command.util.RequestParseNumberHelper;
import com.art.orion.model.service.OrderService;
import com.art.orion.exception.ServiceException;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static com.art.orion.controller.command.PagePath.ORDERS_REDIRECT_PAGE;
import static com.art.orion.util.Constant.ORDER_ID;

public class RemoveOrderCommand implements Command {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public String execute(HttpServletRequest req) {
        int orderId = RequestParseNumberHelper.getInt(req, ORDER_ID);
        try {
           OrderService.removeOrderById(orderId);
        } catch (ServiceException e) {
            logger.log(Level.ERROR,e);
        }
        return ORDERS_REDIRECT_PAGE;
    }
}