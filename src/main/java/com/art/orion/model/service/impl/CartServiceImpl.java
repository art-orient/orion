package com.art.orion.model.service.impl;

import com.art.orion.model.entity.Accessory;
import com.art.orion.model.entity.Clothing;
import com.art.orion.model.entity.Shoes;
import com.art.orion.model.service.CartService;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * The {@code CartServiceImpl} class represents cart service implementation
 *
 * @author Aliaksandr Artsikhovich
 * @see CartService
 */
public class CartServiceImpl implements CartService {

    @Override
    public Map<Object, Long> groupProducts(List<Object> cart) {
        return cart.stream().collect(Collectors.groupingBy(p -> p, Collectors.counting()));
    }

    @Override
    public BigDecimal findTotalCost(Map<Object, Long> products) {
        BigDecimal totalCost = BigDecimal.ZERO;
        BigDecimal cost = BigDecimal.ZERO;
        for (Map.Entry<Object, Long> entry : products.entrySet()) {
            if (entry.getKey() instanceof Accessory) {
                cost = ((Accessory) entry.getKey()).getProductDetails().getCost();
                cost = cost.multiply(BigDecimal.valueOf(entry.getValue()));
            } else if (entry.getKey() instanceof Clothing) {
                cost = ((Clothing) entry.getKey()).getProductDetails().getCost();
                cost = cost.multiply(BigDecimal.valueOf(entry.getValue()));
            } else if (entry.getKey() instanceof Shoes) {
                cost = ((Shoes) entry.getKey()).getProductDetails().getCost();
                cost = cost.multiply(BigDecimal.valueOf(entry.getValue()));
            }
            totalCost = totalCost.add(cost);
        }
        return totalCost;
    }
}
