package com.example.order.domain;

import com.example.order.domain.common.OrderDetail;
import com.example.order.domain.common.OrderDetails;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface OrderRepository extends CrudRepository<OrderDetail, Long> {
}
