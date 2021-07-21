package com.art.orion.model.service;

import com.art.orion.exception.ServiceException;
import com.art.orion.model.entity.Accessory;
import com.art.orion.model.entity.Clothing;
import com.art.orion.model.entity.ProductCategory;
import com.art.orion.model.entity.Shoes;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

/**
 * The {@code ProductService} interface represents product service
 *
 * @author Aliaksandr Artsikhovich
 * @version 1.0
 */
public interface ProductService {

    /**
     * Adds a new product to the database
     *
     * @param product {@link Object} the product from different category
     * @throws ServiceException the service exception
     */
    void addProductToDatabase(Object product) throws ServiceException;

    /**
     * Finds all accessories
     *
     * @param limit number of products per page
     * @param offset index of the first product on the page
     * @param isAdmin is the user an admin
     * @return {@link List} of {@link Accessory} the list of found accessories
     * @throws ServiceException the service exception
     */
    List<Accessory> searchAccessories(int limit, int offset, boolean isAdmin) throws ServiceException;

    /**
     * Finds all clothing
     *
     * @param limit number of products per page
     * @param offset index of the first product on the page
     * @param isAdmin is the user an admin
     * @return {@link List} of {@link Clothing} the list of found clothing
     * @throws ServiceException the service exception
     */
    List<Clothing> searchClothing(int limit, int offset, boolean isAdmin) throws ServiceException;

    /**
     * Finds all shoes
     *
     * @param limit number of products per page
     * @param offset index of the first product on the page
     * @param isAdmin is the user an admin
     * @return {@link List} of {@link Shoes} the list of found shoes
     * @throws ServiceException the service exception
     */
    List<Shoes> searchShoes(int limit, int offset, boolean isAdmin) throws ServiceException;

    /**
     * Finds an accessory by id
     *
     * @param id the id of product
     * @return {@link Optional} of {@link Accessory} received from database
     * @throws ServiceException the service exception
     */
    Optional<Accessory> findAccessoryById(int id) throws ServiceException;

    /**
     * Finds clothing by id
     *
     * @param id the id of product
     * @return {@link Optional} of {@link Clothing} received from database
     * @throws ServiceException the service exception
     */
    Optional<Clothing> findClothingById(int id) throws ServiceException;

    /**
     * Finds shoes by id
     *
     * @param id the id of product
     * @return {@link Optional} of {@link Shoes} received from database
     * @throws ServiceException the service exception
     */
    Optional<Shoes> findShoesById(int id) throws ServiceException;

    /**
     * Finds the number of product in the category
     *
     * @param productCategory {@link ProductCategory} the category of product
     * @param isAdmin is the user an admin
     * @return the number of product in the category
     * @throws ServiceException the service exception
     */
    int countNumberProducts(ProductCategory productCategory, boolean isAdmin) throws ServiceException;

    /**
     * Edits the product data
     *
     * @param product {@link Object} the product from different category
     * @throws ServiceException the service exception
     */
    void updateProduct(Object product) throws ServiceException;

    /**
     * Validates the product brand, model name and cost
     *
     * @param brand {@link String} the name of brand
     * @param modelName {@link String} the name of model
     * @param cost {@link BigDecimal} the cost of product
     * @return the boolean are product data valid
     */
    boolean isProductValid(String brand, String modelName, BigDecimal cost);
}
