package com.art.orion.model.service;

import com.art.orion.model.entity.Accessory;
import com.art.orion.model.entity.Clothing;
import com.art.orion.model.entity.Shoes;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CartService {

    private CartService() {
    }

    public static Map<Object, Long> groupProducts(List<Object> cart) {
        return cart.stream().collect(Collectors.groupingBy(p -> p, Collectors.counting()));
    }

    public static BigDecimal findTotalCost(List<Object> products) {
        BigDecimal totalCost = BigDecimal.ZERO;
        BigDecimal cost = BigDecimal.ZERO;
        for (Object o : products) {
            if (o instanceof Accessory) {
                cost = ((Accessory) o).getProductDetails().getCost();
            } else if (o instanceof Clothing) {
                cost = ((Clothing) o).getProductDetails().getCost();
            } else if (o instanceof Shoes) {
                cost = ((Shoes) o).getProductDetails().getCost();
            }
            totalCost = totalCost.add(cost);
        }
        return totalCost;
    }
}
