package com.art.orion.model.service;

import com.art.orion.model.dao.OrderDao;
import com.art.orion.model.dao.OrionDatabaseException;
import com.art.orion.model.dao.impl.OrderDaoJdbc;
import com.art.orion.model.entity.Order;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.SQLException;
import java.util.List;

public class OrderService {
    private static final Logger logger = LogManager.getLogger();
    private static final OrderDao ORDER_DAO = OrderDaoJdbc.getInstance();

    private OrderService() {
    }

    public static boolean addOrderToDatabase(Order order) throws ServiceException {
        try {
            return ORDER_DAO.addOrderToDatabase(order) > 0;
        } catch (SQLException | OrionDatabaseException e) {
            throw new ServiceException("Order is not added in the database", e);
        }
    }

    public static List<Order> getUserOrders(String username, int limit, int offset) throws ServiceException {
        try {
            return ORDER_DAO.getUserOrders(username, limit, offset);
        } catch (OrionDatabaseException e) {
            throw new ServiceException("Database access error occurred while retrieving user's orders", e);
        }
    }

    public static int countNumberOrders(String username) throws ServiceException {
        int numberOrders;
        try {
            numberOrders = ORDER_DAO.countNumberOrders(username);
            logger.log(Level.DEBUG, "OrderService - counting the number of orders = {} by username", numberOrders);
        } catch (OrionDatabaseException e) {
            System.out.println("----------- error from OrderDAO");
            throw new ServiceException("Database access error occurred while retrieving user's orders", e);
        }
        return numberOrders;
    }
}
