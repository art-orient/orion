package com.art.orion.model.dao;

import com.art.orion.model.entity.Accessory;
import com.art.orion.model.entity.Clothing;

import java.util.List;

public interface ProductDao {

    int addProductToDatabase(Object product);

    List<Accessory> searchAccessories(int limit, int offset);

    List<Clothing> searchClothing(int limit, int offset);

    Accessory getAccessoryById(int id);

    Clothing getClothingById(int id);

    int countNumberAccessories();

    int countNumberClothing();
}
