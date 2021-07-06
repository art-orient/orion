package com.art.orion.model.service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CartService {

    public static Map<Object, Long> groupProducts(List<Object> cart) {
        return cart.stream().collect(Collectors.groupingBy(p -> p, Collectors.counting()));
    }
}
