package com.art.orion.model.service;

import com.art.orion.model.dao.OrderDao;
import com.art.orion.model.dao.impl.OrderDaoJdbc;
import com.art.orion.model.entity.Order;

public class OrderService {
    private static final OrderDao ORDER_DAO = new OrderDaoJdbc();

    public static boolean addOrderToDatabase(Order order) {
        return ORDER_DAO.addOrderToDatabase(order) > 0;
    }
}
