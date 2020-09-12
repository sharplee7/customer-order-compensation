package com.example.bff.web;

public class CustomerOrderState {
    private Long customerId;

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    private Long orderId;

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getCreditLimit() {
        return creditLimit;
    }

    public void setCreditLimit(Long creditLimit) {
        this.creditLimit = creditLimit;
    }

    private String name;
    private Long creditLimit;

    public OrderState getOrderState() {
        return orderState;
    }

    public void setOrderState(OrderState orderState) {
        this.orderState = orderState;
    }

    private OrderState orderState;

    public CustomerOrderState(Long id, String name, Long creditLimit, OrderState orderState) {
        this.customerId = id;
        this.name = name;
        this.creditLimit = creditLimit;
        this.orderState = orderState;
    }
    public CustomerOrderState() {}
}
