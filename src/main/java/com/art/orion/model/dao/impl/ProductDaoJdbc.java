package com.art.orion.model.dao.impl;

import com.art.orion.model.dao.ProductDao;
import com.art.orion.model.entity.Accessory;
import com.art.orion.model.entity.Clothing;
import com.art.orion.model.entity.Shoes;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class ProductDaoJdbc implements ProductDao {
    private static final Logger logger = LogManager.getLogger();
    private static final AccessoryJdbc ACCESSORY_JDBC = new AccessoryJdbc();
    private static final ClothingJdbc CLOTHING_JDBC = new ClothingJdbc();
    private static final ShoesJdbc SHOES_JDBC = new ShoesJdbc();

    @Override
    public int addProductToDatabase(Object product) {
        int numberOfRecords = 0;
        if (product instanceof Accessory) {
            numberOfRecords = ACCESSORY_JDBC.addAccessoryToDatabase((Accessory) product);
        } else if (product instanceof Clothing) {
            numberOfRecords = CLOTHING_JDBC.addClothingToDatabase((Clothing) product);
        } else if (product instanceof Shoes) {
            numberOfRecords = SHOES_JDBC.addShoesToDatabase((Shoes) product);
        }
        logger.log(Level.DEBUG, "Add product in the database");
        return numberOfRecords;
    }

    @Override
    public List<Accessory> searchAccessories(int limit, int offset) {
        return ACCESSORY_JDBC.searchAccessories(limit, offset);
    }

    @Override
    public Accessory getAccessoryById(int id) {
        return ACCESSORY_JDBC.getAccessoryById(id);
    }

    @Override
    public int countNumberAccessories() {
        return ACCESSORY_JDBC.countNumberAccessories();
    }

    @Override
    public List<Clothing> searchClothing(int limit, int offset) {
        return CLOTHING_JDBC.searchClothing(limit, offset);
    }

    @Override
    public int countNumberClothing() {
        return CLOTHING_JDBC.countNumberClothing();
    }

    @Override
    public Clothing getClothingById(int id) {
        return CLOTHING_JDBC.getClothingById(id);
    }

    @Override
    public List<Shoes> searchShoes(int limit, int offset) {
        return SHOES_JDBC.searchShoes(limit, offset);
    }

    @Override
    public int countNumberShoes() {
        return SHOES_JDBC.countNumberShoes();
    }

    @Override
    public Shoes getShoesById(int id) {
        return SHOES_JDBC.getShoesById(id);
    }
}
