package com.art.orion.model.dao;

import com.art.orion.exception.OrionDatabaseException;
import com.art.orion.model.entity.Accessory;
import com.art.orion.model.entity.Clothing;
import com.art.orion.model.entity.ProductCategory;
import com.art.orion.model.entity.Shoes;
import com.art.orion.exception.ServiceException;

import java.util.List;
import java.util.Optional;

public interface ProductDao {

    void addProductToDatabase(Object product) throws OrionDatabaseException;

    List<Accessory> searchAccessories(int limit, int offset, boolean isAdmin) throws OrionDatabaseException;

    List<Clothing> searchClothing(int limit, int offset, boolean isAdmin) throws OrionDatabaseException;

    List<Shoes> searchShoes(int limit, int offset, boolean isAdmin) throws OrionDatabaseException;

    Optional<Accessory> findAccessoryById(int id) throws ServiceException, OrionDatabaseException;

    Optional<Clothing> findClothingById(int id) throws ServiceException, OrionDatabaseException;

    Optional<Shoes> findShoesById(int id) throws ServiceException, OrionDatabaseException;

    int countNumberProducts(ProductCategory productCategory, boolean isAdmin) throws ServiceException, OrionDatabaseException;
}
