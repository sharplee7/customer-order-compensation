package com.example.customer.domain.common;


import javax.persistence.*;

//@Entity(name = "learningtest.org.hibernate.tutorial.annotations.Event")

//@Entity(name = "learningtest.org.hibernate.tutorial.em.Event")
@Entity(name = "com.example.customer.domain.common.OrderDetail")
@Table(name="orders")
@Access(AccessType.FIELD)
public class OrderDetail {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private OrderState state;

  @Embedded
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
