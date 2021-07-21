package com.art.orion.model.dao;

import com.art.orion.exception.OrionDatabaseException;
import com.art.orion.model.entity.Accessory;
import com.art.orion.model.entity.Clothing;
import com.art.orion.model.entity.ProductCategory;
import com.art.orion.model.entity.Shoes;

import java.util.List;
import java.util.Optional;

/**
 * The {@code ProductDao} interface for working with database tables: accessories, clothing and shoes
 *
 * @author Aliaksandr Artsikhovich
 * @version 1.0
 */
public interface ProductDao {

    /**
     * Saves the product
     *
     * @param product {@link Object} the product from different categories
     * @throws OrionDatabaseException the OrionDatabaseException exception
     */
    void addProductToDatabase(Object product) throws OrionDatabaseException;

    /**
     * Finds all accessories
     *
     * @param limit number of products per page
     * @param offset index of the first product on the page
     * @param isAdmin is the user an admin
     * @return {@link List} of {@link Accessory} the list of found accessories
     * @throws OrionDatabaseException the OrionDatabaseException exception
     */
    List<Accessory> searchAccessories(int limit, int offset, boolean isAdmin) throws OrionDatabaseException;

    /**
     * Finds all clothing
     *
     * @param limit number of products per page
     * @param offset index of the first product on the page
     * @param isAdmin is the user an admin
     * @return {@link List} of {@link Clothing} the list of found clothing
     * @throws OrionDatabaseException the OrionDatabaseException exception
     */
    List<Clothing> searchClothing(int limit, int offset, boolean isAdmin) throws OrionDatabaseException;

    /**
     * Finds all shoes
     *
     * @param limit number of products per page
     * @param offset index of the first product on the page
     * @param isAdmin is the user an admin
     * @return {@link List} of {@link Shoes} the list of found shoes
     * @throws OrionDatabaseException the OrionDatabaseException exception
     */
    List<Shoes> searchShoes(int limit, int offset, boolean isAdmin) throws OrionDatabaseException;

    /**
     * Finds an accessory by id
     *
     * @param id the id of product
     * @return {@link Optional} of {@link Accessory} received from database
     * @throws OrionDatabaseException the OrionDatabaseException exception
     */
    Optional<Accessory> findAccessoryById(int id) throws OrionDatabaseException;

    /**
     * Finds clothing by id
     *
     * @param id the id of product
     * @return {@link Optional} of {@link Clothing} received from database
     * @throws OrionDatabaseException the OrionDatabaseException exception
     */
    Optional<Clothing> findClothingById(int id) throws OrionDatabaseException;

    /**
     * Finds shoes by id
     *
     * @param id the id of product
     * @return {@link Optional} of {@link Shoes} received from database
     * @throws OrionDatabaseException the OrionDatabaseException exception
     */
    Optional<Shoes> findShoesById(int id) throws OrionDatabaseException;

    /**
     * Finds the number of product in the category
     *
     * @param productCategory {@link ProductCategory} the category of product
     * @param isAdmin is the user an admin
     * @return the number of product in the category
     * @throws OrionDatabaseException the OrionDatabaseException exception
     */
    int countNumberProducts(ProductCategory productCategory, boolean isAdmin) throws OrionDatabaseException;

    /**
     * Edits the product data
     *
     * @param product {@link Object} the product from different category
     * @throws OrionDatabaseException the OrionDatabaseException exception
     */
    void updateProduct(Object product) throws OrionDatabaseException;
}
