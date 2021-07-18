package com.art.orion.model.service.impl;

import com.art.orion.exception.ServiceException;
import com.art.orion.model.dao.OrderDao;
import com.art.orion.exception.OrionDatabaseException;
import com.art.orion.model.dao.impl.OrderDaoJdbc;
import com.art.orion.model.entity.Order;
import com.art.orion.model.service.OrderService;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.SQLException;
import java.util.List;

public class OrderServiceImpl implements OrderService {
    private static final Logger logger = LogManager.getLogger();
    private static final OrderDao ORDER_DAO = OrderDaoJdbc.getInstance();

    @Override
    public boolean addOrderToDatabase(Order order) throws ServiceException {
        try {
            return ORDER_DAO.addOrderToDatabase(order) > 0;
        } catch (SQLException | OrionDatabaseException e) {
            throw new ServiceException("Order is not added in the database", e);
        }
    }

    @Override
    public List<Order> findUserOrders(String username, int limit, int offset) throws ServiceException {
        try {
            return ORDER_DAO.findUserOrders(username, limit, offset);
        } catch (OrionDatabaseException e) {
            throw new ServiceException("Database access error occurred while retrieving user's orders", e);
        }
    }

    @Override
    public int countNumberOrders(String username) throws ServiceException {
        int numberOrders;
        try {
            numberOrders = ORDER_DAO.countNumberOrders(username);
            logger.log(Level.DEBUG, "OrderService - counting the number of orders = {} by username", numberOrders);
        } catch (OrionDatabaseException e) {
            throw new ServiceException("Database access error occurred while counting user's orders", e);
        }
        return numberOrders;
    }

    @Override
    public void removeOrderById(int orderId) throws ServiceException {
        try {
            ORDER_DAO.removeOrderById(orderId);
        } catch (SQLException | OrionDatabaseException e) {
            throw new ServiceException("Order is not removed from the database", e);
        }
    }

    @Override
    public List<Order> findAllOrders(int limit, int offset) throws ServiceException {
        try {
            return ORDER_DAO.findAllOrders(limit, offset);
        } catch (OrionDatabaseException e) {
            throw new ServiceException("Database access error occurred while retrieving all orders", e);
        }
    }

    @Override
    public int countNumberAllOrders() throws ServiceException {
        int numberOrders;
        try {
            numberOrders = ORDER_DAO.countNumberAllOrders();
            logger.log(Level.DEBUG, "OrderService - counting the number of orders = {}", numberOrders);
        } catch (OrionDatabaseException e) {
            throw new ServiceException("Database access error occurred while counting all orders", e);
        }
        return numberOrders;
    }
}
