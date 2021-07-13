package com.art.orion.model.dao;

import com.art.orion.model.entity.Order;
import com.art.orion.model.service.ServiceException;

import java.sql.SQLException;
import java.util.List;

public interface OrderDao {

    int addOrderToDatabase(Order order) throws SQLException, OrionDatabaseException;

    List<Order> getUserOrders(String username, int limit, int offset)
                                throws OrionDatabaseException, ServiceException;

    int countNumberOrders(String username) throws OrionDatabaseException;

    void removeOrderById(int orderId) throws SQLException, OrionDatabaseException;
}
