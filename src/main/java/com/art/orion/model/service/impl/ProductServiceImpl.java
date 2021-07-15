package com.art.orion.model.service.impl;

import com.art.orion.exception.OrionDatabaseException;
import com.art.orion.exception.ServiceException;
import com.art.orion.model.dao.ProductDao;
import com.art.orion.model.dao.impl.ProductDaoJdbc;
import com.art.orion.model.entity.Accessory;
import com.art.orion.model.entity.Clothing;
import com.art.orion.model.entity.ProductCategory;
import com.art.orion.model.entity.Shoes;
import com.art.orion.model.service.ProductService;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class ProductServiceImpl implements ProductService {
    private static final Logger logger = LogManager.getLogger();
    private static final ProductDao PRODUCT_DAO = ProductDaoJdbc.getInstance();

    @Override
    public void addProductToDatabase(Object product) throws ServiceException {
        try {
            PRODUCT_DAO.addProductToDatabase(product);
            logger.log(Level.DEBUG, () -> "ProductService - product added to the database");
        } catch (SQLException | OrionDatabaseException e) {
            throw new ServiceException("Product is not added in the database", e);
        }
    }

    @Override
    public List<Accessory> searchAccessories(int limit, int offset, boolean isAdmin) throws ServiceException {
        try {
            logger.log(Level.DEBUG, () -> "ProductService - searching for accessories");
            return PRODUCT_DAO.searchAccessories(limit, offset, isAdmin);
        } catch (OrionDatabaseException e) {
            throw new ServiceException("Database access error occurred while searching for accessories", e);
        }
    }

    @Override
    public List<Clothing> searchClothing(int limit, int offset) throws ServiceException {
        try {
            logger.log(Level.DEBUG, () -> "ProductService - searching for clothing");
            return PRODUCT_DAO.searchClothing(limit, offset);
        } catch (OrionDatabaseException e) {
            throw new ServiceException("Database access error occurred while searching for clothing", e);
        }
    }

    @Override
    public List<Shoes> searchShoes(int limit, int offset) throws ServiceException {
        try {
            logger.log(Level.DEBUG, () -> "ProductService - searching for shoes");
            return PRODUCT_DAO.searchShoes(limit, offset);
        } catch (OrionDatabaseException e) {
            throw new ServiceException("Database access error occurred while searching for shoes", e);
        }
    }

    @Override
    public Optional<Accessory> findAccessoryById(int id) throws ServiceException {
        try {
            logger.log(Level.DEBUG, () -> "ProductService - getting an accessory by id");
            return PRODUCT_DAO.findAccessoryById(id);
        } catch (OrionDatabaseException e) {
            throw new ServiceException("Database access error occurred while retrieving accessory by id", e);
        }
    }

    @Override
    public Optional<Clothing> findClothingById(int id) throws ServiceException {
        try {
            logger.log(Level.DEBUG, () -> "ProductService - getting clothing by id");
            return PRODUCT_DAO.findClothingById(id);
        } catch (OrionDatabaseException e) {
            throw new ServiceException("Database access error occurred while retrieving clothing by id", e);
        }
    }

    @Override
    public Optional<Shoes> findShoesById(int id) throws ServiceException {
        try {
            logger.log(Level.DEBUG, () -> "ProductService - getting shoes by id");
            return PRODUCT_DAO.findShoesById(id);
        } catch (OrionDatabaseException e) {
            throw new ServiceException("Database access error occurred while retrieving clothing by id", e);
        }
    }

    @Override
    public int countNumberProducts(ProductCategory productCategory, boolean isAdmin) throws ServiceException {
        int numberProducts;
        try {
            numberProducts = PRODUCT_DAO.countNumberProducts(productCategory, isAdmin);
            logger.log(Level.DEBUG, () -> "ProductService - counting the quantity of products");
        } catch (OrionDatabaseException e) {
            throw new ServiceException("Database access error occurred while counting the quantity of product", e);
        }
        return numberProducts;
    }
}
