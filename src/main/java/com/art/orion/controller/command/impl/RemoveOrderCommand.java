package com.art.orion.controller.command.impl;

import com.art.orion.controller.command.Command;
import com.art.orion.controller.command.util.RequestParseNumberHelper;
import com.art.orion.model.service.OrderService;
import com.art.orion.exception.ServiceException;
import com.art.orion.util.ConfigManager;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class RemoveOrderCommand implements Command {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public String execute(HttpServletRequest req) {
        int orderId = RequestParseNumberHelper.getInt(req, "orderId");
        try {
           OrderService.removeOrderById(orderId);
        } catch (ServiceException e) {
            logger.log(Level.ERROR,e);
        }
        return ConfigManager.getProperty("page.ordersRedirect");
    }
}