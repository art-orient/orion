package com.art.orion.model.dao.impl;

import com.art.orion.exception.OrionDatabaseException;
import com.art.orion.model.entity.Accessory;
import com.art.orion.model.entity.ProductDetails;
import com.art.orion.model.pool.ConnectionPool;

import com.art.orion.exception.ServiceException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.art.orion.util.Constant.ACTIVE;
import static com.art.orion.util.Constant.BRAND;
import static com.art.orion.util.Constant.COST;
import static com.art.orion.util.Constant.DATABASE_EXCEPTION;
import static com.art.orion.util.Constant.DB_DESCRIPTION_EN;
import static com.art.orion.util.Constant.DB_DESCRIPTION_RU;
import static com.art.orion.util.Constant.DB_IMAGE_PATH;
import static com.art.orion.util.Constant.DB_MODEL_NAME;

public class AccessoryJdbc {
    private static final Logger logger = LogManager.getLogger();
    private static final AccessoryJdbc INSTANCE = new AccessoryJdbc();
    private static final String INSERT_ACCESSORY = "INSERT INTO accessories " +
        "(type_Ru, type_En, brand, model_name, description_RU, description_EN, image_path, cost, availability, active) " +
        "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String GET_ACCESSORY_BY_ID = "SELECT accessories_id, type_Ru, type_En, brand, model_name, " +
            "description_RU, description_EN, image_path, cost, availability, active " +
            "FROM accessories WHERE accessories_id = ?";
    private static final int ACCESSORIES_ID_INDEX = 1;
    private static final int TYPE_RU_INDEX = 2;
    private static final int TYPE_EN_INDEX = 3;
    private static final int AVAILABILITY_INDEX = 10;
    private static final String SELECT_ACCESSORIES = "SELECT accessories_id, type_Ru, type_En, brand, model_name, " +
            "description_RU, description_EN, image_path, cost, availability, active " +
            "FROM accessories WHERE active = 1 LIMIT ? OFFSET ?";
    private static final String SELECT_ALL_ACCESSORIES = "SELECT accessories_id, type_Ru, type_En, brand, model_name, " +
            "description_RU, description_EN, image_path, cost, availability, active " +
            "FROM accessories LIMIT ? OFFSET ?";
    private static final Map<String, Integer> indices;

    static {
        indices = new HashMap<>();
        indices.put(BRAND, 4);
        indices.put(DB_MODEL_NAME, 5);
        indices.put(DB_DESCRIPTION_RU, 6);
        indices.put(DB_DESCRIPTION_EN, 7);
        indices.put(DB_IMAGE_PATH, 8);
        indices.put(COST, 9);
        indices.put(ACTIVE, 11);
    }

    private AccessoryJdbc() {
    }

    public static AccessoryJdbc getInstance() {
        return INSTANCE;
    }

    public void addAccessoryToDatabase(Accessory accessory) throws SQLException, OrionDatabaseException {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = ConnectionPool.INSTANCE.getConnection();
            connection.setAutoCommit(false);
            statement = connection.prepareStatement(INSERT_ACCESSORY);
            statement.setString(TYPE_RU_INDEX - 1, accessory.getTypeRu());
            statement.setString(TYPE_EN_INDEX - 1, accessory.getTypeEn());
            ProductDetails productDetails = accessory.getProductDetails();
            ProductDaoJdbc.setProductDetailsInStatement(statement, productDetails, indices);
            statement.setInt(AVAILABILITY_INDEX - 1, accessory.getAvailability());
            statement.executeUpdate();
            connection.commit();
            logger.log(Level.INFO, () -> "The accessory is saved in the database");
        } catch (SQLException e) {
            connection.rollback();
            throw new OrionDatabaseException(DATABASE_EXCEPTION, e);
        } finally {
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.setAutoCommit(true);
                connection.close();
            }
        }
    }

    public List<Accessory> searchAccessories(int limit, int offset, boolean isAdmin) throws OrionDatabaseException {
        List<Accessory> accessories = new ArrayList<>();
        String query = SELECT_ACCESSORIES;
        if (isAdmin) {
            query = SELECT_ALL_ACCESSORIES;
        }
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, limit);
            statement.setInt(2, offset);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    accessories.add(createAccessory(resultSet));
                }
            }
            logger.log(Level.INFO, () -> "Accessory search completed successfully");
        } catch (SQLException e) {
            throw new OrionDatabaseException(DATABASE_EXCEPTION, e);
        }
        return accessories;
    }

    private Accessory createAccessory(ResultSet resultSet) throws SQLException, OrionDatabaseException {
        int accessoryId = resultSet.getInt(ACCESSORIES_ID_INDEX);
        String typeRu = resultSet.getString(TYPE_RU_INDEX);
        String typeEn = resultSet.getString(TYPE_EN_INDEX);
        ProductDetails productDetails = ProductDaoJdbc.createProductDetails(resultSet);
        int availability = resultSet.getInt(AVAILABILITY_INDEX);
        logger.log(Level.DEBUG, () -> "Accessory creation completed successfully");
        return new Accessory(accessoryId, typeRu, typeEn, productDetails, availability);
    }

    public Accessory getAccessoryById(int id) throws ServiceException, OrionDatabaseException {
        Accessory accessory;
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(GET_ACCESSORY_BY_ID)) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    accessory = createAccessory(resultSet);
                } else {
                    throw new ServiceException(String.format("Accessory with id = %s is not found", id));
                }
            }
            logger.log(Level.DEBUG, () -> String.format("Accessory with id = %s got from the database", id));
        } catch (SQLException e) {
            throw new OrionDatabaseException(DATABASE_EXCEPTION, e);
        }
        return accessory;
    }
}
