package com.art.orion.model.dao;

import com.art.orion.model.entity.Accessory;
import com.art.orion.model.entity.Clothing;
import com.art.orion.model.entity.ProductCategory;
import com.art.orion.model.entity.Shoes;
import com.art.orion.model.service.ServiceException;

import java.util.List;

public interface ProductDao {

    int addProductToDatabase(Object product);

    List<Accessory> searchAccessories(int limit, int offset);

    List<Clothing> searchClothing(int limit, int offset);

    List<Shoes> searchShoes(int limit, int offset);

    Accessory getAccessoryById(int id);

    Clothing getClothingById(int id);

    Shoes getShoesById(int id);

    int countNumberProducts(ProductCategory productCategory) throws ServiceException;
}
