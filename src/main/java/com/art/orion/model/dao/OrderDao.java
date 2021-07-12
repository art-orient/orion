package com.art.orion.model.dao;

import com.art.orion.model.entity.Order;

import java.sql.SQLException;

public interface OrderDao {

    int addOrderToDatabase(Order order) throws SQLException, OrionDatabaseException;
}
