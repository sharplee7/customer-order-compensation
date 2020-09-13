package com.example.common.domain;




public class OrderDetail {

  private Long id;

  private OrderState state;

  private OrderDetails orderDetails;

  public OrderDetail() {
  }

  public OrderDetail(OrderDetails orderDetails) {
    this.orderDetails = orderDetails;
    this.state = OrderState.PENDING;
  }

  public static OrderDetail createOrder(OrderDetails orderDetails) {
    OrderDetail order = new OrderDetail(orderDetails);
    return order;
  }

  public Long getId() {
    return id;
  }

  public void noteCreditReserved() {
    this.state = OrderState.APPROVED;
  }

  public void noteCreditReservationFailed() {
    this.state = OrderState.REJECTED;
  }

  public OrderState getState() {
    return state;
  }

  public OrderDetails getOrderDetails() {
    return orderDetails;
  }
}
