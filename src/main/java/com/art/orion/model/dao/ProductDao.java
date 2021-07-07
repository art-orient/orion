package com.art.orion.model.dao;

import com.art.orion.model.entity.Accessory;

import java.util.List;

public interface ProductDao {

    int createProduct(Accessory accessory);

    List<Accessory> searchAccessories(int limit, int offset);

    Accessory getAccessoryById(int id);
}
