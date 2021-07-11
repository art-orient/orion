package com.art.orion.model.dao.impl;

import com.art.orion.model.dao.OrionDatabaseException;
import com.art.orion.model.entity.Clothing;
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

import static com.art.orion.util.Constant.ACTIVE;
import static com.art.orion.util.Constant.BRAND;
import static com.art.orion.util.Constant.COST;
import static com.art.orion.util.Constant.DB_DESCRIPTION_EN;
import static com.art.orion.util.Constant.DB_DESCRIPTION_RU;
import static com.art.orion.util.Constant.DB_IMAGE_PATH;
import static com.art.orion.util.Constant.DB_MODEL_NAME;

public class ClothingJdbc {
    private static final Logger logger = LogManager.getLogger();
    private static final String SELECT_CLOTHING = "SELECT * FROM clothing WHERE active = 1 LIMIT ? OFFSET ?";
    private static final int CLOTHING_ID_INDEX = 1;
    private static final int TYPE_RU_INDEX = 2;
    private static final int TYPE_EN_INDEX = 3;
    private static final int COLOR_INDEX = 9;
    private static final String INSERT_CLOTHING = "INSERT INTO clothing " +
            "(type_Ru, type_En, brand, model_name, description_RU, description_EN, image_path, color, cost, active)" +
            " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String GET_CLOSING_BY_ID = "SELECT * FROM clothing WHERE clothing_id = ?";
    private static final Map<String, Integer> indices;

    static {
        indices = new HashMap<>();
        indices.put(BRAND, 4);
        indices.put(DB_MODEL_NAME, 5);
        indices.put(DB_DESCRIPTION_RU, 6);
        indices.put(DB_DESCRIPTION_EN, 7);
        indices.put(DB_IMAGE_PATH, 8);
        indices.put(COST, 10);
        indices.put(ACTIVE, 11);
    }

    public int addClothingToDatabase(Clothing clothing) {
        int numberOfRecords = 0;
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(INSERT_CLOTHING)) {
            statement.setString(TYPE_RU_INDEX - 1, clothing.getTypeRu());
            statement.setString(TYPE_EN_INDEX - 1, clothing.getTypeEn());
            statement.setString(COLOR_INDEX - 1, clothing.getColor());
            ProductDetails productDetails = clothing.getProductDetails();
            ProductDaoJdbc.setProductDetailsInStatement(statement, productDetails, indices);
            numberOfRecords = statement.executeUpdate();
        } catch (SQLException e) {
            logger.log(Level.ERROR, "Error writing to the database", e);
        }
        return numberOfRecords;
    }

    public List<Clothing> searchClothing(int limit, int offset) {
        List<Clothing> clothing = new ArrayList<>();
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_CLOTHING)) {
            statement.setInt(1, limit);
            statement.setInt(2, offset);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    clothing.add(createClothing(resultSet));
                }
            }
        } catch (SQLException | OrionDatabaseException e) {
            logger.log(Level.ERROR, e);
        }
        return clothing;
    }

    private Clothing createClothing(ResultSet resultSet) throws SQLException, OrionDatabaseException {
        int clothingId = resultSet.getInt(CLOTHING_ID_INDEX);
        String typeRu = resultSet.getString(TYPE_RU_INDEX);
        String typeEn = resultSet.getString(TYPE_EN_INDEX);
        String color = resultSet.getString(COLOR_INDEX);
        ProductDetails productDetails = ProductDaoJdbc.createProductDetails(resultSet);
        return new Clothing(clothingId, typeRu, typeEn, productDetails, color);
    }

    public Clothing getClothingById(int id) {
        Clothing clothing = null;
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(GET_CLOSING_BY_ID)) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    clothing = createClothing(resultSet);
                }
            }
        } catch (SQLException |OrionDatabaseException e) {
            logger.log(Level.ERROR, e);
        }
        return clothing;
    }
}
