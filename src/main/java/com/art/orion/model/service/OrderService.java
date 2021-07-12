package com.art.orion.model.service;

import com.art.orion.model.dao.OrderDao;
import com.art.orion.model.dao.OrionDatabaseException;
import com.art.orion.model.dao.impl.OrderDaoJdbc;
import com.art.orion.model.entity.Order;

import java.sql.SQLException;

public class OrderService {
    private static final OrderDao ORDER_DAO = new OrderDaoJdbc();

    private OrderService() {
    }

    public static boolean addOrderToDatabase(Order order) throws ServiceException {
        try {
            return ORDER_DAO.addOrderToDatabase(order) > 0;
        } catch (SQLException | OrionDatabaseException e) {
            throw new ServiceException("Order is not added in the database", e);
        }
    }
}
