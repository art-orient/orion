package com.art.orion.model.service;

import com.art.orion.exception.ServiceException;
import com.art.orion.model.entity.Accessory;
import com.art.orion.model.entity.Clothing;
import com.art.orion.model.entity.ProductCategory;
import com.art.orion.model.entity.Shoes;

import java.util.List;
import java.util.Optional;

public interface ProductService {

    void addProductToDatabase(Object product) throws ServiceException;

    List<Accessory> searchAccessories(int limit, int offset, boolean isAdmin) throws ServiceException;

    List<Clothing> searchClothing(int limit, int offset, boolean isAdmin) throws ServiceException;

    List<Shoes> searchShoes(int limit, int offset, boolean isAdmin) throws ServiceException;

    Optional<Accessory> findAccessoryById(int id) throws ServiceException;

    Optional<Clothing> findClothingById(int id) throws ServiceException;

    Optional<Shoes> findShoesById(int id) throws ServiceException;

    int countNumberProducts(ProductCategory productCategory, boolean isAdmin) throws ServiceException;
}
