package com.art.orion.model.service;

import com.art.orion.exception.ServiceException;
import com.art.orion.model.entity.Order;

import java.util.List;

public interface OrderService {

    boolean addOrderToDatabase(Order order) throws ServiceException;

    List<Order> findUserOrders(String username, int limit, int offset) throws ServiceException;

    int countNumberOrders(String username) throws ServiceException;

    void removeOrderById(int orderId) throws ServiceException;

    List<Order> findAllOrders(int limit, int offset) throws ServiceException;

    int countNumberAllOrders() throws ServiceException;
}
