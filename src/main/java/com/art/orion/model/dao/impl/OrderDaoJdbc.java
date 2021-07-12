package com.art.orion.model.dao.impl;

import com.art.orion.model.dao.OrderDao;
import com.art.orion.model.dao.OrionDatabaseException;
import com.art.orion.model.entity.Accessory;
import com.art.orion.model.entity.Clothing;
import com.art.orion.model.entity.Order;
import com.art.orion.model.entity.Shoes;
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
import java.util.Map;

import static com.art.orion.util.Constant.DATABASE_EXCEPTION;

public class OrderDaoJdbc implements OrderDao {
    private static final Logger logger = LogManager.getLogger();
    private static final int INVALID_ID = -1;
    private static final String INSERT_ORDER = "INSERT INTO orders " +
            "(username, order_date, order_cost, confirmation_status) VALUES (?, ?, ?, ?)";
    private static final int INSERT_ORDER_USERNAME_INDEX = 1;
    private static final int INSERT_ORDER_DATE_INDEX = 2;
    private static final int INSERT_ORDER_COST_INDEX = 3;
    private static final int INSERT_ORDER_STATUS_INDEX = 4;
    private static final String INSERT_ORDER_DETAILS = "INSERT INTO order_details VALUES (?, ?, ?, ?, ?)";
    private static final int INSERT_DETAILS_ORDER_ID_INDEX = 1;
    private static final int INSERT_DETAILS_CATEGORY_INDEX = 2;
    private static final int INSERT_DETAILS_PRODUCT_ID_INDEX = 3;
    private static final int INSERT_DETAILS_NUMBER_PRODUCTS_INDEX = 4;
    private static final int INSERT_DETAILS_COST_INDEX = 5;
    private static final int SHOES_CATEGORY_NUMBER = 1;
    private static final int CLOTHING_CATEGORY_NUMBER = 2;
    private static final int ACCESSORIES_CATEGORY_NUMBER = 3;

    @Override
    public int addOrderToDatabase(Order order) throws SQLException, OrionDatabaseException {
        int orderId;
        Connection connection = null;
        try {
            connection = ConnectionPool.INSTANCE.getConnection();
            connection.setAutoCommit(false);
            orderId = saveOrder(connection, order);
            saveOrderDetails(connection, order, orderId);
            connection.commit();
            connection.setAutoCommit(true);
            logger.log(Level.INFO, () -> "The order is saved in the database");
        } catch (SQLException e) {
            connection.rollback();
            throw new OrionDatabaseException(DATABASE_EXCEPTION, e);
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
        return orderId;
    }

    private int saveOrder(Connection connection, Order order) throws SQLException {
        int orderId = INVALID_ID;
        try (PreparedStatement statement = connection.prepareStatement(INSERT_ORDER, Statement.RETURN_GENERATED_KEYS)){
            statement.setString(INSERT_ORDER_USERNAME_INDEX, order.getUsername());
            statement.setDate(INSERT_ORDER_DATE_INDEX, new Date(order.getOrderDate().getTime()));
            statement.setBigDecimal(INSERT_ORDER_COST_INDEX, order.getOrderCost());
            statement.setBoolean(INSERT_ORDER_STATUS_INDEX, order.isConfirmed());
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                orderId = resultSet.getInt(1);
                logger.log(Level.DEBUG, "orderId = {}", orderId);
            }
        }
        return orderId;
    }

    private void saveOrderDetails(Connection connection, Order order, int orderId) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(INSERT_ORDER_DETAILS,
                                                                        Statement.RETURN_GENERATED_KEYS)) {
            for (Map.Entry<Object, Long> entry : order.getProductsAndQuantity().entrySet()) {
                Object product = entry.getKey();
                int numberProducts = entry.getValue().intValue();
                statement.setInt(INSERT_DETAILS_ORDER_ID_INDEX, orderId);
                if (product instanceof Shoes) {
                    Shoes shoes = (Shoes) product;
                    statement.setInt(INSERT_DETAILS_CATEGORY_INDEX, SHOES_CATEGORY_NUMBER);
                    statement.setInt(INSERT_DETAILS_PRODUCT_ID_INDEX, shoes.getShoesId());
                    statement.setBigDecimal(INSERT_DETAILS_COST_INDEX, shoes.getProductDetails().getCost());
                } else if (product instanceof Clothing) {
                    Clothing clothing = (Clothing) product;
                    statement.setInt(INSERT_DETAILS_CATEGORY_INDEX, CLOTHING_CATEGORY_NUMBER);
                    statement.setInt(INSERT_DETAILS_PRODUCT_ID_INDEX, clothing.getClothingId());
                    statement.setBigDecimal(INSERT_DETAILS_COST_INDEX, clothing.getProductDetails().getCost());
                } else if (product instanceof Accessory) {
                    Accessory accessory = (Accessory) product;
                    statement.setInt(INSERT_DETAILS_CATEGORY_INDEX, ACCESSORIES_CATEGORY_NUMBER);
                    statement.setInt(INSERT_DETAILS_PRODUCT_ID_INDEX, accessory.getAccessoryId());
                    statement.setBigDecimal(INSERT_DETAILS_COST_INDEX, accessory.getProductDetails().getCost());
                }
                statement.setInt(INSERT_DETAILS_NUMBER_PRODUCTS_INDEX, numberProducts);
                statement.executeUpdate();
            }
        }
    }
}
