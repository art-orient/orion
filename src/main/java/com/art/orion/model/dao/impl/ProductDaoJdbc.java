package com.art.orion.model.dao.impl;

import com.art.orion.model.dao.ProductDao;
import com.art.orion.model.entity.Accessory;
import com.art.orion.model.pool.ConnectionPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class ProductDaoJdbc implements ProductDao {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public int createProduct(Accessory accessory) {
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
                PreparedStatement statement = connection.prepareStatement(INSERT_ACCESSORY)) {

        }
        return 0;
    }
}
