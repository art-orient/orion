package com.art.orion.model.dao;

import com.art.orion.model.entity.Order;

public interface OrderDao {

    int addOrderToDatabase(Order order);
}
