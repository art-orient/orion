package com.art.orion.model.service;

import com.art.orion.model.dao.ProductDao;
import com.art.orion.model.dao.impl.ProductDaoJdbc;
import com.art.orion.model.entity.Accessory;
import com.art.orion.model.entity.Clothing;

import java.util.List;

public class ProductService {
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

    public static Accessory getAccessoryById(int id) {
        return PRODUCT_DAO.getAccessoryById(id);
    }

    public static int countNumberAccessories() {
        return PRODUCT_DAO.countNumberAccessories();
    }

    public static int countNumberClothing() {
        return PRODUCT_DAO.countNumberClothing();
    }

    public static Clothing getClothingById(int id) {
        return PRODUCT_DAO.getClothingById(id);
    }
}
