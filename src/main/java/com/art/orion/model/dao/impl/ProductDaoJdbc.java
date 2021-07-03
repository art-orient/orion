package com.art.orion.model.dao.impl;

import com.art.orion.model.dao.ProductDao;
import com.art.orion.model.entity.Accessory;
import com.art.orion.model.pool.ConnectionPool;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ProductDaoJdbc implements ProductDao {
    private static final Logger logger = LogManager.getLogger();
    private static final String INSERT_ACCESSORY = "INSERT INTO accessories " +
            "(type_Ru, type_En, brand, model_name, description_RU, description_EN, image_path, cost, availability, active) " +
            "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private static final int TYPE_RU_INDEX = 1;
    private static final int TYPE_EN_INDEX = 2;
    private static final int BRAND_INDEX = 3;
    private static final int MODEL_NAME_INDEX = 4;
    private static final int DESCRIPTION_RU_INDEX = 5;
    private static final int DESCRIPTION_EN_INDEX = 6;
    private static final int IMAGE_PATH_INDEX = 7;
    private static final int COST_INDEX = 8;
    private static final int AVAILABILITY_INDEX = 9;
    private static final int ACTIVE_INDEX = 10;

    @Override
    public int createProduct(Accessory accessory) {
        int numberOfRecords = 0;
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
                PreparedStatement statement = connection.prepareStatement(INSERT_ACCESSORY)) {
            statement.setString(TYPE_RU_INDEX, accessory.getTypeRu());
            statement.setString(TYPE_EN_INDEX, accessory.getTypeEn());
            statement.setString(BRAND_INDEX, accessory.getProductDetails().getBrand());
            statement.setString(MODEL_NAME_INDEX, accessory.getProductDetails().getModelName());
            statement.setString(DESCRIPTION_RU_INDEX, accessory.getProductDetails().getDescriptionRu());
            statement.setString(DESCRIPTION_EN_INDEX, accessory.getProductDetails().getDescriptionEn());
            statement.setString(IMAGE_PATH_INDEX, accessory.getProductDetails().getImgPath());
            statement.setBigDecimal(COST_INDEX, accessory.getProductDetails().getCost());
            statement.setInt(AVAILABILITY_INDEX, accessory.getAvailability());
            statement.setBoolean(ACTIVE_INDEX, accessory.getProductDetails().isActive());
            numberOfRecords = statement.executeUpdate();
        } catch (SQLException e) {
            logger.log(Level.ERROR, "Error writing to the database", e);
        }
        return numberOfRecords;
    }
}
