package com.art.orion.model.dao;

import com.art.orion.model.entity.Accessory;
import com.art.orion.model.entity.Clothing;
import com.art.orion.model.entity.ProductCategory;
import com.art.orion.model.entity.Shoes;
import com.art.orion.model.service.ServiceException;

import java.sql.SQLException;
import java.util.List;

public interface ProductDao {

    void addProductToDatabase(Object product) throws SQLException, OrionDatabaseException;

    List<Accessory> searchAccessories(int limit, int offset, boolean isAdmin) throws OrionDatabaseException;

    List<Clothing> searchClothing(int limit, int offset) throws OrionDatabaseException;

    List<Shoes> searchShoes(int limit, int offset) throws OrionDatabaseException;

    Accessory getAccessoryById(int id) throws ServiceException, OrionDatabaseException;

    Clothing getClothingById(int id) throws ServiceException, OrionDatabaseException;

    Shoes getShoesById(int id) throws ServiceException, OrionDatabaseException;

    int countNumberProducts(ProductCategory productCategory, boolean isAdmin) throws ServiceException, OrionDatabaseException;
}
