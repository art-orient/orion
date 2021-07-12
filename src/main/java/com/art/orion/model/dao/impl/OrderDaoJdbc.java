package com.art.orion.model.dao.impl;

import com.art.orion.model.dao.OrderDao;
import com.art.orion.model.dao.OrionDatabaseException;
import com.art.orion.model.entity.Order;
import com.art.orion.model.pool.ConnectionPool;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static com.art.orion.util.Constant.DATABASE_EXCEPTION;

public class OrderDaoJdbc implements OrderDao {
    private static final Logger logger = LogManager.getLogger();
    private static final String INSERT_ORDER = "INSERT INTO orders " +
            "(username, order_date, order_cost, confirmation_status) VALUES (?, ?, ?, ?)";
    private static final int ORDER_ID_INDEX = 1;
    private static final int USERNAME_INDEX = 2;
    private static final int ORDER_DATE_INDEX = 3;
    private static final int ORDER_COST_INDEX = 4;
    private static final int CONFIRMATION_STATUS_INDEX = 5;

    @Override
    public int addOrderToDatabase(Order order) throws SQLException, OrionDatabaseException {
        int orderId = 0;
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = ConnectionPool.INSTANCE.getConnection();
            connection.setAutoCommit(false);
            statement = connection.prepareStatement(INSERT_ORDER, Statement.RETURN_GENERATED_KEYS);
            statement.setString(USERNAME_INDEX - 1, order.getUsername());
            statement.setDate(ORDER_DATE_INDEX - 1, new Date(order.getOrderDate().getTime()));
            statement.setBigDecimal(ORDER_COST_INDEX - 1, order.getOrderCost());
            statement.setBoolean(CONFIRMATION_STATUS_INDEX - 1, order.isConfirmed());
            statement.executeUpdate();
            connection.commit();
            connection.setAutoCommit(true);
            logger.log(Level.INFO, () -> "The accessory is saved in the database");
            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                orderId = resultSet.getInt(1);
                logger.log(Level.INFO, String.format("orderId = %s", orderId));
            }
        } catch (SQLException e) {
            connection.rollback();
            throw new OrionDatabaseException(DATABASE_EXCEPTION, e);
        } finally {
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
        return orderId;
    }
}
