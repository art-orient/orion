package com.art.orion.model.dao.impl;

import com.art.orion.exception.OrionDatabaseException;
import com.art.orion.model.entity.Accessory;
import com.art.orion.model.entity.ProductDetails;
import com.art.orion.model.pool.ConnectionPool;

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
import java.util.Optional;

import static com.art.orion.model.dao.column.AccessoriesColumn.ACCESSORIES_ID;
import static com.art.orion.model.dao.column.AccessoriesColumn.ACCESSORIES_ID_INDEX;
import static com.art.orion.model.dao.column.AccessoriesColumn.ACTIVE;
import static com.art.orion.model.dao.column.AccessoriesColumn.ACTIVE_INDEX;
import static com.art.orion.model.dao.column.AccessoriesColumn.AVAILABILITY;
import static com.art.orion.model.dao.column.AccessoriesColumn.AVAILABILITY_INDEX;
import static com.art.orion.model.dao.column.AccessoriesColumn.BRAND;
import static com.art.orion.model.dao.column.AccessoriesColumn.BRAND_INDEX;
import static com.art.orion.model.dao.column.AccessoriesColumn.COST;
import static com.art.orion.model.dao.column.AccessoriesColumn.COST_INDEX;
import static com.art.orion.model.dao.column.AccessoriesColumn.DESCRIPTION_EN;
import static com.art.orion.model.dao.column.AccessoriesColumn.DESCRIPTION_EN_INDEX;
import static com.art.orion.model.dao.column.AccessoriesColumn.DESCRIPTION_RU;
import static com.art.orion.model.dao.column.AccessoriesColumn.DESCRIPTION_RU_INDEX;
import static com.art.orion.model.dao.column.AccessoriesColumn.IMAGE_PATH;
import static com.art.orion.model.dao.column.AccessoriesColumn.IMAGE_PATH_INDEX;
import static com.art.orion.model.dao.column.AccessoriesColumn.MODEL_NAME;
import static com.art.orion.model.dao.column.AccessoriesColumn.MODEL_NAME_INDEX;
import static com.art.orion.model.dao.column.AccessoriesColumn.TYPE_EN;
import static com.art.orion.model.dao.column.AccessoriesColumn.TYPE_EN_INDEX;
import static com.art.orion.model.dao.column.AccessoriesColumn.TYPE_RU;
import static com.art.orion.model.dao.column.AccessoriesColumn.TYPE_RU_INDEX;
import static com.art.orion.util.Constant.DATABASE_EXCEPTION;

/**
 * The {@code ProductDaoJdbc} class works with database table accessories
 *
 * @author Aliaksandr Artsikhovich
 * @version 1.0
 */
public class AccessoryJdbc {
    private static final Logger logger = LogManager.getLogger();
    private static final AccessoryJdbc INSTANCE = new AccessoryJdbc();
    private static final String INSERT_ACCESSORY = "INSERT INTO accessories " +
        "(type_Ru, type_En, brand, model_name, description_RU, description_EN, image_path, cost, availability, active) " +
        "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String FIND_ACCESSORY_BY_ID = "SELECT accessories_id, type_Ru, type_En, brand, model_name, " +
            "description_RU, description_EN, image_path, cost, availability, active " +
            "FROM accessories WHERE accessories_id = ?";
    private static final String SELECT = "SELECT accessories_id, type_Ru, type_En, brand, model_name, " +
            "description_RU, description_EN, image_path, cost, availability, active FROM accessories ";
    private static final String SELECT_ACTIVE_ACCESSORIES = SELECT + "WHERE active = 1 LIMIT ? OFFSET ?";
    private static final String SELECT_ALL_ACCESSORIES = SELECT + "LIMIT ? OFFSET ?";
    private static final String UPDATE_ACCESSORY = "UPDATE accessories SET type_Ru = ?, type_En = ?," +
            " brand = ?, model_name = ?, description_RU = ?, description_EN = ?, image_path = ?, cost = ?, " +
            "availability = ?, active = ? WHERE accessories_id = ?";
    private static final int UPDATE_ID_INDEX = 11;
    private static final Map<String, Integer> indices;

    static {
        indices = new HashMap<>();
        indices.put(BRAND, BRAND_INDEX);
        indices.put(MODEL_NAME, MODEL_NAME_INDEX);
        indices.put(DESCRIPTION_RU, DESCRIPTION_RU_INDEX);
        indices.put(DESCRIPTION_EN, DESCRIPTION_EN_INDEX);
        indices.put(IMAGE_PATH, IMAGE_PATH_INDEX);
        indices.put(COST, COST_INDEX);
        indices.put(ACTIVE, ACTIVE_INDEX);
    }

    private AccessoryJdbc() {
    }

    public static AccessoryJdbc getInstance() {
        return INSTANCE;
    }

    /**
     * Saves the accessory
     *
     * @param accessory {@link Accessory} the accessory
     * @throws OrionDatabaseException the OrionDatabaseException exception
     */
    public void addAccessoryToDatabase(Accessory accessory) throws OrionDatabaseException {
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(INSERT_ACCESSORY)){
            fillStatement(statement, accessory);
            statement.executeUpdate();
            logger.log(Level.INFO, () -> "The accessory is saved in the database");
        } catch (SQLException e) {
            throw new OrionDatabaseException(DATABASE_EXCEPTION, e);
        }
    }

    /**
     * Finds only active accessories or all accessories if the user role is an admin
     *
     * @param limit number of products per page
     * @param offset index of the first product on the page
     * @param isAdmin is the user role an admin
     * @return {@link List} of {@link Accessory} the list of found accessories
     * @throws OrionDatabaseException the OrionDatabaseException exception
     */
    public List<Accessory> searchAccessories(int limit, int offset, boolean isAdmin) throws OrionDatabaseException {
        List<Accessory> accessories = new ArrayList<>();
        String query = SELECT_ACTIVE_ACCESSORIES;
        if (isAdmin) {
            query = SELECT_ALL_ACCESSORIES;
        }
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, limit);
            statement.setInt(2, offset);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Accessory accessory = createAccessory(resultSet);
                    accessories.add(accessory);
                }
            }
            logger.log(Level.INFO, () -> "Accessory search completed successfully");
        } catch (SQLException e) {
            throw new OrionDatabaseException(DATABASE_EXCEPTION, e);
        }
        return accessories;
    }

    /**
     * Finds the accessory by id
     *
     * @param id the accessory id
     * @return {@link Optional} of {@link Accessory} the Optional of found accessory
     * @throws OrionDatabaseException the OrionDatabaseException exception
     */
    public Optional<Accessory> findAccessoryById(int id) throws OrionDatabaseException {
        Optional<Accessory> optionalAccessory;
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_ACCESSORY_BY_ID)) {
            statement.setInt(ACCESSORIES_ID_INDEX, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    Accessory accessory = createAccessory(resultSet);
                    optionalAccessory = Optional.of(accessory);
                } else {
                    throw new OrionDatabaseException(String.format("Accessory with id = %s is not found", id));
                }
            }
            logger.log(Level.DEBUG, () -> String.format("Accessory with id = %s got from the database", id));
        } catch (SQLException e) {
            throw new OrionDatabaseException(DATABASE_EXCEPTION, e);
        }
        return optionalAccessory;
    }

    /**
     * Updates the accessory
     *
     * @param accessory {@link Accessory} the accessory
     * @throws OrionDatabaseException the OrionDatabaseException exception
     */
    public void updateProduct(Accessory accessory) throws OrionDatabaseException {
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_ACCESSORY)){
            fillStatement(statement, accessory);
            statement.setInt(UPDATE_ID_INDEX, accessory.getAccessoryId());
            statement.executeUpdate();
            logger.log(Level.INFO, () -> "The accessory is updated in the database");
        } catch (SQLException e) {
            throw new OrionDatabaseException(DATABASE_EXCEPTION, e);
        }
    }

    /**
     * Creates the accessory
     *
     * @param resultSet {@link ResultSet} the accessory
     * @return {@link Accessory} the accessory
     * @throws SQLException, OrionDatabaseException the SQLException and OrionDatabaseException exceptions
     */
    private Accessory createAccessory(ResultSet resultSet) throws SQLException, OrionDatabaseException {
        int accessoryId = resultSet.getInt(ACCESSORIES_ID);
        String typeRu = resultSet.getString(TYPE_RU);
        String typeEn = resultSet.getString(TYPE_EN);
        ProductDetails productDetails = ProductDaoJdbc.createProductDetails(resultSet);
        int availability = resultSet.getInt(AVAILABILITY);
        logger.log(Level.DEBUG, () -> "Accessory creation completed successfully");
        return new Accessory(accessoryId, typeRu, typeEn, productDetails, availability);
    }

    /**
     * Fills the statement
     *
     * @param statement {@link PreparedStatement} the preparedStatement
     * @param accessory {@link Accessory} the accessory
     * @throws SQLException the SQLException exception
     */
    private void fillStatement(PreparedStatement statement, Accessory accessory) throws SQLException {
        statement.setString(TYPE_RU_INDEX - 1, accessory.getTypeRu());
        statement.setString(TYPE_EN_INDEX - 1, accessory.getTypeEn());
        ProductDetails productDetails = accessory.getProductDetails();
        ProductDaoJdbc.setProductDetailsInStatement(statement, productDetails, indices);
        statement.setInt(AVAILABILITY_INDEX - 1, accessory.getAvailability());
    }
}