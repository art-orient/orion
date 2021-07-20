package com.art.orion.model.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * The {@code CartService} interface represents cart service
 *
 * @author Aliaksandr Artsikhovich
 */
public interface CartService {

    /**
     * Groups the products by their name and finds their quantity
     *
     * @param cart {@link List<Object>} the list of different categories of products
     * @return {@link Map} of {@link Object} amd {@link Long} the list and the quantity of products
     */
    Map<Object, Long> groupProducts(List<Object> cart);

    /**
     * Finds total cost of order
     *
     * @param products {@link Map} of {@link Object} amd {@link Long} the list and the quantity of products
     * @return {@link BigDecimal} cost of order
     */
    BigDecimal findTotalCost(Map<Object, Long> products);
}
