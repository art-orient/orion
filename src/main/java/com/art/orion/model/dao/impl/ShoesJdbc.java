package com.art.orion.model.dao.impl;

import com.art.orion.controller.command.util.TextHandler;
import com.art.orion.model.entity.Clothing;
import com.art.orion.model.entity.ProductDetails;
import com.art.orion.model.entity.Shoes;
import com.art.orion.model.pool.ConnectionPool;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ShoesJdbc {
    private static final Logger logger = LogManager.getLogger();
    private static final String SELECT_SHOES = "SELECT * FROM shoes WHERE active = 1 LIMIT ? OFFSET ?";
    private static final int SHOES_ID_INDEX = 1;
    private static final int TYPE_RU_INDEX = 2;
    private static final int TYPE_EN_INDEX = 3;
    private static final int BRAND_INDEX = 4;
    private static final int MODEL_NAME_INDEX = 5;
    private static final int DESCRIPTION_RU_INDEX = 6;
    private static final int DESCRIPTION_EN_INDEX = 7;
    private static final int IMAGE_PATH_INDEX = 8;
    private static final int COLOR_INDEX = 9;
    private static final int COST_INDEX = 10;
    private static final int ACTIVE_INDEX = 11;
    private static final String COUNT_SHOES = "SELECT count(*) FROM shoes WHERE active = 1";
    private static final String INSERT_SHOES = "INSERT INTO shoes " +
            "(type_Ru, type_En, brand, model_name, description_RU, description_EN, image_path, color, cost, active)" +
            " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String GET_SHOES_BY_ID = "SELECT * FROM shoes WHERE shoes_id = ?";

    public int addShoesToDatabase(Shoes shoes) {
        int numberOfRecords = 0;
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(INSERT_SHOES)) {
            statement.setString(TYPE_RU_INDEX - 1, shoes.getTypeRu());
            statement.setString(TYPE_EN_INDEX - 1, shoes.getTypeEn());
            statement.setString(BRAND_INDEX - 1, shoes.getProductDetails().getBrand());
            statement.setString(MODEL_NAME_INDEX - 1, shoes.getProductDetails().getModelName());
            statement.setString(DESCRIPTION_RU_INDEX - 1,
                    TextHandler.createTextFromList(shoes.getProductDetails().getDescriptionRu()));
            statement.setString(DESCRIPTION_EN_INDEX - 1,
                    TextHandler.createTextFromList(shoes.getProductDetails().getDescriptionEn()));
            statement.setString(IMAGE_PATH_INDEX - 1, shoes.getProductDetails().getImgPath());
            statement.setString(COLOR_INDEX - 1, shoes.getColor());
            statement.setBigDecimal(COST_INDEX - 1, shoes.getProductDetails().getCost());
            statement.setBoolean(ACTIVE_INDEX - 1, shoes.getProductDetails().isActive());
            numberOfRecords = statement.executeUpdate();
        } catch (SQLException e) {
            logger.log(Level.ERROR, "Error writing to the database", e);
        }
        return numberOfRecords;
    }

    public List<Shoes> searchShoes(int limit, int offset) {
        List<Shoes> products = new ArrayList<>();
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_SHOES)) {
            statement.setInt(1, limit);
            statement.setInt(2, offset);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    products.add(createShoes(resultSet));
                }
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, e);
        }
        return products;
    }

    private Shoes createShoes(ResultSet resultSet) throws SQLException {
        int shoesId = resultSet.getInt(SHOES_ID_INDEX);
        String typeRu = resultSet.getString(TYPE_RU_INDEX);
        String typeEn = resultSet.getString(TYPE_EN_INDEX);
        String brand = resultSet.getString(BRAND_INDEX);
        String modelName = resultSet.getString(MODEL_NAME_INDEX);
        List<String> descriptionRu = TextHandler.createListFromText(resultSet.getString(DESCRIPTION_RU_INDEX));
        List<String> descriptionEn = TextHandler.createListFromText(resultSet.getString(DESCRIPTION_EN_INDEX));
        String imagePath = resultSet.getString(IMAGE_PATH_INDEX);
        String color = resultSet.getString(COLOR_INDEX);
        BigDecimal cost = BigDecimal.valueOf(resultSet.getDouble(COST_INDEX)).setScale(2, RoundingMode.HALF_UP);
        boolean active = resultSet.getBoolean(ACTIVE_INDEX);
        ProductDetails productDetails = new ProductDetails(brand, modelName, descriptionRu, descriptionEn,
                imagePath, cost, active);
        return new Shoes(shoesId, typeRu, typeEn, productDetails, color);
    }

    public Shoes getShoesById(int id) {
        Shoes shoes = null;
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(GET_SHOES_BY_ID)) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    shoes = createShoes(resultSet);
                }
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, e);
        }
        return shoes;
    }

    public int countNumberShoes() {
        int number = 0;
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(COUNT_SHOES)) {
            while (resultSet.next()) {
                number = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, e);
        }
        return number;
    }
}
