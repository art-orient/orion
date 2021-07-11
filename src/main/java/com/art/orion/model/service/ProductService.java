package com.art.orion.model.service;

import com.art.orion.model.dao.ProductDao;
import com.art.orion.model.dao.impl.ProductDaoJdbc;
import com.art.orion.model.entity.Accessory;
import com.art.orion.model.entity.Clothing;
import com.art.orion.model.entity.ProductCategory;
import com.art.orion.model.entity.Shoes;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class ProductService {
    private static final Logger logger = LogManager.getLogger();
    private static final ProductDao PRODUCT_DAO = new ProductDaoJdbc();

    private ProductService() {
    }

    public static int addProductToDatabase(Object product) {
        return PRODUCT_DAO.addProductToDatabase(product);
    }

    public static List<Accessory> searchAccessories(int limit, int offset) {
        return PRODUCT_DAO.searchAccessories(limit, offset);
    }

    public static List<Clothing> searchClothing(int limit, int offset) {
        return PRODUCT_DAO.searchClothing(limit, offset);
    }

    public static List<Shoes> searchShoes(int limit, int offset) {
        return PRODUCT_DAO.searchShoes(limit, offset);
    }

    public static Accessory getAccessoryById(int id) {
        return PRODUCT_DAO.getAccessoryById(id);
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
