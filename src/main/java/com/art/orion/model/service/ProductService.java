package com.art.orion.model.service;

import com.art.orion.model.dao.OrionDatabaseException;
import com.art.orion.model.dao.ProductDao;
import com.art.orion.model.dao.impl.ProductDaoJdbc;
import com.art.orion.model.entity.Accessory;
import com.art.orion.model.entity.Clothing;
import com.art.orion.model.entity.ProductCategory;
import com.art.orion.model.entity.Shoes;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.SQLException;
import java.util.List;

public class ProductService {
    private static final Logger logger = LogManager.getLogger();
    private static final ProductDao PRODUCT_DAO = new ProductDaoJdbc();

    private ProductService() {
    }

    public static void addProductToDatabase(Object product) throws ServiceException {
        try {
            PRODUCT_DAO.addProductToDatabase(product);
        } catch (SQLException | OrionDatabaseException e) {
            throw new ServiceException("Product is not added in the database", e);
        }
    }

    public static List<Accessory> searchAccessories(int limit, int offset) throws ServiceException {
        try {
            return PRODUCT_DAO.searchAccessories(limit, offset);
        } catch (OrionDatabaseException e) {
            throw new ServiceException("Database access error occurred while searching for accessories", e);
        }
    }

    public static List<Clothing> searchClothing(int limit, int offset) {
        return PRODUCT_DAO.searchClothing(limit, offset);
    }

    public static List<Shoes> searchShoes(int limit, int offset) {
        return PRODUCT_DAO.searchShoes(limit, offset);
    }

    public static Accessory getAccessoryById(int id) throws ServiceException {
        try {
            return PRODUCT_DAO.getAccessoryById(id);
        } catch (OrionDatabaseException e) {
            throw new ServiceException("Database access error occurred while retrieving accessory by id", e);
        }
    }

    public static Clothing getClothingById(int id) {
        return PRODUCT_DAO.getClothingById(id);
    }

    public static Shoes getShoesById(int id) {
        return PRODUCT_DAO.getShoesById(id);
    }

    public static int countNumberProducts(ProductCategory productCategory) {
        int numberProducts = 0;
        try {
            numberProducts = PRODUCT_DAO.countNumberProducts(productCategory);
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e);
        }
        return numberProducts;
    }
}
