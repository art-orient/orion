package com.art.orion.model.dao.impl;

import com.art.orion.model.dao.ProductDao;
import com.art.orion.model.entity.Accessory;
import com.art.orion.model.entity.Clothing;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class ProductDaoJdbc implements ProductDao {
    private static final Logger logger = LogManager.getLogger();
    AccessoryJdbc accessoryJdbc = new AccessoryJdbc();
    ClothingJdbc clothingJdbc = new ClothingJdbc();

    @Override
    public int addProductToDatabase(Object product) {
        Accessory accessory = null;
        int numberOfRecords = 0;
        if (product instanceof Accessory) {
            accessory = (Accessory) product;
            numberOfRecords = accessoryJdbc.addAccessoryToDatabase(accessory);
        }
        return numberOfRecords;
    }

    @Override
    public List<Accessory> searchAccessories(int limit, int offset) {
        return accessoryJdbc.searchAccessories(limit, offset);
    }

    @Override
    public Accessory getAccessoryById(int id) {
        return accessoryJdbc.getAccessoryById(id);
    }

    @Override
    public int countNumberAccessories() {
        return accessoryJdbc.countNumberAccessories();
    }

    @Override
    public List<Clothing> searchClothing(int limit, int offset) {
        return clothingJdbc.searchClothing(limit, offset);
    }

    @Override
    public int countNumberClothing() {
        return clothingJdbc.countNumberClothing();
    }
}
