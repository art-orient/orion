package com.art.orion.model.entity;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

/**
 * Bean class of model layer represents the clothing as the product
 *
 * @author Aliaksandr Artsikhovich
 * @version 1.0
 */
public class Order {
    /**
     * Unique identification {@code int} value for the order
     */
    private long orderId;
    /**
     * Unique identification {@code String} value for user's account
     */
    private String username;
    /**
     * The {@link Date} the date of order
     */
    private Date orderDate;
    /**
     * The {@link Map} of {@link Object} and {@link Long} the list of products and their quantity
     */
    private Map<Object, Long> productsAndQuantity;
    /**
     * The {@link BigDecimal} the total cost of order
     */
    private BigDecimal orderCost;
    /**
     * The status of order
     */
    private boolean confirmationStatus;

    /**
     * The constructor with parameters.
     */
    public Order(long orderId, String username, Date orderDate, Map<Object, Long> productsAndQuantity,
                 BigDecimal orderCost, boolean confirmationStatus) {
        this.orderId = orderId;
        this.username = username;
        this.orderDate = orderDate;
        this.productsAndQuantity = productsAndQuantity;
        this.orderCost = orderCost;
        this.confirmationStatus = confirmationStatus;
    }

    /**
     * The constructor with parameters.
     */
    public Order(String username, Date orderDate, Map<Object, Long> productsAndAmount,
                 BigDecimal orderCost) {
        this.username = username;
        this.orderDate = orderDate;
        this.productsAndQuantity = productsAndAmount;
        this.orderCost = orderCost;
    }

    /**
     * Explicit default constructor.
     */
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

    public Map<Object, Long> getProductsAndQuantity() {
        return productsAndQuantity;
    }

    public BigDecimal getOrderCost() {
        return orderCost;
    }

    public boolean isConfirmed() {
        return confirmationStatus;
    }

    public void setConfirmationStatus(boolean confirmationStatus) {
        this.confirmationStatus = confirmationStatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return orderId == order.orderId;
    }

    @Override
    public int hashCode() {
        return (int) orderId;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Order{");
        sb.append("orderId=").append(orderId);
        sb.append(", username='").append(username).append('\'');
        sb.append(", orderDate=").append(orderDate);
        sb.append(", productsAndQuantity=").append(productsAndQuantity);
        sb.append(", orderCost=").append(orderCost);
        sb.append(", confirmationStatus=").append(confirmationStatus);
        sb.append('}');
        return sb.toString();
    }
}
