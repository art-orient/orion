package com.art.orion.model.entity;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

public class Order {
    private long orderId;
    private String username;
    private Date orderDate;
    private Map<Product, Long> listOfProducts;
    private BigDecimal orderCost;
    private boolean confirmationStatus;

    public Order(long orderId, String username, Date orderDate, Map<Product, Long> listOfProducts,
                 BigDecimal orderCost, boolean confirmationStatus) {
        this.orderId = orderId;
        this.username = username;
        this.orderDate = orderDate;
        this.listOfProducts = listOfProducts;
        this.orderCost = orderCost;
        this.confirmationStatus = confirmationStatus;
    }

    public Order(String username, Date orderDate, Map<Product, Long> listOfProducts, BigDecimal orderCost, boolean confirmationStatus) {
        this.username = username;
        this.orderDate = orderDate;
        this.listOfProducts = listOfProducts;
        this.orderCost = orderCost;
        this.confirmationStatus = confirmationStatus;
    }

    public Order() {
    }

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public Map<Product, Long> getListOfProducts() {
        return listOfProducts;
    }

    public void setListOfProducts(Map<Product, Long> listOfProducts) {
        this.listOfProducts = listOfProducts;
    }

    public BigDecimal getOrderCost() {
        return orderCost;
    }

    public void setOrderCost(BigDecimal orderCost) {
        this.orderCost = orderCost;
    }

    public boolean isConfirmed() {
        return confirmationStatus;
    }

    public void setConfirmationStatus(boolean confirmationStatus) {
        this.confirmationStatus = confirmationStatus;
    }
}
