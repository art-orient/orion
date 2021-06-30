package com.art.orion.model.service;

import com.art.orion.model.dao.ProductDao;
import com.art.orion.model.dao.impl.ProductDaoJdbc;
import com.art.orion.model.entity.Accessory;

public class ProductService {
    private static final ProductDao productDao = new ProductDaoJdbc();

    private ProductService() {
    }

    public static int createProduct(Accessory accessory) {
        return productDao.createProduct(accessory);
    }
}
