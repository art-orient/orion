package com.art.orion.model.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public interface CartService {

    Map<Object, Long> groupProducts(List<Object> cart);

    BigDecimal findTotalCost(Map<Object, Long> products);
}
