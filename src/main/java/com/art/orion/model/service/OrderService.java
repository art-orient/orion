package com.art.orion.model.service;

import com.art.orion.exception.ServiceException;
import com.art.orion.model.entity.Order;

import java.util.List;
import java.util.Optional;

/**
 * The {@code OrderService} interface represents order service
 *
 * @author Aliaksandr Artsikhovich
 * @version 1.0
 */
public interface OrderService {

    /**
     * Adds a new order to the database
     *
     * @param order {@link Order} the new order
     * @return the boolean is order added
     * @throws ServiceException the service exception
     */
    boolean addOrderToDatabase(Order order) throws ServiceException;

    /**
     * Finds all user orders
     *
     * @param username {@link String} the username of user
     * @param limit number of orders per page
     * @param offset index of the first order on the page
     * @return {@link List} of {@link Order} the list of found orders
     * @throws ServiceException the service exception
     */
    List<Order> findUserOrders(String username, int limit, int offset) throws ServiceException;

    /**
     * Finds the number of orders of user by username
     *
     * @param username {@link String} the username of user
     * @return the number of orders of the user
     * @throws ServiceException the service exception
     */
    int countNumberOrders(String username) throws ServiceException;

    /**
     * Removed the order by id
     *
     * @param orderId the order id
     * @throws ServiceException the service exception
     */
    void removeOrderById(int orderId) throws ServiceException;

    /**
     * Finds all orders
     *
     * @param limit number of orders per page
     * @param offset index of the first order on the page
     * @return {@link List} of {@link Order} the list of found orders
     * @throws ServiceException the service exception
     */
    List<Order> findAllOrders(int limit, int offset) throws ServiceException;

    /**
     * Finds the number of orders of all users
     *
     * @return the number of all orders
     * @throws ServiceException the service exception
     */
    int countNumberAllOrders() throws ServiceException;

    /**
     * Finds the order by id
     *
     * @param id the id of order
     * @return {@link Optional} of {@link Order} received from database
     * @throws ServiceException the service exception
     */
    Optional<Order> findOrderById(int id) throws ServiceException;

    /**
     * Edits the order data
     *
     * @param order {@link Order} the order
     * @return the boolean is order updated
     * @throws ServiceException the service exception
     */
    boolean updateOrder(Order order) throws ServiceException;
}
