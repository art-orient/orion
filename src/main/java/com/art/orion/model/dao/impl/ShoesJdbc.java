package com.art.orion.model.dao.impl;

import com.art.orion.exception.OrionDatabaseException;
import com.art.orion.model.entity.ProductDetails;
import com.art.orion.model.entity.Shoes;
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

import static com.art.orion.model.dao.column.ShoesColumn.ACTIVE;
import static com.art.orion.model.dao.column.ShoesColumn.ACTIVE_INDEX;
import static com.art.orion.model.dao.column.ShoesColumn.BRAND;
import static com.art.orion.model.dao.column.ShoesColumn.BRAND_INDEX;
import static com.art.orion.model.dao.column.ShoesColumn.COLOR;
import static com.art.orion.model.dao.column.ShoesColumn.COLOR_INDEX;
import static com.art.orion.model.dao.column.ShoesColumn.COST;
import static com.art.orion.model.dao.column.ShoesColumn.COST_INDEX;
import static com.art.orion.model.dao.column.ShoesColumn.DESCRIPTION_EN;
import static com.art.orion.model.dao.column.ShoesColumn.DESCRIPTION_EN_INDEX;
import static com.art.orion.model.dao.column.ShoesColumn.DESCRIPTION_RU;
import static com.art.orion.model.dao.column.ShoesColumn.DESCRIPTION_RU_INDEX;
import static com.art.orion.model.dao.column.ShoesColumn.IMAGE_PATH;
import static com.art.orion.model.dao.column.ShoesColumn.IMAGE_PATH_INDEX;
import static com.art.orion.model.dao.column.ShoesColumn.MODEL_NAME;
import static com.art.orion.model.dao.column.ShoesColumn.MODEL_NAME_INDEX;
import static com.art.orion.model.dao.column.ShoesColumn.SHOES_ID;
import static com.art.orion.model.dao.column.ShoesColumn.SHOES_ID_INDEX;
import static com.art.orion.model.dao.column.ShoesColumn.TYPE_EN;
import static com.art.orion.model.dao.column.ShoesColumn.TYPE_EN_INDEX;
import static com.art.orion.model.dao.column.ShoesColumn.TYPE_RU;
import static com.art.orion.model.dao.column.ShoesColumn.TYPE_RU_INDEX;
import static com.art.orion.util.Constant.DATABASE_EXCEPTION;

public class ShoesJdbc {
    private static final Logger logger = LogManager.getLogger();
    private static final ShoesJdbc INSTANCE = new ShoesJdbc();
    private static final String SELECT = "SELECT shoes_id, type_Ru, type_En, brand, model_name, " +
            "description_RU, description_EN, image_path, color, cost, active FROM shoes ";
    private static final String SELECT_ACTIVE_SHOES = SELECT + "WHERE active = 1 LIMIT ? OFFSET ?";
    private static final String INSERT_SHOES = "INSERT INTO shoes " +
            "(type_Ru, type_En, brand, model_name, description_RU, description_EN, image_path, color, cost, active)" +
            " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String FIND_SHOES_BY_ID = SELECT + "WHERE shoes_id = ?";
    private static final String SELECT_ALL_SHOES = SELECT + "LIMIT ? OFFSET ?";
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

    private ShoesJdbc() {
    }

    public static ShoesJdbc getInstance() {
        return INSTANCE;
    }

    public void addShoesToDatabase(Shoes shoes) throws OrionDatabaseException {
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(INSERT_SHOES)){
            ProductDetails productDetails = shoes.getProductDetails();
            ProductDaoJdbc.setProductDetailsInStatement(statement, productDetails, indices);
            statement.setString(TYPE_RU_INDEX - 1, shoes.getTypeRu());
            statement.setString(TYPE_EN_INDEX - 1, shoes.getTypeEn());
            statement.setString(COLOR_INDEX - 1, shoes.getColor());
            statement.executeUpdate();
            logger.log(Level.INFO, () -> "The shoes is saved in the database");
        } catch (SQLException e) {
            throw new OrionDatabaseException(DATABASE_EXCEPTION, e);
        }
    }

    public List<Shoes> searchShoes(int limit, int offset, boolean isAdmin) throws OrionDatabaseException {
        List<Shoes> products = new ArrayList<>();
        String query = SELECT_ACTIVE_SHOES;
        if (isAdmin) {
            query = SELECT_ALL_SHOES;
        }
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, limit);
            statement.setInt(2, offset);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Shoes shoes = createShoes(resultSet);
                    products.add(shoes);
                }
            }
            logger.log(Level.INFO, () -> "Shoes search completed successfully");
        } catch (SQLException e) {
            throw new OrionDatabaseException(DATABASE_EXCEPTION, e);
        }
        return products;
    }

    public Optional<Shoes> findShoesById(int id) throws OrionDatabaseException {
        Optional<Shoes> optionalShoes;
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_SHOES_BY_ID)) {
            statement.setInt(SHOES_ID_INDEX, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    Shoes shoes = createShoes(resultSet);
                    optionalShoes = Optional.of(shoes);
                } else {
                    throw new OrionDatabaseException(String.format("Shoes with id = %s is not found", id));
                }
            }
            logger.log(Level.DEBUG, () -> String.format("Shoes with id = %s got from the database", id));
        } catch (SQLException e) {
            throw new OrionDatabaseException(DATABASE_EXCEPTION, e);
        }
        return optionalShoes;
    }

    private Shoes createShoes(ResultSet resultSet) throws SQLException, OrionDatabaseException {
        int shoesId = resultSet.getInt(SHOES_ID);
        String typeRu = resultSet.getString(TYPE_RU);
        String typeEn = resultSet.getString(TYPE_EN);
        ProductDetails productDetails = ProductDaoJdbc.createProductDetails(resultSet);
        String color = resultSet.getString(COLOR);
        logger.log(Level.DEBUG, () -> "Shoes creation completed successfully");
        return new Shoes(shoesId, typeRu, typeEn, productDetails, color);
    }
}
