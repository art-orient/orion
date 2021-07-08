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
        int numberOfRecords = 0;
        if (product instanceof Accessory) {
            Accessory accessory = (Accessory) product;
            numberOfRecords = accessoryJdbc.addAccessoryToDatabase(accessory);
        } else if (product instanceof Clothing) {
            Clothing clothing = (Clothing) product;
            numberOfRecords = clothingJdbc.addClothingToDatabase(clothing);
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

    @Override
    public Clothing getClothingById(int id) {
        return clothingJdbc.getClothingById(id);
    }
}
