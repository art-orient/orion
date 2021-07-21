package com.art.orion.model.dao;

import com.art.orion.exception.OrionDatabaseException;
import com.art.orion.model.entity.Order;
import com.art.orion.model.entity.User;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

/**
 * The {@code OrderDao} interface for working with database tables orders and order_details
 *
 * @author Aliaksandr Artsikhovich
 * @version 1.0
 */
public interface OrderDao {

    /**
     * Saves an order
     *
     * @param order {@link Order} the order
     * @return the int the number of updated rows
     * @throws SQLException, OrionDatabaseException the SQLException and OrionDatabaseException exceptions
     */
    int addOrderToDatabase(Order order) throws SQLException, OrionDatabaseException;

    /**
     * Finds all user orders
     *
     * @param username {@link String} the username
     * @param limit number of orders per page
     * @param offset index of the first order on the page
     * @return {@link List} of {@link User} the list of found users
     * @throws OrionDatabaseException the OrionDatabaseException exception
     */
    List<Order> findUserOrders(String username, int limit, int offset) throws OrionDatabaseException;

    /**
     * Сounts the number of all user orders
     *
     * @param username {@link String} the username
     * @return the number of all orders
     * @throws OrionDatabaseException the OrionDatabaseException exception
     */
    int countNumberOrders(String username) throws OrionDatabaseException;

    /**
     * Removes the order by id
     *
     * @param orderId the order id
     * @throws SQLException, OrionDatabaseException the SQLException and OrionDatabaseException exceptions
     */
    void removeOrderById(int orderId) throws SQLException, OrionDatabaseException;

    /**
     * Finds all orders
     *
     * @param limit number of orders per page
     * @param offset index of the first order on the page
     * @return {@link List} of {@link Order} the list of found orders
     * @throws OrionDatabaseException the OrionDatabaseException exception
     */
    List<Order> findAllOrders(int limit, int offset) throws OrionDatabaseException;

    /**
     * Сounts the number of all orders
     *
     * @return the number of all orders
     * @throws OrionDatabaseException the OrionDatabaseException exception
     */
    int countNumberAllOrders() throws OrionDatabaseException;

    /**
     * Finds the order by id
     *
     * @param id the order id
     * @return {@link Optional} of {@link Order} the optional of the found order
     * @throws OrionDatabaseException the OrionDatabaseException exception
     */
    Optional<Order> findOrderById(int id) throws OrionDatabaseException;

    /**
     * Changes order data
     *
     * @param order {@link Order} the order
     * @return the boolean is the order updated
     * @throws OrionDatabaseException the OrionDatabaseException exception
     */
    boolean updateOrder(Order order) throws OrionDatabaseException;
}
