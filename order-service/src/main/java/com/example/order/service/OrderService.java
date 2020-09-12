package com.example.order.service;

import com.example.order.aync.OrderProducer;
import com.example.order.domain.common.OrderDetail;
import com.example.order.domain.OrderRepository;
import com.example.order.domain.common.OrderDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public class OrderService {

  @Autowired
  private OrderProducer orderProducer;

  @Autowired
  private OrderRepository orderRepository;

  /**
   * 주문 생성
   * @param orderDetails
   * @return
   */
  public OrderDetail createOrder(OrderDetails orderDetails) {
    OrderDetail order =  OrderDetail.createOrder(orderDetails);
    orderRepository.save(order);
    orderProducer.publishOrder(order);
    return order;
  }

  public void approveOrder(Long orderId) {
    OrderDetail order = orderRepository
            .findById(orderId)
            .orElseThrow(() -> new IllegalArgumentException(String.format("order with id %s not found", orderId)));
    order.noteCreditReserved();

  }

  public void rejectOrder(Long orderId) {
    OrderDetail order = orderRepository
            .findById(orderId)
            .orElseThrow(() -> new IllegalArgumentException(String.format("order with id %s not found", orderId)));
    order.noteCreditReservationFailed();

  }
}
