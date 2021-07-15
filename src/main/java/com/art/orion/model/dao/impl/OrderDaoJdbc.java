package com.art.orion.model.dao.impl;

import com.art.orion.model.dao.OrderDao;
import com.art.orion.exception.OrionDatabaseException;
import com.art.orion.model.entity.Accessory;
import com.art.orion.model.entity.Clothing;
import com.art.orion.model.entity.Order;
import com.art.orion.model.entity.Shoes;
import com.art.orion.model.pool.ConnectionPool;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static com.art.orion.model.dao.column.OrdersColumn.ORDER_COST;
import static com.art.orion.model.dao.column.OrdersColumn.ORDER_DATE;
import static com.art.orion.model.dao.column.OrdersColumn.ORDER_ID;
import static com.art.orion.model.dao.column.OrdersColumn.ORDER_STATUS;
import static com.art.orion.model.dao.column.OrdersDetailsColumn.CATEGORY_INDEX;
import static com.art.orion.model.dao.column.OrdersDetailsColumn.COST_INDEX;
import static com.art.orion.model.dao.column.OrdersDetailsColumn.NUMBER_PRODUCTS;
import static com.art.orion.model.dao.column.OrdersDetailsColumn.NUMBER_PRODUCTS_INDEX;
import static com.art.orion.model.dao.column.OrdersDetailsColumn.ORDER_ID_INDEX;
import static com.art.orion.model.dao.column.OrdersDetailsColumn.PRODUCTS_COST;
import static com.art.orion.model.dao.column.OrdersDetailsColumn.PRODUCT_CATEGORY;
import static com.art.orion.model.dao.column.OrdersDetailsColumn.PRODUCT_ID;
import static com.art.orion.model.dao.column.OrdersDetailsColumn.PRODUCT_ID_INDEX;
import static com.art.orion.util.Constant.DATABASE_EXCEPTION;
import static com.art.orion.util.Constant.USERNAME;

public class OrderDaoJdbc implements OrderDao {
    private static final Logger logger = LogManager.getLogger();
    private static final OrderDaoJdbc INSTANCE = new OrderDaoJdbc();
    private static final int INVALID_ID = -1;
    private static final String INSERT_ORDER = "INSERT INTO orders " +
            "(username, order_date, order_cost, confirmation_status) VALUES (?, ?, ?, ?)";
    private static final int INSERT_ORDER_USERNAME = 1;
    private static final int INSERT_ORDER_DATE = 2;
    private static final int INSERT_ORDER_COST = 3;
    private static final int INSERT_ORDER_STATUS = 4;
    private static final String INSERT_ORDER_DETAILS = "INSERT INTO order_details VALUES (?, ?, ?, ?, ?)";
    private static final String SELECT_USER_ORDERS = "SELECT order_id, username, order_date, order_cost, " +
            "confirmation_status FROM orders WHERE username = ? LIMIT ? OFFSET ?";
    private static final int SELECT_USER_ORDERS_USERNAME = 1;
    private static final int SELECT_USER_ORDERS_LIMIT = 2;
    private static final int SELECT_USER_ORDERS_OFFSET = 3;
    private static final String SELECT_ORDER_DETAILS = "SELECT order_id, product_category, product_id, " +
            "number_of_products, products_cost FROM order_details WHERE order_id = ?";
    private static final String COUNT_ORDERS = "SELECT count(*) FROM orders WHERE username = ?";
    private static final String REMOVE_ORDER_DETAILS = "DELETE FROM order_details WHERE order_id = ?";
    private static final String REMOVE_ORDER = "DELETE FROM orders WHERE order_id = ?";
    private static final int SHOES_CATEGORY_NUMBER = 1;
    private static final int CLOTHING_CATEGORY_NUMBER = 2;
    private static final int ACCESSORIES_CATEGORY_NUMBER = 3;
    private static final String SHOES_IMAGE_PATH_PREFIX = "images/shoes/";
    private static final String CLOTHING_IMAGE_PATH_PREFIX = "images/clothing/";
    private static final String ACCESSORIES_IMAGE_PATH_PREFIX = "images/accessories/";

    private OrderDaoJdbc() {
    }

    public static OrderDaoJdbc getInstance() {
        return INSTANCE;
    }

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
            logger.log(Level.INFO, () -> "The order is saved in the database");
        } catch (SQLException e) {
            connection.rollback();
            throw new OrionDatabaseException(DATABASE_EXCEPTION, e);
        } finally {
            if (connection != null) {
                connection.setAutoCommit(true);
                connection.close();
            }
        }
        return orderId;
    }

    private int saveOrder(Connection connection, Order order) throws SQLException {
        int orderId = INVALID_ID;
        try (PreparedStatement statement = connection.prepareStatement(INSERT_ORDER, Statement.RETURN_GENERATED_KEYS)){
            statement.setString(INSERT_ORDER_USERNAME, order.getUsername());
            statement.setDate(INSERT_ORDER_DATE, new java.sql.Date(order.getOrderDate().getTime()));
            statement.setBigDecimal(INSERT_ORDER_COST, order.getOrderCost());
            statement.setBoolean(INSERT_ORDER_STATUS, order.isConfirmed());
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
                statement.setInt(ORDER_ID_INDEX, orderId);
                if (product instanceof Shoes) {
                    Shoes shoes = (Shoes) product;
                    statement.setInt(CATEGORY_INDEX, SHOES_CATEGORY_NUMBER);
                    statement.setInt(PRODUCT_ID_INDEX, shoes.getShoesId());
                    statement.setBigDecimal(COST_INDEX, shoes.getProductDetails().getCost());
                } else if (product instanceof Clothing) {
                    Clothing clothing = (Clothing) product;
                    statement.setInt(CATEGORY_INDEX, CLOTHING_CATEGORY_NUMBER);
                    statement.setInt(PRODUCT_ID_INDEX, clothing.getClothingId());
                    statement.setBigDecimal(COST_INDEX, clothing.getProductDetails().getCost());
                } else if (product instanceof Accessory) {
                    Accessory accessory = (Accessory) product;
                    statement.setInt(CATEGORY_INDEX, ACCESSORIES_CATEGORY_NUMBER);
                    statement.setInt(PRODUCT_ID_INDEX, accessory.getAccessoryId());
                    statement.setBigDecimal(COST_INDEX, accessory.getProductDetails().getCost());
                }
                statement.setInt(NUMBER_PRODUCTS_INDEX, numberProducts);
                statement.executeUpdate();
            }
        }
    }

    public List<Order> findUserOrders(String username, int limit, int offset)
            throws OrionDatabaseException {
        List<Order> orders = new ArrayList<>();
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
        PreparedStatement statement = connection.prepareStatement(SELECT_USER_ORDERS)) {
            statement.setString(SELECT_USER_ORDERS_USERNAME, username);
            statement.setInt(SELECT_USER_ORDERS_LIMIT, limit);
            statement.setInt(SELECT_USER_ORDERS_OFFSET, offset);
            ResultSet ordersSet = statement.executeQuery();
            while (ordersSet.next()) {
                Order order = createOrder(connection, ordersSet);
                orders.add(order);
            }
        } catch (SQLException e) {
            throw new OrionDatabaseException(DATABASE_EXCEPTION, e);
        }
        return orders;
    }

    private Order createOrder(Connection connection, ResultSet ordersSet)
            throws SQLException, OrionDatabaseException {
        int orderId = ordersSet.getInt(ORDER_ID);
        try (PreparedStatement statement = connection.prepareStatement(SELECT_ORDER_DETAILS)) {
            statement.setInt(1, orderId);
            ResultSet productSet = statement.executeQuery();
            Map<Object, Long> products = createProducts(productSet);
            String username = ordersSet.getString(USERNAME);
            Date orderDate = ordersSet.getDate(ORDER_DATE);
            BigDecimal cost = ordersSet.getBigDecimal(ORDER_COST);
            boolean status = ordersSet.getBoolean(ORDER_STATUS);
            return new Order(orderId, username, orderDate, products, cost, status);
        }
    }

    private Map<Object, Long> createProducts(ResultSet productSet)
                                            throws SQLException, OrionDatabaseException {
        Map<Object, Long> products = new HashMap<>();
        Object product = null;
        while (productSet.next()) {
            int categoryNumber = productSet.getInt(PRODUCT_CATEGORY);
            int productId = productSet.getInt(PRODUCT_ID);
            BigDecimal productCost = productSet.getBigDecimal(PRODUCTS_COST);
            Long numberProducts = (long) productSet.getInt(NUMBER_PRODUCTS);
            switch (categoryNumber) {
                case SHOES_CATEGORY_NUMBER -> {
                    Optional<Shoes> optionalShoes = ShoesJdbc.getInstance().findShoesById(productId);
                    if (optionalShoes.isPresent()) {
                        Shoes shoes = optionalShoes.get();
                        String imagePath = shoes.getProductDetails().getImgPath();
                        shoes.getProductDetails().setImgPath(SHOES_IMAGE_PATH_PREFIX + imagePath);
                        shoes.getProductDetails().setCost(productCost);
                        product = shoes;
                    }
                }
                case CLOTHING_CATEGORY_NUMBER -> {
                    Optional<Clothing> optionalClothing = ClothingJdbc.getInstance().findClothingById(productId);
                    if (optionalClothing.isPresent()) {
                        Clothing clothing = optionalClothing.get();
                        String imagePath = clothing.getProductDetails().getImgPath();
                        clothing.getProductDetails().setImgPath(CLOTHING_IMAGE_PATH_PREFIX + imagePath);
                        clothing.getProductDetails().setCost(productCost);
                        product = clothing;
                    }
                }
                case ACCESSORIES_CATEGORY_NUMBER -> {
                    Optional<Accessory> optionalAccessory = AccessoryJdbc.getInstance().findAccessoryById(productId);
                    if (optionalAccessory.isPresent()) {
                        Accessory accessory = optionalAccessory.get();
                        String imagePath = accessory.getProductDetails().getImgPath();
                        accessory.getProductDetails().setImgPath(ACCESSORIES_IMAGE_PATH_PREFIX + imagePath);
                        accessory.getProductDetails().setCost(productCost);
                        product = accessory;
                    }
                }
                default -> throw new OrionDatabaseException("Invalid number of product category");
            }
            products.put(product, numberProducts);
        }
        return products;
    }

    public int countNumberOrders(String username) throws OrionDatabaseException {
        int number = 0;
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(COUNT_ORDERS)) {
            statement.setString(1, username);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                number = resultSet.getInt(1);
            }
            logger.log(Level.DEBUG, "Counting the number of orders = {}", number);
        } catch (SQLException e) {
            throw new OrionDatabaseException(DATABASE_EXCEPTION, e);
        }
        return number;
    }

    public void removeOrderById(int orderId) throws SQLException, OrionDatabaseException {
        Connection connection = null;
        try {
            connection = ConnectionPool.INSTANCE.getConnection();
            connection.setAutoCommit(false);
            removeOrderData(connection, orderId, REMOVE_ORDER_DETAILS);
            removeOrderData(connection, orderId, REMOVE_ORDER);
            connection.commit();
            logger.log(Level.INFO, () -> "The order is removed from the database");
        } catch (SQLException e) {
            connection.rollback();
            throw new OrionDatabaseException(DATABASE_EXCEPTION, e);
        } finally {
            if (connection != null) {
                connection.setAutoCommit(true);
                connection.close();
            }
        }
    }

    private void removeOrderData(Connection connection, int orderId, String query) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, orderId);
            statement.executeUpdate();
        }
    }
}
