package com.art.orion.model.dao;

import com.art.orion.exception.OrionDatabaseException;
import com.art.orion.model.entity.Order;
import com.art.orion.exception.ServiceException;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface OrderDao {

    int addOrderToDatabase(Order order) throws SQLException, OrionDatabaseException;

    List<Order> findUserOrders(String username, int limit, int offset)
                                throws OrionDatabaseException, ServiceException;

    int countNumberOrders(String username) throws OrionDatabaseException;

    void removeOrderById(int orderId) throws SQLException, OrionDatabaseException;

    List<Order> findAllOrders(int limit, int offset) throws OrionDatabaseException;

    int countNumberAllOrders() throws OrionDatabaseException;

    Optional<Order> findOrderById(int id) throws OrionDatabaseException;

    boolean updateOrder(Order order) throws OrionDatabaseException;
}
