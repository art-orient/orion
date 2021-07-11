package com.art.orion.model.dao.impl;

import com.art.orion.controller.command.util.TextHandler;
import com.art.orion.model.dao.OrionDatabaseException;
import com.art.orion.model.dao.ProductDao;
import com.art.orion.model.entity.Accessory;
import com.art.orion.model.entity.Clothing;
import com.art.orion.model.entity.ProductCategory;
import com.art.orion.model.entity.ProductDetails;
import com.art.orion.model.entity.Shoes;
import com.art.orion.model.pool.ConnectionPool;
import com.art.orion.model.service.ServiceException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import static com.art.orion.util.Constant.ACTIVE;
import static com.art.orion.util.Constant.BRAND;
import static com.art.orion.util.Constant.COST;
import static com.art.orion.util.Constant.DESCRIPTION_EN;
import static com.art.orion.util.Constant.DESCRIPTION_RU;
import static com.art.orion.util.Constant.IMAGE;
import static com.art.orion.util.Constant.MODEL_NAME;

public class ProductDaoJdbc implements ProductDao {
    private static final Logger logger = LogManager.getLogger();
    private static final AccessoryJdbc ACCESSORY_JDBC = new AccessoryJdbc();
    private static final ClothingJdbc CLOTHING_JDBC = new ClothingJdbc();
    private static final ShoesJdbc SHOES_JDBC = new ShoesJdbc();
    private static final String COUNT_ACCESSORIES = "SELECT count(*) FROM accessories WHERE active = 1";
    private static final String COUNT_CLOTHING = "SELECT count(*) FROM clothing WHERE active = 1";
    private static final String COUNT_SHOES = "SELECT count(*) FROM shoes WHERE active = 1";

    @Override
    public int addProductToDatabase(Object product) {
        int numberOfRecords = 0;
        if (product instanceof Accessory) {
            numberOfRecords = ACCESSORY_JDBC.addAccessoryToDatabase((Accessory) product);
        } else if (product instanceof Clothing) {
            numberOfRecords = CLOTHING_JDBC.addClothingToDatabase((Clothing) product);
        } else if (product instanceof Shoes) {
            numberOfRecords = SHOES_JDBC.addShoesToDatabase((Shoes) product);
        }
        logger.log(Level.DEBUG, "Add product in the database");
        return numberOfRecords;
    }

    @Override
    public List<Accessory> searchAccessories(int limit, int offset) {
        return ACCESSORY_JDBC.searchAccessories(limit, offset);
    }

    @Override
    public Accessory getAccessoryById(int id) {
        return ACCESSORY_JDBC.getAccessoryById(id);
    }

    @Override
    public List<Clothing> searchClothing(int limit, int offset) {
        return CLOTHING_JDBC.searchClothing(limit, offset);
    }

    @Override
    public Clothing getClothingById(int id) {
        return CLOTHING_JDBC.getClothingById(id);
    }

    @Override
    public List<Shoes> searchShoes(int limit, int offset) {
        return SHOES_JDBC.searchShoes(limit, offset);
    }

    @Override
    public Shoes getShoesById(int id) {
        return SHOES_JDBC.getShoesById(id);
    }

    public int countNumberProducts(ProductCategory productCategory) throws ServiceException {
        int number = 0;
        String query;
        switch (productCategory) {
            case ACCESSORIES -> query = COUNT_ACCESSORIES;
            case CLOTHING -> query = COUNT_CLOTHING;
            case SHOES -> query = COUNT_SHOES;
            default -> throw new ServiceException("Invalid product category - " + productCategory);
        }
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            while (resultSet.next()) {
                number = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, e);
        }
        return number;
    }

    public static ProductDetails createProductDetails (ResultSet resultSet) throws OrionDatabaseException {
        ProductDetails productDetails;
        try {
            String brand = resultSet.getString(BRAND);
            String modelName = resultSet.getString(MODEL_NAME);
            List<String> descriptionRu = TextHandler.createListFromText(resultSet.getString(DESCRIPTION_RU));
            List<String> descriptionEn = TextHandler.createListFromText(resultSet.getString(DESCRIPTION_EN));
            String imagePath = resultSet.getString(IMAGE);
            BigDecimal cost = BigDecimal.valueOf(resultSet.getDouble(COST)).setScale(2, RoundingMode.HALF_UP);
            boolean active = resultSet.getBoolean(ACTIVE);
            productDetails = new ProductDetails(brand, modelName, descriptionRu, descriptionEn,
                    imagePath, cost, active);
        } catch (SQLException e) {
            throw new OrionDatabaseException();
        }
        return productDetails;
    }
}
